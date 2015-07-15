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
public class FrmwrkAuthAssignmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "itemname")
    private String itemname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;

    public FrmwrkAuthAssignmentPK() {
    }

    public FrmwrkAuthAssignmentPK(String itemname, int userid) {
        this.itemname = itemname;
        this.userid = userid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemname != null ? itemname.hashCode() : 0);
        hash += (int) userid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkAuthAssignmentPK)) {
            return false;
        }
        FrmwrkAuthAssignmentPK other = (FrmwrkAuthAssignmentPK) object;
        if ((this.itemname == null && other.itemname != null) || (this.itemname != null && !this.itemname.equals(other.itemname))) {
            return false;
        }
        if (this.userid != other.userid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkAuthAssignmentPK[ itemname=" + itemname + ", userid=" + userid + " ]";
    }

}
