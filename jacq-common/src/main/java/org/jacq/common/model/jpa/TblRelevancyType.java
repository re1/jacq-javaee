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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_relevancy_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRelevancyType.findAll", query = "SELECT t FROM TblRelevancyType t"),
    @NamedQuery(name = "TblRelevancyType.findById", query = "SELECT t FROM TblRelevancyType t WHERE t.id = :id"),
    @NamedQuery(name = "TblRelevancyType.findByType", query = "SELECT t FROM TblRelevancyType t WHERE t.type = :type"),
    @NamedQuery(name = "TblRelevancyType.findByImportant", query = "SELECT t FROM TblRelevancyType t WHERE t.important = :important")})
public class TblRelevancyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 25)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "important")
    private boolean important;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relevancyTypeId")
    private Collection<TblRelevancy> tblRelevancyCollection;

    public TblRelevancyType() {
    }

    public TblRelevancyType(Integer id) {
        this.id = id;
    }

    public TblRelevancyType(Integer id, boolean important) {
        this.id = id;
        this.important = important;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @XmlTransient
    public Collection<TblRelevancy> getTblRelevancyCollection() {
        return tblRelevancyCollection;
    }

    public void setTblRelevancyCollection(Collection<TblRelevancy> tblRelevancyCollection) {
        this.tblRelevancyCollection = tblRelevancyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRelevancyType)) {
            return false;
        }
        TblRelevancyType other = (TblRelevancyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblRelevancyType[ id=" + id + " ]";
    }

}
