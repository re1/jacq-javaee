package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_botanical_object database table.
 * 
 */
@Entity
@Table(name="tbl_botanical_object")
@NamedQuery(name="TblBotanicalObject.findAll", query="SELECT t FROM TblBotanicalObject t")
public class TblBotanicalObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte accessible;
	private String annotation;
	private Date determinationDate;
	private String habitat;
	private String habitus;
	private Date recordingDate;
	private byte redetermine;
	private int scientificNameId;
	private byte separated;
	private TblAcquisitionEvent tblAcquisitionEvent;
	private TblIdentStatus tblIdentStatus;
	private TblOrganisation tblOrganisation;
	private TblPerson tblPerson;
	private TblPhenology tblPhenology;
	private List<TblBotanicalObjectSex> tblBotanicalObjectSexs;
	private TblDiaspora tblDiaspora;
	private List<TblImportProperty> tblImportProperties;
	private List<TblIndexSeminumContent> tblIndexSeminumContents;
	private List<TblInventoryObject> tblInventoryObjects;
	private List<TblLabelType> tblLabelTypes;
	private TblLivingPlant tblLivingPlant;
	private List<TblSeparation> tblSeparations;
	private List<TblSpecimen> tblSpecimens;

	public TblBotanicalObject() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false)
	public byte getAccessible() {
		return this.accessible;
	}

	public void setAccessible(byte accessible) {
		this.accessible = accessible;
	}


	@Lob
	public String getAnnotation() {
		return this.annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="determination_date")
	public Date getDeterminationDate() {
		return this.determinationDate;
	}

	public void setDeterminationDate(Date determinationDate) {
		this.determinationDate = determinationDate;
	}


	@Column(length=45)
	public String getHabitat() {
		return this.habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}


	@Column(length=45)
	public String getHabitus() {
		return this.habitus;
	}

	public void setHabitus(String habitus) {
		this.habitus = habitus;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="recording_date", nullable=false)
	public Date getRecordingDate() {
		return this.recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}


	@Column(nullable=false)
	public byte getRedetermine() {
		return this.redetermine;
	}

	public void setRedetermine(byte redetermine) {
		this.redetermine = redetermine;
	}


	@Column(name="scientific_name_id", nullable=false)
	public int getScientificNameId() {
		return this.scientificNameId;
	}

	public void setScientificNameId(int scientificNameId) {
		this.scientificNameId = scientificNameId;
	}


	@Column(nullable=false)
	public byte getSeparated() {
		return this.separated;
	}

	public void setSeparated(byte separated) {
		this.separated = separated;
	}


	//bi-directional many-to-one association to TblAcquisitionEvent
	@ManyToOne
	@JoinColumn(name="acquisition_event_id", nullable=false)
	public TblAcquisitionEvent getTblAcquisitionEvent() {
		return this.tblAcquisitionEvent;
	}

	public void setTblAcquisitionEvent(TblAcquisitionEvent tblAcquisitionEvent) {
		this.tblAcquisitionEvent = tblAcquisitionEvent;
	}


	//bi-directional many-to-one association to TblIdentStatus
	@ManyToOne
	@JoinColumn(name="ident_status_id")
	public TblIdentStatus getTblIdentStatus() {
		return this.tblIdentStatus;
	}

	public void setTblIdentStatus(TblIdentStatus tblIdentStatus) {
		this.tblIdentStatus = tblIdentStatus;
	}


	//bi-directional many-to-one association to TblOrganisation
	@ManyToOne
	@JoinColumn(name="organisation_id")
	public TblOrganisation getTblOrganisation() {
		return this.tblOrganisation;
	}

	public void setTblOrganisation(TblOrganisation tblOrganisation) {
		this.tblOrganisation = tblOrganisation;
	}


	//bi-directional many-to-one association to TblPerson
	@ManyToOne
	@JoinColumn(name="determined_by_id")
	public TblPerson getTblPerson() {
		return this.tblPerson;
	}

	public void setTblPerson(TblPerson tblPerson) {
		this.tblPerson = tblPerson;
	}


	//bi-directional many-to-one association to TblPhenology
	@ManyToOne
	@JoinColumn(name="phenology_id")
	public TblPhenology getTblPhenology() {
		return this.tblPhenology;
	}

	public void setTblPhenology(TblPhenology tblPhenology) {
		this.tblPhenology = tblPhenology;
	}


	//bi-directional many-to-one association to TblBotanicalObjectSex
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblBotanicalObjectSex> getTblBotanicalObjectSexs() {
		return this.tblBotanicalObjectSexs;
	}

	public void setTblBotanicalObjectSexs(List<TblBotanicalObjectSex> tblBotanicalObjectSexs) {
		this.tblBotanicalObjectSexs = tblBotanicalObjectSexs;
	}

	public TblBotanicalObjectSex addTblBotanicalObjectSex(TblBotanicalObjectSex tblBotanicalObjectSex) {
		getTblBotanicalObjectSexs().add(tblBotanicalObjectSex);
		tblBotanicalObjectSex.setTblBotanicalObject(this);

		return tblBotanicalObjectSex;
	}

	public TblBotanicalObjectSex removeTblBotanicalObjectSex(TblBotanicalObjectSex tblBotanicalObjectSex) {
		getTblBotanicalObjectSexs().remove(tblBotanicalObjectSex);
		tblBotanicalObjectSex.setTblBotanicalObject(null);

		return tblBotanicalObjectSex;
	}


	//bi-directional one-to-one association to TblDiaspora
	@OneToOne(mappedBy="tblBotanicalObject")
	public TblDiaspora getTblDiaspora() {
		return this.tblDiaspora;
	}

	public void setTblDiaspora(TblDiaspora tblDiaspora) {
		this.tblDiaspora = tblDiaspora;
	}


	//bi-directional many-to-one association to TblImportProperty
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblImportProperty> getTblImportProperties() {
		return this.tblImportProperties;
	}

	public void setTblImportProperties(List<TblImportProperty> tblImportProperties) {
		this.tblImportProperties = tblImportProperties;
	}

	public TblImportProperty addTblImportProperty(TblImportProperty tblImportProperty) {
		getTblImportProperties().add(tblImportProperty);
		tblImportProperty.setTblBotanicalObject(this);

		return tblImportProperty;
	}

	public TblImportProperty removeTblImportProperty(TblImportProperty tblImportProperty) {
		getTblImportProperties().remove(tblImportProperty);
		tblImportProperty.setTblBotanicalObject(null);

		return tblImportProperty;
	}


	//bi-directional many-to-one association to TblIndexSeminumContent
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblIndexSeminumContent> getTblIndexSeminumContents() {
		return this.tblIndexSeminumContents;
	}

	public void setTblIndexSeminumContents(List<TblIndexSeminumContent> tblIndexSeminumContents) {
		this.tblIndexSeminumContents = tblIndexSeminumContents;
	}

	public TblIndexSeminumContent addTblIndexSeminumContent(TblIndexSeminumContent tblIndexSeminumContent) {
		getTblIndexSeminumContents().add(tblIndexSeminumContent);
		tblIndexSeminumContent.setTblBotanicalObject(this);

		return tblIndexSeminumContent;
	}

	public TblIndexSeminumContent removeTblIndexSeminumContent(TblIndexSeminumContent tblIndexSeminumContent) {
		getTblIndexSeminumContents().remove(tblIndexSeminumContent);
		tblIndexSeminumContent.setTblBotanicalObject(null);

		return tblIndexSeminumContent;
	}


	//bi-directional many-to-one association to TblInventoryObject
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblInventoryObject> getTblInventoryObjects() {
		return this.tblInventoryObjects;
	}

	public void setTblInventoryObjects(List<TblInventoryObject> tblInventoryObjects) {
		this.tblInventoryObjects = tblInventoryObjects;
	}

	public TblInventoryObject addTblInventoryObject(TblInventoryObject tblInventoryObject) {
		getTblInventoryObjects().add(tblInventoryObject);
		tblInventoryObject.setTblBotanicalObject(this);

		return tblInventoryObject;
	}

	public TblInventoryObject removeTblInventoryObject(TblInventoryObject tblInventoryObject) {
		getTblInventoryObjects().remove(tblInventoryObject);
		tblInventoryObject.setTblBotanicalObject(null);

		return tblInventoryObject;
	}


	//bi-directional many-to-many association to TblLabelType
	@ManyToMany(mappedBy="tblBotanicalObjects")
	public List<TblLabelType> getTblLabelTypes() {
		return this.tblLabelTypes;
	}

	public void setTblLabelTypes(List<TblLabelType> tblLabelTypes) {
		this.tblLabelTypes = tblLabelTypes;
	}


	//bi-directional one-to-one association to TblLivingPlant
	@OneToOne(mappedBy="tblBotanicalObject")
	public TblLivingPlant getTblLivingPlant() {
		return this.tblLivingPlant;
	}

	public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
		this.tblLivingPlant = tblLivingPlant;
	}


	//bi-directional many-to-one association to TblSeparation
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblSeparation> getTblSeparations() {
		return this.tblSeparations;
	}

	public void setTblSeparations(List<TblSeparation> tblSeparations) {
		this.tblSeparations = tblSeparations;
	}

	public TblSeparation addTblSeparation(TblSeparation tblSeparation) {
		getTblSeparations().add(tblSeparation);
		tblSeparation.setTblBotanicalObject(this);

		return tblSeparation;
	}

	public TblSeparation removeTblSeparation(TblSeparation tblSeparation) {
		getTblSeparations().remove(tblSeparation);
		tblSeparation.setTblBotanicalObject(null);

		return tblSeparation;
	}


	//bi-directional many-to-one association to TblSpecimen
	@OneToMany(mappedBy="tblBotanicalObject")
	public List<TblSpecimen> getTblSpecimens() {
		return this.tblSpecimens;
	}

	public void setTblSpecimens(List<TblSpecimen> tblSpecimens) {
		this.tblSpecimens = tblSpecimens;
	}

	public TblSpecimen addTblSpecimen(TblSpecimen tblSpecimen) {
		getTblSpecimens().add(tblSpecimen);
		tblSpecimen.setTblBotanicalObject(this);

		return tblSpecimen;
	}

	public TblSpecimen removeTblSpecimen(TblSpecimen tblSpecimen) {
		getTblSpecimens().remove(tblSpecimen);
		tblSpecimen.setTblBotanicalObject(null);

		return tblSpecimen;
	}

}