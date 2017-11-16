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
import javax.persistence.Id;
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
@Table(name = "tbl_geo_nation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblGeoNation.findAll", query = "SELECT t FROM TblGeoNation t")
    , @NamedQuery(name = "TblGeoNation.findByNationId", query = "SELECT t FROM TblGeoNation t WHERE t.nationId = :nationId")
    , @NamedQuery(name = "TblGeoNation.findByNation", query = "SELECT t FROM TblGeoNation t WHERE t.nation = :nation")
    , @NamedQuery(name = "TblGeoNation.findByFnnumber", query = "SELECT t FROM TblGeoNation t WHERE t.fnnumber = :fnnumber")
    , @NamedQuery(name = "TblGeoNation.findByNationEngl", query = "SELECT t FROM TblGeoNation t WHERE t.nationEngl = :nationEngl")
    , @NamedQuery(name = "TblGeoNation.findByNationDeutsch", query = "SELECT t FROM TblGeoNation t WHERE t.nationDeutsch = :nationDeutsch")
    , @NamedQuery(name = "TblGeoNation.findByAnnotation", query = "SELECT t FROM TblGeoNation t WHERE t.annotation = :annotation")
    , @NamedQuery(name = "TblGeoNation.findByNationCode", query = "SELECT t FROM TblGeoNation t WHERE t.nationCode = :nationCode")
    , @NamedQuery(name = "TblGeoNation.findByUsgsCode", query = "SELECT t FROM TblGeoNation t WHERE t.usgsCode = :usgsCode")
    , @NamedQuery(name = "TblGeoNation.findByIsoAlpha3Code", query = "SELECT t FROM TblGeoNation t WHERE t.isoAlpha3Code = :isoAlpha3Code")
    , @NamedQuery(name = "TblGeoNation.findByIsoAlpha2Code", query = "SELECT t FROM TblGeoNation t WHERE t.isoAlpha2Code = :isoAlpha2Code")
    , @NamedQuery(name = "TblGeoNation.findByRegionId", query = "SELECT t FROM TblGeoNation t WHERE t.regionId = :regionId")
    , @NamedQuery(name = "TblGeoNation.findByLanguageVariants", query = "SELECT t FROM TblGeoNation t WHERE t.languageVariants = :languageVariants")})
public class TblGeoNation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nation_id")
    private int nationId;
    @Size(max = 50)
    @Column(name = "nation")
    private String nation;
    @Column(name = "fnnumber")
    private Long fnnumber;
    @Size(max = 50)
    @Column(name = "nation_engl")
    private String nationEngl;
    @Size(max = 50)
    @Column(name = "nation_deutsch")
    private String nationDeutsch;
    @Size(max = 250)
    @Column(name = "annotation")
    private String annotation;
    @Size(max = 10)
    @Column(name = "nation_code")
    private String nationCode;
    @Size(max = 5)
    @Column(name = "usgs_code")
    private String usgsCode;
    @Size(max = 3)
    @Column(name = "iso_alpha_3_code")
    private String isoAlpha3Code;
    @Size(max = 2)
    @Column(name = "iso_alpha_2_code")
    private String isoAlpha2Code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "region_id")
    private int regionId;
    @Size(max = 2000)
    @Column(name = "language_variants")
    private String languageVariants;

    public TblGeoNation() {
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Long getFnnumber() {
        return fnnumber;
    }

    public void setFnnumber(Long fnnumber) {
        this.fnnumber = fnnumber;
    }

    public String getNationEngl() {
        return nationEngl;
    }

    public void setNationEngl(String nationEngl) {
        this.nationEngl = nationEngl;
    }

    public String getNationDeutsch() {
        return nationDeutsch;
    }

    public void setNationDeutsch(String nationDeutsch) {
        this.nationDeutsch = nationDeutsch;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getUsgsCode() {
        return usgsCode;
    }

    public void setUsgsCode(String usgsCode) {
        this.usgsCode = usgsCode;
    }

    public String getIsoAlpha3Code() {
        return isoAlpha3Code;
    }

    public void setIsoAlpha3Code(String isoAlpha3Code) {
        this.isoAlpha3Code = isoAlpha3Code;
    }

    public String getIsoAlpha2Code() {
        return isoAlpha2Code;
    }

    public void setIsoAlpha2Code(String isoAlpha2Code) {
        this.isoAlpha2Code = isoAlpha2Code;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getLanguageVariants() {
        return languageVariants;
    }

    public void setLanguageVariants(String languageVariants) {
        this.languageVariants = languageVariants;
    }

}
