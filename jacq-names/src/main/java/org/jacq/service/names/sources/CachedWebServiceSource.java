package org.jacq.service.names.sources;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import de.ailis.pherialize.Pherialize;
import org.jacq.common.model.jpa.openup.TblWebserviceCache;
import org.jacq.common.model.jpa.openup.TblWebserviceCacheDiffs;
import org.jacq.common.model.names.NameParserResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Abstract base class for all Common Names Web service sources using the Web service cache
 *
 * @author re1
 */
@Transactional
public abstract class CachedWebServiceSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(CachedWebServiceSource.class.getName());

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * Unique id for the service implementation set from @PostConstruct annotated method
     */
    private int serviceId;
    /**
     * Seconds from a UNIX timestamp the cached response is timed out. Can be set from @PostConstruct annotated method
     */
    private long timeout = 86400; // 24 hours

    /**
     * Helper function to create a PHP SHA1 hash for a given string.
     *
     * @param s String to return PHP SHA1 hash for
     * @return PHP SHA1 hash of string s
     */
    private String phpSha1(String s) {
        try {
            // create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            // create Hex String for comparision with existing PHP SHA1 hashes
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest.digest()) hexString.append(String.format("%02X", 0xFF & b));
            // existing query hashes are also in lower case
            s = hexString.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Returns the cached response for a given query.
     *
     * @param query Query to look for
     * @return null if no valid response was found, else the response
     */
    private TblWebserviceCache getWebServiceCache(String query) {
        // PHP serialize queries and create a SHA1 hash for a quicker comparison with existing queries
        query = phpSha1(Pherialize.serialize(query));

        // build SQL lookup query for this service and query
        String lookupQuery = "SELECT row FROM TblWebserviceCache row WHERE row.serviceId = :serviceId AND row.query = :query ORDER BY row.timestamp DESC";
        TypedQuery<TblWebserviceCache> sourceQuery =
                em.createQuery(lookupQuery, TblWebserviceCache.class)
                        // find the most recent cached response for this service and query
                        .setParameter("serviceId", serviceId)
                        .setParameter("query", query)
                        .setMaxResults(1);
        // get SQL lookup query results
        // TODO: Check if getSingleResult is the better option here
        List<TblWebserviceCache> sourceQueryResults = sourceQuery.getResultList();
        // check for valid cache entry
        if (sourceQueryResults.isEmpty()) return null;
        // deserialize PHP serialized response for existing queries
        return sourceQueryResults.get(0);
    }

    /**
     * Returns the Web service response from cache or by querying the Web service source.
     *
     * @param query parsed scientific name
     * @return response string for the given query
     */
    public String getResponse(NameParserResponse query) {
        String cachedResponse = null;
        // get cached response
        TblWebserviceCache webServiceCache = getWebServiceCache(query.getScientificName());
        // set response string to Web service response string if a cached response is found
        if (webServiceCache != null) {
            // existing responses are cached in PHP serialized format
            cachedResponse = Pherialize.unserialize(webServiceCache.getResponse()).toString();
            if (System.currentTimeMillis() / 1000L - webServiceCache.getTimestamp() < timeout) return cachedResponse;
        }
        // get the Web service response if no cached response has been returned
        String webServiceResponse = getWebServiceResponse(query);
        // return null or timed out cached response if there was no Web service response
        if (webServiceResponse == null) return cachedResponse;
        // if no cache exists for this Web service response create a new one
        if (cachedResponse == null) {
            webServiceCache = new TblWebserviceCache();
            webServiceCache.setServiceId(this.serviceId);
            // PHP serialize queries and create a SHA1 hash for a quicker comparison with existing queries
            webServiceCache.setQuery(phpSha1(Pherialize.serialize(query.getScientificName())));
            webServiceCache.setResponse(Pherialize.serialize(webServiceResponse));
        } else {
            // update the cached response if both a Web Service and cached response exist and persist the difference
            if (!webServiceResponse.equals(cachedResponse)) {
                try {
                    // calculate difference between cached response and Web service response
                    Patch<String> diff = DiffUtils.diffInline(cachedResponse, webServiceResponse);
                    // create a unified diff for this patch
                    List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff(null, null, Collections.singletonList(cachedResponse), diff, 0);
                    // persist differences for the cached response and keep its timestamp
                    TblWebserviceCacheDiffs cacheDiff = new TblWebserviceCacheDiffs();
                    cacheDiff.setDiff(Pherialize.serialize(unifiedDiff));
                    cacheDiff.setTblWebserviceCacheId(webServiceCache.getId());
                    cacheDiff.setTimestamp(webServiceCache.getTimestamp());
                    em.persist(cacheDiff);
                } catch (DiffException e) {
                    // Exception handling will likely be removed as it was stated unnecessary in this
                    // <a href="https://github.com/java-diff-utils/java-diff-utils/issues/70">java-diff-utils issue</a>.
                    e.printStackTrace();
                }
            }
        }
        // update timeout and persist changes to the Web service cache
        webServiceCache.setTimestamp(System.currentTimeMillis() / 1000L);
        em.persist(webServiceCache);
        // return Web service response after updating the cache
        return webServiceResponse;
    }

    /**
     * Returns the unprocessed Web service response string for a given parsed scientific name.
     *
     * @param query parsed scientific name
     * @return unprocessed Web service response string
     */
    public abstract String getWebServiceResponse(NameParserResponse query);

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    // TODO: clear cache (https://github.com/wkollernhm/openup/blob/master/protected/components/WSComponent.php#L117)
}
