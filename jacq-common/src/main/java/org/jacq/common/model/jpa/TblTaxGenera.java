package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_genera database table.
 * 
 */
@Entity
@Table(name="tbl_tax_genera")
@NamedQuery(name="TblTaxGenera.findAll", query="SELECT t FROM TblTaxGenera t")
public class TblTaxGenera implements Serializable {
	private static final long serialVersionUID = 1L;
	private int genID;
	private byte accepted;
	private int authorID;
	private int dallaTorreIDs;
	private String dallaTorreZusatzIDs;
	private byte external;
	private int externalID;
	private int familyID;
	private int fk_taxonID;
	private int genID_inc0406;
	private int genID_old;
	private String genus;
	private String hybrid;
	private byte locked;
	private String remarks;

	public TblTaxGenera() {
	}


	@Id
	public int getGenID() {
		return this.genID;
	}

	public void setGenID(int genID) {
		this.genID = genID;
	}


	public byte getAccepted() {
		return this.accepted;
	}

	public void setAccepted(byte accepted) {
		this.accepted = accepted;
	}


	public int getAuthorID() {
		return this.authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	@Column(name="DallaTorreIDs")
	public int getDallaTorreIDs() {
		return this.dallaTorreIDs;
	}

	public void setDallaTorreIDs(int dallaTorreIDs) {
		this.dallaTorreIDs = dallaTorreIDs;
	}


	@Column(name="DallaTorreZusatzIDs")
	public String getDallaTorreZusatzIDs() {
		return this.dallaTorreZusatzIDs;
	}

	public void setDallaTorreZusatzIDs(String dallaTorreZusatzIDs) {
		this.dallaTorreZusatzIDs = dallaTorreZusatzIDs;
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


	public int getFamilyID() {
		return this.familyID;
	}

	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}


	public int getFk_taxonID() {
		return this.fk_taxonID;
	}

	public void setFk_taxonID(int fk_taxonID) {
		this.fk_taxonID = fk_taxonID;
	}


	public int getGenID_inc0406() {
		return this.genID_inc0406;
	}

	public void setGenID_inc0406(int genID_inc0406) {
		this.genID_inc0406 = genID_inc0406;
	}


	public int getGenID_old() {
		return this.genID_old;
	}

	public void setGenID_old(int genID_old) {
		this.genID_old = genID_old;
	}


	public String getGenus() {
		return this.genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}


	public String getHybrid() {
		return this.hybrid;
	}

	public void setHybrid(String hybrid) {
		this.hybrid = hybrid;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Lob
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}