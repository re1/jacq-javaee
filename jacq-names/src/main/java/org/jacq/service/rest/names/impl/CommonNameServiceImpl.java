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
import org.jacq.common.rest.names.CommonNameService;
import org.jacq.service.names.manager.CommonNameManager;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main common names, OpenRefine compliant, service
 *
 * @author wkoller
 */
public class CommonNameServiceImpl implements CommonNameService {

    private static final Logger LOGGER = Logger.getLogger(CommonNameServiceImpl.class.getName());

    @Inject
    protected CommonNameManager commonNameManager;

    /**
     * @see CommonNameService#query(java.lang.String)
     */
    @Override
    public Response query(String query) {
        try {
            if (StringUtils.isEmpty(query)) {
                return Response.ok(commonNameManager.info()).build();
            }
            else {
                // check if query string is valid json
                try (StringReader stringReader = new StringReader(query)) {
                    // create object from valid json string
                    JsonObject queryObject = Json.createReader(stringReader).readObject();
                    query = queryObject.getString("query");
                } catch (JsonParsingException e) {
                    // log invalid json query
                    // TODO: add notice to query results
                    LOGGER.log(Level.FINE, "Query is not a valid JSON object and therefore interpreted as a string.", e);
                }

                return Response.ok(commonNameManager.query(query)).build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }

}
