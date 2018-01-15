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
package org.jacq.common.model.jpa.custom;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Union object for displaying and search botanical object derivatives
 *
 * @author wkoller
 */
@Entity
@XmlRootElement
public class BotanicalObjectDerivative implements Serializable {

    public static final String LIVING = "living";

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "derivative_id")
    private Long derivativeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "botanical_object_id")
    private Long botanicalObjectId;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name")
    private String scientificName;
    @Column(name = "scientific_name_id")
    private Long scientificNameId;
    @Size(max = 7)
    @Column(name = "accession_number")
    private String accessionNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "label_annotation")
    private String labelAnnotation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "type")
    private String type;
    @Lob
    @Size(max = 65535)
    @Column(name = "organisation_description")
    private String organisationDescription;
    @Column(name = "organisation_id")
    private Long organisationId;
    @Size(max = 20)
    @Column(name = "place_number")
    private String placeNumber;
    @Column(name = "derivative_count")
    private Long derivativeCount;
    @Column(name = "separated")
    private Boolean separated;
    @Column(name = "cultivar_name")
    private String cultivarName;

    public BotanicalObjectDerivative() {
    }

    public Long getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(Long derivativeId) {
        this.derivativeId = derivativeId;
    }

    public Long getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(Long botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getLabelAnnotation() {
        return labelAnnotation;
    }

    public void setLabelAnnotation(String labelAnnotation) {
        this.labelAnnotation = labelAnnotation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganisationDescription() {
        return organisationDescription;
    }

    public void setOrganisationDescription(String organisationDescription) {
        this.organisationDescription = organisationDescription;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Long getDerivativeCount() {
        return derivativeCount;
    }

    public void setDerivativeCount(Long derivativeCount) {
        this.derivativeCount = derivativeCount;
    }

    public Boolean getSeparated() {
        return separated;
    }

    public void setSeparated(Boolean separated) {
        this.separated = separated;
    }

    public String getCultivarName() {
        return cultivarName;
    }

    public void setCultivarName(String cultivarName) {
        this.cultivarName = cultivarName;
    }

}
