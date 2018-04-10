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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.TblAcquisitionDate;
import org.jacq.common.model.jpa.TblAcquisitionEvent;
import org.jacq.common.model.jpa.TblAcquisitionEventSource;
import org.jacq.common.model.jpa.TblAcquisitionSource;
import org.jacq.common.model.jpa.TblAcquisitionType;
import org.jacq.common.model.jpa.TblAlternativeAccessionNumber;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblCertificate;
import org.jacq.common.model.jpa.TblCertificateType;
import org.jacq.common.model.jpa.TblCultivar;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblDerivativeType;
import org.jacq.common.model.jpa.TblIdentStatus;
import org.jacq.common.model.jpa.TblIndexSeminumType;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblLocation;
import org.jacq.common.model.jpa.TblLocationCoordinates;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblPerson;
import org.jacq.common.model.jpa.TblPhenology;
import org.jacq.common.model.jpa.TblRelevancyType;
import org.jacq.common.model.jpa.TblSeparation;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.jpa.TblSex;
import org.jacq.common.model.jpa.TblSpecimen;
import org.jacq.common.model.rest.AcquistionEventSourceResult;
import org.jacq.common.model.rest.AlternativeAccessionNumberResult;
import org.jacq.common.model.rest.CertificateResult;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.PersonResult;
import org.jacq.common.model.rest.SpecimenResult;
import org.jacq.common.model.rest.RelevancyTypeResult;
import org.jacq.common.model.rest.SeparationResult;
import org.jacq.common.model.rest.SexResult;
import org.jacq.common.rest.DerivativeService;

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
    @Transactional(rollbackOn = Exception.class)
    public LivingPlantResult save(LivingPlantResult livingPlantResult) {
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
        tblLivingPlant.setIndexSeminum(livingPlantResult.isIndexSeminum());
        tblLivingPlant.setPhytoControl(livingPlantResult.isPhytoControl());
        tblLivingPlant.setBgci(livingPlantResult.isBgci());
        tblLivingPlant.setCultivationDate(livingPlantResult.getCultivationDate());
        tblLivingPlant.setLabelAnnotation(livingPlantResult.getLabelAnnotation());
        tblLivingPlant.setPlaceNumber(livingPlantResult.getPlaceNumber());
        tblLivingPlant.setCultivarId((livingPlantResult.getCultivar() != null && livingPlantResult.getCultivar().getCultivarId() != null) ? em.find(TblCultivar.class, livingPlantResult.getCultivar().getCultivarId()) : null);
        tblLivingPlant.setIndexSeminumTypeId((livingPlantResult.getIndexSeminumType() != null && livingPlantResult.getIndexSeminumType().getIndexSeminumTypeId() != null) ? em.find(TblIndexSeminumType.class, livingPlantResult.getIndexSeminumType().getIndexSeminumTypeId()) : null);

        if (livingPlantResult.getLabelSynonymScientificName() != null) {
            tblLivingPlant.setLabelSynonymScientificNameId((livingPlantResult.getLabelSynonymScientificName().getScientificNameId() != null) ? livingPlantResult.getLabelSynonymScientificName().getScientificNameId() : null);
        }

        // assign relevancy
        if (tblLivingPlant.getTblRelevancyTypeList() == null) {
            tblLivingPlant.setTblRelevancyTypeList(new ArrayList<TblRelevancyType>());
        }
        tblLivingPlant.getTblRelevancyTypeList().clear();
        for (RelevancyTypeResult relevancyType : livingPlantResult.getRelevancyTypes()) {
            if (relevancyType.getRelevancyTypeId() != null) {
                tblLivingPlant.getTblRelevancyTypeList().add(em.find(TblRelevancyType.class, relevancyType.getRelevancyTypeId()));
            }
        }

        // save incoming date
        TblAcquisitionDate tblIncomingDate = tblLivingPlant.getIncomingDateId();
        if (tblIncomingDate == null) {
            tblIncomingDate = new TblAcquisitionDate();
            tblLivingPlant.setIncomingDateId(tblIncomingDate);
        }
        if (livingPlantResult.getIncomingDate() != null) {
            tblIncomingDate.setYear(String.valueOf(livingPlantResult.getIncomingDate().getYear() + 1900));
            tblIncomingDate.setMonth(String.valueOf(livingPlantResult.getIncomingDate().getMonth() + 1));
            tblIncomingDate.setDay(String.valueOf(livingPlantResult.getIncomingDate().getDate()));
        }

        // derivative properties
        TblDerivative tblDerivative = tblLivingPlant.getTblDerivative();
        if (tblDerivative == null) {
            tblDerivative = new TblDerivative();
            tblLivingPlant.setTblDerivative(tblDerivative);
        }
        tblDerivative.setCount(livingPlantResult.getCount());
        tblDerivative.setPrice(livingPlantResult.getPrice());
        tblDerivative.setOrganisationId(em.find(TblOrganisation.class, livingPlantResult.getOrganisation().getOrganisationId()));
        tblDerivative.setDerivativeTypeId(em.find(TblDerivativeType.class, 1L));

        // botanical object properties
        TblBotanicalObject tblBotanicalObject = tblDerivative.getBotanicalObjectId();
        if (tblBotanicalObject == null) {
            tblBotanicalObject = new TblBotanicalObject();
            tblBotanicalObject.setRecordingDate(new Date());
            tblDerivative.setBotanicalObjectId(tblBotanicalObject);
        }
        tblBotanicalObject.setHabitat(livingPlantResult.getHabitat());
        tblBotanicalObject.setRedetermine(livingPlantResult.isRedetermine());
        tblBotanicalObject.setDeterminationDate(livingPlantResult.getDeterminationDate());
        tblBotanicalObject.setAnnotation(livingPlantResult.getGeneralAnnotation());
        tblBotanicalObject.setSeparated(livingPlantResult.getSeparated());
        tblBotanicalObject.setIdentStatusId((livingPlantResult.getIdentStatus() != null && livingPlantResult.getIdentStatus().getIdentStatusId() != null) ? em.find(TblIdentStatus.class, livingPlantResult.getIdentStatus().getIdentStatusId()) : null);
        tblBotanicalObject.setPhenologyId((livingPlantResult.getPhenology() != null && livingPlantResult.getPhenology().getPhenologyId() != null) ? em.find(TblPhenology.class, livingPlantResult.getPhenology().getPhenologyId()) : null);
        tblBotanicalObject.setScientificNameId(livingPlantResult.getScientificNameResult().getScientificNameId());

        // assign sexes
        if (tblBotanicalObject.getTblSexList() == null) {
            tblBotanicalObject.setTblSexList(new ArrayList<TblSex>());
        }
        tblBotanicalObject.getTblSexList().clear();
        for (SexResult sex : livingPlantResult.getSexes()) {
            if (sex.getSexId() != null) {
                tblBotanicalObject.getTblSexList().add(em.find(TblSex.class, sex.getSexId()));
            }
        }

        // save determined by person
        if (livingPlantResult.getDeterminedBy() != null && !StringUtils.isEmpty(livingPlantResult.getDeterminedBy().getName())) {
            TypedQuery<TblPerson> personQuery = em.createNamedQuery("TblPerson.findByName", TblPerson.class);
            personQuery.setParameter("name", livingPlantResult.getDeterminedBy().getName());
            List<TblPerson> personList = personQuery.getResultList();
            TblPerson determinedBy = null;
            if (personList != null && personList.size() > 0) {
                determinedBy = personList.get(0);
            }
            else {
                determinedBy = new TblPerson();
                determinedBy.setName(livingPlantResult.getDeterminedBy().getName());
                em.persist(determinedBy);
            }
            tblBotanicalObject.setDeterminedById(determinedBy);
        }

        // lookup or add gathering location
        TblLocation tblGatheringLocation = null;
        if (livingPlantResult.getGatheringLocation() != null && !StringUtils.isEmpty(livingPlantResult.getGatheringLocation().getLocation())) {
            TypedQuery<TblLocation> locationQuery = em.createNamedQuery("TblLocation.findByLocation", TblLocation.class);
            locationQuery.setParameter("location", livingPlantResult.getGatheringLocation().getLocation());
            List<TblLocation> locationList = locationQuery.getResultList();
            if (locationList != null & locationList.size() > 0) {
                tblGatheringLocation = locationList.get(0);
            }
            else {
                tblGatheringLocation = new TblLocation();
                tblGatheringLocation.setLocation(livingPlantResult.getGatheringLocation().getLocation());
                em.persist(tblGatheringLocation);
            }
        }

        // save gathering event
        TblAcquisitionEvent tblAcquisitionEvent = tblBotanicalObject.getAcquisitionEventId();
        if (tblAcquisitionEvent == null) {
            tblAcquisitionEvent = new TblAcquisitionEvent();
            tblBotanicalObject.setAcquisitionEventId(tblAcquisitionEvent);
        }
        tblAcquisitionEvent.setNumber(livingPlantResult.getGatheringNumber());
        tblAcquisitionEvent.setAnnotation(livingPlantResult.getGatheringAnnotation());
        // living plants do not use the required acquisition type relation, that's why we set it to unknown by default
        tblAcquisitionEvent.setAcquisitionTypeId(em.find(TblAcquisitionType.class, 1L));
        tblAcquisitionEvent.setLocationId(tblGatheringLocation);

        // save gatherers list
        if (tblAcquisitionEvent.getTblPersonList() == null) {
            tblAcquisitionEvent.setTblPersonList(new ArrayList<TblPerson>());
        }
        tblAcquisitionEvent.getTblPersonList().clear();
        for (PersonResult gatherer : livingPlantResult.getGatherers()) {
            TypedQuery<TblPerson> tblGathererQuery = em.createNamedQuery("TblPerson.findByName", TblPerson.class);
            tblGathererQuery.setParameter("name", gatherer.getName());
            List<TblPerson> gatherers = tblGathererQuery.getResultList();
            TblPerson tblGatherer = null;
            if (gatherers != null & gatherers.size() > 0) {
                tblGatherer = gatherers.get(0);
            }
            else {
                tblGatherer = new TblPerson();
                tblGatherer.setName(gatherer.getName());
                em.persist(tblGatherer);
            }

            tblAcquisitionEvent.getTblPersonList().add(tblGatherer);
        }

        // save gathering date
        TblAcquisitionDate tblGatheringDate = tblAcquisitionEvent.getAcquisitionDateId();
        if (tblGatheringDate == null) {
            tblGatheringDate = new TblAcquisitionDate();
            tblAcquisitionEvent.setAcquisitionDateId(tblGatheringDate);
        }
        tblGatheringDate.setCustom(livingPlantResult.getCustomGatheringDate());
        if (livingPlantResult.getGatheringDate() != null) {
            tblGatheringDate.setYear(String.valueOf(livingPlantResult.getGatheringDate().getYear() + 1900));
            tblGatheringDate.setMonth(String.valueOf(livingPlantResult.getGatheringDate().getMonth() + 1));
            tblGatheringDate.setDay(String.valueOf(livingPlantResult.getGatheringDate().getDate()));
        }

        // save gathering coordinates
        TblLocationCoordinates tblGatheringCoordinates = tblAcquisitionEvent.getLocationCoordinatesId();
        if (tblGatheringCoordinates == null) {
            tblGatheringCoordinates = new TblLocationCoordinates();
            tblAcquisitionEvent.setLocationCoordinatesId(tblGatheringCoordinates);
        }
        tblGatheringCoordinates.setAltitudeMin(livingPlantResult.getAltitudeMin());
        tblGatheringCoordinates.setAltitudeMax(livingPlantResult.getAltitudeMax());
        tblGatheringCoordinates.setExactness(livingPlantResult.getExactness());
        tblGatheringCoordinates.setLatitudeDegrees(livingPlantResult.getLatitudeDegrees());
        tblGatheringCoordinates.setLatitudeMinutes(livingPlantResult.getLatitudeMinutes());
        tblGatheringCoordinates.setLatitudeSeconds(livingPlantResult.getLatitudeSeconds());
        tblGatheringCoordinates.setLatitudeHalf(livingPlantResult.getLatitudeHalf());
        tblGatheringCoordinates.setLongitudeDegrees(livingPlantResult.getLongitudeDegrees());
        tblGatheringCoordinates.setLongitudeMinutes(livingPlantResult.getLongitudeMinutes());
        tblGatheringCoordinates.setLongitudeSeconds(livingPlantResult.getLongitudeSeconds());
        tblGatheringCoordinates.setLongitudeHalf(livingPlantResult.getLongitudeHalf());

        // persist entities in correct order
        em.persist(tblIncomingDate);
        em.persist(tblGatheringCoordinates);
        em.persist(tblGatheringDate);
        em.persist(tblAcquisitionEvent);
        em.persist(tblBotanicalObject);
        em.persist(tblDerivative);

        // living plant needs manual assignment of id
        tblLivingPlant.setId(tblDerivative.getDerivativeId());
        em.persist(tblLivingPlant);

        // save alternative accession numbers
        for (AlternativeAccessionNumberResult alternativeAccessionNumber : livingPlantResult.getAlternativeAccessionNumbers()) {
            TblAlternativeAccessionNumber tblAlternativeAccessionNumber = null;
            if (alternativeAccessionNumber.getAlternativeAccessionNumberId() != null) {
                tblAlternativeAccessionNumber = em.find(TblAlternativeAccessionNumber.class, alternativeAccessionNumber.getAlternativeAccessionNumberId());
            }
            else {
                tblAlternativeAccessionNumber = new TblAlternativeAccessionNumber();
            }

            // set properties
            tblAlternativeAccessionNumber.setLivingPlantId(tblLivingPlant);
            tblAlternativeAccessionNumber.setNumber(alternativeAccessionNumber.getNumber());

            // save alternative accession number
            em.persist(tblAlternativeAccessionNumber);
        }

        // save certificates
        for (CertificateResult certificate : livingPlantResult.getCertificates()) {
            TblCertificate tblCertificate = null;
            if (certificate.getCertificateId() != null) {
                tblCertificate = em.find(TblCertificate.class, certificate.getCertificateId());
            }
            else {
                tblCertificate = new TblCertificate();
            }

            // set properties
            tblCertificate.setLivingPlantId(tblLivingPlant);
            tblCertificate.setNumber(certificate.getNumber());
            tblCertificate.setAnnotation(certificate.getAnnotation());
            tblCertificate.setCertificateTypeId(em.find(TblCertificateType.class, certificate.getCertificateType().getCertificateTypeId()));

            // save certificate
            em.persist(tblCertificate);
        }

        // save separations
        for (SeparationResult separation : livingPlantResult.getSeparations()) {
            TblSeparation tblSeparation = null;
            if (separation.getSeparationId() != null) {
                tblSeparation = em.find(TblSeparation.class, separation.getSeparationId());
            }
            else {
                tblSeparation = new TblSeparation();
            }

            // set properties
            tblSeparation.setDerivativeId(tblDerivative);
            tblSeparation.setDate(separation.getDate());
            tblSeparation.setAnnotation(separation.getAnnotation());
            tblSeparation.setSeparationTypeId(em.find(TblSeparationType.class, separation.getSeparationType().getSeparationTypeId()));

            // save separation
            em.persist(tblSeparation);
        }

        // save acquisition event sources
        for (AcquistionEventSourceResult acquistionEventSource : livingPlantResult.getAcquistionEventSources()) {
            TblAcquisitionEventSource tblAcquisitionEventSource = null;
            if (acquistionEventSource.getAcquisitionEventSourceId() != null) {
                tblAcquisitionEventSource = em.find(TblAcquisitionEventSource.class, acquistionEventSource.getAcquisitionEventSourceId());
            }
            else {
                tblAcquisitionEventSource = new TblAcquisitionEventSource();
            }

            TblAcquisitionSource tblAcquisitionSource = null;
            if (acquistionEventSource.getAcquisitionEventSource().getAcquisitionSourceId() != null) {
                tblAcquisitionSource = em.find(TblAcquisitionSource.class, acquistionEventSource.getAcquisitionEventSource().getAcquisitionSourceId());
            }
            else {
                tblAcquisitionSource = new TblAcquisitionSource();
                tblAcquisitionSource.setName(acquistionEventSource.getAcquisitionEventSource().getName());
            }

            // set properties
            em.persist(tblAcquisitionSource);

            // set properties
            tblAcquisitionEventSource.setAcquisitionEventId(tblBotanicalObject.getAcquisitionEventId());
            tblAcquisitionEventSource.setAcquisitionSourceId(tblAcquisitionSource);
            tblAcquisitionEventSource.setSourceDate(acquistionEventSource.getSourceDate());

            // save acquisition
            em.persist(tblAcquisitionEventSource);
        }

        TypedQuery<TblSpecimen> specimenQuery = em.createNamedQuery("TblSpecimen.findByBotanicalObjectId", TblSpecimen.class);
        specimenQuery.setParameter("botanicalObjectId", em.find(TblBotanicalObject.class, tblBotanicalObject.getId()));

        // Deleted Entries remove from DB
        List<SpecimenResult> specimenResults = SpecimenResult.fromList(specimenQuery.getResultList());
        for (SpecimenResult specimenResult : specimenResults) {
            Boolean contains = false;
            for (SpecimenResult specimen : livingPlantResult.getSpecimensList()) {
                if (specimen.getSpecimenId() != null && Objects.equals(specimen.getSpecimenId(), specimenResult.getSpecimenId())) {
                    contains = true;
                }
            }
            if (!contains) {
                TblSpecimen tblSpecimen = em.find(TblSpecimen.class, specimenResult.getSpecimenId());
                tblDerivative = tblSpecimen.getTblDerivative();
                em.remove(tblSpecimen);
                em.remove(tblDerivative);
            }
        }

        // save specimen
        if (livingPlantResult.getSpecimensList() != null) {
            for (SpecimenResult specimenResult : livingPlantResult.getSpecimensList()) {
                if (specimenResult.getSpecimenId() == null) {
                    tblDerivative = new TblDerivative();
                    tblLivingPlant.setTblDerivative(tblDerivative);
                    tblDerivative.setCount(livingPlantResult.getCount());
                    tblDerivative.setPrice(livingPlantResult.getPrice());
                    tblDerivative.setOrganisationId(em.find(TblOrganisation.class, livingPlantResult.getOrganisation().getOrganisationId()));
                    tblDerivative.setDerivativeTypeId(em.find(TblDerivativeType.class, 3L));
                    tblDerivative.setBotanicalObjectId(tblBotanicalObject);
                    em.persist(tblDerivative);
                    TblSpecimen tblSpecimen = new TblSpecimen();
                    tblSpecimen.setSpecimenId(tblDerivative.getDerivativeId());
                    tblSpecimen.setHerbarNumber(specimenResult.getHerbarNumber());
                    tblSpecimen.setTblDerivative(tblDerivative);
                    em.persist(tblSpecimen);
                }
                else {
                    TblSpecimen tblSpecimen = em.find(TblSpecimen.class, specimenResult.getSpecimenId());
                    tblSpecimen.setHerbarNumber(specimenResult.getHerbarNumber());
                    em.merge(tblSpecimen);
                }
            }
        }

        // make sure changes are flushed to the database
        em.flush();
        // refresh botanical object in order to resolve manual relations
        em.refresh(tblBotanicalObject);
        em.refresh(tblLivingPlant);

        // convert back to result and return it to caller
        return new LivingPlantResult(tblLivingPlant);
    }

}
