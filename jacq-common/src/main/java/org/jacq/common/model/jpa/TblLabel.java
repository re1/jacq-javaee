package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_labels database table.
 * 
 */
@Entity
@Table(name="tbl_labels")
@NamedQuery(name="TblLabel.findAll", query="SELECT t FROM TblLabel t")
public class TblLabel implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblLabelPK id;
	private int label;

	public TblLabel() {
	}


	@EmbeddedId
	public TblLabelPK getId() {
		return this.id;
	}

	public void setId(TblLabelPK id) {
		this.id = id;
	}


	public int getLabel() {
		return this.label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

}