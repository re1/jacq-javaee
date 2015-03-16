package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_location_geonames database table.
 * 
 */
@Entity
@Table(name="tbl_location_geonames")
@NamedQuery(name="TblLocationGeoname.findAll", query="SELECT t FROM TblLocationGeoname t")
public class TblLocationGeoname implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String countryCode;
	private int geonameId;
	private String serviceData;
	private TblLocation tblLocation;

	public TblLocationGeoname() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=2)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	@Column(nullable=false)
	public int getGeonameId() {
		return this.geonameId;
	}

	public void setGeonameId(int geonameId) {
		this.geonameId = geonameId;
	}


	@Lob
	@Column(name="service_data", nullable=false)
	public String getServiceData() {
		return this.serviceData;
	}

	public void setServiceData(String serviceData) {
		this.serviceData = serviceData;
	}


	//bi-directional one-to-one association to TblLocation
	@OneToOne
	@JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public TblLocation getTblLocation() {
		return this.tblLocation;
	}

	public void setTblLocation(TblLocation tblLocation) {
		this.tblLocation = tblLocation;
	}

}