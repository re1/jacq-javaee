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
package org.jacq.common.rest.output;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.OrganisationResult;

/**
 * Main service for searching derivatives
 *
 * @author wkoller
 */
@Path("/derivative")
public interface SearchService {

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
     * Search the database using the given filter
     *
     * @param id
     * @param description
     * @param department
     * @param ipenCode
     * @param greenhouse
     * @param parentOrganisationDescription
     * @param gardener
     * @param offset Return result with an offset
     * @param limit Limit total count of results
     * @return
     */
    @GET
    @Path("/organisation/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrganisationResult> organisationSearch(@QueryParam("organisationId") Long id, @QueryParam("description") String description, @QueryParam("department") String department, @QueryParam("greenhouse") Boolean greenhouse, @QueryParam("ipenCode") String ipenCode, @QueryParam("parentOrganisationDescription") String parentOrganisationDescription, @QueryParam("gardener") String gardener, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);
}
