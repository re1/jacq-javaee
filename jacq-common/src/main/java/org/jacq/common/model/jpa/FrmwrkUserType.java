/*
 * Copyright 2019 wkoller.
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "FrmwrkUserType.findAll", query = "SELECT f FROM FrmwrkUserType f")
    , @NamedQuery(name = "FrmwrkUserType.findByUserTypeId", query = "SELECT f FROM FrmwrkUserType f WHERE f.userTypeId = :userTypeId")
    , @NamedQuery(name = "FrmwrkUserType.findByType", query = "SELECT f FROM FrmwrkUserType f WHERE f.type = :type")})
public class FrmwrkUserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_type_id")
    private Long userTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTypeId", fetch = FetchType.LAZY)
    private List<FrmwrkUser> frmwrkUserList;

    public FrmwrkUserType() {
    }

    public FrmwrkUserType(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public FrmwrkUserType(Long userTypeId, String type) {
        this.userTypeId = userTypeId;
        this.type = type;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
