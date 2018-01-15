/*
 * Copyright 2018 fhafner.
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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblDerivative;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.TblPerson;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BotanicalObjectDownloadResult extends BotanicalObjectDerivative {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    protected String habitat;
    protected String altitudeMin;
    protected String altitudeMax;
    protected String latitude;
    protected String longitude;
    protected String acquisitionDate;
    protected String acquisitionNumber;
    protected String ipenNumber;
    protected String family;
    protected String person;

    public BotanicalObjectDownloadResult() {
    }

    public BotanicalObjectDownloadResult(BotanicalObjectDerivative botanicalObjectDerivative) {
        // BotanicalObjectDerivative properties
        this.setType(botanicalObjectDerivative.getType());
        this.setDerivativeId(botanicalObjectDerivative.getDerivativeId());
        this.setBotanicalObjectId(botanicalObjectDerivative.getBotanicalObjectId());
        this.setScientificName(botanicalObjectDerivative.getScientificName());
        this.setAccessionNumber(botanicalObjectDerivative.getAccessionNumber());
        this.setLabelAnnotation(botanicalObjectDerivative.getLabelAnnotation());
        this.setOrganisationDescription(botanicalObjectDerivative.getOrganisationDescription());
        this.setPlaceNumber(botanicalObjectDerivative.getPlaceNumber());
        this.setDerivativeCount(botanicalObjectDerivative.getDerivativeCount());
        this.setSeparated(botanicalObjectDerivative.getSeparated());
        this.setScientificNameId(botanicalObjectDerivative.getScientificNameId());
        super.setCultivarName(botanicalObjectDerivative.getCultivarName());

        TblDerivative derivative = em.find(TblDerivative.class, botanicalObjectDerivative.getDerivativeId());

        //TODO Family
        if (derivative.getBotanicalObjectId().getAcquisitionEventId() != null) {
            // acqustition_number
            this.setAcquisitionNumber(derivative.getBotanicalObjectId().getAcquisitionEventId().getNumber() != null ? String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getNumber()) : null);
            // acquisition_date
            if (!StringUtils.isEmpty(derivative.getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getCustom())) {
                this.setAcquisitionDate(derivative.getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getCustom());
            }
            else {
                this.setAcquisitionDate(derivative.getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getDay() + "." + derivative.getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getMonth() + "." + derivative.getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear());
            }
            if (derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId() != null) {
                // altitude_min
                this.setAltitudeMin(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin() != null ? derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin().toString() : null);
                // altitude_max
                this.setAltitudeMax(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax() != null ? derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax().toString() : null);
                // latitude
                if (derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees() != null && derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes() != null && derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds() != null) {
                    this.setLatitude(String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees()) + "." + String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes()) + "." + String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds()));
                }
                // longitude
                if (derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees() != null && derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes() != null && derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds() != null) {
                    this.setLongitude(String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees()) + "." + String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes()) + "." + String.valueOf(derivative.getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds()));
                }

            }
        }
        if (derivative.getTblLivingPlant() != null) {
            // accession_number
            this.setAccessionNumber(String.valueOf(derivative.getTblLivingPlant().getAccessionNumber()));
            // ipen_number
            this.setIpenNumber(derivative.getTblLivingPlant().getIpenNumber());
        }
        // habitat
        this.setHabitat(derivative.getBotanicalObjectId().getHabitat() != null ? derivative.getBotanicalObjectId().getHabitat() : null);

        // Load all Person to Acquisition Event
        List<TblPerson> tblPersonList = derivative.getBotanicalObjectId().getAcquisitionEventId().getTblPersonList();
        for (TblPerson person : tblPersonList) {
            this.setPerson(this.getPerson() != null ? this.getPerson() : "" + person.getName() + ",");
        }
        this.setPerson(this.getPerson().replaceAll(", $", ""));

    }

    /**
     * Helper function for converting a list of BotanicalObjectDerivative entries to BotanicalObjectDownloadResult
     *
     * @param botanicalObjectDerivativeList
     * @return
     */
    public static List<BotanicalObjectDownloadResult> fromList(List<BotanicalObjectDerivative> botanicalObjectDerivativeList) {
        List<BotanicalObjectDownloadResult> botanicalObjectDownloadResults = new ArrayList<>();

        if (botanicalObjectDerivativeList != null) {
            for (BotanicalObjectDerivative botanicalObjectDerivative : botanicalObjectDerivativeList) {
                botanicalObjectDownloadResults.add(new BotanicalObjectDownloadResult(botanicalObjectDerivative));
            }
        }

        return botanicalObjectDownloadResults;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAcquisitionNumber() {
        return acquisitionNumber;
    }

    public void setAcquisitionNumber(String acquisitionNumber) {
        this.acquisitionNumber = acquisitionNumber;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getAltitudeMin() {
        return altitudeMin;
    }

    public void setAltitudeMin(String altitudeMin) {
        this.altitudeMin = altitudeMin;
    }

    public String getAltitudeMax() {
        return altitudeMax;
    }

    public void setAltitudeMax(String altitudeMax) {
        this.altitudeMax = altitudeMax;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

}
