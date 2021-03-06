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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.InventoryResult;
import org.jacq.common.model.rest.InventoryTypeResult;

/**
 *
 * @author fhafner
 */
@Path("/inventory")
public interface InventoryService {

    /**
     * Update or Add Single inventory entry
     *
     * @param inventoryResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public InventoryResult save(InventoryResult inventoryResult);

    /**
     * Retrieve a list of all InventoryType entries
     *
     * @return
     */
    @GET
    @Path("/findAllInventoryType")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryTypeResult> findAllInventoryType();

}
