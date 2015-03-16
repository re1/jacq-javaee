package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_relevancy database table.
 * 
 */
@Entity
@Table(name="tbl_relevancy")
@NamedQuery(name="TblRelevancy.findAll", query="SELECT t FROM TblRelevancy t")
public class TblRelevancy implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private TblLivingPlant tblLivingPlant;
	private TblRelevancyType tblRelevancyType;

	public TblRelevancy() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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


	//bi-directional many-to-one association to TblRelevancyType
	@ManyToOne
	@JoinColumn(name="relevancy_type_id", nullable=false)
	public TblRelevancyType getTblRelevancyType() {
		return this.tblRelevancyType;
	}

	public void setTblRelevancyType(TblRelevancyType tblRelevancyType) {
		this.tblRelevancyType = tblRelevancyType;
	}

}