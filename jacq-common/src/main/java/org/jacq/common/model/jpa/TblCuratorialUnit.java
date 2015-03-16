package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_curatorial_unit database table.
 * 
 */
@Entity
@Table(name="tbl_curatorial_unit")
@NamedQuery(name="TblCuratorialUnit.findAll", query="SELECT t FROM TblCuratorialUnit t")
public class TblCuratorialUnit implements Serializable {
	private static final long serialVersionUID = 1L;
	private int curatorialUnitId;
	private Timestamp timestamp;
	private TblCuratorialUnitType tblCuratorialUnitType;
	private List<TblSpecimen> tblSpecimens;

	public TblCuratorialUnit() {
	}


	@Id
	@Column(name="curatorial_unit_id", unique=true, nullable=false)
	public int getCuratorialUnitId() {
		return this.curatorialUnitId;
	}

	public void setCuratorialUnitId(int curatorialUnitId) {
		this.curatorialUnitId = curatorialUnitId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblCuratorialUnitType
	@ManyToOne
	@JoinColumn(name="curatorial_unit_type_id", nullable=false)
	public TblCuratorialUnitType getTblCuratorialUnitType() {
		return this.tblCuratorialUnitType;
	}

	public void setTblCuratorialUnitType(TblCuratorialUnitType tblCuratorialUnitType) {
		this.tblCuratorialUnitType = tblCuratorialUnitType;
	}


	//bi-directional many-to-one association to TblSpecimen
	@OneToMany(mappedBy="tblCuratorialUnit")
	public List<TblSpecimen> getTblSpecimens() {
		return this.tblSpecimens;
	}

	public void setTblSpecimens(List<TblSpecimen> tblSpecimens) {
		this.tblSpecimens = tblSpecimens;
	}

	public TblSpecimen addTblSpecimen(TblSpecimen tblSpecimen) {
		getTblSpecimens().add(tblSpecimen);
		tblSpecimen.setTblCuratorialUnit(this);

		return tblSpecimen;
	}

	public TblSpecimen removeTblSpecimen(TblSpecimen tblSpecimen) {
		getTblSpecimens().remove(tblSpecimen);
		tblSpecimen.setTblCuratorialUnit(null);

		return tblSpecimen;
	}

}