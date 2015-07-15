/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_inventory_object")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventoryObject.findAll", query = "SELECT t FROM TblInventoryObject t"),
    @NamedQuery(name = "TblInventoryObject.findByInventoryObjectId", query = "SELECT t FROM TblInventoryObject t WHERE t.inventoryObjectId = :inventoryObjectId"),
    @NamedQuery(name = "TblInventoryObject.findByTimestamp", query = "SELECT t FROM TblInventoryObject t WHERE t.timestamp = :timestamp")})
public class TblInventoryObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_object_id")
    private Integer inventoryObjectId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id")
    @ManyToOne(optional = false)
    private TblInventory inventoryId;
    @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")
    @ManyToOne
    private TblBotanicalObject botanicalObjectId;

    public TblInventoryObject() {
    }

    public TblInventoryObject(Integer inventoryObjectId) {
        this.inventoryObjectId = inventoryObjectId;
    }

    public TblInventoryObject(Integer inventoryObjectId, String message, Date timestamp) {
        this.inventoryObjectId = inventoryObjectId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getInventoryObjectId() {
        return inventoryObjectId;
    }

    public void setInventoryObjectId(Integer inventoryObjectId) {
        this.inventoryObjectId = inventoryObjectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public TblInventory getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(TblInventory inventoryId) {
        this.inventoryId = inventoryId;
    }

    public TblBotanicalObject getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(TblBotanicalObject botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryObjectId != null ? inventoryObjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblInventoryObject)) {
            return false;
        }
        TblInventoryObject other = (TblInventoryObject) object;
        if ((this.inventoryObjectId == null && other.inventoryObjectId != null) || (this.inventoryObjectId != null && !this.inventoryObjectId.equals(other.inventoryObjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblInventoryObject[ inventoryObjectId=" + inventoryObjectId + " ]";
    }

}
