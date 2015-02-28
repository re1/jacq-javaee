package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_specimens_import database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_import")
@NamedQuery(name="TblSpecimensImport.findAll", query="SELECT t FROM TblSpecimensImport t")
public class TblSpecimensImport implements Serializable {
	private static final long serialVersionUID = 1L;
	private int specimen_ID;
	private byte accessible;
	private Timestamp aktualdatum;
	private String altNumber;
	private int altitudeMax;
	private int altitudeMin;
	private String bemerkungen;
	private String bezirk;
	private byte checked;
	private int collectionID;
	private String collNummer;
	private int coord_E;
	private int coord_N;
	private int coord_S;
	private int coord_W;
	private String datum;
	private String datum2;
	private String det;
	private byte digitalImage;
	private byte digitalImageObs;
	private int e_Min;
	private double e_Sec;
	private Timestamp eingabedatum;
	private double exactness;
	private String foreign_db_ID;
	private String fundort;
	private String fundort_engl;
	private String garten;
	private String habitat;
	private String habitus;
	private String herbNummer;
	private int identstatusID;
	private byte label;
	private int n_Min;
	private double n_Sec;
	private int nationID;
	private String ncbiAccession;
	private int nummer;
	private byte observation;
	private int provinceID;
	private int quadrant;
	private int quadrantSub;
	private int s_Min;
	private double s_Sec;
	private int sammler_2ID;
	private int sammlerID;
	private String seriesNumber;
	private int seriesID;
	private String taxonAlt;
	private int taxonID;
	private String typified;
	private int typusID;
	private int userID;
	private int voucherID;
	private int w_Min;
	private double w_Sec;

	public TblSpecimensImport() {
	}


	@Id
	public int getSpecimen_ID() {
		return this.specimen_ID;
	}

	public void setSpecimen_ID(int specimen_ID) {
		this.specimen_ID = specimen_ID;
	}


	public byte getAccessible() {
		return this.accessible;
	}

	public void setAccessible(byte accessible) {
		this.accessible = accessible;
	}


	public Timestamp getAktualdatum() {
		return this.aktualdatum;
	}

	public void setAktualdatum(Timestamp aktualdatum) {
		this.aktualdatum = aktualdatum;
	}


	@Column(name="alt_number")
	public String getAltNumber() {
		return this.altNumber;
	}

	public void setAltNumber(String altNumber) {
		this.altNumber = altNumber;
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
	@Column(name="Bemerkungen")
	public String getBemerkungen() {
		return this.bemerkungen;
	}

	public void setBemerkungen(String bemerkungen) {
		this.bemerkungen = bemerkungen;
	}


	@Column(name="Bezirk")
	public String getBezirk() {
		return this.bezirk;
	}

	public void setBezirk(String bezirk) {
		this.bezirk = bezirk;
	}


	public byte getChecked() {
		return this.checked;
	}

	public void setChecked(byte checked) {
		this.checked = checked;
	}


	public int getCollectionID() {
		return this.collectionID;
	}

	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}


	@Column(name="CollNummer")
	public String getCollNummer() {
		return this.collNummer;
	}

	public void setCollNummer(String collNummer) {
		this.collNummer = collNummer;
	}


	@Column(name="Coord_E")
	public int getCoord_E() {
		return this.coord_E;
	}

	public void setCoord_E(int coord_E) {
		this.coord_E = coord_E;
	}


	@Column(name="Coord_N")
	public int getCoord_N() {
		return this.coord_N;
	}

	public void setCoord_N(int coord_N) {
		this.coord_N = coord_N;
	}


	@Column(name="Coord_S")
	public int getCoord_S() {
		return this.coord_S;
	}

	public void setCoord_S(int coord_S) {
		this.coord_S = coord_S;
	}


	@Column(name="Coord_W")
	public int getCoord_W() {
		return this.coord_W;
	}

	public void setCoord_W(int coord_W) {
		this.coord_W = coord_W;
	}


