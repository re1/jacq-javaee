package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_nom_status database table.
 * 
 */
@Entity
@Table(name="tbl_nom_status")
@NamedQuery(name="TblNomStatus.findAll", query="SELECT t FROM TblNomStatus t")
public class TblNomStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int statusID;
	private String status;
	private String statusDescription;
	private String statusSp2000;

	public TblNomStatus() {
	}


	@Id
	public int getStatusID() {
		return this.statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Column(name="status_description")
	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}


	@Column(name="status_sp2000")
	public String getStatusSp2000() {
		return this.statusSp2000;
	}

	public void setStatusSp2000(String statusSp2000) {
		this.statusSp2000 = statusSp2000;
	}

}