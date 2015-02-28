package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_nom_common_names database table.
 * 
 */
@Entity
@Table(name="tbl_nom_common_names")
@NamedQuery(name="TblNomCommonName.findAll", query="SELECT t FROM TblNomCommonName t")
public class TblNomCommonName implements Serializable {
	private static final long serialVersionUID = 1L;
	private int common_name_ID;
	private String annotation;
	private String commonName;
	private byte locked;

	public TblNomCommonName() {
	}


	@Id
	public int getCommon_name_ID() {
		return this.common_name_ID;
	}

	public void setCommon_name_ID(int common_name_ID) {
		this.common_name_ID = common_name_ID;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	@Column(name="common_name")
	public String getCommonName() {
		return this.commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}

}