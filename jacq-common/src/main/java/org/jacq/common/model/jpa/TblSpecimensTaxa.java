package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_taxa database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_taxa")
@NamedQuery(name="TblSpecimensTaxa.findAll", query="SELECT t FROM TblSpecimensTaxa t")
public class TblSpecimensTaxa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int specimens_tax_ID;
	private int specimen_ID;
	private int taxonID;

	public TblSpecimensTaxa() {
	}


	@Id
	public int getSpecimens_tax_ID() {
		return this.specimens_tax_ID;
	}

	public void setSpecimens_tax_ID(int specimens_tax_ID) {
		this.specimens_tax_ID = specimens_tax_ID;
	}


	public int getSpecimen_ID() {
		return this.specimen_ID;
	}

	public void setSpecimen_ID(int specimen_ID) {
		this.specimen_ID = specimen_ID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}

}