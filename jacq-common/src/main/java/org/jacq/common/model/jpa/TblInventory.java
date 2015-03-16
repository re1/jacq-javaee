package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_inventory database table.
 * 
 */
@Entity
@Table(name="tbl_inventory")
@NamedQuery(name="TblInventory.findAll", query="SELECT t FROM TblInventory t")
public class TblInventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private int inventoryId;
	private Timestamp timestamp;
	private FrmwrkUser frmwrkUser;
	private TblInventoryType tblInventoryType;
	private List<TblInventoryObject> tblInventoryObjects;

	public TblInventory() {
	}


	@Id
	@Column(name="inventory_id", unique=true, nullable=false)
	public int getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}


	//bi-directional many-to-one association to TblInventoryType
	@ManyToOne
	@JoinColumn(name="inventory_type_id", nullable=false)
	public TblInventoryType getTblInventoryType() {
		return this.tblInventoryType;
	}

	public void setTblInventoryType(TblInventoryType tblInventoryType) {
		this.tblInventoryType = tblInventoryType;
	}


	//bi-directional many-to-one association to TblInventoryObject
	@OneToMany(mappedBy="tblInventory")
	public List<TblInventoryObject> getTblInventoryObjects() {
		return this.tblInventoryObjects;
	}

	public void setTblInventoryObjects(List<TblInventoryObject> tblInventoryObjects) {
		this.tblInventoryObjects = tblInventoryObjects;
	}

	public TblInventoryObject addTblInventoryObject(TblInventoryObject tblInventoryObject) {
		getTblInventoryObjects().add(tblInventoryObject);
		tblInventoryObject.setTblInventory(this);

		return tblInventoryObject;
	}

	public TblInventoryObject removeTblInventoryObject(TblInventoryObject tblInventoryObject) {
		getTblInventoryObjects().remove(tblInventoryObject);
		tblInventoryObject.setTblInventory(null);

		return tblInventoryObject;
	}

}