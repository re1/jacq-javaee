package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_species database table.
 * 
 */
@Entity
@Table(name="tbl_tax_species")
@NamedQuery(name="TblTaxSpecy.findAll", query="SELECT t FROM TblTaxSpecy t")
public class TblTaxSpecy implements Serializable {
	private static final long serialVersionUID = 1L;
	private int taxonID;
	private String annotation;
	private String API_taxID_fk;
	private int authorID;
	private int basID;
	private byte external;
	private int externalID;
	private int forma_authorID;
	private int formaID;
	private int genID;
	private String IPNI_version;
	private String IPNItax_IDfk;
	private String linn_taxID_fk;
	private byte locked;
	private int speciesID;
	private int statusID;
	private int subforma_authorID;
	private int subformaID;
	private int subspecies_authorID;
	private int subspeciesID;
	private int subvariety_authorID;
	private int subvarietyID;
	private int synID;
	private int tax_rankID;
	private String tropicos_taxID_fk;
	private int variety_authorID;
	private int varietyID;

	public TblTaxSpecy() {
	}


	@Id
	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public String getAPI_taxID_fk() {
		return this.API_taxID_fk;
	}

	public void setAPI_taxID_fk(String API_taxID_fk) {
		this.API_taxID_fk = API_taxID_fk;
	}


	public int getAuthorID() {
		return this.authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	public int getBasID() {
		return this.basID;
	}

	public void setBasID(int basID) {
		this.basID = basID;
	}


	public byte getExternal() {
		return this.external;
	}

	public void setExternal(byte external) {
		this.external = external;
	}


	public int getExternalID() {
		return this.externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}


	public int getForma_authorID() {
		return this.forma_authorID;
	}

	public void setForma_authorID(int forma_authorID) {
		this.forma_authorID = forma_authorID;
	}


	public int getFormaID() {
		return this.formaID;
	}

	public void setFormaID(int formaID) {
		this.formaID = formaID;
	}


	public int getGenID() {
		return this.genID;
	}

	public void setGenID(int genID) {
		this.genID = genID;
	}


	public String getIPNI_version() {
		return this.IPNI_version;
	}

	public void setIPNI_version(String IPNI_version) {
		this.IPNI_version = IPNI_version;
	}


	public String getIPNItax_IDfk() {
		return this.IPNItax_IDfk;
	}

	public void setIPNItax_IDfk(String IPNItax_IDfk) {
		this.IPNItax_IDfk = IPNItax_IDfk;
	}


	public String getLinn_taxID_fk() {
		return this.linn_taxID_fk;
	}

	public void setLinn_taxID_fk(String linn_taxID_fk) {
		this.linn_taxID_fk = linn_taxID_fk;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public int getSpeciesID() {
		return this.speciesID;
	}

	public void setSpeciesID(int speciesID) {
		this.speciesID = speciesID;
	}


	public int getStatusID() {
		return this.statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}


	public int getSubforma_authorID() {
		return this.subforma_authorID;
	}

	public void setSubforma_authorID(int subforma_authorID) {
		this.subforma_authorID = subforma_authorID;
	}


	public int getSubformaID() {
		return this.subformaID;
	}

	public void setSubformaID(int subformaID) {
		this.subformaID = subformaID;
	}


	public int getSubspecies_authorID() {
		return this.subspecies_authorID;
	}

	public void setSubspecies_authorID(int subspecies_authorID) {
		this.subspecies_authorID = subspecies_authorID;
	}


	public int getSubspeciesID() {
		return this.subspeciesID;
	}

	public void setSubspeciesID(int subspeciesID) {
		this.subspeciesID = subspeciesID;
	}


	public int getSubvariety_authorID() {
		return this.subvariety_authorID;
	}

	public void setSubvariety_authorID(int subvariety_authorID) {
		this.subvariety_authorID = subvariety_authorID;
	}


	public int getSubvarietyID() {
		return this.subvarietyID;
	}

	public void setSubvarietyID(int subvarietyID) {
		this.subvarietyID = subvarietyID;
	}


	public int getSynID() {
		return this.synID;
	}

	public void setSynID(int synID) {
		this.synID = synID;
	}


	public int getTax_rankID() {
		return this.tax_rankID;
	}

	public void setTax_rankID(int tax_rankID) {
		this.tax_rankID = tax_rankID;
	}


	public String getTropicos_taxID_fk() {
		return this.tropicos_taxID_fk;
	}

	public void setTropicos_taxID_fk(String tropicos_taxID_fk) {
		this.tropicos_taxID_fk = tropicos_taxID_fk;
	}


	public int getVariety_authorID() {
		return this.variety_authorID;
	}

	public void setVariety_authorID(int variety_authorID) {
		this.variety_authorID = variety_authorID;
	}


	public int getVarietyID() {
		return this.varietyID;
	}

	public void setVarietyID(int varietyID) {
		this.varietyID = varietyID;
	}

}