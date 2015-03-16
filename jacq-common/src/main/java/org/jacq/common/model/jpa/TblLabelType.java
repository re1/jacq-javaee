package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_label_type database table.
 * 
 */
@Entity
@Table(name="tbl_label_type")
@NamedQuery(name="TblLabelType.findAll", query="SELECT t FROM TblLabelType t")
public class TblLabelType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int labelTypeId;
	private String type;
	private List<TblBotanicalObject> tblBotanicalObjects;

	public TblLabelType() {
	}


	@Id
	@Column(name="label_type_id", unique=true, nullable=false)
	public int getLabelTypeId() {
		return this.labelTypeId;
	}

	public void setLabelTypeId(int labelTypeId) {
		this.labelTypeId = labelTypeId;
	}


	@Column(nullable=false, length=25)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-many association to TblBotanicalObject
	@ManyToMany
	@JoinTable(
		name="tbl_botanical_object_label"
		, joinColumns={
			@JoinColumn(name="label_type_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="botanical_object_id", nullable=false)
			}
		)
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

}