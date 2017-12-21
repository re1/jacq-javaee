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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/*
* Custom imports
*/
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_classification_chorol_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblClassificationChorolStatus.findAll", query = "SELECT t FROM TblClassificationChorolStatus t")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByClassificationChorolStatusId", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.classificationChorolStatusId = :classificationChorolStatusId")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByScientificNameId", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "TblClassificationChorolStatus.findBySource", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.source = :source")
    , @NamedQuery(name = "TblClassificationChorolStatus.findBySourceId", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.sourceId = :sourceId")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByChorolStatus", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.chorolStatus = :chorolStatus")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByStatusDebatable", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.statusDebatable = :statusDebatable")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByNationId", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.nationId = :nationId")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByProvinceId", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.provinceId = :provinceId")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByProvinceDebatable", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.provinceDebatable = :provinceDebatable")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByDateLastEdited", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.dateLastEdited = :dateLastEdited")
    , @NamedQuery(name = "TblClassificationChorolStatus.findByLocked", query = "SELECT t FROM TblClassificationChorolStatus t WHERE t.locked = :locked")})
public class TblClassificationChorolStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "classification_chorol_status_id")
    private long classificationChorolStatusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private long scientificNameId;
    @Size(max = 8)
    @Column(name = "source")
    private String source;
    @Column(name = "source_id")
    private Long sourceId;
    @Size(max = 255)
    @Column(name = "chorol_status")
    private String chorolStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_debatable")
    private short statusDebatable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nation_id")
    private long nationId;
    @Column(name = "province_id")
    private Long provinceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "province_debatable")
    private short provinceDebatable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_last_edited")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastEdited;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private long locked;

    public TblClassificationChorolStatus() {
    }

    public long getClassificationChorolStatusId() {
        return classificationChorolStatusId;
    }

    public void setClassificationChorolStatusId(long classificationChorolStatusId) {
        this.classificationChorolStatusId = classificationChorolStatusId;
    }

    public long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getChorolStatus() {
        return chorolStatus;
    }

    public void setChorolStatus(String chorolStatus) {
        this.chorolStatus = chorolStatus;
    }

    public short getStatusDebatable() {
        return statusDebatable;
    }

    public void setStatusDebatable(short statusDebatable) {
        this.statusDebatable = statusDebatable;
    }

    public long getNationId() {
        return nationId;
    }

    public void setNationId(long nationId) {
        this.nationId = nationId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public short getProvinceDebatable() {
        return provinceDebatable;
    }

    public void setProvinceDebatable(short provinceDebatable) {
        this.provinceDebatable = provinceDebatable;
    }

    public Date getDateLastEdited() {
        return dateLastEdited;
    }

    public void setDateLastEdited(Date dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }

    public long getLocked() {
        return locked;
    }

    public void setLocked(long locked) {
        this.locked = locked;
    }

    /**
     * Custom Mappings
     */
    @ManyToOne
    @JoinColumn(name = "nation_id", updatable = false, insertable = false)
    private TblGeoNation tblGeoNation;

    public TblGeoNation getTblGeoNation() {
        return tblGeoNation;
    }

    public void setTblGeoNation(TblGeoNation tblGeoNation) {
        this.tblGeoNation = tblGeoNation;
    }

    @ManyToOne
    @JoinColumn(name = "province_id", updatable = false, insertable = false)
    private TblGeoProvince tblGeoProvince;

    public TblGeoProvince getTblGeoProvince() {
        return tblGeoProvince;
    }

    public void setTblGeoProvince(TblGeoProvince tblGeoProvince) {
        this.tblGeoProvince = tblGeoProvince;
    }

}
