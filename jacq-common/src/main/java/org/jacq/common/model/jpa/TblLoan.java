package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_loans database table.
 * 
 */
@Entity
@Table(name="tbl_loans")
@NamedQuery(name="TblLoan.findAll", query="SELECT t FROM TblLoan t")
public class TblLoan implements Serializable {
	private static final long serialVersionUID = 1L;
	private int loanId;
	private String annotations;
	private Date dateOfLoan;
	private String foreignReference;
	private int herbariumID;
	private int loantypeID;
	private int numberOfSheets;
	private Date received;
	private Date sent;
	private String wuReference;

	public TblLoan() {
	}


	@Id
	@Column(name="loan_id")
	public int getLoanId() {
		return this.loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}


	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_loan")
	public Date getDateOfLoan() {
		return this.dateOfLoan;
	}

	public void setDateOfLoan(Date dateOfLoan) {
		this.dateOfLoan = dateOfLoan;
	}


	@Column(name="foreign_reference")
	public String getForeignReference() {
		return this.foreignReference;
	}

	public void setForeignReference(String foreignReference) {
		this.foreignReference = foreignReference;
	}


	public int getHerbariumID() {
		return this.herbariumID;
	}

	public void setHerbariumID(int herbariumID) {
		this.herbariumID = herbariumID;
	}


	public int getLoantypeID() {
		return this.loantypeID;
	}

	public void setLoantypeID(int loantypeID) {
		this.loantypeID = loantypeID;
	}


	@Column(name="number_of_sheets")
	public int getNumberOfSheets() {
		return this.numberOfSheets;
	}

	public void setNumberOfSheets(int numberOfSheets) {
		this.numberOfSheets = numberOfSheets;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getReceived() {
		return this.received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getSent() {
		return this.sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}


	@Column(name="wu_reference")
	public String getWuReference() {
		return this.wuReference;
	}

	public void setWuReference(String wuReference) {
		this.wuReference = wuReference;
	}

}