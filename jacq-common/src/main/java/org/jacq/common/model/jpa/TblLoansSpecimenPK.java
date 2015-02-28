package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_loans_specimens database table.
 * 
 */
@Embeddable
public class TblLoansSpecimenPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wuReference;
	private int specimenId;

	public TblLoansSpecimenPK() {
	}

	@Column(name="wu_reference")
	public String getWuReference() {
		return this.wuReference;
	}
	public void setWuReference(String wuReference) {
		this.wuReference = wuReference;
	}

	@Column(name="specimen_id")
	public int getSpecimenId() {
		return this.specimenId;
	}
	public void setSpecimenId(int specimenId) {
		this.specimenId = specimenId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblLoansSpecimenPK)) {
			return false;
		}
		TblLoansSpecimenPK castOther = (TblLoansSpecimenPK)other;
		return 
			this.wuReference.equals(castOther.wuReference)
			&& (this.specimenId == castOther.specimenId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.wuReference.hashCode();
		hash = hash * prime + this.specimenId;
		
		return hash;
	}
}