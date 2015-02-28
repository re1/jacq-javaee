package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_collector_2 database table.
 * 
 */
@Entity
@Table(name="tbl_collector_2")
@NamedQuery(name="TblCollector2.findAll", query="SELECT t FROM TblCollector2 t")
public class TblCollector2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sammler_2ID;
	private byte locked;
	private String sammler_2;
	private String sammler_2_FN_list;
	private String sammler_2_FN_short;

	public TblCollector2() {
	}


	@Id
	@Column(name="Sammler_2ID")
	public int getSammler_2ID() {
		return this.sammler_2ID;
	}

	public void setSammler_2ID(int sammler_2ID) {
		this.sammler_2ID = sammler_2ID;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="Sammler_2")
	public String getSammler_2() {
		return this.sammler_2;
	}

	public void setSammler_2(String sammler_2) {
		this.sammler_2 = sammler_2;
	}


	@Column(name="Sammler_2_FN_list")
	public String getSammler_2_FN_list() {
		return this.sammler_2_FN_list;
	}

	public void setSammler_2_FN_list(String sammler_2_FN_list) {
		this.sammler_2_FN_list = sammler_2_FN_list;
	}


	@Column(name="Sammler_2_FN_short")
	public String getSammler_2_FN_short() {
		return this.sammler_2_FN_short;
	}

	public void setSammler_2_FN_short(String sammler_2_FN_short) {
		this.sammler_2_FN_short = sammler_2_FN_short;
	}

}