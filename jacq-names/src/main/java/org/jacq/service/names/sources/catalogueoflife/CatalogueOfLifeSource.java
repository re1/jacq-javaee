/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jacq.service.names.sources.catalogueoflife;

import org.jacq.common.model.names.CommonName;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the Catalogue of Life using the CatalogueOfLifeService interface.
 *
 * @see CatalogueOfLifeService#query(String, String, String, String, String)
 * @see <a href="http://www.catalogueoflife.org/col/webservice">Catalogue of Life Webservice</a>
 */
@ManagedBean
public class CatalogueOfLifeSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(CatalogueOfLifeSource.class.getName());

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        // connect to CatalogueOfLifeService
        CatalogueOfLifeService service = SourcesUtil.getProxy(CatalogueOfLifeService.class, "http://www.catalogueoflife.org/col/webservice");
        // query source for parsed scientific name using JSON format and full response (tense has no common_names field)
        String response = service.query(query.getScientificName(), null, "json", "full", null);
        // check if result is valid JSON
        try (StringReader stringReader = new StringReader(response)) {
            // create object from valid JSON string
            JsonArray resultsArray = Json.createReader(stringReader).readObject().getJsonArray("results");
            // iterate over results
            for (int i = 0; i < resultsArray.size(); i++) {
                JsonObject resultObject = resultsArray.getJsonObject(i);
                // only process results where "name_status" is "accepted name" as they are the only ones with common names
                if ("accepted name".equals(resultObject.getString("name_status", null))) {
                    JsonArray commonNamesArray = resultObject.getJsonArray("common_names");
                    // skip result if it has no common names
                    if (commonNamesArray == null) continue;
                    // iterate over common names for this result
                    for (int j = 0; j < commonNamesArray.size(); j++) {
                        JsonObject commonNameObject = commonNamesArray.getJsonObject(j);

                        CommonName commonName = new CommonName();
                        // TODO: refactor CommonName.id to string as not all common name ids are numbers
                        // commonName.setId(result.getString("id", ""));
                        commonName.setName(commonNameObject.getString("name", null));
                        commonName.setLanguage(commonNameObject.getString("language", null));
                        commonName.setGeography(commonNameObject.getString("country", null));
                        commonName.setTaxon(resultObject.getString("name", null));
                        // TODO: refactor CommonName.taxonId to string as not all taxon ids are numbers
                        // commonName.setTaxonId(resultObject.getString("id", null));
                        // TODO: build references from JSONArray
                        // commonName.setReferences(commonNameObject.getJsonArray("references"));
                        results.add(commonName);
                    }
                }
            }

        } catch (JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string is not valid JSON", e);
        } catch (JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
        } catch (NullPointerException e) {
            // JSON object has no valid result array field
            LOGGER.log(Level.WARNING, "JSON object has no valid result array field", e);
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
