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
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.TreeRecordFileResult;

/**
 *
 * @author fhafner
 */
@Path("/treeRecordFile")
public interface TreeRecordFileService {

    /**
     * Search the database using the given filter
     *
     * @param id
     * @param year
     * @param name
     * @param documentNumber
     * @param offset Return result with an offset
     * @param limit Limit total count of results
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreeRecordFileResult> search(@QueryParam("treeRecordFileId") Long id, @QueryParam("year") Date year, @QueryParam("name") String name, @QueryParam("documentNumber") String documentNumber, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     * Search the database using the given filter and return the count
     *
     * @param id
     * @param year
     * @param name
     * @param documentNumber
     * @see treeRecordFile#search()
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount(@QueryParam("treeRecordFileId") Long id, @QueryParam("year") Date year, @QueryParam("name") String name, @QueryParam("documentNumber") String documentNumber);

    /**
     * Update or Add Single User entry
     *
     * @param treeRecordFileResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TreeRecordFileResult save(TreeRecordFileResult treeRecordFileResult);

    /**
     *
     * @param treeRecordFileId
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TreeRecordFileResult load(@QueryParam("treeRecordFileId") Long treeRecordFileId);
}
