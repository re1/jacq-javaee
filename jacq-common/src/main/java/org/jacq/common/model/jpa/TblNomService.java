package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_nom_service database table.
 * 
 */
@Entity
@Table(name="tbl_nom_service")
@NamedQuery(name="TblNomService.findAll", query="SELECT t FROM TblNomService t")
public class TblNomService implements Serializable {
	private static final long serialVersionUID = 1L;
	private int serviceID;
	private String name;
	private String urlHead;
	private String urlMiddle;
	private String urlTrail;

	public TblNomService() {
	}


	@Id
	public int getServiceID() {
		return this.serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="url_head")
	public String getUrlHead() {
		return this.urlHead;
	}

	public void setUrlHead(String urlHead) {
		this.urlHead = urlHead;
	}


	@Column(name="url_middle")
	public String getUrlMiddle() {
		return this.urlMiddle;
	}

	public void setUrlMiddle(String urlMiddle) {
		this.urlMiddle = urlMiddle;
	}


	@Column(name="url_trail")
	public String getUrlTrail() {
		return this.urlTrail;
	}

	public void setUrlTrail(String urlTrail) {
		this.urlTrail = urlTrail;
	}

}