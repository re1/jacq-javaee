package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_orders database table.
 * 
 */
@Entity
@Table(name="tbl_tax_orders")
@NamedQuery(name="TblTaxOrder.findAll", query="SELECT t FROM TblTaxOrder t")
public class TblTaxOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderID;
	private int authorID;
	private int categoryID;
	private byte locked;
	private String order;

	public TblTaxOrder() {
	}


	@Id
	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public int getAuthorID() {
		return this.authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}