package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimen_identifier database table.
 * 
 */
@Entity
@Table(name="tbl_specimen_identifier")
@NamedQuery(name="TblSpecimenIdentifier.findAll", query="SELECT t FROM TblSpecimenIdentifier t")
public class TblSpecimenIdentifier implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblSpecimenIdentifierPK id;

	public TblSpecimenIdentifier() {
	}


	@EmbeddedId
	public TblSpecimenIdentifierPK getId() {
		return this.id;
	}

	public void setId(TblSpecimenIdentifierPK id) {
		this.id = id;
	}

}