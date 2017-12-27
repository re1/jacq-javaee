/*
 * Copyright 2017 wkoller.
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventory.findAll", query = "SELECT t FROM TblInventory t")
    , @NamedQuery(name = "TblInventory.findByInventoryId", query = "SELECT t FROM TblInventory t WHERE t.inventoryId = :inventoryId")
    , @NamedQuery(name = "TblInventory.findByTimestamp", query = "SELECT t FROM TblInventory t WHERE t.timestamp = :timestamp")})
public class TblInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_id")
    private Long inventoryId;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryId")
    private List<TblInventoryObject> tblInventoryObjectList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FrmwrkUser userId;
    @JoinColumn(name = "inventory_type_id", referencedColumnName = "inventory_type_id")
    @ManyToOne(optional = false)
    private TblInventoryType inventoryTypeId;

    public TblInventory() {
    }

    public TblInventory(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public List<TblInventoryObject> getTblInventoryObjectList() {
        return tblInventoryObjectList;
    }

    public void setTblInventoryObjectList(List<TblInventoryObject> tblInventoryObjectList) {
        this.tblInventoryObjectList = tblInventoryObjectList;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    public TblInventoryType getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(TblInventoryType inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryId != null ? inventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblInventory)) {
            return false;
        }
        TblInventory other = (TblInventory) object;
        if ((this.inventoryId == null && other.inventoryId != null) || (this.inventoryId != null && !this.inventoryId.equals(other.inventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblInventory[ inventoryId=" + inventoryId + " ]";
    }

}
