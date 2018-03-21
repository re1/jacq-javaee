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
package org.jacq.common.model.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;

/**
 * Model for transfering living plant details over REST interface
 *
 * @author wkoller
 */
public class LivingPlantResult extends BotanicalObjectDerivative {

    protected static final SimpleDateFormat acquisitionDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected boolean reviewed;
    protected String ipenType;
    protected String ipenNumber;
    protected boolean ipenLocked;
    protected CultivarResult cultivar;
    protected String gatheringNumber;
    protected String cultureNotes;
    protected long count;
    protected List<AlternativeAccessionNumberResult> alternativeAccessionNumbers;
    protected Date gatheringDate;
    protected String customGatheringDate;
    protected String gatheringLocation;

    protected Long altitudeMin;
    protected Long altitudeMax;
    protected Long exactness;
    protected Long latitudeDegrees;
    protected Long latitudeMinutes;
    protected Long latitudeSeconds;
    protected String latitudeHalf;
    protected Long longitudeDegrees;
    protected Long longitudeMinutes;
    protected Long longitudeSeconds;
    protected String longitudeHalf;
    protected String gatheringAnnotation;
    protected String habitat;
    protected boolean indexSeminum;
    protected IndexSeminumTypeResult indexSeminumType;
    protected float price;

    protected String family;
    protected String familyReference;

    protected Date recordingDate;
    protected Date incomingDate;
    protected Date cultivationDate;
    protected String generalAnnotation;
    protected PhenologyResult phenology;

    protected List<AcquistionEventSourceResult> acquistionEventSources;

    protected boolean redetermine;
    protected Date determinationDate;
    protected IdentStatusResult identStatus;
    protected PersonResult determinedBy;
    protected boolean phytoControl;
    protected boolean bgci;

    protected List<RelevancyTypeResult> relevancyTypes;
    protected List<SeparationResult> separations;
    protected List<CertificateResult> certificates;

    protected ScientificNameResult scientificNameResult;

    protected OrganisationResult organisation;

    protected List<SexResult> sexes;

    protected List<PersonResult> gatherers;

    protected ScientificNameResult labelSynonymScientificName;

    protected ImportPropertiesResult importPropertiesResult;

    protected List<ImageServerResource> imageServerResources;

    public LivingPlantResult() {
        this.indexSeminumType = new IndexSeminumTypeResult();
        this.phenology = new PhenologyResult();
        this.cultivar = new CultivarResult();
        this.identStatus = new IdentStatusResult();
        this.determinedBy = new PersonResult();
        this.scientificNameResult = new ScientificNameResult();
        this.organisation = new OrganisationResult();
        this.labelSynonymScientificName = new ScientificNameResult();

        this.alternativeAccessionNumbers = new ArrayList<>();
        this.relevancyTypes = new ArrayList<>();
        this.separations = new ArrayList<>();
        this.certificates = new ArrayList<>();
        this.acquistionEventSources = new ArrayList<>();
        this.sexes = new ArrayList<>();
        this.gatherers = new ArrayList<>();
    }

