package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_periodicals database table.
 * 
 */
@Entity
@Table(name="tbl_lit_periodicals")
@NamedQuery(name="TblLitPeriodical.findAll", query="SELECT t FROM TblLitPeriodical t")
public class TblLitPeriodical implements Serializable {
	private static final long serialVersionUID = 1L;
	private int periodicalID;
	private String bphNumber;
	private String ipni_ID;
	private String IPNI_version;
	private byte locked;
	private String periodical;
	private String periodicalFull;
	private int predecessor_ID;
	private int successor_ID;
	private int tl2Number;

	public TblLitPeriodical() {
	}


	@Id
	public int getPeriodicalID() {
		return this.periodicalID;
	}

	public void setPeriodicalID(int periodicalID) {
		this.periodicalID = periodicalID;
	}


	@Column(name="bph_number")
	public String getBphNumber() {
		return this.bphNumber;
	}

	public void setBphNumber(String bphNumber) {
		this.bphNumber = bphNumber;
	}


	public String getIpni_ID() {
		return this.ipni_ID;
	}

	public void setIpni_ID(String ipni_ID) {
		this.ipni_ID = ipni_ID;
	}


	public String getIPNI_version() {
		return this.IPNI_version;
	}

	public void setIPNI_version(String IPNI_version) {
		this.IPNI_version = IPNI_version;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getPeriodical() {
		return this.periodical;
	}

	public void setPeriodical(String periodical) {
		this.periodical = periodical;
	}


	@Column(name="periodical_full")
	public String getPeriodicalFull() {
		return this.periodicalFull;
	}

	public void setPeriodicalFull(String periodicalFull) {
		this.periodicalFull = periodicalFull;
	}


	public int getPredecessor_ID() {
		return this.predecessor_ID;
	}

	public void setPredecessor_ID(int predecessor_ID) {
		this.predecessor_ID = predecessor_ID;
	}


	public int getSuccessor_ID() {
		return this.successor_ID;
	}

	public void setSuccessor_ID(int successor_ID) {
		this.successor_ID = successor_ID;
	}


	@Column(name="tl2_number")
	public int getTl2Number() {
		return this.tl2Number;
	}

	public void setTl2Number(int tl2Number) {
		this.tl2Number = tl2Number;
	}

}