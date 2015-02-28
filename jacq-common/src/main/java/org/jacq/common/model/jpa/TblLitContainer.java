package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_lit_container database table.
 * 
 */
@Entity
@Table(name="tbl_lit_container")
@NamedQuery(name="TblLitContainer.findAll", query="SELECT t FROM TblLitContainer t")
public class TblLitContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tbl_lit_containerID;
	private int citation_child_ID;
	private int citation_parent_ID;

	public TblLitContainer() {
	}


	@Id
	public int getTbl_lit_containerID() {
		return this.tbl_lit_containerID;
	}

	public void setTbl_lit_containerID(int tbl_lit_containerID) {
		this.tbl_lit_containerID = tbl_lit_containerID;
	}


	public int getCitation_child_ID() {
		return this.citation_child_ID;
	}

	public void setCitation_child_ID(int citation_child_ID) {
		this.citation_child_ID = citation_child_ID;
	}


	public int getCitation_parent_ID() {
		return this.citation_parent_ID;
	}

	public void setCitation_parent_ID(int citation_parent_ID) {
		this.citation_parent_ID = citation_parent_ID;
	}

}