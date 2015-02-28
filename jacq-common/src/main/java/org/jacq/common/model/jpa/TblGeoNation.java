package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_geo_nation database table.
 * 
 */
@Entity
@Table(name="tbl_geo_nation")
@NamedQuery(name="TblGeoNation.findAll", query="SELECT t FROM TblGeoNation t")
public class TblGeoNation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nationID;
	private String annotation;
	private int fnnumber;
	private String isoAlpha2Code;
	private String isoAlpha3Code;
	private String languageVariants;
	private String nation;
	private String nationCode;
	private String nationDeutsch;
	private String nationEngl;
	private int regionID_fk;
	private String usgsCode;

	public TblGeoNation() {
	}


	@Id
	public int getNationID() {
		return this.nationID;
	}

	public void setNationID(int nationID) {
		this.nationID = nationID;
	}


	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public int getFnnumber() {
		return this.fnnumber;
	}

	public void setFnnumber(int fnnumber) {
		this.fnnumber = fnnumber;
	}


	@Column(name="iso_alpha_2_code")
	public String getIsoAlpha2Code() {
		return this.isoAlpha2Code;
	}

	public void setIsoAlpha2Code(String isoAlpha2Code) {
		this.isoAlpha2Code = isoAlpha2Code;
	}


	@Column(name="iso_alpha_3_code")
	public String getIsoAlpha3Code() {
		return this.isoAlpha3Code;
	}

	public void setIsoAlpha3Code(String isoAlpha3Code) {
		this.isoAlpha3Code = isoAlpha3Code;
	}


	@Column(name="language_variants")
	public String getLanguageVariants() {
		return this.languageVariants;
	}

	public void setLanguageVariants(String languageVariants) {
		this.languageVariants = languageVariants;
	}


	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}


	@Column(name="nation_code")
	public String getNationCode() {
		return this.nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}


	@Column(name="nation_deutsch")
	public String getNationDeutsch() {
		return this.nationDeutsch;
	}

	public void setNationDeutsch(String nationDeutsch) {
		this.nationDeutsch = nationDeutsch;
	}


	@Column(name="nation_engl")
	public String getNationEngl() {
		return this.nationEngl;
	}

	public void setNationEngl(String nationEngl) {
		this.nationEngl = nationEngl;
	}


	public int getRegionID_fk() {
		return this.regionID_fk;
	}

	public void setRegionID_fk(int regionID_fk) {
		this.regionID_fk = regionID_fk;
	}


	@Column(name="usgs_code")
	public String getUsgsCode() {
		return this.usgsCode;
	}

	public void setUsgsCode(String usgsCode) {
		this.usgsCode = usgsCode;
	}

}