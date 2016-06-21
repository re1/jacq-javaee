/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "tbl_index_seminum_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndexSeminumType.findAll", query = "SELECT t FROM TblIndexSeminumType t"),
    @NamedQuery(name = "TblIndexSeminumType.findById", query = "SELECT t FROM TblIndexSeminumType t WHERE t.id = :id"),
    @NamedQuery(name = "TblIndexSeminumType.findByType", query = "SELECT t FROM TblIndexSeminumType t WHERE t.type = :type")})
public class TblIndexSeminumType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "indexSeminumTypeId")
    private Collection<TblLivingPlant> tblLivingPlantCollection;

    public TblIndexSeminumType() {
    }

    public TblIndexSeminumType(Integer id) {
        this.id = id;
    }

    public TblIndexSeminumType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<TblLivingPlant> getTblLivingPlantCollection() {
        return tblLivingPlantCollection;
    }

    public void setTblLivingPlantCollection(Collection<TblLivingPlant> tblLivingPlantCollection) {
        this.tblLivingPlantCollection = tblLivingPlantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndexSeminumType)) {
            return false;
        }
        TblIndexSeminumType other = (TblIndexSeminumType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIndexSeminumType[ id=" + id + " ]";
    }

}
