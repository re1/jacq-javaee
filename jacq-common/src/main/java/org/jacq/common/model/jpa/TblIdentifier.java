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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tbl_identifier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIdentifier.findAll", query = "SELECT t FROM TblIdentifier t"),
    @NamedQuery(name = "TblIdentifier.findByIdentifierId", query = "SELECT t FROM TblIdentifier t WHERE t.identifierId = :identifierId"),
    @NamedQuery(name = "TblIdentifier.findByIdentifier", query = "SELECT t FROM TblIdentifier t WHERE t.identifier = :identifier"),
    @NamedQuery(name = "TblIdentifier.findByTimestamp", query = "SELECT t FROM TblIdentifier t WHERE t.timestamp = :timestamp")})
public class TblIdentifier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier_id")
    private Integer identifierId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "identifier")
    private String identifier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinTable(name = "tbl_specimen_identifier", joinColumns = {
        @JoinColumn(name = "identifier_id", referencedColumnName = "identifier_id")}, inverseJoinColumns = {
        @JoinColumn(name = "specimen_id", referencedColumnName = "specimen_id")})
    @ManyToMany
    private Collection<TblSpecimen> tblSpecimenCollection;
    @ManyToMany(mappedBy = "tblIdentifierCollection")
    private Collection<TblCuratorialUnit> tblCuratorialUnitCollection;
    @JoinColumn(name = "identifier_type_id", referencedColumnName = "identifier_type_id")
    @ManyToOne(optional = false)
    private TblIdentifierType identifierTypeId;

    public TblIdentifier() {
    }

    public TblIdentifier(Integer identifierId) {
        this.identifierId = identifierId;
    }

    public TblIdentifier(Integer identifierId, String identifier, Date timestamp) {
        this.identifierId = identifierId;
        this.identifier = identifier;
        this.timestamp = timestamp;
    }

    public Integer getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(Integer identifierId) {
        this.identifierId = identifierId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblSpecimen> getTblSpecimenCollection() {
        return tblSpecimenCollection;
    }

    public void setTblSpecimenCollection(Collection<TblSpecimen> tblSpecimenCollection) {
        this.tblSpecimenCollection = tblSpecimenCollection;
    }

    @XmlTransient
    public Collection<TblCuratorialUnit> getTblCuratorialUnitCollection() {
        return tblCuratorialUnitCollection;
    }

    public void setTblCuratorialUnitCollection(Collection<TblCuratorialUnit> tblCuratorialUnitCollection) {
        this.tblCuratorialUnitCollection = tblCuratorialUnitCollection;
    }

    public TblIdentifierType getIdentifierTypeId() {
        return identifierTypeId;
    }

    public void setIdentifierTypeId(TblIdentifierType identifierTypeId) {
        this.identifierTypeId = identifierTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identifierId != null ? identifierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIdentifier)) {
            return false;
        }
        TblIdentifier other = (TblIdentifier) object;
        if ((this.identifierId == null && other.identifierId != null) || (this.identifierId != null && !this.identifierId.equals(other.identifierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIdentifier[ identifierId=" + identifierId + " ]";
    }

}
