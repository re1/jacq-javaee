package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frmwrk_AuthItem database table.
 * 
 */
@Entity
@Table(name="frmwrk_AuthItem")
@NamedQuery(name="Frmwrk_AuthItem.findAll", query="SELECT f FROM Frmwrk_AuthItem f")
public class Frmwrk_AuthItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String bizrule;
	private String data;
	private String description;
	private int type;
	private List<Frmwrk_AuthAssignment> frmwrkAuthAssignments;
	private List<Frmwrk_AuthItem> frmwrkAuthItems1;
	private List<Frmwrk_AuthItem> frmwrkAuthItems2;
	private List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects;
	private List<Frmwrk_accessClassification> frmwrkAccessClassifications;
	private List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations;

	public Frmwrk_AuthItem() {
	}


	@Id
	@Column(unique=true, nullable=false, length=64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Lob
	public String getBizrule() {
		return this.bizrule;
	}

	public void setBizrule(String bizrule) {
		this.bizrule = bizrule;
	}


	@Lob
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}


	//bi-directional many-to-one association to Frmwrk_AuthAssignment
	@OneToMany(mappedBy="frmwrkAuthItem")
	public List<Frmwrk_AuthAssignment> getFrmwrkAuthAssignments() {
		return this.frmwrkAuthAssignments;
	}

	public void setFrmwrkAuthAssignments(List<Frmwrk_AuthAssignment> frmwrkAuthAssignments) {
		this.frmwrkAuthAssignments = frmwrkAuthAssignments;
	}

	public Frmwrk_AuthAssignment addFrmwrkAuthAssignment(Frmwrk_AuthAssignment frmwrkAuthAssignment) {
		getFrmwrkAuthAssignments().add(frmwrkAuthAssignment);
		frmwrkAuthAssignment.setFrmwrkAuthItem(this);

		return frmwrkAuthAssignment;
	}

	public Frmwrk_AuthAssignment removeFrmwrkAuthAssignment(Frmwrk_AuthAssignment frmwrkAuthAssignment) {
		getFrmwrkAuthAssignments().remove(frmwrkAuthAssignment);
		frmwrkAuthAssignment.setFrmwrkAuthItem(null);

		return frmwrkAuthAssignment;
	}


	//bi-directional many-to-many association to Frmwrk_AuthItem
	@ManyToMany
	@JoinTable(
		name="frmwrk_AuthItemChild"
		, joinColumns={
			@JoinColumn(name="child", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="parent", nullable=false)
			}
		)
	public List<Frmwrk_AuthItem> getFrmwrkAuthItems1() {
		return this.frmwrkAuthItems1;
	}

	public void setFrmwrkAuthItems1(List<Frmwrk_AuthItem> frmwrkAuthItems1) {
		this.frmwrkAuthItems1 = frmwrkAuthItems1;
	}


	//bi-directional many-to-many association to Frmwrk_AuthItem
	@ManyToMany(mappedBy="frmwrkAuthItems1")
	public List<Frmwrk_AuthItem> getFrmwrkAuthItems2() {
		return this.frmwrkAuthItems2;
	}

	public void setFrmwrkAuthItems2(List<Frmwrk_AuthItem> frmwrkAuthItems2) {
		this.frmwrkAuthItems2 = frmwrkAuthItems2;
	}


	//bi-directional many-to-one association to Frmwrk_accessBotanicalObject
	@OneToMany(mappedBy="frmwrkAuthItem")
	public List<Frmwrk_accessBotanicalObject> getFrmwrkAccessBotanicalObjects() {
		return this.frmwrkAccessBotanicalObjects;
	}

	public void setFrmwrkAccessBotanicalObjects(List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects) {
		this.frmwrkAccessBotanicalObjects = frmwrkAccessBotanicalObjects;
	}

	public Frmwrk_accessBotanicalObject addFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().add(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setFrmwrkAuthItem(this);

		return frmwrkAccessBotanicalObject;
	}

	public Frmwrk_accessBotanicalObject removeFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().remove(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setFrmwrkAuthItem(null);

		return frmwrkAccessBotanicalObject;
	}


	//bi-directional many-to-one association to Frmwrk_accessClassification
	@OneToMany(mappedBy="frmwrkAuthItem")
	public List<Frmwrk_accessClassification> getFrmwrkAccessClassifications() {
		return this.frmwrkAccessClassifications;
	}

	public void setFrmwrkAccessClassifications(List<Frmwrk_accessClassification> frmwrkAccessClassifications) {
		this.frmwrkAccessClassifications = frmwrkAccessClassifications;
	}

	public Frmwrk_accessClassification addFrmwrkAccessClassification(Frmwrk_accessClassification frmwrkAccessClassification) {
		getFrmwrkAccessClassifications().add(frmwrkAccessClassification);
		frmwrkAccessClassification.setFrmwrkAuthItem(this);

		return frmwrkAccessClassification;
	}

	public Frmwrk_accessClassification removeFrmwrkAccessClassification(Frmwrk_accessClassification frmwrkAccessClassification) {
		getFrmwrkAccessClassifications().remove(frmwrkAccessClassification);
		frmwrkAccessClassification.setFrmwrkAuthItem(null);

		return frmwrkAccessClassification;
	}


	//bi-directional many-to-one association to Frmwrk_accessOrganisation
	@OneToMany(mappedBy="frmwrkAuthItem")
	public List<Frmwrk_accessOrganisation> getFrmwrkAccessOrganisations() {
		return this.frmwrkAccessOrganisations;
	}

	public void setFrmwrkAccessOrganisations(List<Frmwrk_accessOrganisation> frmwrkAccessOrganisations) {
		this.frmwrkAccessOrganisations = frmwrkAccessOrganisations;
	}

	public Frmwrk_accessOrganisation addFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().add(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setFrmwrkAuthItem(this);

		return frmwrkAccessOrganisation;
	}

	public Frmwrk_accessOrganisation removeFrmwrkAccessOrganisation(Frmwrk_accessOrganisation frmwrkAccessOrganisation) {
		getFrmwrkAccessOrganisations().remove(frmwrkAccessOrganisation);
		frmwrkAccessOrganisation.setFrmwrkAuthItem(null);

		return frmwrkAccessOrganisation;
	}

}