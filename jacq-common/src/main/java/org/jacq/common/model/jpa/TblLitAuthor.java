package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_authors database table.
 * 
 */
@Entity
@Table(name="tbl_lit_authors")
@NamedQuery(name="TblLitAuthor.findAll", query="SELECT t FROM TblLitAuthor t")
public class TblLitAuthor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int autorID;
	private String autor;
	private String autorsystbot;
	private byte locked;

	public TblLitAuthor() {
	}


	@Id
	public int getAutorID() {
		return this.autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}


	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getAutorsystbot() {
		return this.autorsystbot;
	}

	public void setAutorsystbot(String autorsystbot) {
		this.autorsystbot = autorsystbot;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

}