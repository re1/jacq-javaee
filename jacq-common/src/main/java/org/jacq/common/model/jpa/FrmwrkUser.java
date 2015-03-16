package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the frmwrk_user database table.
 * 
 */
@Entity
@Table(name="frmwrk_user")
@NamedQuery(name="FrmwrkUser.findAll", query="SELECT f FROM FrmwrkUser f")
public class FrmwrkUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date birthdate;
	private String firstname;
	private byte forcePasswordChange;
	private String lastname;
	private String password;
	private String salt;
	private String titlePrefix;
	private String titleSuffix;
	private String username;
	private List<Frmwrk2AuthAssignment> frmwrk2AuthAssignments;
	private List<Frmwrk_AuthAssignment> frmwrkAuthAssignments;
	private List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects;
	private List<Frmwrk_accessClassification> frmwrkAccessClassifications;
	private List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations;
	private FrmwrkEmploymentType frmwrkEmploymentType;
	private FrmwrkUserType frmwrkUserType;
	private TblOrganisation tblOrganisation;
	private List<TblIndexSeminumRevision> tblIndexSeminumRevisions;
	private List<TblInventory> tblInventories;
	private List<TblOrganisation> tblOrganisations;

	public FrmwrkUser() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	@Column(nullable=false, length=45)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	@Column(name="force_password_change", nullable=false)
	public byte getForcePasswordChange() {
		return this.forcePasswordChange;
	}

	public void setForcePasswordChange(byte forcePasswordChange) {
		this.forcePasswordChange = forcePasswordChange;
	}


	@Column(nullable=false, length=45)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	@Column(nullable=false, length=64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(nullable=false, length=64)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Column(name="title_prefix", length=45)
	public String getTitlePrefix() {
		return this.titlePrefix;
	}

	public void setTitlePrefix(String titlePrefix) {
		this.titlePrefix = titlePrefix;
	}


	@Column(name="title_suffix", length=45)
	public String getTitleSuffix() {
		return this.titleSuffix;
	}

	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}


	@Column(nullable=false, length=128)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	//bi-directional many-to-one association to Frmwrk2AuthAssignment
	@OneToMany(mappedBy="frmwrkUser")
	public List<Frmwrk2AuthAssignment> getFrmwrk2AuthAssignments() {
		return this.frmwrk2AuthAssignments;
	}

	public void setFrmwrk2AuthAssignments(List<Frmwrk2AuthAssignment> frmwrk2AuthAssignments) {
		this.frmwrk2AuthAssignments = frmwrk2AuthAssignments;
	}

	public Frmwrk2AuthAssignment addFrmwrk2AuthAssignment(Frmwrk2AuthAssignment frmwrk2AuthAssignment) {
		getFrmwrk2AuthAssignments().add(frmwrk2AuthAssignment);
		frmwrk2AuthAssignment.setFrmwrkUser(this);

		return frmwrk2AuthAssignment;
	}

	public Frmwrk2AuthAssignment removeFrmwrk2AuthAssignment(Frmwrk2AuthAssignment frmwrk2AuthAssignment) {
		getFrmwrk2AuthAssignments().remove(frmwrk2AuthAssignment);
		frmwrk2AuthAssignment.setFrmwrkUser(null);

		return frmwrk2AuthAssignment;
	}


	//bi-directional many-to-one association to Frmwrk_AuthAssignment
	@OneToMany(mappedBy="frmwrkUser")
	public List<Frmwrk_AuthAssignment> getFrmwrkAuthAssignments() {
		return this.frmwrkAuthAssignments;
	}

	public void setFrmwrkAuthAssignments(List<Frmwrk_AuthAssignment> frmwrkAuthAssignments) {
		this.frmwrkAuthAssignments = frmwrkAuthAssignments;
	}

	public Frmwrk_AuthAssignment addFrmwrkAuthAssignment(Frmwrk_AuthAssignment frmwrkAuthAssignment) {
		getFrmwrkAuthAssignments().add(frmwrkAuthAssignment);
		frmwrkAuthAssignment.setFrmwrkUser(this);

		return frmwrkAuthAssignment;
	}

	public Frmwrk_AuthAssignment removeFrmwrkAuthAssignment(Frmwrk_AuthAssignment frmwrkAuthAssignment) {
		getFrmwrkAuthAssignments().remove(frmwrkAuthAssignment);
		frmwrkAuthAssignment.setFrmwrkUser(null);

		return frmwrkAuthAssignment;
	}


	//bi-directional many-to-one association to Frmwrk_accessBotanicalObject
	@OneToMany(mappedBy="frmwrkUser")
	public List<Frmwrk_accessBotanicalObject> getFrmwrkAccessBotanicalObjects() {
		return this.frmwrkAccessBotanicalObjects;
	}

	public void setFrmwrkAccessBotanicalObjects(List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects) {
		this.frmwrkAccessBotanicalObjects = frmwrkAccessBotanicalObjects;
	}

	public Frmwrk_accessBotanicalObject addFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().add(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setFrmwrkUser(this);

		return frmwrkAccessBotanicalObject;
	}

	public Frmwrk_accessBotanicalObject removeFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().remove(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setFrmwrkUser(null);

		return frmwrkAccessBotanicalObject;
	}


	//bi-directional many-to-one association to Frmwrk_accessClassification
	@OneToMany(mappedBy="frmwrkUser")
	public List<Frmwrk_accessClassification> getFrmwrkAccessClassifications() {
		return this.frmwrkAccessClassifications;
	}

	public void setFrmwrkAccessClassifications(List<Frmwrk_accessClassification> frmwrkAccessClassifications) {
		this.frmwrkAccessClassifications = frmwrkAccessClassifications;
	}

	public Frmwrk_accessClassification addFrmwrkAccessClassification(Frmwrk_accessClassification frmwrkAccessClassification) {
		getFrmwrkAccessClassifications().add(frmwrkAccessClassification);
		frmwrkAccessClassification.setFrmwrkUser(this);

		return frmwrkAccessClassification;
	}

	public Frmwrk_accessClassification removeFrmwrkAccessClassification(Frmwrk_accessClassification frmwrkAccessClassification) {
		getFrmwrkAccessClassifications().remove(frmwrkAccessClassification);
		frmwrkAccessClassification.setFrmwrkUser(null);

		return frmwrkAccessClassification;
	}


	//bi-directional many-to-one association to Frmwrk_accessOrganisation
	@OneToMany(mappedBy="frmwrkUser")
	public List<Frmwrk_accessOrganisation> getFrmwrkAccessOrganisations() {
		return this.frmwrkAccessOrganisations;
	}

	public void setFrmwrkAccessOrganisations(List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations) {
		this.frmwrkAccessOrganisations = frmwrkAccessOrganisations;
	}

	public Frmwrk_accessOrganisation addFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().add(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setFrmwrkUser(this);

		return frmwrkAccessOrganisation;
	}

	public Frmwrk_accessOrganisation removeFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().remove(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setFrmwrkUser(null);

		return frmwrkAccessOrganisation;
	}


	//bi-directional many-to-one association to FrmwrkEmploymentType
	@ManyToOne
	@JoinColumn(name="employment_type_id", nullable=false)
	public FrmwrkEmploymentType getFrmwrkEmploymentType() {
		return this.frmwrkEmploymentType;
	}

	public void setFrmwrkEmploymentType(FrmwrkEmploymentType frmwrkEmploymentType) {
		this.frmwrkEmploymentType = frmwrkEmploymentType;
	}


	//bi-directional many-to-one association to FrmwrkUserType
	@ManyToOne
	@JoinColumn(name="user_type_id", nullable=false)
	public FrmwrkUserType getFrmwrkUserType() {
		return this.frmwrkUserType;
	}

	public void setFrmwrkUserType(FrmwrkUserType frmwrkUserType) {
		this.frmwrkUserType = frmwrkUserType;
	}


	//bi-directional many-to-one association to TblOrganisation
	@ManyToOne
	@JoinColumn(name="organisation_id", nullable=false)
	public TblOrganisation getTblOrganisation() {
		return this.tblOrganisation;
	}

	public void setTblOrganisation(TblOrganisation tblOrganisation) {
		this.tblOrganisation = tblOrganisation;
	}


	//bi-directional many-to-one association to TblIndexSeminumRevision
	@OneToMany(mappedBy="frmwrkUser")
	public List<TblIndexSeminumRevision> getTblIndexSeminumRevisions() {
		return this.tblIndexSeminumRevisions;
	}

	public void setTblIndexSeminumRevisions(List<TblIndexSeminumRevision> tblIndexSeminumRevisions) {
		this.tblIndexSeminumRevisions = tblIndexSeminumRevisions;
	}

	public TblIndexSeminumRevision addTblIndexSeminumRevision(TblIndexSeminumRevision tblIndexSeminumRevision) {
		getTblIndexSeminumRevisions().add(tblIndexSeminumRevision);
		tblIndexSeminumRevision.setFrmwrkUser(this);

		return tblIndexSeminumRevision;
	}

	public TblIndexSeminumRevision removeTblIndexSeminumRevision(TblIndexSeminumRevision tblIndexSeminumRevision) {
		getTblIndexSeminumRevisions().remove(tblIndexSeminumRevision);
		tblIndexSeminumRevision.setFrmwrkUser(null);

		return tblIndexSeminumRevision;
	}


	//bi-directional many-to-one association to TblInventory
	@OneToMany(mappedBy="frmwrkUser")
	public List<TblInventory> getTblInventories() {
		return this.tblInventories;
	}

	public void setTblInventories(List<TblInventory> tblInventories) {
		this.tblInventories = tblInventories;
	}

	public TblInventory addTblInventory(TblInventory tblInventory) {
		getTblInventories().add(tblInventory);
		tblInventory.setFrmwrkUser(this);

		return tblInventory;
	}

	public TblInventory removeTblInventory(TblInventory tblInventory) {
		getTblInventories().remove(tblInventory);
		tblInventory.setFrmwrkUser(null);

		return tblInventory;
	}


	//bi-directional many-to-one association to TblOrganisation
	@OneToMany(mappedBy="frmwrkUser")
	public List<TblOrganisation> getTblOrganisations() {
		return this.tblOrganisations;
	}

	public void setTblOrganisations(List<TblOrganisation> tblOrganisations) {
		this.tblOrganisations = tblOrganisations;
	}

	public TblOrganisation addTblOrganisation(TblOrganisation tblOrganisation) {
		getTblOrganisations().add(tblOrganisation);
		tblOrganisation.setFrmwrkUser(this);

		return tblOrganisation;
	}

	public TblOrganisation removeTblOrganisation(TblOrganisation tblOrganisation) {
		getTblOrganisations().remove(tblOrganisation);
		tblOrganisation.setFrmwrkUser(null);

		return tblOrganisation;
	}

}