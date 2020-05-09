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
package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author wkoller
 */
@Entity
@Table(name = "tbl_service", schema = "openup")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblService.findAll", query = "SELECT t FROM TblService t"),
        @NamedQuery(name = "TblService.findById", query = "SELECT t FROM TblService t WHERE t.id = :id"),
        @NamedQuery(name = "TblService.findByUrl", query = "SELECT t FROM TblService t WHERE t.url = :url")})
public class TblService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Collection<TblWebserviceCache> tblWebserviceCacheCollection;

    public TblService() {
    }

    public TblService(Integer id) {
        this.id = id;
    }

    public TblService(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<TblWebserviceCache> getTblWebserviceCacheCollection() {
        return tblWebserviceCacheCollection;
    }

    public void setTblWebserviceCacheCollection(Collection<TblWebserviceCache> tblWebserviceCacheCollection) {
        this.tblWebserviceCacheCollection = tblWebserviceCacheCollection;
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
        if (!(object instanceof TblService)) {
            return false;
        }
        TblService other = (TblService) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.openup.TblService[ id=" + id + " ]";
    }

}
