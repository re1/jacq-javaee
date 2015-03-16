package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tbl_tax_classification database table.
 * 
 */
@Entity
@Table(name = "tbl_tax_classification")
@NamedQueries({
		@NamedQuery(name = "TaxClassification.findAll", query = "SELECT t FROM TaxClassification t"),
		@NamedQuery(name = "TaxClassification.findAllTopLevel", query = "SELECT t FROM TaxClassification t WHERE t.parentTaxClassification IS NULL AND EXISTS( FROM TaxClassification AS children WHERE parentTaxClassification = t )")

})
public class TaxClassification implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer taxClassificationId;
	private Integer acceptedScientificNameId;
	private String annotations;
	private Byte locked;
	private String number;
	private Integer order;
	private Byte preferredTaxonomy;
	private Date refDate;
	private Integer scientificNameId;
	private String source;
	private Integer sourceCitationId;
	private Integer sourcePersonId;
	private Integer sourceServiceId;
	private Integer sourceSpecimenId;
	private Timestamp timestamp;
	private Integer userId;
	private TaxClassification parentTaxClassification;
	private List<TaxClassification> taxClassificationChildren;

	public TaxClassification() {
	}

	@Id
	@Column(name = "tax_classification_id", unique = true, nullable = false)
	public Integer getTaxClassificationId() {
		return this.taxClassificationId;
	}

	public void setTaxClassificationId(Integer taxClassificationId) {
		this.taxClassificationId = taxClassificationId;
	}

	@Column(name = "accepted_scientific_name_id")
	public Integer getAcceptedScientificNameId() {
		return this.acceptedScientificNameId;
	}

	public void setAcceptedScientificNameId(Integer acceptedScientificNameId) {
		this.acceptedScientificNameId = acceptedScientificNameId;
	}

	@Lob
	public String getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}

	@Column(nullable = false)
	public Byte getLocked() {
		return this.locked;
	}

	public void setLocked(Byte locked) {
		this.locked = locked;
	}

	@Column(length = 15)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "preferred_taxonomy", nullable = false)
	public Byte getPreferredTaxonomy() {
		return this.preferredTaxonomy;
	}

	public void setPreferredTaxonomy(Byte preferredTaxonomy) {
		this.preferredTaxonomy = preferredTaxonomy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ref_date")
	public Date getRefDate() {
		return this.refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	@Column(name = "scientific_name_id", unique = true, nullable = false)
	public Integer getScientificNameId() {
		return this.scientificNameId;
	}

	public void setScientificNameId(Integer scientificNameId) {
		this.scientificNameId = scientificNameId;
	}

	@Column(nullable = false, length = 20)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "source_citation_id")
	public Integer getSourceCitationId() {
		return this.sourceCitationId;
	}

	public void setSourceCitationId(Integer sourceCitationId) {
		this.sourceCitationId = sourceCitationId;
	}

	@Column(name = "source_person_id")
	public Integer getSourcePersonId() {
		return this.sourcePersonId;
	}

	public void setSourcePersonId(Integer sourcePersonId) {
		this.sourcePersonId = sourcePersonId;
	}

	@Column(name = "source_service_id")
	public Integer getSourceServiceId() {
		return this.sourceServiceId;
	}

	public void setSourceServiceId(Integer sourceServiceId) {
		this.sourceServiceId = sourceServiceId;
	}

	@Column(name = "source_specimen_id")
	public Integer getSourceSpecimenId() {
		return this.sourceSpecimenId;
	}

	public void setSourceSpecimenId(Integer sourceSpecimenId) {
		this.sourceSpecimenId = sourceSpecimenId;
	}

	@Column(nullable = false)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	//bi-directional many-to-one association to TblTaxClassification
	@ManyToOne
	@JoinColumn(name = "parent_scientific_name_id")
	public TaxClassification getParentTaxClassification() {
		return this.parentTaxClassification;
	}

	public void setParentTaxClassification(TaxClassification parentTaxClassification) {
		this.parentTaxClassification = parentTaxClassification;
	}

	//bi-directional many-to-one association to TblTaxClassification
	@OneToMany(mappedBy = "parentTaxClassification")
	public List<TaxClassification> getTaxClassificationChildren() {
		return this.taxClassificationChildren;
	}

	public void setTaxClassificationChildren(List<TaxClassification> tblTaxClassifications) {
		this.taxClassificationChildren = tblTaxClassifications;
	}
}