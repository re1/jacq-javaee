/*
 * Copyright 2016 Naturhistorisches Museum Wien.
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
package org.jacq.service.rest.names.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.rest.names.CommonNamesService;
import org.jacq.service.names.manager.CommonNamesManager;

/**
 * Main common names, OpenRefine compliant, service
 *
 * @author wkoller
 */
public class CommonNamesServiceImpl implements CommonNamesService {

    private static final Logger LOGGER = Logger.getLogger(CommonNamesServiceImpl.class.getName());

    @Inject
    protected CommonNamesManager commonNamesManager;

    /**
     * @see CommonNamesService#query(java.lang.String)
     */
    @Override
    public Response query(String query) {
        try {
            if (StringUtils.isEmpty(query)) {
                return Response.ok(commonNamesManager.info()).build();
            }
            else {
                return Response.ok(commonNamesManager.query(query)).build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }

}
