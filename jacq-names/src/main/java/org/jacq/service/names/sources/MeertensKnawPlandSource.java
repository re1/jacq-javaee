package org.jacq.service.names.sources;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import de.ailis.pherialize.exceptions.UnserializeException;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.services.MeertensKnawPlandService;
import org.jacq.service.names.sources.util.SourcesUtil;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the Meertens KNAW (Pland) service using the MeertensKnawPlandService interface.
 *
 * @author re1
 * @see MeertensKnawPlandService#query(String, String)
 * @see <a href="http://www.meertens.knaw.nl/pland/">Meertens KNAW (Pland) service</a>
 */
@ManagedBean
public class MeertensKnawPlandSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(MeertensKnawPlandSource.class.getName());

    private static final String serviceUrl = "https://www.meertens.knaw.nl/pland/rest";

    @PostConstruct
    public void init() {
        setServiceId(8);
        setTimeout(2592000); // 30 days
    }

    /**
     * TODO: Improve error handling
     *
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        String response = getResponse(query);
        if (response == null || response.isEmpty()) return results;

        try {
            // iterate over response arrays
            MixedArray responseArray = Pherialize.unserialize(response, StandardCharsets.ISO_8859_1).toArray();
            for (Object responseKey : responseArray.keySet()) {
                // iterate over common name results
                MixedArray resultsArray = responseArray.getArray(responseKey);
                for (Object resultKey : resultsArray.keySet()) {
                    // build common name from result values
                    MixedArray resultArray = resultsArray.getArray(resultKey);
                    CommonName commonName = new CommonName();
                    commonName.setName(resultArray.getString("nl_naam"));
                    commonName.setLanguage("dut"); // language is always dutch for this source
                    commonName.setGeography(resultArray.getString("kaart"));
                    commonName.setTaxon(resultArray.getString("botanische_naam"));
                    commonName.getReferences().add(resultArray.getString("woordenboekartikel"));
                    // add common name to result list
                    results.add(commonName);
                    // iterate over common name's dialect names
                    MixedArray dialectNameArray = resultArray.getArray("dialectnamen");
                    for (Object dialectNameKey : dialectNameArray.keySet()) {
                        CommonName dialectName = new CommonName();
                        dialectName.setName(dialectNameArray.getString(dialectNameKey));
                        dialectName.setLanguage("dut"); // language is always dutch for this source
                        dialectName.setGeography(resultArray.getString("kaart"));
                        dialectName.setTaxon(resultArray.getString("botanische_naam"));
                        dialectName.getReferences().add(resultArray.getString("woordenboekartikel"));
                        // add common name's dialect name to result list
                        results.add(dialectName);
                    }
                }
            }
        } catch (UnserializeException e) {
            LOGGER.log(Level.WARNING, "Failed to deserialize '" + response + "' at " + e.position, e);
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "Failed to retrieve value from deserialized PHP object", e);
        }

        return results;
    }

    /**
     * @see CommonNamesSource#query(String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see CachedWebServiceSource#getWebServiceResponse(NameParserResponse)
     */
    @Override
    public String getWebServiceResponse(NameParserResponse query) {
        // connect to MeertensKnawPlandService
        MeertensKnawPlandService service = SourcesUtil.getProxy(MeertensKnawPlandService.class, serviceUrl);
        // query source for parsed scientific name using PHP response format
        return service.query("php", query.getScientificName());
    }
}
