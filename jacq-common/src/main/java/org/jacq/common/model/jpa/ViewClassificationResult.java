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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.ClassificationSourceType;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "view_classification_result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewClassificationResult.findAll", query = "SELECT v FROM ViewClassificationResult v")
    , @NamedQuery(name = "ViewClassificationResult.findByClassificationId", query = "SELECT v FROM ViewClassificationResult v WHERE v.classificationId = :classificationId")
    , @NamedQuery(name = "ViewClassificationResult.findByScientificNameId", query = "SELECT v FROM ViewClassificationResult v WHERE v.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "ViewClassificationResult.findByAccScientificNameId", query = "SELECT v FROM ViewClassificationResult v WHERE v.accScientificNameId = :accScientificNameId")
    , @NamedQuery(name = "ViewClassificationResult.findByPreferredTaxonomy", query = "SELECT v FROM ViewClassificationResult v WHERE v.preferredTaxonomy = :preferredTaxonomy")
    , @NamedQuery(name = "ViewClassificationResult.findByLocked", query = "SELECT v FROM ViewClassificationResult v WHERE v.locked = :locked")
    , @NamedQuery(name = "ViewClassificationResult.findBySource", query = "SELECT v FROM ViewClassificationResult v WHERE v.source = :source")
    , @NamedQuery(name = "ViewClassificationResult.findBySourceId", query = "SELECT v FROM ViewClassificationResult v WHERE v.sourceId = :sourceId")
    , @NamedQuery(name = "ViewClassificationResult.findByUserId", query = "SELECT v FROM ViewClassificationResult v WHERE v.userId = :userId")
    , @NamedQuery(name = "ViewClassificationResult.findByParentScientificNameId", query = "SELECT v FROM ViewClassificationResult v WHERE v.parentScientificNameId = :parentScientificNameId")
    , @NamedQuery(name = "ViewClassificationResult.findByNumber", query = "SELECT v FROM ViewClassificationResult v WHERE v.number = :number")
    , @NamedQuery(name = "ViewClassificationResult.findByOrder", query = "SELECT v FROM ViewClassificationResult v WHERE v.order = :order")
    , @NamedQuery(name = "ViewClassificationResult.findTopLevelBySource", query = "SELECT v FROM ViewClassificationResult v WHERE v.source = :source AND v.sourceId = :sourceId AND v.parentScientificNameId IS NULL")
    , @NamedQuery(name = "ViewClassificationResult.findBySourceAndParent", query = "SELECT v FROM ViewClassificationResult v WHERE v.source = :source AND v.sourceId = :sourceId AND v.parentScientificNameId = :parentScientificNameId")
})
public class ViewClassificationResult implements Serializable {

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

    public ViewClassificationResult() {
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

}
