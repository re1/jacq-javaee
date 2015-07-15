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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "tbl_tax_classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTaxClassification.findAllTopLevel", query = "SELECT t FROM TaxClassification t WHERE t.parentTaxClassification IS NULL AND EXISTS( FROM TaxClassification AS children WHERE parentTaxClassification = t )"),
    @NamedQuery(name = "TblTaxClassification.findAll", query = "SELECT t FROM TblTaxClassification t"),
    @NamedQuery(name = "TblTaxClassification.findByTaxClassificationId", query = "SELECT t FROM TblTaxClassification t WHERE t.taxClassificationId = :taxClassificationId"),
    @NamedQuery(name = "TblTaxClassification.findByScientificNameId", query = "SELECT t FROM TblTaxClassification t WHERE t.scientificNameId = :scientificNameId"),
    @NamedQuery(name = "TblTaxClassification.findByAcceptedScientificNameId", query = "SELECT t FROM TblTaxClassification t WHERE t.acceptedScientificNameId = :acceptedScientificNameId"),
    @NamedQuery(name = "TblTaxClassification.findByRefDate", query = "SELECT t FROM TblTaxClassification t WHERE t.refDate = :refDate"),
    @NamedQuery(name = "TblTaxClassification.findByPreferredTaxonomy", query = "SELECT t FROM TblTaxClassification t WHERE t.preferredTaxonomy = :preferredTaxonomy"),
    @NamedQuery(name = "TblTaxClassification.findByLocked", query = "SELECT t FROM TblTaxClassification t WHERE t.locked = :locked"),
    @NamedQuery(name = "TblTaxClassification.findBySource", query = "SELECT t FROM TblTaxClassification t WHERE t.source = :source"),
    @NamedQuery(name = "TblTaxClassification.findBySourceCitationId", query = "SELECT t FROM TblTaxClassification t WHERE t.sourceCitationId = :sourceCitationId"),
    @NamedQuery(name = "TblTaxClassification.findBySourcePersonId", query = "SELECT t FROM TblTaxClassification t WHERE t.sourcePersonId = :sourcePersonId"),
    @NamedQuery(name = "TblTaxClassification.findBySourceServiceId", query = "SELECT t FROM TblTaxClassification t WHERE t.sourceServiceId = :sourceServiceId"),
    @NamedQuery(name = "TblTaxClassification.findBySourceSpecimenId", query = "SELECT t FROM TblTaxClassification t WHERE t.sourceSpecimenId = :sourceSpecimenId"),
    @NamedQuery(name = "TblTaxClassification.findByUserId", query = "SELECT t FROM TblTaxClassification t WHERE t.userId = :userId"),
    @NamedQuery(name = "TblTaxClassification.findByNumber", query = "SELECT t FROM TblTaxClassification t WHERE t.number = :number"),
    @NamedQuery(name = "TblTaxClassification.findByOrder", query = "SELECT t FROM TblTaxClassification t WHERE t.order = :order"),
    @NamedQuery(name = "TblTaxClassification.findByTimestamp", query = "SELECT t FROM TblTaxClassification t WHERE t.timestamp = :timestamp")

})
public class TblTaxClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tax_classification_id")
    private Integer taxClassificationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private int scientificNameId;
    @Column(name = "accepted_scientific_name_id")
    private Integer acceptedScientificNameId;
    @Column(name = "ref_date")
    @Temporal(TemporalType.DATE)
    private Date refDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preferred_taxonomy")
    private short preferredTaxonomy;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "annotations")
    private String annotations;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private short locked;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "source")
    private String source;
    @Column(name = "source_citation_id")
    private Integer sourceCitationId;
    @Column(name = "source_person_id")
    private Integer sourcePersonId;
    @Column(name = "source_service_id")
    private Integer sourceServiceId;
    @Column(name = "source_specimen_id")
    private Integer sourceSpecimenId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Size(max = 15)
    @Column(name = "number")
    private String number;
    @Column(name = "order")
    private Integer order;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(mappedBy = "parentScientificNameId")
    private Collection<TblTaxClassification> tblTaxClassificationCollection;
    @JoinColumn(name = "parent_scientific_name_id", referencedColumnName = "scientific_name_id")
    @ManyToOne
    private TblTaxClassification parentScientificNameId;

    public TblTaxClassification() {
    }

    public TblTaxClassification(Integer taxClassificationId) {
        this.taxClassificationId = taxClassificationId;
    }

    public TblTaxClassification(Integer taxClassificationId, int scientificNameId, short preferredTaxonomy, short locked, String source, int userId, Date timestamp) {
        this.taxClassificationId = taxClassificationId;
        this.scientificNameId = scientificNameId;
        this.preferredTaxonomy = preferredTaxonomy;
        this.locked = locked;
        this.source = source;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public Integer getTaxClassificationId() {
        return taxClassificationId;
    }

    public void setTaxClassificationId(Integer taxClassificationId) {
        this.taxClassificationId = taxClassificationId;
    }

    public int getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(int scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Integer getAcceptedScientificNameId() {
        return acceptedScientificNameId;
    }

    public void setAcceptedScientificNameId(Integer acceptedScientificNameId) {
        this.acceptedScientificNameId = acceptedScientificNameId;
    }

    public Date getRefDate() {
        return refDate;
    }

    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }

    public short getPreferredTaxonomy() {
        return preferredTaxonomy;
    }

    public void setPreferredTaxonomy(short preferredTaxonomy) {
        this.preferredTaxonomy = preferredTaxonomy;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public short getLocked() {
        return locked;
    }

    public void setLocked(short locked) {
        this.locked = locked;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getSourceCitationId() {
        return sourceCitationId;
    }

    public void setSourceCitationId(Integer sourceCitationId) {
        this.sourceCitationId = sourceCitationId;
    }

    public Integer getSourcePersonId() {
        return sourcePersonId;
    }

    public void setSourcePersonId(Integer sourcePersonId) {
        this.sourcePersonId = sourcePersonId;
    }

    public Integer getSourceServiceId() {
        return sourceServiceId;
    }

    public void setSourceServiceId(Integer sourceServiceId) {
        this.sourceServiceId = sourceServiceId;
    }

    public Integer getSourceSpecimenId() {
        return sourceSpecimenId;
    }

    public void setSourceSpecimenId(Integer sourceSpecimenId) {
        this.sourceSpecimenId = sourceSpecimenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblTaxClassification> getTblTaxClassificationCollection() {
        return tblTaxClassificationCollection;
    }

    public void setTblTaxClassificationCollection(Collection<TblTaxClassification> tblTaxClassificationCollection) {
        this.tblTaxClassificationCollection = tblTaxClassificationCollection;
    }

    public TblTaxClassification getParentScientificNameId() {
        return parentScientificNameId;
    }

    public void setParentScientificNameId(TblTaxClassification parentScientificNameId) {
        this.parentScientificNameId = parentScientificNameId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxClassificationId != null ? taxClassificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTaxClassification)) {
            return false;
        }
        TblTaxClassification other = (TblTaxClassification) object;
        if ((this.taxClassificationId == null && other.taxClassificationId != null) || (this.taxClassificationId != null && !this.taxClassificationId.equals(other.taxClassificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblTaxClassification[ taxClassificationId=" + taxClassificationId + " ]";
    }

}
