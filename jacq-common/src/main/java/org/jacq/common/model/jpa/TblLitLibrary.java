package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_libraries database table.
 * 
 */
@Entity
@Table(name="tbl_lit_libraries")
@NamedQuery(name="TblLitLibrary.findAll", query="SELECT t FROM TblLitLibrary t")
public class TblLitLibrary implements Serializable {
	private static final long serialVersionUID = 1L;
	private int library_ID;
	private String library;

	public TblLitLibrary() {
	}


	@Id
	public int getLibrary_ID() {
		return this.library_ID;
	}

	public void setLibrary_ID(int library_ID) {
		this.library_ID = library_ID;
	}


	public String getLibrary() {
		return this.library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

}