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
package org.jacq.common.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.BotanicalObjectResult;

/**
 *
 * @author wkoller
 */
@Path("/botanicalObject")
public interface BotanicalObjectService {

    /**
     * Search the database using the given filter
     *
     * @param scientificName Scientific name to search for
     * @param organization Organization to filter for
     * @param hasImage Only display results containing images
     * @param offset Return result with an offset
     * @param limit Limit total count of results
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BotanicalObjectResult> search(@QueryParam("scientificName") String scientificName, @QueryParam("organization") String organization, @QueryParam("hasImage") Boolean hasImage, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     * Search the database using the given filter and return the count
     *
     * @see BotanicalObjectService#search(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Integer,
     * java.lang.Integer)
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount(@QueryParam("scientificName") String scientificName, @QueryParam("organization") String organization, @QueryParam("hasImage") Boolean hasImage);
}
