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
package org.jacq.common.manager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.jacq.common.model.names.NameParserResponse;

/**
 * Provides easy access to the biodiversity nameParser Note: the parser needs to be started manually using the parserver
 * command:
 *
 * ~ # parserver -r -o json
 *
 * @see "https://rubygems.org/gems/biodiversity/"
 *
 * @author wkoller
 */
@ManagedBean
public class NameParserManager {

    private static final Logger LOGGER = Logger.getLogger(NameParserManager.class.getName());

    /**
     * Reference to open socket
     */
    protected Socket socket;

    protected PrintWriter out;

    protected BufferedReader in;

    /**
     * Connects to parserver on startup
     */
    @PostConstruct
    public void init() {
        try {
            socket = new Socket("localhost", 4334);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Parse a given scientific name into its components
     *
     * @param scientificName
     * @return
     */
    public NameParserResponse parseName(String scientificName) {
        NameParserResponse nameParserResponse = new NameParserResponse();
        nameParserResponse.setScientificName(scientificName);

        try {
            out.println(scientificName);

            JsonReader reader = Json.createReader(new StringReader(in.readLine()));
            JsonObject responseObject = reader.readObject();
            JsonObject scientificNameObject = responseObject.getJsonObject("scientificName");

            // check for parsing
            if (scientificNameObject.getBoolean("parsed")) {
                // update scientific name with canonical, parsed, name
                nameParserResponse.setScientificName(scientificNameObject.getString("canonical"));

                // get detail information
                JsonObject scientificNameDetails = scientificNameObject.getJsonArray("details").getJsonObject(0);

                // check for uninomial
                JsonObject uninomial = scientificNameDetails.getJsonObject("uninomial");
                if (uninomial != null) {
                    nameParserResponse.setUninomial(uninomial.getString("string"));
                }
                // check for genus
                JsonObject genus = scientificNameDetails.getJsonObject("genus");
                if (genus != null) {
                    nameParserResponse.setGenus(genus.getString("string"));
                }
                // check for species
                JsonObject species = scientificNameDetails.getJsonObject("species");
                if (species != null) {
                    nameParserResponse.setSpecies(species.getString("string"));
                }
                // check for infraspecies
                JsonObject infraspecies = scientificNameDetails.getJsonObject("infraspecies");
                if (infraspecies != null) {
                    nameParserResponse.setInfraspecies(infraspecies.getString("string"));
                    nameParserResponse.setRank(infraspecies.getString("rank", null));
                }
            }
            else {
                LOGGER.log(Level.WARNING, "Unable to parse scientific-name ''{0}''", scientificName);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return nameParserResponse;
    }
}
