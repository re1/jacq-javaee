package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_tree_record_file database table.
 * 
 */
@Entity
@Table(name="tbl_tree_record_file")
@NamedQuery(name="TblTreeRecordFile.findAll", query="SELECT t FROM TblTreeRecordFile t")
public class TblTreeRecordFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String documentNumber;
	private String name;
	private Date year;
	private List<TblTreeRecordFilePage> tblTreeRecordFilePages;

	public TblTreeRecordFile() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="document_number", length=20)
	public String getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}


	@Column(length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Temporal(TemporalType.DATE)
	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}


	//bi-directional many-to-one association to TblTreeRecordFilePage
	@OneToMany(mappedBy="tblTreeRecordFile")
	public List<TblTreeRecordFilePage> getTblTreeRecordFilePages() {
		return this.tblTreeRecordFilePages;
	}

	public void setTblTreeRecordFilePages(List<TblTreeRecordFilePage> tblTreeRecordFilePages) {
		this.tblTreeRecordFilePages = tblTreeRecordFilePages;
	}

	public TblTreeRecordFilePage addTblTreeRecordFilePage(TblTreeRecordFilePage tblTreeRecordFilePage) {
		getTblTreeRecordFilePages().add(tblTreeRecordFilePage);
		tblTreeRecordFilePage.setTblTreeRecordFile(this);

		return tblTreeRecordFilePage;
	}

	public TblTreeRecordFilePage removeTblTreeRecordFilePage(TblTreeRecordFilePage tblTreeRecordFilePage) {
		getTblTreeRecordFilePages().remove(tblTreeRecordFilePage);
		tblTreeRecordFilePage.setTblTreeRecordFile(null);

		return tblTreeRecordFilePage;
	}

}