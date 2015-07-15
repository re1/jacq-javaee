/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_scientific_name_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblScientificNameInformation.findAll", query = "SELECT t FROM TblScientificNameInformation t"),
    @NamedQuery(name = "TblScientificNameInformation.findByScientificNameId", query = "SELECT t FROM TblScientificNameInformation t WHERE t.scientificNameId = :scientificNameId"),
    @NamedQuery(name = "TblScientificNameInformation.findBySpatialDistribution", query = "SELECT t FROM TblScientificNameInformation t WHERE t.spatialDistribution = :spatialDistribution"),
    @NamedQuery(name = "TblScientificNameInformation.findByCommonNames", query = "SELECT t FROM TblScientificNameInformation t WHERE t.commonNames = :commonNames")})
public class TblScientificNameInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scientific_name_id")
    private Integer scientificNameId;
    @Size(max = 255)
    @Column(name = "spatial_distribution")
    private String spatialDistribution;
    @Size(max = 255)
    @Column(name = "common_names")
    private String commonNames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scientificNameId")
    private Collection<TblCultivar> tblCultivarCollection;
    @JoinColumn(name = "habitus_type_id", referencedColumnName = "habitus_type_id")
    @ManyToOne
    private TblHabitusType habitusTypeId;

    public TblScientificNameInformation() {
    }

    public TblScientificNameInformation(Integer scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Integer getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Integer scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(String spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    @XmlTransient
    public Collection<TblCultivar> getTblCultivarCollection() {
        return tblCultivarCollection;
    }

    public void setTblCultivarCollection(Collection<TblCultivar> tblCultivarCollection) {
        this.tblCultivarCollection = tblCultivarCollection;
    }

    public TblHabitusType getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(TblHabitusType habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scientificNameId != null ? scientificNameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblScientificNameInformation)) {
            return false;
        }
        TblScientificNameInformation other = (TblScientificNameInformation) object;
        if ((this.scientificNameId == null && other.scientificNameId != null) || (this.scientificNameId != null && !this.scientificNameId.equals(other.scientificNameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblScientificNameInformation[ scientificNameId=" + scientificNameId + " ]";
    }

}
