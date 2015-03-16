package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_phenology database table.
 * 
 */
@Entity
@Table(name="tbl_phenology")
@NamedQuery(name="TblPhenology.findAll", query="SELECT t FROM TblPhenology t")
public class TblPhenology implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String phenology;
	private List<TblBotanicalObject> tblBotanicalObjects;

	public TblPhenology() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=45)
	public String getPhenology() {
		return this.phenology;
	}

	public void setPhenology(String phenology) {
		this.phenology = phenology;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@OneToMany(mappedBy="tblPhenology")
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

	public TblBotanicalObject addTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().add(tblBotanicalObject);
		tblBotanicalObject.setTblPhenology(this);

		return tblBotanicalObject;
	}

	public TblBotanicalObject removeTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().remove(tblBotanicalObject);
		tblBotanicalObject.setTblPhenology(null);

		return tblBotanicalObject;
	}

}