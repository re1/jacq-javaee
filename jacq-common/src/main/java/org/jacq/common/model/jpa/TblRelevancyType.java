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
import java.util.Collection;
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
@Table(name = "tbl_relevancy_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRelevancyType.findAll", query = "SELECT t FROM TblRelevancyType t")
    , @NamedQuery(name = "TblRelevancyType.findById", query = "SELECT t FROM TblRelevancyType t WHERE t.id = :id")
    , @NamedQuery(name = "TblRelevancyType.findByType", query = "SELECT t FROM TblRelevancyType t WHERE t.type = :type")
    , @NamedQuery(name = "TblRelevancyType.findByImportant", query = "SELECT t FROM TblRelevancyType t WHERE t.important = :important")})
public class TblRelevancyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 25)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "important")
    private boolean important;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relevancyTypeId", fetch = FetchType.LAZY)
    private Collection<TblRelevancy> tblRelevancyCollection;

    public TblRelevancyType() {
    }

    public TblRelevancyType(Long id) {
        this.id = id;
    }

    public TblRelevancyType(Long id, boolean important) {
        this.id = id;
        this.important = important;
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

    public boolean getImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @XmlTransient
    public Collection<TblRelevancy> getTblRelevancyCollection() {
        return tblRelevancyCollection;
    }

    public void setTblRelevancyCollection(Collection<TblRelevancy> tblRelevancyCollection) {
        this.tblRelevancyCollection = tblRelevancyCollection;
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
        if (!(object instanceof TblRelevancyType)) {
            return false;
        }
        TblRelevancyType other = (TblRelevancyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblRelevancyType[ id=" + id + " ]";
    }

}
