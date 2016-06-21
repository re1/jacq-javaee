/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_location_geonames")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLocationGeonames.findAll", query = "SELECT t FROM TblLocationGeonames t"),
    @NamedQuery(name = "TblLocationGeonames.findById", query = "SELECT t FROM TblLocationGeonames t WHERE t.id = :id"),
    @NamedQuery(name = "TblLocationGeonames.findByGeonameId", query = "SELECT t FROM TblLocationGeonames t WHERE t.geonameId = :geonameId"),
    @NamedQuery(name = "TblLocationGeonames.findByCountryCode", query = "SELECT t FROM TblLocationGeonames t WHERE t.countryCode = :countryCode")})
public class TblLocationGeonames implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "service_data")
    private String serviceData;
    @Basic(optional = false)
    @NotNull
    @Column(name = "geonameId")
    private int geonameId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "countryCode")
    private String countryCode;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblLocation tblLocation;

    public TblLocationGeonames() {
    }

    public TblLocationGeonames(Integer id) {
        this.id = id;
    }

    public TblLocationGeonames(Integer id, String serviceData, int geonameId, String countryCode) {
        this.id = id;
        this.serviceData = serviceData;
        this.geonameId = geonameId;
        this.countryCode = countryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceData() {
        return serviceData;
    }

    public void setServiceData(String serviceData) {
        this.serviceData = serviceData;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public TblLocation getTblLocation() {
        return tblLocation;
    }

    public void setTblLocation(TblLocation tblLocation) {
        this.tblLocation = tblLocation;
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
        if (!(object instanceof TblLocationGeonames)) {
            return false;
        }
        TblLocationGeonames other = (TblLocationGeonames) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLocationGeonames[ id=" + id + " ]";
    }

}
