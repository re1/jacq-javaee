/*
 * Copyright 2018 wkoller.
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
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.manager.BaseDerivativeManager;
import org.jacq.common.model.jpa.TblCertificateType;
import org.jacq.common.model.jpa.TblClassification;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblIdentStatus;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblPhenology;
import org.jacq.common.model.jpa.TblRelevancyType;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.jpa.TblSex;
import org.jacq.common.model.jpa.TblVegetative;
import org.jacq.common.model.jpa.ViewProtolog;
import org.jacq.common.model.rest.BotanicalObjectDownloadResult;
import org.jacq.common.model.rest.CertificateTypeResult;
import org.jacq.common.model.rest.ClassificationSourceType;
import org.jacq.common.model.rest.IdentStatusResult;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.PhenologyResult;
import org.jacq.common.model.rest.RelevancyTypeResult;
import org.jacq.common.model.rest.SeparationTypeResult;
import org.jacq.common.model.rest.SexResult;
import org.jacq.common.model.rest.VegetativeResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.service.ApplicationManager;
import org.jacq.service.JacqServiceConfig;

/**
 * Helper class for querying all derivatives in a unified way Due to MySQL not
 * performing well on views with UNION ALL we simulate a view by writing the
 * queries directly in this class Normally native queries should not be used at
 * all costs
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeManager extends BaseDerivativeManager {

    @Inject
    protected ClassificationManager classificationManager;

    @Inject
    protected JacqServiceConfig jacqConfig;

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected ApplicationManager applicationManager;

    @Inject
    protected ImageServerManager imageServerManager;

    /**
     * Initialize bean and make sure abstract base class has entity manager
     */
    @PostConstruct
    public void init() {
        this.setEntityManager(em);
        this.setBaseApplicationManager(applicationManager);
    }

    /**
     * @see DerivativeService#load(java.lang.Long, java.lang.String)
     */
    @Transactional
    public BotanicalObjectDerivative load(Long derivativeId, String type) {
        if (LivingPlantResult.LIVING.equalsIgnoreCase(type)) {
            TblLivingPlant tblLivingPlant = em.find(TblLivingPlant.class, derivativeId);
            if (tblLivingPlant != null) {
                LivingPlantResult livingPlantResult = new LivingPlantResult(tblLivingPlant);
                livingPlantResult.setImageServerResources(imageServerManager.getResources(tblLivingPlant.getTblDerivative(), false));
                if (tblLivingPlant.getTblDerivative().getBotanicalObjectId() != null) {
                    TblClassification classification = classificationManager.getFamily(ClassificationSourceType.CITATION, jacqConfig.getLong(JacqServiceConfig.CLASSIFICATION_FAMILY_REFERENCE_ID), tblLivingPlant.getTblDerivative().getBotanicalObjectId().getScientificNameId());
                    ViewProtolog protolog = getProtolog(classification);
                    if (classification != null && classification.getViewScientificName() != null) {
                        livingPlantResult.setFamily(classification.getViewScientificName().getScientificName() != null ? classification.getViewScientificName().getScientificName() : null);
                    }
                    if (protolog != null) {
                        livingPlantResult.setFamilyReference(protolog.getProtolog() != null ? protolog.getProtolog() : null);
                    }
                }

                return livingPlantResult;

            }
        }

        return null;
    }

    /**
     * @see DerivativeService#vegetativeFind(java.lang.Long)
     */
    public List<VegetativeResult> vegetativeFind(Long parentDerivativeId) {
        TypedQuery<TblVegetative> vegetativeQuery = em.createNamedQuery("TblVegetative.findByParentDerivative", TblVegetative.class);
        vegetativeQuery.setParameter("parentDerivativeId", em.find(TblDerivative.class, parentDerivativeId));

        return VegetativeResult.fromList(vegetativeQuery.getResultList());
    }

    /**
     *
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param scientificNameId
     * @param organisationId
     * @param orderColumn
     * @param orderDirection
     * @param offset
     * @param count
     * @return
     */
    @Transactional
    public List<BotanicalObjectDownloadResult> downloadFind(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        List<BotanicalObjectDownloadResult> botanicalObjectDownloadResultList = new ArrayList<>();

        List<BotanicalObjectDerivative> botanicalObjectDerivativeList = this.find(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, orderColumn, orderDirection, offset, count);

        for (BotanicalObjectDerivative botanicalObjectDerivative : botanicalObjectDerivativeList) {
            TblDerivative dervivative = em.find(TblDerivative.class, botanicalObjectDerivative.getDerivativeId());
            TblClassification classification = classificationManager.getFamily(ClassificationSourceType.CITATION, jacqConfig.getLong(JacqServiceConfig.CLASSIFICATION_FAMILY_REFERENCE_ID), botanicalObjectDerivative.getScientificNameId());
            ViewProtolog protolog = getProtolog(classification);
            BotanicalObjectDownloadResult botanicalObjectDownloadResult = new BotanicalObjectDownloadResult(botanicalObjectDerivative, dervivative, classification, protolog);
            botanicalObjectDownloadResultList.add(botanicalObjectDownloadResult);
        }

        return botanicalObjectDownloadResultList;
    }

    /**
     * @see DerivativeService#findAllPhenology()
     */
    @Transactional
    public List<PhenologyResult> findAllPhenology() {
        TypedQuery<TblPhenology> phenologyQuery = em.createNamedQuery("TblPhenology.findAll", TblPhenology.class);

        return PhenologyResult.fromList(phenologyQuery.getResultList());
    }

    /**
     * @see DerivativeService#findAllIdentStatus()
     */
    @Transactional
    public List<IdentStatusResult> findAllIdentStatus() {
        TypedQuery<TblIdentStatus> identStatusQuery = em.createNamedQuery("TblIdentStatus.findAll", TblIdentStatus.class);

        return IdentStatusResult.fromList(identStatusQuery.getResultList());
    }

    /**
     * @see DerivativeService#findAllRelevancyTypes()
     */
    public List<RelevancyTypeResult> findAllRelevancyType() {
        return RelevancyTypeResult.fromList(this.findAllRelevancyType(false));
    }

    /**
     * @see DerivativeService#findAllImportantRelevancyTypes()
     */
    public List<RelevancyTypeResult> findAllImportantRelevancyType() {
        return RelevancyTypeResult.fromList(this.findAllRelevancyType(true));
    }

    /**
     * @see DerivativeService#findAllSeparationType()
     */
    public List<SeparationTypeResult> findAllSeparationType() {
        TypedQuery<TblSeparationType> separationTypeQuery = em.createNamedQuery("TblSeparationType.findAll", TblSeparationType.class);

        return SeparationTypeResult.fromList(separationTypeQuery.getResultList());
    }

    /**
     * @see DerivativeService#findAllCertificateType()
     */
    public List<CertificateTypeResult> findAllCertificateType() {
        TypedQuery<TblCertificateType> certificateTypeQuery = em.createNamedQuery("TblCertificateType.findAll", TblCertificateType.class);

        return CertificateTypeResult.fromList(certificateTypeQuery.getResultList());
    }

    /**
     * @see DerivativeService#findAllSex()
     */
    public List<SexResult> findAllSex() {
        TypedQuery<TblSex> sexQuery = em.createNamedQuery("TblSex.findAll", TblSex.class);

        return SexResult.fromList(sexQuery.getResultList());
    }

    /**
     * Small helper function for fetching relevancy type based on important
     * parameter
     *
     * @param important
     * @return
     */
    @Transactional
    protected List<TblRelevancyType> findAllRelevancyType(boolean important) {
        TypedQuery<TblRelevancyType> relevancyTypeQuery = em.createNamedQuery("TblRelevancyType.findByImportant", TblRelevancyType.class);
        relevancyTypeQuery.setParameter("important", important);

        return relevancyTypeQuery.getResultList();
    }

    /**
     *
     * @param classification
     * @return
     */
    @Transactional
    protected ViewProtolog getProtolog(TblClassification classification) {
        ViewProtolog protolog = null;
        if (classification != null && classification.getSourceId() != null) {
            protolog = em.find(ViewProtolog.class, classification.getSourceId());
        }
        return protolog;
    }
}
