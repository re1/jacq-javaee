package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_scrutiny database table.
 * 
 */
@Entity
@Table(name="tbl_tax_scrutiny")
@NamedQuery(name="TblTaxScrutiny.findAll", query="SELECT t FROM TblTaxScrutiny t")
public class TblTaxScrutiny implements Serializable {
	private static final long serialVersionUID = 1L;
	private int scrutiny_ID;
	private String annotation;
	private int citationID;
	private String date;
	private int scrutiny_person_ID;
	private int taxonID;

	public TblTaxScrutiny() {
	}


	@Id
	public int getScrutiny_ID() {
		return this.scrutiny_ID;
	}

	public void setScrutiny_ID(int scrutiny_ID) {
		this.scrutiny_ID = scrutiny_ID;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public int getCitationID() {
		return this.citationID;
	}

	public void setCitationID(int citationID) {
		this.citationID = citationID;
	}


	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getScrutiny_person_ID() {
		return this.scrutiny_person_ID;
	}

	public void setScrutiny_person_ID(int scrutiny_person_ID) {
		this.scrutiny_person_ID = scrutiny_person_ID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}

}