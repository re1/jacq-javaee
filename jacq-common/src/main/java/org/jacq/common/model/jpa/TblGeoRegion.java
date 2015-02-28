package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_geo_region database table.
 * 
 */
@Entity
@Table(name="tbl_geo_region")
@NamedQuery(name="TblGeoRegion.findAll", query="SELECT t FROM TblGeoRegion t")
public class TblGeoRegion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int regionID;
	private String geoGeneral;
	private String geoRegion;

	public TblGeoRegion() {
	}


	@Id
	public int getRegionID() {
		return this.regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}


	@Column(name="geo_general")
	public String getGeoGeneral() {
		return this.geoGeneral;
	}

	public void setGeoGeneral(String geoGeneral) {
		this.geoGeneral = geoGeneral;
	}


	@Column(name="geo_region")
	public String getGeoRegion() {
		return this.geoRegion;
	}

	public void setGeoRegion(String geoRegion) {
		this.geoRegion = geoRegion;
	}

}