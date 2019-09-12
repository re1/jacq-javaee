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

import org.jacq.common.model.names.NameParserResponse;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides easy access to the Global Names Parser
 * Note: The parser is available as a ruby gem and needs to be installed and started manually on port 4334.
 * 1. {@code gem install biodiversity}
 * 2. {@code parserver -r -o json}
 *
 * @author wkoller
 * @author re1
 * @see "https://rubygems.org/gems/biodiversity/"
 */
@ManagedBean
public class NameParserManager {

    private static final Logger LOGGER = Logger.getLogger(NameParserManager.class.getName());

    // Reference to open socket
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    /**
     * Connects to the Global Names Parser on startup
     */
    @PostConstruct
    public void init() {
        try {
            socket = new Socket("localhost", 4334);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    /**
     * Parse a given scientific name into its components
     *
     * @param scientificName unparsed scientific name
     * @return parsed scientific name
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

                /* Infraspecies value can either be a single object or an array of multiple objects of values string,
                rank, authorship and basionymAuthorTeam, the last being an object of authorTeam and author. */
                JsonValue infraspeciesValue = scientificNameDetails.get("infraspecies");
                // check if infraspecies has a JSON value
                if (infraspeciesValue != null) {
                    // check if infraspecies is a JSON object
                    if (infraspeciesValue.getValueType() == JsonValue.ValueType.OBJECT) {
                        JsonObject infraspeciesObject = scientificNameDetails.getJsonObject("infraspecies");
                        // set name parser response fields from infraspecies array
                        nameParserResponse.setInfraspecies(infraspeciesObject.getString("string"));
                        nameParserResponse.setRank(infraspeciesObject.getString("rank", null));
                    }
                    // if infraspecies is not a JSON object check if it is a JSON array
                    else if (infraspeciesValue.getValueType() == JsonValue.ValueType.ARRAY) {
                        JsonArray infraspeciesArray = scientificNameDetails.getJsonArray("infraspecies");
                        // check if infraspecies array is empty
                        if (!infraspeciesArray.isEmpty()) {
                            JsonObject infraspeciesObject = infraspeciesArray.getJsonObject(0);
                            /* both name parser response fields infraspecies and rank only support a single string so
                            only one element can be used. A warning is given that additional elements are ignored */
                            if (infraspeciesArray.size() > 1) {
                                LOGGER.log(Level.WARNING,
                                        "Name parser response array for 'infraspecies' has more than one element. " +
                                                "Additional elements are not used and therefore ignored.",
                                        infraspeciesArray);
                            }
                            // set name parser response fields from infraspecies object
                            nameParserResponse.setInfraspecies(infraspeciesObject.getString("string"));
                            nameParserResponse.setRank(infraspeciesObject.getString("rank", null));
                        }
                    }
                    // if infraspecies is neither a JSON array nor object a warning is given
                    else {
                        LOGGER.log(Level.WARNING, String.format(
                                "Name parser response object's value 'infraspecies' is neither array nor object but '%s'",
                                infraspeciesValue.toString()), infraspeciesValue);
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "Unable to parse scientific-name ''{0}''", scientificName);
            }
        } catch (ClassCastException e) {
            LOGGER.log(Level.SEVERE, String.format("Name parser response for scientific name '%s' contains an unexpected value.", scientificName), e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return nameParserResponse;
    }
}
