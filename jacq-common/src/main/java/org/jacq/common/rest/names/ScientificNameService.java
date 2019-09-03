/*
 * Copyright 2018 wkoller.
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

import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.HabitusTypeResult;
import org.jacq.common.model.rest.ScientificNameInformationResult;
import org.jacq.common.model.rest.ScientificNameResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Service interface providing methods for access to scientific name searching
 *
 * @author wkoller
 */
@Path("{a:scientificName|names/scientific}")
public interface ScientificNameService {

    /**
     * Search for a scientific name, search string will be split and quoted for autocompletion
     *
     * @param search       String to search for - no wildcards are allowed
     * @param autocomplete If set to true, search will return matches with
     *                     wildcards (i.e. autocomplete) as well
     * @return
     */
    @GET
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<ScientificNameResult> find(@QueryParam("search") String search, @QueryParam("autocomplete") Boolean autocomplete);

    /**
     * Load a single scientific name id entry
     *
     * @param scientificNameId
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameResult load(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Find cultivar entries for the given scientific name id
     *
     * @param scientificNameId
     * @return
     */
    @GET
    @Path("/cultivar/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<CultivarResult> cultivarFind(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Load a single cultivar entry by its id
     *
     * @param cultivarId
     * @return
     */
    @GET
    @Path("/cultivar/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CultivarResult cultivarLoad(@QueryParam("cultivarId") Long cultivarId);

    /**
     * Load the scientific name information for a given id
     *
     * @param scientificNameId
     * @return
     */
    @GET
    @Path("/scientificNameInformation/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameInformationResult scientificNameInformationLoad(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Save (create or update) a given scientific name information
     *
     * @param scientificNameInformationResult
     * @return
     */
    @POST
    @Path("/scientificNameInformation/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameInformationResult scientificNameInformationSave(ScientificNameInformationResult scientificNameInformationResult);

    /**
     * Fetch a list of all habitus types
     *
     * @return
     */
    @GET
    @Path("/habitusType/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<HabitusTypeResult> findAllHabitusType();
}
