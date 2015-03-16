package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_specimen_identifier database table.
 * 
 */
@Embeddable
public class TblSpecimenIdentifierPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int specimenId;
	private int identifierId;

	public TblSpecimenIdentifierPK() {
	}

	@Column(name="specimen_id", insertable=false, updatable=false, unique=true, nullable=false)
	public int getSpecimenId() {
		return this.specimenId;
	}
	public void setSpecimenId(int specimenId) {
		this.specimenId = specimenId;
	}

	@Column(name="identifier_id", insertable=false, updatable=false, unique=true, nullable=false)
	public int getIdentifierId() {
		return this.identifierId;
	}
	public void setIdentifierId(int identifierId) {
		this.identifierId = identifierId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblSpecimenIdentifierPK)) {
			return false;
		}
		TblSpecimenIdentifierPK castOther = (TblSpecimenIdentifierPK)other;
		return 
			(this.specimenId == castOther.specimenId)
			&& (this.identifierId == castOther.identifierId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.specimenId;
		hash = hash * prime + this.identifierId;
		
		return hash;
	}
}