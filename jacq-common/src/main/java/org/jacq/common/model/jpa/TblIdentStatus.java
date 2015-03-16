package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_ident_status database table.
 * 
 */
@Entity
@Table(name="tbl_ident_status")
@NamedQuery(name="TblIdentStatus.findAll", query="SELECT t FROM TblIdentStatus t")
public class TblIdentStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int identStatusId;
	private String status;
	private List<TblBotanicalObject> tblBotanicalObjects;

	public TblIdentStatus() {
	}


	@Id
	@Column(name="ident_status_id", unique=true, nullable=false)
	public int getIdentStatusId() {
		return this.identStatusId;
	}

	public void setIdentStatusId(int identStatusId) {
		this.identStatusId = identStatusId;
	}


	@Column(nullable=false, length=10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@OneToMany(mappedBy="tblIdentStatus")
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

	public TblBotanicalObject addTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().add(tblBotanicalObject);
		tblBotanicalObject.setTblIdentStatus(this);

		return tblBotanicalObject;
	}

	public TblBotanicalObject removeTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().remove(tblBotanicalObject);
		tblBotanicalObject.setTblIdentStatus(null);

		return tblBotanicalObject;
	}

}