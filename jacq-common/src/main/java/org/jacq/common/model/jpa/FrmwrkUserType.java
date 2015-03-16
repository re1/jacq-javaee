package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frmwrk_user_type database table.
 * 
 */
@Entity
@Table(name="frmwrk_user_type")
@NamedQuery(name="FrmwrkUserType.findAll", query="SELECT f FROM FrmwrkUserType f")
public class FrmwrkUserType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userTypeId;
	private String type;
	private List<FrmwrkUser> frmwrkUsers;

	public FrmwrkUserType() {
	}


	@Id
	@Column(name="user_type_id", unique=true, nullable=false)
	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}


	@Column(nullable=false, length=45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@OneToMany(mappedBy="frmwrkUserType")
	public List<FrmwrkUser> getFrmwrkUsers() {
		return this.frmwrkUsers;
	}

	public void setFrmwrkUsers(List<FrmwrkUser> frmwrkUsers) {
		this.frmwrkUsers = frmwrkUsers;
	}

	public FrmwrkUser addFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().add(frmwrkUser);
		frmwrkUser.setFrmwrkUserType(this);

		return frmwrkUser;
	}

	public FrmwrkUser removeFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().remove(frmwrkUser);
		frmwrkUser.setFrmwrkUserType(null);

		return frmwrkUser;
	}

}