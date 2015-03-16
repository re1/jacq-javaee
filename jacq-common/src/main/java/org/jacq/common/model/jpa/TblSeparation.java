package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_separation database table.
 * 
 */
@Entity
@Table(name="tbl_separation")
@NamedQuery(name="TblSeparation.findAll", query="SELECT t FROM TblSeparation t")
public class TblSeparation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String annotation;
	private Date date;
	private TblBotanicalObject tblBotanicalObject;
	private TblSeparationType tblSeparationType;

	public TblSeparation() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@ManyToOne
	@JoinColumn(name="botanical_object_id", nullable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblSeparationType
	@ManyToOne
	@JoinColumn(name="separation_type_id", nullable=false)
	public TblSeparationType getTblSeparationType() {
		return this.tblSeparationType;
	}

	public void setTblSeparationType(TblSeparationType tblSeparationType) {
		this.tblSeparationType = tblSeparationType;
	}

}