	@Column(name="Datum")
	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}


	@Column(name="Datum2")
	public String getDatum2() {
		return this.datum2;
	}

	public void setDatum2(String datum2) {
		this.datum2 = datum2;
	}


	public String getDet() {
		return this.det;
	}

	public void setDet(String det) {
		this.det = det;
	}


	@Column(name="digital_image")
	public byte getDigitalImage() {
		return this.digitalImage;
	}

	public void setDigitalImage(byte digitalImage) {
		this.digitalImage = digitalImage;
	}


	@Column(name="digital_image_obs")
	public byte getDigitalImageObs() {
		return this.digitalImageObs;
	}

	public void setDigitalImageObs(byte digitalImageObs) {
		this.digitalImageObs = digitalImageObs;
	}


	@Column(name="E_Min")
	public int getE_Min() {
		return this.e_Min;
	}

	public void setE_Min(int e_Min) {
		this.e_Min = e_Min;
	}


	@Column(name="E_Sec")
	public double getE_Sec() {
		return this.e_Sec;
	}

	public void setE_Sec(double e_Sec) {
		this.e_Sec = e_Sec;
	}


	public Timestamp getEingabedatum() {
		return this.eingabedatum;
	}

	public void setEingabedatum(Timestamp eingabedatum) {
		this.eingabedatum = eingabedatum;
	}


	public double getExactness() {
		return this.exactness;
	}

	public void setExactness(double exactness) {
		this.exactness = exactness;
	}


	@Lob
	public String getForeign_db_ID() {
		return this.foreign_db_ID;
	}

	public void setForeign_db_ID(String foreign_db_ID) {
		this.foreign_db_ID = foreign_db_ID;
	}


	@Lob
	@Column(name="Fundort")
	public String getFundort() {
		return this.fundort;
	}

	public void setFundort(String fundort) {
		this.fundort = fundort;
	}


	@Lob
	@Column(name="Fundort_engl")
	public String getFundort_engl() {
		return this.fundort_engl;
	}

	public void setFundort_engl(String fundort_engl) {
		this.fundort_engl = fundort_engl;
	}


	public String getGarten() {
		return this.garten;
	}

	public void setGarten(String garten) {
		this.garten = garten;
	}


	@Lob
	public String getHabitat() {
		return this.habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}


	@Lob
	public String getHabitus() {
		return this.habitus;
	}

	public void setHabitus(String habitus) {
		this.habitus = habitus;
	}


	@Column(name="HerbNummer")
	public String getHerbNummer() {
		return this.herbNummer;
	}

	public void setHerbNummer(String herbNummer) {
		this.herbNummer = herbNummer;
	}


	public int getIdentstatusID() {
		return this.identstatusID;
	}

	public void setIdentstatusID(int identstatusID) {
		this.identstatusID = identstatusID;
	}


	public byte getLabel() {
		return this.label;
	}

	public void setLabel(byte label) {
		this.label = label;
	}


	@Column(name="N_Min")
	public int getN_Min() {
		return this.n_Min;
	}

	public void setN_Min(int n_Min) {
		this.n_Min = n_Min;
	}


	@Column(name="N_Sec")
	public double getN_Sec() {
		return this.n_Sec;
	}

	public void setN_Sec(double n_Sec) {
		this.n_Sec = n_Sec;
	}


	@Column(name="NationID")
	public int getNationID() {
		return this.nationID;
	}

	public void setNationID(int nationID) {
		this.nationID = nationID;
	}


	@Column(name="ncbi_accession")
	public String getNcbiAccession() {
		return this.ncbiAccession;
	}

	public void setNcbiAccession(String ncbiAccession) {
		this.ncbiAccession = ncbiAccession;
	}


	@Column(name="Nummer")
	public int getNummer() {
		return this.nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}


	public byte getObservation() {
		return this.observation;
	}

	public void setObservation(byte observation) {
		this.observation = observation;
	}


	public int getProvinceID() {
		return this.provinceID;
	}

	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}


	public int getQuadrant() {
		return this.quadrant;
	}

	public void setQuadrant(int quadrant) {
		this.quadrant = quadrant;
	}


	@Column(name="quadrant_sub")
	public int getQuadrantSub() {
		return this.quadrantSub;
	}

	public void setQuadrantSub(int quadrantSub) {
		this.quadrantSub = quadrantSub;
	}


	@Column(name="S_Min")
	public int getS_Min() {
		return this.s_Min;
	}

	public void setS_Min(int s_Min) {
		this.s_Min = s_Min;
	}


	@Column(name="S_Sec")
	public double getS_Sec() {
		return this.s_Sec;
	}

	public void setS_Sec(double s_Sec) {
		this.s_Sec = s_Sec;
	}


	@Column(name="Sammler_2ID")
	public int getSammler_2ID() {
		return this.sammler_2ID;
	}

	public void setSammler_2ID(int sammler_2ID) {
		this.sammler_2ID = sammler_2ID;
	}


	@Column(name="SammlerID")
	public int getSammlerID() {
		return this.sammlerID;
	}

	public void setSammlerID(int sammlerID) {
		this.sammlerID = sammlerID;
	}


	@Column(name="series_number")
	public String getSeriesNumber() {
		return this.seriesNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}


	public int getSeriesID() {
		return this.seriesID;
	}

	public void setSeriesID(int seriesID) {
		this.seriesID = seriesID;
	}


	@Lob
	@Column(name="taxon_alt")
	public String getTaxonAlt() {
		return this.taxonAlt;
	}

	public void setTaxonAlt(String taxonAlt) {
		this.taxonAlt = taxonAlt;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	public String getTypified() {
		return this.typified;
	}

	public void setTypified(String typified) {
		this.typified = typified;
	}


	public int getTypusID() {
		return this.typusID;
	}

	public void setTypusID(int typusID) {
		this.typusID = typusID;
	}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getVoucherID() {
		return this.voucherID;
	}

	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}


	@Column(name="W_Min")
	public int getW_Min() {
		return this.w_Min;
	}

	public void setW_Min(int w_Min) {
		this.w_Min = w_Min;
	}


	@Column(name="W_Sec")
	public double getW_Sec() {
		return this.w_Sec;
	}

	public void setW_Sec(double w_Sec) {
		this.w_Sec = w_Sec;
	}

}