package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_habitus_type database table.
 * 
 */
@Entity
@Table(name="tbl_habitus_type")
@NamedQuery(name="TblHabitusType.findAll", query="SELECT t FROM TblHabitusType t")
public class TblHabitusType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int habitusTypeId;
	private String habitus;
	private List<TblScientificNameInformation> tblScientificNameInformations;

	public TblHabitusType() {
	}


	@Id
	@Column(name="habitus_type_id", unique=true, nullable=false)
	public int getHabitusTypeId() {
		return this.habitusTypeId;
	}

	public void setHabitusTypeId(int habitusTypeId) {
		this.habitusTypeId = habitusTypeId;
	}


	@Column(nullable=false, length=255)
	public String getHabitus() {
		return this.habitus;
	}

	public void setHabitus(String habitus) {
		this.habitus = habitus;
	}


	//bi-directional many-to-one association to TblScientificNameInformation
	@OneToMany(mappedBy="tblHabitusType")
	public List<TblScientificNameInformation> getTblScientificNameInformations() {
		return this.tblScientificNameInformations;
	}

	public void setTblScientificNameInformations(List<TblScientificNameInformation> tblScientificNameInformations) {
		this.tblScientificNameInformations = tblScientificNameInformations;
	}

	public TblScientificNameInformation addTblScientificNameInformation(TblScientificNameInformation tblScientificNameInformation) {
		getTblScientificNameInformations().add(tblScientificNameInformation);
		tblScientificNameInformation.setTblHabitusType(this);

		return tblScientificNameInformation;
	}

	public TblScientificNameInformation removeTblScientificNameInformation(TblScientificNameInformation tblScientificNameInformation) {
		getTblScientificNameInformations().remove(tblScientificNameInformation);
		tblScientificNameInformation.setTblHabitusType(null);

		return tblScientificNameInformation;
	}

}