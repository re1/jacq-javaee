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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_image_server")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblImageServer.findAll", query = "SELECT t FROM TblImageServer t")
    , @NamedQuery(name = "TblImageServer.findByOrganisationId", query = "SELECT t FROM TblImageServer t WHERE t.organisationId = :organisationId")
    , @NamedQuery(name = "TblImageServer.findByKey", query = "SELECT t FROM TblImageServer t WHERE t.key = :key")})
public class TblImageServer implements Serializable {

    @Column(name = "last_synchronized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSynchronized;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "organisation_id")
    private Long organisationId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "base_url")
    private String baseUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "key")
    private String key;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation tblOrganisation;

    public TblImageServer() {
    }

    public TblImageServer(Long organisationId) {
        this.organisationId = organisationId;
    }

    public TblImageServer(Long organisationId, String baseUrl, String key) {
        this.organisationId = organisationId;
        this.baseUrl = baseUrl;
        this.key = key;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        if (!(object instanceof TblImageServer)) {
            return false;
        }
        TblImageServer other = (TblImageServer) object;
        if ((this.organisationId == null && other.organisationId != null) || (this.organisationId != null && !this.organisationId.equals(other.organisationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblImageServer[ organisationId=" + organisationId + " ]";
    }

    public Date getLastSynchronized() {
        return lastSynchronized;
    }

    public void setLastSynchronized(Date lastSynchronized) {
        this.lastSynchronized = lastSynchronized;
    }

}
