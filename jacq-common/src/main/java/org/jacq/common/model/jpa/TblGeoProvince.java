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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tbl_geo_province")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblGeoProvince.findAll", query = "SELECT t FROM TblGeoProvince t")
    , @NamedQuery(name = "TblGeoProvince.findByProvinceId", query = "SELECT t FROM TblGeoProvince t WHERE t.provinceId = :provinceId")
    , @NamedQuery(name = "TblGeoProvince.findByProvinz", query = "SELECT t FROM TblGeoProvince t WHERE t.provinz = :provinz")
    , @NamedQuery(name = "TblGeoProvince.findByProvinzLocal", query = "SELECT t FROM TblGeoProvince t WHERE t.provinzLocal = :provinzLocal")
    , @NamedQuery(name = "TblGeoProvince.findByProvinzCode", query = "SELECT t FROM TblGeoProvince t WHERE t.provinzCode = :provinzCode")
    , @NamedQuery(name = "TblGeoProvince.findByNationId", query = "SELECT t FROM TblGeoProvince t WHERE t.nationId = :nationId")
    , @NamedQuery(name = "TblGeoProvince.findByUsgsNumber", query = "SELECT t FROM TblGeoProvince t WHERE t.usgsNumber = :usgsNumber")})
public class TblGeoProvince implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "province_id")
    private int provinceId;
    @Size(max = 100)
    @Column(name = "provinz")
    private String provinz;
    @Size(max = 150)
    @Column(name = "provinz_local")
    private String provinzLocal;
    @Size(max = 5)
    @Column(name = "provinz_code")
    private String provinzCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nation_id")
    private int nationId;
    @Size(max = 5)
    @Column(name = "usgs_number")
    private String usgsNumber;

    public TblGeoProvince() {
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinz() {
        return provinz;
    }

    public void setProvinz(String provinz) {
        this.provinz = provinz;
    }

    public String getProvinzLocal() {
        return provinzLocal;
    }

    public void setProvinzLocal(String provinzLocal) {
        this.provinzLocal = provinzLocal;
    }

    public String getProvinzCode() {
        return provinzCode;
    }

    public void setProvinzCode(String provinzCode) {
        this.provinzCode = provinzCode;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public String getUsgsNumber() {
        return usgsNumber;
    }

    public void setUsgsNumber(String usgsNumber) {
        this.usgsNumber = usgsNumber;
    }

}