    public LivingPlantResult(TblLivingPlant tblLivingPlant) {
        // BotanicalObjectDerivative properties
        this.setType(BotanicalObjectDerivative.LIVING);
        this.setBotanicalObjectId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getId());
        this.setSeparated(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getSeparated());
        this.setScientificName(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getViewScientificName().getScientificName());
        this.setScientificNameId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getScientificNameId());
        this.setDerivativeId(tblLivingPlant.getTblDerivative().getDerivativeId());
        this.setOrganisationDescription(tblLivingPlant.getTblDerivative().getOrganisationId().getDescription());
        this.setOrganisationId(tblLivingPlant.getTblDerivative().getOrganisationId().getId());
        this.setDerivativeCount(tblLivingPlant.getTblDerivative().getCount());
        this.setAccessionNumber(String.format("%07d", tblLivingPlant.getAccessionNumber()));
        this.setLabelAnnotation(tblLivingPlant.getLabelAnnotation());
        this.setPlaceNumber(tblLivingPlant.getPlaceNumber());
        this.setCultivarName((tblLivingPlant.getCultivarId() != null) ? tblLivingPlant.getCultivarId().getCultivar() : null);

        // Botanical object properties
        this.habitat = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getHabitat();
        this.recordingDate = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getRecordingDate();
        this.redetermine = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getRedetermine();
        this.determinationDate = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getDeterminationDate();
        this.identStatus = new IdentStatusResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getIdentStatusId());
        this.determinedBy = new PersonResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getDeterminedById());
        this.generalAnnotation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAnnotation();
        this.phenology = new PhenologyResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getPhenologyId());
        this.scientificNameResult = new ScientificNameResult(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getViewScientificName().getScientificName(), tblLivingPlant.getTblDerivative().getBotanicalObjectId().getScientificNameId());
        this.acquistionEventSources = AcquistionEventSourceResult.fromList(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getTblAcquisitionEventSourceList());
        this.sexes = SexResult.fromList(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getTblSexList());
        this.gatherers = PersonResult.fromList(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getTblPersonList());

        // Derivative properties
        this.count = tblLivingPlant.getTblDerivative().getCount();
        this.price = tblLivingPlant.getTblDerivative().getPrice();
        this.separations = SeparationResult.fromList(tblLivingPlant.getTblDerivative().getTblSeparationList());
        this.organisation = new OrganisationResult(tblLivingPlant.getTblDerivative().getOrganisationId());

        // Livingplant properties
        this.reviewed = tblLivingPlant.getReviewed();
        this.ipenType = tblLivingPlant.getIpenType();
        this.ipenNumber = tblLivingPlant.getIpenNumber();
        this.ipenLocked = tblLivingPlant.getIpenLocked();
        this.cultivar = new CultivarResult(tblLivingPlant.getCultivarId());
        this.cultureNotes = tblLivingPlant.getCultureNotes();
        this.alternativeAccessionNumbers = AlternativeAccessionNumberResult.fromList(tblLivingPlant.getTblAlternativeAccessionNumberList());
        this.indexSeminum = tblLivingPlant.getIndexSeminum();
        this.indexSeminumType = new IndexSeminumTypeResult(tblLivingPlant.getIndexSeminumTypeId());
        this.phytoControl = tblLivingPlant.getPhytoControl();
        this.bgci = tblLivingPlant.getBgci();
        this.certificates = CertificateResult.fromList(tblLivingPlant.getTblCertificateList());
        this.cultivationDate = tblLivingPlant.getCultivationDate();
        this.relevancyTypes = RelevancyTypeResult.fromList(tblLivingPlant.getTblRelevancyTypeList());
        this.labelSynonymScientificName = new ScientificNameResult();
        if (tblLivingPlant.getViewLabelSynonymScientificName() != null) {
            this.labelSynonymScientificName.setScientificName(tblLivingPlant.getViewLabelSynonymScientificName().getScientificName());
            this.labelSynonymScientificName.setScientificNameId(tblLivingPlant.getViewLabelSynonymScientificName().getScientificNameId());
        }

        try {
            this.incomingDate = acquisitionDateFormat.parse(
                    String.format(
                            "%04d-%02d-%02d",
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getYear()),
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getMonth()),
                            Integer.valueOf(tblLivingPlant.getIncomingDateId().getDay())
                    )
            );
        } catch (Exception ex) {
        }

        if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId() != null) {
            this.gatheringNumber = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getNumber();
            this.gatheringAnnotation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAnnotation();
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId() != null) {
                if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear() != null) {
                    try {
                        this.gatheringDate = acquisitionDateFormat.parse(
                                String.format(
                                        "%04d-%02d-%02d",
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getYear()),
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getMonth()),
                                        Integer.valueOf(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getDay())
                                )
                        );
                    } catch (Exception ex) {
                    }
                }
                this.customGatheringDate = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getAcquisitionDateId().getCustom();
            }
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationId() != null) {
                this.gatheringLocation = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationId().getLocation();
            }
            if (tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId() != null) {
                this.altitudeMin = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMin();
                this.altitudeMax = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getAltitudeMax();
                this.exactness = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getExactness();
                this.latitudeDegrees = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeDegrees();
                this.latitudeMinutes = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeMinutes();
                this.latitudeSeconds = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeSeconds();
                this.latitudeHalf = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLatitudeHalf();
                this.longitudeDegrees = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeDegrees();
                this.longitudeMinutes = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeMinutes();
                this.longitudeSeconds = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeSeconds();
                this.longitudeHalf = tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getLocationCoordinatesId().getLongitudeHalf();
            }
        }

        // Import Properties
        if (tblLivingPlant.getTblDerivative().getTblImportProperties() != null) {
            this.importPropertiesResult = new ImportPropertiesResult(tblLivingPlant.getTblDerivative().getTblImportProperties());
        }
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getIpenType() {
        return ipenType;
    }

    public void setIpenType(String ipenType) {
        this.ipenType = ipenType;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public boolean isIpenLocked() {
        return ipenLocked;
    }

    public void setIpenLocked(boolean ipenLocked) {
        this.ipenLocked = ipenLocked;
    }

    public CultivarResult getCultivar() {
        return cultivar;
    }

    public void setCultivar(CultivarResult cultivar) {
        this.cultivar = cultivar;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public String getCultureNotes() {
        return cultureNotes;
    }

    public void setCultureNotes(String cultureNotes) {
        this.cultureNotes = cultureNotes;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<AlternativeAccessionNumberResult> getAlternativeAccessionNumbers() {
        return alternativeAccessionNumbers;
    }

    public void setAlternativeAccessionNumbers(List<AlternativeAccessionNumberResult> alternativeAccessionNumbers) {
        this.alternativeAccessionNumbers = alternativeAccessionNumbers;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public String getCustomGatheringDate() {
        return customGatheringDate;
    }

    public void setCustomGatheringDate(String customGatheringDate) {
        this.customGatheringDate = customGatheringDate;
    }

    public String getGatheringLocation() {
        return gatheringLocation;
    }

    public void setGatheringLocation(String gatheringLocation) {
        this.gatheringLocation = gatheringLocation;
    }

    public Long getAltitudeMin() {
        return altitudeMin;
    }

    public void setAltitudeMin(Long altitudeMin) {
        this.altitudeMin = altitudeMin;
    }

    public Long getAltitudeMax() {
        return altitudeMax;
    }

    public void setAltitudeMax(Long altitudeMax) {
        this.altitudeMax = altitudeMax;
    }

    public Long getExactness() {
        return exactness;
    }

    public void setExactness(Long exactness) {
        this.exactness = exactness;
    }

    public Long getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(Long latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public Long getLatitudeMinutes() {
        return latitudeMinutes;
    }

    public void setLatitudeMinutes(Long latitudeMinutes) {
        this.latitudeMinutes = latitudeMinutes;
    }

    public Long getLatitudeSeconds() {
        return latitudeSeconds;
    }

    public void setLatitudeSeconds(Long latitudeSeconds) {
        this.latitudeSeconds = latitudeSeconds;
    }

    public String getLatitudeHalf() {
        return latitudeHalf;
    }

    public void setLatitudeHalf(String latitudeHalf) {
        this.latitudeHalf = latitudeHalf;
    }

    public Long getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(Long longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public Long getLongitudeMinutes() {
        return longitudeMinutes;
    }

    public void setLongitudeMinutes(Long longitudeMinutes) {
        this.longitudeMinutes = longitudeMinutes;
    }

    public Long getLongitudeSeconds() {
        return longitudeSeconds;
    }

    public void setLongitudeSeconds(Long longitudeSeconds) {
        this.longitudeSeconds = longitudeSeconds;
    }

    public String getLongitudeHalf() {
        return longitudeHalf;
    }

    public void setLongitudeHalf(String longitudeHalf) {
        this.longitudeHalf = longitudeHalf;
    }

    public String getGatheringAnnotation() {
        return gatheringAnnotation;
    }

    public void setGatheringAnnotation(String gatheringAnnotation) {
        this.gatheringAnnotation = gatheringAnnotation;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public IndexSeminumTypeResult getIndexSeminumType() {
        return indexSeminumType;
    }

    public void setIndexSeminumType(IndexSeminumTypeResult indexSeminumType) {
        this.indexSeminumType = indexSeminumType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public Date getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(Date incomingDate) {
        this.incomingDate = incomingDate;
    }

    public Date getCultivationDate() {
        return cultivationDate;
    }

    public void setCultivationDate(Date cultivationDate) {
        this.cultivationDate = cultivationDate;
    }

    public String getGeneralAnnotation() {
        return generalAnnotation;
    }

    public void setGeneralAnnotation(String generalAnnotation) {
        this.generalAnnotation = generalAnnotation;
    }

    public PhenologyResult getPhenology() {
        return phenology;
    }

    public void setPhenology(PhenologyResult phenology) {
        this.phenology = phenology;
    }

    public Date getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(Date determinationDate) {
        this.determinationDate = determinationDate;
    }

    public IdentStatusResult getIdentStatus() {
        return identStatus;
    }

    public void setIdentStatus(IdentStatusResult identStatus) {
        this.identStatus = identStatus;
    }

    public PersonResult getDeterminedBy() {
        return determinedBy;
    }

    public void setDeterminedBy(PersonResult determinedBy) {
        this.determinedBy = determinedBy;
    }

    public List<RelevancyTypeResult> getRelevancyTypes() {
        return relevancyTypes;
    }

    public void setRelevancyTypes(List<RelevancyTypeResult> relevancyTypes) {
        this.relevancyTypes = relevancyTypes;
    }

    public List<SeparationResult> getSeparations() {
        return separations;
    }

    public void setSeparations(List<SeparationResult> separations) {
        this.separations = separations;
    }

    public List<CertificateResult> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateResult> certificates) {
        this.certificates = certificates;
    }

    public ScientificNameResult getScientificNameResult() {
        return scientificNameResult;
    }

    public void setScientificNameResult(ScientificNameResult scientificNameResult) {
        this.scientificNameResult = scientificNameResult;
    }

    public boolean isIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public boolean isRedetermine() {
        return redetermine;
    }

    public void setRedetermine(boolean redetermine) {
        this.redetermine = redetermine;
    }

    public boolean isPhytoControl() {
        return phytoControl;
    }

    public void setPhytoControl(boolean phytoControl) {
        this.phytoControl = phytoControl;
    }

    public boolean isBgci() {
        return bgci;
    }

    public void setBgci(boolean bgci) {
        this.bgci = bgci;
    }

    public List<AcquistionEventSourceResult> getAcquistionEventSources() {
        return acquistionEventSources;
    }

    public void setAcquistionEventSources(List<AcquistionEventSourceResult> acquistionEventSources) {
        this.acquistionEventSources = acquistionEventSources;
    }

    public OrganisationResult getOrganisation() {
        return organisation;
    }

    public void setOrganisation(OrganisationResult organisation) {
        this.organisation = organisation;
    }

    public List<SexResult> getSexes() {
        return sexes;
    }

    public void setSexes(List<SexResult> sexes) {
        this.sexes = sexes;
    }

    public List<PersonResult> getGatherers() {
        return gatherers;
    }

    public void setGatherers(List<PersonResult> gatherers) {
        this.gatherers = gatherers;
    }

    public ScientificNameResult getLabelSynonymScientificName() {
        return labelSynonymScientificName;
    }

    public void setLabelSynonymScientificName(ScientificNameResult labelSynonymScientificName) {
        this.labelSynonymScientificName = labelSynonymScientificName;
    }

    public ImportPropertiesResult getImportPropertiesResult() {
        return importPropertiesResult;
    }

    public void setImportPropertiesResult(ImportPropertiesResult importPropertiesResult) {
        this.importPropertiesResult = importPropertiesResult;
    }

    public List<ImageServerResource> getImageServerResources() {
        return imageServerResources;
    }

    public void setImageServerResources(List<ImageServerResource> imageServerResources) {
        this.imageServerResources = imageServerResources;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFamilyReference() {
        return familyReference;
    }

    public void setFamilyReference(String familyReference) {
        this.familyReference = familyReference;
    }

}
