package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_external_import database table.
 * 
 */
@Entity
@Table(name="tbl_external_import")
@NamedQuery(name="TblExternalImport.findAll", query="SELECT t FROM TblExternalImport t")
public class TblExternalImport implements Serializable {
	private static final long serialVersionUID = 1L;
	private int externalID;
	private String annotation;
	private String description;
	private Date enddate;
	private Date startdate;
	private byte used;

	public TblExternalImport() {
	}


	@Id
	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Temporal(TemporalType.DATE)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}


	@Temporal(TemporalType.DATE)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}


	public byte getUsed() {
		return this.used;
	}

	public void setUsed(byte used) {
		this.used = used;
	}

}