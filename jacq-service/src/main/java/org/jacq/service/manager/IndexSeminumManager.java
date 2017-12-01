/*
 * Copyright 2017 fhafner.
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
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblIndexSeminumContent;
import org.jacq.common.model.jpa.TblIndexSeminumPerson;
import org.jacq.common.model.jpa.TblIndexSeminumRevision;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblScientificNameInformation;
import org.jacq.common.model.rest.IndexSeminumResult;

/**
 *
 * @author fhafner
 */
public class IndexSeminumManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     *
     * @param indexSeminumResult
     * @return
     */
    @Transactional
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult) {
        TblIndexSeminumRevision tblIndexSeminumRevision = new TblIndexSeminumRevision();
        tblIndexSeminumRevision.setName(indexSeminumResult.getName());
        tblIndexSeminumRevision.setUserId(em.find(FrmwrkUser.class, 1L));
        Calendar rightNow = Calendar.getInstance();
        tblIndexSeminumRevision.setTimestamp(rightNow.getTime());

        TblOrganisation tblOrganisation = em.find(TblOrganisation.class, 1L);
        tblOrganisation = findIndexSeminumStart(tblOrganisation);
        if (tblOrganisation == null) {
            return null;
        }

        List<TblOrganisation> organisationIdList = findchilds(tblOrganisation);
        organisationIdList.add(tblOrganisation);

        Query query = em.createQuery("SELECT t FROM TblBotanicalObject t WHERE t.organisationId in :organisationIdList").setParameter("organisationIdList", organisationIdList);
        List<TblBotanicalObject> botanicalObjectList = query.getResultList();

        for (TblBotanicalObject botanicalObject : botanicalObjectList) {
            em.persist(tblIndexSeminumRevision);
            TblIndexSeminumContent tblIndexSeminumContent = new TblIndexSeminumContent();
            tblIndexSeminumContent.setIndexSeminumRevisionId(tblIndexSeminumRevision);
            tblIndexSeminumContent.setBotanicalObjectId(botanicalObject);
            tblIndexSeminumContent.setAccessionNumber(String.valueOf(botanicalObject.getTblLivingPlant().getAccessionNumber()));
            TblScientificNameInformation tblScientificNameInformation = em.find(TblScientificNameInformation.class, botanicalObject.getScientificNameId());
            tblIndexSeminumContent.setScientificName(tblScientificNameInformation.getCommonNames());
            tblIndexSeminumContent.setIpenNumber(botanicalObject.getTblLivingPlant().getIpenNumber());
            if (botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getYear().equals("")) {
                tblIndexSeminumContent.setAcquisitionDate(botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getDay() + "." + botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getMonth() + "." + botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getYear());
            } else {
                tblIndexSeminumContent.setAcquisitionDate(botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getCustom());
            }
            tblIndexSeminumContent.setHabitat(botanicalObject.getHabitat());
            tblIndexSeminumContent.setAltitudeMin(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin());
            tblIndexSeminumContent.setAltitudeMax(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax());
            tblIndexSeminumContent.setLatitude(String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds()));
            tblIndexSeminumContent.setLongitude(String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds()));
            tblIndexSeminumContent.setAcquisitionLocation(botanicalObject.getAcquisitionEventId().getLocationId().getLocation());
            tblIndexSeminumContent.setTimestamp(rightNow.getTime());
            em.persist(tblIndexSeminumContent);
            TblIndexSeminumPerson tblIndexSeminumPerson = new TblIndexSeminumPerson();
            tblIndexSeminumPerson.setIndexSeminumContentId(tblIndexSeminumContent);
            tblIndexSeminumPerson.setName(botanicalObject.getDeterminedById().getName());
            tblIndexSeminumPerson.setTimestamp(rightNow.getTime());
            em.persist(tblIndexSeminumPerson);

        }

        return indexSeminumResult;
    }

    /**
     * Find the Head of the Organisation Tree
     *
     * @param tblorganisation
     * @return
     */
    protected TblOrganisation findIndexSeminumStart(TblOrganisation tblOrganisation) {
        while (tblOrganisation.getIndexSeminumStart() != true) {
            if (tblOrganisation.getParentOrganisationId() == null) {
                return null;
            }
            tblOrganisation = em.find(TblOrganisation.class, tblOrganisation.getParentOrganisationId().getId());
        }
        return tblOrganisation;

    }

    /**
     *
     * @param tblOrganisation
     * @return
     */
    protected List<TblOrganisation> findchilds(TblOrganisation tblOrganisation) {
        List<TblOrganisation> organisationIdList = new ArrayList<>();
        for (TblOrganisation organisation : tblOrganisation.getTblOrganisationList()) {
            organisationIdList.add(organisation);
            organisationIdList.addAll(findchilds(organisation));
        }
        return organisationIdList;

    }

}
