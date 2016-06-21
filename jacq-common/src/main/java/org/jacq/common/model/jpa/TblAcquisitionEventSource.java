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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_event_source")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionEventSource.findAll", query = "SELECT t FROM TblAcquisitionEventSource t"),
    @NamedQuery(name = "TblAcquisitionEventSource.findByAcquisitionEventSourceId", query = "SELECT t FROM TblAcquisitionEventSource t WHERE t.acquisitionEventSourceId = :acquisitionEventSourceId"),
    @NamedQuery(name = "TblAcquisitionEventSource.findBySourceDate", query = "SELECT t FROM TblAcquisitionEventSource t WHERE t.sourceDate = :sourceDate")})
public class TblAcquisitionEventSource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acquisition_event_source_id")
    private Integer acquisitionEventSourceId;
    @Column(name = "source_date")
    @Temporal(TemporalType.DATE)
    private Date sourceDate;
    @JoinColumn(name = "acquisition_source_id", referencedColumnName = "acquisition_source_id")
    @ManyToOne(optional = false)
    private TblAcquisitionSource acquisitionSourceId;
    @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblAcquisitionEvent acquisitionEventId;

    public TblAcquisitionEventSource() {
    }

    public TblAcquisitionEventSource(Integer acquisitionEventSourceId) {
        this.acquisitionEventSourceId = acquisitionEventSourceId;
    }

    public Integer getAcquisitionEventSourceId() {
        return acquisitionEventSourceId;
    }

    public void setAcquisitionEventSourceId(Integer acquisitionEventSourceId) {
        this.acquisitionEventSourceId = acquisitionEventSourceId;
    }

    public Date getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(Date sourceDate) {
        this.sourceDate = sourceDate;
    }

    public TblAcquisitionSource getAcquisitionSourceId() {
        return acquisitionSourceId;
    }

    public void setAcquisitionSourceId(TblAcquisitionSource acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    public TblAcquisitionEvent getAcquisitionEventId() {
        return acquisitionEventId;
    }

    public void setAcquisitionEventId(TblAcquisitionEvent acquisitionEventId) {
        this.acquisitionEventId = acquisitionEventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acquisitionEventSourceId != null ? acquisitionEventSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcquisitionEventSource)) {
            return false;
        }
        TblAcquisitionEventSource other = (TblAcquisitionEventSource) object;
        if ((this.acquisitionEventSourceId == null && other.acquisitionEventSourceId != null) || (this.acquisitionEventSourceId != null && !this.acquisitionEventSourceId.equals(other.acquisitionEventSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionEventSource[ acquisitionEventSourceId=" + acquisitionEventSourceId + " ]";
    }

}
