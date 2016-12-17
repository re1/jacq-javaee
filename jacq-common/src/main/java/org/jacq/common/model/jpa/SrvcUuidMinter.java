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
@Table(name = "srvc_uuid_minter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrvcUuidMinter.findAll", query = "SELECT s FROM SrvcUuidMinter s"),
    @NamedQuery(name = "SrvcUuidMinter.findByUuidMinterId", query = "SELECT s FROM SrvcUuidMinter s WHERE s.uuidMinterId = :uuidMinterId"),
    @NamedQuery(name = "SrvcUuidMinter.findByInternalId", query = "SELECT s FROM SrvcUuidMinter s WHERE s.internalId = :internalId"),
    @NamedQuery(name = "SrvcUuidMinter.findByUuid", query = "SELECT s FROM SrvcUuidMinter s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "SrvcUuidMinter.findByTimestamp", query = "SELECT s FROM SrvcUuidMinter s WHERE s.timestamp = :timestamp")})
public class SrvcUuidMinter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uuid_minter_id")
    private Integer uuidMinterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "internal_id")
    private int internalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "uuid_minter_type_id", referencedColumnName = "uuid_minter_type_id")
    @ManyToOne(optional = false)
    private SrvcUuidMinterType uuidMinterTypeId;

    public SrvcUuidMinter() {
    }

    public SrvcUuidMinter(Integer uuidMinterId) {
        this.uuidMinterId = uuidMinterId;
    }

    public SrvcUuidMinter(Integer uuidMinterId, int internalId, String uuid, Date timestamp) {
        this.uuidMinterId = uuidMinterId;
        this.internalId = internalId;
        this.uuid = uuid;
        this.timestamp = timestamp;
    }

    public Integer getUuidMinterId() {
        return uuidMinterId;
    }

    public void setUuidMinterId(Integer uuidMinterId) {
        this.uuidMinterId = uuidMinterId;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public SrvcUuidMinterType getUuidMinterTypeId() {
        return uuidMinterTypeId;
    }

    public void setUuidMinterTypeId(SrvcUuidMinterType uuidMinterTypeId) {
        this.uuidMinterTypeId = uuidMinterTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuidMinterId != null ? uuidMinterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrvcUuidMinter)) {
            return false;
        }
        SrvcUuidMinter other = (SrvcUuidMinter) object;
        if ((this.uuidMinterId == null && other.uuidMinterId != null) || (this.uuidMinterId != null && !this.uuidMinterId.equals(other.uuidMinterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.SrvcUuidMinter[ uuidMinterId=" + uuidMinterId + " ]";
    }

}
