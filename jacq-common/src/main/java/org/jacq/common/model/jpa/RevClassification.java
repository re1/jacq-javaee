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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
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
@Table(name = "rev_classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RevClassification.findAll", query = "SELECT r FROM RevClassification r")
    , @NamedQuery(name = "RevClassification.findByClassificationBrowserRevisionId", query = "SELECT r FROM RevClassification r WHERE r.classificationBrowserRevisionId = :classificationBrowserRevisionId")
    , @NamedQuery(name = "RevClassification.findByClassificationId", query = "SELECT r FROM RevClassification r WHERE r.classificationId = :classificationId")
    , @NamedQuery(name = "RevClassification.findByScientificNameId", query = "SELECT r FROM RevClassification r WHERE r.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "RevClassification.findByAccScientificNameId", query = "SELECT r FROM RevClassification r WHERE r.accScientificNameId = :accScientificNameId")
    , @NamedQuery(name = "RevClassification.findByPreferredTaxonomy", query = "SELECT r FROM RevClassification r WHERE r.preferredTaxonomy = :preferredTaxonomy")
    , @NamedQuery(name = "RevClassification.findByLocked", query = "SELECT r FROM RevClassification r WHERE r.locked = :locked")
    , @NamedQuery(name = "RevClassification.findBySource", query = "SELECT r FROM RevClassification r WHERE r.source = :source")
    , @NamedQuery(name = "RevClassification.findBySourceId", query = "SELECT r FROM RevClassification r WHERE r.sourceId = :sourceId")
    , @NamedQuery(name = "RevClassification.findByUserId", query = "SELECT r FROM RevClassification r WHERE r.userId = :userId")
    , @NamedQuery(name = "RevClassification.findByParentScientificNameId", query = "SELECT r FROM RevClassification r WHERE r.parentScientificNameId = :parentScientificNameId")
    , @NamedQuery(name = "RevClassification.findByNumber", query = "SELECT r FROM RevClassification r WHERE r.number = :number")
    , @NamedQuery(name = "RevClassification.findByOrder", query = "SELECT r FROM RevClassification r WHERE r.order = :order")
    , @NamedQuery(name = "RevClassification.findByUuidMinterIdAndParent", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId = :accScientificNameId ORDER BY r.order ASC, r.scientificName ASC")
    , @NamedQuery(name = "RevClassification.findByUuidMinterIdAndTopLevel", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId IS NULL ORDER BY r.order ASC, r.scientificName ASC")
    , @NamedQuery(name = "RevClassification.findByUuidMinterIdAndTopLevelAndProvinceId", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId IS NULL AND r.provinceIds LIKE :provinceId ORDER BY r.order ASC, r.scientificName ASC")
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "RevClassification.addRevision", procedureName = "AddRevClassification",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "i_source")
                ,
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "i_source_id")
                ,
		@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "o_uuid")
            })
})
public class RevClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "classification_browser_revision_id")
    private Long classificationBrowserRevisionId;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "source")
    private String source;
    @Column(name = "source_id")
    private Long sourceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Column(name = "parent_scientific_name_id")
    private Long parentScientificNameId;
    @Size(max = 15)
    @Column(name = "number")
    private String number;
    @Column(name = "order")
    private Long order;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name")
    private String scientificName;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name_no_author")
    private String scientificNameNoAuthor;
    @Lob
    @Size(max = 65535)
    @Column(name = "province_ids")
    private String provinceIds;
    @Lob
    @Size(max = 65535)
    @Column(name = "province_codes")
    private String provinceCodes;
    @Column(name = "uuid_minter_id")
    private Long uuidMinterId;

    public RevClassification() {
    }

    public RevClassification(Long classificationBrowserRevisionId) {
        this.classificationBrowserRevisionId = classificationBrowserRevisionId;
    }

    public RevClassification(Long classificationBrowserRevisionId, long classificationId, long scientificNameId, short preferredTaxonomy, short locked, String source, long userId, Date timestamp) {
        this.classificationBrowserRevisionId = classificationBrowserRevisionId;
        this.classificationId = classificationId;
        this.scientificNameId = scientificNameId;
        this.preferredTaxonomy = preferredTaxonomy;
        this.locked = locked;
        this.source = source;
        this.userId = userId;
    }

    public Long getClassificationBrowserRevisionId() {
        return classificationBrowserRevisionId;
    }

    public void setClassificationBrowserRevisionId(Long classificationBrowserRevisionId) {
        this.classificationBrowserRevisionId = classificationBrowserRevisionId;
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

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getScientificNameNoAuthor() {
        return scientificNameNoAuthor;
    }

    public void setScientificNameNoAuthor(String scientificNameNoAuthor) {
        this.scientificNameNoAuthor = scientificNameNoAuthor;
    }

    public String getProvinceIds() {
        return provinceIds;
    }

    public void setProvinceIds(String provinceIds) {
        this.provinceIds = provinceIds;
    }

    public String getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public Long getUuidMinterId() {
        return uuidMinterId;
    }

    public void setUuidMinterId(Long uuidMinterId) {
        this.uuidMinterId = uuidMinterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classificationBrowserRevisionId != null ? classificationBrowserRevisionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RevClassification)) {
            return false;
        }
        RevClassification other = (RevClassification) object;
        if ((this.classificationBrowserRevisionId == null && other.classificationBrowserRevisionId != null) || (this.classificationBrowserRevisionId != null && !this.classificationBrowserRevisionId.equals(other.classificationBrowserRevisionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.RevClassification[ classificationBrowserRevisionId=" + classificationBrowserRevisionId + " ]";
    }
}
