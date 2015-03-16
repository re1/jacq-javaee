package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the srvc_uuid_minter database table.
 * 
 */
@Entity
@Table(name="srvc_uuid_minter")
@NamedQuery(name="SrvcUuidMinter.findAll", query="SELECT s FROM SrvcUuidMinter s")
public class SrvcUuidMinter implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uuidMinterId;
	private int internalId;
	private Timestamp timestamp;
	private String uuid;
	private SrvcUuidMinterType srvcUuidMinterType;

	public SrvcUuidMinter() {
	}


	@Id
	@Column(name="uuid_minter_id", unique=true, nullable=false)
	public int getUuidMinterId() {
		return this.uuidMinterId;
	}

	public void setUuidMinterId(int uuidMinterId) {
		this.uuidMinterId = uuidMinterId;
	}


	@Column(name="internal_id", nullable=false)
	public int getInternalId() {
		return this.internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	@Column(nullable=false, length=36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	//bi-directional many-to-one association to SrvcUuidMinterType
	@ManyToOne
	@JoinColumn(name="uuid_minter_type_id", nullable=false)
	public SrvcUuidMinterType getSrvcUuidMinterType() {
		return this.srvcUuidMinterType;
	}

	public void setSrvcUuidMinterType(SrvcUuidMinterType srvcUuidMinterType) {
		this.srvcUuidMinterType = srvcUuidMinterType;
	}

}