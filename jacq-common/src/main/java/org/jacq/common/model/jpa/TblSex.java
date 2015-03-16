package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sex database table.
 * 
 */
@Entity
@Table(name="tbl_sex")
@NamedQuery(name="TblSex.findAll", query="SELECT t FROM TblSex t")
public class TblSex implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String sex;
	private List<TblBotanicalObjectSex> tblBotanicalObjectSexs;

	public TblSex() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	//bi-directional many-to-one association to TblBotanicalObjectSex
	@OneToMany(mappedBy="tblSex")
	public List<TblBotanicalObjectSex> getTblBotanicalObjectSexs() {
		return this.tblBotanicalObjectSexs;
	}

	public void setTblBotanicalObjectSexs(List<TblBotanicalObjectSex> tblBotanicalObjectSexs) {
		this.tblBotanicalObjectSexs = tblBotanicalObjectSexs;
	}

	public TblBotanicalObjectSex addTblBotanicalObjectSex(TblBotanicalObjectSex tblBotanicalObjectSex) {
		getTblBotanicalObjectSexs().add(tblBotanicalObjectSex);
		tblBotanicalObjectSex.setTblSex(this);

		return tblBotanicalObjectSex;
	}

	public TblBotanicalObjectSex removeTblBotanicalObjectSex(TblBotanicalObjectSex tblBotanicalObjectSex) {
		getTblBotanicalObjectSexs().remove(tblBotanicalObjectSex);
		tblBotanicalObjectSex.setTblSex(null);

		return tblBotanicalObjectSex;
	}

}