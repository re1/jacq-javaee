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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblIndexSeminumContent;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IndexSeminumDownloadResult {

    protected Long indexSeminumContentId;
    protected Long indexSeminumRevisionId;
    protected Long botanicalObjectId;
    protected String accessionNumber;
    protected String family;
    protected String scientificName;
    protected String indexSeminumType;
    protected String ipenNumber;
    protected String acquisitionLocation;
    protected String habitat;
    protected String altitudeMin;
    protected String altitudeMax;
    protected String latitude;
    protected String longitude;
    protected String acquisitionDate;
    protected String Person;

    public IndexSeminumDownloadResult() {
    }

    public IndexSeminumDownloadResult(TblIndexSeminumContent tblIndexSeminumContent) {
        this.indexSeminumContentId = tblIndexSeminumContent.getIndexSeminumContentId();
        this.indexSeminumRevisionId = tblIndexSeminumContent.getIndexSeminumRevisionId().getIndexSeminumRevisionId();
        this.botanicalObjectId = tblIndexSeminumContent.getBotanicalObjectId().getId();
        this.accessionNumber = tblIndexSeminumContent.getAccessionNumber() != null ? tblIndexSeminumContent.getAccessionNumber() : null;
        this.family = tblIndexSeminumContent.getFamily() != null ? tblIndexSeminumContent.getFamily() : null;
        this.scientificName = tblIndexSeminumContent.getScientificName() != null ? tblIndexSeminumContent.getScientificName() : null;
        this.indexSeminumType = tblIndexSeminumContent.getIndexSeminumType() != null ? tblIndexSeminumContent.getIndexSeminumType() : null;
        this.ipenNumber = tblIndexSeminumContent.getIpenNumber() != null ? tblIndexSeminumContent.getIpenNumber() : null;
        this.acquisitionLocation = tblIndexSeminumContent.getAcquisitionLocation() != null ? tblIndexSeminumContent.getAcquisitionLocation() : null;
        this.habitat = tblIndexSeminumContent.getHabitat() != null ? tblIndexSeminumContent.getHabitat() : null;
        this.altitudeMin = tblIndexSeminumContent.getAltitudeMin() != null ? tblIndexSeminumContent.getAltitudeMin().toString() : null;
        this.altitudeMax = tblIndexSeminumContent.getAltitudeMax() != null ? tblIndexSeminumContent.getAltitudeMax().toString() : null;
        this.latitude = tblIndexSeminumContent.getLatitude() != null ? tblIndexSeminumContent.getLatitude() : null;
        this.longitude = tblIndexSeminumContent.getLongitude() != null ? tblIndexSeminumContent.getLongitude() : null;
        this.acquisitionDate = tblIndexSeminumContent.getAcquisitionDate() != null ? tblIndexSeminumContent.getAcquisitionDate() : null;

    }

    public Long getIndexSeminumContentId() {
        return indexSeminumContentId;
    }

    public void setIndexSeminumContentId(Long indexSeminumContentId) {
        this.indexSeminumContentId = indexSeminumContentId;
    }

    public Long getIndexSeminumRevisionId() {
        return indexSeminumRevisionId;
    }

    public void setIndexSeminumRevisionId(Long indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

    public Long getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(Long botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getIndexSeminumType() {
        return indexSeminumType;
    }

    public void setIndexSeminumType(String indexSeminumType) {
        this.indexSeminumType = indexSeminumType;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public String getAcquisitionLocation() {
        return acquisitionLocation;
    }

    public void setAcquisitionLocation(String acquisitionLocation) {
        this.acquisitionLocation = acquisitionLocation;
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

    public String getPerson() {
        return Person;
    }

    public void setPerson(String Person) {
        this.Person = Person;
    }

}
