/*
 * Copyright 2017 wkoller.
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
@Table(name = "mig_nom_epithet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNomEpithet.findAll", query = "SELECT t FROM TblNomEpithet t")
    , @NamedQuery(name = "TblNomEpithet.findByEpithetId", query = "SELECT t FROM TblNomEpithet t WHERE t.epithetId = :epithetId")
    , @NamedQuery(name = "TblNomEpithet.findByEpithet", query = "SELECT t FROM TblNomEpithet t WHERE t.epithet = :epithet")})
public class TblNomEpithet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "epithet_id")
    private Long epithetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "epithet")
    private String epithet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firstEpithetId")
    private List<TblNomName> tblNomNameList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secondEpithetId")
    private List<TblNomName> tblNomNameList1;

    public TblNomEpithet() {
    }

    public TblNomEpithet(Long epithetId) {
        this.epithetId = epithetId;
    }

    public TblNomEpithet(Long epithetId, String epithet) {
        this.epithetId = epithetId;
        this.epithet = epithet;
    }

    public Long getEpithetId() {
        return epithetId;
    }

    public void setEpithetId(Long epithetId) {
        this.epithetId = epithetId;
    }

    public String getEpithet() {
        return epithet;
    }

    public void setEpithet(String epithet) {
        this.epithet = epithet;
    }

    @XmlTransient
    public List<TblNomName> getTblNomNameList() {
        return tblNomNameList;
    }

    public void setTblNomNameList(List<TblNomName> tblNomNameList) {
        this.tblNomNameList = tblNomNameList;
    }

    @XmlTransient
    public List<TblNomName> getTblNomNameList1() {
        return tblNomNameList1;
    }

    public void setTblNomNameList1(List<TblNomName> tblNomNameList1) {
        this.tblNomNameList1 = tblNomNameList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epithetId != null ? epithetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNomEpithet)) {
            return false;
        }
        TblNomEpithet other = (TblNomEpithet) object;
        if ((this.epithetId == null && other.epithetId != null) || (this.epithetId != null && !this.epithetId.equals(other.epithetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblNomEpithet[ epithetId=" + epithetId + " ]";
    }

}
