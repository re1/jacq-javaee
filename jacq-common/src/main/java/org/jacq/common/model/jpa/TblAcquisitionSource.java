package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_acquisition_source database table.
 * 
 */
@Entity
@Table(name="tbl_acquisition_source")
@NamedQuery(name="TblAcquisitionSource.findAll", query="SELECT t FROM TblAcquisitionSource t")
public class TblAcquisitionSource implements Serializable {
	private static final long serialVersionUID = 1L;
	private int acquisitionSourceId;
	private String name;
	private List<TblAcquisitionEventSource> tblAcquisitionEventSources;

	public TblAcquisitionSource() {
	}


	@Id
	@Column(name="acquisition_source_id", unique=true, nullable=false)
	public int getAcquisitionSourceId() {
		return this.acquisitionSourceId;
	}

	public void setAcquisitionSourceId(int acquisitionSourceId) {
		this.acquisitionSourceId = acquisitionSourceId;
	}


	@Column(nullable=false, length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TblAcquisitionEventSource
	@OneToMany(mappedBy="tblAcquisitionSource")
	public List<TblAcquisitionEventSource> getTblAcquisitionEventSources() {
		return this.tblAcquisitionEventSources;
	}

	public void setTblAcquisitionEventSources(List<TblAcquisitionEventSource> tblAcquisitionEventSources) {
		this.tblAcquisitionEventSources = tblAcquisitionEventSources;
	}

	public TblAcquisitionEventSource addTblAcquisitionEventSource(TblAcquisitionEventSource tblAcquisitionEventSource) {
		getTblAcquisitionEventSources().add(tblAcquisitionEventSource);
		tblAcquisitionEventSource.setTblAcquisitionSource(this);

		return tblAcquisitionEventSource;
	}

	public TblAcquisitionEventSource removeTblAcquisitionEventSource(TblAcquisitionEventSource tblAcquisitionEventSource) {
		getTblAcquisitionEventSources().remove(tblAcquisitionEventSource);
		tblAcquisitionEventSource.setTblAcquisitionSource(null);

		return tblAcquisitionEventSource;
	}

}