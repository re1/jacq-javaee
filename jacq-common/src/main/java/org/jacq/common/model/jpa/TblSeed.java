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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_seed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSeed.findAll", query = "SELECT t FROM TblSeed t")
    , @NamedQuery(name = "TblSeed.findBySeedId", query = "SELECT t FROM TblSeed t WHERE t.seedId = :seedId")
    , @NamedQuery(name = "TblSeed.findByIndexSeminum", query = "SELECT t FROM TblSeed t WHERE t.indexSeminum = :indexSeminum")})
public class TblSeed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "seed_id")
    private Long seedId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "index_seminum")
    private boolean indexSeminum;
    @JoinTable(name = "tbl_orderer_seed", joinColumns = {
        @JoinColumn(name = "seed_id", referencedColumnName = "seed_id")}, inverseJoinColumns = {
        @JoinColumn(name = "seed_order_id", referencedColumnName = "seed_order_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TblSeedOrder> tblSeedOrderList;
    @JoinColumn(name = "seed_id", referencedColumnName = "derivative_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblDerivative tblDerivative;
    @JoinColumn(name = "index_seminum_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblIndexSeminumType indexSeminumTypeId;

    public TblSeed() {
    }

    public TblSeed(Long seedId) {
        this.seedId = seedId;
    }

    public TblSeed(Long seedId, boolean indexSeminum) {
        this.seedId = seedId;
        this.indexSeminum = indexSeminum;
    }

    public Long getSeedId() {
        return seedId;
    }

    public void setSeedId(Long seedId) {
        this.seedId = seedId;
    }

    public boolean getIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    @XmlTransient
    public List<TblSeedOrder> getTblSeedOrderList() {
        return tblSeedOrderList;
    }

    public void setTblSeedOrderList(List<TblSeedOrder> tblSeedOrderList) {
        this.tblSeedOrderList = tblSeedOrderList;
    }

    public TblDerivative getTblDerivative() {
        return tblDerivative;
    }

    public void setTblDerivative(TblDerivative tblDerivative) {
        this.tblDerivative = tblDerivative;
    }

    public TblIndexSeminumType getIndexSeminumTypeId() {
        return indexSeminumTypeId;
    }

    public void setIndexSeminumTypeId(TblIndexSeminumType indexSeminumTypeId) {
        this.indexSeminumTypeId = indexSeminumTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seedId != null ? seedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSeed)) {
            return false;
        }
        TblSeed other = (TblSeed) object;
        if ((this.seedId == null && other.seedId != null) || (this.seedId != null && !this.seedId.equals(other.seedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSeed[ seedId=" + seedId + " ]";
    }

}
