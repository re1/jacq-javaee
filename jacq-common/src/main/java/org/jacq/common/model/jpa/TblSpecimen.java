package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_specimen database table.
 * 
 */
@Entity
@Table(name="tbl_specimen")
@NamedQuery(name="TblSpecimen.findAll", query="SELECT t FROM TblSpecimen t")
public class TblSpecimen implements Serializable {
	private static final long serialVersionUID = 1L;
	private int specimenId;
	private Timestamp timestamp;
	private TblBotanicalObject tblBotanicalObject;
	private TblCuratorialUnit tblCuratorialUnit;

	public TblSpecimen() {
	}


	@Id
	@Column(name="specimen_id", unique=true, nullable=false)
	public int getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(int specimenId) {
		this.specimenId = specimenId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@ManyToOne
	@JoinColumn(name="botanical_object_id", nullable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblCuratorialUnit
	@ManyToOne
	@JoinColumn(name="curatorial_unit_id", nullable=false)
	public TblCuratorialUnit getTblCuratorialUnit() {
		return this.tblCuratorialUnit;
	}

	public void setTblCuratorialUnit(TblCuratorialUnit tblCuratorialUnit) {
		this.tblCuratorialUnit = tblCuratorialUnit;
	}

}