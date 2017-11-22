/*
 * Copyright 2017 fhafner.
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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.OrganisationResult;

/**
 *
 * @author fhafner
 */
@Path("/organisation")
public interface OrganisationService {

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
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrganisationResult> search(@QueryParam("organisationId") Long id, @QueryParam("description") String description, @QueryParam("department") String department, @QueryParam("greenhouse") Boolean greenhouse, @QueryParam("ipenCode") String ipenCode, @QueryParam("parentOrganisationDescription") String parentOrganisationDescription, @QueryParam("gardener") String gardener, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     * Search the database using the given filter and return the count
     *
     * @param id
     * @param description
     * @param department
     * @param ipenCode
     * @param greenhouse
     * @param parentOrganisationDescription
     * @param gardener
     * @see OrganisationService#search()
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount(@QueryParam("organisationId") Long id, @QueryParam("description") String description, @QueryParam("department") String department, @QueryParam("greenhouse") Boolean greenhouse, @QueryParam("ipenCode") String ipenCode, @QueryParam("parentOrganisationDescription") String parentOrganisationDescription, @QueryParam("gardener") String gardener);

    /**
     * Retrieve a single organisation entry by id
     *
     * @param id
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrganisationResult load(@QueryParam("organisationId") Long id);

    /**
     * Update or Add Single organisation entry
     *
     * @param organisationResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrganisationResult save(OrganisationResult organisationResult);

    /**
     * Retrieve a list of all organisation entries
     *
     * @return
     */
    @GET
    @Path("/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrganisationResult> findAll();

}
