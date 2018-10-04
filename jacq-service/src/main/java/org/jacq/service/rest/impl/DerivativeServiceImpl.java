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
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jacq.service.manager.DerivativeManager;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.BotanicalObjectDownloadResult;
import org.jacq.common.model.rest.CertificateTypeResult;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.IdentStatusResult;
import org.jacq.common.model.rest.LabelTypeResult;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.PhenologyResult;
import org.jacq.common.model.rest.RelevancyTypeResult;
import org.jacq.common.model.rest.SeparationTypeResult;
import org.jacq.common.model.rest.SexResult;
import org.jacq.common.model.rest.SpecimenResult;
import org.jacq.common.model.rest.VegetativeResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.service.manager.LivingPlantManager;
import org.jacq.service.manager.VegetativeManager;

/**
 * Implementation of derivative service
 *
 * @see DerivativeService
 * @author wkoller
 */
@ManagedBean
public class DerivativeServiceImpl implements DerivativeService {

    @Inject
    protected DerivativeManager derivativeManager;

    @Inject
    protected LivingPlantManager livingPlantManager;

    @Inject
    protected VegetativeManager vegetativeManager;

    /**
     * @see DerivativeService#find(java.lang.String, java.lang.Long,
     * java.lang.String, org.jacq.common.model.rest.OrderDirection,
     * java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BotanicalObjectDerivative> find(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String gatheringLocation, Long exhibition, Long working, String cultivar, Boolean classification, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        return derivativeManager.find(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, gatheringLocation, exhibition, working, cultivar, classification, orderColumn, orderDirection, offset, count);
    }

    /**
     * @see DerivativeService#count(java.lang.String, java.lang.Long)
     */
    @Override
    public int count(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String gatheringLocation, Long exhibition, Long working, String cultivar, Boolean classification) {
        return derivativeManager.count(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, gatheringLocation, exhibition, working, cultivar, classification);
    }

    /**
     * @see DerivativeService#load(java.lang.Long, java.lang.String)
     */
    @Override
    public Response load(Long derivativeId, String type) {
        return Response.ok().entity(derivativeManager.load(derivativeId, type)).build();
    }

    @Override
    public List<BotanicalObjectDownloadResult> downloadFind(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String gatheringLocation, Long exhibition, Long working, String cultivar, Boolean classification, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        return derivativeManager.downloadFind(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, gatheringLocation, exhibition, working, cultivar, classification, orderColumn, orderDirection, offset, count);
    }

    /**
     * @see DerivativeService#findAllPhenology()
     */
    @Override
    public List<PhenologyResult> findAllPhenology() {
        return derivativeManager.findAllPhenology();
    }

    /**
     * @see DerivativeService#findAllIdentStatus()
     */
    @Override
    public List<IdentStatusResult> findAllIdentStatus() {
        return derivativeManager.findAllIdentStatus();
    }

    /**
     * @see DerivativeService#findAllRelevancyType()
     */
    @Override
    public List<RelevancyTypeResult> findAllRelevancyType() {
        return derivativeManager.findAllRelevancyType();
    }

    /**
     * @see DerivativeService#findAllImportantRelevancyType()
     */
    @Override
    public List<RelevancyTypeResult> findAllImportantRelevancyType() {
        return derivativeManager.findAllImportantRelevancyType();
    }

    /**
     * @see DerivativeService#findAllSeparationType()
     */
    @Override
    public List<SeparationTypeResult> findAllSeparationType() {
        return derivativeManager.findAllSeparationType();
    }

    /**
     * @see DerivativeService#findAllCertificateType()
     */
    @Override
    public List<CertificateTypeResult> findAllCertificateType() {
        return derivativeManager.findAllCertificateType();
    }

    /**
     * @see
     * DerivativeService#livingPlantSave(org.jacq.common.model.rest.LivingPlantResult)
     */
    @Override
    public LivingPlantResult livingPlantSave(LivingPlantResult livingPlantResult) {
        return livingPlantManager.save(livingPlantResult);
    }

