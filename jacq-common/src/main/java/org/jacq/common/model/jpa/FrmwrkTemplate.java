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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "frmwrk_template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkTemplate.findAll", query = "SELECT f FROM FrmwrkTemplate f")
    , @NamedQuery(name = "FrmwrkTemplate.findByFtId", query = "SELECT f FROM FrmwrkTemplate f WHERE f.ftId = :ftId")
    , @NamedQuery(name = "FrmwrkTemplate.findByFtName", query = "SELECT f FROM FrmwrkTemplate f WHERE f.ftName = :ftName")})
public class FrmwrkTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ft_id")
    private Long ftId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ft_name")
    private String ftName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ft_template")
    private byte[] ftTemplate;

    public FrmwrkTemplate() {
    }

    public FrmwrkTemplate(Long ftId) {
        this.ftId = ftId;
    }

    public FrmwrkTemplate(Long ftId, String ftName, byte[] ftTemplate) {
        this.ftId = ftId;
        this.ftName = ftName;
        this.ftTemplate = ftTemplate;
    }

    public Long getFtId() {
        return ftId;
    }

    public void setFtId(Long ftId) {
        this.ftId = ftId;
    }

    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName;
    }

    public byte[] getFtTemplate() {
        return ftTemplate;
    }

    public void setFtTemplate(byte[] ftTemplate) {
        this.ftTemplate = ftTemplate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ftId != null ? ftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkTemplate)) {
            return false;
        }
        FrmwrkTemplate other = (FrmwrkTemplate) object;
        if ((this.ftId == null && other.ftId != null) || (this.ftId != null && !this.ftId.equals(other.ftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkTemplate[ ftId=" + ftId + " ]";
    }

}
