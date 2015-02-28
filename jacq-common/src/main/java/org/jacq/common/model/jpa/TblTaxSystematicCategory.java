package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_systematic_categories database table.
 * 
 */
@Entity
@Table(name="tbl_tax_systematic_categories")
@NamedQuery(name="TblTaxSystematicCategory.findAll", query="SELECT t FROM TblTaxSystematicCategory t")
public class TblTaxSystematicCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	private int categoryID;
	private String catDescription;
	private String category;

	public TblTaxSystematicCategory() {
	}


	@Id
	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	@Column(name="cat_description")
	public String getCatDescription() {
		return this.catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}