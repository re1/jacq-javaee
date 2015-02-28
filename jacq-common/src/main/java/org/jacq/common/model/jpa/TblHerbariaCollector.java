package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_herbaria_collectors database table.
 * 
 */
@Entity
@Table(name="tbl_herbaria_collectors")
@NamedQuery(name="TblHerbariaCollector.findAll", query="SELECT t FROM TblHerbariaCollector t")
public class TblHerbariaCollector implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblHerbariaCollectorPK id;
	private String annotation;
	private double numberObjects;

	public TblHerbariaCollector() {
	}


	@EmbeddedId
	public TblHerbariaCollectorPK getId() {
		return this.id;
	}

	public void setId(TblHerbariaCollectorPK id) {
		this.id = id;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	@Column(name="number_objects")
	public double getNumberObjects() {
		return this.numberObjects;
	}

	public void setNumberObjects(double numberObjects) {
		this.numberObjects = numberObjects;
	}

}