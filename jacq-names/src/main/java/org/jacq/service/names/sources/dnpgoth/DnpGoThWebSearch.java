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
package org.jacq.service.names.sources.dnpgoth;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface definition for http://www.dnp.go.th web search
 *
 * @author wkoller
 */
// http://www.dnp.go.th/botany/ThaiPlantName/SearchTree.aspx?Genus=%25Acanthus%25&Species=%25ebracteatus%25&GroupBy=Species
// http://www.dnp.go.th/botany/ThaiPlantName/SearchTree.aspx?Genus=%25andrographis%25&Species=%25laxiflora%25&GroupBy=Species
@Path("/botany/ThaiPlantName/")
public interface DnpGoThWebSearch {

    /**
     * Search names for the given genus / species, returns HTML formatted
     * results
     *
     * @param genus
     * @param species
     * @param groupBy
     * @return
     */
    @GET
    @Path("/SearchTree.aspx")
    public Response searchTree(@HeaderParam("User-Agent") String userAgent, @QueryParam("Genus") String genus, @QueryParam("Species") String species, @QueryParam("GroupBy") String groupBy);

    /**
     * Search names for the given genus / species, returns HTML formatted
     * results
     *
     * @param genus
     * @param species
     * @param groupBy
     * @return
     */
    @POST
    @Path("/SearchTree.aspx")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response searchTreeExpand(@HeaderParam("User-Agent") String userAgent, @HeaderParam("Referer") String referer, @QueryParam("Genus") String genus, @QueryParam("Species") String species, @QueryParam("GroupBy") String groupBy, @FormParam("__VIEWSTATE") String viewState, @FormParam("__VIEWSTATEGENERATOR") String viewStateGenerator, @FormParam("__EVENTVALIDATION") String eventValidation, @FormParam("__EVENTTARGET") String eventTarget, @FormParam("__EVENTARGUMENT") String eventArgument, @FormParam("__SCROLLPOSITIONX") String scrollPositionX, @FormParam("__SCROLLPOSITIONY") String scrollPositionY, @FormParam("tvwResult_ExpandState") String tvwResultExpandState, @FormParam("tvwResult_PopulateLog") String tvwResultPopulateLog, @FormParam("tvwResult_SelectedNode") String tvwResultSelectedNode);
}
