package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_tax_index database table.
 * 
 */
@Embeddable
public class TblTaxIndexPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int taxonID;
	private int citationID;

	public TblTaxIndexPK() {
	}

	public int getTaxonID() {
		return this.taxonID;
	}
	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}

	public int getCitationID() {
		return this.citationID;
	}
	public void setCitationID(int citationID) {
		this.citationID = citationID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblTaxIndexPK)) {
			return false;
		}
		TblTaxIndexPK castOther = (TblTaxIndexPK)other;
		return 
			(this.taxonID == castOther.taxonID)
			&& (this.citationID == castOther.citationID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taxonID;
		hash = hash * prime + this.citationID;
		
		return hash;
	}
}