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
@Table(name = "tbl_seed_order_derivative")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSeedOrderDerivative.findAll", query = "SELECT t FROM TblSeedOrderDerivative t")
    , @NamedQuery(name = "TblSeedOrderDerivative.findBySeedOrderDerivativeId", query = "SELECT t FROM TblSeedOrderDerivative t WHERE t.seedOrderDerivativeId = :seedOrderDerivativeId")})
public class TblSeedOrderDerivative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seed_order_derivative_id")
    private Long seedOrderDerivativeId;
    @JoinColumn(name = "derivative_id", referencedColumnName = "derivative_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblDerivative derivativeId;
    @JoinColumn(name = "seed_order_id", referencedColumnName = "seed_order_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblSeedOrder seedOrderId;

    public TblSeedOrderDerivative() {
    }

    public TblSeedOrderDerivative(Long seedOrderDerivativeId) {
        this.seedOrderDerivativeId = seedOrderDerivativeId;
    }

    public Long getSeedOrderDerivativeId() {
        return seedOrderDerivativeId;
    }

    public void setSeedOrderDerivativeId(Long seedOrderDerivativeId) {
        this.seedOrderDerivativeId = seedOrderDerivativeId;
    }

    public TblDerivative getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(TblDerivative derivativeId) {
        this.derivativeId = derivativeId;
    }

    public TblSeedOrder getSeedOrderId() {
        return seedOrderId;
    }

    public void setSeedOrderId(TblSeedOrder seedOrderId) {
        this.seedOrderId = seedOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seedOrderDerivativeId != null ? seedOrderDerivativeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSeedOrderDerivative)) {
            return false;
        }
        TblSeedOrderDerivative other = (TblSeedOrderDerivative) object;
        if ((this.seedOrderDerivativeId == null && other.seedOrderDerivativeId != null) || (this.seedOrderDerivativeId != null && !this.seedOrderDerivativeId.equals(other.seedOrderDerivativeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSeedOrderDerivative[ seedOrderDerivativeId=" + seedOrderDerivativeId + " ]";
    }

}
