package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the frmwrk_accessOrganisation database table.
 * 
 */
@Entity
@Table(name="frmwrk_accessOrganisation")
@NamedQuery(name="Frmwrk_accessOrganisation.findAll", query="SELECT f FROM Frmwrk_accessOrganisation f")
public class Frmwrk_accessOrganisation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte allowDeny;
	private Frmwrk_AuthItem frmwrkAuthItem;
	private FrmwrkUser frmwrkUser;
	private TblOrganisation tblOrganisation;

	public Frmwrk_accessOrganisation() {
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


	//bi-directional many-to-one association to TblOrganisation
	@ManyToOne
	@JoinColumn(name="organisation_id", nullable=false)
	public TblOrganisation getTblOrganisation() {
		return this.tblOrganisation;
	}

	public void setTblOrganisation(TblOrganisation tblOrganisation) {
		this.tblOrganisation = tblOrganisation;
	}

}