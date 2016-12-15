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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "tbl_botanical_object")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBotanicalObject.findAll", query = "SELECT t FROM TblBotanicalObject t"),
    @NamedQuery(name = "TblBotanicalObject.findById", query = "SELECT t FROM TblBotanicalObject t WHERE t.id = :id"),
    @NamedQuery(name = "TblBotanicalObject.findByScientificNameId", query = "SELECT t FROM TblBotanicalObject t WHERE t.scientificNameId = :scientificNameId"),
    @NamedQuery(name = "TblBotanicalObject.findByDeterminationDate", query = "SELECT t FROM TblBotanicalObject t WHERE t.determinationDate = :determinationDate"),
    @NamedQuery(name = "TblBotanicalObject.findByHabitat", query = "SELECT t FROM TblBotanicalObject t WHERE t.habitat = :habitat"),
    @NamedQuery(name = "TblBotanicalObject.findByHabitus", query = "SELECT t FROM TblBotanicalObject t WHERE t.habitus = :habitus"),
    @NamedQuery(name = "TblBotanicalObject.findByRecordingDate", query = "SELECT t FROM TblBotanicalObject t WHERE t.recordingDate = :recordingDate"),
    @NamedQuery(name = "TblBotanicalObject.findByAccessible", query = "SELECT t FROM TblBotanicalObject t WHERE t.accessible = :accessible"),
    @NamedQuery(name = "TblBotanicalObject.findByRedetermine", query = "SELECT t FROM TblBotanicalObject t WHERE t.redetermine = :redetermine"),
    @NamedQuery(name = "TblBotanicalObject.findBySeparated", query = "SELECT t FROM TblBotanicalObject t WHERE t.separated = :separated")})
