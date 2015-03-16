package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_index_seminum_content database table.
 * 
 */
@Entity
@Table(name="tbl_index_seminum_content")
@NamedQuery(name="TblIndexSeminumContent.findAll", query="SELECT t FROM TblIndexSeminumContent t")
public class TblIndexSeminumContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private int indexSeminumContentId;
	private int accessionNumber;
	private String acquisitionDate;
	private String acquisitionLocation;
	private String acquisitionNumber;
	private int altitudeMax;
	private int altitudeMin;
	private String family;
	private String habitat;
	private String indexSeminumType;
	private String ipenNumber;
	private String latitude;
	private String longitude;
	private String scientificName;
	private Timestamp timestamp;
	private TblBotanicalObject tblBotanicalObject;
	private TblIndexSeminumRevision tblIndexSeminumRevision;
	private List<TblIndexSeminumPerson> tblIndexSeminumPersons;

	public TblIndexSeminumContent() {
	}


	@Id
	@Column(name="index_seminum_content_id", unique=true, nullable=false)
	public int getIndexSeminumContentId() {
		return this.indexSeminumContentId;
	}

	public void setIndexSeminumContentId(int indexSeminumContentId) {
		this.indexSeminumContentId = indexSeminumContentId;
	}


	@Column(name="accession_number", nullable=false)
	public int getAccessionNumber() {
		return this.accessionNumber;
	}

	public void setAccessionNumber(int accessionNumber) {
		this.accessionNumber = accessionNumber;
	}


	@Column(name="acquisition_date", length=20)
	public String getAcquisitionDate() {
		return this.acquisitionDate;
	}

	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}


	@Lob
	@Column(name="acquisition_location")
	public String getAcquisitionLocation() {
		return this.acquisitionLocation;
	}

	public void setAcquisitionLocation(String acquisitionLocation) {
		this.acquisitionLocation = acquisitionLocation;
	}


	@Lob
	@Column(name="acquisition_number")
	public String getAcquisitionNumber() {
		return this.acquisitionNumber;
	}

	public void setAcquisitionNumber(String acquisitionNumber) {
		this.acquisitionNumber = acquisitionNumber;
	}


	@Column(name="altitude_max")
	public int getAltitudeMax() {
		return this.altitudeMax;
	}

	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}


	@Column(name="altitude_min")
	public int getAltitudeMin() {
		return this.altitudeMin;
	}

	public void setAltitudeMin(int altitudeMin) {
		this.altitudeMin = altitudeMin;
	}


	@Lob
	@Column(nullable=false)
	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}


	@Column(length=45)
	public String getHabitat() {
		return this.habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}


	@Column(name="index_seminum_type", nullable=false, length=3)
	public String getIndexSeminumType() {
		return this.indexSeminumType;
	}

	public void setIndexSeminumType(String indexSeminumType) {
		this.indexSeminumType = indexSeminumType;
	}


	@Column(name="ipen_number", nullable=false, length=28)
	public String getIpenNumber() {
		return this.ipenNumber;
	}

	public void setIpenNumber(String ipenNumber) {
		this.ipenNumber = ipenNumber;
	}


	@Column(length=14)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	@Column(length=14)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	@Lob
	@Column(name="scientific_name", nullable=false)
	public String getScientificName() {
		return this.scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblBotanicalObject
	@ManyToOne
	@JoinColumn(name="botanical_object_id", nullable=false)
	public TblBotanicalObject getTblBotanicalObject() {
		return this.tblBotanicalObject;
	}

	public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
		this.tblBotanicalObject = tblBotanicalObject;
	}


	//bi-directional many-to-one association to TblIndexSeminumRevision
	@ManyToOne
	@JoinColumn(name="index_seminum_revision_id", nullable=false)
	public TblIndexSeminumRevision getTblIndexSeminumRevision() {
		return this.tblIndexSeminumRevision;
	}

	public void setTblIndexSeminumRevision(TblIndexSeminumRevision tblIndexSeminumRevision) {
		this.tblIndexSeminumRevision = tblIndexSeminumRevision;
	}


	//bi-directional many-to-one association to TblIndexSeminumPerson
	@OneToMany(mappedBy="tblIndexSeminumContent")
	public List<TblIndexSeminumPerson> getTblIndexSeminumPersons() {
		return this.tblIndexSeminumPersons;
	}

	public void setTblIndexSeminumPersons(List<TblIndexSeminumPerson> tblIndexSeminumPersons) {
		this.tblIndexSeminumPersons = tblIndexSeminumPersons;
	}

	public TblIndexSeminumPerson addTblIndexSeminumPerson(TblIndexSeminumPerson tblIndexSeminumPerson) {
		getTblIndexSeminumPersons().add(tblIndexSeminumPerson);
		tblIndexSeminumPerson.setTblIndexSeminumContent(this);

		return tblIndexSeminumPerson;
	}

	public TblIndexSeminumPerson removeTblIndexSeminumPerson(TblIndexSeminumPerson tblIndexSeminumPerson) {
		getTblIndexSeminumPersons().remove(tblIndexSeminumPerson);
		tblIndexSeminumPerson.setTblIndexSeminumContent(null);

		return tblIndexSeminumPerson;
	}

}