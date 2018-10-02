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

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model for holding a single import record
 *
 * @author wkoller
 */
@Entity
@XmlRootElement
public class ImportRecord implements Serializable {

    /**
     * Reference to originating importFile object, must not be null
     */
    @Transient
    protected ImportFile importFile;

    @Id
    @Column(name = "original_id")
    protected Long originalId;
    @Column(name = "specimen_number")
    protected String specimenNumber;
    @Column(name = "livingplant_number")
    protected String livingPlantNumber;
    @Column(name = "alternative_living_plant_number")
    protected String alternativeLivingPlantNumber;
    @Column(name = "organization")
    protected String organization;
    @Column(name = "scientific_name")
    protected String scientificName;
    @Column(name = "alternative_number")
    protected String alternativeNumber;
    @Column(name = "generic_annotation")
    protected String genericAnnotation;
    @Column(name = "gathering_number")
    protected String gatheringNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "separation_date")
    protected Date separationDate;
    @Column(name = "separation_type")
    protected String separationType;
    @Column(name = "separation_annotation")
    protected String separationAnnotation;
    @Column(name = "label_annotation")
    protected String labelAnnotation;
    @Column(name = "match_family")
    protected String matchFamily;
    @Column(name = "ipen_number")
    protected String ipenNumber;
    @Column(name = "culture_notes")
    protected String cultureNotes;
    @Column(name = "place_number")
    protected String placeNumber;
    @Column(name = "count")
    protected Long count;
    @Column(name = "source_name")
    protected String sourceName;
    @Column(name = "original_botanical_object_id")
    protected Long originalBotanicalObjectId;
    @Column(name = "cultivar")
    protected String cultivar;
    @Column(name = "common_names")
    protected String commonNames;
    @Column(name = "price")
    protected Float price;
    @Column(name = "gathering_date")
    protected Date gatheringDate;
    @Column(name = "gathering_source")
    protected String gatheringSource;
    @Column(name = "altitude_min")
    protected Long altitudeMin;
    @Column(name = "altitude_max")
    protected Long altitudeMax;
    @Column(name = "ident_status")
    protected Long identStatus;
    @Column(name = "gathering_person")
    protected String gatheringPerson;
    @Column(name = "gathering_location")
    protected String gatheringLocation;
    @Column(name = "default_scientific_name_id")
    protected Long defaultScientificNameId;
    @Column(name = "habitat")
    protected String habitat;
    @Column(name = "acquisition_date")
    protected Date acquisitionDate;
    @Column(name = "custom_acquisition_date")
    protected String customAcquisitionDate;

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

    public String getAlternativeLivingPlantNumber() {
        return alternativeLivingPlantNumber;
    }

    public void setAlternativeLivingPlantNumber(String alternativeLivingPlantNumber) {
        this.alternativeLivingPlantNumber = alternativeLivingPlantNumber;
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

    public String getMatchFamily() {
        return matchFamily;
    }

    public void setMatchFamily(String matchFamily) {
        this.matchFamily = matchFamily;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public ImportFile getImportFile() {
        return importFile;
    }

    public void setImportFile(ImportFile importFile) {
        this.importFile = importFile;
    }

    public String getCultureNotes() {
        return cultureNotes;
    }

    public void setCultureNotes(String cultureNotes) {
        this.cultureNotes = cultureNotes;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Long getOriginalBotanicalObjectId() {
        return originalBotanicalObjectId;
    }

    public void setOriginalBotanicalObjectId(Long originalBotanicalObjectId) {
        this.originalBotanicalObjectId = originalBotanicalObjectId;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public String getGatheringSource() {
        return gatheringSource;
    }

    public void setGatheringSource(String gatheringSource) {
        this.gatheringSource = gatheringSource;
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

    public Long getIdentStatus() {
        return identStatus;
    }

    public void setIdentStatus(Long identStatus) {
        this.identStatus = identStatus;
    }

    public String getGatheringPerson() {
        return gatheringPerson;
    }

    public void setGatheringPerson(String gatheringPerson) {
        this.gatheringPerson = gatheringPerson;
    }

    public String getGatheringLocation() {
        return gatheringLocation;
    }

    public void setGatheringLocation(String gatheringLocation) {
        this.gatheringLocation = gatheringLocation;
    }

    public Long getDefaultScientificNameId() {
        return defaultScientificNameId;
    }

    public void setDefaultScientificNameId(Long defaultScientificNameId) {
        this.defaultScientificNameId = defaultScientificNameId;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getCustomAcquisitionDate() {
        return customAcquisitionDate;
    }

    public void setCustomAcquisitionDate(String customAcquisitionDate) {
        this.customAcquisitionDate = customAcquisitionDate;
    }

    /**
     * Helper function for converting the import record to a single line string
     * for appending to the annotation field of an existing record
     *
     * @return
     */
    public String toAnnotationString() {
        String annotationString = "";

        if (originalId != null) {
            annotationString += originalId + ";";
        }
        if (specimenNumber != null) {
            annotationString += specimenNumber + ";";
        }
        if (livingPlantNumber != null) {
            annotationString += livingPlantNumber + ";";
        }
        if (alternativeLivingPlantNumber != null) {
            annotationString += alternativeLivingPlantNumber + ";";
        }
        if (organization != null) {
            annotationString += organization + ";";
        }
        if (scientificName != null) {
            annotationString += scientificName + ";";
        }
        if (alternativeNumber != null) {
            annotationString += alternativeNumber + ";";
        }
        if (genericAnnotation != null) {
            annotationString += genericAnnotation + ";";
        }
        if (gatheringNumber != null) {
            annotationString += gatheringNumber + ";";
        }
        if (separationDate != null) {
            annotationString += separationDate + ";";
        }
        if (separationType != null) {
            annotationString += separationType + ";";
        }
        if (separationAnnotation != null) {
            annotationString += separationAnnotation + ";";
        }
        if (labelAnnotation != null) {
            annotationString += labelAnnotation + ";";
        }
        if (ipenNumber != null) {
            annotationString += ipenNumber + ";";
        }

        return annotationString;
    }
}
