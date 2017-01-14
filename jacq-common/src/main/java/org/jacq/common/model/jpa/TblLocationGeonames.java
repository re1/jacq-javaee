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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "TblLocationGeonames.findAll", query = "SELECT t FROM TblLocationGeonames t")
    , @NamedQuery(name = "TblLocationGeonames.findById", query = "SELECT t FROM TblLocationGeonames t WHERE t.id = :id")
    , @NamedQuery(name = "TblLocationGeonames.findByGeonameId", query = "SELECT t FROM TblLocationGeonames t WHERE t.geonameId = :geonameId")
    , @NamedQuery(name = "TblLocationGeonames.findByCountryCode", query = "SELECT t FROM TblLocationGeonames t WHERE t.countryCode = :countryCode")})
public class TblLocationGeonames implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
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
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblLocation tblLocation;

    public TblLocationGeonames() {
    }

    public TblLocationGeonames(Long id) {
        this.id = id;
    }

    public TblLocationGeonames(Long id, String serviceData, int geonameId, String countryCode) {
        this.id = id;
        this.serviceData = serviceData;
        this.geonameId = geonameId;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
