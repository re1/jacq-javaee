/*
 * Copyright 2019 wkoller.
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_location_coordinates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLocationCoordinates.findAll", query = "SELECT t FROM TblLocationCoordinates t")
    , @NamedQuery(name = "TblLocationCoordinates.findById", query = "SELECT t FROM TblLocationCoordinates t WHERE t.id = :id")
    , @NamedQuery(name = "TblLocationCoordinates.findByAltitudeMin", query = "SELECT t FROM TblLocationCoordinates t WHERE t.altitudeMin = :altitudeMin")
    , @NamedQuery(name = "TblLocationCoordinates.findByAltitudeMax", query = "SELECT t FROM TblLocationCoordinates t WHERE t.altitudeMax = :altitudeMax")
    , @NamedQuery(name = "TblLocationCoordinates.findByExactness", query = "SELECT t FROM TblLocationCoordinates t WHERE t.exactness = :exactness")
    , @NamedQuery(name = "TblLocationCoordinates.findByLatitudeDegrees", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeDegrees = :latitudeDegrees")
    , @NamedQuery(name = "TblLocationCoordinates.findByLatitudeMinutes", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeMinutes = :latitudeMinutes")
    , @NamedQuery(name = "TblLocationCoordinates.findByLatitudeSeconds", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeSeconds = :latitudeSeconds")
    , @NamedQuery(name = "TblLocationCoordinates.findByLatitudeHalf", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeHalf = :latitudeHalf")
    , @NamedQuery(name = "TblLocationCoordinates.findByLongitudeDegrees", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeDegrees = :longitudeDegrees")
    , @NamedQuery(name = "TblLocationCoordinates.findByLongitudeMinutes", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeMinutes = :longitudeMinutes")
    , @NamedQuery(name = "TblLocationCoordinates.findByLongitudeSeconds", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeSeconds = :longitudeSeconds")
    , @NamedQuery(name = "TblLocationCoordinates.findByLongitudeHalf", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeHalf = :longitudeHalf")})
public class TblLocationCoordinates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "altitude_min")
    private Long altitudeMin;
    @Column(name = "altitude_max")
    private Long altitudeMax;
    @Column(name = "exactness")
    private Long exactness;
    @Column(name = "latitude_degrees")
    private Long latitudeDegrees;
    @Column(name = "latitude_minutes")
    private Long latitudeMinutes;
    @Column(name = "latitude_seconds")
    private Long latitudeSeconds;
    @Size(max = 2)
    @Column(name = "latitude_half")
    private String latitudeHalf;
    @Column(name = "longitude_degrees")
    private Long longitudeDegrees;
    @Column(name = "longitude_minutes")
    private Long longitudeMinutes;
    @Column(name = "longitude_seconds")
    private Long longitudeSeconds;
    @Size(max = 2)
    @Column(name = "longitude_half")
    private String longitudeHalf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationCoordinatesId", fetch = FetchType.LAZY)
    private List<TblAcquisitionEvent> tblAcquisitionEventList;

    public TblLocationCoordinates() {
    }

    public TblLocationCoordinates(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlTransient
    public List<TblAcquisitionEvent> getTblAcquisitionEventList() {
        return tblAcquisitionEventList;
    }

    public void setTblAcquisitionEventList(List<TblAcquisitionEvent> tblAcquisitionEventList) {
        this.tblAcquisitionEventList = tblAcquisitionEventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLocationCoordinates)) {
            return false;
        }
        TblLocationCoordinates other = (TblLocationCoordinates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLocationCoordinates[ id=" + id + " ]";
    }

}
