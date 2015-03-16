package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the frmwrk2_auth_assignment database table.
 * 
 */
@Embeddable
public class Frmwrk2AuthAssignmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String itemName;
	private int userId;

	public Frmwrk2AuthAssignmentPK() {
	}

	@Column(name="item_name", insertable=false, updatable=false, unique=true, nullable=false, length=64)
	public String getItemName() {
		return this.itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name="user_id", insertable=false, updatable=false, unique=true, nullable=false)
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Frmwrk2AuthAssignmentPK)) {
			return false;
		}
		Frmwrk2AuthAssignmentPK castOther = (Frmwrk2AuthAssignmentPK)other;
		return 
			this.itemName.equals(castOther.itemName)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itemName.hashCode();
		hash = hash * prime + this.userId;
		
		return hash;
	}
}