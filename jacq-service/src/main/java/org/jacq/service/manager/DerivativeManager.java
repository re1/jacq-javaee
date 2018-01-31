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
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.manager.DerivativeSearchManager;
import org.jacq.common.model.jpa.TblClassification;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblPhenology;
import org.jacq.common.model.jpa.ViewProtolog;
import org.jacq.common.model.rest.BotanicalObjectDownloadResult;
import org.jacq.common.model.rest.ClassificationSourceType;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.PhenologyResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.service.JacqServiceConfig;

/**
 * Helper class for querying all derivatives in a unified way Due to MySQL not performing well on views with UNION ALL
 * we simulate a view by writing the queries directly in this class Normally native queries should not be used at all
 * costs
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeManager extends DerivativeSearchManager {

    @Inject
    protected ClassificationManager classificationManager;

    @Inject
    protected JacqServiceConfig jacqConfig;

    /**
     * @see DerivativeService#load(java.lang.Long, java.lang.String)
     */
    @Transactional
    public BotanicalObjectDerivative load(Long derivativeId, String type) {
        if (LivingPlantResult.LIVING.equalsIgnoreCase(type)) {
            TblLivingPlant tblLivingPlant = em.find(TblLivingPlant.class, derivativeId);
            if (tblLivingPlant != null) {
                return new LivingPlantResult(tblLivingPlant);
            }
        }

        return null;
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
    public List<BotanicalObjectDownloadResult> downloadFind(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        List<BotanicalObjectDownloadResult> botanicalObjectDownloadResultList = new ArrayList<>();

        List<BotanicalObjectDerivative> botanicalObjectDerivativeList = this.find(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, orderColumn, orderDirection, offset, count);

        for (BotanicalObjectDerivative botanicalObjectDerivative : botanicalObjectDerivativeList) {
            TblDerivative dervivative = em.find(TblDerivative.class, botanicalObjectDerivative.getDerivativeId());
            TblClassification classification = classificationManager.getFamily(ClassificationSourceType.CITATION, jacqConfig.getLong(JacqServiceConfig.CLASSIFICATION_FAMILY_REFERENCE_ID), botanicalObjectDerivative.getScientificNameId());
            ViewProtolog protolog = em.find(ViewProtolog.class, classification.getSourceId());
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
}
