package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_classification database table.
 * 
 */
@Entity
@Table(name="tbl_tax_classification")
@NamedQuery(name="TblTaxClassification.findAll", query="SELECT t FROM TblTaxClassification t")
public class TblTaxClassification implements Serializable {
	private static final long serialVersionUID = 1L;
	private int classificationId;
	private String number;
	private int order;
	private int parent_taxonID;
	private int tax_syn_ID;

	public TblTaxClassification() {
	}


	@Id
	@Column(name="classification_id")
	public int getClassificationId() {
		return this.classificationId;
	}

	public void setClassificationId(int classificationId) {
		this.classificationId = classificationId;
	}


	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}


	public int getParent_taxonID() {
		return this.parent_taxonID;
	}

	public void setParent_taxonID(int parent_taxonID) {
		this.parent_taxonID = parent_taxonID;
	}


	public int getTax_syn_ID() {
		return this.tax_syn_ID;
	}

	public void setTax_syn_ID(int tax_syn_ID) {
		this.tax_syn_ID = tax_syn_ID;
	}

}