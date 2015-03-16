package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_cultivar database table.
 * 
 */
@Entity
@Table(name="tbl_cultivar")
@NamedQuery(name="TblCultivar.findAll", query="SELECT t FROM TblCultivar t")
public class TblCultivar implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cultivarId;
	private String cultivar;
	private TblScientificNameInformation tblScientificNameInformation;
	private List<TblLivingPlant> tblLivingPlants;

	public TblCultivar() {
	}


	@Id
	@Column(name="cultivar_id", unique=true, nullable=false)
	public int getCultivarId() {
		return this.cultivarId;
	}

	public void setCultivarId(int cultivarId) {
		this.cultivarId = cultivarId;
	}


	@Column(nullable=false, length=255)
	public String getCultivar() {
		return this.cultivar;
	}

	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}


	//bi-directional many-to-one association to TblScientificNameInformation
	@ManyToOne
	@JoinColumn(name="scientific_name_id", nullable=false)
	public TblScientificNameInformation getTblScientificNameInformation() {
		return this.tblScientificNameInformation;
	}

	public void setTblScientificNameInformation(TblScientificNameInformation tblScientificNameInformation) {
		this.tblScientificNameInformation = tblScientificNameInformation;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@OneToMany(mappedBy="tblCultivar")
	public List<TblLivingPlant> getTblLivingPlants() {
		return this.tblLivingPlants;
	}

	public void setTblLivingPlants(List<TblLivingPlant> tblLivingPlants) {
		this.tblLivingPlants = tblLivingPlants;
	}

	public TblLivingPlant addTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().add(tblLivingPlant);
		tblLivingPlant.setTblCultivar(this);

		return tblLivingPlant;
	}

	public TblLivingPlant removeTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().remove(tblLivingPlant);
		tblLivingPlant.setTblCultivar(null);

		return tblLivingPlant;
	}

}