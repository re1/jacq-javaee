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

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_common_names_cache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCommonNamesCache.findAll", query = "SELECT t FROM TblCommonNamesCache t"),
    @NamedQuery(name = "TblCommonNamesCache.findById", query = "SELECT t FROM TblCommonNamesCache t WHERE t.id = :id"),
    @NamedQuery(name = "TblCommonNamesCache.findByName", query = "SELECT t FROM TblCommonNamesCache t WHERE t.name = :name"),
    @NamedQuery(name = "TblCommonNamesCache.findByLanguage", query = "SELECT t FROM TblCommonNamesCache t WHERE t.language = :language"),
    @NamedQuery(name = "TblCommonNamesCache.findByGeography", query = "SELECT t FROM TblCommonNamesCache t WHERE t.geography = :geography"),
    @NamedQuery(name = "TblCommonNamesCache.findByPeriod", query = "SELECT t FROM TblCommonNamesCache t WHERE t.period = :period"),
    @NamedQuery(name = "TblCommonNamesCache.findCachedEntry", query = "SELECT t FROM TblCommonNamesCache t WHERE t.name = :name and t.language = :language and t.geography = :geography and t.period = :period")
})
public class TblCommonNamesCache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 15)
    @Column(name = "language")
    private String language;
    @Size(max = 200)
    @Column(name = "geography")
    private String geography;
    @Size(max = 45)
    @Column(name = "period")
    private String period;

    public TblCommonNamesCache() {
    }

    public TblCommonNamesCache(Long id) {
        this.id = id;
    }

    public TblCommonNamesCache(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
        if (!(object instanceof TblCommonNamesCache)) {
            return false;
        }
        TblCommonNamesCache other = (TblCommonNamesCache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.openup.TblCommonNamesCache[ id=" + id + " ]";
    }

}
