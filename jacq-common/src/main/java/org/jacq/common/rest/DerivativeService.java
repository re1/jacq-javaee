/*
 * Copyright 2017 wkoller.
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
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.BotanicalObjectDownloadResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.PhenologyResult;

/**
 * Main service for searching derivatives
 *
 * @author wkoller
 */
@Path("/derivative")
public interface DerivativeService {

    /**
     * Search for a specific derivative and return it
     *
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param orderColumn
     * @param scientificNameId
     * @param orderDirection
     * @param offset
     * @param count
     * @return
     */
    @GET
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BotanicalObjectDerivative> find(@QueryParam("type") String type, @QueryParam("derivativeId") Long derivativeId, @QueryParam("placeNumber") String placeNumber, @QueryParam("accessionNumber") String accessionNumber, @QueryParam("separated") Boolean separated, @QueryParam("scientificNameId") Long scientificNameId, @QueryParam("organisationId") Long organisationId, @QueryParam("orderColumn") String orderColumn, @QueryParam("orderDirection") OrderDirection orderDirection, @QueryParam("offset") Integer offset, @QueryParam("count") Integer count);

    /**
     * Return total count of results for the given search parameters
     *
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param scientificNameId
     * @return
     */
    @GET
    @Path("/count")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int count(@QueryParam("type") String type, @QueryParam("derivativeId") Long derivativeId, @QueryParam("placeNumber") String placeNumber, @QueryParam("accessionNumber") String accessionNumber, @QueryParam("separated") Boolean separated, @QueryParam("scientificNameId") Long scientificNameId, @QueryParam("organisationId") Long organisationId);

    /**
     * Get details for a given derivative entry
     *
     * @param derivativeId
     * @param type
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(@QueryParam("derivativeId") Long derivativeId, @QueryParam("type") String type);

    /**
     * Search for a specific derivative and return it
     *
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param orderColumn
     * @param scientificNameId
     * @param orderDirection
     * @param offset
     * @param count
     * @return
     */
    @GET
    @Path("/download/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BotanicalObjectDownloadResult> downloadFind(@QueryParam("type") String type, @QueryParam("derivativeId") Long derivativeId, @QueryParam("placeNumber") String placeNumber, @QueryParam("accessionNumber") String accessionNumber, @QueryParam("separated") Boolean separated, @QueryParam("scientificNameId") Long scientificNameId, @QueryParam("organisationId") Long organisationId, @QueryParam("orderColumn") String orderColumn, @QueryParam("orderDirection") OrderDirection orderDirection, @QueryParam("offset") Integer offset, @QueryParam("count") Integer count);

    /**
     * Returns a list of available phenology types
     *
     * @return
     */
    @GET
    @Path("/phenology/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhenologyResult> findAllPhenology();

}
