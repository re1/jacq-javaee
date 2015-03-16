package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_inventory_type database table.
 * 
 */
@Entity
@Table(name="tbl_inventory_type")
@NamedQuery(name="TblInventoryType.findAll", query="SELECT t FROM TblInventoryType t")
public class TblInventoryType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int inventoryTypeId;
	private Timestamp timestamp;
	private String type;
	private List<TblInventory> tblInventories;

	public TblInventoryType() {
	}


	@Id
	@Column(name="inventory_type_id", unique=true, nullable=false)
	public int getInventoryTypeId() {
		return this.inventoryTypeId;
	}

	public void setInventoryTypeId(int inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	@Column(nullable=false, length=45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblInventory
	@OneToMany(mappedBy="tblInventoryType")
	public List<TblInventory> getTblInventories() {
		return this.tblInventories;
	}

	public void setTblInventories(List<TblInventory> tblInventories) {
		this.tblInventories = tblInventories;
	}

	public TblInventory addTblInventory(TblInventory tblInventory) {
		getTblInventories().add(tblInventory);
		tblInventory.setTblInventoryType(this);

		return tblInventory;
	}

	public TblInventory removeTblInventory(TblInventory tblInventory) {
		getTblInventories().remove(tblInventory);
		tblInventory.setTblInventoryType(null);

		return tblInventory;
	}

}