    /**
     * @see DerivativeService#findAllSex()
     */
    @Override
    public List<SexResult> findAllSex() {
        return derivativeManager.findAllSex();
    }

    /**
     * @see DerivativeService#vegetativeFind(java.lang.Long)
     */
    @Override
    public List<VegetativeResult> vegetativeFind(Long derivativeId) {
        return derivativeManager.vegetativeFind(derivativeId);
    }

    /**
     * @see DerivativeService#specimenFind(java.lang.Long)
     */
    @Override
    public List<SpecimenResult> specimenFind(Long botanicalObjectId) {
        return derivativeManager.specimenFind(botanicalObjectId);
    }

    /**
     * @see
     * DerivativeService#vegetativeSave(org.jacq.common.model.rest.VegetativeResult)
     */
    @Override
    public VegetativeResult vegetativeSave(VegetativeResult vegetativeResult) {
        return vegetativeManager.vegetativeSave(vegetativeResult);
    }

    /**
     * @see DerivativeService#findAllLabelType()
     */
    @Override
    public List<LabelTypeResult> findAllLabelType() {
        return derivativeManager.findAllLabelType();
    }

    /**
     * @see DerivativeService#cultivarFind()
     * @param cultivar
     * @param offset
     * @param count
     * @return
     */
    @Override
    public List<CultivarResult> cultivarFind(String cultivar, Integer offset, Integer count) {
        return derivativeManager.cultivarFind(cultivar, offset, count);
    }

    /**
     * @see DerivativeService#cultivarLoad()
     * @param cultivarId
     * @return
     */
    @Override
    public CultivarResult cultivarLoad(Long cultivarId) {
        return derivativeManager.cultivarLoad(cultivarId);
    }

    /**
     * @see DerivativeService#removeIndexSeminumMarking(java.lang.String,
     * java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.Long, java.lang.Long, java.lang.Boolean, java.lang.Boolean,
     * java.lang.String, java.lang.Long, java.lang.Long, java.lang.String,
     * java.lang.Boolean, java.lang.String,
     * org.jacq.common.model.rest.OrderDirection, java.lang.Integer,
     * java.lang.Integer)
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param scientificNameId
     * @param organisationId
     * @param hierarchic
     * @param indexSeminum
     * @param gatheringLocation
     * @param exhibition
     * @param working
     * @param cultivar
     * @param classification
     * @param orderColumn
     * @param orderDirection
     * @return
     */
    @Override
    public List<BotanicalObjectDerivative> removeIndexSeminumMarking(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String gatheringLocation, Long exhibition, Long working, String cultivar, Boolean classification, String orderColumn, OrderDirection orderDirection) {
        return this.derivativeManager.removeIndexSeminumMarking(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, gatheringLocation, exhibition, working, cultivar, classification, orderColumn, orderDirection);
    }

    /**
     * @see DerivativeService#removeLabelMarking(java.lang.String,
     * java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.Long, java.lang.Long, java.lang.Boolean, java.lang.Boolean,
     * java.lang.String, java.lang.Long, java.lang.Long, java.lang.String,
     * java.lang.Boolean, java.lang.String,
     * org.jacq.common.model.rest.OrderDirection)
     * @param type
     * @param derivativeId
     * @param placeNumber
     * @param accessionNumber
     * @param separated
     * @param scientificNameId
     * @param organisationId
     * @param hierarchic
     * @param indexSeminum
     * @param gatheringLocation
     * @param exhibition
     * @param working
     * @param cultivar
     * @param classification
     * @param orderColumn
     * @param orderDirection
     * @return
     */
    @Override
    public List<BotanicalObjectDerivative> removeLabelMarking(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, Boolean indexSeminum, String gatheringLocation, Long exhibition, Long working, String cultivar, Boolean classification, String orderColumn, OrderDirection orderDirection) {
        return this.derivativeManager.removeLabelMarking(type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationId, hierarchic, indexSeminum, gatheringLocation, exhibition, working, cultivar, classification, orderColumn, orderDirection);
    }

}
