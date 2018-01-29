/*
 * Copyright 2018 wkoller.
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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_derivative")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDerivative.findAll", query = "SELECT t FROM TblDerivative t")
    , @NamedQuery(name = "TblDerivative.findByDerivativeId", query = "SELECT t FROM TblDerivative t WHERE t.derivativeId = :derivativeId")
    , @NamedQuery(name = "TblDerivative.findByCount", query = "SELECT t FROM TblDerivative t WHERE t.count = :count")
    , @NamedQuery(name = "TblDerivative.findByPrice", query = "SELECT t FROM TblDerivative t WHERE t.price = :price")})
public class TblDerivative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "derivative_id")
    private Long derivativeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count")
    private long count;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblDerivative", fetch = FetchType.LAZY)
    private TblVegetative tblVegetative;
    @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblBotanicalObject botanicalObjectId;
    @JoinColumn(name = "derivative_type_id", referencedColumnName = "derivative_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblDerivativeType derivativeTypeId;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation organisationId;
    @OneToMany(mappedBy = "parentDerivativeId", fetch = FetchType.LAZY)
    private List<TblDerivative> tblDerivativeList;
    @JoinColumn(name = "parent_derivative_id", referencedColumnName = "derivative_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblDerivative parentDerivativeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "derivativeId", fetch = FetchType.LAZY)
    private List<TblImportProperties> tblImportPropertiesList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblDerivative", fetch = FetchType.LAZY)
    private TblLivingPlant tblLivingPlant;

    public TblDerivative() {
    }

    public TblDerivative(Long derivativeId) {
        this.derivativeId = derivativeId;
    }

    public TblDerivative(Long derivativeId, long count, float price) {
        this.derivativeId = derivativeId;
        this.count = count;
        this.price = price;
    }

    public Long getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(Long derivativeId) {
        this.derivativeId = derivativeId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public TblVegetative getTblVegetative() {
        return tblVegetative;
    }

    public void setTblVegetative(TblVegetative tblVegetative) {
        this.tblVegetative = tblVegetative;
    }

    public TblBotanicalObject getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(TblBotanicalObject botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public TblDerivativeType getDerivativeTypeId() {
        return derivativeTypeId;
    }

    public void setDerivativeTypeId(TblDerivativeType derivativeTypeId) {
        this.derivativeTypeId = derivativeTypeId;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
    }

    @XmlTransient
    public List<TblDerivative> getTblDerivativeList() {
        return tblDerivativeList;
    }

    public void setTblDerivativeList(List<TblDerivative> tblDerivativeList) {
        this.tblDerivativeList = tblDerivativeList;
    }

    public TblDerivative getParentDerivativeId() {
        return parentDerivativeId;
    }

    public void setParentDerivativeId(TblDerivative parentDerivativeId) {
        this.parentDerivativeId = parentDerivativeId;
    }

    @XmlTransient
    public List<TblImportProperties> getTblImportPropertiesList() {
        return tblImportPropertiesList;
    }

    public void setTblImportPropertiesList(List<TblImportProperties> tblImportPropertiesList) {
        this.tblImportPropertiesList = tblImportPropertiesList;
    }

    public TblLivingPlant getTblLivingPlant() {
        return tblLivingPlant;
    }

    public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
        this.tblLivingPlant = tblLivingPlant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (derivativeId != null ? derivativeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDerivative)) {
            return false;
        }
        TblDerivative other = (TblDerivative) object;
        if ((this.derivativeId == null && other.derivativeId != null) || (this.derivativeId != null && !this.derivativeId.equals(other.derivativeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblDerivative[ derivativeId=" + derivativeId + " ]";
    }

}
