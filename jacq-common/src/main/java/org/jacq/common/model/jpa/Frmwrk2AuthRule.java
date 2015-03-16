package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frmwrk2_auth_rule database table.
 * 
 */
@Entity
@Table(name="frmwrk2_auth_rule")
@NamedQuery(name="Frmwrk2AuthRule.findAll", query="SELECT f FROM Frmwrk2AuthRule f")
public class Frmwrk2AuthRule implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int createdAt;
	private String data;
	private int updatedAt;
	private List<Frmwrk2AuthItem> frmwrk2AuthItems;

	public Frmwrk2AuthRule() {
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


	@Column(name="updated_at")
	public int getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}


	//bi-directional many-to-one association to Frmwrk2AuthItem
	@OneToMany(mappedBy="frmwrk2AuthRule")
	public List<Frmwrk2AuthItem> getFrmwrk2AuthItems() {
		return this.frmwrk2AuthItems;
	}

	public void setFrmwrk2AuthItems(List<Frmwrk2AuthItem> frmwrk2AuthItems) {
		this.frmwrk2AuthItems = frmwrk2AuthItems;
	}

	public Frmwrk2AuthItem addFrmwrk2AuthItem(Frmwrk2AuthItem frmwrk2AuthItem) {
		getFrmwrk2AuthItems().add(frmwrk2AuthItem);
		frmwrk2AuthItem.setFrmwrk2AuthRule(this);

		return frmwrk2AuthItem;
	}

	public Frmwrk2AuthItem removeFrmwrk2AuthItem(Frmwrk2AuthItem frmwrk2AuthItem) {
		getFrmwrk2AuthItems().remove(frmwrk2AuthItem);
		frmwrk2AuthItem.setFrmwrk2AuthRule(null);

		return frmwrk2AuthItem;
	}

}