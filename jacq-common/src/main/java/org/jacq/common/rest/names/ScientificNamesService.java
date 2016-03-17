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
package org.jacq.common.rest.names;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jacq.common.model.names.JsonRpcRequest;
import org.jacq.common.model.names.taxamatch.TaxamatchResponse;

/**
 * Interface for scientific names service
 *
 * @author wkoller
 */
@Path("/")
public interface ScientificNamesService {

    /**
     * Taxamatch-MDLD service Returns found matches and their resulting
     * information (score etc.)
     *
     * @param jsonRpcRequest
     * @return
     */
    @Path("/taxamatch/jsonRPC/json_rpc_taxamatchMdld.php")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public TaxamatchResponse taxamatchMdld(JsonRpcRequest jsonRpcRequest);
}
