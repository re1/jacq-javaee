package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_organisation database table.
 * 
 */
@Entity
@Table(name="tbl_organisation")
@NamedQuery(name="TblOrganisation.findAll", query="SELECT t FROM TblOrganisation t")
public class TblOrganisation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String department;
	private String description;
	private byte greenhouse;
	private String ipenCode;
	private List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations;
	private List<FrmwrkUser> frmwrkUsers;
	private List<TblBotanicalObject> tblBotanicalObjects;
	private TblImageServer tblImageServer;
	private FrmwrkUser frmwrkUser;
	private TblOrganisation tblOrganisation;
	private List<TblOrganisation> tblOrganisations;

	public TblOrganisation() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=255)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	@Column(length=255)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public byte getGreenhouse() {
		return this.greenhouse;
	}

	public void setGreenhouse(byte greenhouse) {
		this.greenhouse = greenhouse;
	}


	@Column(name="ipen_code", length=5)
	public String getIpenCode() {
		return this.ipenCode;
	}

	public void setIpenCode(String ipenCode) {
		this.ipenCode = ipenCode;
	}


	//bi-directional many-to-one association to Frmwrk_accessOrganisation
	@OneToMany(mappedBy="tblOrganisation")
	public List<Frmwrk_accessOrganisation> getFrmwrkAccessOrganisations() {
		return this.frmwrkAccessOrganisations;
	}

	public void setFrmwrkAccessOrganisations(List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations) {
		this.frmwrkAccessOrganisations = frmwrkAccessOrganisations;
	}

	public Frmwrk_accessOrganisation addFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().add(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setTblOrganisation(this);

		return frmwrkAccessOrganisation;
	}

	public Frmwrk_accessOrganisation removeFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().remove(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setTblOrganisation(null);

		return frmwrkAccessOrganisation;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@OneToMany(mappedBy="tblOrganisation")
	public List<FrmwrkUser> getFrmwrkUsers() {
		return this.frmwrkUsers;
	}

	public void setFrmwrkUsers(List<FrmwrkUser> frmwrkUsers) {
		this.frmwrkUsers = frmwrkUsers;
	}

	public FrmwrkUser addFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().add(frmwrkUser);
		frmwrkUser.setTblOrganisation(this);

		return frmwrkUser;
	}

	public FrmwrkUser removeFrmwrkUser(FrmwrkUser frmwrkUser) {
		getFrmwrkUsers().remove(frmwrkUser);
		frmwrkUser.setTblOrganisation(null);

		return frmwrkUser;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@OneToMany(mappedBy="tblOrganisation")
	public List<TblBotanicalObject> getTblBotanicalObjects() {
		return this.tblBotanicalObjects;
	}

	public void setTblBotanicalObjects(List<TblBotanicalObject> tblBotanicalObjects) {
		this.tblBotanicalObjects = tblBotanicalObjects;
	}

	public TblBotanicalObject addTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().add(tblBotanicalObject);
		tblBotanicalObject.setTblOrganisation(this);

		return tblBotanicalObject;
	}

	public TblBotanicalObject removeTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		getTblBotanicalObjects().remove(tblBotanicalObject);
		tblBotanicalObject.setTblOrganisation(null);

		return tblBotanicalObject;
	}


	//bi-directional one-to-one association to TblImageServer
	@OneToOne(mappedBy="tblOrganisation")
	public TblImageServer getTblImageServer() {
		return this.tblImageServer;
	}

	public void setTblImageServer(TblImageServer tblImageServer) {
		this.tblImageServer = tblImageServer;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="gardener_id")
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}


	//bi-directional many-to-one association to TblOrganisation
	@ManyToOne
	@JoinColumn(name="parent_organisation_id")
	public TblOrganisation getTblOrganisation() {
		return this.tblOrganisation;
	}

	public void setTblOrganisation(TblOrganisation tblOrganisation) {
		this.tblOrganisation = tblOrganisation;
	}


	//bi-directional many-to-one association to TblOrganisation
	@OneToMany(mappedBy="tblOrganisation")
	public List<TblOrganisation> getTblOrganisations() {
		return this.tblOrganisations;
	}

	public void setTblOrganisations(List<TblOrganisation> tblOrganisations) {
		this.tblOrganisations = tblOrganisations;
	}

	public TblOrganisation addTblOrganisation(TblOrganisation tblOrganisation) {
		getTblOrganisations().add(tblOrganisation);
		tblOrganisation.setTblOrganisation(this);

		return tblOrganisation;
	}

	public TblOrganisation removeTblOrganisation(TblOrganisation tblOrganisation) {
		getTblOrganisations().remove(tblOrganisation);
		tblOrganisation.setTblOrganisation(null);

		return tblOrganisation;
	}

}