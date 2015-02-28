package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_publishers database table.
 * 
 */
@Entity
@Table(name="tbl_lit_publishers")
@NamedQuery(name="TblLitPublisher.findAll", query="SELECT t FROM TblLitPublisher t")
public class TblLitPublisher implements Serializable {
	private static final long serialVersionUID = 1L;
	private int publisherID;
	private byte locked;
	private String publisher;

	public TblLitPublisher() {
	}


	@Id
	public int getPublisherID() {
		return this.publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}