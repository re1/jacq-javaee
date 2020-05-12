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

package org.jacq.service.names.sources.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Resteasy Proxy Framework interface for the Catalogue of Life
 *
 * @author re1
 */
@Path("/")
public interface CatalogueOfLifeService {

    /**
     * Queries the Catalogue of Life Webservice for either a name or an id and returns the response string.
     * Results can be accepted (infra)species names, synonyms or common names for an (infra)species, or a higher taxon.
     *
     * @param name     String to search for. Only returns exact matches, unless a wildcard (*) is appended.
     *                 Must be at least 3 characters long, not counting the wildcard character.
     * @param id       Unique record number of a specific scientific name.
     * @param format   Format of the results returned. Can be xml (default), json or php.
     *                 php results are returned as a serialized PHP array.
     * @param response Can be terse (default) for a reduced set of results or full to include all available information.
     * @param start    Index of the record to return. Useful for limiting results.
     *                 Maximum number of results returned by a single query is 500 for terse and 50 for full queries.
     * @return Response string of results using the given format
     */
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
    String query(@QueryParam("name") String name,
                 @QueryParam("id") String id,
                 @QueryParam("format") String format,
                 @QueryParam("response") String response,
                 @QueryParam("start") String start);
}
