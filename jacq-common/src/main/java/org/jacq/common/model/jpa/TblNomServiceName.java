package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_nom_service_names database table.
 * 
 */
@Entity
@Table(name="tbl_nom_service_names")
@NamedQuery(name="TblNomServiceName.findAll", query="SELECT t FROM TblNomServiceName t")
public class TblNomServiceName implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String param1;
	private String param2;
	private int serviceID;
	private int taxonID;

	public TblNomServiceName() {
	}


	@Id
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getParam1() {
		return this.param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}


	public String getParam2() {
		return this.param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}


	public int getServiceID() {
		return this.serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}

}