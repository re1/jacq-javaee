package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_person database table.
 * 
 */
@Entity
@Table(name="tbl_person")
@NamedQuery(name="TblPerson.findAll", query="SELECT t FROM TblPerson t")
public class TblPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<TblBotanicalObject> tblBotanicalObjects;
	private List<TblAcquisitionEvent> tblAcquisitionEvents;

	public TblPerson() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@OneToMany(mappedBy="tblPerson")
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

	public TblBotanicalObject addTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().add(tblBotanicalObject);
		tblBotanicalObject.setTblPerson(this);

		return tblBotanicalObject;
	}

	public TblBotanicalObject removeTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().remove(tblBotanicalObject);
		tblBotanicalObject.setTblPerson(null);

		return tblBotanicalObject;
	}


	//bi-directional many-to-many association to TblAcquisitionEvent
	@ManyToMany
	@JoinTable(
		name="tbl_acquisition_event_person"
		, joinColumns={
			@JoinColumn(name="person_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="acquisition_event_id", nullable=false)
			}
		)
	public List<TblAcquisitionEvent> getTblAcquisitionEvents() {
		return this.tblAcquisitionEvents;
	}

	public void setTblAcquisitionEvents(List<TblAcquisitionEvent> tblAcquisitionEvents) {
		this.tblAcquisitionEvents = tblAcquisitionEvents;
	}

}