package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_location database table.
 * 
 */
@Entity
@Table(name="tbl_location")
@NamedQuery(name="TblLocation.findAll", query="SELECT t FROM TblLocation t")
public class TblLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String location;
	private List<TblAcquisitionEvent> tblAcquisitionEvents;
	private TblLocationGeoname tblLocationGeoname;

	public TblLocation() {
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
	@Column(nullable=false)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@OneToMany(mappedBy="tblLocation")
	public List<TblAcquisitionEvent> getTblAcquisitionEvents() {
		return this.tblAcquisitionEvents;
	}

	public void setTblAcquisitionEvents(List<TblAcquisitionEvent> tblAcquisitionEvents) {
		this.tblAcquisitionEvents = tblAcquisitionEvents;
	}

	public TblAcquisitionEvent addTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().add(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblLocation(this);

		return tblAcquisitionEvent;
	}

	public TblAcquisitionEvent removeTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().remove(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblLocation(null);

		return tblAcquisitionEvent;
	}


	//bi-directional one-to-one association to TblLocationGeoname
	@OneToOne(mappedBy="tblLocation")
	public TblLocationGeoname getTblLocationGeoname() {
		return this.tblLocationGeoname;
	}

	public void setTblLocationGeoname(TblLocationGeoname tblLocationGeoname) {
		this.tblLocationGeoname = tblLocationGeoname;
	}

}