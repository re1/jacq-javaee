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
import javax.persistence.Lob;
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
@Table(name = "tbl_import_error")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblImportError.findAll", query = "SELECT t FROM TblImportError t"),
    @NamedQuery(name = "TblImportError.findById", query = "SELECT t FROM TblImportError t WHERE t.id = :id"),
    @NamedQuery(name = "TblImportError.findByIDPflanze", query = "SELECT t FROM TblImportError t WHERE t.iDPflanze = :iDPflanze")})
public class TblImportError implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPflanze")
    private int iDPflanze;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;

    public TblImportError() {
    }

    public TblImportError(Integer id) {
        this.id = id;
    }

    public TblImportError(Integer id, int iDPflanze, String message) {
        this.id = id;
        this.iDPflanze = iDPflanze;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIDPflanze() {
        return iDPflanze;
    }

    public void setIDPflanze(int iDPflanze) {
        this.iDPflanze = iDPflanze;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof TblImportError)) {
            return false;
        }
        TblImportError other = (TblImportError) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblImportError[ id=" + id + " ]";
    }

}
