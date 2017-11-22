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
package org.jacq.common.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(BotanicalObjectDerivativeKey.class)
public class BotanicalObjectDerivative implements Serializable {

    public static final String LIVING = "living";

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private long id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "derivative_id")
    private long derivativeId;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name")
    private String scientificName;
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
    @Size(max = 20)
    @Column(name = "place_number")
    private String placeNumber;
    @Column(name = "vegetative_count")
    private Long vegetativeCount;

    public BotanicalObjectDerivative() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(long derivativeId) {
        this.derivativeId = derivativeId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
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

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Long getVegetativeCount() {
        return vegetativeCount;
    }

    public void setVegetativeCount(Long vegetativeCount) {
        this.vegetativeCount = vegetativeCount;
    }

}
