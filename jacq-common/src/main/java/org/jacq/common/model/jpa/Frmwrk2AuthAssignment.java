package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the frmwrk2_auth_assignment database table.
 * 
 */
@Entity
@Table(name="frmwrk2_auth_assignment")
@NamedQuery(name="Frmwrk2AuthAssignment.findAll", query="SELECT f FROM Frmwrk2AuthAssignment f")
public class Frmwrk2AuthAssignment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Frmwrk2AuthAssignmentPK id;
	private int createdAt;
	private Frmwrk2AuthItem frmwrk2AuthItem;
	private FrmwrkUser frmwrkUser;

	public Frmwrk2AuthAssignment() {
	}


	@EmbeddedId
	public Frmwrk2AuthAssignmentPK getId() {
		return this.id;
	}

	public void setId(Frmwrk2AuthAssignmentPK id) {
		this.id = id;
	}


	@Column(name="created_at")
	public int getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}


	//bi-directional many-to-one association to Frmwrk2AuthItem
	@ManyToOne
	@JoinColumn(name="item_name", nullable=false, insertable=false, updatable=false)
	public Frmwrk2AuthItem getFrmwrk2AuthItem() {
		return this.frmwrk2AuthItem;
	}

	public void setFrmwrk2AuthItem(Frmwrk2AuthItem frmwrk2AuthItem) {
		this.frmwrk2AuthItem = frmwrk2AuthItem;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}

}