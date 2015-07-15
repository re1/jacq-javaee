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
@Table(name = "tbl_acquisition_source")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionSource.findAll", query = "SELECT t FROM TblAcquisitionSource t"),
    @NamedQuery(name = "TblAcquisitionSource.findByAcquisitionSourceId", query = "SELECT t FROM TblAcquisitionSource t WHERE t.acquisitionSourceId = :acquisitionSourceId"),
    @NamedQuery(name = "TblAcquisitionSource.findByName", query = "SELECT t FROM TblAcquisitionSource t WHERE t.name = :name")})
public class TblAcquisitionSource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acquisition_source_id")
    private Integer acquisitionSourceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionSourceId")
    private Collection<TblAcquisitionEventSource> tblAcquisitionEventSourceCollection;

    public TblAcquisitionSource() {
    }

    public TblAcquisitionSource(Integer acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    public TblAcquisitionSource(Integer acquisitionSourceId, String name) {
        this.acquisitionSourceId = acquisitionSourceId;
        this.name = name;
    }

    public Integer getAcquisitionSourceId() {
        return acquisitionSourceId;
    }

    public void setAcquisitionSourceId(Integer acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<TblAcquisitionEventSource> getTblAcquisitionEventSourceCollection() {
        return tblAcquisitionEventSourceCollection;
    }

    public void setTblAcquisitionEventSourceCollection(Collection<TblAcquisitionEventSource> tblAcquisitionEventSourceCollection) {
        this.tblAcquisitionEventSourceCollection = tblAcquisitionEventSourceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acquisitionSourceId != null ? acquisitionSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcquisitionSource)) {
            return false;
        }
        TblAcquisitionSource other = (TblAcquisitionSource) object;
        if ((this.acquisitionSourceId == null && other.acquisitionSourceId != null) || (this.acquisitionSourceId != null && !this.acquisitionSourceId.equals(other.acquisitionSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionSource[ acquisitionSourceId=" + acquisitionSourceId + " ]";
    }

}
