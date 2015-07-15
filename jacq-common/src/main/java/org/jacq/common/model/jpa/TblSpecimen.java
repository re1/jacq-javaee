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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_specimen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSpecimen.findAll", query = "SELECT t FROM TblSpecimen t"),
    @NamedQuery(name = "TblSpecimen.findBySpecimenId", query = "SELECT t FROM TblSpecimen t WHERE t.specimenId = :specimenId"),
    @NamedQuery(name = "TblSpecimen.findByTimestamp", query = "SELECT t FROM TblSpecimen t WHERE t.timestamp = :timestamp")})
public class TblSpecimen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "specimen_id")
    private Integer specimenId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @ManyToMany(mappedBy = "tblSpecimenCollection")
    private Collection<TblIdentifier> tblIdentifierCollection;
    @JoinColumn(name = "curatorial_unit_id", referencedColumnName = "curatorial_unit_id")
    @ManyToOne(optional = false)
    private TblCuratorialUnit curatorialUnitId;
    @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblBotanicalObject botanicalObjectId;

    public TblSpecimen() {
    }

    public TblSpecimen(Integer specimenId) {
        this.specimenId = specimenId;
    }

    public TblSpecimen(Integer specimenId, Date timestamp) {
        this.specimenId = specimenId;
        this.timestamp = timestamp;
    }

    public Integer getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(Integer specimenId) {
        this.specimenId = specimenId;
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

    public TblCuratorialUnit getCuratorialUnitId() {
        return curatorialUnitId;
    }

    public void setCuratorialUnitId(TblCuratorialUnit curatorialUnitId) {
        this.curatorialUnitId = curatorialUnitId;
    }

    public TblBotanicalObject getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(TblBotanicalObject botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specimenId != null ? specimenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSpecimen)) {
            return false;
        }
        TblSpecimen other = (TblSpecimen) object;
        if ((this.specimenId == null && other.specimenId != null) || (this.specimenId != null && !this.specimenId.equals(other.specimenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSpecimen[ specimenId=" + specimenId + " ]";
    }

}
