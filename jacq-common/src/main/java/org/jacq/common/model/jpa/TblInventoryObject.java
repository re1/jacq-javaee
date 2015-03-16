package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_inventory_object database table.
 * 
 */
@Entity
@Table(name="tbl_inventory_object")
@NamedQuery(name="TblInventoryObject.findAll", query="SELECT t FROM TblInventoryObject t")
public class TblInventoryObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int inventoryObjectId;
	private String message;
	private Timestamp timestamp;
	private TblBotanicalObject tblBotanicalObject;
	private TblInventory tblInventory;

	public TblInventoryObject() {
	}


	@Id
	@Column(name="inventory_object_id", unique=true, nullable=false)
	public int getInventoryObjectId() {
		return this.inventoryObjectId;
	}

	public void setInventoryObjectId(int inventoryObjectId) {
		this.inventoryObjectId = inventoryObjectId;
	}


	@Lob
	@Column(nullable=false)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@ManyToOne
	@JoinColumn(name="botanical_object_id")
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblInventory
	@ManyToOne
	@JoinColumn(name="inventory_id", nullable=false)
	public TblInventory getTblInventory() {
		return this.tblInventory;
	}

	public void setTblInventory(TblInventory tblInventory) {
		this.tblInventory = tblInventory;
	}

}