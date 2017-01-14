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
@Table(name = "tbl_habitus_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHabitusType.findAll", query = "SELECT t FROM TblHabitusType t")
    , @NamedQuery(name = "TblHabitusType.findByHabitusTypeId", query = "SELECT t FROM TblHabitusType t WHERE t.habitusTypeId = :habitusTypeId")
    , @NamedQuery(name = "TblHabitusType.findByHabitus", query = "SELECT t FROM TblHabitusType t WHERE t.habitus = :habitus")})
public class TblHabitusType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "habitus_type_id")
    private Long habitusTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "habitus")
    private String habitus;
    @OneToMany(mappedBy = "habitusTypeId", fetch = FetchType.LAZY)
    private List<TblScientificNameInformation> tblScientificNameInformationList;

    public TblHabitusType() {
    }

    public TblHabitusType(Long habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    public TblHabitusType(Long habitusTypeId, String habitus) {
        this.habitusTypeId = habitusTypeId;
        this.habitus = habitus;
    }

    public Long getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(Long habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    public String getHabitus() {
        return habitus;
    }

    public void setHabitus(String habitus) {
        this.habitus = habitus;
    }

    @XmlTransient
    public List<TblScientificNameInformation> getTblScientificNameInformationList() {
        return tblScientificNameInformationList;
    }

    public void setTblScientificNameInformationList(List<TblScientificNameInformation> tblScientificNameInformationList) {
        this.tblScientificNameInformationList = tblScientificNameInformationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habitusTypeId != null ? habitusTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHabitusType)) {
            return false;
        }
        TblHabitusType other = (TblHabitusType) object;
        if ((this.habitusTypeId == null && other.habitusTypeId != null) || (this.habitusTypeId != null && !this.habitusTypeId.equals(other.habitusTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblHabitusType[ habitusTypeId=" + habitusTypeId + " ]";
    }

}
