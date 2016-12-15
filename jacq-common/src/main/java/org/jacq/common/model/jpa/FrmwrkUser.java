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
@Table(name = "frmwrk_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkUser.findAll", query = "SELECT f FROM FrmwrkUser f"),
    @NamedQuery(name = "FrmwrkUser.findById", query = "SELECT f FROM FrmwrkUser f WHERE f.id = :id"),
    @NamedQuery(name = "FrmwrkUser.findByUsername", query = "SELECT f FROM FrmwrkUser f WHERE f.username = :username"),
    @NamedQuery(name = "FrmwrkUser.findByPassword", query = "SELECT f FROM FrmwrkUser f WHERE f.password = :password"),
    @NamedQuery(name = "FrmwrkUser.findBySalt", query = "SELECT f FROM FrmwrkUser f WHERE f.salt = :salt"),
    @NamedQuery(name = "FrmwrkUser.findByTitlePrefix", query = "SELECT f FROM FrmwrkUser f WHERE f.titlePrefix = :titlePrefix"),
    @NamedQuery(name = "FrmwrkUser.findByFirstname", query = "SELECT f FROM FrmwrkUser f WHERE f.firstname = :firstname"),
    @NamedQuery(name = "FrmwrkUser.findByLastname", query = "SELECT f FROM FrmwrkUser f WHERE f.lastname = :lastname"),
    @NamedQuery(name = "FrmwrkUser.findByTitleSuffix", query = "SELECT f FROM FrmwrkUser f WHERE f.titleSuffix = :titleSuffix"),
    @NamedQuery(name = "FrmwrkUser.findByBirthdate", query = "SELECT f FROM FrmwrkUser f WHERE f.birthdate = :birthdate"),
    @NamedQuery(name = "FrmwrkUser.findByForcePasswordChange", query = "SELECT f FROM FrmwrkUser f WHERE f.forcePasswordChange = :forcePasswordChange")})
public class FrmwrkUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "salt")
    private String salt;
    @Size(max = 45)
    @Column(name = "title_prefix")
    private String titlePrefix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 45)
    @Column(name = "title_suffix")
    private String titleSuffix;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "force_password_change")
    private boolean forcePasswordChange;
    @OneToMany(mappedBy = "userId")
    private Collection<FrmwrkaccessClassification> frmwrkaccessClassificationCollection;
    @OneToMany(mappedBy = "gardenerId")
    private Collection<TblOrganisation> tblOrganisationCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<FrmwrkaccessOrganisation> frmwrkaccessOrganisationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<TblInventory> tblInventoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frmwrkUser")
    private Collection<FrmwrkAuthAssignment> frmwrkAuthAssignmentCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<TblIndexSeminumRevision> tblIndexSeminumRevisionCollection;
    @JoinColumn(name = "employment_type_id", referencedColumnName = "employment_type_id")
    @ManyToOne(optional = false)
    private FrmwrkEmploymentType employmentTypeId;
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    @ManyToOne(optional = false)
    private FrmwrkUserType userTypeId;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblOrganisation organisationId;

    public FrmwrkUser() {
    }

    public FrmwrkUser(Integer id) {
        this.id = id;
    }

    public FrmwrkUser(Integer id, String username, String password, String salt, String firstname, String lastname, boolean forcePasswordChange) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.firstname = firstname;
        this.lastname = lastname;
        this.forcePasswordChange = forcePasswordChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean getForcePasswordChange() {
        return forcePasswordChange;
    }

    public void setForcePasswordChange(boolean forcePasswordChange) {
        this.forcePasswordChange = forcePasswordChange;
    }

    @XmlTransient
    public Collection<FrmwrkaccessClassification> getFrmwrkaccessClassificationCollection() {
        return frmwrkaccessClassificationCollection;
    }

    public void setFrmwrkaccessClassificationCollection(Collection<FrmwrkaccessClassification> frmwrkaccessClassificationCollection) {
        this.frmwrkaccessClassificationCollection = frmwrkaccessClassificationCollection;
    }

    @XmlTransient
    public Collection<TblOrganisation> getTblOrganisationCollection() {
        return tblOrganisationCollection;
    }

    public void setTblOrganisationCollection(Collection<TblOrganisation> tblOrganisationCollection) {
        this.tblOrganisationCollection = tblOrganisationCollection;
    }

    @XmlTransient
    public Collection<FrmwrkaccessOrganisation> getFrmwrkaccessOrganisationCollection() {
        return frmwrkaccessOrganisationCollection;
    }

    public void setFrmwrkaccessOrganisationCollection(Collection<FrmwrkaccessOrganisation> frmwrkaccessOrganisationCollection) {
        this.frmwrkaccessOrganisationCollection = frmwrkaccessOrganisationCollection;
    }

    @XmlTransient
    public Collection<TblInventory> getTblInventoryCollection() {
        return tblInventoryCollection;
    }

    public void setTblInventoryCollection(Collection<TblInventory> tblInventoryCollection) {
        this.tblInventoryCollection = tblInventoryCollection;
    }

    @XmlTransient
    public Collection<FrmwrkAuthAssignment> getFrmwrkAuthAssignmentCollection() {
        return frmwrkAuthAssignmentCollection;
    }

    public void setFrmwrkAuthAssignmentCollection(Collection<FrmwrkAuthAssignment> frmwrkAuthAssignmentCollection) {
        this.frmwrkAuthAssignmentCollection = frmwrkAuthAssignmentCollection;
    }

    @XmlTransient
    public Collection<FrmwrkaccessBotanicalObject> getFrmwrkaccessBotanicalObjectCollection() {
        return frmwrkaccessBotanicalObjectCollection;
    }

    public void setFrmwrkaccessBotanicalObjectCollection(Collection<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectCollection) {
        this.frmwrkaccessBotanicalObjectCollection = frmwrkaccessBotanicalObjectCollection;
    }

    @XmlTransient
    public Collection<TblIndexSeminumRevision> getTblIndexSeminumRevisionCollection() {
        return tblIndexSeminumRevisionCollection;
    }

    public void setTblIndexSeminumRevisionCollection(Collection<TblIndexSeminumRevision> tblIndexSeminumRevisionCollection) {
        this.tblIndexSeminumRevisionCollection = tblIndexSeminumRevisionCollection;
    }

    public FrmwrkEmploymentType getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(FrmwrkEmploymentType employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public FrmwrkUserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(FrmwrkUserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
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
        if (!(object instanceof FrmwrkUser)) {
            return false;
        }
        FrmwrkUser other = (FrmwrkUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkUser[ id=" + id + " ]";
    }

}
