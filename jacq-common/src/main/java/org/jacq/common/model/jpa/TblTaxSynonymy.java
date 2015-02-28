package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tbl_tax_synonymy database table.
 * 
 */
@Entity
@Table(name="tbl_tax_synonymy")
@NamedQuery(name="TblTaxSynonymy.findAll", query="SELECT t FROM TblTaxSynonymy t")
public class TblTaxSynonymy implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tax_syn_ID;
	private int acc_taxon_ID;
	private String annotations;
	private byte locked;
	private byte preferredTaxonomy;
	private Date refDate;
	private String source;
	private int source_citationID;
	private int source_person_ID;
	private int source_serviceID;
	private int source_specimenID;
	private int taxonID;
	private Timestamp timestamp;
	private int userID;

	public TblTaxSynonymy() {
	}


	@Id
	public int getTax_syn_ID() {
		return this.tax_syn_ID;
	}

	public void setTax_syn_ID(int tax_syn_ID) {
		this.tax_syn_ID = tax_syn_ID;
	}


	public int getAcc_taxon_ID() {
		return this.acc_taxon_ID;
	}

	public void setAcc_taxon_ID(int acc_taxon_ID) {
		this.acc_taxon_ID = acc_taxon_ID;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	@Column(name="preferred_taxonomy")
	public byte getPreferredTaxonomy() {
		return this.preferredTaxonomy;
	}

	public void setPreferredTaxonomy(byte preferredTaxonomy) {
		this.preferredTaxonomy = preferredTaxonomy;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="ref_date")
	public Date getRefDate() {
		return this.refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}


	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public int getSource_citationID() {
		return this.source_citationID;
	}

	public void setSource_citationID(int source_citationID) {
		this.source_citationID = source_citationID;
	}


	public int getSource_person_ID() {
		return this.source_person_ID;
	}

	public void setSource_person_ID(int source_person_ID) {
		this.source_person_ID = source_person_ID;
	}


	public int getSource_serviceID() {
		return this.source_serviceID;
	}

	public void setSource_serviceID(int source_serviceID) {
		this.source_serviceID = source_serviceID;
	}


	public int getSource_specimenID() {
		return this.source_specimenID;
	}

	public void setSource_specimenID(int source_specimenID) {
		this.source_specimenID = source_specimenID;
	}


	public int getTaxonID() {
		return this.taxonID;
	}

	public void setTaxonID(int taxonID) {
		this.taxonID = taxonID;
	}


	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}