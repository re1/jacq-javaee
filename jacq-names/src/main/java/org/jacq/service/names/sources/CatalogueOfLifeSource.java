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

package org.jacq.service.names.sources;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.services.CatalogueOfLifeService;
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
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Source implementation for the Catalogue of Life using the CatalogueOfLifeService interface.
 *
 * @see CatalogueOfLifeService#query(String, String, String, String, String)
 * @see <a href="http://www.catalogueoflife.org/col/webservice">Catalogue of Life Webservice</a>
 */
@ManagedBean
public class CatalogueOfLifeSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(CatalogueOfLifeSource.class.getName());

    private static final String serviceUrl = "http://www.catalogueoflife.org/col/webservice";

    @PostConstruct
    public void init() {
        setServiceId(1);
        setTimeout(2592000); // 30 days
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        // get cached response if possible
        String response = getResponse(query);
        if (response == null || response.isEmpty()) return results;

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
                        commonName.setName(commonNameObject.getString("name", null));
                        // languages do not come in ISO-639-6 format and are therefore converted
                        // TODO: improve language name conversion
                        String language = commonNameObject.getString("language", null);
                        if (language != null && !language.isEmpty()) {
                            for (Locale locale : Locale.getAvailableLocales()) {
                                if (locale.getDisplayLanguage().equals(language)) {
                                    try {
                                        commonName.setLanguage(locale.getISO3Language());
                                    } catch (MissingResourceException e) {
                                        LOGGER.warning("No three-letter language abbreviation available for language " + language);
                                    }
                                    break;
                                }
                            }
                        }

                        commonName.setGeography(commonNameObject.getString("country", null));
                        commonName.setTaxon(resultObject.getString("name", query.getScientificName()));
                        // add default reference (from https://github.com/wkollernhm/openup/blob/master/protected/components/Sources/COL.php)
                        commonName.getReferences().add("Bisby F., Roskov Y., Culham A., Orrell T., Nicolson D., Paglinawan L., Bailly N., Kirk P., Bourgoin T., Baillargeon G., Hernandez F., De Wever A., Kunze T., eds (2013). Species 2000 & ITIS Catalogue of Life, 8th February 2013. Digital resource at www.catalogueoflife.org/col/. Species 2000: Reading, UK.");
                        // build references from JSONArray
                        List<String> list = commonNameObject
                                .getJsonArray("references")
                                .getValuesAs(JsonObject.class).stream().map(reference -> Stream.of(
                                        reference.getString("author", null),
                                        reference.getString("year", null),
                                        reference.getString("title", null),
                                        reference.getString("source", null))
                                        .filter(s -> s != null && !s.isEmpty())
                                        .collect(Collectors.joining(", "))).collect(Collectors.toList());
                        commonName.getReferences().addAll(list);
                        commonName.setScore(query.getScientificName());

                        results.add(commonName);
                    }
                }
            }
        } catch (ClassCastException e) {
            // a JSON object could not be converted from the excepted type
            LOGGER.log(Level.WARNING, "JSON object contains elements of unexpected type" + response, e);
        } catch (JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string is not valid JSON", e);
        } catch (JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
        } catch (NullPointerException e) {
            // ignore empty JSON response object messages
            if (!response.contains("No names found")) {
                // JSON object has no valid result array field
                LOGGER.log(Level.WARNING, "JSON object \"" + response + "\" has no valid result array field", e);
            }
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
        // connect to CatalogueOfLifeService
        CatalogueOfLifeService service = SourcesUtil.getProxy(CatalogueOfLifeService.class, serviceUrl);
        // query source for parsed scientific name using JSON format and full response (tense has no common_names field)
        return service.query(query.getScientificName(), null, "json", "full", null);
    }
}
