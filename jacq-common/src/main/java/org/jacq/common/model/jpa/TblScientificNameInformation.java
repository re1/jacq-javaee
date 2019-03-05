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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_scientific_name_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblScientificNameInformation.findAll", query = "SELECT t FROM TblScientificNameInformation t")
    , @NamedQuery(name = "TblScientificNameInformation.findByScientificNameId", query = "SELECT t FROM TblScientificNameInformation t WHERE t.scientificNameId = :scientificNameId")})
public class TblScientificNameInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private Long scientificNameId;
    @Lob
    @Size(max = 65535)
    @Column(name = "spatial_distribution")
    private String spatialDistribution;
    @Lob
    @Size(max = 65535)
    @Column(name = "common_names")
    private String commonNames;
    @JoinColumn(name = "habitus_type_id", referencedColumnName = "habitus_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblHabitusType habitusTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scientificNameId", fetch = FetchType.LAZY)
    private List<TblCultivar> tblCultivarList;

    public TblScientificNameInformation() {
    }

    public TblScientificNameInformation(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(String spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    public TblHabitusType getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(TblHabitusType habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    @XmlTransient
    public List<TblCultivar> getTblCultivarList() {
        return tblCultivarList;
    }

    public void setTblCultivarList(List<TblCultivar> tblCultivarList) {
        this.tblCultivarList = tblCultivarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scientificNameId != null ? scientificNameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblScientificNameInformation)) {
            return false;
        }
        TblScientificNameInformation other = (TblScientificNameInformation) object;
        if ((this.scientificNameId == null && other.scientificNameId != null) || (this.scientificNameId != null && !this.scientificNameId.equals(other.scientificNameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblScientificNameInformation[ scientificNameId=" + scientificNameId + " ]";
    }

}
