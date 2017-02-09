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
package org.jacq.service.manager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.model.ClassificationSourceType;
import org.jacq.common.model.jpa.TblClassification;
import org.jacq.common.model.jpa.ViewClassificationResult;
import org.jacq.common.rest.ClassificationService;

/**
 *
 * @author wkoller
 */
public class ClassificationManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @see ClassificationService#getEntries(org.jacq.common.model.ClassificationSourceType, long, java.lang.Long)
     */
    @Transactional
    public List<ViewClassificationResult> getEntries(ClassificationSourceType source, long sourceId, Long parentId) {
        TypedQuery<ViewClassificationResult> classificationQuery = null;

        if (parentId == null) {
            classificationQuery = em.createNamedQuery("ViewClassificationResult.findTopLevelBySource", ViewClassificationResult.class);
            classificationQuery.setParameter("source", source.toString());
            classificationQuery.setParameter("sourceId", sourceId);
        }
        else {
            classificationQuery = em.createNamedQuery("ViewClassificationResult.findBySourceAndParent", ViewClassificationResult.class);
            classificationQuery.setParameter("source", source.toString());
            classificationQuery.setParameter("sourceId", sourceId);
            classificationQuery.setParameter("parentScientificNameId", parentId);
        }

        return classificationQuery.getResultList();
    }

    public List<ViewClassificationResult> getAccepted(ClassificationSourceType source, long sourceId) {
        TypedQuery<ViewClassificationResult> classificationQuery = null;

        classificationQuery = em.createNamedQuery("ViewClassificationResult.findBySourceAndAccepted", ViewClassificationResult.class);
        classificationQuery.setParameter("source", source.toString());
        classificationQuery.setParameter("sourceId", sourceId);

        return classificationQuery.getResultList();
    }
}
