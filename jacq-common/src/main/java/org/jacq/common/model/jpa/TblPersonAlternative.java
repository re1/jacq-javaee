package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_person_alternative database table.
 * 
 */
@Entity
@Table(name="tbl_person_alternative")
@NamedQuery(name="TblPersonAlternative.findAll", query="SELECT t FROM TblPersonAlternative t")
public class TblPersonAlternative implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_alternative_ID;
	private String pAlternative;
	private int person_ID;

	public TblPersonAlternative() {
	}


	@Id
	public int getPerson_alternative_ID() {
		return this.person_alternative_ID;
	}

	public void setPerson_alternative_ID(int person_alternative_ID) {
		this.person_alternative_ID = person_alternative_ID;
	}


	@Column(name="p_alternative")
	public String getPAlternative() {
		return this.pAlternative;
	}

	public void setPAlternative(String pAlternative) {
		this.pAlternative = pAlternative;
	}


	public int getPerson_ID() {
		return this.person_ID;
	}

	public void setPerson_ID(int person_ID) {
		this.person_ID = person_ID;
	}

}