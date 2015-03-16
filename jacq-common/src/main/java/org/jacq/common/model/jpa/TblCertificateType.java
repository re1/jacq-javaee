package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_certificate_type database table.
 * 
 */
@Entity
@Table(name="tbl_certificate_type")
@NamedQuery(name="TblCertificateType.findAll", query="SELECT t FROM TblCertificateType t")
public class TblCertificateType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private List<TblCertificate> tblCertificates;

	public TblCertificateType() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=15)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	//bi-directional many-to-one association to TblCertificate
	@OneToMany(mappedBy="tblCertificateType")
	public List<TblCertificate> getTblCertificates() {
		return this.tblCertificates;
	}

	public void setTblCertificates(List<TblCertificate> tblCertificates) {
		this.tblCertificates = tblCertificates;
	}

	public TblCertificate addTblCertificate(TblCertificate tblCertificate) {
		getTblCertificates().add(tblCertificate);
		tblCertificate.setTblCertificateType(this);

		return tblCertificate;
	}

	public TblCertificate removeTblCertificate(TblCertificate tblCertificate) {
		getTblCertificates().remove(tblCertificate);
		tblCertificate.setTblCertificateType(null);

		return tblCertificate;
	}

}