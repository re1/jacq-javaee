package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_acquisition_date database table.
 * 
 */
@Entity
@Table(name="tbl_acquisition_date")
@NamedQuery(name="TblAcquisitionDate.findAll", query="SELECT t FROM TblAcquisitionDate t")
public class TblAcquisitionDate implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String custom;
	private String day;
	private String month;
	private String year;
	private List<TblAcquisitionEvent> tblAcquisitionEvents;
	private List<TblLivingPlant> tblLivingPlants;

	public TblAcquisitionDate() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=20)
	public String getCustom() {
		return this.custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}


	@Column(length=2)
	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	@Column(length=2)
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}


	@Column(length=4)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@OneToMany(mappedBy="tblAcquisitionDate")
	public List<TblAcquisitionEvent> getTblAcquisitionEvents() {
		return this.tblAcquisitionEvents;
	}

	public void setTblAcquisitionEvents(List<TblAcquisitionEvent> tblAcquisitionEvents) {
		this.tblAcquisitionEvents = tblAcquisitionEvents;
	}

	public TblAcquisitionEvent addTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().add(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblAcquisitionDate(this);

		return tblAcquisitionEvent;
	}

	public TblAcquisitionEvent removeTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().remove(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblAcquisitionDate(null);

		return tblAcquisitionEvent;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@OneToMany(mappedBy="tblAcquisitionDate")
	public List<TblLivingPlant> getTblLivingPlants() {
		return this.tblLivingPlants;
	}

	public void setTblLivingPlants(List<TblLivingPlant> tblLivingPlants) {
		this.tblLivingPlants = tblLivingPlants;
	}

	public TblLivingPlant addTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().add(tblLivingPlant);
		tblLivingPlant.setTblAcquisitionDate(this);

		return tblLivingPlant;
	}

	public TblLivingPlant removeTblLivingPlant(TblLivingPlant tblLivingPlant) {
		getTblLivingPlants().remove(tblLivingPlant);
		tblLivingPlant.setTblAcquisitionDate(null);

		return tblLivingPlant;
	}

}