package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_separation_type database table.
 * 
 */
@Entity
@Table(name="tbl_separation_type")
@NamedQuery(name="TblSeparationType.findAll", query="SELECT t FROM TblSeparationType t")
public class TblSeparationType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private List<TblSeparation> tblSeparations;

	public TblSeparationType() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=25)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblSeparation
	@OneToMany(mappedBy="tblSeparationType")
	public List<TblSeparation> getTblSeparations() {
		return this.tblSeparations;
	}

	public void setTblSeparations(List<TblSeparation> tblSeparations) {
		this.tblSeparations = tblSeparations;
	}

	public TblSeparation addTblSeparation(TblSeparation tblSeparation) {
		getTblSeparations().add(tblSeparation);
		tblSeparation.setTblSeparationType(this);

		return tblSeparation;
	}

	public TblSeparation removeTblSeparation(TblSeparation tblSeparation) {
		getTblSeparations().remove(tblSeparation);
		tblSeparation.setTblSeparationType(null);

		return tblSeparation;
	}

}