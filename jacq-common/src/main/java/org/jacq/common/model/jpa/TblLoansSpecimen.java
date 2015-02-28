package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_loans_specimens database table.
 * 
 */
@Entity
@Table(name="tbl_loans_specimens")
@NamedQuery(name="TblLoansSpecimen.findAll", query="SELECT t FROM TblLoansSpecimen t")
public class TblLoansSpecimen implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblLoansSpecimenPK id;
	private String transaction;

	public TblLoansSpecimen() {
	}


	@EmbeddedId
	public TblLoansSpecimenPK getId() {
		return this.id;
	}

	public void setId(TblLoansSpecimenPK id) {
		this.id = id;
	}


	public String getTransaction() {
		return this.transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

}