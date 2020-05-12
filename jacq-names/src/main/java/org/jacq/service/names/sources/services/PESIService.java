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

package org.jacq.service.names.sources.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Resteasy Proxy Framework interface for the Pan-European Species directories infrastructure (PESI)
 *
 * @author re1
 */
@Path("/")
public interface PESIService {

    /**
     * Get the first exact matching GUID for a given name
     *
     * @param scientificName Parsed scientific name to get GUID for
     * @return GUID for scientificName in double quotes or no content
     */
    @GET
    @Path("PESIGUIDByName/{ScientificName}")
    @Produces(MediaType.APPLICATION_JSON)
    String getGUIDByName(@PathParam("ScientificName") String scientificName);

    /**
     * Get the correct name for a given GUID
     *
     * @param guid PESI GUID to search for
     * @return Name string for GUID in double quotes or no content
     */
    @GET
    @Path("PESINameByGUID/{GUID}")
    @Produces(MediaType.APPLICATION_JSON)
    String getNameByGUID(@PathParam("GUID") String guid);

    /**
     * Get the complete PESI Record for a given GUID
     *
     * @param guid PESI GUID to search for
     * @return JSON of PESI record for given GUID or no content
     */
    @GET
    @Path("PESIRecordByGUID/{GUID}")
    @Produces(MediaType.APPLICATION_JSON)
    String getRecordByGUID(@PathParam("GUID") String guid);

    /**
     * Fuzzy matches multiple ScientificNames (max. 50) to one or more PESI Records using
     * <a href="http://www.cmar.csiro.au/datacentre/taxamatch.htm">Tony Rees' TAXAMATCH</a> algorithm.
     *
     * @param query List of scientific names to query
     * @return PESI records for given scientific names or no content
     */
    @GET
    @Path("PESIRecordsByMatchTaxa")
    @Produces(MediaType.APPLICATION_JSON)
    String getRecordsByMatchTaxa(@QueryParam("scientificnames[]") List<String> query);

    /**
     * Fuzzy matches one ScientificName to one or more (max. 50) PESI Records using
     * <a href="http://www.cmar.csiro.au/datacentre/taxamatch.htm">Tony Rees' TAXAMATCH</a> algorithm.
     *
     * @param scientificName Scientific name to get records for
     * @return JSON of PESI records for given scientific name or no content
     */
    @GET
    @Path("PESIRecordsByMatchTaxon/{ScientificName}")
    @Produces(MediaType.APPLICATION_JSON)
    String getRecordsByMatchTaxon(@PathParam("ScientificName") String scientificName);

    /**
     * Get one or more matching (max. 50) PESIRecords for a given name
     *
     * @param scientificName Name to search for
     * @param like           Add '%'-sign after name (SQL LIKE function). Default=true
     * @param offset         Starting record number, when retrieving next chunk of (50) records. Default=1
     * @return JSON of PESI records for given scientific name or no content
     */
    @GET
    @Path("PESIRecordsByName/{ScientificName}")
    @Produces(MediaType.APPLICATION_JSON)
    String getRecordsByName(@PathParam("ScientificName") String scientificName, @QueryParam("like") boolean like, @QueryParam("offset") Integer offset);

    /**
     * Get one or more PESI Records (max. 50) for a given common name or vernacular
     *
     * @param vernacular The vernacular to search for
     * @param offset     Starting record number, when retrieving next chunk of (50) records. Default=1
     * @return JSON of PESI records for given vernacular of no content
     */
    @GET
    @Path("PESIRecordsByVernacular/{Vernacular}")
    @Produces(MediaType.APPLICATION_JSON)
    String getRecordsByName(@PathParam("Vernacular") String vernacular, @QueryParam("offset") Integer offset);

    /**
     * Get all vernaculars for a given GUID (max. 50)
     *
     * @param guid   PESI GUID to search for
     * @param offset Starting record number, when retrieving next chunk of (50) records. Default=1
     * @return JSON of vernacular objects or no content
     */
    @GET
    @Path("PESIVernacularsByGUID/{GUID}")
    @Produces(MediaType.APPLICATION_JSON)
    String getVernacularsByGUID(@PathParam("GUID") String guid, @QueryParam("offset") Integer offset);
}
