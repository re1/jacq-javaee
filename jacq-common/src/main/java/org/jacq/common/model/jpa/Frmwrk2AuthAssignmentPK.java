/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author wkoller
 */
@Embeddable
public class Frmwrk2AuthAssignmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "item_name")
    private String itemName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public Frmwrk2AuthAssignmentPK() {
    }

    public Frmwrk2AuthAssignmentPK(String itemName, int userId) {
        this.itemName = itemName;
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemName != null ? itemName.hashCode() : 0);
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frmwrk2AuthAssignmentPK)) {
            return false;
        }
        Frmwrk2AuthAssignmentPK other = (Frmwrk2AuthAssignmentPK) object;
        if ((this.itemName == null && other.itemName != null) || (this.itemName != null && !this.itemName.equals(other.itemName))) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.Frmwrk2AuthAssignmentPK[ itemName=" + itemName + ", userId=" + userId + " ]";
    }

}
