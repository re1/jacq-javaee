package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_typi database table.
 * 
 */
@Entity
@Table(name="tbl_typi")
@NamedQuery(name="TblTypi.findAll", query="SELECT t FROM TblTypi t")
public class TblTypi implements Serializable {
	private static final long serialVersionUID = 1L;
	private int typusID;
	private String typus;
	private String typusApiStandard;
	private String typusDescription;
	private String typusEngl;
	private String typusIcbn;
	private String typusLat;

	public TblTypi() {
	}


	@Id
	public int getTypusID() {
		return this.typusID;
	}

	public void setTypusID(int typusID) {
		this.typusID = typusID;
	}


	public String getTypus() {
		return this.typus;
	}

	public void setTypus(String typus) {
		this.typus = typus;
	}


	@Column(name="typus_api_standard")
	public String getTypusApiStandard() {
		return this.typusApiStandard;
	}

	public void setTypusApiStandard(String typusApiStandard) {
		this.typusApiStandard = typusApiStandard;
	}


	@Column(name="typus_description")
	public String getTypusDescription() {
		return this.typusDescription;
	}

	public void setTypusDescription(String typusDescription) {
		this.typusDescription = typusDescription;
	}


	@Column(name="typus_engl")
	public String getTypusEngl() {
		return this.typusEngl;
	}

	public void setTypusEngl(String typusEngl) {
		this.typusEngl = typusEngl;
	}


	@Column(name="typus_icbn")
	public String getTypusIcbn() {
		return this.typusIcbn;
	}

	public void setTypusIcbn(String typusIcbn) {
		this.typusIcbn = typusIcbn;
	}


	@Column(name="typus_lat")
	public String getTypusLat() {
		return this.typusLat;
	}

	public void setTypusLat(String typusLat) {
		this.typusLat = typusLat;
	}

}