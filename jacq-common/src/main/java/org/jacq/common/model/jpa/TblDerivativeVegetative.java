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
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_derivative_vegetative")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDerivativeVegetative.findAll", query = "SELECT t FROM TblDerivativeVegetative t")
    , @NamedQuery(name = "TblDerivativeVegetative.findByDerivativeVegetativeId", query = "SELECT t FROM TblDerivativeVegetative t WHERE t.derivativeVegetativeId = :derivativeVegetativeId")
    , @NamedQuery(name = "TblDerivativeVegetative.findByAccessionNumber", query = "SELECT t FROM TblDerivativeVegetative t WHERE t.accessionNumber = :accessionNumber")
    , @NamedQuery(name = "TblDerivativeVegetative.findByCultivationDate", query = "SELECT t FROM TblDerivativeVegetative t WHERE t.cultivationDate = :cultivationDate")
    , @NamedQuery(name = "TblDerivativeVegetative.findByIndexSeminum", query = "SELECT t FROM TblDerivativeVegetative t WHERE t.indexSeminum = :indexSeminum")})
public class TblDerivativeVegetative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "derivative_vegetative_id")
    private Long derivativeVegetativeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accession_number")
    private int accessionNumber;
    @Column(name = "cultivation_date")
    @Temporal(TemporalType.DATE)
    private Date cultivationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "index_seminum")
    private boolean indexSeminum;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @OneToMany(mappedBy = "derivativeVegetativeId", fetch = FetchType.LAZY)
    private Collection<TblSeparation> tblSeparationCollection;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblLivingPlant livingPlantId;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation organisationId;
    @JoinColumn(name = "phenology_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblPhenology phenologyId;

    public TblDerivativeVegetative() {
    }

    public TblDerivativeVegetative(Long derivativeVegetativeId) {
        this.derivativeVegetativeId = derivativeVegetativeId;
    }

    public TblDerivativeVegetative(Long derivativeVegetativeId, int accessionNumber, boolean indexSeminum) {
        this.derivativeVegetativeId = derivativeVegetativeId;
        this.accessionNumber = accessionNumber;
        this.indexSeminum = indexSeminum;
    }

    public Long getDerivativeVegetativeId() {
        return derivativeVegetativeId;
    }

    public void setDerivativeVegetativeId(Long derivativeVegetativeId) {
        this.derivativeVegetativeId = derivativeVegetativeId;
    }

    public int getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(int accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public Date getCultivationDate() {
        return cultivationDate;
    }

    public void setCultivationDate(Date cultivationDate) {
        this.cultivationDate = cultivationDate;
    }

    public boolean getIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @XmlTransient
    public Collection<TblSeparation> getTblSeparationCollection() {
        return tblSeparationCollection;
    }

    public void setTblSeparationCollection(Collection<TblSeparation> tblSeparationCollection) {
        this.tblSeparationCollection = tblSeparationCollection;
    }

    public TblLivingPlant getLivingPlantId() {
        return livingPlantId;
    }

    public void setLivingPlantId(TblLivingPlant livingPlantId) {
        this.livingPlantId = livingPlantId;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
    }

    public TblPhenology getPhenologyId() {
        return phenologyId;
    }

    public void setPhenologyId(TblPhenology phenologyId) {
        this.phenologyId = phenologyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (derivativeVegetativeId != null ? derivativeVegetativeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDerivativeVegetative)) {
            return false;
        }
        TblDerivativeVegetative other = (TblDerivativeVegetative) object;
        if ((this.derivativeVegetativeId == null && other.derivativeVegetativeId != null) || (this.derivativeVegetativeId != null && !this.derivativeVegetativeId.equals(other.derivativeVegetativeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblDerivativeVegetative[ derivativeVegetativeId=" + derivativeVegetativeId + " ]";
    }

}
