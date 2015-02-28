package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_hybrids database table.
 * 
 */
@Entity
@Table(name="tbl_tax_hybrids")
@NamedQuery(name="TblTaxHybrid.findAll", query="SELECT t FROM TblTaxHybrid t")
public class TblTaxHybrid implements Serializable {
	private static final long serialVersionUID = 1L;
	private int hybrid_ID;
	private int parent_1_ID;
	private int parent_2_ID;
	private int parent_3_ID;
	private int taxon_ID_fk;

	public TblTaxHybrid() {
	}


	@Id
	public int getHybrid_ID() {
		return this.hybrid_ID;
	}

	public void setHybrid_ID(int hybrid_ID) {
		this.hybrid_ID = hybrid_ID;
	}


	public int getParent_1_ID() {
		return this.parent_1_ID;
	}

	public void setParent_1_ID(int parent_1_ID) {
		this.parent_1_ID = parent_1_ID;
	}


	public int getParent_2_ID() {
		return this.parent_2_ID;
	}

	public void setParent_2_ID(int parent_2_ID) {
		this.parent_2_ID = parent_2_ID;
	}


	public int getParent_3_ID() {
		return this.parent_3_ID;
	}

	public void setParent_3_ID(int parent_3_ID) {
		this.parent_3_ID = parent_3_ID;
	}


	public int getTaxon_ID_fk() {
		return this.taxon_ID_fk;
	}

	public void setTaxon_ID_fk(int taxon_ID_fk) {
		this.taxon_ID_fk = taxon_ID_fk;
	}

}