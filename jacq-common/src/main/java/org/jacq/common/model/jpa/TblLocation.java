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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLocation.findAll", query = "SELECT t FROM TblLocation t"),
    @NamedQuery(name = "TblLocation.findById", query = "SELECT t FROM TblLocation t WHERE t.id = :id")})
public class TblLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "location")
    private String location;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblLocation")
    private TblLocationGeonames tblLocationGeonames;
    @OneToMany(mappedBy = "locationId")
    private Collection<TblAcquisitionEvent> tblAcquisitionEventCollection;

    public TblLocation() {
    }

    public TblLocation(Integer id) {
        this.id = id;
    }

    public TblLocation(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TblLocationGeonames getTblLocationGeonames() {
        return tblLocationGeonames;
    }

    public void setTblLocationGeonames(TblLocationGeonames tblLocationGeonames) {
        this.tblLocationGeonames = tblLocationGeonames;
    }

    @XmlTransient
    public Collection<TblAcquisitionEvent> getTblAcquisitionEventCollection() {
        return tblAcquisitionEventCollection;
    }

    public void setTblAcquisitionEventCollection(Collection<TblAcquisitionEvent> tblAcquisitionEventCollection) {
        this.tblAcquisitionEventCollection = tblAcquisitionEventCollection;
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
        if (!(object instanceof TblLocation)) {
            return false;
        }
        TblLocation other = (TblLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLocation[ id=" + id + " ]";
    }

}
