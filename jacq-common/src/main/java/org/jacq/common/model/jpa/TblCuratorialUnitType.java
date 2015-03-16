package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_curatorial_unit_type database table.
 * 
 */
@Entity
@Table(name="tbl_curatorial_unit_type")
@NamedQuery(name="TblCuratorialUnitType.findAll", query="SELECT t FROM TblCuratorialUnitType t")
public class TblCuratorialUnitType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int curatorialUnitTypeId;
	private Timestamp timestamp;
	private String typeName;
	private List<TblCuratorialUnit> tblCuratorialUnits;

	public TblCuratorialUnitType() {
	}


	@Id
	@Column(name="curatorial_unit_type_id", unique=true, nullable=false)
	public int getCuratorialUnitTypeId() {
		return this.curatorialUnitTypeId;
	}

	public void setCuratorialUnitTypeId(int curatorialUnitTypeId) {
		this.curatorialUnitTypeId = curatorialUnitTypeId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	@Column(name="type_name", nullable=false, length=45)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	//bi-directional many-to-one association to TblCuratorialUnit
	@OneToMany(mappedBy="tblCuratorialUnitType")
	public List<TblCuratorialUnit> getTblCuratorialUnits() {
		return this.tblCuratorialUnits;
	}

	public void setTblCuratorialUnits(List<TblCuratorialUnit> tblCuratorialUnits) {
		this.tblCuratorialUnits = tblCuratorialUnits;
	}

	public TblCuratorialUnit addTblCuratorialUnit(TblCuratorialUnit tblCuratorialUnit) {
		getTblCuratorialUnits().add(tblCuratorialUnit);
		tblCuratorialUnit.setTblCuratorialUnitType(this);

		return tblCuratorialUnit;
	}

	public TblCuratorialUnit removeTblCuratorialUnit(TblCuratorialUnit tblCuratorialUnit) {
		getTblCuratorialUnits().remove(tblCuratorialUnit);
		tblCuratorialUnit.setTblCuratorialUnitType(null);

		return tblCuratorialUnit;
	}

}