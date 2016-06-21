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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionDate.findAll", query = "SELECT t FROM TblAcquisitionDate t"),
    @NamedQuery(name = "TblAcquisitionDate.findById", query = "SELECT t FROM TblAcquisitionDate t WHERE t.id = :id"),
    @NamedQuery(name = "TblAcquisitionDate.findByYear", query = "SELECT t FROM TblAcquisitionDate t WHERE t.year = :year"),
    @NamedQuery(name = "TblAcquisitionDate.findByMonth", query = "SELECT t FROM TblAcquisitionDate t WHERE t.month = :month"),
    @NamedQuery(name = "TblAcquisitionDate.findByDay", query = "SELECT t FROM TblAcquisitionDate t WHERE t.day = :day"),
    @NamedQuery(name = "TblAcquisitionDate.findByCustom", query = "SELECT t FROM TblAcquisitionDate t WHERE t.custom = :custom")})
public class TblAcquisitionDate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 4)
    @Column(name = "year")
    private String year;
    @Size(max = 2)
    @Column(name = "month")
    private String month;
    @Size(max = 2)
    @Column(name = "day")
    private String day;
    @Size(max = 20)
    @Column(name = "custom")
    private String custom;
    @OneToMany(mappedBy = "incomingDateId")
    private Collection<TblLivingPlant> tblLivingPlantCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionDateId")
    private Collection<TblAcquisitionEvent> tblAcquisitionEventCollection;

    public TblAcquisitionDate() {
    }

    public TblAcquisitionDate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    @XmlTransient
    public Collection<TblLivingPlant> getTblLivingPlantCollection() {
        return tblLivingPlantCollection;
    }

    public void setTblLivingPlantCollection(Collection<TblLivingPlant> tblLivingPlantCollection) {
        this.tblLivingPlantCollection = tblLivingPlantCollection;
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
        if (!(object instanceof TblAcquisitionDate)) {
            return false;
        }
        TblAcquisitionDate other = (TblAcquisitionDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionDate[ id=" + id + " ]";
    }

}
