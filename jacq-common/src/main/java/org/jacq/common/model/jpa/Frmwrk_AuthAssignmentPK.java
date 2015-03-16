package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the frmwrk_AuthAssignment database table.
 * 
 */
@Embeddable
public class Frmwrk_AuthAssignmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String itemname;
	private int userid;

	public Frmwrk_AuthAssignmentPK() {
	}

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=64)
	public String getItemname() {
		return this.itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	public int getUserid() {
		return this.userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Frmwrk_AuthAssignmentPK)) {
			return false;
		}
		Frmwrk_AuthAssignmentPK castOther = (Frmwrk_AuthAssignmentPK)other;
		return 
			this.itemname.equals(castOther.itemname)
			&& (this.userid == castOther.userid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itemname.hashCode();
		hash = hash * prime + this.userid;
		
		return hash;
	}
}