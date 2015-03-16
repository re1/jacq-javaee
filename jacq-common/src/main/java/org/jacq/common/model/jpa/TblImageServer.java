package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_image_server database table.
 * 
 */
@Entity
@Table(name="tbl_image_server")
@NamedQuery(name="TblImageServer.findAll", query="SELECT t FROM TblImageServer t")
public class TblImageServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int organisationId;
	private String baseUrl;
	private String key;
	private TblOrganisation tblOrganisation;

	public TblImageServer() {
	}


	@Id
	@Column(name="organisation_id", unique=true, nullable=false)
	public int getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}


	@Lob
	@Column(name="base_url", nullable=false)
	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	@Column(nullable=false, length=50)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	//bi-directional one-to-one association to TblOrganisation
	@OneToOne
	@JoinColumn(name="organisation_id", nullable=false, insertable=false, updatable=false)
	public TblOrganisation getTblOrganisation() {
		return this.tblOrganisation;
	}

	public void setTblOrganisation(TblOrganisation tblOrganisation) {
		this.tblOrganisation = tblOrganisation;
	}

}