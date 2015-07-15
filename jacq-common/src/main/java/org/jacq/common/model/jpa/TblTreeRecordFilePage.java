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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_tree_record_file_page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTreeRecordFilePage.findAll", query = "SELECT t FROM TblTreeRecordFilePage t"),
    @NamedQuery(name = "TblTreeRecordFilePage.findById", query = "SELECT t FROM TblTreeRecordFilePage t WHERE t.id = :id"),
    @NamedQuery(name = "TblTreeRecordFilePage.findByPage", query = "SELECT t FROM TblTreeRecordFilePage t WHERE t.page = :page")})
public class TblTreeRecordFilePage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "page")
    private int page;
    @JoinColumn(name = "tree_record_file_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblTreeRecordFile treeRecordFileId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treeRecordFilePageId")
    private Collection<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageCollection;

    public TblTreeRecordFilePage() {
    }

    public TblTreeRecordFilePage(Integer id) {
        this.id = id;
    }

    public TblTreeRecordFilePage(Integer id, int page) {
        this.id = id;
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public TblTreeRecordFile getTreeRecordFileId() {
        return treeRecordFileId;
    }

    public void setTreeRecordFileId(TblTreeRecordFile treeRecordFileId) {
        this.treeRecordFileId = treeRecordFileId;
    }

    @XmlTransient
    public Collection<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePageCollection() {
        return tblLivingPlantTreeRecordFilePageCollection;
    }

    public void setTblLivingPlantTreeRecordFilePageCollection(Collection<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageCollection) {
        this.tblLivingPlantTreeRecordFilePageCollection = tblLivingPlantTreeRecordFilePageCollection;
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
        if (!(object instanceof TblTreeRecordFilePage)) {
            return false;
        }
        TblTreeRecordFilePage other = (TblTreeRecordFilePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblTreeRecordFilePage[ id=" + id + " ]";
    }

}
