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
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private int scientificNameId;
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
    @Column(name = "accessible")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblSpecimen> tblSpecimenCollection;
    @JoinColumn(name = "phenology_id", referencedColumnName = "id")
    @ManyToOne
    private TblPhenology phenologyId;
    @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblAcquisitionEvent acquisitionEventId;
    @JoinColumn(name = "determined_by_id", referencedColumnName = "id")
    @ManyToOne
    private TblPerson determinedById;
    @JoinColumn(name = "ident_status_id", referencedColumnName = "ident_status_id")
    @ManyToOne
    private TblIdentStatus identStatusId;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne
    private TblOrganisation organisationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblBotanicalObjectSex> tblBotanicalObjectSexCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblImportProperties> tblImportPropertiesCollection;
    @OneToMany(mappedBy = "botanicalObjectId")
    private Collection<TblInventoryObject> tblInventoryObjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblIndexSeminumContent> tblIndexSeminumContentCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblBotanicalObject")
    private TblLivingPlant tblLivingPlant;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblBotanicalObject")
    private TblDiaspora tblDiaspora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "botanicalObjectId")
    private Collection<TblSeparation> tblSeparationCollection;

    public TblBotanicalObject() {
    }

    public TblBotanicalObject(Integer id) {
        this.id = id;
    }

    public TblBotanicalObject(Integer id, int scientificNameId, Date recordingDate, boolean accessible, boolean redetermine, boolean separated) {
        this.id = id;
        this.scientificNameId = scientificNameId;
        this.recordingDate = recordingDate;
        this.accessible = accessible;
        this.redetermine = redetermine;
        this.separated = separated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(int scientificNameId) {
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

    @XmlTransient
    public Collection<TblSpecimen> getTblSpecimenCollection() {
        return tblSpecimenCollection;
    }

    public void setTblSpecimenCollection(Collection<TblSpecimen> tblSpecimenCollection) {
        this.tblSpecimenCollection = tblSpecimenCollection;
    }

    public TblPhenology getPhenologyId() {
        return phenologyId;
    }

    public void setPhenologyId(TblPhenology phenologyId) {
        this.phenologyId = phenologyId;
    }

    public TblAcquisitionEvent getAcquisitionEventId() {
        return acquisitionEventId;
    }

    public void setAcquisitionEventId(TblAcquisitionEvent acquisitionEventId) {
        this.acquisitionEventId = acquisitionEventId;
    }

    public TblPerson getDeterminedById() {
        return determinedById;
    }

    public void setDeterminedById(TblPerson determinedById) {
        this.determinedById = determinedById;
    }

    public TblIdentStatus getIdentStatusId() {
        return identStatusId;
    }

    public void setIdentStatusId(TblIdentStatus identStatusId) {
        this.identStatusId = identStatusId;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
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

    public TblLivingPlant getTblLivingPlant() {
        return tblLivingPlant;
    }

    public void setTblLivingPlant(TblLivingPlant tblLivingPlant) {
        this.tblLivingPlant = tblLivingPlant;
    }

    public TblDiaspora getTblDiaspora() {
        return tblDiaspora;
    }

    public void setTblDiaspora(TblDiaspora tblDiaspora) {
        this.tblDiaspora = tblDiaspora;
    }

    @XmlTransient
    public Collection<TblSeparation> getTblSeparationCollection() {
        return tblSeparationCollection;
    }

    public void setTblSeparationCollection(Collection<TblSeparation> tblSeparationCollection) {
        this.tblSeparationCollection = tblSeparationCollection;
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
    @ManyToOne
    private ViewScientificName viewScientificName;

    public ViewScientificName getViewScientificName() {
        return viewScientificName;
    }
}
