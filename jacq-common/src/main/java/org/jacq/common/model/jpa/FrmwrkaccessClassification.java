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
@Table(name = "frmwrk_accessClassification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkaccessClassification.findAll", query = "SELECT f FROM FrmwrkaccessClassification f"),
    @NamedQuery(name = "FrmwrkaccessClassification.findByAccessClassificationId", query = "SELECT f FROM FrmwrkaccessClassification f WHERE f.accessClassificationId = :accessClassificationId"),
    @NamedQuery(name = "FrmwrkaccessClassification.findByAllowDeny", query = "SELECT f FROM FrmwrkaccessClassification f WHERE f.allowDeny = :allowDeny"),
    @NamedQuery(name = "FrmwrkaccessClassification.findByTaxsynID", query = "SELECT f FROM FrmwrkaccessClassification f WHERE f.taxsynID = :taxsynID")})
public class FrmwrkaccessClassification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "access_classification_id")
    private Integer accessClassificationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allowDeny")
    private boolean allowDeny;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tax_syn_ID")
    private int taxsynID;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private FrmwrkUser userId;
    @JoinColumn(name = "AuthItem_name", referencedColumnName = "name")
    @ManyToOne
    private FrmwrkAuthItem authItemname;

    public FrmwrkaccessClassification() {
    }

    public FrmwrkaccessClassification(Integer accessClassificationId) {
        this.accessClassificationId = accessClassificationId;
    }

    public FrmwrkaccessClassification(Integer accessClassificationId, boolean allowDeny, int taxsynID) {
        this.accessClassificationId = accessClassificationId;
        this.allowDeny = allowDeny;
        this.taxsynID = taxsynID;
    }

    public Integer getAccessClassificationId() {
        return accessClassificationId;
    }

    public void setAccessClassificationId(Integer accessClassificationId) {
        this.accessClassificationId = accessClassificationId;
    }

    public boolean getAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(boolean allowDeny) {
        this.allowDeny = allowDeny;
    }

    public int getTaxsynID() {
        return taxsynID;
    }

    public void setTaxsynID(int taxsynID) {
        this.taxsynID = taxsynID;
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
        hash += (accessClassificationId != null ? accessClassificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkaccessClassification)) {
            return false;
        }
        FrmwrkaccessClassification other = (FrmwrkaccessClassification) object;
        if ((this.accessClassificationId == null && other.accessClassificationId != null) || (this.accessClassificationId != null && !this.accessClassificationId.equals(other.accessClassificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkaccessClassification[ accessClassificationId=" + accessClassificationId + " ]";
    }

}
