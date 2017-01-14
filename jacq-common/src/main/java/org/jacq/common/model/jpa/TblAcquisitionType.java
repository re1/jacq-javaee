/*
 * Copyright 2016 wkoller.
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionType.findAll", query = "SELECT t FROM TblAcquisitionType t")
    , @NamedQuery(name = "TblAcquisitionType.findById", query = "SELECT t FROM TblAcquisitionType t WHERE t.id = :id")
    , @NamedQuery(name = "TblAcquisitionType.findByType", query = "SELECT t FROM TblAcquisitionType t WHERE t.type = :type")})
public class TblAcquisitionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionTypeId", fetch = FetchType.LAZY)
    private List<TblAcquisitionEvent> tblAcquisitionEventList;

    public TblAcquisitionType() {
    }

    public TblAcquisitionType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<TblAcquisitionEvent> getTblAcquisitionEventList() {
        return tblAcquisitionEventList;
    }

    public void setTblAcquisitionEventList(List<TblAcquisitionEvent> tblAcquisitionEventList) {
        this.tblAcquisitionEventList = tblAcquisitionEventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcquisitionType)) {
            return false;
        }
        TblAcquisitionType other = (TblAcquisitionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionType[ id=" + id + " ]";
    }

}
