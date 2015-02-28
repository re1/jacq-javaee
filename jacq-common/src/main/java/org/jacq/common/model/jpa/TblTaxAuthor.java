package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_authors database table.
 * 
 */
@Entity
@Table(name="tbl_tax_authors")
@NamedQuery(name="TblTaxAuthor.findAll", query="SELECT t FROM TblTaxAuthor t")
public class TblTaxAuthor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int authorID;
	private String author;
	private String brummit_Powell_full;
	private byte external;
	private int externalID;
	private byte locked;

	public TblTaxAuthor() {
	}


	@Id
	public int getAuthorID() {
		return this.authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	@Column(name="Brummit_Powell_full")
	public String getBrummit_Powell_full() {
		return this.brummit_Powell_full;
	}

	public void setBrummit_Powell_full(String brummit_Powell_full) {
		this.brummit_Powell_full = brummit_Powell_full;
	}


	public byte getExternal() {
		return this.external;
	}

	public void setExternal(byte external) {
		this.external = external;
	}


	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

}