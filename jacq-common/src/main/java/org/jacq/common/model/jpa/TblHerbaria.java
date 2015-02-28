package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_herbaria database table.
 * 
 */
@Entity
@Table(name="tbl_herbaria")
@NamedQuery(name="TblHerbaria.findAll", query="SELECT t FROM TblHerbaria t")
public class TblHerbaria implements Serializable {
	private static final long serialVersionUID = 1L;
	private int herbariumID;
	private String adressat;
	private String annotation1;
	private String annotation2;
	private String city;
	private String correspondent;
	private String department;
	private String fax;
	private String herbarium;
	private String ihcode;
	private String institution;
	private String location;
	private String nation;
	private String phone;
	private String streetAddress;
	private Date updated;
	private String url1;
	private String url2;

	public TblHerbaria() {
	}


	@Id
	public int getHerbariumID() {
		return this.herbariumID;
	}

	public void setHerbariumID(int herbariumID) {
		this.herbariumID = herbariumID;
	}


	public String getAdressat() {
		return this.adressat;
	}

	public void setAdressat(String adressat) {
		this.adressat = adressat;
	}


	@Lob
	@Column(name="Annotation1")
	public String getAnnotation1() {
		return this.annotation1;
	}

	public void setAnnotation1(String annotation1) {
		this.annotation1 = annotation1;
	}


	@Lob
	@Column(name="Annotation2")
	public String getAnnotation2() {
		return this.annotation2;
	}

	public void setAnnotation2(String annotation2) {
		this.annotation2 = annotation2;
	}


	@Column(name="City")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	@Lob
	@Column(name="Correspondent")
	public String getCorrespondent() {
		return this.correspondent;
	}

	public void setCorrespondent(String correspondent) {
		this.correspondent = correspondent;
	}


	@Column(name="Department")
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	@Column(name="Fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}


	@Column(name="Herbarium")
	public String getHerbarium() {
		return this.herbarium;
	}

	public void setHerbarium(String herbarium) {
		this.herbarium = herbarium;
	}


	public String getIhcode() {
		return this.ihcode;
	}

	public void setIhcode(String ihcode) {
		this.ihcode = ihcode;
	}


	@Column(name="Institution")
	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}


	@Lob
	@Column(name="Location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	@Column(name="Nation")
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}


	@Column(name="Phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Column(name="StreetAddress")
	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Updated")
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	@Lob
	@Column(name="Url1")
	public String getUrl1() {
		return this.url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}


	@Lob
	@Column(name="Url2")
	public String getUrl2() {
		return this.url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

}