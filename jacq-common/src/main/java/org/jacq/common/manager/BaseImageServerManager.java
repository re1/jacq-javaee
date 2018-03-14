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
package org.jacq.common.manager;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import org.jacq.common.external.rest.ImageServer;
import org.jacq.common.model.rest.ImageServerResource;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblImageServer;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.util.ServicesUtil;

/**
 * Manager class for handling image server requests
 *
 * @author wkoller
 */
public class BaseImageServerManager {

    private static final Logger LOGGER = Logger.getLogger(BaseImageServerManager.class.getName());

    protected EntityManager em;

    /**
     * Search for resources for a given botanical object
     *
     * @param botanicalObject
     * @return
     */
    public List<ImageServerResource> getResources(TblDerivative derivative) {
        // search for matching image server first
        TblImageServer tblImageServer = findImageServer(derivative.getOrganisationId());
        ArrayList<ImageServerResource> imageServerResources = new ArrayList<>();

        // check if the botanical object has an image server assigned
        if (tblImageServer != null) {
            // wrap image server communication in order to gracefully fall back on error
            try {
                // create image server proxy object
                ImageServer imageServer = ServicesUtil.getImageServer(tblImageServer.getBaseUrl());

                // format identifier
                String identifier = "";

                // check if derivative is a living plant
                if (derivative.getTblLivingPlant() != null) {
                    identifier = String.format("%07d", derivative.getTblLivingPlant().getAccessionNumber());
                }

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

            } // in case of an error, log it but continue serving content
            catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error during communication with image server - ignoring & continuing", e);
            }

        }

        return imageServerResources;
    }

    /**
     * Helper function for synchronizing image server content with has-image flags in botanical object list
     */
    @Transactional
    public void synchronizeImageFlags() {
        // find all image servers
        TypedQuery<TblImageServer> imageServerQuery = em.createNamedQuery("TblImageServer.findAll", TblImageServer.class);
        List<TblImageServer> imageServers = imageServerQuery.getResultList();

        // now process each server
        for (TblImageServer tblImageServer : imageServers) {
            // create proxy object to image server
            ImageServer imageServer = ServicesUtil.getImageServer(tblImageServer.getBaseUrl());

            // query image server for all resources
            JsonObject request = Json.createObjectBuilder()
                    .add("id", "1")
                    .add("method", "listResources")
                    .add("params", Json.createArrayBuilder()
                            .add(tblImageServer.getKey())
                            .add(Json.createArrayBuilder()
                                    .add("%")
                                    .build()
                            )
                            .build()
                    )
                    .build();

            // run query & parse response
            Response response = imageServer.request(request);
            String responseString = response.readEntity(String.class);
            JsonObject responseObject = Json.createReader(new StringReader(responseString)).readObject();

            // check for valid response
            if (responseObject != null) {
                JsonArray foundResources = responseObject.getJsonArray("result");

                // check if resources were returned
                if (foundResources != null) {
                    // reset has image info for all objects within this image server hierarchy
                    Query resetImageStatusQuery = em.createNamedQuery("TblLivingPlant.resetImageStatus");
                    resetImageStatusQuery.setParameter("organisations", findOrganisations(tblImageServer));
                    resetImageStatusQuery.executeUpdate();

                    // now iterate over returned resources and update image status for those objects
                    for (int i = 0; i < foundResources.size(); i++) {
                        JsonObject resourceInfo = foundResources.getJsonObject(i);
                        // resourceInfo.getString("public")
                        // resourceInfo.getString("identifier")

                        // TODO: this logic only works for living plants right now, we need to define this in a more generic way
                        String identifier = resourceInfo.getString("identifier");
                        identifier = identifier.split("_")[0];

                        // find living plant for identifier and update image status
                        TypedQuery<TblLivingPlant> livingPlantQuery = em.createNamedQuery("TblLivingPlant.findByAccessionNumber", TblLivingPlant.class);
                        livingPlantQuery.setParameter("accessionNumber", Integer.parseInt(identifier));
                        TblLivingPlant livingPlant = livingPlantQuery.getSingleResult();
                        livingPlant.setHasImage(true);
                        if (resourceInfo.getString("public").equals("1")) {
                            livingPlant.setHasPublicImage(true);
                        }
                        em.persist(livingPlant);
                    }
                }
            }
        }
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

    /**
     * Finds all organisations handled by an image server
     *
     * @param imageServer
     * @return
     */
    protected List<TblOrganisation> findOrganisations(TblImageServer imageServer) {
        List<TblOrganisation> organisations = new ArrayList<>();

        // start with the organisation directly assigned
        organisations.add(imageServer.getTblOrganisation());

        // now handle all assigned organisations
        organisations.addAll(traverseOrganisations(imageServer.getTblOrganisation()));

        return organisations;
    }

    /**
     * Find all organisations belonging to a partial organisations tree, taking into account image-server assignments
     *
     * @param parentOrganisation
     * @return
     */
    protected List<TblOrganisation> traverseOrganisations(TblOrganisation parentOrganisation) {
        List<TblOrganisation> organisations = new ArrayList<>();

        // iterate over childs and check for overwriting image server
        for (TblOrganisation organisation : parentOrganisation.getTblOrganisationList()) {
            if (organisation.getTblImageServer() == null) {
                organisations.add(organisation);

                // now add all childs
                organisations.addAll(traverseOrganisations(organisation));
            }
        }

        return organisations;
    }
}