public class TblBotanicalObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private Long scientificNameId;
    @Column(name = "determination_date")
    @Temporal(TemporalType.DATE)
    private Date determinationDate;
    @Size(max = 45)
    @Column(name = "habitat")
    private String habitat;
    @Size(max = 45)
    @Column(name = "habitus")
    private String habitus;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recording_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "[accessible]")
    private boolean accessible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redetermine")
    private boolean redetermine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "separated")
    private boolean separated;
    @JoinTable(name = "tbl_botanical_object_label", joinColumns = {
        @JoinColumn(name = "botanical_object_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "label_type_id", referencedColumnName = "label_type_id")})
    @ManyToMany
    private Collection<TblLabelType> tblLabelTypeCollection;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne
    private TblOrganisation organisationId;
    @JoinColumn(name = "ident_status_id", referencedColumnName = "ident_status_id")
    @ManyToOne
    private TblIdentStatus identStatusId;
    @JoinColumn(name = "determined_by_id", referencedColumnName = "id")
    @ManyToOne
    private TblPerson determinedById;
    @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblAcquisitionEvent acquisitionEventId;
    @JoinColumn(name = "phenology_id", referencedColumnName = "id")
    @ManyToOne
    private TblPhenology phenologyId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblBotanicalObject")
    private TblDiaspora tblDiaspora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblSpecimen> tblSpecimenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblSeparation> tblSeparationCollection;
    @OneToMany(mappedBy = "botanicalObjectId")
    private Collection<TblInventoryObject> tblInventoryObjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblBotanicalObjectSex> tblBotanicalObjectSexCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblImportProperties> tblImportPropertiesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblBotanicalObject")
    private TblLivingPlant tblLivingPlant;

    public TblBotanicalObject() {
    }

    public TblBotanicalObject(Long id) {
        this.id = id;
    }

    public TblBotanicalObject(Long id, Long scientificNameId, Date recordingDate, boolean accessible, boolean redetermine, boolean separated) {
        this.id = id;
        this.scientificNameId = scientificNameId;
        this.recordingDate = recordingDate;
        this.accessible = accessible;
        this.redetermine = redetermine;
        this.separated = separated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Date getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Date determinationDate) {
        this.determinationDate = determinationDate;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getHabitus() {
        return habitus;
    }

    public void setHabitus(String habitus) {
        this.habitus = habitus;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean getRedetermine() {
        return redetermine;
    }

    public void setRedetermine(boolean redetermine) {
        this.redetermine = redetermine;
    }

    public boolean getSeparated() {
        return separated;
    }

    public void setSeparated(boolean separated) {
        this.separated = separated;
    }

    @XmlTransient
    public Collection<TblLabelType> getTblLabelTypeCollection() {
        return tblLabelTypeCollection;
    }

    public void setTblLabelTypeCollection(Collection<TblLabelType> tblLabelTypeCollection) {
        this.tblLabelTypeCollection = tblLabelTypeCollection;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
    }

    public TblIdentStatus getIdentStatusId() {
        return identStatusId;
    }

    public void setIdentStatusId(TblIdentStatus identStatusId) {
        this.identStatusId = identStatusId;
    }

    public TblPerson getDeterminedById() {
        return determinedById;
    }

    public void setDeterminedById(TblPerson determinedById) {
        this.determinedById = determinedById;
    }

    public TblAcquisitionEvent getAcquisitionEventId() {
        return acquisitionEventId;
    }

    public void setAcquisitionEventId(TblAcquisitionEvent acquisitionEventId) {
        this.acquisitionEventId = acquisitionEventId;
    }

    public TblPhenology getPhenologyId() {
        return phenologyId;
    }

    public void setPhenologyId(TblPhenology phenologyId) {
        this.phenologyId = phenologyId;
    }

    public TblDiaspora getTblDiaspora() {
        return tblDiaspora;
    }

    public void setTblDiaspora(TblDiaspora tblDiaspora) {
        this.tblDiaspora = tblDiaspora;
    }

    @XmlTransient
    public Collection<TblSpecimen> getTblSpecimenCollection() {
        return tblSpecimenCollection;
    }

    public void setTblSpecimenCollection(Collection<TblSpecimen> tblSpecimenCollection) {
        this.tblSpecimenCollection = tblSpecimenCollection;
    }

    @XmlTransient
    public Collection<TblSeparation> getTblSeparationCollection() {
        return tblSeparationCollection;
    }

    public void setTblSeparationCollection(Collection<TblSeparation> tblSeparationCollection) {
        this.tblSeparationCollection = tblSeparationCollection;
    }

    @XmlTransient
    public Collection<TblInventoryObject> getTblInventoryObjectCollection() {
        return tblInventoryObjectCollection;
    }

    public void setTblInventoryObjectCollection(Collection<TblInventoryObject> tblInventoryObjectCollection) {
        this.tblInventoryObjectCollection = tblInventoryObjectCollection;
    }

    @XmlTransient
    public Collection<TblIndexSeminumContent> getTblIndexSeminumContentCollection() {
        return tblIndexSeminumContentCollection;
    }

    public void setTblIndexSeminumContentCollection(Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection) {
        this.tblIndexSeminumContentCollection = tblIndexSeminumContentCollection;
    }

    @XmlTransient
    public Collection<TblBotanicalObjectSex> getTblBotanicalObjectSexCollection() {
        return tblBotanicalObjectSexCollection;
    }

    public void setTblBotanicalObjectSexCollection(Collection<TblBotanicalObjectSex> tblBotanicalObjectSexCollection) {
        this.tblBotanicalObjectSexCollection = tblBotanicalObjectSexCollection;
    }

    @XmlTransient
    public Collection<TblImportProperties> getTblImportPropertiesCollection() {
        return tblImportPropertiesCollection;
    }

    public void setTblImportPropertiesCollection(Collection<TblImportProperties> tblImportPropertiesCollection) {
        this.tblImportPropertiesCollection = tblImportPropertiesCollection;
    }

    public TblLivingPlant getTblLivingPlant() {
        return tblLivingPlant;
    }

    public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
        this.tblLivingPlant = tblLivingPlant;
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
        if (!(object instanceof TblBotanicalObject)) {
            return false;
        }
        TblBotanicalObject other = (TblBotanicalObject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblBotanicalObject[ id=" + id + " ]";
    }

    /**
     * Custom mappings
     */
    @JoinColumn(name = "scientific_name_id", referencedColumnName = "scientific_name_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ViewScientificName viewScientificName;

    public ViewScientificName getViewScientificName() {
        return viewScientificName;
    }

    @JoinColumn(name = "scientific_name_id", referencedColumnName = "taxonID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ViewTaxon viewTaxon;

    public ViewTaxon getViewTaxon() {
        return viewTaxon;
    }
}
