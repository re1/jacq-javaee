package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the srvc_uuid_minter_type database table.
 * 
 */
@Entity
@Table(name="srvc_uuid_minter_type")
@NamedQuery(name="SrvcUuidMinterType.findAll", query="SELECT s FROM SrvcUuidMinterType s")
public class SrvcUuidMinterType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uuidMinterTypeId;
	private String description;
	private Timestamp timestamp;
	private List<SrvcUuidMinter> srvcUuidMinters;

	public SrvcUuidMinterType() {
	}


	@Id
	@Column(name="uuid_minter_type_id", unique=true, nullable=false)
	public int getUuidMinterTypeId() {
		return this.uuidMinterTypeId;
	}

	public void setUuidMinterTypeId(int uuidMinterTypeId) {
		this.uuidMinterTypeId = uuidMinterTypeId;
	}


	@Column(nullable=false, length=45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	//bi-directional many-to-one association to SrvcUuidMinter
	@OneToMany(mappedBy="srvcUuidMinterType")
	public List<SrvcUuidMinter> getSrvcUuidMinters() {
		return this.srvcUuidMinters;
	}

	public void setSrvcUuidMinters(List<SrvcUuidMinter> srvcUuidMinters) {
		this.srvcUuidMinters = srvcUuidMinters;
	}

	public SrvcUuidMinter addSrvcUuidMinter(SrvcUuidMinter srvcUuidMinter) {
		getSrvcUuidMinters().add(srvcUuidMinter);
		srvcUuidMinter.setSrvcUuidMinterType(this);

		return srvcUuidMinter;
	}

	public SrvcUuidMinter removeSrvcUuidMinter(SrvcUuidMinter srvcUuidMinter) {
		getSrvcUuidMinters().remove(srvcUuidMinter);
		srvcUuidMinter.setSrvcUuidMinterType(null);

		return srvcUuidMinter;
	}

}