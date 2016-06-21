/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "TblInventory.findAll", query = "SELECT t FROM TblInventory t"),
    @NamedQuery(name = "TblInventory.findByInventoryId", query = "SELECT t FROM TblInventory t WHERE t.inventoryId = :inventoryId"),
    @NamedQuery(name = "TblInventory.findByTimestamp", query = "SELECT t FROM TblInventory t WHERE t.timestamp = :timestamp")})
public class TblInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "inventory_type_id", referencedColumnName = "inventory_type_id")
    @ManyToOne(optional = false)
    private TblInventoryType inventoryTypeId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FrmwrkUser userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryId")
    private Collection<TblInventoryObject> tblInventoryObjectCollection;

    public TblInventory() {
    }

    public TblInventory(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public TblInventory(Integer inventoryId, Date timestamp) {
        this.inventoryId = inventoryId;
        this.timestamp = timestamp;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public TblInventoryType getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(TblInventoryType inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<TblInventoryObject> getTblInventoryObjectCollection() {
        return tblInventoryObjectCollection;
    }

    public void setTblInventoryObjectCollection(Collection<TblInventoryObject> tblInventoryObjectCollection) {
        this.tblInventoryObjectCollection = tblInventoryObjectCollection;
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
