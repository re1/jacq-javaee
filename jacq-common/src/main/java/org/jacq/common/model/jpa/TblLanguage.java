package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_languages database table.
 * 
 */
@Entity
@Table(name="tbl_languages")
@NamedQuery(name="TblLanguage.findAll", query="SELECT t FROM TblLanguage t")
public class TblLanguage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int languageID;
	private int language_familyID_fk;
	private String languageName;

	public TblLanguage() {
	}


	@Id
	public int getLanguageID() {
		return this.languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}


	public int getLanguage_familyID_fk() {
		return this.language_familyID_fk;
	}

	public void setLanguage_familyID_fk(int language_familyID_fk) {
		this.language_familyID_fk = language_familyID_fk;
	}


	@Column(name="language_name")
	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}