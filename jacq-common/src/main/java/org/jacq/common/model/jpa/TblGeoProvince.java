package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_geo_province database table.
 * 
 */
@Entity
@Table(name="tbl_geo_province")
@NamedQuery(name="TblGeoProvince.findAll", query="SELECT t FROM TblGeoProvince t")
public class TblGeoProvince implements Serializable {
	private static final long serialVersionUID = 1L;
	private int provinceID;
	private int nationID;
	private String provinz;
	private String provinzCode;
	private String provinzLocal;
	private String usgsNumber;

	public TblGeoProvince() {
	}


	@Id
	public int getProvinceID() {
		return this.provinceID;
	}

	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}


	public int getNationID() {
		return this.nationID;
	}

	public void setNationID(int nationID) {
		this.nationID = nationID;
	}


	public String getProvinz() {
		return this.provinz;
	}

	public void setProvinz(String provinz) {
		this.provinz = provinz;
	}


	@Column(name="provinz_code")
	public String getProvinzCode() {
		return this.provinzCode;
	}

	public void setProvinzCode(String provinzCode) {
		this.provinzCode = provinzCode;
	}


	@Column(name="provinz_local")
	public String getProvinzLocal() {
		return this.provinzLocal;
	}

	public void setProvinzLocal(String provinzLocal) {
		this.provinzLocal = provinzLocal;
	}


	@Column(name="usgs_number")
	public String getUsgsNumber() {
		return this.usgsNumber;
	}

	public void setUsgsNumber(String usgsNumber) {
		this.usgsNumber = usgsNumber;
	}

}