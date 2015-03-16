package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_acquisition_event_source database table.
 * 
 */
@Entity
@Table(name="tbl_acquisition_event_source")
@NamedQuery(name="TblAcquisitionEventSource.findAll", query="SELECT t FROM TblAcquisitionEventSource t")
public class TblAcquisitionEventSource implements Serializable {
	private static final long serialVersionUID = 1L;
	private int acquisitionEventSourceId;
	private Date sourceDate;
	private TblAcquisitionEvent tblAcquisitionEvent;
	private TblAcquisitionSource tblAcquisitionSource;

	public TblAcquisitionEventSource() {
	}


	@Id
	@Column(name="acquisition_event_source_id", unique=true, nullable=false)
	public int getAcquisitionEventSourceId() {
		return this.acquisitionEventSourceId;
	}

	public void setAcquisitionEventSourceId(int acquisitionEventSourceId) {
		this.acquisitionEventSourceId = acquisitionEventSourceId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="source_date")
	public Date getSourceDate() {
		return this.sourceDate;
	}

	public void setSourceDate(Date sourceDate) {
		this.sourceDate = sourceDate;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@ManyToOne
	@JoinColumn(name="acquisition_event_id", nullable=false)
	public TblAcquisitionEvent getTblAcquisitionEvent() {
		return this.tblAcquisitionEvent;
	}

	public void setTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		this.tblAcquisitionEvent = tblAcquisitionEvent;
	}


	//bi-directional many-to-one association to TblAcquisitionSource
	@ManyToOne
	@JoinColumn(name="acquisition_source_id", nullable=false)
	public TblAcquisitionSource getTblAcquisitionSource() {
		return this.tblAcquisitionSource;
	}

	public void setTblAcquisitionSource(TblAcquisitionSource tblAcquisitionSource) {
		this.tblAcquisitionSource = tblAcquisitionSource;
	}

}