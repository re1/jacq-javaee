package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_identstatus database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_identstatus")
@NamedQuery(name="TblSpecimensIdentstatus.findAll", query="SELECT t FROM TblSpecimensIdentstatus t")
public class TblSpecimensIdentstatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int identstatusID;
	private String identificationStatus;

	public TblSpecimensIdentstatus() {
	}


	@Id
	public int getIdentstatusID() {
		return this.identstatusID;
	}

	public void setIdentstatusID(int identstatusID) {
		this.identstatusID = identstatusID;
	}


	@Column(name="identification_status")
	public String getIdentificationStatus() {
		return this.identificationStatus;
	}

	public void setIdentificationStatus(String identificationStatus) {
		this.identificationStatus = identificationStatus;
	}

}