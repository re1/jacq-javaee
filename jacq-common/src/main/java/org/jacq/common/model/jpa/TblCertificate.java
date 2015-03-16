package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_certificate database table.
 * 
 */
@Entity
@Table(name="tbl_certificate")
@NamedQuery(name="TblCertificate.findAll", query="SELECT t FROM TblCertificate t")
public class TblCertificate implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String annotation;
	private String number;
	private TblCertificateType tblCertificateType;
	private TblLivingPlant tblLivingPlant;

	public TblCertificate() {
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


	@Lob
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	//bi-directional many-to-one association to TblCertificateType
	@ManyToOne
	@JoinColumn(name="certificate_type_id", nullable=false)
	public TblCertificateType getTblCertificateType() {
		return this.tblCertificateType;
	}

	public void setTblCertificateType(TblCertificateType tblCertificateType) {
		this.tblCertificateType = tblCertificateType;
	}


	//bi-directional many-to-one association to TblLivingPlant
	@ManyToOne
	@JoinColumn(name="living_plant_id", nullable=false)
	public TblLivingPlant getTblLivingPlant() {
		return this.tblLivingPlant;
	}

	public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
		this.tblLivingPlant = tblLivingPlant;
	}

}