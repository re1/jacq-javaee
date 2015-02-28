package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_epithets database table.
 * 
 */
@Entity
@Table(name="tbl_tax_epithets")
@NamedQuery(name="TblTaxEpithet.findAll", query="SELECT t FROM TblTaxEpithet t")
public class TblTaxEpithet implements Serializable {
	private static final long serialVersionUID = 1L;
	private int epithetID;
	private String epithet;
	private byte external;
	private int externalID;
	private byte locked;

	public TblTaxEpithet() {
	}


	@Id
	public int getEpithetID() {
		return this.epithetID;
	}

	public void setEpithetID(int epithetID) {
		this.epithetID = epithetID;
	}


	public String getEpithet() {
		return this.epithet;
	}

	public void setEpithet(String epithet) {
		this.epithet = epithet;
	}


	public byte getExternal() {
		return this.external;
	}

	public void setExternal(byte external) {
		this.external = external;
	}


	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

}