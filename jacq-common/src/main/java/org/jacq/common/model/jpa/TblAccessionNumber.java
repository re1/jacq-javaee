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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_accession_number")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAccessionNumber.findAll", query = "SELECT t FROM TblAccessionNumber t")
    , @NamedQuery(name = "TblAccessionNumber.findByOrganisationId", query = "SELECT t FROM TblAccessionNumber t WHERE t.organisationId = :organisationId")
    , @NamedQuery(name = "TblAccessionNumber.findByAccessionNumber", query = "SELECT t FROM TblAccessionNumber t WHERE t.accessionNumber = :accessionNumber")})
public class TblAccessionNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "organisation_id")
    private Long organisationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accession_number")
    private long accessionNumber;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation tblOrganisation;

    public TblAccessionNumber() {
    }

    public TblAccessionNumber(Long organisationId) {
        this.organisationId = organisationId;
    }

    public TblAccessionNumber(Long organisationId, long accessionNumber) {
        this.organisationId = organisationId;
        this.accessionNumber = accessionNumber;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public long getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(long accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public TblOrganisation getTblOrganisation() {
        return tblOrganisation;
    }

    public void setTblOrganisation(TblOrganisation tblOrganisation) {
        this.tblOrganisation = tblOrganisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organisationId != null ? organisationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAccessionNumber)) {
            return false;
        }
        TblAccessionNumber other = (TblAccessionNumber) object;
        if ((this.organisationId == null && other.organisationId != null) || (this.organisationId != null && !this.organisationId.equals(other.organisationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAccessionNumber[ organisationId=" + organisationId + " ]";
    }

}
