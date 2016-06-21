/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "frmwrk2_auth_rule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frmwrk2AuthRule.findAll", query = "SELECT f FROM Frmwrk2AuthRule f"),
    @NamedQuery(name = "Frmwrk2AuthRule.findByName", query = "SELECT f FROM Frmwrk2AuthRule f WHERE f.name = :name"),
    @NamedQuery(name = "Frmwrk2AuthRule.findByCreatedAt", query = "SELECT f FROM Frmwrk2AuthRule f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "Frmwrk2AuthRule.findByUpdatedAt", query = "SELECT f FROM Frmwrk2AuthRule f WHERE f.updatedAt = :updatedAt")})
public class Frmwrk2AuthRule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "data")
    private String data;
    @Column(name = "created_at")
    private Integer createdAt;
    @Column(name = "updated_at")
    private Integer updatedAt;
    @OneToMany(mappedBy = "ruleName")
    private Collection<Frmwrk2AuthItem> frmwrk2AuthItemCollection;

    public Frmwrk2AuthRule() {
    }

    public Frmwrk2AuthRule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frmwrk2AuthRule)) {
            return false;
        }
        Frmwrk2AuthRule other = (Frmwrk2AuthRule) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.Frmwrk2AuthRule[ name=" + name + " ]";
    }

}
