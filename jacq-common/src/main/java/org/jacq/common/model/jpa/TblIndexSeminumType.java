package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_index_seminum_type database table.
 * 
 */
@Entity
@Table(name="tbl_index_seminum_type")
@NamedQuery(name="TblIndexSeminumType.findAll", query="SELECT t FROM TblIndexSeminumType t")
public class TblIndexSeminumType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private List<TblLivingPlant> tblLivingPlants;

	public TblIndexSeminumType() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=3)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@OneToMany(mappedBy="tblIndexSeminumType")
	public List<TblLivingPlant> getTblLivingPlants() {
		return this.tblLivingPlants;
	}

	public void setTblLivingPlants(List<TblLivingPlant> tblLivingPlants) {
		this.tblLivingPlants = tblLivingPlants;
	}

	public TblLivingPlant addTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().add(tblLivingPlant);
		tblLivingPlant.setTblIndexSeminumType(this);

		return tblLivingPlant;
	}

	public TblLivingPlant removeTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().remove(tblLivingPlant);
		tblLivingPlant.setTblIndexSeminumType(null);

		return tblLivingPlant;
	}

}