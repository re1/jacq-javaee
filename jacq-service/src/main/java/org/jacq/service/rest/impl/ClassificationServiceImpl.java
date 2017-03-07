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
package org.jacq.service.rest.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.jacq.common.model.ClassificationSourceType;
import org.jacq.common.model.jpa.RevClassification;
import org.jacq.common.model.jpa.ViewClassificationResult;
import org.jacq.common.rest.ClassificationService;
import org.jacq.service.manager.ClassificationManager;

/**
 *
 * @author wkoller
 */
public class ClassificationServiceImpl implements ClassificationService {

    private static final Logger LOGGER = Logger.getLogger(ClassificationServiceImpl.class.getName());

    @Inject
    protected ClassificationManager classificationManager;

    /**
     * @see ClassificationService#getEntries(org.jacq.common.model.ClassificationSourceType, long, java.lang.Long)
     */
    @Override
    public List<ViewClassificationResult> getEntries(ClassificationSourceType source, long sourceId, Long parentId) {
        try {
            return classificationManager.getEntries(source, sourceId, parentId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }

    @Override
    public List<ViewClassificationResult> getAccepted(ClassificationSourceType source, long sourceId) {
        try {
            return classificationManager.getAccepted(source, sourceId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }

    /**
     * @see ClassificationService#addRevision(org.jacq.common.model.ClassificationSourceType, long)
     */
    @Override
    public UUID addRevision(ClassificationSourceType source, long sourceId) {
        try {
            return classificationManager.addRevision(source, sourceId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }

    /**
     * @see ClassificationService#getRevision(java.util.UUID, java.lang.Long)
     * @return
     */
    @Override
    public List<RevClassification> getRevision(@QueryParam("uuid") UUID revision, @QueryParam("parentId") Long parentId) {
        try {
            return classificationManager.getRevision(revision, parentId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw new WebApplicationException(Response.serverError().entity(e.getMessage()).build());
        }
    }
}
