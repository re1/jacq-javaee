/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_alternative_accession_number")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAlternativeAccessionNumber.findAll", query = "SELECT t FROM TblAlternativeAccessionNumber t"),
    @NamedQuery(name = "TblAlternativeAccessionNumber.findById", query = "SELECT t FROM TblAlternativeAccessionNumber t WHERE t.id = :id"),
    @NamedQuery(name = "TblAlternativeAccessionNumber.findByNumber", query = "SELECT t FROM TblAlternativeAccessionNumber t WHERE t.number = :number")})
public class TblAlternativeAccessionNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "number")
    private String number;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblLivingPlant livingPlantId;

    public TblAlternativeAccessionNumber() {
    }

    public TblAlternativeAccessionNumber(Integer id) {
        this.id = id;
    }

    public TblAlternativeAccessionNumber(Integer id, String number) {
        this.id = id;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TblLivingPlant getLivingPlantId() {
        return livingPlantId;
    }

    public void setLivingPlantId(TblLivingPlant livingPlantId) {
        this.livingPlantId = livingPlantId;
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
        if (!(object instanceof TblAlternativeAccessionNumber)) {
            return false;
        }
        TblAlternativeAccessionNumber other = (TblAlternativeAccessionNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAlternativeAccessionNumber[ id=" + id + " ]";
    }

}
