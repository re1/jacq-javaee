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
package org.jacq.common.rest.names;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Common Names service interface
 *
 * @author wkoller
 */
@Path("/names/common")
public interface CommonNamesService {

    /**
     * Definition of JSON Media-Type with UTF-8 encoding for valid response headers
     */
    public static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON + "; charset=UTF-8";

    /**
     * Outputs OpenRefine compliant metadata information or Query the common names service
     *
     * @param query
     * @return List of matched common names
     */
    @Path("/")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(APPLICATION_JSON_UTF8)
    public Response query(@QueryParam("query") String query);
}
