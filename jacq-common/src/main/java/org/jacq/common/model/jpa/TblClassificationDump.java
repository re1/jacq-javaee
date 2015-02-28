package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_classification_dump database table.
 * 
 */
@Entity
@Table(name="tbl_classification_dump")
@NamedQuery(name="TblClassificationDump.findAll", query="SELECT t FROM TblClassificationDump t")
public class TblClassificationDump implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String unranked;
	private int citationID;
	private String class_;
	private String division;
	private String family;
	private String forma;
	private String genus;
	private String grex;
	private String kingdom;
	private String lusus;
	private String order;
	private String phylum;
	private String proles;
	private String section;
	private String series;
	private String species;
	private String subclass;
	private String subdivision;
	private String subfamily;
	private String subforma;
	private String subgenus;
	private String subkingdom;
	private String suborder;
	private String subphylum;
	private String subsection;
	private String subseries;
	private String subspecies;
	private String subtribe;
	private String subvariety;
	private String superdivision;
	private String superorder;
	private String tribe;
	private String variety;

	public TblClassificationDump() {
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="_unranked")
	public String getUnranked() {
		return this.unranked;
	}

	public void setUnranked(String unranked) {
		this.unranked = unranked;
	}


	public int getCitationID() {
		return this.citationID;
	}

	public void setCitationID(int citationID) {
		this.citationID = citationID;
	}


	@Column(name="class")
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}


	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}


	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}


	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}


	public String getGenus() {
		return this.genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}


	public String getGrex() {
		return this.grex;
	}

	public void setGrex(String grex) {
		this.grex = grex;
	}


	public String getKingdom() {
		return this.kingdom;
	}

	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}


	public String getLusus() {
		return this.lusus;
	}

	public void setLusus(String lusus) {
		this.lusus = lusus;
	}


	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	public String getPhylum() {
		return this.phylum;
	}

	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}


	public String getProles() {
		return this.proles;
	}

	public void setProles(String proles) {
		this.proles = proles;
	}


	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}


	public String getSpecies() {
		return this.species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}


	public String getSubclass() {
		return this.subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}


	public String getSubdivision() {
		return this.subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}


	public String getSubfamily() {
		return this.subfamily;
	}

	public void setSubfamily(String subfamily) {
		this.subfamily = subfamily;
	}


	public String getSubforma() {
		return this.subforma;
	}

	public void setSubforma(String subforma) {
		this.subforma = subforma;
	}


	public String getSubgenus() {
		return this.subgenus;
	}

	public void setSubgenus(String subgenus) {
		this.subgenus = subgenus;
	}


	public String getSubkingdom() {
		return this.subkingdom;
	}

	public void setSubkingdom(String subkingdom) {
		this.subkingdom = subkingdom;
	}


	public String getSuborder() {
		return this.suborder;
	}

	public void setSuborder(String suborder) {
		this.suborder = suborder;
	}


	public String getSubphylum() {
		return this.subphylum;
	}

	public void setSubphylum(String subphylum) {
		this.subphylum = subphylum;
	}


	public String getSubsection() {
		return this.subsection;
	}

	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}


	public String getSubseries() {
		return this.subseries;
	}

	public void setSubseries(String subseries) {
		this.subseries = subseries;
	}


	public String getSubspecies() {
		return this.subspecies;
	}

	public void setSubspecies(String subspecies) {
		this.subspecies = subspecies;
	}


	public String getSubtribe() {
		return this.subtribe;
	}

	public void setSubtribe(String subtribe) {
		this.subtribe = subtribe;
	}


	public String getSubvariety() {
		return this.subvariety;
	}

	public void setSubvariety(String subvariety) {
		this.subvariety = subvariety;
	}


	public String getSuperdivision() {
		return this.superdivision;
	}

	public void setSuperdivision(String superdivision) {
		this.superdivision = superdivision;
	}


	public String getSuperorder() {
		return this.superorder;
	}

	public void setSuperorder(String superorder) {
		this.superorder = superorder;
	}


	public String getTribe() {
		return this.tribe;
	}

	public void setTribe(String tribe) {
		this.tribe = tribe;
	}


	public String getVariety() {
		return this.variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

}