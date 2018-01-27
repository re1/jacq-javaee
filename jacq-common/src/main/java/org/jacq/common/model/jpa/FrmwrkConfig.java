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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkConfig.findAll", query = "SELECT f FROM FrmwrkConfig f")
    , @NamedQuery(name = "FrmwrkConfig.findByFcId", query = "SELECT f FROM FrmwrkConfig f WHERE f.fcId = :fcId")
    , @NamedQuery(name = "FrmwrkConfig.findByFcName", query = "SELECT f FROM FrmwrkConfig f WHERE f.fcName = :fcName")})
public class FrmwrkConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fc_id")
    private Long fcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fc_name")
    private String fcName;
    @Lob
    @Size(max = 65535)
    @Column(name = "fc_value")
    private String fcValue;

    public FrmwrkConfig() {
    }

    public FrmwrkConfig(Long fcId) {
        this.fcId = fcId;
    }

    public FrmwrkConfig(Long fcId, String fcName) {
        this.fcId = fcId;
        this.fcName = fcName;
    }

    public Long getFcId() {
        return fcId;
    }

    public void setFcId(Long fcId) {
        this.fcId = fcId;
    }

    public String getFcName() {
        return fcName;
    }

    public void setFcName(String fcName) {
        this.fcName = fcName;
    }

    public String getFcValue() {
        return fcValue;
    }

    public void setFcValue(String fcValue) {
        this.fcValue = fcValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fcId != null ? fcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkConfig)) {
            return false;
        }
        FrmwrkConfig other = (FrmwrkConfig) object;
        if ((this.fcId == null && other.fcId != null) || (this.fcId != null && !this.fcId.equals(other.fcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkConfig[ fcId=" + fcId + " ]";
    }

}
