package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_nom_common_names_links database table.
 * 
 */
@Entity
@Table(name="tbl_nom_common_names_links")
@NamedQuery(name="TblNomCommonNamesLink.findAll", query="SELECT t FROM TblNomCommonNamesLink t")
public class TblNomCommonNamesLink implements Serializable {
	private static final long serialVersionUID = 1L;
	private int common_name_links_ID;
	private String annotation;
	private int citationID_fk;
	private int common_name_ID_fk;
	private int languageID_fk;
	private byte locked;
	private int taxonID_fk;

	public TblNomCommonNamesLink() {
	}


	@Id
	public int getCommon_name_links_ID() {
		return this.common_name_links_ID;
	}

	public void setCommon_name_links_ID(int common_name_links_ID) {
		this.common_name_links_ID = common_name_links_ID;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public int getCitationID_fk() {
		return this.citationID_fk;
	}

	public void setCitationID_fk(int citationID_fk) {
		this.citationID_fk = citationID_fk;
	}


	public int getCommon_name_ID_fk() {
		return this.common_name_ID_fk;
	}

	public void setCommon_name_ID_fk(int common_name_ID_fk) {
		this.common_name_ID_fk = common_name_ID_fk;
	}


	public int getLanguageID_fk() {
		return this.languageID_fk;
	}

	public void setLanguageID_fk(int languageID_fk) {
		this.languageID_fk = languageID_fk;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public int getTaxonID_fk() {
		return this.taxonID_fk;
	}

	public void setTaxonID_fk(int taxonID_fk) {
		this.taxonID_fk = taxonID_fk;
	}

}