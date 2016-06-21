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
@Table(name = "frmwrk_template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkTemplate.findAll", query = "SELECT f FROM FrmwrkTemplate f"),
    @NamedQuery(name = "FrmwrkTemplate.findByFtId", query = "SELECT f FROM FrmwrkTemplate f WHERE f.ftId = :ftId"),
    @NamedQuery(name = "FrmwrkTemplate.findByFtName", query = "SELECT f FROM FrmwrkTemplate f WHERE f.ftName = :ftName")})
public class FrmwrkTemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ft_id")
    private Integer ftId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ft_name")
    private String ftName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ft_template")
    private byte[] ftTemplate;

    public FrmwrkTemplate() {
    }

    public FrmwrkTemplate(Integer ftId) {
        this.ftId = ftId;
    }

    public FrmwrkTemplate(Integer ftId, String ftName, byte[] ftTemplate) {
        this.ftId = ftId;
        this.ftName = ftName;
        this.ftTemplate = ftTemplate;
    }

    public Integer getFtId() {
        return ftId;
    }

    public void setFtId(Integer ftId) {
        this.ftId = ftId;
    }

    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName;
    }

    public byte[] getFtTemplate() {
        return ftTemplate;
    }

    public void setFtTemplate(byte[] ftTemplate) {
        this.ftTemplate = ftTemplate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ftId != null ? ftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkTemplate)) {
            return false;
        }
        FrmwrkTemplate other = (FrmwrkTemplate) object;
        if ((this.ftId == null && other.ftId != null) || (this.ftId != null && !this.ftId.equals(other.ftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkTemplate[ ftId=" + ftId + " ]";
    }
    
}
