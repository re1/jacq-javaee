package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_img_definition database table.
 * 
 */
@Entity
@Table(name="tbl_img_definition")
@NamedQuery(name="TblImgDefinition.findAll", query="SELECT t FROM TblImgDefinition t")
public class TblImgDefinition implements Serializable {
	private static final long serialVersionUID = 1L;
	private int img_def_ID;
	private byte herbNummerNrDigits;
	private String imgCollShort;
	private String imgDirectory;
	private String imgObsDirectory;
	private String imgServiceDirectory;
	private String imgTabDirectory;
	private String imgserver_IP;
	private byte isDjatoka;
	private String key;
	private int sourceIdFk;

	public TblImgDefinition() {
	}


	@Id
	public int getImg_def_ID() {
		return this.img_def_ID;
	}

	public void setImg_def_ID(int img_def_ID) {
		this.img_def_ID = img_def_ID;
	}


	@Column(name="HerbNummerNrDigits")
	public byte getHerbNummerNrDigits() {
		return this.herbNummerNrDigits;
	}

	public void setHerbNummerNrDigits(byte herbNummerNrDigits) {
		this.herbNummerNrDigits = herbNummerNrDigits;
	}


	@Column(name="img_coll_short")
	public String getImgCollShort() {
		return this.imgCollShort;
	}

	public void setImgCollShort(String imgCollShort) {
		this.imgCollShort = imgCollShort;
	}


	@Column(name="img_directory")
	public String getImgDirectory() {
		return this.imgDirectory;
	}

	public void setImgDirectory(String imgDirectory) {
		this.imgDirectory = imgDirectory;
	}


	@Column(name="img_obs_directory")
	public String getImgObsDirectory() {
		return this.imgObsDirectory;
	}

	public void setImgObsDirectory(String imgObsDirectory) {
		this.imgObsDirectory = imgObsDirectory;
	}


	@Column(name="img_service_directory")
	public String getImgServiceDirectory() {
		return this.imgServiceDirectory;
	}

	public void setImgServiceDirectory(String imgServiceDirectory) {
		this.imgServiceDirectory = imgServiceDirectory;
	}


	@Column(name="img_tab_directory")
	public String getImgTabDirectory() {
		return this.imgTabDirectory;
	}

	public void setImgTabDirectory(String imgTabDirectory) {
		this.imgTabDirectory = imgTabDirectory;
	}


	public String getImgserver_IP() {
		return this.imgserver_IP;
	}

	public void setImgserver_IP(String imgserver_IP) {
		this.imgserver_IP = imgserver_IP;
	}


	@Column(name="is_djatoka")
	public byte getIsDjatoka() {
		return this.isDjatoka;
	}

	public void setIsDjatoka(byte isDjatoka) {
		this.isDjatoka = isDjatoka;
	}


	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	@Column(name="source_id_fk")
	public int getSourceIdFk() {
		return this.sourceIdFk;
	}

	public void setSourceIdFk(int sourceIdFk) {
		this.sourceIdFk = sourceIdFk;
	}

}