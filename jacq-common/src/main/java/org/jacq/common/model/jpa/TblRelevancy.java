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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_relevancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRelevancy.findAll", query = "SELECT t FROM TblRelevancy t"),
    @NamedQuery(name = "TblRelevancy.findById", query = "SELECT t FROM TblRelevancy t WHERE t.id = :id")})
public class TblRelevancy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "relevancy_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblRelevancyType relevancyTypeId;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblLivingPlant livingPlantId;

    public TblRelevancy() {
    }

    public TblRelevancy(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblRelevancyType getRelevancyTypeId() {
        return relevancyTypeId;
    }

    public void setRelevancyTypeId(TblRelevancyType relevancyTypeId) {
        this.relevancyTypeId = relevancyTypeId;
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
        if (!(object instanceof TblRelevancy)) {
            return false;
        }
        TblRelevancy other = (TblRelevancy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblRelevancy[ id=" + id + " ]";
    }

}
