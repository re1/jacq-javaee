package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_lib_period database table.
 * 
 */
@Entity
@Table(name="tbl_lit_lib_period")
@NamedQuery(name="TblLitLibPeriod.findAll", query="SELECT t FROM TblLitLibPeriod t")
public class TblLitLibPeriod implements Serializable {
	private static final long serialVersionUID = 1L;
	private int lib_period_ID;
	private String bestand;
	private int library_ID;
	private int periodicalID;
	private String signature;
	private String url;

	public TblLitLibPeriod() {
	}


	@Id
	public int getLib_period_ID() {
		return this.lib_period_ID;
	}

	public void setLib_period_ID(int lib_period_ID) {
		this.lib_period_ID = lib_period_ID;
	}


	public String getBestand() {
		return this.bestand;
	}

	public void setBestand(String bestand) {
		this.bestand = bestand;
	}


	public int getLibrary_ID() {
		return this.library_ID;
	}

	public void setLibrary_ID(int library_ID) {
		this.library_ID = library_ID;
	}


	public int getPeriodicalID() {
		return this.periodicalID;
	}

	public void setPeriodicalID(int periodicalID) {
		this.periodicalID = periodicalID;
	}


	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}


	@Lob
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}