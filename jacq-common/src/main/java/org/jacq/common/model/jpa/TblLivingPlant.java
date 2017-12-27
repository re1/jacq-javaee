/*
 * Copyright 2017 wkoller.
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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_living_plant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLivingPlant.findAll", query = "SELECT t FROM TblLivingPlant t")
    , @NamedQuery(name = "TblLivingPlant.findById", query = "SELECT t FROM TblLivingPlant t WHERE t.id = :id")
    , @NamedQuery(name = "TblLivingPlant.findByAccessionNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.accessionNumber = :accessionNumber")
    , @NamedQuery(name = "TblLivingPlant.findByIpenNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.ipenNumber = :ipenNumber")
    , @NamedQuery(name = "TblLivingPlant.findByIpenLocked", query = "SELECT t FROM TblLivingPlant t WHERE t.ipenLocked = :ipenLocked")
    , @NamedQuery(name = "TblLivingPlant.findByIpenType", query = "SELECT t FROM TblLivingPlant t WHERE t.ipenType = :ipenType")
    , @NamedQuery(name = "TblLivingPlant.findByPhytoControl", query = "SELECT t FROM TblLivingPlant t WHERE t.phytoControl = :phytoControl")
    , @NamedQuery(name = "TblLivingPlant.findByPlaceNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.placeNumber = :placeNumber")
    , @NamedQuery(name = "TblLivingPlant.findByIndexSeminum", query = "SELECT t FROM TblLivingPlant t WHERE t.indexSeminum = :indexSeminum")
    , @NamedQuery(name = "TblLivingPlant.findByCultivationDate", query = "SELECT t FROM TblLivingPlant t WHERE t.cultivationDate = :cultivationDate")
    , @NamedQuery(name = "TblLivingPlant.findByLabelSynonymScientificNameId", query = "SELECT t FROM TblLivingPlant t WHERE t.labelSynonymScientificNameId = :labelSynonymScientificNameId")
    , @NamedQuery(name = "TblLivingPlant.findByBgci", query = "SELECT t FROM TblLivingPlant t WHERE t.bgci = :bgci")
    , @NamedQuery(name = "TblLivingPlant.findByReviewed", query = "SELECT t FROM TblLivingPlant t WHERE t.reviewed = :reviewed")
    , @NamedQuery(name = "TblLivingPlant.findByHasImage", query = "SELECT t FROM TblLivingPlant t WHERE t.hasImage = :hasImage")
    , @NamedQuery(name = "TblLivingPlant.findByHasPublicImage", query = "SELECT t FROM TblLivingPlant t WHERE t.hasPublicImage = :hasPublicImage")})
public class TblLivingPlant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "accession_number")
    private long accessionNumber;
    @Size(max = 20)
    @Column(name = "ipen_number")
    private String ipenNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ipen_locked")
    private boolean ipenLocked;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ipen_type")
    private String ipenType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phyto_control")
    private boolean phytoControl;
    @Size(max = 20)
    @Column(name = "place_number")
    private String placeNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "index_seminum")
    private boolean indexSeminum;
    @Lob
    @Size(max = 65535)
    @Column(name = "culture_notes")
    private String cultureNotes;
    @Column(name = "cultivation_date")
    @Temporal(TemporalType.DATE)
    private Date cultivationDate;
    @Column(name = "label_synonym_scientific_name_id")
    private Long labelSynonymScientificNameId;
    @Lob
    @Size(max = 65535)
    @Column(name = "label_annotation")
    private String labelAnnotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bgci")
    private boolean bgci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reviewed")
    private boolean reviewed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_image")
    private boolean hasImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "has_public_image")
    private boolean hasPublicImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private List<TblAlternativeAccessionNumber> tblAlternativeAccessionNumberList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private List<TblRelevancy> tblRelevancyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private List<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private List<TblCertificate> tblCertificateList;
    @JoinColumn(name = "id", referencedColumnName = "derivative_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblDerivative tblDerivative;
    @JoinColumn(name = "incoming_date_id", referencedColumnName = "id")
    @ManyToOne
    private TblAcquisitionDate incomingDateId;
    @JoinColumn(name = "cultivar_id", referencedColumnName = "cultivar_id")
    @ManyToOne
    private TblCultivar cultivarId;
    @JoinColumn(name = "index_seminum_type_id", referencedColumnName = "id")
    @ManyToOne
    private TblIndexSeminumType indexSeminumTypeId;

    public TblLivingPlant() {
    }

    public TblLivingPlant(Long id) {
        this.id = id;
    }

    public TblLivingPlant(Long id, long accessionNumber, boolean ipenLocked, String ipenType, boolean phytoControl, boolean indexSeminum, boolean bgci, boolean reviewed, boolean hasImage, boolean hasPublicImage) {
        this.id = id;
        this.accessionNumber = accessionNumber;
        this.ipenLocked = ipenLocked;
        this.ipenType = ipenType;
        this.phytoControl = phytoControl;
        this.indexSeminum = indexSeminum;
        this.bgci = bgci;
        this.reviewed = reviewed;
        this.hasImage = hasImage;
        this.hasPublicImage = hasPublicImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(long accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public boolean getIpenLocked() {
        return ipenLocked;
    }

    public void setIpenLocked(boolean ipenLocked) {
        this.ipenLocked = ipenLocked;
    }

    public String getIpenType() {
        return ipenType;
    }

    public void setIpenType(String ipenType) {
        this.ipenType = ipenType;
    }

    public boolean getPhytoControl() {
        return phytoControl;
    }

    public void setPhytoControl(boolean phytoControl) {
        this.phytoControl = phytoControl;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public boolean getIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public String getCultureNotes() {
        return cultureNotes;
    }

    public void setCultureNotes(String cultureNotes) {
        this.cultureNotes = cultureNotes;
    }

    public Date getCultivationDate() {
        return cultivationDate;
    }

    public void setCultivationDate(Date cultivationDate) {
        this.cultivationDate = cultivationDate;
    }

    public Long getLabelSynonymScientificNameId() {
        return labelSynonymScientificNameId;
    }

    public void setLabelSynonymScientificNameId(Long labelSynonymScientificNameId) {
        this.labelSynonymScientificNameId = labelSynonymScientificNameId;
    }

    public String getLabelAnnotation() {
        return labelAnnotation;
    }

    public void setLabelAnnotation(String labelAnnotation) {
        this.labelAnnotation = labelAnnotation;
    }

    public boolean getBgci() {
        return bgci;
    }

    public void setBgci(boolean bgci) {
        this.bgci = bgci;
    }

    public boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public boolean getHasPublicImage() {
        return hasPublicImage;
    }

    public void setHasPublicImage(boolean hasPublicImage) {
        this.hasPublicImage = hasPublicImage;
    }

    @XmlTransient
    public List<TblAlternativeAccessionNumber> getTblAlternativeAccessionNumberList() {
        return tblAlternativeAccessionNumberList;
    }

    public void setTblAlternativeAccessionNumberList(List<TblAlternativeAccessionNumber> tblAlternativeAccessionNumberList) {
        this.tblAlternativeAccessionNumberList = tblAlternativeAccessionNumberList;
    }

    @XmlTransient
    public List<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePageList() {
        return tblLivingPlantTreeRecordFilePageList;
    }

    public void setTblLivingPlantTreeRecordFilePageList(List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageList) {
        this.tblLivingPlantTreeRecordFilePageList = tblLivingPlantTreeRecordFilePageList;
    }

    @XmlTransient
    public List<TblRelevancy> getTblRelevancyList() {
        return tblRelevancyList;
    }

    public void setTblRelevancyList(List<TblRelevancy> tblRelevancyList) {
        this.tblRelevancyList = tblRelevancyList;
    }

    @XmlTransient
    public List<FrmwrkaccessBotanicalObject> getFrmwrkaccessBotanicalObjectList() {
        return frmwrkaccessBotanicalObjectList;
    }

    public void setFrmwrkaccessBotanicalObjectList(List<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectList) {
        this.frmwrkaccessBotanicalObjectList = frmwrkaccessBotanicalObjectList;
    }

    @XmlTransient
    public List<TblCertificate> getTblCertificateList() {
        return tblCertificateList;
    }

    public void setTblCertificateList(List<TblCertificate> tblCertificateList) {
        this.tblCertificateList = tblCertificateList;
    }

    public TblDerivative getTblDerivative() {
        return tblDerivative;
    }

    public void setTblDerivative(TblDerivative tblDerivative) {
        this.tblDerivative = tblDerivative;
    }

    public TblAcquisitionDate getIncomingDateId() {
        return incomingDateId;
    }

    public void setIncomingDateId(TblAcquisitionDate incomingDateId) {
        this.incomingDateId = incomingDateId;
    }

    public TblCultivar getCultivarId() {
        return cultivarId;
    }

    public void setCultivarId(TblCultivar cultivarId) {
        this.cultivarId = cultivarId;
    }

    public TblIndexSeminumType getIndexSeminumTypeId() {
        return indexSeminumTypeId;
    }

    public void setIndexSeminumTypeId(TblIndexSeminumType indexSeminumTypeId) {
        this.indexSeminumTypeId = indexSeminumTypeId;
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
        if (!(object instanceof TblLivingPlant)) {
            return false;
        }
        TblLivingPlant other = (TblLivingPlant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLivingPlant[ id=" + id + " ]";
    }

}
