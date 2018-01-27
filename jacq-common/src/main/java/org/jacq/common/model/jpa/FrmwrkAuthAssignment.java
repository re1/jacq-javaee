/*
 * Copyright 2018 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk_AuthAssignment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkAuthAssignment.findAll", query = "SELECT f FROM FrmwrkAuthAssignment f")
    , @NamedQuery(name = "FrmwrkAuthAssignment.findByItemname", query = "SELECT f FROM FrmwrkAuthAssignment f WHERE f.frmwrkAuthAssignmentPK.itemname = :itemname")
    , @NamedQuery(name = "FrmwrkAuthAssignment.findByUserid", query = "SELECT f FROM FrmwrkAuthAssignment f WHERE f.frmwrkAuthAssignmentPK.userid = :userid")})
public class FrmwrkAuthAssignment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FrmwrkAuthAssignmentPK frmwrkAuthAssignmentPK;
    @Lob
    @Size(max = 65535)
    @Column(name = "bizrule")
    private String bizrule;
    @Lob
    @Size(max = 65535)
    @Column(name = "data")
    private String data;
    @JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FrmwrkUser frmwrkUser;
    @JoinColumn(name = "itemname", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FrmwrkAuthItem frmwrkAuthItem;

    public FrmwrkAuthAssignment() {
    }

    public FrmwrkAuthAssignment(FrmwrkAuthAssignmentPK frmwrkAuthAssignmentPK) {
        this.frmwrkAuthAssignmentPK = frmwrkAuthAssignmentPK;
    }

    public FrmwrkAuthAssignment(String itemname, long userid) {
        this.frmwrkAuthAssignmentPK = new FrmwrkAuthAssignmentPK(itemname, userid);
    }

    public FrmwrkAuthAssignmentPK getFrmwrkAuthAssignmentPK() {
        return frmwrkAuthAssignmentPK;
    }

    public void setFrmwrkAuthAssignmentPK(FrmwrkAuthAssignmentPK frmwrkAuthAssignmentPK) {
        this.frmwrkAuthAssignmentPK = frmwrkAuthAssignmentPK;
    }

    public String getBizrule() {
        return bizrule;
    }

    public void setBizrule(String bizrule) {
        this.bizrule = bizrule;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public FrmwrkUser getFrmwrkUser() {
        return frmwrkUser;
    }

    public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
        this.frmwrkUser = frmwrkUser;
    }

    public FrmwrkAuthItem getFrmwrkAuthItem() {
        return frmwrkAuthItem;
    }

    public void setFrmwrkAuthItem(FrmwrkAuthItem frmwrkAuthItem) {
        this.frmwrkAuthItem = frmwrkAuthItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frmwrkAuthAssignmentPK != null ? frmwrkAuthAssignmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkAuthAssignment)) {
            return false;
        }
        FrmwrkAuthAssignment other = (FrmwrkAuthAssignment) object;
        if ((this.frmwrkAuthAssignmentPK == null && other.frmwrkAuthAssignmentPK != null) || (this.frmwrkAuthAssignmentPK != null && !this.frmwrkAuthAssignmentPK.equals(other.frmwrkAuthAssignmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkAuthAssignment[ frmwrkAuthAssignmentPK=" + frmwrkAuthAssignmentPK + " ]";
    }

}
