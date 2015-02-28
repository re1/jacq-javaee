package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_typecollections database table.
 * 
 */
@Entity
@Table(name="tbl_tax_typecollections")
@NamedQuery(name="TblTaxTypecollection.findAll", query="SELECT t FROM TblTaxTypecollection t")
public class TblTaxTypecollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private int typecollID;
	private String alternateNumber;
	private String annotation;
	private String date;
	private String duplicates;
	private int legNr;
	private byte locked;
	private int sammler_2ID;
	private int sammlerID;
	private String series;
	private int taxonID;
	private int typusID;

	public TblTaxTypecollection() {
	}


	@Id
	public int getTypecollID() {
		return this.typecollID;
	}

	public void setTypecollID(int typecollID) {
		this.typecollID = typecollID;
	}


	@Column(name="alternate_number")
	public String getAlternateNumber() {
		return this.alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getDuplicates() {
		return this.duplicates;
	}

	public void setDuplicates(String duplicates) {
		this.duplicates = duplicates;
	}


	@Column(name="leg_nr")
	public int getLegNr() {
		return this.legNr;
	}

	public void setLegNr(int legNr) {
		this.legNr = legNr;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="Sammler_2ID")
	public int getSammler_2ID() {
		return this.sammler_2ID;
	}

	public void setSammler_2ID(int sammler_2ID) {
		this.sammler_2ID = sammler_2ID;
	}


	@Column(name="SammlerID")
	public int getSammlerID() {
		return this.sammlerID;
	}

	public void setSammlerID(int sammlerID) {
		this.sammlerID = sammlerID;
	}


	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	public int getTypusID() {
		return this.typusID;
	}

	public void setTypusID(int typusID) {
		this.typusID = typusID;
	}

}