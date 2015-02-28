package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_lit_persons database table.
 * 
 */
@Entity
@Table(name="tbl_lit_persons")
@NamedQuery(name="TblLitPerson.findAll", query="SELECT t FROM TblLitPerson t")
public class TblLitPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private int lit_persons_ID;
	private String annotations;
	private int citationID_fk;
	private byte locked;
	private int personID_fk;
	private Timestamp timestamp;
	private int userID;

	public TblLitPerson() {
	}


	@Id
	public int getLit_persons_ID() {
		return this.lit_persons_ID;
	}

	public void setLit_persons_ID(int lit_persons_ID) {
		this.lit_persons_ID = lit_persons_ID;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public int getCitationID_fk() {
		return this.citationID_fk;
	}

	public void setCitationID_fk(int citationID_fk) {
		this.citationID_fk = citationID_fk;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public int getPersonID_fk() {
		return this.personID_fk;
	}

	public void setPersonID_fk(int personID_fk) {
		this.personID_fk = personID_fk;
	}


	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}