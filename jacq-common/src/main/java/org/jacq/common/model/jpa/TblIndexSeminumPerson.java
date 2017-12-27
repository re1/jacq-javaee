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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tbl_index_seminum_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndexSeminumPerson.findAll", query = "SELECT t FROM TblIndexSeminumPerson t")
    , @NamedQuery(name = "TblIndexSeminumPerson.findByIndexSeminumPersonId", query = "SELECT t FROM TblIndexSeminumPerson t WHERE t.indexSeminumPersonId = :indexSeminumPersonId")
    , @NamedQuery(name = "TblIndexSeminumPerson.findByName", query = "SELECT t FROM TblIndexSeminumPerson t WHERE t.name = :name")
    , @NamedQuery(name = "TblIndexSeminumPerson.findByTimestamp", query = "SELECT t FROM TblIndexSeminumPerson t WHERE t.timestamp = :timestamp")})
public class TblIndexSeminumPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_seminum_person_id")
    private Long indexSeminumPersonId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "index_seminum_content_id", referencedColumnName = "index_seminum_content_id")
    @ManyToOne(optional = false)
    private TblIndexSeminumContent indexSeminumContentId;

    public TblIndexSeminumPerson() {
    }

    public TblIndexSeminumPerson(Long indexSeminumPersonId) {
        this.indexSeminumPersonId = indexSeminumPersonId;
    }

    public TblIndexSeminumPerson(Long indexSeminumPersonId, String name) {
        this.indexSeminumPersonId = indexSeminumPersonId;
        this.name = name;
    }

    public Long getIndexSeminumPersonId() {
        return indexSeminumPersonId;
    }

    public void setIndexSeminumPersonId(Long indexSeminumPersonId) {
        this.indexSeminumPersonId = indexSeminumPersonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public TblIndexSeminumContent getIndexSeminumContentId() {
        return indexSeminumContentId;
    }

    public void setIndexSeminumContentId(TblIndexSeminumContent indexSeminumContentId) {
        this.indexSeminumContentId = indexSeminumContentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indexSeminumPersonId != null ? indexSeminumPersonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndexSeminumPerson)) {
            return false;
        }
        TblIndexSeminumPerson other = (TblIndexSeminumPerson) object;
        if ((this.indexSeminumPersonId == null && other.indexSeminumPersonId != null) || (this.indexSeminumPersonId != null && !this.indexSeminumPersonId.equals(other.indexSeminumPersonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIndexSeminumPerson[ indexSeminumPersonId=" + indexSeminumPersonId + " ]";
    }

}
