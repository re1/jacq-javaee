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
package org.jacq.common.model.rest;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;

/**
 * Model for transfering living plant details over REST interface
 *
 * @author wkoller
 */
public class LivingPlantResult extends BotanicalObjectDerivative {

    protected boolean reviewed;
    protected String ipenType;
    protected String ipenNumber;
    protected boolean ipenLocked;
    protected CultivarResult cultivar;
    protected String gatheringNumber;
    protected String cultureNotes;
    protected long count;
    protected List<AlternativeAccessionNumberResult> alternativeAccessionNumberResults;
    protected Date gatheringDate;
    protected String customGatheringDate;
    protected String gatheringLocation;

    protected Long altitudeMin;
    protected Long altitudeMax;
    protected Long exactness;
    protected Long latitudeDegrees;
    protected Long latitudeMinutes;
    protected Long latitudeSeconds;
    protected String latitudeHalf;
    protected Long longitudeDegrees;
    protected Long longitudeMinutes;
    protected Long longitudeSeconds;
    protected String longitudeHalf;
    protected String gatheringAnnotation;
    protected String habitat;
    protected Boolean indexSeminum;
    protected IndexSeminumTypeResult indexSeminumType;

    public LivingPlantResult() {
    }

    public LivingPlantResult(TblLivingPlant tblLivingPlant) {
        // BotanicalObjectDerivative properties
        this.setType(BotanicalObjectDerivative.LIVING);
        this.setDerivativeId(tblLivingPlant.getTblDerivative().getDerivativeId());
        this.setBotanicalObjectId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getId());
        this.setScientificName(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getViewScientificName().getScientificName());
        this.setAccessionNumber(String.format("%07d", tblLivingPlant.getAccessionNumber()));
        this.setLabelAnnotation(tblLivingPlant.getLabelAnnotation());
        this.setOrganisationDescription(tblLivingPlant.getTblDerivative().getOrganisationId().getDescription());
        this.setPlaceNumber(tblLivingPlant.getPlaceNumber());
        this.setDerivativeCount(tblLivingPlant.getTblDerivative().getCount());
        this.setSeparated(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getSeparated());
        this.setScientificNameId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getScientificNameId());

        // new properties
        this.reviewed = tblLivingPlant.getReviewed();
        this.ipenType = tblLivingPlant.getIpenType();
        this.ipenNumber = tblLivingPlant.getIpenNumber();
        this.ipenLocked = tblLivingPlant.getIpenLocked();
        this.cultivar = new CultivarResult(tblLivingPlant.getCultivarId());
        this.cultureNotes = tblLivingPlant.getCultureNotes();
        this.count = tblLivingPlant.getTblDerivative().getCount();
        this.alternativeAccessionNumberResults = AlternativeAccessionNumberResult.fromList(tblLivingPlant.getTblAlternativeAccessionNumberList());
        this.habitat = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getHabitat();
        this.indexSeminum = tblLivingPlant.getIndexSeminum();
        this.indexSeminumType = new IndexSeminumTypeResult(tblLivingPlant.getIndexSeminumTypeId());

        if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId() != null) {
            this.gatheringNumber = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getNumber();
            this.gatheringAnnotation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAnnotation();
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId() != null) {
                if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear() != null) {
                    this.gatheringDate = (new GregorianCalendar(
                            Integer.parseInt(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear()),
                            Integer.parseInt(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getMonth()),
                            Integer.parseInt(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getDay()))).getTime();
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

    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getIpenType() {
        return ipenType;
    }

    public void setIpenType(String ipenType) {
        this.ipenType = ipenType;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public boolean isIpenLocked() {
        return ipenLocked;
    }

    public void setIpenLocked(boolean ipenLocked) {
        this.ipenLocked = ipenLocked;
    }

    public CultivarResult getCultivar() {
        return cultivar;
    }

    public void setCultivar(CultivarResult cultivar) {
        this.cultivar = cultivar;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public String getCultureNotes() {
        return cultureNotes;
    }

    public void setCultureNotes(String cultureNotes) {
        this.cultureNotes = cultureNotes;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<AlternativeAccessionNumberResult> getAlternativeAccessionNumberResults() {
        return alternativeAccessionNumberResults;
    }

    public void setAlternativeAccessionNumberResults(List<AlternativeAccessionNumberResult> alternativeAccessionNumberResults) {
        this.alternativeAccessionNumberResults = alternativeAccessionNumberResults;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public String getCustomGatheringDate() {
        return customGatheringDate;
    }

    public void setCustomGatheringDate(String customGatheringDate) {
        this.customGatheringDate = customGatheringDate;
    }

    public String getGatheringLocation() {
        return gatheringLocation;
    }

    public void setGatheringLocation(String gatheringLocation) {
        this.gatheringLocation = gatheringLocation;
    }

    public Long getAltitudeMin() {
        return altitudeMin;
    }

    public void setAltitudeMin(Long altitudeMin) {
        this.altitudeMin = altitudeMin;
    }

    public Long getAltitudeMax() {
        return altitudeMax;
    }

    public void setAltitudeMax(Long altitudeMax) {
        this.altitudeMax = altitudeMax;
    }

    public Long getExactness() {
        return exactness;
    }

    public void setExactness(Long exactness) {
        this.exactness = exactness;
    }

    public Long getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(Long latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public Long getLatitudeMinutes() {
        return latitudeMinutes;
    }

    public void setLatitudeMinutes(Long latitudeMinutes) {
        this.latitudeMinutes = latitudeMinutes;
    }

    public Long getLatitudeSeconds() {
        return latitudeSeconds;
    }

    public void setLatitudeSeconds(Long latitudeSeconds) {
        this.latitudeSeconds = latitudeSeconds;
    }

    public String getLatitudeHalf() {
        return latitudeHalf;
    }

    public void setLatitudeHalf(String latitudeHalf) {
        this.latitudeHalf = latitudeHalf;
    }

    public Long getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(Long longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public Long getLongitudeMinutes() {
        return longitudeMinutes;
    }

    public void setLongitudeMinutes(Long longitudeMinutes) {
        this.longitudeMinutes = longitudeMinutes;
    }

    public Long getLongitudeSeconds() {
        return longitudeSeconds;
    }

    public void setLongitudeSeconds(Long longitudeSeconds) {
        this.longitudeSeconds = longitudeSeconds;
    }

    public String getLongitudeHalf() {
        return longitudeHalf;
    }

    public void setLongitudeHalf(String longitudeHalf) {
        this.longitudeHalf = longitudeHalf;
    }

    public String getGatheringAnnotation() {
        return gatheringAnnotation;
    }

    public void setGatheringAnnotation(String gatheringAnnotation) {
        this.gatheringAnnotation = gatheringAnnotation;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public Boolean getIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(Boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public IndexSeminumTypeResult getIndexSeminumType() {
        return indexSeminumType;
    }

    public void setIndexSeminumType(IndexSeminumTypeResult indexSeminumType) {
        this.indexSeminumType = indexSeminumType;
    }

}