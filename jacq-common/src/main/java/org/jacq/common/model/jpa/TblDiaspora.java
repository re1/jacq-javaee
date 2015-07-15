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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_diaspora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDiaspora.findAll", query = "SELECT t FROM TblDiaspora t"),
    @NamedQuery(name = "TblDiaspora.findById", query = "SELECT t FROM TblDiaspora t WHERE t.id = :id")})
public class TblDiaspora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblBotanicalObject tblBotanicalObject;
    @JoinColumn(name = "diaspora_bank_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblDiasporaBank diasporaBankId;

    public TblDiaspora() {
    }

    public TblDiaspora(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblBotanicalObject getTblBotanicalObject() {
        return tblBotanicalObject;
    }

    public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
        this.tblBotanicalObject = tblBotanicalObject;
    }

    public TblDiasporaBank getDiasporaBankId() {
        return diasporaBankId;
    }

    public void setDiasporaBankId(TblDiasporaBank diasporaBankId) {
        this.diasporaBankId = diasporaBankId;
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
        if (!(object instanceof TblDiaspora)) {
            return false;
        }
        TblDiaspora other = (TblDiaspora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblDiaspora[ id=" + id + " ]";
    }

}
