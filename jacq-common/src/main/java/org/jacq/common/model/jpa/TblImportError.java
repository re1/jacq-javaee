package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_import_error database table.
 * 
 */
@Entity
@Table(name="tbl_import_error")
@NamedQuery(name="TblImportError.findAll", query="SELECT t FROM TblImportError t")
public class TblImportError implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int IDPflanze;
	private String message;

	public TblImportError() {
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
	public int getIDPflanze() {
		return this.IDPflanze;
	}

	public void setIDPflanze(int IDPflanze) {
		this.IDPflanze = IDPflanze;
	}


	@Lob
	@Column(nullable=false)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}