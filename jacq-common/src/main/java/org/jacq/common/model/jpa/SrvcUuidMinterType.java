/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "srvc_uuid_minter_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrvcUuidMinterType.findAll", query = "SELECT s FROM SrvcUuidMinterType s"),
    @NamedQuery(name = "SrvcUuidMinterType.findByUuidMinterTypeId", query = "SELECT s FROM SrvcUuidMinterType s WHERE s.uuidMinterTypeId = :uuidMinterTypeId"),
    @NamedQuery(name = "SrvcUuidMinterType.findByDescription", query = "SELECT s FROM SrvcUuidMinterType s WHERE s.description = :description"),
    @NamedQuery(name = "SrvcUuidMinterType.findByTimestamp", query = "SELECT s FROM SrvcUuidMinterType s WHERE s.timestamp = :timestamp")})
public class SrvcUuidMinterType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "uuid_minter_type_id")
    private Integer uuidMinterTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uuidMinterTypeId")
    private Collection<SrvcUuidMinter> srvcUuidMinterCollection;

    public SrvcUuidMinterType() {
    }

    public SrvcUuidMinterType(Integer uuidMinterTypeId) {
        this.uuidMinterTypeId = uuidMinterTypeId;
    }

    public SrvcUuidMinterType(Integer uuidMinterTypeId, String description, Date timestamp) {
        this.uuidMinterTypeId = uuidMinterTypeId;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Integer getUuidMinterTypeId() {
        return uuidMinterTypeId;
    }

    public void setUuidMinterTypeId(Integer uuidMinterTypeId) {
        this.uuidMinterTypeId = uuidMinterTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<SrvcUuidMinter> getSrvcUuidMinterCollection() {
        return srvcUuidMinterCollection;
    }

    public void setSrvcUuidMinterCollection(Collection<SrvcUuidMinter> srvcUuidMinterCollection) {
        this.srvcUuidMinterCollection = srvcUuidMinterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuidMinterTypeId != null ? uuidMinterTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrvcUuidMinterType)) {
            return false;
        }
        SrvcUuidMinterType other = (SrvcUuidMinterType) object;
        if ((this.uuidMinterTypeId == null && other.uuidMinterTypeId != null) || (this.uuidMinterTypeId != null && !this.uuidMinterTypeId.equals(other.uuidMinterTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.SrvcUuidMinterType[ uuidMinterTypeId=" + uuidMinterTypeId + " ]";
    }

}
