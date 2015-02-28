package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_descriptions database table.
 * 
 */
@Entity
@Table(name="tbl_descriptions")
@NamedQuery(name="TblDescription.findAll", query="SELECT t FROM TblDescription t")
public class TblDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String column;
	private String description;
	private String table;

	public TblDescription() {
	}


	@Id
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getColumn() {
		return this.column;
	}

	public void setColumn(String column) {
		this.column = column;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}