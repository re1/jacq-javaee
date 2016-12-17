/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import javax.persistence.OneToOne;
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
@Table(name = "tbl_organisation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrganisation.findAll", query = "SELECT t FROM TblOrganisation t"),
    @NamedQuery(name = "TblOrganisation.findById", query = "SELECT t FROM TblOrganisation t WHERE t.id = :id"),
    @NamedQuery(name = "TblOrganisation.findByDescription", query = "SELECT t FROM TblOrganisation t WHERE t.description = :description"),
    @NamedQuery(name = "TblOrganisation.findByDepartment", query = "SELECT t FROM TblOrganisation t WHERE t.department = :department"),
    @NamedQuery(name = "TblOrganisation.findByGreenhouse", query = "SELECT t FROM TblOrganisation t WHERE t.greenhouse = :greenhouse"),
    @NamedQuery(name = "TblOrganisation.findByIpenCode", query = "SELECT t FROM TblOrganisation t WHERE t.ipenCode = :ipenCode")})
public class TblOrganisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @NotNull
    @Column(name = "greenhouse")
    private boolean greenhouse;
    @Size(max = 5)
    @Column(name = "ipen_code")
    private String ipenCode;
    @OneToMany(mappedBy = "organisationId")
    private Collection<TblBotanicalObject> tblBotanicalObjectCollection;
    @OneToMany(mappedBy = "parentOrganisationId")
    private Collection<TblOrganisation> tblOrganisationCollection;
    @JoinColumn(name = "parent_organisation_id", referencedColumnName = "id")
    @ManyToOne
    private TblOrganisation parentOrganisationId;
    @JoinColumn(name = "gardener_id", referencedColumnName = "id")
    @ManyToOne
    private FrmwrkUser gardenerId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblOrganisation")
    private TblImageServer tblImageServer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId")
    private Collection<FrmwrkaccessOrganisation> frmwrkaccessOrganisationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId")
    private Collection<TblDerivativeVegetative> tblDerivativeVegetativeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId")
    private Collection<FrmwrkUser> frmwrkUserCollection;

    public TblOrganisation() {
    }

    public TblOrganisation(Integer id) {
        this.id = id;
    }

    public TblOrganisation(Integer id, boolean greenhouse) {
        this.id = id;
        this.greenhouse = greenhouse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(boolean greenhouse) {
        this.greenhouse = greenhouse;
    }

    public String getIpenCode() {
        return ipenCode;
    }

    public void setIpenCode(String ipenCode) {
        this.ipenCode = ipenCode;
    }

    @XmlTransient
    public Collection<TblBotanicalObject> getTblBotanicalObjectCollection() {
        return tblBotanicalObjectCollection;
    }

    public void setTblBotanicalObjectCollection(Collection<TblBotanicalObject> tblBotanicalObjectCollection) {
        this.tblBotanicalObjectCollection = tblBotanicalObjectCollection;
    }

    @XmlTransient
    public Collection<TblOrganisation> getTblOrganisationCollection() {
        return tblOrganisationCollection;
    }

    public void setTblOrganisationCollection(Collection<TblOrganisation> tblOrganisationCollection) {
        this.tblOrganisationCollection = tblOrganisationCollection;
    }

    public TblOrganisation getParentOrganisationId() {
        return parentOrganisationId;
    }

    public void setParentOrganisationId(TblOrganisation parentOrganisationId) {
        this.parentOrganisationId = parentOrganisationId;
    }

    public FrmwrkUser getGardenerId() {
        return gardenerId;
    }

    public void setGardenerId(FrmwrkUser gardenerId) {
        this.gardenerId = gardenerId;
    }

    public TblImageServer getTblImageServer() {
        return tblImageServer;
    }

    public void setTblImageServer(TblImageServer tblImageServer) {
        this.tblImageServer = tblImageServer;
    }

    @XmlTransient
    public Collection<FrmwrkaccessOrganisation> getFrmwrkaccessOrganisationCollection() {
        return frmwrkaccessOrganisationCollection;
    }

    public void setFrmwrkaccessOrganisationCollection(Collection<FrmwrkaccessOrganisation> frmwrkaccessOrganisationCollection) {
        this.frmwrkaccessOrganisationCollection = frmwrkaccessOrganisationCollection;
    }

    @XmlTransient
    public Collection<TblDerivativeVegetative> getTblDerivativeVegetativeCollection() {
        return tblDerivativeVegetativeCollection;
    }

    public void setTblDerivativeVegetativeCollection(Collection<TblDerivativeVegetative> tblDerivativeVegetativeCollection) {
        this.tblDerivativeVegetativeCollection = tblDerivativeVegetativeCollection;
    }

    @XmlTransient
    public Collection<FrmwrkUser> getFrmwrkUserCollection() {
        return frmwrkUserCollection;
    }

    public void setFrmwrkUserCollection(Collection<FrmwrkUser> frmwrkUserCollection) {
        this.frmwrkUserCollection = frmwrkUserCollection;
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
        if (!(object instanceof TblOrganisation)) {
            return false;
        }
        TblOrganisation other = (TblOrganisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblOrganisation[ id=" + id + " ]";
    }

}
