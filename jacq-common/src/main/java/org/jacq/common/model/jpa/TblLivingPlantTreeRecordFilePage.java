package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_living_plant_tree_record_file_page database table.
 * 
 */
@Entity
@Table(name="tbl_living_plant_tree_record_file_page")
@NamedQuery(name="TblLivingPlantTreeRecordFilePage.findAll", query="SELECT t FROM TblLivingPlantTreeRecordFilePage t")
public class TblLivingPlantTreeRecordFilePage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date correctionsDate;
	private byte correctionsDone;
	private TblLivingPlant tblLivingPlant;
	private TblTreeRecordFilePage tblTreeRecordFilePage;

	public TblLivingPlantTreeRecordFilePage() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="corrections_date")
	public Date getCorrectionsDate() {
		return this.correctionsDate;
	}

	public void setCorrectionsDate(Date correctionsDate) {
		this.correctionsDate = correctionsDate;
	}


	@Column(name="corrections_done", nullable=false)
	public byte getCorrectionsDone() {
		return this.correctionsDone;
	}

	public void setCorrectionsDone(byte correctionsDone) {
		this.correctionsDone = correctionsDone;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@ManyToOne
	@JoinColumn(name="living_plant_id", nullable=false)
	public TblLivingPlant getTblLivingPlant() {
		return this.tblLivingPlant;
	}

	public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
		this.tblLivingPlant = tblLivingPlant;
	}


	//bi-directional many-to-one association to TblTreeRecordFilePage
	@ManyToOne
	@JoinColumn(name="tree_record_file_page_id", nullable=false)
	public TblTreeRecordFilePage getTblTreeRecordFilePage() {
		return this.tblTreeRecordFilePage;
	}

	public void setTblTreeRecordFilePage(TblTreeRecordFilePage tblTreeRecordFilePage) {
		this.tblTreeRecordFilePage = tblTreeRecordFilePage;
	}

}