package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_collector database table.
 * 
 */
@Entity
@Table(name="tbl_collector")
@NamedQuery(name="TblCollector.findAll", query="SELECT t FROM TblCollector t")
public class TblCollector implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sammlerID;
	private int huhId;
	private byte locked;
	private String sammler;
	private String sammler_FN_List;
	private String sammler_FN_short;

	public TblCollector() {
	}


	@Id
	@Column(name="SammlerID")
	public int getSammlerID() {
		return this.sammlerID;
	}

	public void setSammlerID(int sammlerID) {
		this.sammlerID = sammlerID;
	}


	@Column(name="HUH_ID")
	public int getHuhId() {
		return this.huhId;
	}

	public void setHuhId(int huhId) {
		this.huhId = huhId;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="Sammler")
	public String getSammler() {
		return this.sammler;
	}

	public void setSammler(String sammler) {
		this.sammler = sammler;
	}


	@Column(name="Sammler_FN_List")
	public String getSammler_FN_List() {
		return this.sammler_FN_List;
	}

	public void setSammler_FN_List(String sammler_FN_List) {
		this.sammler_FN_List = sammler_FN_List;
	}


	@Column(name="Sammler_FN_short")
	public String getSammler_FN_short() {
		return this.sammler_FN_short;
	}

	public void setSammler_FN_short(String sammler_FN_short) {
		this.sammler_FN_short = sammler_FN_short;
	}

}