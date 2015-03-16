package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_diaspora_bank database table.
 * 
 */
@Entity
@Table(name="tbl_diaspora_bank")
@NamedQuery(name="TblDiasporaBank.findAll", query="SELECT t FROM TblDiasporaBank t")
public class TblDiasporaBank implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<TblDiaspora> tblDiasporas;

	public TblDiasporaBank() {
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to TblDiaspora
	@OneToMany(mappedBy="tblDiasporaBank")
	public List<TblDiaspora> getTblDiasporas() {
		return this.tblDiasporas;
	}

	public void setTblDiasporas(List<TblDiaspora> tblDiasporas) {
		this.tblDiasporas = tblDiasporas;
	}

	public TblDiaspora addTblDiaspora(TblDiaspora tblDiaspora) {
		getTblDiasporas().add(tblDiaspora);
		tblDiaspora.setTblDiasporaBank(this);

		return tblDiaspora;
	}

	public TblDiaspora removeTblDiaspora(TblDiaspora tblDiaspora) {
		getTblDiasporas().remove(tblDiaspora);
		tblDiaspora.setTblDiasporaBank(null);

		return tblDiaspora;
	}

}