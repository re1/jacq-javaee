package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_lit_taxa database table.
 * 
 */
@Entity
@Table(name="tbl_lit_taxa")
@NamedQuery(name="TblLitTaxa.findAll", query="SELECT t FROM TblLitTaxa t")
public class TblLitTaxa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int lit_tax_ID;
	private int acc_taxon_ID;
	private String annotations;
	private int citationID;
	private byte etAl;
	private byte locked;
	private String source;
	private int source_citationID;
	private int source_person_ID;
	private int taxonID;
	private Timestamp timestamp;
	private int userID;

	public TblLitTaxa() {
	}


	@Id
	public int getLit_tax_ID() {
		return this.lit_tax_ID;
	}

	public void setLit_tax_ID(int lit_tax_ID) {
		this.lit_tax_ID = lit_tax_ID;
	}


	public int getAcc_taxon_ID() {
		return this.acc_taxon_ID;
	}

	public void setAcc_taxon_ID(int acc_taxon_ID) {
		this.acc_taxon_ID = acc_taxon_ID;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public int getCitationID() {
		return this.citationID;
	}

	public void setCitationID(int citationID) {
		this.citationID = citationID;
	}


	@Column(name="et_al")
	public byte getEtAl() {
		return this.etAl;
	}

	public void setEtAl(byte etAl) {
		this.etAl = etAl;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public int getSource_citationID() {
		return this.source_citationID;
	}

	public void setSource_citationID(int source_citationID) {
		this.source_citationID = source_citationID;
	}


	public int getSource_person_ID() {
		return this.source_person_ID;
	}

	public void setSource_person_ID(int source_person_ID) {
		this.source_person_ID = source_person_ID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}