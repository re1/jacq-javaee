package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_geo_ref_geonames database table.
 * 
 */
@Entity
@Table(name="tbl_geo_ref_geonames")
@NamedQuery(name="TblGeoRefGeoname.findAll", query="SELECT t FROM TblGeoRefGeoname t")
public class TblGeoRefGeoname implements Serializable {
	private static final long serialVersionUID = 1L;
	private int geonameid;
	private String admin1_code;
	private String admin2_code;
	private String admin3_code;
	private String admin4_code;
	private String alternatenames;
	private String asciiname;
	private String cc2;
	private String country_code;
	private int elevation;
	private String feature_code;
	private String featureClass;
	private int gtopo30;
	private double latitude;
	private double longitude;
	private Date modification_date;
	private String name;
	private int population;
	private String timezone;

	public TblGeoRefGeoname() {
	}


	@Id
	public int getGeonameid() {
		return this.geonameid;
	}

	public void setGeonameid(int geonameid) {
		this.geonameid = geonameid;
	}


	@Column(name="`admin1 code`")
	public String getAdmin1_code() {
		return this.admin1_code;
	}

	public void setAdmin1_code(String admin1_code) {
		this.admin1_code = admin1_code;
	}


	@Column(name="`admin2 code`")
	public String getAdmin2_code() {
		return this.admin2_code;
	}

	public void setAdmin2_code(String admin2_code) {
		this.admin2_code = admin2_code;
	}


	@Column(name="`admin3 code`")
	public String getAdmin3_code() {
		return this.admin3_code;
	}

	public void setAdmin3_code(String admin3_code) {
		this.admin3_code = admin3_code;
	}


	@Column(name="`admin4 code`")
	public String getAdmin4_code() {
		return this.admin4_code;
	}

	public void setAdmin4_code(String admin4_code) {
		this.admin4_code = admin4_code;
	}


	public String getAlternatenames() {
		return this.alternatenames;
	}

	public void setAlternatenames(String alternatenames) {
		this.alternatenames = alternatenames;
	}


	public String getAsciiname() {
		return this.asciiname;
	}

	public void setAsciiname(String asciiname) {
		this.asciiname = asciiname;
	}


	public String getCc2() {
		return this.cc2;
	}

	public void setCc2(String cc2) {
		this.cc2 = cc2;
	}


	@Column(name="`country code`")
	public String getCountry_code() {
		return this.country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}


	public int getElevation() {
		return this.elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}


	@Column(name="`feature code`")
	public String getFeature_code() {
		return this.feature_code;
	}

	public void setFeature_code(String feature_code) {
		this.feature_code = feature_code;
	}


	@Column(name="feature_class")
	public String getFeatureClass() {
		return this.featureClass;
	}

	public void setFeatureClass(String featureClass) {
		this.featureClass = featureClass;
	}


	public int getGtopo30() {
		return this.gtopo30;
	}

	public void setGtopo30(int gtopo30) {
		this.gtopo30 = gtopo30;
	}


	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="`modification date`")
	public Date getModification_date() {
		return this.modification_date;
	}

	public void setModification_date(Date modification_date) {
		this.modification_date = modification_date;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}


	public String getTimezone() {
		return this.timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}