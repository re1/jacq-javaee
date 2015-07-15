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
@Table(name = "tbl_habitus_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHabitusType.findAll", query = "SELECT t FROM TblHabitusType t"),
    @NamedQuery(name = "TblHabitusType.findByHabitusTypeId", query = "SELECT t FROM TblHabitusType t WHERE t.habitusTypeId = :habitusTypeId"),
    @NamedQuery(name = "TblHabitusType.findByHabitus", query = "SELECT t FROM TblHabitusType t WHERE t.habitus = :habitus")})
public class TblHabitusType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "habitus_type_id")
    private Integer habitusTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "habitus")
    private String habitus;
    @OneToMany(mappedBy = "habitusTypeId")
    private Collection<TblScientificNameInformation> tblScientificNameInformationCollection;

    public TblHabitusType() {
    }

    public TblHabitusType(Integer habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    public TblHabitusType(Integer habitusTypeId, String habitus) {
        this.habitusTypeId = habitusTypeId;
        this.habitus = habitus;
    }

    public Integer getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(Integer habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    public String getHabitus() {
        return habitus;
    }

    public void setHabitus(String habitus) {
        this.habitus = habitus;
    }

    @XmlTransient
    public Collection<TblScientificNameInformation> getTblScientificNameInformationCollection() {
        return tblScientificNameInformationCollection;
    }

    public void setTblScientificNameInformationCollection(Collection<TblScientificNameInformation> tblScientificNameInformationCollection) {
        this.tblScientificNameInformationCollection = tblScientificNameInformationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habitusTypeId != null ? habitusTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHabitusType)) {
            return false;
        }
        TblHabitusType other = (TblHabitusType) object;
        if ((this.habitusTypeId == null && other.habitusTypeId != null) || (this.habitusTypeId != null && !this.habitusTypeId.equals(other.habitusTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblHabitusType[ habitusTypeId=" + habitusTypeId + " ]";
    }

}
