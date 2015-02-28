package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_external_import_content database table.
 * 
 */
@Entity
@Table(name="tbl_external_import_content")
@NamedQuery(name="TblExternalImportContent.findAll", query="SELECT t FROM TblExternalImportContent t")
public class TblExternalImportContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private int contentID;
	private int externalID;
	private String filename;
	private String line;
	private int linenumber;
	private byte pending;
	private String processingError;
	private int specimen_ID;
	private int taxonID;
	private Timestamp timestamp;
	private int userID;

	public TblExternalImportContent() {
	}


	@Id
	public int getContentID() {
		return this.contentID;
	}

	public void setContentID(int contentID) {
		this.contentID = contentID;
	}


	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Lob
	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}


	public int getLinenumber() {
		return this.linenumber;
	}

	public void setLinenumber(int linenumber) {
		this.linenumber = linenumber;
	}


	public byte getPending() {
		return this.pending;
	}

	public void setPending(byte pending) {
		this.pending = pending;
	}


	@Lob
	public String getProcessingError() {
		return this.processingError;
	}

	public void setProcessingError(String processingError) {
		this.processingError = processingError;
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


	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}