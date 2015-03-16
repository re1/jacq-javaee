package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the frmwrk_AuthAssignment database table.
 * 
 */
@Entity
@Table(name="frmwrk_AuthAssignment")
@NamedQuery(name="Frmwrk_AuthAssignment.findAll", query="SELECT f FROM Frmwrk_AuthAssignment f")
public class Frmwrk_AuthAssignment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Frmwrk_AuthAssignmentPK id;
	private String bizrule;
	private String data;
	private Frmwrk_AuthItem frmwrkAuthItem;
	private FrmwrkUser frmwrkUser;

	public Frmwrk_AuthAssignment() {
	}


	@EmbeddedId
	public Frmwrk_AuthAssignmentPK getId() {
		return this.id;
	}

	public void setId(Frmwrk_AuthAssignmentPK id) {
		this.id = id;
	}


	@Lob
	public String getBizrule() {
		return this.bizrule;
	}

	public void setBizrule(String bizrule) {
		this.bizrule = bizrule;
	}


	@Lob
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}


	//bi-directional many-to-one association to Frmwrk_AuthItem
	@ManyToOne
	@JoinColumn(name="itemname", nullable=false, insertable=false, updatable=false)
	public Frmwrk_AuthItem getFrmwrkAuthItem() {
		return this.frmwrkAuthItem;
	}

	public void setFrmwrkAuthItem(Frmwrk_AuthItem frmwrkAuthItem) {
		this.frmwrkAuthItem = frmwrkAuthItem;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="userid", nullable=false, insertable=false, updatable=false)
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}

}