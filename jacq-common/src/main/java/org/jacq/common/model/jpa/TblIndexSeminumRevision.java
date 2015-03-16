package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbl_index_seminum_revision database table.
 * 
 */
@Entity
@Table(name="tbl_index_seminum_revision")
@NamedQuery(name="TblIndexSeminumRevision.findAll", query="SELECT t FROM TblIndexSeminumRevision t")
public class TblIndexSeminumRevision implements Serializable {
	private static final long serialVersionUID = 1L;
	private int indexSeminumRevisionId;
	private String name;
	private Timestamp timestamp;
	private List<TblIndexSeminumContent> tblIndexSeminumContents;
	private FrmwrkUser frmwrkUser;

	public TblIndexSeminumRevision() {
	}


	@Id
	@Column(name="index_seminum_revision_id", unique=true, nullable=false)
	public int getIndexSeminumRevisionId() {
		return this.indexSeminumRevisionId;
	}

	public void setIndexSeminumRevisionId(int indexSeminumRevisionId) {
		this.indexSeminumRevisionId = indexSeminumRevisionId;
	}


	@Column(nullable=false, length=50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to TblIndexSeminumContent
	@OneToMany(mappedBy="tblIndexSeminumRevision")
	public List<TblIndexSeminumContent> getTblIndexSeminumContents() {
		return this.tblIndexSeminumContents;
	}

	public void setTblIndexSeminumContents(List<TblIndexSeminumContent> tblIndexSeminumContents) {
		this.tblIndexSeminumContents = tblIndexSeminumContents;
	}

	public TblIndexSeminumContent addTblIndexSeminumContent(TblIndexSeminumContent tblIndexSeminumContent) {
		getTblIndexSeminumContents().add(tblIndexSeminumContent);
		tblIndexSeminumContent.setTblIndexSeminumRevision(this);

		return tblIndexSeminumContent;
	}

	public TblIndexSeminumContent removeTblIndexSeminumContent(TblIndexSeminumContent tblIndexSeminumContent) {
		getTblIndexSeminumContents().remove(tblIndexSeminumContent);
		tblIndexSeminumContent.setTblIndexSeminumRevision(null);

		return tblIndexSeminumContent;
	}


	//bi-directional many-to-one association to FrmwrkUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	public FrmwrkUser getFrmwrkUser() {
		return this.frmwrkUser;
	}

	public void setFrmwrkUser(FrmwrkUser frmwrkUser) {
		this.frmwrkUser = frmwrkUser;
	}

}