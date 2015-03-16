package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_scientific_name_information database table.
 * 
 */
@Entity
@Table(name="tbl_scientific_name_information")
@NamedQuery(name="TblScientificNameInformation.findAll", query="SELECT t FROM TblScientificNameInformation t")
public class TblScientificNameInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int scientificNameId;
	private String commonNames;
	private String spatialDistribution;
	private List<TblCultivar> tblCultivars;
	private TblHabitusType tblHabitusType;

	public TblScientificNameInformation() {
	}


	@Id
	@Column(name="scientific_name_id", unique=true, nullable=false)
	public int getScientificNameId() {
		return this.scientificNameId;
	}

	public void setScientificNameId(int scientificNameId) {
		this.scientificNameId = scientificNameId;
	}


	@Column(name="common_names", length=255)
	public String getCommonNames() {
		return this.commonNames;
	}

	public void setCommonNames(String commonNames) {
		this.commonNames = commonNames;
	}


	@Column(name="spatial_distribution", length=255)
	public String getSpatialDistribution() {
		return this.spatialDistribution;
	}

	public void setSpatialDistribution(String spatialDistribution) {
		this.spatialDistribution = spatialDistribution;
	}


	//bi-directional many-to-one association to TblCultivar
	@OneToMany(mappedBy="tblScientificNameInformation")
	public List<TblCultivar> getTblCultivars() {
		return this.tblCultivars;
	}

	public void setTblCultivars(List<TblCultivar> tblCultivars) {
		this.tblCultivars = tblCultivars;
	}

	public TblCultivar addTblCultivar(TblCultivar tblCultivar) {
		getTblCultivars().add(tblCultivar);
		tblCultivar.setTblScientificNameInformation(this);

		return tblCultivar;
	}

	public TblCultivar removeTblCultivar(TblCultivar tblCultivar) {
		getTblCultivars().remove(tblCultivar);
		tblCultivar.setTblScientificNameInformation(null);

		return tblCultivar;
	}


	//bi-directional many-to-one association to TblHabitusType
	@ManyToOne
	@JoinColumn(name="habitus_type_id")
	public TblHabitusType getTblHabitusType() {
		return this.tblHabitusType;
	}

	public void setTblHabitusType(TblHabitusType tblHabitusType) {
		this.tblHabitusType = tblHabitusType;
	}

}