package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_person database table.
 * 
 */
@Entity
@Table(name="tbl_person")
@NamedQuery(name="TblPerson.findAll", query="SELECT t FROM TblPerson t")
public class TblPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_ID;
	private String HUHbotanist_IDfk;
	private String IPNI_version;
	private String IPNIauthor_IDfk;
	private byte locked;
	private String pAbbrev;
	private String pAnnotation;
	private String pBiographyShortEng;
	private String pBiographyShortGer;
	private String pBirthdate;
	private String pBirthplace;
	private String pDeath;
	private String pDeathplace;
	private String pFamilyname;
	private String pFirstname;
	private String pGivenname;

	public TblPerson() {
	}


	@Id
	public int getPerson_ID() {
		return this.person_ID;
	}

	public void setPerson_ID(int person_ID) {
		this.person_ID = person_ID;
	}


	public String getHUHbotanist_IDfk() {
		return this.HUHbotanist_IDfk;
	}

	public void setHUHbotanist_IDfk(String HUHbotanist_IDfk) {
		this.HUHbotanist_IDfk = HUHbotanist_IDfk;
	}


	public String getIPNI_version() {
		return this.IPNI_version;
	}

	public void setIPNI_version(String IPNI_version) {
		this.IPNI_version = IPNI_version;
	}


	public String getIPNIauthor_IDfk() {
		return this.IPNIauthor_IDfk;
	}

	public void setIPNIauthor_IDfk(String IPNIauthor_IDfk) {
		this.IPNIauthor_IDfk = IPNIauthor_IDfk;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="p_abbrev")
	public String getPAbbrev() {
		return this.pAbbrev;
	}

	public void setPAbbrev(String pAbbrev) {
		this.pAbbrev = pAbbrev;
	}


	@Lob
	@Column(name="p_annotation")
	public String getPAnnotation() {
		return this.pAnnotation;
	}

	public void setPAnnotation(String pAnnotation) {
		this.pAnnotation = pAnnotation;
	}


	@Lob
	@Column(name="p_biography_short_eng")
	public String getPBiographyShortEng() {
		return this.pBiographyShortEng;
	}

	public void setPBiographyShortEng(String pBiographyShortEng) {
		this.pBiographyShortEng = pBiographyShortEng;
	}


	@Lob
	@Column(name="p_biography_short_ger")
	public String getPBiographyShortGer() {
		return this.pBiographyShortGer;
	}

	public void setPBiographyShortGer(String pBiographyShortGer) {
		this.pBiographyShortGer = pBiographyShortGer;
	}


	@Column(name="p_birthdate")
	public String getPBirthdate() {
		return this.pBirthdate;
	}

	public void setPBirthdate(String pBirthdate) {
		this.pBirthdate = pBirthdate;
	}


	@Column(name="p_birthplace")
	public String getPBirthplace() {
		return this.pBirthplace;
	}

	public void setPBirthplace(String pBirthplace) {
		this.pBirthplace = pBirthplace;
	}


	@Column(name="p_death")
	public String getPDeath() {
		return this.pDeath;
	}

	public void setPDeath(String pDeath) {
		this.pDeath = pDeath;
	}


	@Column(name="p_deathplace")
	public String getPDeathplace() {
		return this.pDeathplace;
	}

	public void setPDeathplace(String pDeathplace) {
		this.pDeathplace = pDeathplace;
	}


	@Column(name="p_familyname")
	public String getPFamilyname() {
		return this.pFamilyname;
	}

	public void setPFamilyname(String pFamilyname) {
		this.pFamilyname = pFamilyname;
	}


	@Column(name="p_firstname")
	public String getPFirstname() {
		return this.pFirstname;
	}

	public void setPFirstname(String pFirstname) {
		this.pFirstname = pFirstname;
	}


	@Column(name="p_givenname")
	public String getPGivenname() {
		return this.pGivenname;
	}

	public void setPGivenname(String pGivenname) {
		this.pGivenname = pGivenname;
	}

}