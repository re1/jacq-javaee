/*
 * Copyright 2016 wkoller.
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
package org.jacq.common.model.dataimport;

import java.util.Date;

/**
 * Model for holding a single import record
 *
 * @author wkoller
 */
public class ImportRecord {

    protected Long originalId;
    protected String specimenNumber;
    protected String livingPlantNumber;
    protected String organization;
    protected String scientificName;
    protected String alternativeNumber;
    protected String genericAnnotation;
    protected String gatheringNumber;
    protected Date separationDate;
    protected String separationType;
    protected String separationAnnotation;
    protected String labelAnnotation;

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public String getSpecimenNumber() {
        return specimenNumber;
    }

    public void setSpecimenNumber(String specimenNumber) {
        this.specimenNumber = specimenNumber;
    }

    public String getLivingPlantNumber() {
        return livingPlantNumber;
    }

    public void setLivingPlantNumber(String livingPlantNumber) {
        this.livingPlantNumber = livingPlantNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    public String getGenericAnnotation() {
        return genericAnnotation;
    }

    public void setGenericAnnotation(String genericAnnotation) {
        this.genericAnnotation = genericAnnotation;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public Date getSeparationDate() {
        return separationDate;
    }

    public void setSeparationDate(Date separationDate) {
        this.separationDate = separationDate;
    }

    public String getSeparationType() {
        return separationType;
    }

    public void setSeparationType(String separationType) {
        this.separationType = separationType;
    }

    public String getSeparationAnnotation() {
        return separationAnnotation;
    }

    public void setSeparationAnnotation(String separationAnnotation) {
        this.separationAnnotation = separationAnnotation;
    }

    public String getLabelAnnotation() {
        return labelAnnotation;
    }

    public void setLabelAnnotation(String labelAnnotation) {
        this.labelAnnotation = labelAnnotation;
    }

}
