/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk_accessBotanicalObject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkaccessBotanicalObject.findAll", query = "SELECT f FROM FrmwrkaccessBotanicalObject f"),
    @NamedQuery(name = "FrmwrkaccessBotanicalObject.findById", query = "SELECT f FROM FrmwrkaccessBotanicalObject f WHERE f.id = :id"),
    @NamedQuery(name = "FrmwrkaccessBotanicalObject.findByAllowDeny", query = "SELECT f FROM FrmwrkaccessBotanicalObject f WHERE f.allowDeny = :allowDeny")})
public class FrmwrkaccessBotanicalObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allowDeny")
    private boolean allowDeny;
    @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblLivingPlant botanicalObjectId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private FrmwrkUser userId;
    @JoinColumn(name = "AuthItem_name", referencedColumnName = "name")
    @ManyToOne
    private FrmwrkAuthItem authItemname;

    public FrmwrkaccessBotanicalObject() {
    }

    public FrmwrkaccessBotanicalObject(Integer id) {
        this.id = id;
    }

    public FrmwrkaccessBotanicalObject(Integer id, boolean allowDeny) {
        this.id = id;
        this.allowDeny = allowDeny;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(boolean allowDeny) {
        this.allowDeny = allowDeny;
    }

    public TblLivingPlant getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(TblLivingPlant botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    public FrmwrkAuthItem getAuthItemname() {
        return authItemname;
    }

    public void setAuthItemname(FrmwrkAuthItem authItemname) {
        this.authItemname = authItemname;
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
        if (!(object instanceof FrmwrkaccessBotanicalObject)) {
            return false;
        }
        FrmwrkaccessBotanicalObject other = (FrmwrkaccessBotanicalObject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkaccessBotanicalObject[ id=" + id + " ]";
    }

}
