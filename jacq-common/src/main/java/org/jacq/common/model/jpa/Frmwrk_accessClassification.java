package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the frmwrk_accessClassification database table.
 * 
 */
@Entity
@Table(name="frmwrk_accessClassification")
@NamedQuery(name="Frmwrk_accessClassification.findAll", query="SELECT f FROM Frmwrk_accessClassification f")
public class Frmwrk_accessClassification implements Serializable {
	private static final long serialVersionUID = 1L;
	private int accessClassificationId;
	private byte allowDeny;
	private int tax_syn_ID;
	private Frmwrk_AuthItem frmwrkAuthItem;
	private FrmwrkUser frmwrkUser;

	public Frmwrk_accessClassification() {
	}


	@Id
	@Column(name="access_classification_id", unique=true, nullable=false)
	public int getAccessClassificationId() {
		return this.accessClassificationId;
	}

	public void setAccessClassificationId(int accessClassificationId) {
		this.accessClassificationId = accessClassificationId;
	}


	@Column(nullable=false)
	public byte getAllowDeny() {
		return this.allowDeny;
	}

	public void setAllowDeny(byte allowDeny) {
		this.allowDeny = allowDeny;
	}


	@Column(nullable=false)
	public int getTax_syn_ID() {
		return this.tax_syn_ID;
	}

	public void setTax_syn_ID(int tax_syn_ID) {
		this.tax_syn_ID = tax_syn_ID;
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

}