package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_tax_chorol_status database table.
 * 
 */
@Entity
@Table(name="tbl_tax_chorol_status")
@NamedQuery(name="TblTaxChorolStatus.findAll", query="SELECT t FROM TblTaxChorolStatus t")
public class TblTaxChorolStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tax_chorol_status_ID;
	private String chorolStatus;
	private int citationID_fk;
	private Timestamp dateLastEdited;
	private int locked;
	private int nationID_fk;
	private int personID_fk;
	private byte provinceDebatable;
	private int provinceID_fk;
	private byte serviceID_fk;
	private byte statusDebatable;
	private int taxonID_fk;

	public TblTaxChorolStatus() {
	}


	@Id
	public int getTax_chorol_status_ID() {
		return this.tax_chorol_status_ID;
	}

	public void setTax_chorol_status_ID(int tax_chorol_status_ID) {
		this.tax_chorol_status_ID = tax_chorol_status_ID;
	}


	@Column(name="chorol_status")
	public String getChorolStatus() {
		return this.chorolStatus;
	}

	public void setChorolStatus(String chorolStatus) {
		this.chorolStatus = chorolStatus;
	}


	public int getCitationID_fk() {
		return this.citationID_fk;
	}

	public void setCitationID_fk(int citationID_fk) {
		this.citationID_fk = citationID_fk;
	}


	public Timestamp getDateLastEdited() {
		return this.dateLastEdited;
	}

	public void setDateLastEdited(Timestamp dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}


	public int getLocked() {
		return this.locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}


	@Column(name="NationID_fk")
	public int getNationID_fk() {
		return this.nationID_fk;
	}

	public void setNationID_fk(int nationID_fk) {
		this.nationID_fk = nationID_fk;
	}


	public int getPersonID_fk() {
		return this.personID_fk;
	}

	public void setPersonID_fk(int personID_fk) {
		this.personID_fk = personID_fk;
	}


	@Column(name="province_debatable")
	public byte getProvinceDebatable() {
		return this.provinceDebatable;
	}

	public void setProvinceDebatable(byte provinceDebatable) {
		this.provinceDebatable = provinceDebatable;
	}


	public int getProvinceID_fk() {
		return this.provinceID_fk;
	}

	public void setProvinceID_fk(int provinceID_fk) {
		this.provinceID_fk = provinceID_fk;
	}


	public byte getServiceID_fk() {
		return this.serviceID_fk;
	}

	public void setServiceID_fk(byte serviceID_fk) {
		this.serviceID_fk = serviceID_fk;
	}


	@Column(name="status_debatable")
	public byte getStatusDebatable() {
		return this.statusDebatable;
	}

	public void setStatusDebatable(byte statusDebatable) {
		this.statusDebatable = statusDebatable;
	}


	public int getTaxonID_fk() {
		return this.taxonID_fk;
	}

	public void setTaxonID_fk(int taxonID_fk) {
		this.taxonID_fk = taxonID_fk;
	}

}