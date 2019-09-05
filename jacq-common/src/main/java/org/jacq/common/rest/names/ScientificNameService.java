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
     * Get scientific name by a search string which will be split and quoted for autocompletion
     *
     * @param search       String to search for - no wildcards are allowed
     * @param autocomplete If set to true, the search will also return matches with
     *                     wildcards (i.e. autocomplete) as well
     * @return List of scientific name results
     */
    @GET
    @Path("find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<ScientificNameResult> find(@QueryParam("search") String search, @QueryParam("autocomplete") Boolean autocomplete);

    /**
     * Get a single scientific name by its id
     *
     * @param scientificNameId id of the scientific name to search for
     * @return scientific name for id
     */
    @GET
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameResult load(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Get cultivar entries for the given scientific name id
     *
     * @param scientificNameId id of the scientific name to search for
     * @return List of cultivar entries for id
     */
    @GET
    @Path("cultivar/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<CultivarResult> cultivarFind(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Get a single cultivar entry by its id
     *
     * @param cultivarId id of the cultivar entry to search for
     * @return cultivar entry for id
     */
    @GET
    @Path("cultivar/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CultivarResult cultivarLoad(@QueryParam("cultivarId") Long cultivarId);

    /**
     * Get scientific name information by scientific name id
     *
     * @param scientificNameId id of the scientific name to search information for
     * @return scientific name information by given id
     */
    @GET
    @Path("scientificNameInformation/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameInformationResult scientificNameInformationLoad(@QueryParam("scientificNameId") Long scientificNameId);

    /**
     * Save (create or update) a given scientific name information
     *
     * @param scientificNameInformationResult new scientific name information object in JSON format
     * @return newly created or updated scientific name information object
     */
    @POST
    @Path("scientificNameInformation/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ScientificNameInformationResult scientificNameInformationSave(ScientificNameInformationResult scientificNameInformationResult);

    /**
     * Get a list of all habitus types in the form of habitus type tesult objects
     *
     * @return list of all habitus types
     */
    @GET
    @Path("habitusType/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<HabitusTypeResult> findAllHabitusType();
}
