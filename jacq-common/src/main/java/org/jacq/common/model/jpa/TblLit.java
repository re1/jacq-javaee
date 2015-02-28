package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit database table.
 * 
 */
@Entity
@Table(name="tbl_lit")
@NamedQuery(name="TblLit.findAll", query="SELECT t FROM TblLit t")
public class TblLit implements Serializable {
	private static final long serialVersionUID = 1L;
	private int citationID;
	private String additions;
	private String annotation;
	private int autorID;
	private String bestand;
	private String category;
	private String code;
	private int editorsID;
	private byte hideScientificNameAuthors;
	private String jahr;
	private String keywords;
	private String litUrl;
	private byte locked;
	private String part;
	private int periodicalID;
	private String pp;
	private String ppSort;
	private String publ;
	private int publisherID;
	private String signature;
	private String suptitel;
	private String titel;
	private String verlagsort;
	private String vol;

	public TblLit() {
	}


	@Id
	public int getCitationID() {
		return this.citationID;
	}

	public void setCitationID(int citationID) {
		this.citationID = citationID;
	}


	@Lob
	public String getAdditions() {
		return this.additions;
	}

	public void setAdditions(String additions) {
		this.additions = additions;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public int getAutorID() {
		return this.autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}


	public String getBestand() {
		return this.bestand;
	}

	public void setBestand(String bestand) {
		this.bestand = bestand;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public int getEditorsID() {
		return this.editorsID;
	}

	public void setEditorsID(int editorsID) {
		this.editorsID = editorsID;
	}


	public byte getHideScientificNameAuthors() {
		return this.hideScientificNameAuthors;
	}

	public void setHideScientificNameAuthors(byte hideScientificNameAuthors) {
		this.hideScientificNameAuthors = hideScientificNameAuthors;
	}


	public String getJahr() {
		return this.jahr;
	}

	public void setJahr(String jahr) {
		this.jahr = jahr;
	}


	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	@Column(name="lit_url")
	public String getLitUrl() {
		return this.litUrl;
	}

	public void setLitUrl(String litUrl) {
		this.litUrl = litUrl;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}


	public int getPeriodicalID() {
		return this.periodicalID;
	}

	public void setPeriodicalID(int periodicalID) {
		this.periodicalID = periodicalID;
	}


	public String getPp() {
		return this.pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}


	public String getPpSort() {
		return this.ppSort;
	}

	public void setPpSort(String ppSort) {
		this.ppSort = ppSort;
	}


	public String getPubl() {
		return this.publ;
	}

	public void setPubl(String publ) {
		this.publ = publ;
	}


	public int getPublisherID() {
		return this.publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}


	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}


	public String getSuptitel() {
		return this.suptitel;
	}

	public void setSuptitel(String suptitel) {
		this.suptitel = suptitel;
	}


	@Lob
	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}


	public String getVerlagsort() {
		return this.verlagsort;
	}

	public void setVerlagsort(String verlagsort) {
		this.verlagsort = verlagsort;
	}


	public String getVol() {
		return this.vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

}