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
@Table(name = "tbl_sex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSex.findAll", query = "SELECT t FROM TblSex t"),
    @NamedQuery(name = "TblSex.findById", query = "SELECT t FROM TblSex t WHERE t.id = :id"),
    @NamedQuery(name = "TblSex.findBySex", query = "SELECT t FROM TblSex t WHERE t.sex = :sex")})
public class TblSex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sex")
    private String sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexId")
    private Collection<TblBotanicalObjectSex> tblBotanicalObjectSexCollection;

    public TblSex() {
    }

    public TblSex(Integer id) {
        this.id = id;
    }

    public TblSex(Integer id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @XmlTransient
    public Collection<TblBotanicalObjectSex> getTblBotanicalObjectSexCollection() {
        return tblBotanicalObjectSexCollection;
    }

    public void setTblBotanicalObjectSexCollection(Collection<TblBotanicalObjectSex> tblBotanicalObjectSexCollection) {
        this.tblBotanicalObjectSexCollection = tblBotanicalObjectSexCollection;
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
        if (!(object instanceof TblSex)) {
            return false;
        }
        TblSex other = (TblSex) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSex[ id=" + id + " ]";
    }

}
