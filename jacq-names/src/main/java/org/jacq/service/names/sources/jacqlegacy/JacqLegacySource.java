package org.jacq.service.names.sources.jacqlegacy;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.CommonNamesSource;
import org.jacq.service.names.sources.util.SourcesUtil;

import javax.annotation.ManagedBean;
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

@ManagedBean
public class JacqLegacySource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(JacqLegacySource.class.getName());

    /**
     * Query source for common names based on a parsed scientific name
     *
     * @param query parsed scientific name
     * @return list of common names for given scientific name
     * @see <a href="https://sourceforge.net/p/jacq/legacy/ci/master/tree/taxamatch/jsonRPC/json_rpc_taxamatchMdld.php#l136">JACQ Legacy JSON-RPC Public functions</a>
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        // connect to JACQ legacy scientific name service
        JacqLegacyService service = SourcesUtil.getProxy(JacqLegacyService.class, "http://development.jacq.org/jacq-legacy");
        // JSON-RPC params are used as positional arguments: https://www.jsonrpc.org/specification#examples
        // TODO: Consider using an object for name parameters
        LinkedList<Object> params = new LinkedList<>();
        // set searchtext parameter to scientific name
        params.add(query.getScientificName());
        // set nearMatch parameter to false (as scientific names are parsed already)
        params.add(false);
        // Build JSON-RPC request
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(1L);
        request.setMethod("getMatchesCommonNames");
        request.setParams(params);
        // query source for parsed scientific name using JSON format and full response (tense has no common_names field)
        String response = service.query(request);
        // TODO: Improve error handling
        try (StringReader stringReader = new StringReader(response)) {
            // create JSON-RPC response object from valid JSON string
            JsonObject jsonRpcResponseObject = Json.createReader(stringReader).readObject();
            // get JSON-RPC result object
            JsonObject jsonRpcResultObject = jsonRpcResponseObject.getJsonObject("result");
            // get taxamatch result from JSON-RPC result object
            JsonArray resultArray = jsonRpcResultObject.getJsonArray("result");

            for (int i = 0; i < resultArray.size(); i++) {
                JsonObject resultObject = resultArray.getJsonObject(i);
                JsonArray taxamatchArray = resultObject.getJsonArray("searchresult");

                for (int j = 0; j < taxamatchArray.size(); j++) {
                    JsonObject taxamatchObject = taxamatchArray.getJsonObject(j);
                    JsonArray speciesArray = taxamatchObject.getJsonArray("species");

                    for (int k = 0; k < speciesArray.size(); k++) {
                        JsonObject speciesObject = speciesArray.getJsonObject(k);

                        CommonName commonName = new CommonName();
                        commonName.setName(speciesObject.getString("commonName"));
                        commonName.setTaxon(speciesObject.getString("taxon"));
                        commonName.setTaxonId(Long.parseLong(speciesObject.getString("taxonID")));

                        results.add(commonName);
                    }
                }
            }
        } catch (
                JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string is not valid JSON", e);
        } catch (
                JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
        } catch (NullPointerException e) {
            // JSON object has no valid result array field
            LOGGER.log(Level.WARNING, "JSON object has no valid result array field", e);
        } catch (NumberFormatException e) {
            // Long.parseLong could not convert a given id string to type long
            LOGGER.log(Level.WARNING, "Failed to parse id string to number");
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
