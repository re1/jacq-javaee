package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_acquisition_event database table.
 * 
 */
@Entity
@Table(name="tbl_acquisition_event")
@NamedQuery(name="TblAcquisitionEvent.findAll", query="SELECT t FROM TblAcquisitionEvent t")
public class TblAcquisitionEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String annotation;
	private String number;
	private TblAcquisitionDate tblAcquisitionDate;
	private TblAcquisitionType tblAcquisitionType;
	private TblLocation tblLocation;
	private TblLocationCoordinate tblLocationCoordinate;
	private List<TblAcquisitionEventSource> tblAcquisitionEventSources;
	private List<TblBotanicalObject> tblBotanicalObjects;
	private List<TblPerson> tblPersons;

	public TblAcquisitionEvent() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	@Lob
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	//bi-directional many-to-one association to TblAcquisitionDate
	@ManyToOne
	@JoinColumn(name="acquisition_date_id", nullable=false)
	public TblAcquisitionDate getTblAcquisitionDate() {
		return this.tblAcquisitionDate;
	}

	public void setTblAcquisitionDate(TblAcquisitionDate tblAcquisitionDate) {
		this.tblAcquisitionDate = tblAcquisitionDate;
	}


	//bi-directional many-to-one association to TblAcquisitionType
	@ManyToOne
	@JoinColumn(name="acquisition_type_id", nullable=false)
	public TblAcquisitionType getTblAcquisitionType() {
		return this.tblAcquisitionType;
	}

	public void setTblAcquisitionType(TblAcquisitionType tblAcquisitionType) {
		this.tblAcquisitionType = tblAcquisitionType;
	}


	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="location_id")
	public TblLocation getTblLocation() {
		return this.tblLocation;
	}

	public void setTblLocation(TblLocation tblLocation) {
		this.tblLocation = tblLocation;
	}


	//bi-directional many-to-one association to TblLocationCoordinate
	@ManyToOne
	@JoinColumn(name="location_coordinates_id", nullable=false)
	public TblLocationCoordinate getTblLocationCoordinate() {
		return this.tblLocationCoordinate;
	}

	public void setTblLocationCoordinate(TblLocationCoordinate tblLocationCoordinate) {
		this.tblLocationCoordinate = tblLocationCoordinate;
	}


	//bi-directional many-to-one association to TblAcquisitionEventSource
	@OneToMany(mappedBy="tblAcquisitionEvent")
	public List<TblAcquisitionEventSource> getTblAcquisitionEventSources() {
		return this.tblAcquisitionEventSources;
	}

	public void setTblAcquisitionEventSources(List<TblAcquisitionEventSource> tblAcquisitionEventSources) {
		this.tblAcquisitionEventSources = tblAcquisitionEventSources;
	}

	public TblAcquisitionEventSource addTblAcquisitionEventSource(TblAcquisitionEventSource tblAcquisitionEventSource) {
		getTblAcquisitionEventSources().add(tblAcquisitionEventSource);
		tblAcquisitionEventSource.setTblAcquisitionEvent(this);

		return tblAcquisitionEventSource;
	}

	public TblAcquisitionEventSource removeTblAcquisitionEventSource(TblAcquisitionEventSource tblAcquisitionEventSource) {
		getTblAcquisitionEventSources().remove(tblAcquisitionEventSource);
		tblAcquisitionEventSource.setTblAcquisitionEvent(null);

		return tblAcquisitionEventSource;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@OneToMany(mappedBy="tblAcquisitionEvent")
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

	public TblBotanicalObject addTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().add(tblBotanicalObject);
		tblBotanicalObject.setTblAcquisitionEvent(this);

		return tblBotanicalObject;
	}

	public TblBotanicalObject removeTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().remove(tblBotanicalObject);
		tblBotanicalObject.setTblAcquisitionEvent(null);

		return tblBotanicalObject;
	}


	//bi-directional many-to-many association to TblPerson
	@ManyToMany(mappedBy="tblAcquisitionEvents")
	public List<TblPerson> getTblPersons() {
		return this.tblPersons;
	}

	public void setTblPersons(List<TblPerson> tblPersons) {
		this.tblPersons = tblPersons;
	}

}