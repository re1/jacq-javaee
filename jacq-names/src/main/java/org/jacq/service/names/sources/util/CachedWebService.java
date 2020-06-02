package org.jacq.service.names.sources.util;

import de.ailis.pherialize.Pherialize;
import org.jacq.common.model.jpa.openup.TblWebserviceCache;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.service.names.sources.CommonNamesSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Transactional
public abstract class CachedWebService implements CommonNamesSource {

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
    private String getCachedResponse(String query) {
        // PHP serialize queries and create a SHA1 hash for a quicker comparison with existing queries
        query = phpSha1(Pherialize.serialize(query));

        // build SQL lookup query for this service and query
        String lookupQuery = "SELECT row FROM TblWebserviceCache row WHERE row.serviceId = :serviceId AND row.query = :query AND row.timestamp >= :timeout ORDER BY row.timestamp DESC";
        TypedQuery<TblWebserviceCache> sourceQuery =
                em.createQuery(lookupQuery, TblWebserviceCache.class)
                        .setParameter("serviceId", serviceId)
                        // find cached entry for this query
                        .setParameter("query", query)
                        // make sure it's recent enough
                        .setParameter("timeout", System.currentTimeMillis() / 1000L - timeout)
                        // only the most recent entry
                        .setMaxResults(1);
        // get SQL lookup query results
        // TODO: Check if getSingleResult is the better option here
        List<TblWebserviceCache> sourceQueryResults = sourceQuery.getResultList();
        // check for valid cache entry
        if (sourceQueryResults.isEmpty()) return null;
        // deserialize PHP serialized response for existing queries
        return Pherialize.unserialize(sourceQueryResults.get(0).getResponse()).toString();
    }

    /**
     * Stores a response in the cache.
     *
     * @param query     Query to cache this response for
     * @param serviceId unique id used to identify the Web service of the cached response
     * @param response  Response to cache
     */
    private void setCachedResponse(String query, int serviceId, String response) {
        // PHP serialize queries and create a SHA1 hash for a quicker comparison with existing queries
        query = phpSha1(Pherialize.serialize(query));
        response = Pherialize.serialize(response);

        // remember new values and cache them
        TblWebserviceCache webserviceCache = new TblWebserviceCache();
        webserviceCache.setServiceId(serviceId);
        webserviceCache.setQuery(query);
        webserviceCache.setResponse(response);
        webserviceCache.setTimestamp(System.currentTimeMillis() / 1000L);
        em.persist(webserviceCache);
    }

    /**
     * Returns the Web service response from cache or by querying the Web service source.
     *
     * @param query parsed scientific name
     * @return response string for the given query
     */
    public String getResponse(NameParserResponse query) {
        // get cached response if possible
        String response = getCachedResponse(query.getScientificName());
        if (response == null) {
            response = getWebServiceResponse(query);
            // check if there was a webservice response and cache it or get the last timed out cached response
            if (response == null) {
                setTimeout(0); // timeout of zero means a cached response is always valid
                response = getCachedResponse(query.getScientificName());
            } else {
                setCachedResponse(query.getScientificName(), serviceId, response);
            }
        }

        return response;
    }

    ;

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
