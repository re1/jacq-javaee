/*
 * Copyright 2018 wkoller.
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_relevancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRelevancy.findAll", query = "SELECT t FROM TblRelevancy t")
    , @NamedQuery(name = "TblRelevancy.findById", query = "SELECT t FROM TblRelevancy t WHERE t.id = :id")})
public class TblRelevancy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblLivingPlant livingPlantId;
    @JoinColumn(name = "relevancy_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblRelevancyType relevancyTypeId;

    public TblRelevancy() {
    }

    public TblRelevancy(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TblLivingPlant getLivingPlantId() {
        return livingPlantId;
    }

    public void setLivingPlantId(TblLivingPlant livingPlantId) {
        this.livingPlantId = livingPlantId;
    }

    public TblRelevancyType getRelevancyTypeId() {
        return relevancyTypeId;
    }

    public void setRelevancyTypeId(TblRelevancyType relevancyTypeId) {
        this.relevancyTypeId = relevancyTypeId;
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
        if (!(object instanceof TblRelevancy)) {
            return false;
        }
        TblRelevancy other = (TblRelevancy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblRelevancy[ id=" + id + " ]";
    }

}
