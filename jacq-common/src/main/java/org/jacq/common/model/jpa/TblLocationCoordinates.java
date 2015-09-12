/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "TblLocationCoordinates.findAll", query = "SELECT t FROM TblLocationCoordinates t"),
    @NamedQuery(name = "TblLocationCoordinates.findById", query = "SELECT t FROM TblLocationCoordinates t WHERE t.id = :id"),
    @NamedQuery(name = "TblLocationCoordinates.findByAltitudeMin", query = "SELECT t FROM TblLocationCoordinates t WHERE t.altitudeMin = :altitudeMin"),
    @NamedQuery(name = "TblLocationCoordinates.findByAltitudeMax", query = "SELECT t FROM TblLocationCoordinates t WHERE t.altitudeMax = :altitudeMax"),
    @NamedQuery(name = "TblLocationCoordinates.findByExactness", query = "SELECT t FROM TblLocationCoordinates t WHERE t.exactness = :exactness"),
    @NamedQuery(name = "TblLocationCoordinates.findByLatitudeDegrees", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeDegrees = :latitudeDegrees"),
    @NamedQuery(name = "TblLocationCoordinates.findByLatitudeMinutes", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeMinutes = :latitudeMinutes"),
    @NamedQuery(name = "TblLocationCoordinates.findByLatitudeSeconds", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeSeconds = :latitudeSeconds"),
    @NamedQuery(name = "TblLocationCoordinates.findByLatitudeHalf", query = "SELECT t FROM TblLocationCoordinates t WHERE t.latitudeHalf = :latitudeHalf"),
    @NamedQuery(name = "TblLocationCoordinates.findByLongitudeDegrees", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeDegrees = :longitudeDegrees"),
    @NamedQuery(name = "TblLocationCoordinates.findByLongitudeMinutes", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeMinutes = :longitudeMinutes"),
    @NamedQuery(name = "TblLocationCoordinates.findByLongitudeSeconds", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeSeconds = :longitudeSeconds"),
    @NamedQuery(name = "TblLocationCoordinates.findByLongitudeHalf", query = "SELECT t FROM TblLocationCoordinates t WHERE t.longitudeHalf = :longitudeHalf")})
public class TblLocationCoordinates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "altitude_min")
    private Integer altitudeMin;
    @Column(name = "altitude_max")
    private Integer altitudeMax;
    @Column(name = "exactness")
    private Integer exactness;
    @Column(name = "latitude_degrees")
    private Integer latitudeDegrees;
    @Column(name = "latitude_minutes")
    private Integer latitudeMinutes;
    @Column(name = "latitude_seconds")
    private Integer latitudeSeconds;
    @Size(max = 2)
    @Column(name = "latitude_half")
    private String latitudeHalf;
    @Column(name = "longitude_degrees")
    private Integer longitudeDegrees;
    @Column(name = "longitude_minutes")
    private Integer longitudeMinutes;
    @Column(name = "longitude_seconds")
    private Integer longitudeSeconds;
    @Size(max = 2)
    @Column(name = "longitude_half")
    private String longitudeHalf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationCoordinatesId")
    private Collection<TblAcquisitionEvent> tblAcquisitionEventCollection;

    public TblLocationCoordinates() {
    }

    public TblLocationCoordinates(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getExactness() {
        return exactness;
    }

    public void setExactness(Integer exactness) {
        this.exactness = exactness;
    }

    public Integer getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(Integer latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public Integer getLatitudeMinutes() {
        return latitudeMinutes;
    }

    public void setLatitudeMinutes(Integer latitudeMinutes) {
        this.latitudeMinutes = latitudeMinutes;
    }

    public Integer getLatitudeSeconds() {
        return latitudeSeconds;
    }

    public void setLatitudeSeconds(Integer latitudeSeconds) {
        this.latitudeSeconds = latitudeSeconds;
    }

    public String getLatitudeHalf() {
        return latitudeHalf;
    }

    public void setLatitudeHalf(String latitudeHalf) {
        this.latitudeHalf = latitudeHalf;
    }

    public Integer getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(Integer longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public Integer getLongitudeMinutes() {
        return longitudeMinutes;
    }

    public void setLongitudeMinutes(Integer longitudeMinutes) {
        this.longitudeMinutes = longitudeMinutes;
    }

    public Integer getLongitudeSeconds() {
        return longitudeSeconds;
    }

    public void setLongitudeSeconds(Integer longitudeSeconds) {
        this.longitudeSeconds = longitudeSeconds;
    }

    public String getLongitudeHalf() {
        return longitudeHalf;
    }

    public void setLongitudeHalf(String longitudeHalf) {
        this.longitudeHalf = longitudeHalf;
    }

    @XmlTransient
    public Collection<TblAcquisitionEvent> getTblAcquisitionEventCollection() {
        return tblAcquisitionEventCollection;
    }

    public void setTblAcquisitionEventCollection(Collection<TblAcquisitionEvent> tblAcquisitionEventCollection) {
        this.tblAcquisitionEventCollection = tblAcquisitionEventCollection;
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