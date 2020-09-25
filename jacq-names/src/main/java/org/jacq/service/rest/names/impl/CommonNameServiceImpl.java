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

import org.jacq.common.model.names.*;
import org.jacq.common.rest.names.CommonNameService;
import org.jacq.service.names.manager.CommonNameManager;

import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;
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
    public Response query(OpenRefineMultiRequest queries, OpenRefineRequest query, String format) throws WebApplicationException {
        try {
            String mediaType = "edmSkos".equals(format) ? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON + "; charset=UTF-8";

            // use multiple query mode if queries parameter is given (edmSkos format is currently ignored!)
            if (queries != null) {
                // create a hash map to save common name results with their keys
                Map<String, NameResponse<CommonName>> responseMap = new HashMap<>();
                for (String key : queries.keySet()) {
                    // prepare OpenRefine response
                    NameResponse<CommonName> response = new OpenRefineResponse<>();
                    ArrayList<CommonName> tmp = commonNameManager.query(queries.get(key).getQuery());
                    response.setResult(tmp);
                    responseMap.put(key, response);
                    LOGGER.log(Level.INFO, String.valueOf(tmp.size()));
                }
                OpenRefineResponseMap<CommonName> openRefineResponseMap = new OpenRefineResponseMap<>();
                openRefineResponseMap.setResult(responseMap);
                // return map of query responses
                return Response.ok(openRefineResponseMap, mediaType).build();
            }

            // use single query mode if query parameter is given
            if (query != null) {
                NameResponse<CommonName> response = "edmSkos".equals(format)
                        ? new EDMResponse() : new OpenRefineResponse<>();
                // Project documentation states that the type parameter should always be /name/common so other values are logged
                // https://development.senegate.at/confluence/display/JACQ/Common+Names+Webservice#CommonNamesWebservice-III.Requests&Responses
                // TODO: Check if this is even necessary
                // use string comparison from static string as query.getType() can be null
                if (!"/name/common".equals(query.getType())) {
                    LOGGER.log(Level.INFO, "Type parameter is not /name/common", query.getType());
                }
                // sort results before returning them
                ArrayList<CommonName> results = commonNameManager.query(query.getQuery());
                Collections.sort(results);
                response.setResult(results);
                // return results as either EDMResponse or OpenRefineResponse
                return Response.ok(response, mediaType).build();
            }
            // return common name webservice information if no response was returned and no exception thrown
            return Response.ok(commonNameManager.info(), mediaType).build();
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

    /**
     * @see CommonNameService#getCommonNameFromCacheById(String)
     */
    @Override
    public Response getCommonNameFromCacheById(String id) {
        // create an EDM response and set its result to a list of a single common name
        EDMResponse response = new EDMResponse();
        response.setResult(Collections.singletonList(commonNameManager.getById(id)));
        // return as XML
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

    /**
     * @see CommonNameService#getCommonNameReferencesByScientificNameId(String)
     */
    @Override
    public Response getCommonNameReferencesByScientificNameId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}