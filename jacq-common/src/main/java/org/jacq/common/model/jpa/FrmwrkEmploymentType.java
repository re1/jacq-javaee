package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frmwrk_employment_type database table.
 * 
 */
@Entity
@Table(name="frmwrk_employment_type")
@NamedQuery(name="FrmwrkEmploymentType.findAll", query="SELECT f FROM FrmwrkEmploymentType f")
public class FrmwrkEmploymentType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int employmentTypeId;
	private String type;
	private List<FrmwrkUser> frmwrkUsers;

	public FrmwrkEmploymentType() {
	}


	@Id
	@Column(name="employment_type_id", unique=true, nullable=false)
	public int getEmploymentTypeId() {
		return this.employmentTypeId;
	}

	public void setEmploymentTypeId(int employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}


	@Column(nullable=false, length=45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@OneToMany(mappedBy="frmwrkEmploymentType")
	public List<FrmwrkUser> getFrmwrkUsers() {
		return this.frmwrkUsers;
	}

	public void setFrmwrkUsers(List<FrmwrkUser> frmwrkUsers) {
		this.frmwrkUsers = frmwrkUsers;
	}

	public FrmwrkUser addFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().add(frmwrkUser);
		frmwrkUser.setFrmwrkEmploymentType(this);

		return frmwrkUser;
	}

	public FrmwrkUser removeFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().remove(frmwrkUser);
		frmwrkUser.setFrmwrkEmploymentType(null);

		return frmwrkUser;
	}

}