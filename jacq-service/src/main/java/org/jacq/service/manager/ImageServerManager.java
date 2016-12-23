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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import org.jacq.common.model.ImageServerResource;
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

    public List<ImageServerResource> getResources(TblBotanicalObject botanicalObject) {
        // search for matching image server first
        TblImageServer tblImageServer = findImageServer(botanicalObject.getOrganisationId());
        ArrayList<ImageServerResource> imageServerResources = new ArrayList<>();

        // {"id":"1","method":"importImages","params":[12345]}
        // check if the botanical object has an image server assigned
        if (tblImageServer != null) {
            // create image server proxy object
            ImageServer imageServer = ServicesUtil.getImageServer(tblImageServer.getBaseUrl());

            // format identifier
            String identifier = String.format("%07d", botanicalObject.getTblLivingPlant().getAccessionNumber());

            // prepare request to image server
            JsonObject request = Json.createObjectBuilder()
                    .add("id", "1")
                    .add("method", "listResources")
                    .add("params", Json.createArrayBuilder()
                            .add(tblImageServer.getKey())
                            .add(Json.createArrayBuilder()
                                    .add(identifier)
                                    .add(identifier + "_%")
                                    .build()
                            )
                            .build()
                    )
                    .build();

            // parse the response
            Response response = imageServer.request(request);
            String responseString = response.readEntity(String.class);
            JsonObject responseObject = Json.createReader(new StringReader(responseString)).readObject();
            if (responseObject != null) {
                JsonArray foundResources = responseObject.getJsonArray("result");
                if (foundResources != null) {
                    for (int i = 0; i < foundResources.size(); i++) {
                        JsonObject resourceInfo = foundResources.getJsonObject(i);
                        if (resourceInfo.getString("public").equals("1")) {
                            ImageServerResource imageServerResource = new ImageServerResource();
                            imageServerResource.setIdentifier(resourceInfo.getString("identifier"));
                            imageServerResource.setThumbnailUrl(tblImageServer.getBaseUrl() + "/adore-djatoka/resolver?url_ver=Z39.88-2004&rft_id=" + imageServerResource.getIdentifier() + "&svc_id=info:lanl-repo/svc/getRegion&svc_val_fmt=info:ofi/fmt:kev:mtx:jpeg2000&svc.format=image/jpeg&svc.scale=160,0");
                            imageServerResource.setImageUrl(tblImageServer.getBaseUrl() + "/jacq-viewer/viewer.html?rft_id=" + imageServerResource.getIdentifier() + "&identifiers=" + imageServerResource.getIdentifier());

                            imageServerResources.add(imageServerResource);
                        }
                    }
                }
            }

        }

        return imageServerResources;
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
