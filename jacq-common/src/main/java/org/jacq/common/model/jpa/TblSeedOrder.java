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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_seed_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSeedOrder.findAll", query = "SELECT t FROM TblSeedOrder t")
    , @NamedQuery(name = "TblSeedOrder.findBySeedOrderId", query = "SELECT t FROM TblSeedOrder t WHERE t.seedOrderId = :seedOrderId")
    , @NamedQuery(name = "TblSeedOrder.findByOrderDate", query = "SELECT t FROM TblSeedOrder t WHERE t.orderDate = :orderDate")
    , @NamedQuery(name = "TblSeedOrder.findByComplete", query = "SELECT t FROM TblSeedOrder t WHERE t.complete = :complete")})
public class TblSeedOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seed_order_id")
    private Long seedOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "complete")
    private boolean complete;
    @JoinTable(name = "tbl_seed_order_derivative", joinColumns = {
        @JoinColumn(name = "seed_order_id", referencedColumnName = "seed_order_id")}, inverseJoinColumns = {
        @JoinColumn(name = "derivative_id", referencedColumnName = "derivative_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TblDerivative> tblDerivativeList;
    @JoinColumn(name = "sender_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FrmwrkUser senderUserId;
    @JoinColumn(name = "sender_organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation senderOrganisationId;
    @JoinColumn(name = "orderer_organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation ordererOrganisationId;

    public TblSeedOrder() {
    }

    public TblSeedOrder(Long seedOrderId) {
        this.seedOrderId = seedOrderId;
    }

    public TblSeedOrder(Long seedOrderId, Date orderDate, boolean complete) {
        this.seedOrderId = seedOrderId;
        this.orderDate = orderDate;
        this.complete = complete;
    }

    public Long getSeedOrderId() {
        return seedOrderId;
    }

    public void setSeedOrderId(Long seedOrderId) {
        this.seedOrderId = seedOrderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @XmlTransient
    public List<TblDerivative> getTblDerivativeList() {
        return tblDerivativeList;
    }

    public void setTblDerivativeList(List<TblDerivative> tblDerivativeList) {
        this.tblDerivativeList = tblDerivativeList;
    }

    public FrmwrkUser getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(FrmwrkUser senderUserId) {
        this.senderUserId = senderUserId;
    }

    public TblOrganisation getSenderOrganisationId() {
        return senderOrganisationId;
    }

    public void setSenderOrganisationId(TblOrganisation senderOrganisationId) {
        this.senderOrganisationId = senderOrganisationId;
    }

    public TblOrganisation getOrdererOrganisationId() {
        return ordererOrganisationId;
    }

    public void setOrdererOrganisationId(TblOrganisation ordererOrganisationId) {
        this.ordererOrganisationId = ordererOrganisationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seedOrderId != null ? seedOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSeedOrder)) {
            return false;
        }
        TblSeedOrder other = (TblSeedOrder) object;
        if ((this.seedOrderId == null && other.seedOrderId != null) || (this.seedOrderId != null && !this.seedOrderId.equals(other.seedOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSeedOrder[ seedOrderId=" + seedOrderId + " ]";
    }

}
