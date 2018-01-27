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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "frmwrk_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkGroup.findAll", query = "SELECT f FROM FrmwrkGroup f")
    , @NamedQuery(name = "FrmwrkGroup.findByGroupId", query = "SELECT f FROM FrmwrkGroup f WHERE f.groupId = :groupId")
    , @NamedQuery(name = "FrmwrkGroup.findByName", query = "SELECT f FROM FrmwrkGroup f WHERE f.name = :name")})
public class FrmwrkGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "group_id")
    private Long groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "frmwrkGroupList")
    private List<FrmwrkUser> frmwrkUserList;

    public FrmwrkGroup() {
    }

    public FrmwrkGroup(Long groupId) {
        this.groupId = groupId;
    }

    public FrmwrkGroup(Long groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<FrmwrkUser> getFrmwrkUserList() {
        return frmwrkUserList;
    }

    public void setFrmwrkUserList(List<FrmwrkUser> frmwrkUserList) {
        this.frmwrkUserList = frmwrkUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkGroup)) {
            return false;
        }
        FrmwrkGroup other = (FrmwrkGroup) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkGroup[ groupId=" + groupId + " ]";
    }

}
