package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_links database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_links")
@NamedQuery(name="TblSpecimensLink.findAll", query="SELECT t FROM TblSpecimensLink t")
public class TblSpecimensLink implements Serializable {
	private static final long serialVersionUID = 1L;
	private int specimens_linkID;
	private int specimen1_ID;
	private int specimen2_ID;

	public TblSpecimensLink() {
	}


	@Id
	public int getSpecimens_linkID() {
		return this.specimens_linkID;
	}

	public void setSpecimens_linkID(int specimens_linkID) {
		this.specimens_linkID = specimens_linkID;
	}


	public int getSpecimen1_ID() {
		return this.specimen1_ID;
	}

	public void setSpecimen1_ID(int specimen1_ID) {
		this.specimen1_ID = specimen1_ID;
	}


	public int getSpecimen2_ID() {
		return this.specimen2_ID;
	}

	public void setSpecimen2_ID(int specimen2_ID) {
		this.specimen2_ID = specimen2_ID;
	}

}