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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_ident_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIdentStatus.findAll", query = "SELECT t FROM TblIdentStatus t"),
    @NamedQuery(name = "TblIdentStatus.findByIdentStatusId", query = "SELECT t FROM TblIdentStatus t WHERE t.identStatusId = :identStatusId"),
    @NamedQuery(name = "TblIdentStatus.findByStatus", query = "SELECT t FROM TblIdentStatus t WHERE t.status = :status")})
public class TblIdentStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident_status_id")
    private Integer identStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "identStatusId")
    private Collection<TblBotanicalObject> tblBotanicalObjectCollection;

    public TblIdentStatus() {
    }

    public TblIdentStatus(Integer identStatusId) {
        this.identStatusId = identStatusId;
    }

    public TblIdentStatus(Integer identStatusId, String status) {
        this.identStatusId = identStatusId;
        this.status = status;
    }

    public Integer getIdentStatusId() {
        return identStatusId;
    }

    public void setIdentStatusId(Integer identStatusId) {
        this.identStatusId = identStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<TblBotanicalObject> getTblBotanicalObjectCollection() {
        return tblBotanicalObjectCollection;
    }

    public void setTblBotanicalObjectCollection(Collection<TblBotanicalObject> tblBotanicalObjectCollection) {
        this.tblBotanicalObjectCollection = tblBotanicalObjectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identStatusId != null ? identStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIdentStatus)) {
            return false;
        }
        TblIdentStatus other = (TblIdentStatus) object;
        if ((this.identStatusId == null && other.identStatusId != null) || (this.identStatusId != null && !this.identStatusId.equals(other.identStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIdentStatus[ identStatusId=" + identStatusId + " ]";
    }

}
