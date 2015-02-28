package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_labels database table.
 * 
 */
@Embeddable
public class TblLabelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int userID;
	private int specimen_ID;

	public TblLabelPK() {
	}

	public int getUserID() {
		return this.userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getSpecimen_ID() {
		return this.specimen_ID;
	}
	public void setSpecimen_ID(int specimen_ID) {
		this.specimen_ID = specimen_ID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblLabelPK)) {
			return false;
		}
		TblLabelPK castOther = (TblLabelPK)other;
		return 
			(this.userID == castOther.userID)
			&& (this.specimen_ID == castOther.specimen_ID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userID;
		hash = hash * prime + this.specimen_ID;
		
		return hash;
	}
}