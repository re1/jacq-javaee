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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblClassification.findAll", query = "SELECT t FROM TblClassification t")
    , @NamedQuery(name = "TblClassification.findByClassificationId", query = "SELECT t FROM TblClassification t WHERE t.classificationId = :classificationId")
    , @NamedQuery(name = "TblClassification.findByScientificNameId", query = "SELECT t FROM TblClassification t WHERE t.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "TblClassification.findByAccScientificNameId", query = "SELECT t FROM TblClassification t WHERE t.accScientificNameId = :accScientificNameId")
    , @NamedQuery(name = "TblClassification.findByRefDate", query = "SELECT t FROM TblClassification t WHERE t.refDate = :refDate")
    , @NamedQuery(name = "TblClassification.findByPreferredTaxonomy", query = "SELECT t FROM TblClassification t WHERE t.preferredTaxonomy = :preferredTaxonomy")
    , @NamedQuery(name = "TblClassification.findByLocked", query = "SELECT t FROM TblClassification t WHERE t.locked = :locked")
    , @NamedQuery(name = "TblClassification.findBySource", query = "SELECT t FROM TblClassification t WHERE t.source = :source")
    , @NamedQuery(name = "TblClassification.findBySourceId", query = "SELECT t FROM TblClassification t WHERE t.sourceId = :sourceId")
    , @NamedQuery(name = "TblClassification.findByUserId", query = "SELECT t FROM TblClassification t WHERE t.userId = :userId")
    , @NamedQuery(name = "TblClassification.findByTimestamp", query = "SELECT t FROM TblClassification t WHERE t.timestamp = :timestamp")
    , @NamedQuery(name = "TblClassification.findByParentScientificNameId", query = "SELECT t FROM TblClassification t WHERE t.parentScientificNameId = :parentScientificNameId")
    , @NamedQuery(name = "TblClassification.findByNumber", query = "SELECT t FROM TblClassification t WHERE t.number = :number")
    , @NamedQuery(name = "TblClassification.findByOrder", query = "SELECT t FROM TblClassification t WHERE t.order = :order")})
public class TblClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "classification_id")
    private long classificationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private long scientificNameId;
    @Column(name = "acc_scientific_name_id")
    private Long accScientificNameId;
    @Column(name = "ref_date")
    @Temporal(TemporalType.DATE)
    private Date refDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preferred_taxonomy")
    private short preferredTaxonomy;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "annotations")
    private String annotations;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private short locked;
    @Size(max = 20)
    @Column(name = "source")
    private String source;
    @Column(name = "source_id")
    private Long sourceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "parent_scientific_name_id")
    private Long parentScientificNameId;
    @Size(max = 15)
    @Column(name = "number")
    private String number;
    @Column(name = "order")
    private Long order;

    public TblClassification() {
    }

    public long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(long classificationId) {
        this.classificationId = classificationId;
    }

    public long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Long getAccScientificNameId() {
        return accScientificNameId;
    }

    public void setAccScientificNameId(Long accScientificNameId) {
        this.accScientificNameId = accScientificNameId;
    }

    public Date getRefDate() {
        return refDate;
    }

    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }

    public short getPreferredTaxonomy() {
        return preferredTaxonomy;
    }

    public void setPreferredTaxonomy(short preferredTaxonomy) {
        this.preferredTaxonomy = preferredTaxonomy;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public short getLocked() {
        return locked;
    }

    public void setLocked(short locked) {
        this.locked = locked;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getParentScientificNameId() {
        return parentScientificNameId;
    }

    public void setParentScientificNameId(Long parentScientificNameId) {
        this.parentScientificNameId = parentScientificNameId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    /**
     * Custom mappings
     */
    @JoinColumn(name = "scientific_name_id", referencedColumnName = "scientific_name_id", insertable = false, updatable = false)
    @ManyToOne
    private ViewScientificName viewScientificName;

    public ViewScientificName getViewScientificName() {
        return viewScientificName;
    }

    @JoinColumn(name = "source_id", referencedColumnName = "citation_id", insertable = false, updatable = false)
    @ManyToOne
    private ViewProtolog viewProtolog;

    public ViewProtolog getViewProtolog() {
        return viewProtolog;
    }
}
