package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_index_seminum_person database table.
 * 
 */
@Entity
@Table(name="tbl_index_seminum_person")
@NamedQuery(name="TblIndexSeminumPerson.findAll", query="SELECT t FROM TblIndexSeminumPerson t")
public class TblIndexSeminumPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private int indexSeminumPersonId;
	private String name;
	private Timestamp timestamp;
	private TblIndexSeminumContent tblIndexSeminumContent;

	public TblIndexSeminumPerson() {
	}


	@Id
	@Column(name="index_seminum_person_id", unique=true, nullable=false)
	public int getIndexSeminumPersonId() {
		return this.indexSeminumPersonId;
	}

	public void setIndexSeminumPersonId(int indexSeminumPersonId) {
		this.indexSeminumPersonId = indexSeminumPersonId;
	}


	@Column(nullable=false, length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblIndexSeminumContent
	@ManyToOne
	@JoinColumn(name="index_seminum_content_id", nullable=false)
	public TblIndexSeminumContent getTblIndexSeminumContent() {
		return this.tblIndexSeminumContent;
	}

	public void setTblIndexSeminumContent(TblIndexSeminumContent tblIndexSeminumContent) {
		this.tblIndexSeminumContent = tblIndexSeminumContent;
	}

}