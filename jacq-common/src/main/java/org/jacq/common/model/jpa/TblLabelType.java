/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tbl_label_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLabelType.findAll", query = "SELECT t FROM TblLabelType t"),
    @NamedQuery(name = "TblLabelType.findByLabelTypeId", query = "SELECT t FROM TblLabelType t WHERE t.labelTypeId = :labelTypeId"),
    @NamedQuery(name = "TblLabelType.findByType", query = "SELECT t FROM TblLabelType t WHERE t.type = :type")})
public class TblLabelType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "label_type_id")
    private Integer labelTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "type")
    private String type;
    @ManyToMany(mappedBy = "tblLabelTypeCollection")
    private Collection<TblBotanicalObject> tblBotanicalObjectCollection;

    public TblLabelType() {
    }

    public TblLabelType(Integer labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public TblLabelType(Integer labelTypeId, String type) {
        this.labelTypeId = labelTypeId;
        this.type = type;
    }

    public Integer getLabelTypeId() {
        return labelTypeId;
    }

    public void setLabelTypeId(Integer labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<TblBotanicalObject> getTblBotanicalObjectCollection() {
        return tblBotanicalObjectCollection;
    }

    public void setTblBotanicalObjectCollection(Collection<TblBotanicalObject> tblBotanicalObjectCollection) {
        this.tblBotanicalObjectCollection = tblBotanicalObjectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labelTypeId != null ? labelTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLabelType)) {
            return false;
        }
        TblLabelType other = (TblLabelType) object;
        if ((this.labelTypeId == null && other.labelTypeId != null) || (this.labelTypeId != null && !this.labelTypeId.equals(other.labelTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLabelType[ labelTypeId=" + labelTypeId + " ]";
    }

}
