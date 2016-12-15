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
import javax.persistence.CascadeType;
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
@Table(name = "tbl_index_seminum_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndexSeminumContent.findAll", query = "SELECT t FROM TblIndexSeminumContent t"),
    @NamedQuery(name = "TblIndexSeminumContent.findByIndexSeminumContentId", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.indexSeminumContentId = :indexSeminumContentId"),
    @NamedQuery(name = "TblIndexSeminumContent.findByIndexSeminumType", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.indexSeminumType = :indexSeminumType"),
    @NamedQuery(name = "TblIndexSeminumContent.findByIpenNumber", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.ipenNumber = :ipenNumber"),
    @NamedQuery(name = "TblIndexSeminumContent.findByHabitat", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.habitat = :habitat"),
    @NamedQuery(name = "TblIndexSeminumContent.findByAltitudeMin", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.altitudeMin = :altitudeMin"),
    @NamedQuery(name = "TblIndexSeminumContent.findByAltitudeMax", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.altitudeMax = :altitudeMax"),
    @NamedQuery(name = "TblIndexSeminumContent.findByLatitude", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "TblIndexSeminumContent.findByLongitude", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.longitude = :longitude"),
    @NamedQuery(name = "TblIndexSeminumContent.findByAcquisitionDate", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.acquisitionDate = :acquisitionDate"),
    @NamedQuery(name = "TblIndexSeminumContent.findByTimestamp", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.timestamp = :timestamp")})
public class TblIndexSeminumContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_seminum_content_id")
    private Integer indexSeminumContentId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "accession_number")
    private String accessionNumber;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "family")
    private String family;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "scientific_name")
    private String scientificName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "index_seminum_type")
    private String indexSeminumType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 28)
    @Column(name = "ipen_number")
    private String ipenNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "acquisition_number")
    private String acquisitionNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "acquisition_location")
    private String acquisitionLocation;
    @Size(max = 45)
    @Column(name = "habitat")
    private String habitat;
    @Column(name = "altitude_min")
    private Integer altitudeMin;
    @Column(name = "altitude_max")
    private Integer altitudeMax;
    @Size(max = 14)
    @Column(name = "latitude")
    private String latitude;
    @Size(max = 14)
    @Column(name = "longitude")
    private String longitude;
    @Size(max = 20)
    @Column(name = "acquisition_date")
    private String acquisitionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indexSeminumContentId")
    private Collection<TblIndexSeminumPerson> tblIndexSeminumPersonCollection;
    @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblBotanicalObject botanicalObjectId;
    @JoinColumn(name = "index_seminum_revision_id", referencedColumnName = "index_seminum_revision_id")
    @ManyToOne(optional = false)
    private TblIndexSeminumRevision indexSeminumRevisionId;

    public TblIndexSeminumContent() {
    }

    public TblIndexSeminumContent(Integer indexSeminumContentId) {
        this.indexSeminumContentId = indexSeminumContentId;
    }

    public TblIndexSeminumContent(Integer indexSeminumContentId, String accessionNumber, String family, String scientificName, String indexSeminumType, String ipenNumber, Date timestamp) {
        this.indexSeminumContentId = indexSeminumContentId;
        this.accessionNumber = accessionNumber;
        this.family = family;
        this.scientificName = scientificName;
        this.indexSeminumType = indexSeminumType;
        this.ipenNumber = ipenNumber;
        this.timestamp = timestamp;
    }

    public Integer getIndexSeminumContentId() {
        return indexSeminumContentId;
    }

    public void setIndexSeminumContentId(Integer indexSeminumContentId) {
        this.indexSeminumContentId = indexSeminumContentId;
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

    public String getAcquisitionNumber() {
        return acquisitionNumber;
    }

    public void setAcquisitionNumber(String acquisitionNumber) {
        this.acquisitionNumber = acquisitionNumber;
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

    public Integer getAltitudeMin() {
        return altitudeMin;
    }

    public void setAltitudeMin(Integer altitudeMin) {
        this.altitudeMin = altitudeMin;
    }

    public Integer getAltitudeMax() {
        return altitudeMax;
    }

    public void setAltitudeMax(Integer altitudeMax) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblIndexSeminumPerson> getTblIndexSeminumPersonCollection() {
        return tblIndexSeminumPersonCollection;
    }

    public void setTblIndexSeminumPersonCollection(Collection<TblIndexSeminumPerson> tblIndexSeminumPersonCollection) {
        this.tblIndexSeminumPersonCollection = tblIndexSeminumPersonCollection;
    }

    public TblBotanicalObject getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(TblBotanicalObject botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public TblIndexSeminumRevision getIndexSeminumRevisionId() {
        return indexSeminumRevisionId;
    }

    public void setIndexSeminumRevisionId(TblIndexSeminumRevision indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indexSeminumContentId != null ? indexSeminumContentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndexSeminumContent)) {
            return false;
        }
        TblIndexSeminumContent other = (TblIndexSeminumContent) object;
        if ((this.indexSeminumContentId == null && other.indexSeminumContentId != null) || (this.indexSeminumContentId != null && !this.indexSeminumContentId.equals(other.indexSeminumContentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIndexSeminumContent[ indexSeminumContentId=" + indexSeminumContentId + " ]";
    }

}
