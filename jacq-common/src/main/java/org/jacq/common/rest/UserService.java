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

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.EmploymentTypeResult;
import org.jacq.common.model.rest.GroupResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.model.rest.UserTypeResult;

/**
 *
 * @author fhafner
 */
@Path("/user")
public interface UserService {

    /**
     *
     * @param id
     * @param username
     * @param birthdate
     * @param offset
     * @param userType
     * @param organisationDescription
     * @param employmentType
     * @param limit
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResult> search(@QueryParam("id") Long id, @QueryParam("username") String username, @QueryParam("birthdate") Date birthdate, @QueryParam("userType") String userType, @QueryParam("employmentType") String employmentType, @QueryParam("organisationDescription") String organisationDescription, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     *
     * @param id
     * @param username
     * @param userType
     * @param birthdate
     * @param employmentType
     * @param organisationDescription
     * @return
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount(@QueryParam("id") Long id, @QueryParam("username") String username, @QueryParam("birthdate") Date birthdate, @QueryParam("userType") String userType, @QueryParam("employmentType") String employmentType, @QueryParam("organisationDescription") String organisationDescription);

    /**
     * Retrieve a list of all user entries
     *
     * @return
     */
    @GET
    @Path("/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResult> findAll();

    /**
     * Retrieve a single User entry by id
     *
     * @param id
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserResult load(@QueryParam("id") Long id);

    /**
     * Update or Add Single User entry
     *
     * @param userResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserResult save(UserResult userResult);

    /**
     * Retrieve a list of all usertype entries
     *
     * @return
     */
    @GET
    @Path("/findAllUserType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserTypeResult> findAllUserType();

    /**
     * Retrieve a list of all employmenttype entries
     *
     * @return
     */
    @GET
    @Path("/findAllEmploymentType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmploymentTypeResult> findAllEmploymentType();

    /**
     * Retrieve a list of all Group entries
     *
     * @return
     */
    @GET
    @Path("findAllGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupResult> findAllGroup();
}
