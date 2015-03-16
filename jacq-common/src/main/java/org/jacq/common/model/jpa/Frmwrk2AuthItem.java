package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frmwrk2_auth_item database table.
 * 
 */
@Entity
@Table(name="frmwrk2_auth_item")
@NamedQuery(name="Frmwrk2AuthItem.findAll", query="SELECT f FROM Frmwrk2AuthItem f")
public class Frmwrk2AuthItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int createdAt;
	private String data;
	private String description;
	private int type;
	private int updatedAt;
	private List<Frmwrk2AuthAssignment> frmwrk2AuthAssignments;
	private List<Frmwrk2AuthItem> frmwrk2AuthItems1;
	private List<Frmwrk2AuthItem> frmwrk2AuthItems2;
	private Frmwrk2AuthRule frmwrk2AuthRule;

	public Frmwrk2AuthItem() {
	}


	@Id
	@Column(unique=true, nullable=false, length=64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="created_at")
	public int getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}


	@Lob
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}


	@Column(name="updated_at")
	public int getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}


	//bi-directional many-to-one association to Frmwrk2AuthAssignment
	@OneToMany(mappedBy="frmwrk2AuthItem")
	public List<Frmwrk2AuthAssignment> getFrmwrk2AuthAssignments() {
		return this.frmwrk2AuthAssignments;
	}

	public void setFrmwrk2AuthAssignments(List<Frmwrk2AuthAssignment> frmwrk2AuthAssignments) {
		this.frmwrk2AuthAssignments = frmwrk2AuthAssignments;
	}

	public Frmwrk2AuthAssignment addFrmwrk2AuthAssignment(Frmwrk2AuthAssignment frmwrk2AuthAssignment) {
		getFrmwrk2AuthAssignments().add(frmwrk2AuthAssignment);
		frmwrk2AuthAssignment.setFrmwrk2AuthItem(this);

		return frmwrk2AuthAssignment;
	}

	public Frmwrk2AuthAssignment removeFrmwrk2AuthAssignment(Frmwrk2AuthAssignment frmwrk2AuthAssignment) {
		getFrmwrk2AuthAssignments().remove(frmwrk2AuthAssignment);
		frmwrk2AuthAssignment.setFrmwrk2AuthItem(null);

		return frmwrk2AuthAssignment;
	}


	//bi-directional many-to-many association to Frmwrk2AuthItem
	@ManyToMany
	@JoinTable(
		name="frmwrk2_auth_item_child"
		, joinColumns={
			@JoinColumn(name="child", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="parent", nullable=false)
			}
		)
	public List<Frmwrk2AuthItem> getFrmwrk2AuthItems1() {
		return this.frmwrk2AuthItems1;
	}

	public void setFrmwrk2AuthItems1(List<Frmwrk2AuthItem> frmwrk2AuthItems1) {
		this.frmwrk2AuthItems1 = frmwrk2AuthItems1;
	}


	//bi-directional many-to-many association to Frmwrk2AuthItem
	@ManyToMany(mappedBy="frmwrk2AuthItems1")
	public List<Frmwrk2AuthItem> getFrmwrk2AuthItems2() {
		return this.frmwrk2AuthItems2;
	}

	public void setFrmwrk2AuthItems2(List<Frmwrk2AuthItem> frmwrk2AuthItems2) {
		this.frmwrk2AuthItems2 = frmwrk2AuthItems2;
	}


	//bi-directional many-to-one association to Frmwrk2AuthRule
	@ManyToOne
	@JoinColumn(name="rule_name")
	public Frmwrk2AuthRule getFrmwrk2AuthRule() {
		return this.frmwrk2AuthRule;
	}

	public void setFrmwrk2AuthRule(Frmwrk2AuthRule frmwrk2AuthRule) {
		this.frmwrk2AuthRule = frmwrk2AuthRule;
	}

}