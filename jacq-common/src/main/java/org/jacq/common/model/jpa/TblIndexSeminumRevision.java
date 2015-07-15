/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_index_seminum_revision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIndexSeminumRevision.findAll", query = "SELECT t FROM TblIndexSeminumRevision t"),
    @NamedQuery(name = "TblIndexSeminumRevision.findByIndexSeminumRevisionId", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.indexSeminumRevisionId = :indexSeminumRevisionId"),
    @NamedQuery(name = "TblIndexSeminumRevision.findByName", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.name = :name"),
    @NamedQuery(name = "TblIndexSeminumRevision.findByTimestamp", query = "SELECT t FROM TblIndexSeminumRevision t WHERE t.timestamp = :timestamp")})
public class TblIndexSeminumRevision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_seminum_revision_id")
    private Integer indexSeminumRevisionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FrmwrkUser userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indexSeminumRevisionId")
    private Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection;

    public TblIndexSeminumRevision() {
    }

    public TblIndexSeminumRevision(Integer indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

    public TblIndexSeminumRevision(Integer indexSeminumRevisionId, String name, Date timestamp) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Integer getIndexSeminumRevisionId() {
        return indexSeminumRevisionId;
    }

    public void setIndexSeminumRevisionId(Integer indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<TblIndexSeminumContent> getTblIndexSeminumContentCollection() {
        return tblIndexSeminumContentCollection;
    }

    public void setTblIndexSeminumContentCollection(Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection) {
        this.tblIndexSeminumContentCollection = tblIndexSeminumContentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indexSeminumRevisionId != null ? indexSeminumRevisionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIndexSeminumRevision)) {
            return false;
        }
        TblIndexSeminumRevision other = (TblIndexSeminumRevision) object;
        if ((this.indexSeminumRevisionId == null && other.indexSeminumRevisionId != null) || (this.indexSeminumRevisionId != null && !this.indexSeminumRevisionId.equals(other.indexSeminumRevisionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIndexSeminumRevision[ indexSeminumRevisionId=" + indexSeminumRevisionId + " ]";
    }

}
