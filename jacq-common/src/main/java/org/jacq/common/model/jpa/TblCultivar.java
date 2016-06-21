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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_cultivar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCultivar.findAll", query = "SELECT t FROM TblCultivar t"),
    @NamedQuery(name = "TblCultivar.findByCultivarId", query = "SELECT t FROM TblCultivar t WHERE t.cultivarId = :cultivarId"),
    @NamedQuery(name = "TblCultivar.findByCultivar", query = "SELECT t FROM TblCultivar t WHERE t.cultivar = :cultivar")})
public class TblCultivar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cultivar_id")
    private Integer cultivarId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cultivar")
    private String cultivar;
    @JoinColumn(name = "scientific_name_id", referencedColumnName = "scientific_name_id")
    @ManyToOne(optional = false)
    private TblScientificNameInformation scientificNameId;
    @OneToMany(mappedBy = "cultivarId")
    private Collection<TblLivingPlant> tblLivingPlantCollection;

    public TblCultivar() {
    }

    public TblCultivar(Integer cultivarId) {
        this.cultivarId = cultivarId;
    }

    public TblCultivar(Integer cultivarId, String cultivar) {
        this.cultivarId = cultivarId;
        this.cultivar = cultivar;
    }

    public Integer getCultivarId() {
        return cultivarId;
    }

    public void setCultivarId(Integer cultivarId) {
        this.cultivarId = cultivarId;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public TblScientificNameInformation getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(TblScientificNameInformation scientificNameId) {
        this.scientificNameId = scientificNameId;
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
        hash += (cultivarId != null ? cultivarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCultivar)) {
            return false;
        }
        TblCultivar other = (TblCultivar) object;
        if ((this.cultivarId == null && other.cultivarId != null) || (this.cultivarId != null && !this.cultivarId.equals(other.cultivarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblCultivar[ cultivarId=" + cultivarId + " ]";
    }

}
