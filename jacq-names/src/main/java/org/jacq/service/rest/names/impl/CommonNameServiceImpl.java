/*
 * Copyright 2016 Naturhistorisches Museum Wien.
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
package org.jacq.service.rest.names.impl;

import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.OpenRefineResponse;
import org.jacq.common.rest.names.CommonNameService;
import org.jacq.service.names.manager.CommonNameManager;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Common Names Webservice based on the OpenRefine Reconciliation API
 *
 * @author wkoller
 * @author re1
 * @see <a href="https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API">OpenRefine Reconciliation API</a>
 */
public class CommonNameServiceImpl implements CommonNameService {

    private static final Logger LOGGER = Logger.getLogger(CommonNameServiceImpl.class.getName());

    @Inject
    protected CommonNameManager commonNameManager;

    /**
     * @see CommonNameService#query
     */
    @Override
    public Response query(String queries, String query) throws WebApplicationException {
        try {
            if (StringUtils.isNotEmpty(queries)) {
                // create a hash map to save common name results with their keys
                Map<String, OpenRefineResponse<CommonName>> queryResultMap = new HashMap<>();
                // check if string is valid JSON
                try {
                    StringReader stringReader = new StringReader(queries);
                    JsonObject queriesObject = Json.createReader(stringReader).readObject();
                    // iterate over queries and add results with their key to the results map
                    for (String key : queriesObject.keySet()) {
                        String queryString = queriesObject.getJsonObject(key).getString("query");
                        queryResultMap.put(key, commonNameManager.query(queryString));
                    }
                } catch (JsonParsingException e) {
                    // Queries parameter is not valid JSON
                    LOGGER.log(Level.INFO, "Queries parameter is not valid JSON", e);
                    throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Queries parameter is not valid JSON").build());
                } catch (JsonException e) {
                    // Queries parameter is valid JSON but not a JSON object
                    LOGGER.log(Level.INFO, "Queries parameter is valid but not a JSON object", e);
                    throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Queries parameter is not a JSON object").build());
                } catch (ClassCastException e) {
                    // Query JSON object or its query parameter cannot be cast to object or string respectively
                    LOGGER.log(Level.INFO, "Query JSON object's query parameter is not a string", e);
                    throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Query JSON object's query parameter is not a string").build());
                } catch (NullPointerException e) {
                    // Query JSON object has no query parameter
                    LOGGER.log(Level.INFO, "Query JSON object has no query parameter", e);
                    throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Query JSON object has no query parameter").build());
                }
                // return map of query responses
                return Response.ok(queryResultMap).build();
            }

            // use single query mode if query parameter is given
            if (StringUtils.isNotEmpty(query)) {
                // check if query string is valid JSON
                try (StringReader stringReader = new StringReader(query)) {
                    // create object from valid JSON string
                    JsonObject queryObject = Json.createReader(stringReader).readObject();
                    query = queryObject.getString("query");
                    // Project documentation states that the type parameter should always be /name/common so other values ar logged
                    // https://development.senegate.at/confluence/display/JACQ/Common+Names+Webservice#CommonNamesWebservice-III.Requests&Responses
                    // TODO: Check if this is even necessary
                    try {
                        // check if type parameter is /name/common
                        String type = queryObject.getString("type");
                        if (!StringUtils.equals(type, "/name/common")) {
                            LOGGER.log(Level.INFO, "Type parameter is not /name/common", type);
                        }
                    } catch (NullPointerException e) {
                        // type parameter not found
                        LOGGER.log(Level.INFO, "No type parameter given");
                    } catch (ClassCastException e) {
                        // type parameter is not a string
                        LOGGER.log(Level.INFO, "Type parameter is not a string");
                    }
                } catch (JsonParsingException e) {
                    // log invalid JSON query
                    LOGGER.log(Level.INFO, "Query parameter is not valid JSON and used as a string", e);
                }
                // return query response
                return Response.ok(commonNameManager.query(query)).build();
            }
            // return common name webservice information if no response was returned and no exception thrown
            return Response.ok(commonNameManager.info()).build();
        } catch (ClientErrorException e) {
            // Throw ClientErrorExceptions directly
            throw e;
        } catch (Exception e) {
            // Check for other errors which might lead to internal error.
            // TODO: Check if this is obsolete as an internal error is thrown anyway.
            LOGGER.log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }
}