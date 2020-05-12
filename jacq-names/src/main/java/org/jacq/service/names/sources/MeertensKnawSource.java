package org.jacq.service.names.sources;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.services.MeertensKnawService;
import org.jacq.service.names.sources.util.SourcesUtil;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the Meertens KNAW (Pland) service using the MeertensKnawService interface.
 *
 * @see MeertensKnawService#query(String, String)
 * @see <a href="http://www.meertens.knaw.nl/pland/">Meertens KNAW (Pland) service</a>
 */
@ManagedBean
public class MeertensKnawSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(MeertensKnawSource.class.getName());

    /**
     * TODO: Improve error handling
     *
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        // connect to CatalogueOfLifeService
        MeertensKnawService service = SourcesUtil.getProxy(MeertensKnawService.class, "https://www.meertens.knaw.nl/pland/hitlist.php");
        // query source for parsed scientific name using JSON format and full response (tense has no common_names field)
        String response = service.query("php", query.getScientificName());

        try {
            // iterate over response arrays
            MixedArray responseArray = Pherialize.unserialize(response).toArray();
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
}
