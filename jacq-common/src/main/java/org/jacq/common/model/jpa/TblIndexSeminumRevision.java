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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_index_seminum_revision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndexSeminumRevision.findAll", query = "SELECT t FROM TblIndexSeminumRevision t")
    , @NamedQuery(name = "TblIndexSeminumRevision.findByIndexSeminumRevisionId", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.indexSeminumRevisionId = :indexSeminumRevisionId")
    , @NamedQuery(name = "TblIndexSeminumRevision.findByName", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.name = :name")
    , @NamedQuery(name = "TblIndexSeminumRevision.findByTimestamp", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.timestamp = :timestamp")})
public class TblIndexSeminumRevision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_seminum_revision_id")
    private Long indexSeminumRevisionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indexSeminumRevisionId", fetch = FetchType.LAZY)
    private Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FrmwrkUser userId;

    public TblIndexSeminumRevision() {
    }

    public TblIndexSeminumRevision(Long indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

    public TblIndexSeminumRevision(Long indexSeminumRevisionId, String name, Date timestamp) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Long getIndexSeminumRevisionId() {
        return indexSeminumRevisionId;
    }

    public void setIndexSeminumRevisionId(Long indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
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

    @XmlTransient
    public Collection<TblIndexSeminumContent> getTblIndexSeminumContentCollection() {
        return tblIndexSeminumContentCollection;
    }

    public void setTblIndexSeminumContentCollection(Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection) {
        this.tblIndexSeminumContentCollection = tblIndexSeminumContentCollection;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indexSeminumRevisionId != null ? indexSeminumRevisionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndexSeminumRevision)) {
            return false;
        }
        TblIndexSeminumRevision other = (TblIndexSeminumRevision) object;
        if ((this.indexSeminumRevisionId == null && other.indexSeminumRevisionId != null) || (this.indexSeminumRevisionId != null && !this.indexSeminumRevisionId.equals(other.indexSeminumRevisionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIndexSeminumRevision[ indexSeminumRevisionId=" + indexSeminumRevisionId + " ]";
    }

}
