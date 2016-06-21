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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tbl_identifier_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIdentifierType.findAll", query = "SELECT t FROM TblIdentifierType t"),
    @NamedQuery(name = "TblIdentifierType.findByIdentifierTypeId", query = "SELECT t FROM TblIdentifierType t WHERE t.identifierTypeId = :identifierTypeId"),
    @NamedQuery(name = "TblIdentifierType.findByType", query = "SELECT t FROM TblIdentifierType t WHERE t.type = :type"),
    @NamedQuery(name = "TblIdentifierType.findByTimestamp", query = "SELECT t FROM TblIdentifierType t WHERE t.timestamp = :timestamp")})
public class TblIdentifierType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier_type_id")
    private Integer identifierTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identifierTypeId")
    private Collection<TblIdentifier> tblIdentifierCollection;

    public TblIdentifierType() {
    }

    public TblIdentifierType(Integer identifierTypeId) {
        this.identifierTypeId = identifierTypeId;
    }

    public TblIdentifierType(Integer identifierTypeId, String type, Date timestamp) {
        this.identifierTypeId = identifierTypeId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Integer getIdentifierTypeId() {
        return identifierTypeId;
    }

    public void setIdentifierTypeId(Integer identifierTypeId) {
        this.identifierTypeId = identifierTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblIdentifier> getTblIdentifierCollection() {
        return tblIdentifierCollection;
    }

    public void setTblIdentifierCollection(Collection<TblIdentifier> tblIdentifierCollection) {
        this.tblIdentifierCollection = tblIdentifierCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identifierTypeId != null ? identifierTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIdentifierType)) {
            return false;
        }
        TblIdentifierType other = (TblIdentifierType) object;
        if ((this.identifierTypeId == null && other.identifierTypeId != null) || (this.identifierTypeId != null && !this.identifierTypeId.equals(other.identifierTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIdentifierType[ identifierTypeId=" + identifierTypeId + " ]";
    }

}
