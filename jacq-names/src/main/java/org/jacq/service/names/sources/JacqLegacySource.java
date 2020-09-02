package org.jacq.service.names.sources;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.common.model.names.taxamatch.TaxamatchOptions;
import org.jacq.service.names.sources.services.JacqLegacyService;
import org.jacq.service.names.sources.util.SourcesUtil;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the JACQ Legacy JSON-RPC interface.
 *
 * @author re1
 */
@ManagedBean
public class JacqLegacySource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(JacqLegacySource.class.getName());

    private static final String serviceUrl = "http://131.130.131.9";

    @PostConstruct
    public void init() {
        setServiceId(2);
    }

    /**
     * Get Web service and process Web service response.
     *
     * @see CommonNamesSource#query(NameParserResponse)
     * @see <a href="https://sourceforge.net/p/jacq/legacy/ci/master/tree/taxamatch/jsonRPC">JACQ Legacy JSON-RPC</a>
     * @see <a href="https://www.jsonrpc.org/specification>JSON-RPC specification</a>
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // get web service response
        String response = getResponse(query);
        // initialize result list
        ArrayList<CommonName> results = new ArrayList<>();
        // parse JSON-RPC response as JSON
        try (StringReader stringReader = new StringReader(response)) {
            // create JSON-RPC response object from valid JSON string
            JsonObject jsonRpcResponseObject = Json.createReader(stringReader).readObject();
            // get JSON-RPC result object
            JsonObject jsonRpcResultObject = jsonRpcResponseObject.getJsonObject("result");
            // get taxamatch result from JSON-RPC result object
            JsonArray resultArray = jsonRpcResultObject.getJsonArray("result");
            // iterate over JSON-RPC results
            for (int i = 0; i < resultArray.size(); i++) {
                JsonObject resultObject = resultArray.getJsonObject(i);
                JsonArray taxamatchResultArray = resultObject.getJsonArray("searchresult");
                // iterate over TAXAMATCH results
                for (int j = 0; j < taxamatchResultArray.size(); j++) {
                    JsonObject taxamatchObject = taxamatchResultArray.getJsonObject(j);
                    JsonArray speciesArray = taxamatchObject.getJsonArray("species");
                    // iterate over TAXAMATCH results' species
                    for (int k = 0; k < speciesArray.size(); k++) {
                        JsonObject speciesObject = speciesArray.getJsonObject(k);
                        JsonArray commonNamesArray = speciesObject.getJsonArray("commonNames");
                        // iterate over TAXAMATCH results' species' commonNames
                        for (int l = 0; l < commonNamesArray.size(); l++) {
                            JsonObject commonNameObject = commonNamesArray.getJsonObject(l);
                            // build common name from common name and species JSON objects
                            CommonName commonName = new CommonName();
                            commonName.setName(commonNameObject.getString("name"));
                            commonName.setId(Long.parseLong(commonNameObject.getString("id")));
                            commonName.setTaxon(speciesObject.getString("taxon"));
                            commonName.setTaxonId(Long.parseLong(speciesObject.getString("taxonID")));
                            // language can be null and therefore throws a ClassCastException without a default value
                            commonName.setLanguage(commonNameObject.getString("language", null));
                            commonName.setGeography(commonNameObject.getString("geography"));
                            commonName.setPeriod(commonNameObject.getString("period"));
                            commonName.getReferences().add(commonNameObject.getString("reference", "JACQ Legacy source"));
                            commonName.setScore((long) (speciesObject.getJsonNumber("ratio").doubleValue() * 100));
                            // set match to true if score is 100
                            commonName.setMatch(commonName.getScore() == 100);
                            // add common name to results
                            results.add(commonName);
                        }
                    }
                }
            }
        } catch (ClassCastException e) {
            // JSON object is of invalid type
            LOGGER.log(Level.WARNING, "JSON object is of invalid type in response string '" + response + "'", e);
        } catch (JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string '" + response + "' is not valid JSON", e);
        } catch (JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
        } catch (NullPointerException e) {
            // JSON object has no valid result array field
            LOGGER.log(Level.WARNING, "JSON object has no valid result array field in response string '" + response + "'", e);
        } catch (NumberFormatException e) {
            // Long.parseLong could not convert a given id string to type long
            LOGGER.log(Level.WARNING, "Failed to parse id string to number", e);
        } catch (Exception e) {
            // exception was not handled explicitly
            LOGGER.log(Level.WARNING, "An unhandled exception occurred", e);
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
        // connect to JACQ legacy scientific name service
        JacqLegacyService service = SourcesUtil.getProxy(JacqLegacyService.class, serviceUrl);
        // JSON-RPC params are used as positional arguments: https://www.jsonrpc.org/specification#examples
        // 1. database, 2. searchitem, 3. params object
        // TODO: Consider using an object for name parameters
        LinkedList<Object> params = new LinkedList<>();
        // set database parameter
        params.add("vienna");
        // set searchtext parameter to scientific name
        params.add(query.getScientificName());
        // set includeCommonNames parameter to true
        TaxamatchOptions options = new TaxamatchOptions();
        options.setIncludeCommonNames(true);
        params.add(options);
        // Build JSON-RPC request
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(1L);
        request.setMethod("getMatchesService");
        request.setParams(params);
        // query source for parsed scientific name using JSON format and full response (tense has no common_names field)
        return service.query(request);
    }
}
