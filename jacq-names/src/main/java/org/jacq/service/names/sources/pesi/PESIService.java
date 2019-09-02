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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Resteasy Proxy Framework interface for the Pan-European Species directories infrastructure (PESI)
 *
 * @author re1
 */
@Path("/")
public interface PESIService {

    /**
     * Get the first exact matching GUID for a given name
     *
     * @param scientificName Parsed scientific name to get GUID for
     * @return GUID for scientificName or no content
     */
    @GET
    @Path("PESIGUIDByName/{ScientificName}")
    @Produces(MediaType.APPLICATION_JSON)
    String getGUIDByName(@PathParam("ScientificName") String scientificName);

    /**
     * Get all vernaculars for a given GUID (max. 50)
     *
     * @param guid   PESI GUID to search for
     * @param offset Starting record number, when retrieving next chunk of (50) records. Default=1
     * @return JSON array of vernacular objects
     */
    @GET
    @Path("PESIVernacularsByGUID/{GUID}")
    @Produces(MediaType.APPLICATION_JSON)
    String getVernacularsByGUID(@PathParam("GUID") String guid, @QueryParam("offset") String offset);
}
