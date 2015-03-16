package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_botanical_object_sex database table.
 * 
 */
@Entity
@Table(name="tbl_botanical_object_sex")
@NamedQuery(name="TblBotanicalObjectSex.findAll", query="SELECT t FROM TblBotanicalObjectSex t")
public class TblBotanicalObjectSex implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private TblBotanicalObject tblBotanicalObject;
	private TblSex tblSex;

	public TblBotanicalObjectSex() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@ManyToOne
	@JoinColumn(name="botanical_object_id", nullable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblSex
	@ManyToOne
	@JoinColumn(name="sex_id", nullable=false)
	public TblSex getTblSex() {
		return this.tblSex;
	}

	public void setTblSex(TblSex tblSex) {
		this.tblSex = tblSex;
	}

}