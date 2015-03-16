package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_import_properties database table.
 * 
 */
@Entity
@Table(name="tbl_import_properties")
@NamedQuery(name="TblImportProperty.findAll", query="SELECT t FROM TblImportProperty t")
public class TblImportProperty implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int IDPflanze;
	private String speciesName;
	private String verbreitung;
	private TblBotanicalObject tblBotanicalObject;

	public TblImportProperty() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getIDPflanze() {
		return this.IDPflanze;
	}

	public void setIDPflanze(int IDPflanze) {
		this.IDPflanze = IDPflanze;
	}


	@Column(name="species_name", length=255)
	public String getSpeciesName() {
		return this.speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}


	@Lob
	@Column(name="Verbreitung")
	public String getVerbreitung() {
		return this.verbreitung;
	}

	public void setVerbreitung(String verbreitung) {
		this.verbreitung = verbreitung;
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

}