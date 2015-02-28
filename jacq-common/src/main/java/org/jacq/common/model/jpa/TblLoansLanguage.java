package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_loans_language database table.
 * 
 */
@Entity
@Table(name="tbl_loans_language")
@NamedQuery(name="TblLoansLanguage.findAll", query="SELECT t FROM TblLoansLanguage t")
public class TblLoansLanguage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int languageID;
	private String language;

	public TblLoansLanguage() {
	}


	@Id
	public int getLanguageID() {
		return this.languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}


	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}