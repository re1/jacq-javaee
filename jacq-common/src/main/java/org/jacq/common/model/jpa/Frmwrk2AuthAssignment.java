/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk2_auth_assignment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frmwrk2AuthAssignment.findAll", query = "SELECT f FROM Frmwrk2AuthAssignment f"),
    @NamedQuery(name = "Frmwrk2AuthAssignment.findByItemName", query = "SELECT f FROM Frmwrk2AuthAssignment f WHERE f.frmwrk2AuthAssignmentPK.itemName = :itemName"),
    @NamedQuery(name = "Frmwrk2AuthAssignment.findByUserId", query = "SELECT f FROM Frmwrk2AuthAssignment f WHERE f.frmwrk2AuthAssignmentPK.userId = :userId"),
    @NamedQuery(name = "Frmwrk2AuthAssignment.findByCreatedAt", query = "SELECT f FROM Frmwrk2AuthAssignment f WHERE f.createdAt = :createdAt")})
public class Frmwrk2AuthAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Frmwrk2AuthAssignmentPK frmwrk2AuthAssignmentPK;
    @Column(name = "created_at")
    private Integer createdAt;
    @JoinColumn(name = "item_name", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Frmwrk2AuthItem frmwrk2AuthItem;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FrmwrkUser frmwrkUser;

    public Frmwrk2AuthAssignment() {
    }

    public Frmwrk2AuthAssignment(Frmwrk2AuthAssignmentPK frmwrk2AuthAssignmentPK) {
        this.frmwrk2AuthAssignmentPK = frmwrk2AuthAssignmentPK;
    }

    public Frmwrk2AuthAssignment(String itemName, int userId) {
        this.frmwrk2AuthAssignmentPK = new Frmwrk2AuthAssignmentPK(itemName, userId);
    }

    public Frmwrk2AuthAssignmentPK getFrmwrk2AuthAssignmentPK() {
        return frmwrk2AuthAssignmentPK;
    }

    public void setFrmwrk2AuthAssignmentPK(Frmwrk2AuthAssignmentPK frmwrk2AuthAssignmentPK) {
        this.frmwrk2AuthAssignmentPK = frmwrk2AuthAssignmentPK;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Frmwrk2AuthItem getFrmwrk2AuthItem() {
        return frmwrk2AuthItem;
    }

    public void setFrmwrk2AuthItem(Frmwrk2AuthItem frmwrk2AuthItem) {
        this.frmwrk2AuthItem = frmwrk2AuthItem;
    }

    public FrmwrkUser getFrmwrkUser() {
        return frmwrkUser;
    }

    public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
        this.frmwrkUser = frmwrkUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frmwrk2AuthAssignmentPK != null ? frmwrk2AuthAssignmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frmwrk2AuthAssignment)) {
            return false;
        }
        Frmwrk2AuthAssignment other = (Frmwrk2AuthAssignment) object;
        if ((this.frmwrk2AuthAssignmentPK == null && other.frmwrk2AuthAssignmentPK != null) || (this.frmwrk2AuthAssignmentPK != null && !this.frmwrk2AuthAssignmentPK.equals(other.frmwrk2AuthAssignmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.Frmwrk2AuthAssignment[ frmwrk2AuthAssignmentPK=" + frmwrk2AuthAssignmentPK + " ]";
    }

}
