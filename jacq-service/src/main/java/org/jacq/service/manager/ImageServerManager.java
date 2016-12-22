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
package org.jacq.service.manager;

import javax.annotation.ManagedBean;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblImageServer;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.service.rest.ImageServer;
import org.jacq.service.util.ServicesUtil;

/**
 * Manager class for handling image server requests
 *
 * @author wkoller
 */
@ManagedBean
public class ImageServerManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    public String getBaseImageUrl(TblBotanicalObject botanicalObject) {
        TblImageServer tblImageServer = findImageServer(botanicalObject.getOrganisationId());

        // {"id":"1","method":"importImages","params":[12345]}
        if (tblImageServer != null) {
            ImageServer imageServer = ServicesUtil.getImageServer(tblImageServer.getBaseUrl());

            JsonObject request = Json.createObjectBuilder()
                    .add("id", "1")
                    .add("method", "listResources")
                    .add("params", Json.createArrayBuilder()
                            .add(tblImageServer.getKey())
                            .add(botanicalObject.getTblLivingPlant().getAccessionNumber())
                            .build()
                    )
                    .build();

            JsonObject response = imageServer.request(request);
            //response.toString();
        }

        return null;
    }

    /**
     * Traverses the organisation hierarchy in order to find a suitable image server
     *
     * @param organisation
     * @return ImageServer or null if none is found
     */
    protected TblImageServer findImageServer(TblOrganisation organisation) {
        // check for valid organisation first
        if (organisation == null) {
            return null;
        }

        if (organisation.getTblImageServer() != null) {
            return organisation.getTblImageServer();
        }
        else {
            return findImageServer(organisation.getParentOrganisationId());
        }
    }
}
