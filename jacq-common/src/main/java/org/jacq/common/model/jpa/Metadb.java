package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the metadb database table.
 * 
 */
@Entity
@Table(name="metadb")
@NamedQuery(name="Metadb.findAll", query="SELECT m FROM Metadb m")
public class Metadb implements Serializable {
	private static final long serialVersionUID = 1L;
	private int dbId;
	private String acknowledgement;
	private String copyright;
	private String description;
	private String disclaimer;
	private String ipr;
	private String legalOwnerAdress;
	private String legalOwnerEmail;
	private String legalOwnerOrganisation;
	private String legalOwnerOrganisationCode;
	private String legalOwnerPerson;
	private String legalOwnerTelephone;
	private String legalOwnerUrl;
	private String logoUrl;
	private String restrictions;
	private String rightsUrl;
	private int sourceIdFk;
	private String statementUrl;
	private String supplierAdress;
	private String supplierEmail;
	private String supplierOrganisation;
	private String supplierOrganisationCode;
	private String supplierPerson;
	private Date supplierSuppliedWhen;
	private String supplierTelephone;
	private String supplierUrl;
	private String termsOfUse;

	public Metadb() {
	}


	@Id
	@Column(name="db_id")
	public int getDbId() {
		return this.dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}


	@Lob
	public String getAcknowledgement() {
		return this.acknowledgement;
	}

	public void setAcknowledgement(String acknowledgement) {
		this.acknowledgement = acknowledgement;
	}


	@Lob
	public String getCopyright() {
		return this.copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Lob
	public String getDisclaimer() {
		return this.disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}


	@Lob
	public String getIpr() {
		return this.ipr;
	}

	public void setIpr(String ipr) {
		this.ipr = ipr;
	}


	@Column(name="legal_owner_adress")
	public String getLegalOwnerAdress() {
		return this.legalOwnerAdress;
	}

	public void setLegalOwnerAdress(String legalOwnerAdress) {
		this.legalOwnerAdress = legalOwnerAdress;
	}


	@Column(name="legal_owner_email")
	public String getLegalOwnerEmail() {
		return this.legalOwnerEmail;
	}

	public void setLegalOwnerEmail(String legalOwnerEmail) {
		this.legalOwnerEmail = legalOwnerEmail;
	}


	@Column(name="legal_owner_organisation")
	public String getLegalOwnerOrganisation() {
		return this.legalOwnerOrganisation;
	}

	public void setLegalOwnerOrganisation(String legalOwnerOrganisation) {
		this.legalOwnerOrganisation = legalOwnerOrganisation;
	}


	@Column(name="legal_owner_organisation_code")
	public String getLegalOwnerOrganisationCode() {
		return this.legalOwnerOrganisationCode;
	}

	public void setLegalOwnerOrganisationCode(String legalOwnerOrganisationCode) {
		this.legalOwnerOrganisationCode = legalOwnerOrganisationCode;
	}


	@Column(name="legal_owner_person")
	public String getLegalOwnerPerson() {
		return this.legalOwnerPerson;
	}

	public void setLegalOwnerPerson(String legalOwnerPerson) {
		this.legalOwnerPerson = legalOwnerPerson;
	}


	@Column(name="legal_owner_telephone")
	public String getLegalOwnerTelephone() {
		return this.legalOwnerTelephone;
	}

	public void setLegalOwnerTelephone(String legalOwnerTelephone) {
		this.legalOwnerTelephone = legalOwnerTelephone;
	}


	@Column(name="legal_owner_url")
	public String getLegalOwnerUrl() {
		return this.legalOwnerUrl;
	}

	public void setLegalOwnerUrl(String legalOwnerUrl) {
		this.legalOwnerUrl = legalOwnerUrl;
	}


	@Column(name="logo_url")
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}


	@Lob
	public String getRestrictions() {
		return this.restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}


	@Column(name="rights_url")
	public String getRightsUrl() {
		return this.rightsUrl;
	}

	public void setRightsUrl(String rightsUrl) {
		this.rightsUrl = rightsUrl;
	}


	@Column(name="source_id_fk")
	public int getSourceIdFk() {
		return this.sourceIdFk;
	}

	public void setSourceIdFk(int sourceIdFk) {
		this.sourceIdFk = sourceIdFk;
	}


	@Column(name="statement_url")
	public String getStatementUrl() {
		return this.statementUrl;
	}

	public void setStatementUrl(String statementUrl) {
		this.statementUrl = statementUrl;
	}


	@Column(name="supplier_adress")
	public String getSupplierAdress() {
		return this.supplierAdress;
	}

	public void setSupplierAdress(String supplierAdress) {
		this.supplierAdress = supplierAdress;
	}


	@Column(name="supplier_email")
	public String getSupplierEmail() {
		return this.supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}


	@Column(name="supplier_organisation")
	public String getSupplierOrganisation() {
		return this.supplierOrganisation;
	}

	public void setSupplierOrganisation(String supplierOrganisation) {
		this.supplierOrganisation = supplierOrganisation;
	}


	@Column(name="supplier_organisation_code")
	public String getSupplierOrganisationCode() {
		return this.supplierOrganisationCode;
	}

	public void setSupplierOrganisationCode(String supplierOrganisationCode) {
		this.supplierOrganisationCode = supplierOrganisationCode;
	}


	@Column(name="supplier_person")
	public String getSupplierPerson() {
		return this.supplierPerson;
	}

	public void setSupplierPerson(String supplierPerson) {
		this.supplierPerson = supplierPerson;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="supplier_supplied_when")
	public Date getSupplierSuppliedWhen() {
		return this.supplierSuppliedWhen;
	}

	public void setSupplierSuppliedWhen(Date supplierSuppliedWhen) {
		this.supplierSuppliedWhen = supplierSuppliedWhen;
	}


	@Column(name="supplier_telephone")
	public String getSupplierTelephone() {
		return this.supplierTelephone;
	}

	public void setSupplierTelephone(String supplierTelephone) {
		this.supplierTelephone = supplierTelephone;
	}


	@Column(name="supplier_url")
	public String getSupplierUrl() {
		return this.supplierUrl;
	}

	public void setSupplierUrl(String supplierUrl) {
		this.supplierUrl = supplierUrl;
	}


	@Lob
	@Column(name="terms_of_use")
	public String getTermsOfUse() {
		return this.termsOfUse;
	}

	public void setTermsOfUse(String termsOfUse) {
		this.termsOfUse = termsOfUse;
	}

}