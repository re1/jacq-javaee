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
@Table(name = "frmwrk_user_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkUserType.findAll", query = "SELECT f FROM FrmwrkUserType f"),
    @NamedQuery(name = "FrmwrkUserType.findByUserTypeId", query = "SELECT f FROM FrmwrkUserType f WHERE f.userTypeId = :userTypeId"),
    @NamedQuery(name = "FrmwrkUserType.findByType", query = "SELECT f FROM FrmwrkUserType f WHERE f.type = :type")})
public class FrmwrkUserType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_type_id")
    private Integer userTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTypeId")
    private Collection<FrmwrkUser> frmwrkUserCollection;

    public FrmwrkUserType() {
    }

    public FrmwrkUserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public FrmwrkUserType(Integer userTypeId, String type) {
        this.userTypeId = userTypeId;
        this.type = type;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
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
        hash += (userTypeId != null ? userTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkUserType)) {
            return false;
        }
        FrmwrkUserType other = (FrmwrkUserType) object;
        if ((this.userTypeId == null && other.userTypeId != null) || (this.userTypeId != null && !this.userTypeId.equals(other.userTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkUserType[ userTypeId=" + userTypeId + " ]";
    }

}
