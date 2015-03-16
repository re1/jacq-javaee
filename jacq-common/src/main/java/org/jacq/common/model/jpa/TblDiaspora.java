package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_diaspora database table.
 * 
 */
@Entity
@Table(name="tbl_diaspora")
@NamedQuery(name="TblDiaspora.findAll", query="SELECT t FROM TblDiaspora t")
public class TblDiaspora implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private TblBotanicalObject tblBotanicalObject;
	private TblDiasporaBank tblDiasporaBank;

	public TblDiaspora() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	//bi-directional one-to-one association to TblBotanicalObject
	@OneToOne
	@JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblDiasporaBank
	@ManyToOne
	@JoinColumn(name="diaspora_bank_id", nullable=false)
	public TblDiasporaBank getTblDiasporaBank() {
		return this.tblDiasporaBank;
	}

	public void setTblDiasporaBank(TblDiasporaBank tblDiasporaBank) {
		this.tblDiasporaBank = tblDiasporaBank;
	}

}