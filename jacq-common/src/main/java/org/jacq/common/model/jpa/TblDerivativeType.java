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
@Table(name = "tbl_derivative_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDerivativeType.findAll", query = "SELECT t FROM TblDerivativeType t")
    , @NamedQuery(name = "TblDerivativeType.findByDerivativeTypeId", query = "SELECT t FROM TblDerivativeType t WHERE t.derivativeTypeId = :derivativeTypeId")
    , @NamedQuery(name = "TblDerivativeType.findByType", query = "SELECT t FROM TblDerivativeType t WHERE t.type = :type")})
public class TblDerivativeType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "derivative_type_id")
    private Long derivativeTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "derivativeTypeId", fetch = FetchType.LAZY)
    private List<TblDerivative> tblDerivativeList;

    public TblDerivativeType() {
    }

    public TblDerivativeType(Long derivativeTypeId) {
        this.derivativeTypeId = derivativeTypeId;
    }

    public TblDerivativeType(Long derivativeTypeId, String type) {
        this.derivativeTypeId = derivativeTypeId;
        this.type = type;
    }

    public Long getDerivativeTypeId() {
        return derivativeTypeId;
    }

    public void setDerivativeTypeId(Long derivativeTypeId) {
        this.derivativeTypeId = derivativeTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<TblDerivative> getTblDerivativeList() {
        return tblDerivativeList;
    }

    public void setTblDerivativeList(List<TblDerivative> tblDerivativeList) {
        this.tblDerivativeList = tblDerivativeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (derivativeTypeId != null ? derivativeTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDerivativeType)) {
            return false;
        }
        TblDerivativeType other = (TblDerivativeType) object;
        if ((this.derivativeTypeId == null && other.derivativeTypeId != null) || (this.derivativeTypeId != null && !this.derivativeTypeId.equals(other.derivativeTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblDerivativeType[ derivativeTypeId=" + derivativeTypeId + " ]";
    }

}
