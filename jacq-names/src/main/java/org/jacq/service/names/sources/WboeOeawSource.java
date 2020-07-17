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

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
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
 * Source implementation for the WBÖ / DBÖ Web services.
 * The original Web service endpoint was deprecated so this source implementation only exists
 * in order to query cached results for the deprecated service.
 *
 * @author re1
 * @see <a href="http://wboe.oeaw.ac.at">Project page for the original WBÖ / DBÖ Web service</a>
 * @see <a href="https://dboema.acdh.oeaw.ac.at/projekt/beschreibung/">Project page for the new DBO Web service</a>
 */
@ManagedBean
public class WboeOeawSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(WboeOeawSource.class.getName());

    private static final String serviceUrl = "http://wboe.oeaw.ac.at/api/taxonid/";

    @PostConstruct
    public void init() {
        setServiceId(5);
        setTimeout(2592000); // 30 days
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // initialize result list
        ArrayList<CommonName> results = new ArrayList<>();
        // get web service response
        String response = getResponse(query);
        // return empty result list if response is null or empty
        if (response == null || response.isEmpty()) return results;
        // remove text around JSON result array
        response = response.substring(response.indexOf("["), response.lastIndexOf("]") + 1);
        // parse response as JSON
        try (StringReader stringReader = new StringReader(response)) {
            // get result array from JSON response
            JsonArray resultArray = Json.createReader(stringReader).readArray();
            // iterate over JSON results
            for (int i = 0; i < resultArray.size(); i++) {
                JsonObject commonNameObject = resultArray.getJsonObject(i);
                // build common name from common name and species JSON objects
                CommonName commonName = new CommonName();
                commonName.setName(commonNameObject.getString("name"));
                commonName.setTaxon(query.getScientificName());
                commonName.setTaxonId(Long.parseLong(commonNameObject.getString("taxon_id")));
                // language can be null and therefore throws a ClassCastException without a default value
                commonName.setLanguage(commonNameObject.getString("language", null));
                commonName.setGeography(commonNameObject.getString("geography"));
                // unescape slashes
                commonName.getReferences().add(commonNameObject.getString("reference").replace("\\", ""));
                commonName.setScore(commonNameObject.getJsonNumber("ratio").longValue());
                // set match to true if score is 100
                commonName.setMatch(commonName.getScore() == 100);
                // add common name to results
                results.add(commonName);
            }
        } catch (
                JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string is not valid JSON", e);
        } catch (JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
        } catch (NullPointerException e) {
            // JSON object has no valid result array field
            LOGGER.log(Level.WARNING, "JSON object has no valid result array field", e);
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

    @Override
    public String getWebServiceResponse(NameParserResponse query) {
        LOGGER.log(Level.WARNING, "Web service source is unavailable.", serviceUrl);
        return null;
    }
}
