package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_acquisition_type database table.
 * 
 */
@Entity
@Table(name="tbl_acquisition_type")
@NamedQuery(name="TblAcquisitionType.findAll", query="SELECT t FROM TblAcquisitionType t")
public class TblAcquisitionType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private List<TblAcquisitionEvent> tblAcquisitionEvents;

	public TblAcquisitionType() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@OneToMany(mappedBy="tblAcquisitionType")
	public List<TblAcquisitionEvent> getTblAcquisitionEvents() {
		return this.tblAcquisitionEvents;
	}

	public void setTblAcquisitionEvents(List<TblAcquisitionEvent> tblAcquisitionEvents) {
		this.tblAcquisitionEvents = tblAcquisitionEvents;
	}

	public TblAcquisitionEvent addTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().add(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblAcquisitionType(this);

		return tblAcquisitionEvent;
	}

	public TblAcquisitionEvent removeTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		getTblAcquisitionEvents().remove(tblAcquisitionEvent);
		tblAcquisitionEvent.setTblAcquisitionType(null);

		return tblAcquisitionEvent;
	}

}