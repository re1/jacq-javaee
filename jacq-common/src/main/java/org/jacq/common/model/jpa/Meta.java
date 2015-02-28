package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the meta database table.
 * 
 */
@Entity
@Table(name="meta")
@NamedQuery(name="Meta.findAll", query="SELECT m FROM Meta m")
public class Meta implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sourceId;
	private String sourceAbbrEngl;
	private String sourceCode;
	private Date sourceExpiry;
	private String sourceName;
	private int sourceNumberOfRecords;
	private Date sourceUpdate;
	private String sourceUrl;
	private String sourceVersion;

	public Meta() {
	}


	@Id
	@Column(name="source_id")
	public int getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}


	@Column(name="source_abbr_engl")
	public String getSourceAbbrEngl() {
		return this.sourceAbbrEngl;
	}

	public void setSourceAbbrEngl(String sourceAbbrEngl) {
		this.sourceAbbrEngl = sourceAbbrEngl;
	}


	@Column(name="source_code")
	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="source_expiry")
	public Date getSourceExpiry() {
		return this.sourceExpiry;
	}

	public void setSourceExpiry(Date sourceExpiry) {
		this.sourceExpiry = sourceExpiry;
	}


	@Column(name="source_name")
	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	@Column(name="source_number_of_records")
	public int getSourceNumberOfRecords() {
		return this.sourceNumberOfRecords;
	}

	public void setSourceNumberOfRecords(int sourceNumberOfRecords) {
		this.sourceNumberOfRecords = sourceNumberOfRecords;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="source_update")
	public Date getSourceUpdate() {
		return this.sourceUpdate;
	}

	public void setSourceUpdate(Date sourceUpdate) {
		this.sourceUpdate = sourceUpdate;
	}


	@Column(name="source_url")
	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}


	@Column(name="source_version")
	public String getSourceVersion() {
		return this.sourceVersion;
	}

	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

}