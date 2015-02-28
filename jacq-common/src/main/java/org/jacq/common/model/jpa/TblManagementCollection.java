package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_management_collections database table.
 * 
 */
@Entity
@Table(name="tbl_management_collections")
@NamedQuery(name="TblManagementCollection.findAll", query="SELECT t FROM TblManagementCollection t")
public class TblManagementCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private int collectionID;
	private String collDescr;
	private String collGbifPilot;
	private String collShort;
	private String collShortPrj;
	private String collection;
	private int sourceId;

	public TblManagementCollection() {
	}


	@Id
	public int getCollectionID() {
		return this.collectionID;
	}

	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}


	@Lob
	@Column(name="coll_descr")
	public String getCollDescr() {
		return this.collDescr;
	}

	public void setCollDescr(String collDescr) {
		this.collDescr = collDescr;
	}


	@Column(name="coll_gbif_pilot")
	public String getCollGbifPilot() {
		return this.collGbifPilot;
	}

	public void setCollGbifPilot(String collGbifPilot) {
		this.collGbifPilot = collGbifPilot;
	}


	@Column(name="coll_short")
	public String getCollShort() {
		return this.collShort;
	}

	public void setCollShort(String collShort) {
		this.collShort = collShort;
	}


	@Column(name="coll_short_prj")
	public String getCollShortPrj() {
		return this.collShortPrj;
	}

	public void setCollShortPrj(String collShortPrj) {
		this.collShortPrj = collShortPrj;
	}


	public String getCollection() {
		return this.collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}


	@Column(name="source_id")
	public int getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

}