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
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "view_botanical_object_search")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewBotanicalObjectSearch.findAll", query = "SELECT v FROM ViewBotanicalObjectSearch v")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findById", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.id = :id")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByDerivativeId", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.derivativeId = :derivativeId")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByScientificNameId", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByGenus", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.genus = :genus")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByEpithet", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.epithet = :epithet")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByOrganisationId", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.organisationId = :organisationId")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByOrganisationDescription", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.organisationDescription = :organisationDescription")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByPlaceNumber", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.placeNumber = :placeNumber")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findBySeparated", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.separated = :separated")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByIndexSeminum", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.indexSeminum = :indexSeminum")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByGreenhouse", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.greenhouse = :greenhouse")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByAccessionNumber", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.accessionNumber = :accessionNumber")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByVegetativeCount", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.vegetativeCount = :vegetativeCount")
    , @NamedQuery(name = "ViewBotanicalObjectSearch.findByType", query = "SELECT v FROM ViewBotanicalObjectSearch v WHERE v.type = :type")})
public class ViewBotanicalObjectSearch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "derivative_id")
    private int derivativeId;
    @Column(name = "scientific_name_id")
    private Integer scientificNameId;
    @Size(max = 100)
    @Column(name = "genus")
    private String genus;
    @Size(max = 50)
    @Column(name = "epithet")
    private String epithet;
    @Column(name = "organisation_id")
    private Integer organisationId;
    @Size(max = 255)
    @Column(name = "organisation_description")
    private String organisationDescription;
    @Lob
    @Size(max = 16777215)
    @Column(name = "location")
    private String location;
    @Size(max = 20)
    @Column(name = "place_number")
    private String placeNumber;
    @Column(name = "separated")
    private Short separated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "index_seminum")
    private short indexSeminum;
    @Column(name = "greenhouse")
    private Short greenhouse;
    @Size(max = 19)
    @Column(name = "accession_number")
    private String accessionNumber;
    @Column(name = "vegetative_count")
    private BigInteger vegetativeCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type")
    private String type;

    public ViewBotanicalObjectSearch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(int derivativeId) {
        this.derivativeId = derivativeId;
    }

    public Integer getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Integer scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getEpithet() {
        return epithet;
    }

    public void setEpithet(String epithet) {
        this.epithet = epithet;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisationDescription() {
        return organisationDescription;
    }

    public void setOrganisationDescription(String organisationDescription) {
        this.organisationDescription = organisationDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Short getSeparated() {
        return separated;
    }

    public void setSeparated(Short separated) {
        this.separated = separated;
    }

    public short getIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(short indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public Short getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(Short greenhouse) {
        this.greenhouse = greenhouse;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public BigInteger getVegetativeCount() {
        return vegetativeCount;
    }

    public void setVegetativeCount(BigInteger vegetativeCount) {
        this.vegetativeCount = vegetativeCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
