package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_types database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_types")
@NamedQuery(name="TblSpecimensType.findAll", query="SELECT t FROM TblSpecimensType t")
public class TblSpecimensType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int specimens_types_ID;
	private String annotations;
	private byte locked;
	private int specimenID;
	private int taxonID;
	private String typified_by_Person;
	private String typified_Date;
	private int typusID;

	public TblSpecimensType() {
	}


	@Id
	public int getSpecimens_types_ID() {
		return this.specimens_types_ID;
	}

	public void setSpecimens_types_ID(int specimens_types_ID) {
		this.specimens_types_ID = specimens_types_ID;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public int getSpecimenID() {
		return this.specimenID;
	}

	public void setSpecimenID(int specimenID) {
		this.specimenID = specimenID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	public String getTypified_by_Person() {
		return this.typified_by_Person;
	}

	public void setTypified_by_Person(String typified_by_Person) {
		this.typified_by_Person = typified_by_Person;
	}


	public String getTypified_Date() {
		return this.typified_Date;
	}

	public void setTypified_Date(String typified_Date) {
		this.typified_Date = typified_Date;
	}


	public int getTypusID() {
		return this.typusID;
	}

	public void setTypusID(int typusID) {
		this.typusID = typusID;
	}

}