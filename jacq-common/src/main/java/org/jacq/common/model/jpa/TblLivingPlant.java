/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    @NamedQuery(name = "TblLivingPlant.findAll", query = "SELECT t FROM TblLivingPlant t"),
    @NamedQuery(name = "TblLivingPlant.findById", query = "SELECT t FROM TblLivingPlant t WHERE t.id = :id"),
    @NamedQuery(name = "TblLivingPlant.findByAccessionNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.accessionNumber = :accessionNumber"),
    @NamedQuery(name = "TblLivingPlant.findByIpenNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.ipenNumber = :ipenNumber"),
    @NamedQuery(name = "TblLivingPlant.findByIpenLocked", query = "SELECT t FROM TblLivingPlant t WHERE t.ipenLocked = :ipenLocked"),
    @NamedQuery(name = "TblLivingPlant.findByPhytoControl", query = "SELECT t FROM TblLivingPlant t WHERE t.phytoControl = :phytoControl"),
    @NamedQuery(name = "TblLivingPlant.findByPlaceNumber", query = "SELECT t FROM TblLivingPlant t WHERE t.placeNumber = :placeNumber"),
    @NamedQuery(name = "TblLivingPlant.findByIndexSeminum", query = "SELECT t FROM TblLivingPlant t WHERE t.indexSeminum = :indexSeminum"),
    @NamedQuery(name = "TblLivingPlant.findByCultivationDate", query = "SELECT t FROM TblLivingPlant t WHERE t.cultivationDate = :cultivationDate"),
    @NamedQuery(name = "TblLivingPlant.findByLabelSynonymScientificNameId", query = "SELECT t FROM TblLivingPlant t WHERE t.labelSynonymScientificNameId = :labelSynonymScientificNameId"),
    @NamedQuery(name = "TblLivingPlant.findByBgci", query = "SELECT t FROM TblLivingPlant t WHERE t.bgci = :bgci")})
public class TblLivingPlant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "accession_number")
    private int accessionNumber;
    @Size(max = 20)
    @Column(name = "ipen_number")
    private String ipenNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ipen_locked")
    private boolean ipenLocked;
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
    private Integer labelSynonymScientificNameId;
    @Lob
    @Size(max = 65535)
    @Column(name = "label_annotation")
    private String labelAnnotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bgci")
    private boolean bgci;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private Collection<TblCertificate> tblCertificateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private Collection<TblRelevancy> tblRelevancyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private Collection<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageCollection;
    @JoinColumn(name = "index_seminum_type_id", referencedColumnName = "id")
    @ManyToOne
    private TblIndexSeminumType indexSeminumTypeId;
    @JoinColumn(name = "cultivar_id", referencedColumnName = "cultivar_id")
    @ManyToOne
    private TblCultivar cultivarId;
    @JoinColumn(name = "incoming_date_id", referencedColumnName = "id")
    @ManyToOne
    private TblAcquisitionDate incomingDateId;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblBotanicalObject tblBotanicalObject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livingPlantId")
    private Collection<TblAlternativeAccessionNumber> tblAlternativeAccessionNumberCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectCollection;

    public TblLivingPlant() {
    }

    public TblLivingPlant(Integer id) {
        this.id = id;
    }

    public TblLivingPlant(Integer id, int accessionNumber, boolean ipenLocked, boolean phytoControl, boolean indexSeminum, boolean bgci) {
        this.id = id;
        this.accessionNumber = accessionNumber;
        this.ipenLocked = ipenLocked;
        this.phytoControl = phytoControl;
        this.indexSeminum = indexSeminum;
        this.bgci = bgci;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(int accessionNumber) {
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

    public Integer getLabelSynonymScientificNameId() {
        return labelSynonymScientificNameId;
    }

    public void setLabelSynonymScientificNameId(Integer labelSynonymScientificNameId) {
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

    @XmlTransient
    public Collection<TblCertificate> getTblCertificateCollection() {
        return tblCertificateCollection;
    }

    public void setTblCertificateCollection(Collection<TblCertificate> tblCertificateCollection) {
        this.tblCertificateCollection = tblCertificateCollection;
    }

    @XmlTransient
    public Collection<TblRelevancy> getTblRelevancyCollection() {
        return tblRelevancyCollection;
    }

    public void setTblRelevancyCollection(Collection<TblRelevancy> tblRelevancyCollection) {
        this.tblRelevancyCollection = tblRelevancyCollection;
    }

    @XmlTransient
    public Collection<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePageCollection() {
        return tblLivingPlantTreeRecordFilePageCollection;
    }

    public void setTblLivingPlantTreeRecordFilePageCollection(Collection<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageCollection) {
        this.tblLivingPlantTreeRecordFilePageCollection = tblLivingPlantTreeRecordFilePageCollection;
    }

    public TblIndexSeminumType getIndexSeminumTypeId() {
        return indexSeminumTypeId;
    }

    public void setIndexSeminumTypeId(TblIndexSeminumType indexSeminumTypeId) {
        this.indexSeminumTypeId = indexSeminumTypeId;
    }

    public TblCultivar getCultivarId() {
        return cultivarId;
    }

    public void setCultivarId(TblCultivar cultivarId) {
        this.cultivarId = cultivarId;
    }

    public TblAcquisitionDate getIncomingDateId() {
        return incomingDateId;
    }

    public void setIncomingDateId(TblAcquisitionDate incomingDateId) {
        this.incomingDateId = incomingDateId;
    }

    public TblBotanicalObject getTblBotanicalObject() {
        return tblBotanicalObject;
    }

    public void setTblBotanicalObject(TblBotanicalObject tblBotanicalObject) {
        this.tblBotanicalObject = tblBotanicalObject;
    }

    @XmlTransient
    public Collection<TblAlternativeAccessionNumber> getTblAlternativeAccessionNumberCollection() {
        return tblAlternativeAccessionNumberCollection;
    }

    public void setTblAlternativeAccessionNumberCollection(Collection<TblAlternativeAccessionNumber> tblAlternativeAccessionNumberCollection) {
        this.tblAlternativeAccessionNumberCollection = tblAlternativeAccessionNumberCollection;
    }

    @XmlTransient
    public Collection<FrmwrkaccessBotanicalObject> getFrmwrkaccessBotanicalObjectCollection() {
        return frmwrkaccessBotanicalObjectCollection;
    }

    public void setFrmwrkaccessBotanicalObjectCollection(Collection<FrmwrkaccessBotanicalObject> frmwrkaccessBotanicalObjectCollection) {
        this.frmwrkaccessBotanicalObjectCollection = frmwrkaccessBotanicalObjectCollection;
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
