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

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.rest.LivingPlantResult;

/**
 * Business logic regarding living plants
 *
 * @author wkoller
 */
@ManagedBean
public class LivingPlantManager {

    @PersistenceContext
    protected EntityManager em;

    /**
     * @see DerivativeService#saveLivingPlant(org.jacq.common.model.rest.LivingPlantResult)
     */
    @Transactional
    public LivingPlantResult saveLivingPlant(LivingPlantResult livingPlantResult) {
        TblLivingPlant tblLivingPlant = null;

        // check if we edit an existing entry or create a new one
        if (livingPlantResult.getDerivativeId() != null) {
            tblLivingPlant = em.find(TblLivingPlant.class, livingPlantResult.getDerivativeId());
        }
        else {
            tblLivingPlant = new TblLivingPlant();
        }

        // Living plant properties
        tblLivingPlant.setReviewed(livingPlantResult.isReviewed());
        tblLivingPlant.setIpenType(livingPlantResult.getIpenType());
        tblLivingPlant.setIpenNumber(livingPlantResult.getIpenNumber());
        tblLivingPlant.setIpenLocked(livingPlantResult.isIpenLocked());
        tblLivingPlant.setCultureNotes(livingPlantResult.getCultureNotes());
        tblLivingPlant.setIndexSeminum(livingPlantResult.getIndexSeminum());
        tblLivingPlant.setPhytoControl(livingPlantResult.getPhytoControl());
        tblLivingPlant.setBgci(livingPlantResult.getBgci());
        tblLivingPlant.setCultivationDate(livingPlantResult.getCultivationDate());
        tblLivingPlant.setLabelAnnotation(livingPlantResult.getLabelAnnotation());
        tblLivingPlant.setPlaceNumber(livingPlantResult.getPlaceNumber());
        /*
        this.cultivar = new CultivarResult(tblLivingPlant.getCultivarId());
        this.alternativeAccessionNumbers = AlternativeAccessionNumberResult.fromList(tblLivingPlant.getTblAlternativeAccessionNumberList());
        this.indexSeminumType = new IndexSeminumTypeResult(tblLivingPlant.getIndexSeminumTypeId());
        this.certificates = CertificateResult.fromList(tblLivingPlant.getTblCertificateList());
        this.relevancyTypes = RelevancyTypeResult.fromList(tblLivingPlant.getTblRelevancyTypeList());
         */

        // derivative properties
        TblDerivative tblDerivative = tblLivingPlant.getTblDerivative();
        if (tblDerivative == null) {
            tblDerivative = new TblDerivative();
            tblLivingPlant.setTblDerivative(tblDerivative);
        }
        tblDerivative.setCount(livingPlantResult.getCount());
        tblDerivative.setPrice(livingPlantResult.getPrice());
        tblDerivative.setOrganisationId(em.find(TblOrganisation.class, livingPlantResult.getOrganisationId()));
        /*
        this.separations = SeparationResult.fromList(tblLivingPlant.getTblDerivative().getTblSeparationList());
         */

        // botanical object properties
        TblBotanicalObject tblBotanicalObject = tblDerivative.getBotanicalObjectId();
        if (tblBotanicalObject == null) {
            tblBotanicalObject = new TblBotanicalObject();
            tblDerivative.setBotanicalObjectId(tblBotanicalObject);
        }
        tblBotanicalObject.setHabitat(livingPlantResult.getHabitat());
        tblBotanicalObject.setRecordingDate(livingPlantResult.getRecordingDate());
        tblBotanicalObject.setRedetermine(livingPlantResult.getRedetermine());
        tblBotanicalObject.setDeterminationDate(livingPlantResult.getDeterminationDate());
        tblBotanicalObject.setAnnotation(livingPlantResult.getGeneralAnnotation());
        tblBotanicalObject.setSeparated(livingPlantResult.getSeparated());
        tblBotanicalObject.setScientificNameId(livingPlantResult.getScientificNameId());
        /*
        this.identStatus = new IdentStatusResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getIdentStatusId());
        this.determinedBy = new PersonResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getDeterminedById());
        this.phenology = new PhenologyResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getPhenologyId());
        this.acquistionEventSources = AcquistionEventSourceResult.fromList(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getTblAcquisitionEventSourceList());
         */


 /*
        try {
            this.incomingDate = acquisitionDateFormat.parse(
                    String.format(
                            "%04d-%02d-%02d",
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getYear()),
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getMonth()),
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getDay())
                    )
            );
        } catch (Exception ex) {
        }

        if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId() != null) {
            this.gatheringNumber = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getNumber();
            this.gatheringAnnotation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAnnotation();
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId() != null) {
                if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear() != null) {
                    try {
                        this.gatheringDate = acquisitionDateFormat.parse(
                                String.format(
                                        "%04d-%02d-%02d",
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear()),
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getMonth()),
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getDay())
                                )
                        );
                    } catch (Exception ex) {
                    }
                }
                this.customGatheringDate = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getCustom();
            }
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationId() != null) {
                this.gatheringLocation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationId().getLocation();
            }
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId() != null) {
                this.altitudeMin = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin();
                this.altitudeMax = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax();
                this.exactness = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getExactness();
                this.latitudeDegrees = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees();
                this.latitudeMinutes = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes();
                this.latitudeSeconds = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds();
                this.latitudeHalf = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeHalf();
                this.longitudeDegrees = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees();
                this.longitudeMinutes = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes();
                this.longitudeSeconds = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds();
                this.longitudeHalf = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeHalf();
            }
        }

         */
        // convert back to result and return it to caller
        return new LivingPlantResult(tblLivingPlant);
    }

}
