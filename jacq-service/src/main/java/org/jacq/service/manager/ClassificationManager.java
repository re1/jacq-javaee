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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.manager.JacqConfig;
import org.jacq.common.model.rest.ClassificationSourceType;
import org.jacq.common.model.jpa.RevClassification;
import org.jacq.common.model.jpa.SrvcUuidMinter;
import org.jacq.common.model.jpa.TblClassification;
import org.jacq.common.model.jpa.TblNomName;
import org.jacq.common.model.jpa.ViewClassificationResult;
import org.jacq.common.model.rest.ClassificationResult;
import org.jacq.common.rest.ClassificationService;
import org.jacq.service.JacqServiceConfig;

/**
 *
 * @author wkoller
 */
public class ClassificationManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    @Inject
    protected JacqServiceConfig jacqConfig;

    /**
     * @see
     * ClassificationService#getEntries(org.jacq.common.model.ClassificationSourceType,
     * long, java.lang.Long)
     */
    @Transactional
    public List<ViewClassificationResult> getEntries(ClassificationSourceType source, long sourceId, Long parentId) {
        TypedQuery<ViewClassificationResult> classificationQuery = null;

        if (parentId == null) {
            classificationQuery = em.createNamedQuery("ViewClassificationResult.findTopLevelBySource", ViewClassificationResult.class);
            classificationQuery.setParameter("source", source.toString());
            classificationQuery.setParameter("sourceId", sourceId);
        } else {
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

    /**
     * @see
     * ClassificationService#addRevision(org.jacq.common.model.ClassificationSourceType,
     * long)
     */
    @Transactional
    public UUID addRevision(ClassificationSourceType source, long sourceId) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("RevClassification.addRevision");
        query.setParameter("i_source", source.toString());
        query.setParameter("i_source_id", sourceId);
        query.execute();
        return UUID.fromString((String) query.getOutputParameterValue("o_uuid"));
    }

    /**
     * @see ClassificationService#getRevision(java.util.UUID, java.lang.Long,
     * java.lang.Integer)
     */
    public List<RevClassification> getRevision(UUID revision, Long parentId, Integer provinceId) {
        // load uuid-minter entry first
        TypedQuery<SrvcUuidMinter> uuidMinterQuery = em.createNamedQuery("SrvcUuidMinter.findByUuid", SrvcUuidMinter.class);
        uuidMinterQuery.setParameter("uuid", revision.toString());
        try {
            SrvcUuidMinter uuidMinter = uuidMinterQuery.getSingleResult();

            // now load entries from revision table
            TypedQuery<RevClassification> revClassificationQuery = null;

            if (provinceId == null) {
                revClassificationQuery = em.createNamedQuery("RevClassification.findByUuidMinterIdAndTopLevel", RevClassification.class);
                revClassificationQuery.setParameter("uuidMinterId", uuidMinter.getUuidMinterId());
            } else {
                revClassificationQuery = em.createNamedQuery("RevClassification.findByUuidMinterIdAndTopLevelAndProvinceId", RevClassification.class);
                revClassificationQuery.setParameter("uuidMinterId", uuidMinter.getUuidMinterId());
                revClassificationQuery.setParameter("provinceId", "%" + provinceId + "%");
            }

            List<RevClassification> results = revClassificationQuery.getResultList();

            return results;
        } catch (NoResultException | NonUniqueResultException e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public TblClassification getAcceptedName(ClassificationSourceType source, long sourceId, long scientificNameId) {

        // Finds first accepted Scientific Name
        Query query = em.createNamedQuery("TblClassification.findBySourceAndAcceptedAndScientificNameId")
                .setParameter("scientificNameId", scientificNameId)
                .setParameter("source", source.toString())
                .setParameter("sourceId", sourceId);
        List<TblClassification> tblClassificationList = query.getResultList();
        if (tblClassificationList.size() == 0) {
            return null;
        }
        TblClassification tblClassification = tblClassificationList.get(0);

        if (tblClassification.getAccScientificNameId() != null) {
            query.setParameter("scientificNameId", tblClassification.getAccScientificNameId());
            tblClassificationList = query.getResultList();
            if (tblClassificationList.size() == 0) {
                return null;
            }
            tblClassification = tblClassificationList.get(0);

        }

        return tblClassification;
    }

    @Transactional
    public TblClassification getAcceptedName(long scientificNameId) {
        ClassificationSourceType source = ClassificationSourceType.CITATION;
        String[] sourceIds = jacqConfig.getString(JacqConfig.CLASSIFICATION_FAMILY_REFERENCE_IDS).split(",");

        for (int i = 0; i < sourceIds.length; i++) {
            Long sourceId = Long.parseLong(sourceIds[i]);
            TblClassification tblClassification = getAcceptedName(source, sourceId, scientificNameId);
            if (tblClassification != null) {
                return tblClassification;
            }
        }
        return null;
    }

    @Transactional
    public TblClassification getFamily(long scientificNameId) {
        ClassificationSourceType source = ClassificationSourceType.CITATION;
        String[] sourceIds = jacqConfig.getString(JacqConfig.CLASSIFICATION_FAMILY_REFERENCE_IDS).split(",");

        // try to find result for each source id until we match
        for (int i = 0; i < sourceIds.length; i++) {
            Long sourceId = Long.parseLong(sourceIds[i]);

            // Find TblClassification where nomName Rank = 9
            TblClassification tblClassification = getAcceptedName(source, sourceId, scientificNameId);
            if (tblClassification == null) {
                // load name info for extracting genus / substantive id
                TypedQuery<TblNomName> scientificNameQuery = em.createNamedQuery("TblNomName.findByNameId", TblNomName.class);
                scientificNameQuery.setParameter("nameId", scientificNameId);
                List<TblNomName> scientificNameList = scientificNameQuery.getResultList();
                if (scientificNameList != null && scientificNameList.size() > 0) {
                    TblNomName scientificName = scientificNameList.get(0);

                    // check if this is already a genus entry
                    if (scientificName.getRankId() == null || scientificName.getRankId().getRankId().equals(7L)) {
                        continue;
                    }

                    // try to find matching classification entry for genus
                    TypedQuery<TblNomName> genusQuery = em.createNamedQuery("TblNomName.findBySubstantiveAndRank", TblNomName.class);
                    genusQuery.setParameter("substantiveId", scientificName.getSubstantiveId().getSubstantiveId());
                    genusQuery.setParameter("rankId", 7L);
                    List<TblNomName> genusNameList = genusQuery.getResultList();
                    if (genusNameList != null && genusNameList.size() > 0) {
                        TblNomName genusName = genusNameList.get(0);

                        TblClassification genusFamily = getFamily(genusName.getNameId());
                        if (genusFamily != null) {
                            return genusFamily;
                        }
                    }
                }

                // if we did not find any entry using the genus, continue with next reference
                continue;
            }

            Query query = em.createNamedQuery("TblNomName.findByNameId")
                    .setParameter("nameId", tblClassification.getScientificNameId());

            TblNomName tblNomName = (TblNomName) query.getSingleResult();
            while (tblNomName.getRankId().getRankId() != 9 && tblClassification.getParentScientificNameId() != null) {
                tblClassification = getAcceptedName(source, sourceId, tblClassification.getParentScientificNameId());

                if (tblClassification == null) {
                    break;
                }

                query.setParameter("nameId", tblClassification.getScientificNameId());
                tblNomName = (TblNomName) query.getSingleResult();
            }

            // if no family ranked name was found, continue with next reference
            if (tblNomName == null || tblClassification == null || (tblNomName.getRankId().getRankId() != 9 && tblClassification.getParentScientificNameId() == null)) {
                continue;
            }

            return tblClassification;
        }

        return null;
    }

    /**
     * @see ClassificationService#findAll()
     */
    @Transactional
    public List<ClassificationResult> findAll() {
        TypedQuery<TblClassification> availableClassificationsQuery = em.createNamedQuery("TblClassification.findAllAvailable", TblClassification.class);
        return ClassificationResult.fromList(availableClassificationsQuery.getResultList());
    }
}
