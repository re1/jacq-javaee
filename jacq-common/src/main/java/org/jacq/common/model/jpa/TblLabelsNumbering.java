package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_labels_numbering database table.
 * 
 */
@Entity
@Table(name="tbl_labels_numbering")
@NamedQuery(name="TblLabelsNumbering.findAll", query="SELECT t FROM TblLabelsNumbering t")
public class TblLabelsNumbering implements Serializable {
	private static final long serialVersionUID = 1L;
	private int labels_numbering_ID;
	private int collectionID_fk;
	private int digits;
	private String replaceChar;
	private int sourceID_fk;

	public TblLabelsNumbering() {
	}


	@Id
	public int getLabels_numbering_ID() {
		return this.labels_numbering_ID;
	}

	public void setLabels_numbering_ID(int labels_numbering_ID) {
		this.labels_numbering_ID = labels_numbering_ID;
	}


	public int getCollectionID_fk() {
		return this.collectionID_fk;
	}

	public void setCollectionID_fk(int collectionID_fk) {
		this.collectionID_fk = collectionID_fk;
	}


	public int getDigits() {
		return this.digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}


	@Column(name="replace_char")
	public String getReplaceChar() {
		return this.replaceChar;
	}

	public void setReplaceChar(String replaceChar) {
		this.replaceChar = replaceChar;
	}


	public int getSourceID_fk() {
		return this.sourceID_fk;
	}

	public void setSourceID_fk(int sourceID_fk) {
		this.sourceID_fk = sourceID_fk;
	}

}