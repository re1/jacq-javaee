package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_relationships database table.
 * 
 */
@Entity
@Table(name="tbl_tax_relationships")
@NamedQuery(name="TblTaxRelationship.findAll", query="SELECT t FROM TblTaxRelationship t")
public class TblTaxRelationship implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tax_relation_ID;
	private String explanation;
	private byte locked;
	private String relationTerm;

	public TblTaxRelationship() {
	}


	@Id
	public int getTax_relation_ID() {
		return this.tax_relation_ID;
	}

	public void setTax_relation_ID(int tax_relation_ID) {
		this.tax_relation_ID = tax_relation_ID;
	}


	@Lob
	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="relation_term")
	public String getRelationTerm() {
		return this.relationTerm;
	}

	public void setRelationTerm(String relationTerm) {
		this.relationTerm = relationTerm;
	}

}