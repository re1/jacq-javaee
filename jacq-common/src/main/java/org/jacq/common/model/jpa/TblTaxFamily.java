package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_families database table.
 * 
 */
@Entity
@Table(name="tbl_tax_families")
@NamedQuery(name="TblTaxFamily.findAll", query="SELECT t FROM TblTaxFamily t")
public class TblTaxFamily implements Serializable {
	private static final long serialVersionUID = 1L;
	private int familyID;
	private int authorID;
	private int categoryID;
	private byte external;
	private int externalID;
	private String family;
	private String familyAlt;
	private byte locked;

	public TblTaxFamily() {
	}


	@Id
	public int getFamilyID() {
		return this.familyID;
	}

	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}


	public int getAuthorID() {
		return this.authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	public byte getExternal() {
		return this.external;
	}

	public void setExternal(byte external) {
		this.external = external;
	}


	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}


	@Column(name="family_alt")
	public String getFamilyAlt() {
		return this.familyAlt;
	}

	public void setFamilyAlt(String familyAlt) {
		this.familyAlt = familyAlt;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

}