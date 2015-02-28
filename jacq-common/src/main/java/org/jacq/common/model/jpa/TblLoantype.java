package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_loantype database table.
 * 
 */
@Entity
@Table(name="tbl_loantype")
@NamedQuery(name="TblLoantype.findAll", query="SELECT t FROM TblLoantype t")
public class TblLoantype implements Serializable {
	private static final long serialVersionUID = 1L;
	private int loantypeID;
	private String loantypeDeutsch;
	private String loantypeEnglish;
	private String loantypeEspanol;

	public TblLoantype() {
	}


	@Id
	public int getLoantypeID() {
		return this.loantypeID;
	}

	public void setLoantypeID(int loantypeID) {
		this.loantypeID = loantypeID;
	}


	@Column(name="loantype_deutsch")
	public String getLoantypeDeutsch() {
		return this.loantypeDeutsch;
	}

	public void setLoantypeDeutsch(String loantypeDeutsch) {
		this.loantypeDeutsch = loantypeDeutsch;
	}


	@Column(name="loantype_english")
	public String getLoantypeEnglish() {
		return this.loantypeEnglish;
	}

	public void setLoantypeEnglish(String loantypeEnglish) {
		this.loantypeEnglish = loantypeEnglish;
	}


	@Column(name="loantype_espanol")
	public String getLoantypeEspanol() {
		return this.loantypeEspanol;
	}

	public void setLoantypeEspanol(String loantypeEspanol) {
		this.loantypeEspanol = loantypeEspanol;
	}

}