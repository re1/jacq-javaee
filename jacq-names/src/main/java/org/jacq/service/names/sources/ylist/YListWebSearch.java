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
package org.jacq.service.names.sources.ylist;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Interface definition for http://ylist.info/ylist_simple_search.php web search
 *
 * @author wkoller
 */
@Path("/")
public interface YListWebSearch {

    /**
     * Search names for the given scientific name
     *
     * @param anyField scientific name to search for
     * @param capital
     * @param familyOrder
     * @param familyDispType
     * @param specOrder
     * @param familyHeader
     * @param listType
     * @return
     */
    @GET
    @Path("/ylist_simple_search.php")
    public Response simpleSearch(@QueryParam("any_field") String anyField, @QueryParam("capital") Integer capital, @QueryParam("family_order") Integer familyOrder, @QueryParam("family_disp_type") Integer familyDispType, @QueryParam("family_header") Integer familyHeader, @QueryParam("spec_order") Integer specOrder, @QueryParam("list_type") Integer listType);

    /**
     * Displays details for a given result
     *
     * @param pass
     * @return
     */
    @GET
    @Path("/ylist_detail_display.php")
    public Response detailDisplay(@QueryParam("pass") String pass);
}
