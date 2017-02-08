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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.ClassificationSourceType;
import org.jacq.common.model.jpa.TblClassification;

/**
 * Service interface for accessing classification based information
 *
 * @author wkoller
 */
@Path("/classification")
public interface ClassificationService {

    /**
     * Receive children of a given parent entry using the given reference
     *
     * @param source Source of classification (Citation, Person, etc.)
     * @param sourceId Reference to use for looking up
     * @param parentId Parent to look for. Can be NULL to provide top-level entries
     * @return
     */
    @GET
    @Path("/entries")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TblClassification> getEntries(@QueryParam("source") ClassificationSourceType source, @QueryParam("sourceId") long sourceId, @QueryParam("parentId") Long parentId);
}
