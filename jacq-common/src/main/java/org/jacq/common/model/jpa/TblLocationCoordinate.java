package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_location_coordinates database table.
 * 
 */
@Entity
@Table(name="tbl_location_coordinates")
@NamedQuery(name="TblLocationCoordinate.findAll", query="SELECT t FROM TblLocationCoordinate t")
public class TblLocationCoordinate implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int altitudeMax;
	private int altitudeMin;
	private int exactness;
	private int latitudeDegrees;
	private String latitudeHalf;
	private int latitudeMinutes;
	private int latitudeSeconds;
	private int longitudeDegrees;
	private String longitudeHalf;
	private int longitudeMinutes;
	private int longitudeSeconds;
	private List<TblAcquisitionEvent> tblAcquisitionEvents;

	public TblLocationCoordinate() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="altitude_max")
	public int getAltitudeMax() {
		return this.altitudeMax;
	}

	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}


	@Column(name="altitude_min")
	public int getAltitudeMin() {
		return this.altitudeMin;
	}

	public void setAltitudeMin(int altitudeMin) {
		this.altitudeMin = altitudeMin;
	}


	public int getExactness() {
		return this.exactness;
	}

	public void setExactness(int exactness) {
		this.exactness = exactness;
	}


	@Column(name="latitude_degrees")
	public int getLatitudeDegrees() {
		return this.latitudeDegrees;
	}

	public void setLatitudeDegrees(int latitudeDegrees) {
		this.latitudeDegrees = latitudeDegrees;
	}


	@Column(name="latitude_half", length=1)
	public String getLatitudeHalf() {
		return this.latitudeHalf;
	}

	public void setLatitudeHalf(String latitudeHalf) {
		this.latitudeHalf = latitudeHalf;
	}


	@Column(name="latitude_minutes")
	public int getLatitudeMinutes() {
		return this.latitudeMinutes;
	}

	public void setLatitudeMinutes(int latitudeMinutes) {
		this.latitudeMinutes = latitudeMinutes;
	}


	@Column(name="latitude_seconds")
	public int getLatitudeSeconds() {
		return this.latitudeSeconds;
	}

	public void setLatitudeSeconds(int latitudeSeconds) {
		this.latitudeSeconds = latitudeSeconds;
	}


	@Column(name="longitude_degrees")
	public int getLongitudeDegrees() {
		return this.longitudeDegrees;
	}

	public void setLongitudeDegrees(int longitudeDegrees) {
		this.longitudeDegrees = longitudeDegrees;
	}


	@Column(name="longitude_half", length=1)
	public String getLongitudeHalf() {
		return this.longitudeHalf;
	}

	public void setLongitudeHalf(String longitudeHalf) {
		this.longitudeHalf = longitudeHalf;
	}


	@Column(name="longitude_minutes")
	public int getLongitudeMinutes() {
		return this.longitudeMinutes;
	}

	public void setLongitudeMinutes(int longitudeMinutes) {
		this.longitudeMinutes = longitudeMinutes;
	}


	@Column(name="longitude_seconds")
	public int getLongitudeSeconds() {
		return this.longitudeSeconds;
	}

	public void setLongitudeSeconds(int longitudeSeconds) {
		this.longitudeSeconds = longitudeSeconds;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@OneToMany(mappedBy="tblLocationCoordinate")
	public List<TblAcquisitionEvent> getTblAcquisitionEvents() {
		return this.tblAcquisitionEvents;
	}

	public void setTblAcquisitionEvents(List<TblAcquisitionEvent> tblAcquisitionEvents) {
		this.tblAcquisitionEvents = tblAcquisitionEvents;
	}

	public TblAcquisitionEvent addTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().add(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblLocationCoordinate(this);

		return tblAcquisitionEvent;
	}

	public TblAcquisitionEvent removeTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().remove(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblLocationCoordinate(null);

		return tblAcquisitionEvent;
	}

}