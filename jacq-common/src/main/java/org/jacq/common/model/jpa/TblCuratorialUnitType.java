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
@Table(name = "tbl_curatorial_unit_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCuratorialUnitType.findAll", query = "SELECT t FROM TblCuratorialUnitType t"),
    @NamedQuery(name = "TblCuratorialUnitType.findByCuratorialUnitTypeId", query = "SELECT t FROM TblCuratorialUnitType t WHERE t.curatorialUnitTypeId = :curatorialUnitTypeId"),
    @NamedQuery(name = "TblCuratorialUnitType.findByTypeName", query = "SELECT t FROM TblCuratorialUnitType t WHERE t.typeName = :typeName"),
    @NamedQuery(name = "TblCuratorialUnitType.findByTimestamp", query = "SELECT t FROM TblCuratorialUnitType t WHERE t.timestamp = :timestamp")})
public class TblCuratorialUnitType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "curatorial_unit_type_id")
    private Integer curatorialUnitTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type_name")
    private String typeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curatorialUnitTypeId")
    private Collection<TblCuratorialUnit> tblCuratorialUnitCollection;

    public TblCuratorialUnitType() {
    }

    public TblCuratorialUnitType(Integer curatorialUnitTypeId) {
        this.curatorialUnitTypeId = curatorialUnitTypeId;
    }

    public TblCuratorialUnitType(Integer curatorialUnitTypeId, String typeName, Date timestamp) {
        this.curatorialUnitTypeId = curatorialUnitTypeId;
        this.typeName = typeName;
        this.timestamp = timestamp;
    }

    public Integer getCuratorialUnitTypeId() {
        return curatorialUnitTypeId;
    }

    public void setCuratorialUnitTypeId(Integer curatorialUnitTypeId) {
        this.curatorialUnitTypeId = curatorialUnitTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblCuratorialUnit> getTblCuratorialUnitCollection() {
        return tblCuratorialUnitCollection;
    }

    public void setTblCuratorialUnitCollection(Collection<TblCuratorialUnit> tblCuratorialUnitCollection) {
        this.tblCuratorialUnitCollection = tblCuratorialUnitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curatorialUnitTypeId != null ? curatorialUnitTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCuratorialUnitType)) {
            return false;
        }
        TblCuratorialUnitType other = (TblCuratorialUnitType) object;
        if ((this.curatorialUnitTypeId == null && other.curatorialUnitTypeId != null) || (this.curatorialUnitTypeId != null && !this.curatorialUnitTypeId.equals(other.curatorialUnitTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblCuratorialUnitType[ curatorialUnitTypeId=" + curatorialUnitTypeId + " ]";
    }

}
