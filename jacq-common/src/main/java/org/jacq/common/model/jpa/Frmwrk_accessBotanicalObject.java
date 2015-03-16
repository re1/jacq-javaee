package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the frmwrk_accessBotanicalObject database table.
 * 
 */
@Entity
@Table(name="frmwrk_accessBotanicalObject")
@NamedQuery(name="Frmwrk_accessBotanicalObject.findAll", query="SELECT f FROM Frmwrk_accessBotanicalObject f")
public class Frmwrk_accessBotanicalObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte allowDeny;
	private Frmwrk_AuthItem frmwrkAuthItem;
	private FrmwrkUser frmwrkUser;
	private TblLivingPlant tblLivingPlant;

	public Frmwrk_accessBotanicalObject() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false)
	public byte getAllowDeny() {
		return this.allowDeny;
	}

	public void setAllowDeny(byte allowDeny) {
		this.allowDeny = allowDeny;
	}


	//bi-directional many-to-one association to Frmwrk_AuthItem
	@ManyToOne
	@JoinColumn(name="AuthItem_name")
	public Frmwrk_AuthItem getFrmwrkAuthItem() {
		return this.frmwrkAuthItem;
	}

	public void setFrmwrkAuthItem(Frmwrk_AuthItem frmwrkAuthItem) {
		this.frmwrkAuthItem = frmwrkAuthItem;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="user_id")
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@ManyToOne
	@JoinColumn(name="botanical_object_id", nullable=false)
	public TblLivingPlant getTblLivingPlant() {
		return this.tblLivingPlant;
	}

	public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
		this.tblLivingPlant = tblLivingPlant;
	}

}