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
@Table(name = "frmwrk_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkConfig.findAll", query = "SELECT f FROM FrmwrkConfig f"),
    @NamedQuery(name = "FrmwrkConfig.findByFcId", query = "SELECT f FROM FrmwrkConfig f WHERE f.fcId = :fcId"),
    @NamedQuery(name = "FrmwrkConfig.findByFcName", query = "SELECT f FROM FrmwrkConfig f WHERE f.fcName = :fcName")})
public class FrmwrkConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fc_id")
    private Integer fcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fc_name")
    private String fcName;
    @Lob
    @Size(max = 65535)
    @Column(name = "fc_value")
    private String fcValue;

    public FrmwrkConfig() {
    }

    public FrmwrkConfig(Integer fcId) {
        this.fcId = fcId;
    }

    public FrmwrkConfig(Integer fcId, String fcName) {
        this.fcId = fcId;
        this.fcName = fcName;
    }

    public Integer getFcId() {
        return fcId;
    }

    public void setFcId(Integer fcId) {
        this.fcId = fcId;
    }

    public String getFcName() {
        return fcName;
    }

    public void setFcName(String fcName) {
        this.fcName = fcName;
    }

    public String getFcValue() {
        return fcValue;
    }

    public void setFcValue(String fcValue) {
        this.fcValue = fcValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fcId != null ? fcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkConfig)) {
            return false;
        }
        FrmwrkConfig other = (FrmwrkConfig) object;
        if ((this.fcId == null && other.fcId != null) || (this.fcId != null && !this.fcId.equals(other.fcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkConfig[ fcId=" + fcId + " ]";
    }
    
}
