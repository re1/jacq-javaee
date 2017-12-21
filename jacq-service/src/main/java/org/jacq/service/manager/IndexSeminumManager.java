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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblIndexSeminumContent;
import org.jacq.common.model.jpa.TblIndexSeminumPerson;
import org.jacq.common.model.jpa.TblIndexSeminumRevision;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblPerson;
import org.jacq.common.model.rest.IndexSeminumResult;

/**
 *
 * @author fhafner
 */
public class IndexSeminumManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * Create TblIndexSeminumRevision, find Organiation Tree Head of current
     * User Create TblIndexSeminumContent, based on BontanicalObjects in the
     * List of OrganisationTree Including TblIndexSeminumPerson
     *
     * @param indexSeminumResult
     * @return
     */
    @Transactional
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult) {

        // Create new TblIndexSeminumRevision Object set Parameter and Save to DB including set User
        TblIndexSeminumRevision tblIndexSeminumRevision = new TblIndexSeminumRevision();
        tblIndexSeminumRevision.setName(indexSeminumResult.getName());
        FrmwrkUser user = em.find(FrmwrkUser.class, 27L);
        tblIndexSeminumRevision.setUserId(user);
        em.persist(tblIndexSeminumRevision);

        // Load Organisation from User for finding Head of the Organisation Tree
        TblOrganisation tblOrganisation = user.getOrganisationId();
        tblOrganisation = findIndexSeminumStart(tblOrganisation);

        // No Organisation with IndexSeminum Start true
        if (tblOrganisation == null) {
            return null;
        }

        //Find all Organisation childs for a complet Botanical Object List
        List<TblOrganisation> organisationList = findchilds(tblOrganisation);
        organisationList.add(tblOrganisation);

        // Load the BotanicalObject list with Organisation Id in List
        Query query = em.createNamedQuery("TblBotanicalObject.findByOrganisationList").setParameter("organisationList", organisationList);
        List<TblBotanicalObject> botanicalObjectList = query.getResultList();

        // Create TblIndexSeminumContent and TblIndexSeminumPerson based on the BotanicalObject list
        for (TblBotanicalObject botanicalObject : botanicalObjectList) {
            //TODO Family

            // Tbl_index_seminum_content
            TblIndexSeminumContent tblIndexSeminumContent = new TblIndexSeminumContent();

            // family
            tblIndexSeminumContent.setFamily("UNKNOWN");
            // botanical_object_id
            tblIndexSeminumContent.setBotanicalObjectId(botanicalObject);
            // index_seminum_revision_id
            tblIndexSeminumContent.setIndexSeminumRevisionId(tblIndexSeminumRevision);

            // scientificname
            tblIndexSeminumContent.setScientificName(botanicalObject.getViewScientificName().getScientificName());

            if (botanicalObject.getTblLivingPlant() != null) {
                // accession_number
                tblIndexSeminumContent.setAccessionNumber(String.valueOf(botanicalObject.getTblLivingPlant().getAccessionNumber()));
                // ipen_number
                tblIndexSeminumContent.setIpenNumber(botanicalObject.getTblLivingPlant().getIpenNumber());
                // Type
                tblIndexSeminumContent.setIndexSeminumType(botanicalObject.getTblLivingPlant().getIndexSeminumTypeId().getType());
            }

            // habitat
            tblIndexSeminumContent.setHabitat(botanicalObject.getHabitat() != null ? botanicalObject.getHabitat() : null);

            if (botanicalObject.getAcquisitionEventId() != null) {
                // acquisition_location
                tblIndexSeminumContent.setAcquisitionLocation(botanicalObject.getAcquisitionEventId().getLocationId() != null ? botanicalObject.getAcquisitionEventId().getLocationId().getLocation() : null);
                // acqustition_number
                tblIndexSeminumContent.setAcquisitionNumber(botanicalObject.getAcquisitionEventId().getNumber() != null ? String.valueOf(botanicalObject.getAcquisitionEventId().getNumber()) : null);
                // acquisition_date
                if (!StringUtils.isEmpty(botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getCustom())) {
                    tblIndexSeminumContent.setAcquisitionDate(botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getCustom());
                } else {
                    tblIndexSeminumContent.setAcquisitionDate(botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getDay() + "." + botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getMonth() + "." + botanicalObject.getAcquisitionEventId().getAcquisitionDateId().getYear());
                }
                if (botanicalObject.getAcquisitionEventId().getLocationCoordinatesId() != null) {
                    // altitude_min
                    tblIndexSeminumContent.setAltitudeMin(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin() != null ? botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin() : null);
                    // altitude_max
                    tblIndexSeminumContent.setAltitudeMax(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax() != null ? botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax() : null);
                    // latitude
                    if (botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees() != null && botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes() != null && botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds() != null) {
                        tblIndexSeminumContent.setLatitude(String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds()));
                    }
                    // longitude
                    if (botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees() != null && botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes() != null && botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds() != null) {
                        tblIndexSeminumContent.setLongitude(String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes()) + "." + String.valueOf(botanicalObject.getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds()));
                    }

                }
            }
            // save Tbl_index_seminum_content to DB
            em.persist(tblIndexSeminumContent);
            // Load all Person to Acquisition Event
            List<TblPerson> tblPersonList = botanicalObject.getAcquisitionEventId().getTblPersonList();
            for (TblPerson person : tblPersonList) {

                // tbl_index_seminum_person
                TblIndexSeminumPerson tblIndexSeminumPerson = new TblIndexSeminumPerson();

                // index_seminum_content_id
                tblIndexSeminumPerson.setIndexSeminumContentId(tblIndexSeminumContent);
                // name
                tblIndexSeminumPerson.setName(person.getName());
                // save tbl_index_seminum_person to DB
                em.persist(tblIndexSeminumPerson);
            }

        }
        return new IndexSeminumResult(tblIndexSeminumRevision);
    }

    /**
     * Find the Head of the Organisation Tree
     *
     * @param tblOrganisation
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
     * Find all childs to the Head of the organisation Tree Recursiv
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
