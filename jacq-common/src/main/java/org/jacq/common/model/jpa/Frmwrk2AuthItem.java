/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk2_auth_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frmwrk2AuthItem.findAll", query = "SELECT f FROM Frmwrk2AuthItem f"),
    @NamedQuery(name = "Frmwrk2AuthItem.findByName", query = "SELECT f FROM Frmwrk2AuthItem f WHERE f.name = :name"),
    @NamedQuery(name = "Frmwrk2AuthItem.findByType", query = "SELECT f FROM Frmwrk2AuthItem f WHERE f.type = :type"),
    @NamedQuery(name = "Frmwrk2AuthItem.findByCreatedAt", query = "SELECT f FROM Frmwrk2AuthItem f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "Frmwrk2AuthItem.findByUpdatedAt", query = "SELECT f FROM Frmwrk2AuthItem f WHERE f.updatedAt = :updatedAt")})
public class Frmwrk2AuthItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "data")
    private String data;
    @Column(name = "created_at")
    private Integer createdAt;
    @Column(name = "updated_at")
    private Integer updatedAt;
    @JoinTable(name = "frmwrk2_auth_item_child", joinColumns = {
        @JoinColumn(name = "parent", referencedColumnName = "name")}, inverseJoinColumns = {
        @JoinColumn(name = "child", referencedColumnName = "name")})
    @ManyToMany
    private Collection<Frmwrk2AuthItem> frmwrk2AuthItemCollection;
    @ManyToMany(mappedBy = "frmwrk2AuthItemCollection")
    private Collection<Frmwrk2AuthItem> frmwrk2AuthItemCollection1;
    @JoinColumn(name = "rule_name", referencedColumnName = "name")
    @ManyToOne
    private Frmwrk2AuthRule ruleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frmwrk2AuthItem")
    private Collection<Frmwrk2AuthAssignment> frmwrk2AuthAssignmentCollection;

    public Frmwrk2AuthItem() {
    }

    public Frmwrk2AuthItem(String name) {
        this.name = name;
    }

    public Frmwrk2AuthItem(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Collection<Frmwrk2AuthItem> getFrmwrk2AuthItemCollection() {
        return frmwrk2AuthItemCollection;
    }

    public void setFrmwrk2AuthItemCollection(Collection<Frmwrk2AuthItem> frmwrk2AuthItemCollection) {
        this.frmwrk2AuthItemCollection = frmwrk2AuthItemCollection;
    }

    @XmlTransient
    public Collection<Frmwrk2AuthItem> getFrmwrk2AuthItemCollection1() {
        return frmwrk2AuthItemCollection1;
    }

    public void setFrmwrk2AuthItemCollection1(Collection<Frmwrk2AuthItem> frmwrk2AuthItemCollection1) {
        this.frmwrk2AuthItemCollection1 = frmwrk2AuthItemCollection1;
    }

    public Frmwrk2AuthRule getRuleName() {
        return ruleName;
    }

    public void setRuleName(Frmwrk2AuthRule ruleName) {
        this.ruleName = ruleName;
    }

    @XmlTransient
    public Collection<Frmwrk2AuthAssignment> getFrmwrk2AuthAssignmentCollection() {
        return frmwrk2AuthAssignmentCollection;
    }

    public void setFrmwrk2AuthAssignmentCollection(Collection<Frmwrk2AuthAssignment> frmwrk2AuthAssignmentCollection) {
        this.frmwrk2AuthAssignmentCollection = frmwrk2AuthAssignmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frmwrk2AuthItem)) {
            return false;
        }
        Frmwrk2AuthItem other = (Frmwrk2AuthItem) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.Frmwrk2AuthItem[ name=" + name + " ]";
    }

}
