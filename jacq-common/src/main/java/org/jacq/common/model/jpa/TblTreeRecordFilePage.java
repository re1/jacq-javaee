package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_tree_record_file_page database table.
 * 
 */
@Entity
@Table(name="tbl_tree_record_file_page")
@NamedQuery(name="TblTreeRecordFilePage.findAll", query="SELECT t FROM TblTreeRecordFilePage t")
public class TblTreeRecordFilePage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int page;
	private List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePages;
	private TblTreeRecordFile tblTreeRecordFile;

	public TblTreeRecordFilePage() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false)
	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	//bi-directional many-to-one association to TblLivingPlantTreeRecordFilePage
	@OneToMany(mappedBy="tblTreeRecordFilePage")
	public List<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePages() {
		return this.tblLivingPlantTreeRecordFilePages;
	}

	public void setTblLivingPlantTreeRecordFilePages(List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePages) {
		this.tblLivingPlantTreeRecordFilePages = tblLivingPlantTreeRecordFilePages;
	}

	public TblLivingPlantTreeRecordFilePage addTblLivingPlantTreeRecordFilePage(TblLivingPlantTreeRecordFilePage tblLivingPlantTreeRecordFilePage) {
		getTblLivingPlantTreeRecordFilePages().add(tblLivingPlantTreeRecordFilePage);
		tblLivingPlantTreeRecordFilePage.setTblTreeRecordFilePage(this);

		return tblLivingPlantTreeRecordFilePage;
	}

	public TblLivingPlantTreeRecordFilePage removeTblLivingPlantTreeRecordFilePage(TblLivingPlantTreeRecordFilePage tblLivingPlantTreeRecordFilePage) {
		getTblLivingPlantTreeRecordFilePages().remove(tblLivingPlantTreeRecordFilePage);
		tblLivingPlantTreeRecordFilePage.setTblTreeRecordFilePage(null);

		return tblLivingPlantTreeRecordFilePage;
	}


	//bi-directional many-to-one association to TblTreeRecordFile
	@ManyToOne
	@JoinColumn(name="tree_record_file_id", nullable=false)
	public TblTreeRecordFile getTblTreeRecordFile() {
		return this.tblTreeRecordFile;
	}

	public void setTblTreeRecordFile(TblTreeRecordFile tblTreeRecordFile) {
		this.tblTreeRecordFile = tblTreeRecordFile;
	}

}