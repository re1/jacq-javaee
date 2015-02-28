package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_index database table.
 * 
 */
@Entity
@Table(name="tbl_tax_index")
@NamedQuery(name="TblTaxIndex.findAll", query="SELECT t FROM TblTaxIndex t")
public class TblTaxIndex implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblTaxIndexPK id;
	private String annotations;
	private String dateFigures;
	private String datePaginae;
	private String figures;
	private byte locked;
	private String paginae;
	private int taxindID;

	public TblTaxIndex() {
	}


	@EmbeddedId
	public TblTaxIndexPK getId() {
		return this.id;
	}

	public void setId(TblTaxIndexPK id) {
		this.id = id;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	@Column(name="date_figures")
	public String getDateFigures() {
		return this.dateFigures;
	}

	public void setDateFigures(String dateFigures) {
		this.dateFigures = dateFigures;
	}


	@Column(name="date_paginae")
	public String getDatePaginae() {
		return this.datePaginae;
	}

	public void setDatePaginae(String datePaginae) {
		this.datePaginae = datePaginae;
	}


	public String getFigures() {
		return this.figures;
	}

	public void setFigures(String figures) {
		this.figures = figures;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getPaginae() {
		return this.paginae;
	}

	public void setPaginae(String paginae) {
		this.paginae = paginae;
	}


	public int getTaxindID() {
		return this.taxindID;
	}

	public void setTaxindID(int taxindID) {
		this.taxindID = taxindID;
	}

}