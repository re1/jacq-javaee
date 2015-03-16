package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_sequence database table.
 * 
 */
@Entity
@Table(name="tbl_sequence")
@NamedQuery(name="TblSequence.findAll", query="SELECT t FROM TblSequence t")
public class TblSequence implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;

	public TblSequence() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}