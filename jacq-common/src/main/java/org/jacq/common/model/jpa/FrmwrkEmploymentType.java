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
@Table(name = "frmwrk_employment_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkEmploymentType.findAll", query = "SELECT f FROM FrmwrkEmploymentType f"),
    @NamedQuery(name = "FrmwrkEmploymentType.findByEmploymentTypeId", query = "SELECT f FROM FrmwrkEmploymentType f WHERE f.employmentTypeId = :employmentTypeId"),
    @NamedQuery(name = "FrmwrkEmploymentType.findByType", query = "SELECT f FROM FrmwrkEmploymentType f WHERE f.type = :type")})
public class FrmwrkEmploymentType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employment_type_id")
    private Integer employmentTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employmentTypeId")
    private Collection<FrmwrkUser> frmwrkUserCollection;

    public FrmwrkEmploymentType() {
    }

    public FrmwrkEmploymentType(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public FrmwrkEmploymentType(Integer employmentTypeId, String type) {
        this.employmentTypeId = employmentTypeId;
        this.type = type;
    }

    public Integer getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<FrmwrkUser> getFrmwrkUserCollection() {
        return frmwrkUserCollection;
    }

    public void setFrmwrkUserCollection(Collection<FrmwrkUser> frmwrkUserCollection) {
        this.frmwrkUserCollection = frmwrkUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employmentTypeId != null ? employmentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkEmploymentType)) {
            return false;
        }
        FrmwrkEmploymentType other = (FrmwrkEmploymentType) object;
        if ((this.employmentTypeId == null && other.employmentTypeId != null) || (this.employmentTypeId != null && !this.employmentTypeId.equals(other.employmentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkEmploymentType[ employmentTypeId=" + employmentTypeId + " ]";
    }

}
