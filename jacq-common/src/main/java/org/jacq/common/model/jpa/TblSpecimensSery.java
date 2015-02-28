package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_series database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_series")
@NamedQuery(name="TblSpecimensSery.findAll", query="SELECT t FROM TblSpecimensSery t")
public class TblSpecimensSery implements Serializable {
	private static final long serialVersionUID = 1L;
	private int seriesID;
	private byte locked;
	private String series;

	public TblSpecimensSery() {
	}


	@Id
	public int getSeriesID() {
		return this.seriesID;
	}

	public void setSeriesID(int seriesID) {
		this.seriesID = seriesID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

}