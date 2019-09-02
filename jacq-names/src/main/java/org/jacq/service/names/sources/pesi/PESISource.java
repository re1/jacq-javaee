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

package org.jacq.service.names.sources.pesi;

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
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the Pan-European Species directories infrastructure (PESI) using the PESIService interface.
 *
 * @see PESIService
 * @see <a href="http://www.eu-nomen.eu/portal/rest/">PESI RestAPI</a>
 */
@ManagedBean
public class PESISource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(PESISource.class.getName());

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        // connect to CatalogueOfLifeService
        PESIService service = SourcesUtil.getProxy(PESIService.class, "http://www.eu-nomen.eu/portal/rest");
        // query GUID for parsed scientific name
        String guid = service.getGUIDByName(query.getScientificName());
        // return if no GUID was found for this scientific name
        if (guid == null || guid.isEmpty()) return results;
        // query vernaculars for GUID (double quotes are remove manually)
        String response = service.getVernacularsByGUID(guid.replaceAll("\"", ""), null);
        // return if no vernaculars were found for this GUID
        if (response == null || response.isEmpty()) return results;
        // check if response is valid JSON
        try (StringReader stringReader = new StringReader(response)) {
            // create object from valid JSON string
            JsonArray vernaculars = Json.createReader(stringReader).readArray();
            // iterate over results
            for (int i = 0; i < vernaculars.size(); i++) {
                JsonObject vernacular = vernaculars.getJsonObject(i);

                CommonName commonName = new CommonName();

                commonName.setTaxon(query.getScientificName());
                commonName.setName(vernacular.getString("vernacular", null));
                commonName.setLanguage(vernacular.getString("language_code", null));

                results.add(commonName);
            }
        } catch (JsonParsingException e) {
            // response is not valid JSON
            LOGGER.log(Level.WARNING, "Response string is not valid JSON", e);
        } catch (JsonException e) {
            // JSON object could not be created due to an i/o error
            LOGGER.log(Level.WARNING, "JSON object could not be created due to an i/o error", e);
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
