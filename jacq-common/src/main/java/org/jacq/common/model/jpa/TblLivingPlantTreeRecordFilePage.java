/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_living_plant_tree_record_file_page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findAll", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t"),
    @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findById", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.id = :id"),
    @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findByCorrectionsDone", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.correctionsDone = :correctionsDone"),
    @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findByCorrectionsDate", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.correctionsDate = :correctionsDate")})
public class TblLivingPlantTreeRecordFilePage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "corrections_done")
    private boolean correctionsDone;
    @Column(name = "corrections_date")
    @Temporal(TemporalType.DATE)
    private Date correctionsDate;
    @JoinColumn(name = "tree_record_file_page_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblTreeRecordFilePage treeRecordFilePageId;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblLivingPlant livingPlantId;

    public TblLivingPlantTreeRecordFilePage() {
    }

    public TblLivingPlantTreeRecordFilePage(Integer id) {
        this.id = id;
    }

    public TblLivingPlantTreeRecordFilePage(Integer id, boolean correctionsDone) {
        this.id = id;
        this.correctionsDone = correctionsDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getCorrectionsDone() {
        return correctionsDone;
    }

    public void setCorrectionsDone(boolean correctionsDone) {
        this.correctionsDone = correctionsDone;
    }

    public Date getCorrectionsDate() {
        return correctionsDate;
    }

    public void setCorrectionsDate(Date correctionsDate) {
        this.correctionsDate = correctionsDate;
    }

    public TblTreeRecordFilePage getTreeRecordFilePageId() {
        return treeRecordFilePageId;
    }

    public void setTreeRecordFilePageId(TblTreeRecordFilePage treeRecordFilePageId) {
        this.treeRecordFilePageId = treeRecordFilePageId;
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
        if (!(object instanceof TblLivingPlantTreeRecordFilePage)) {
            return false;
        }
        TblLivingPlantTreeRecordFilePage other = (TblLivingPlantTreeRecordFilePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLivingPlantTreeRecordFilePage[ id=" + id + " ]";
    }

}
