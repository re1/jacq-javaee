package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_alternative_accession_number database table.
 * 
 */
@Entity
@Table(name="tbl_alternative_accession_number")
@NamedQuery(name="TblAlternativeAccessionNumber.findAll", query="SELECT t FROM TblAlternativeAccessionNumber t")
public class TblAlternativeAccessionNumber implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String number;
	private TblLivingPlant tblLivingPlant;

	public TblAlternativeAccessionNumber() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=255)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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

}