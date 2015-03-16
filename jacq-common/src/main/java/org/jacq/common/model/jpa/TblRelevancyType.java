package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_relevancy_type database table.
 * 
 */
@Entity
@Table(name="tbl_relevancy_type")
@NamedQuery(name="TblRelevancyType.findAll", query="SELECT t FROM TblRelevancyType t")
public class TblRelevancyType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte important;
	private String type;
	private List<TblRelevancy> tblRelevancies;

	public TblRelevancyType() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false)
	public byte getImportant() {
		return this.important;
	}

	public void setImportant(byte important) {
		this.important = important;
	}


	@Column(length=25)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblRelevancy
	@OneToMany(mappedBy="tblRelevancyType")
	public List<TblRelevancy> getTblRelevancies() {
		return this.tblRelevancies;
	}

	public void setTblRelevancies(List<TblRelevancy> tblRelevancies) {
		this.tblRelevancies = tblRelevancies;
	}

	public TblRelevancy addTblRelevancy(TblRelevancy tblRelevancy) {
		getTblRelevancies().add(tblRelevancy);
		tblRelevancy.setTblRelevancyType(this);

		return tblRelevancy;
	}

	public TblRelevancy removeTblRelevancy(TblRelevancy tblRelevancy) {
		getTblRelevancies().remove(tblRelevancy);
		tblRelevancy.setTblRelevancyType(null);

		return tblRelevancy;
	}

}