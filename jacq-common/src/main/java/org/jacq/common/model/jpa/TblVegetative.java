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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_vegetative")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblVegetative.findAll", query = "SELECT t FROM TblVegetative t")
    , @NamedQuery(name = "TblVegetative.findByVegetativeId", query = "SELECT t FROM TblVegetative t WHERE t.vegetativeId = :vegetativeId")
    , @NamedQuery(name = "TblVegetative.findByAccessionNumber", query = "SELECT t FROM TblVegetative t WHERE t.accessionNumber = :accessionNumber")
    , @NamedQuery(name = "TblVegetative.findByCultivationDate", query = "SELECT t FROM TblVegetative t WHERE t.cultivationDate = :cultivationDate")
    , @NamedQuery(name = "TblVegetative.findByIndexSeminum", query = "SELECT t FROM TblVegetative t WHERE t.indexSeminum = :indexSeminum")
    , @NamedQuery(name = "TblVegetative.findByPlaceNumber", query = "SELECT t FROM TblVegetative t WHERE t.placeNumber = :placeNumber")
    , @NamedQuery(name = "TblVegetative.findBySeparated", query = "SELECT t FROM TblVegetative t WHERE t.separated = :separated")})
public class TblVegetative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vegetative_id")
    private Long vegetativeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accession_number")
    private long accessionNumber;
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
    @Size(max = 20)
    @Column(name = "place_number")
    private String placeNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "separated")
    private boolean separated;
    @OneToMany(mappedBy = "derivativeVegetativeId")
    private List<TblSeparation> tblSeparationList;
    @JoinColumn(name = "phenology_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPhenology phenologyId;
    @JoinColumn(name = "vegetative_id", referencedColumnName = "derivative_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblDerivative tblDerivative;

    public TblVegetative() {
    }

    public TblVegetative(Long vegetativeId) {
        this.vegetativeId = vegetativeId;
    }

    public TblVegetative(Long vegetativeId, long accessionNumber, boolean indexSeminum, boolean separated) {
        this.vegetativeId = vegetativeId;
        this.accessionNumber = accessionNumber;
        this.indexSeminum = indexSeminum;
        this.separated = separated;
    }

    public Long getVegetativeId() {
        return vegetativeId;
    }

    public void setVegetativeId(Long vegetativeId) {
        this.vegetativeId = vegetativeId;
    }

    public long getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(long accessionNumber) {
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

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public boolean getSeparated() {
        return separated;
    }

    public void setSeparated(boolean separated) {
        this.separated = separated;
    }

    @XmlTransient
    public List<TblSeparation> getTblSeparationList() {
        return tblSeparationList;
    }

    public void setTblSeparationList(List<TblSeparation> tblSeparationList) {
        this.tblSeparationList = tblSeparationList;
    }

    public TblPhenology getPhenologyId() {
        return phenologyId;
    }

    public void setPhenologyId(TblPhenology phenologyId) {
        this.phenologyId = phenologyId;
    }

    public TblDerivative getTblDerivative() {
        return tblDerivative;
    }

    public void setTblDerivative(TblDerivative tblDerivative) {
        this.tblDerivative = tblDerivative;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vegetativeId != null ? vegetativeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblVegetative)) {
            return false;
        }
        TblVegetative other = (TblVegetative) object;
        if ((this.vegetativeId == null && other.vegetativeId != null) || (this.vegetativeId != null && !this.vegetativeId.equals(other.vegetativeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblVegetative[ vegetativeId=" + vegetativeId + " ]";
    }

}
