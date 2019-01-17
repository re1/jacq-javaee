/*
 * Copyright 2019 wkoller.
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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "TblOrganisation.findAll", query = "SELECT t FROM TblOrganisation t")
    , @NamedQuery(name = "TblOrganisation.findById", query = "SELECT t FROM TblOrganisation t WHERE t.id = :id")
    , @NamedQuery(name = "TblOrganisation.findByDescription", query = "SELECT t FROM TblOrganisation t WHERE t.description = :description")
    , @NamedQuery(name = "TblOrganisation.findByDepartment", query = "SELECT t FROM TblOrganisation t WHERE t.department = :department")
    , @NamedQuery(name = "TblOrganisation.findByGreenhouse", query = "SELECT t FROM TblOrganisation t WHERE t.greenhouse = :greenhouse")
    , @NamedQuery(name = "TblOrganisation.findByIpenCode", query = "SELECT t FROM TblOrganisation t WHERE t.ipenCode = :ipenCode")
    , @NamedQuery(name = "TblOrganisation.findByIndexSeminumStart", query = "SELECT t FROM TblOrganisation t WHERE t.indexSeminumStart = :indexSeminumStart")
    , @NamedQuery(name = "TblOrganisation.findByAccessionStart", query = "SELECT t FROM TblOrganisation t WHERE t.accessionStart = :accessionStart")
    , @NamedQuery(name = "TblOrganisation.findByAddress", query = "SELECT t FROM TblOrganisation t WHERE t.address = :address")
    , @NamedQuery(name = "TblOrganisation.findByCity", query = "SELECT t FROM TblOrganisation t WHERE t.city = :city")
    , @NamedQuery(name = "TblOrganisation.findByPostcode", query = "SELECT t FROM TblOrganisation t WHERE t.postcode = :postcode")
    , @NamedQuery(name = "TblOrganisation.findByCountry", query = "SELECT t FROM TblOrganisation t WHERE t.country = :country")
    , @NamedQuery(name = "TblOrganisation.findByPhone", query = "SELECT t FROM TblOrganisation t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblOrganisation.findByEmail", query = "SELECT t FROM TblOrganisation t WHERE t.email = :email")})
public class TblOrganisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "index_seminum_start")
    private boolean indexSeminumStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accession_start")
    private boolean accessionStart;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "postcode")
    private String postcode;
    @Size(max = 45)
    @Column(name = "country")
    private String country;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderOrganisationId", fetch = FetchType.LAZY)
    private List<TblSeedOrder> tblSeedOrderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordererOrganisationId", fetch = FetchType.LAZY)
    private List<TblSeedOrder> tblSeedOrderList1;
    @OneToMany(mappedBy = "parentOrganisationId", fetch = FetchType.LAZY)
    private List<TblOrganisation> tblOrganisationList;
    @JoinColumn(name = "parent_organisation_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblOrganisation parentOrganisationId;
    @JoinColumn(name = "gardener_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FrmwrkUser gardenerId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblOrganisation", fetch = FetchType.LAZY)
    private TblImageServer tblImageServer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId", fetch = FetchType.LAZY)
    private List<FrmwrkaccessOrganisation> frmwrkaccessOrganisationList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblOrganisation", fetch = FetchType.LAZY)
    private TblAccessionNumber tblAccessionNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId", fetch = FetchType.LAZY)
    private List<FrmwrkUser> frmwrkUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisationId", fetch = FetchType.LAZY)
    private List<TblDerivative> tblDerivativeList;

    public TblOrganisation() {
    }

    public TblOrganisation(Long id) {
        this.id = id;
    }

    public TblOrganisation(Long id, boolean greenhouse, boolean indexSeminumStart, boolean accessionStart) {
        this.id = id;
        this.greenhouse = greenhouse;
        this.indexSeminumStart = indexSeminumStart;
        this.accessionStart = accessionStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean getIndexSeminumStart() {
        return indexSeminumStart;
    }

    public void setIndexSeminumStart(boolean indexSeminumStart) {
        this.indexSeminumStart = indexSeminumStart;
    }

    public boolean getAccessionStart() {
        return accessionStart;
    }

    public void setAccessionStart(boolean accessionStart) {
        this.accessionStart = accessionStart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<TblSeedOrder> getTblSeedOrderList() {
        return tblSeedOrderList;
    }

    public void setTblSeedOrderList(List<TblSeedOrder> tblSeedOrderList) {
        this.tblSeedOrderList = tblSeedOrderList;
    }

    @XmlTransient
    public List<TblSeedOrder> getTblSeedOrderList1() {
        return tblSeedOrderList1;
    }

    public void setTblSeedOrderList1(List<TblSeedOrder> tblSeedOrderList1) {
        this.tblSeedOrderList1 = tblSeedOrderList1;
    }

    @XmlTransient
    public List<TblOrganisation> getTblOrganisationList() {
        return tblOrganisationList;
    }

    public void setTblOrganisationList(List<TblOrganisation> tblOrganisationList) {
        this.tblOrganisationList = tblOrganisationList;
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
    public List<FrmwrkaccessOrganisation> getFrmwrkaccessOrganisationList() {
        return frmwrkaccessOrganisationList;
    }

    public void setFrmwrkaccessOrganisationList(List<FrmwrkaccessOrganisation> frmwrkaccessOrganisationList) {
        this.frmwrkaccessOrganisationList = frmwrkaccessOrganisationList;
    }

    public TblAccessionNumber getTblAccessionNumber() {
        return tblAccessionNumber;
    }

    public void setTblAccessionNumber(TblAccessionNumber tblAccessionNumber) {
        this.tblAccessionNumber = tblAccessionNumber;
    }

    @XmlTransient
    public List<FrmwrkUser> getFrmwrkUserList() {
        return frmwrkUserList;
    }

    public void setFrmwrkUserList(List<FrmwrkUser> frmwrkUserList) {
        this.frmwrkUserList = frmwrkUserList;
    }

    @XmlTransient
    public List<TblDerivative> getTblDerivativeList() {
        return tblDerivativeList;
    }

    public void setTblDerivativeList(List<TblDerivative> tblDerivativeList) {
        this.tblDerivativeList = tblDerivativeList;
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
