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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "tbl_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPerson.findAll", query = "SELECT t FROM TblPerson t"),
    @NamedQuery(name = "TblPerson.findById", query = "SELECT t FROM TblPerson t WHERE t.id = :id"),
    @NamedQuery(name = "TblPerson.findByName", query = "SELECT t FROM TblPerson t WHERE t.name = :name")})
public class TblPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "tblPersonCollection")
    private Collection<TblAcquisitionEvent> tblAcquisitionEventCollection;
    @OneToMany(mappedBy = "determinedById")
    private Collection<TblBotanicalObject> tblBotanicalObjectCollection;

    public TblPerson() {
    }

    public TblPerson(Integer id) {
        this.id = id;
    }

    public TblPerson(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<TblAcquisitionEvent> getTblAcquisitionEventCollection() {
        return tblAcquisitionEventCollection;
    }

    public void setTblAcquisitionEventCollection(Collection<TblAcquisitionEvent> tblAcquisitionEventCollection) {
        this.tblAcquisitionEventCollection = tblAcquisitionEventCollection;
    }

    @XmlTransient
    public Collection<TblBotanicalObject> getTblBotanicalObjectCollection() {
        return tblBotanicalObjectCollection;
    }

    public void setTblBotanicalObjectCollection(Collection<TblBotanicalObject> tblBotanicalObjectCollection) {
        this.tblBotanicalObjectCollection = tblBotanicalObjectCollection;
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
        if (!(object instanceof TblPerson)) {
            return false;
        }
        TblPerson other = (TblPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblPerson[ id=" + id + " ]";
    }

}
