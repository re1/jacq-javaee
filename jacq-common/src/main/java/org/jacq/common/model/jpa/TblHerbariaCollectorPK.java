package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_herbaria_collectors database table.
 * 
 */
@Embeddable
public class TblHerbariaCollectorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int person_IDfk;
	private int herbarium_IDfk;

	public TblHerbariaCollectorPK() {
	}

	public int getPerson_IDfk() {
		return this.person_IDfk;
	}
	public void setPerson_IDfk(int person_IDfk) {
		this.person_IDfk = person_IDfk;
	}

	public int getHerbarium_IDfk() {
		return this.herbarium_IDfk;
	}
	public void setHerbarium_IDfk(int herbarium_IDfk) {
		this.herbarium_IDfk = herbarium_IDfk;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblHerbariaCollectorPK)) {
			return false;
		}
		TblHerbariaCollectorPK castOther = (TblHerbariaCollectorPK)other;
		return 
			(this.person_IDfk == castOther.person_IDfk)
			&& (this.herbarium_IDfk == castOther.herbarium_IDfk);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.person_IDfk;
		hash = hash * prime + this.herbarium_IDfk;
		
		return hash;
	}
}