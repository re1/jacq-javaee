package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_living_plant database table.
 * 
 */
@Entity
@Table(name="tbl_living_plant")
@NamedQuery(name="TblLivingPlant.findAll", query="SELECT t FROM TblLivingPlant t")
public class TblLivingPlant implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int accessionNumber;
	private byte bgci;
	private Date cultivationDate;
	private String cultureNotes;
	private byte indexSeminum;
	private byte ipenLocked;
	private String ipenNumber;
	private String labelAnnotation;
	private int labelSynonymScientificNameId;
	private byte phytoControl;
	private String placeNumber;
	private List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects;
	private List<TblAlternativeAccessionNumber> tblAlternativeAccessionNumbers;
	private List<TblCertificate> tblCertificates;
	private TblAcquisitionDate tblAcquisitionDate;
	private TblBotanicalObject tblBotanicalObject;
	private TblCultivar tblCultivar;
	private TblIndexSeminumType tblIndexSeminumType;
	private List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePages;
	private List<TblRelevancy> tblRelevancies;

	public TblLivingPlant() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="accession_number", nullable=false)
	public int getAccessionNumber() {
		return this.accessionNumber;
	}

	public void setAccessionNumber(int accessionNumber) {
		this.accessionNumber = accessionNumber;
	}


	@Column(nullable=false)
	public byte getBgci() {
		return this.bgci;
	}

	public void setBgci(byte bgci) {
		this.bgci = bgci;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="cultivation_date")
	public Date getCultivationDate() {
		return this.cultivationDate;
	}

	public void setCultivationDate(Date cultivationDate) {
		this.cultivationDate = cultivationDate;
	}


	@Lob
	@Column(name="culture_notes")
	public String getCultureNotes() {
		return this.cultureNotes;
	}

	public void setCultureNotes(String cultureNotes) {
		this.cultureNotes = cultureNotes;
	}


	@Column(name="index_seminum", nullable=false)
	public byte getIndexSeminum() {
		return this.indexSeminum;
	}

	public void setIndexSeminum(byte indexSeminum) {
		this.indexSeminum = indexSeminum;
	}


	@Column(name="ipen_locked", nullable=false)
	public byte getIpenLocked() {
		return this.ipenLocked;
	}

	public void setIpenLocked(byte ipenLocked) {
		this.ipenLocked = ipenLocked;
	}


	@Column(name="ipen_number", length=20)
	public String getIpenNumber() {
		return this.ipenNumber;
	}

	public void setIpenNumber(String ipenNumber) {
		this.ipenNumber = ipenNumber;
	}


	@Lob
	@Column(name="label_annotation")
	public String getLabelAnnotation() {
		return this.labelAnnotation;
	}

	public void setLabelAnnotation(String labelAnnotation) {
		this.labelAnnotation = labelAnnotation;
	}


	@Column(name="label_synonym_scientific_name_id")
	public int getLabelSynonymScientificNameId() {
		return this.labelSynonymScientificNameId;
	}

	public void setLabelSynonymScientificNameId(int labelSynonymScientificNameId) {
		this.labelSynonymScientificNameId = labelSynonymScientificNameId;
	}


	@Column(name="phyto_control", nullable=false)
	public byte getPhytoControl() {
		return this.phytoControl;
	}

	public void setPhytoControl(byte phytoControl) {
		this.phytoControl = phytoControl;
	}


	@Column(name="place_number", length=20)
	public String getPlaceNumber() {
		return this.placeNumber;
	}

	public void setPlaceNumber(String placeNumber) {
		this.placeNumber = placeNumber;
	}


	//bi-directional many-to-one association to Frmwrk_accessBotanicalObject
	@OneToMany(mappedBy="tblLivingPlant")
	public List<Frmwrk_accessBotanicalObject> getFrmwrkAccessBotanicalObjects() {
		return this.frmwrkAccessBotanicalObjects;
	}

	public void setFrmwrkAccessBotanicalObjects(List<Frmwrk_accessBotanicalObject> frmwrkAccessBotanicalObjects) {
		this.frmwrkAccessBotanicalObjects = frmwrkAccessBotanicalObjects;
	}

	public Frmwrk_accessBotanicalObject addFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().add(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setTblLivingPlant(this);

		return frmwrkAccessBotanicalObject;
	}

	public Frmwrk_accessBotanicalObject removeFrmwrkAccessBotanicalObject(Frmwrk_accessBotanicalObject frmwrkAccessBotanicalObject) {
		getFrmwrkAccessBotanicalObjects().remove(frmwrkAccessBotanicalObject);
		frmwrkAccessBotanicalObject.setTblLivingPlant(null);

		return frmwrkAccessBotanicalObject;
	}


	//bi-directional many-to-one association to TblAlternativeAccessionNumber
	@OneToMany(mappedBy="tblLivingPlant")
	public List<TblAlternativeAccessionNumber> getTblAlternativeAccessionNumbers() {
		return this.tblAlternativeAccessionNumbers;
	}

	public void setTblAlternativeAccessionNumbers(List<TblAlternativeAccessionNumber> tblAlternativeAccessionNumbers) {
		this.tblAlternativeAccessionNumbers = tblAlternativeAccessionNumbers;
	}

	public TblAlternativeAccessionNumber addTblAlternativeAccessionNumber(TblAlternativeAccessionNumber tblAlternativeAccessionNumber) {
		getTblAlternativeAccessionNumbers().add(tblAlternativeAccessionNumber);
		tblAlternativeAccessionNumber.setTblLivingPlant(this);

		return tblAlternativeAccessionNumber;
	}

	public TblAlternativeAccessionNumber removeTblAlternativeAccessionNumber(TblAlternativeAccessionNumber tblAlternativeAccessionNumber) {
		getTblAlternativeAccessionNumbers().remove(tblAlternativeAccessionNumber);
		tblAlternativeAccessionNumber.setTblLivingPlant(null);

		return tblAlternativeAccessionNumber;
	}


	//bi-directional many-to-one association to TblCertificate
	@OneToMany(mappedBy="tblLivingPlant")
	public List<TblCertificate> getTblCertificates() {
		return this.tblCertificates;
	}

	public void setTblCertificates(List<TblCertificate> tblCertificates) {
		this.tblCertificates = tblCertificates;
	}

	public TblCertificate addTblCertificate(TblCertificate tblCertificate) {
		getTblCertificates().add(tblCertificate);
		tblCertificate.setTblLivingPlant(this);

		return tblCertificate;
	}

	public TblCertificate removeTblCertificate(TblCertificate tblCertificate) {
		getTblCertificates().remove(tblCertificate);
		tblCertificate.setTblLivingPlant(null);

		return tblCertificate;
	}


	//bi-directional many-to-one association to TblAcquisitionDate
	@ManyToOne
	@JoinColumn(name="incoming_date_id")
	public TblAcquisitionDate getTblAcquisitionDate() {
		return this.tblAcquisitionDate;
	}

	public void setTblAcquisitionDate(TblAcquisitionDate tblAcquisitionDate) {
		this.tblAcquisitionDate = tblAcquisitionDate;
	}


	//bi-directional one-to-one association to TblBotanicalObject
	@OneToOne
	@JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblCultivar
	@ManyToOne
	@JoinColumn(name="cultivar_id")
	public TblCultivar getTblCultivar() {
		return this.tblCultivar;
	}

	public void setTblCultivar(TblCultivar tblCultivar) {
		this.tblCultivar = tblCultivar;
	}


	//bi-directional many-to-one association to TblIndexSeminumType
	@ManyToOne
	@JoinColumn(name="index_seminum_type_id")
	public TblIndexSeminumType getTblIndexSeminumType() {
		return this.tblIndexSeminumType;
	}

	public void setTblIndexSeminumType(TblIndexSeminumType tblIndexSeminumType) {
		this.tblIndexSeminumType = tblIndexSeminumType;
	}


	//bi-directional many-to-one association to TblLivingPlantTreeRecordFilePage
	@OneToMany(mappedBy="tblLivingPlant")
	public List<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePages() {
		return this.tblLivingPlantTreeRecordFilePages;
	}

	public void setTblLivingPlantTreeRecordFilePages(List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePages) {
		this.tblLivingPlantTreeRecordFilePages = tblLivingPlantTreeRecordFilePages;
	}

	public TblLivingPlantTreeRecordFilePage addTblLivingPlantTreeRecordFilePage(TblLivingPlantTreeRecordFilePage tblLivingPlantTreeRecordFilePage) {
		getTblLivingPlantTreeRecordFilePages().add(tblLivingPlantTreeRecordFilePage);
		tblLivingPlantTreeRecordFilePage.setTblLivingPlant(this);

		return tblLivingPlantTreeRecordFilePage;
	}

	public TblLivingPlantTreeRecordFilePage removeTblLivingPlantTreeRecordFilePage(TblLivingPlantTreeRecordFilePage tblLivingPlantTreeRecordFilePage) {
		getTblLivingPlantTreeRecordFilePages().remove(tblLivingPlantTreeRecordFilePage);
		tblLivingPlantTreeRecordFilePage.setTblLivingPlant(null);

		return tblLivingPlantTreeRecordFilePage;
	}


	//bi-directional many-to-one association to TblRelevancy
	@OneToMany(mappedBy="tblLivingPlant")
	public List<TblRelevancy> getTblRelevancies() {
		return this.tblRelevancies;
	}

	public void setTblRelevancies(List<TblRelevancy> tblRelevancies) {
		this.tblRelevancies = tblRelevancies;
	}

	public TblRelevancy addTblRelevancy(TblRelevancy tblRelevancy) {
		getTblRelevancies().add(tblRelevancy);
		tblRelevancy.setTblLivingPlant(this);

		return tblRelevancy;
	}

	public TblRelevancy removeTblRelevancy(TblRelevancy tblRelevancy) {
		getTblRelevancies().remove(tblRelevancy);
		tblRelevancy.setTblLivingPlant(null);

		return tblRelevancy;
	}

}