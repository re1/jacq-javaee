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
package org.jacq.input.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.rest.AcquisitionSourceResult;
import org.jacq.common.model.rest.AcquistionEventSourceResult;
import org.jacq.common.model.rest.AlternativeAccessionNumberResult;
import org.jacq.common.model.rest.CertificateResult;
import org.jacq.common.model.rest.CertificateTypeResult;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.HabitusTypeResult;
import org.jacq.common.model.rest.IdentStatusResult;
import org.jacq.common.model.rest.IndexSeminumTypeResult;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.PersonResult;
import org.jacq.common.model.rest.PhenologyResult;
import org.jacq.common.model.rest.RelevancyTypeResult;
import org.jacq.common.model.rest.ScientificNameInformationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.model.rest.SeparationResult;
import org.jacq.common.model.rest.SeparationTypeResult;
import org.jacq.common.model.rest.SexResult;
import org.jacq.common.model.rest.SpecimenResult;
import org.jacq.common.model.rest.VegetativeResult;
import org.jacq.common.rest.AcquisitionService;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.GatheringService;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.PersonService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.common.rest.report.LabelService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.listener.OrganisationSelectListener;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Controller for handling creating / editing of a living plant entry
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantEditController implements OrganisationSelectListener {

    @Inject
    protected SessionController sessionController;

    /**
     * Reference to derivative id which is currently edited
     */
    protected Long derivativeId;

    /**
     * Reference to derivative service which is called during editing
     */
    protected DerivativeService derivativeService;

    /**
     * Reference to scientific name service which is used for cultivar and scientific name editing
     */
    protected ScientificNameService scientificNameService;

    /**
     * Reference to organisation service
     */
    protected OrganisationService organisationService;

    /**
     * Reference to person service
     */
    protected PersonService personService;

    /**
     * Reference to acquisition service
     */
    protected AcquisitionService acquisitionService;

    /**
     * Reference to gathering service
     */
    protected GatheringService gatheringService;

    /**
     * Index seminum service which is used for displaying the available types
     */
    protected IndexSeminumService indexSeminumService;

    /**
     * Reference to service for label printing
     */
    protected LabelService labelService;

    protected LivingPlantResult livingPlantResult;

    protected List<CultivarResult> cultivarResults;

    protected List<IndexSeminumTypeResult> indexSeminumTypes;

    protected ScientificNameInformationResult scientificNameInformationResult;

    protected List<VegetativeResult> vegetativeList;
    protected List<SpecimenResult> specimenList;

    protected List<HabitusTypeResult> habitusTypes;
    protected List<PhenologyResult> phenologies;
    protected List<IdentStatusResult> identStatus;
    protected List<RelevancyTypeResult> relevancyTypes;
    protected List<SeparationTypeResult> separationTypes;
    protected List<CertificateTypeResult> certificateTypes;
    protected List<SelectItem> sexes;

    protected List<String> selectedSexes;

    @PostConstruct
    public void init() {
        this.derivativeService = ServicesUtil.getDerivativeService();
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.indexSeminumService = ServicesUtil.getIndexSeminumService();
        this.organisationService = ServicesUtil.getOrganisationService();
        this.personService = ServicesUtil.getPersonService();
        this.acquisitionService = ServicesUtil.getAcquisitionService();
        this.gatheringService = ServicesUtil.getGatheringService();
        this.labelService = ServicesUtil.getLabelService();

        this.livingPlantResult = new LivingPlantResult();

        // setup default values
        this.livingPlantResult.setIpenType("default");

        // load all lookup tables
        this.indexSeminumTypes = this.indexSeminumService.typeFindAll();
        this.phenologies = this.derivativeService.findAllPhenology();
        this.identStatus = this.derivativeService.findAllIdentStatus();
        this.relevancyTypes = this.derivativeService.findAllRelevancyType();
        this.separationTypes = this.derivativeService.findAllSeparationType();
        this.certificateTypes = this.derivativeService.findAllCertificateType();

        // load list of sexes and convert to select item
        List<SexResult> sexList = this.derivativeService.findAllSex();
        this.sexes = new ArrayList<>();
        for (SexResult sex : sexList) {
            this.sexes.add(new SelectItem(sex.getSexId(), sex.getSex()));
        }
        this.selectedSexes = new ArrayList<>();
    }

    /**
     * Called when the user clicks on the button for reviewing the scientific name information, only then this info is
     * loaded
     *
     * @return
     */
    public void showScientificNameInformation() {
        if (this.habitusTypes == null) {
            this.habitusTypes = this.scientificNameService.findAllHabitusType();
        }

        // load scientific name information
        this.scientificNameInformationResult = this.scientificNameService.scientificNameInformationLoad(this.livingPlantResult.getScientificNameId());
    }

    public void addCultivar() {
        this.scientificNameInformationResult.getCultivarList().add(new CultivarResult());
    }

    public void removeCultivar(CultivarResult cultivarResult) {
        this.scientificNameInformationResult.getCultivarList().remove(cultivarResult);
    }

    public void addAlternativeAccessionNumber() {
        this.livingPlantResult.getAlternativeAccessionNumbers().add(new AlternativeAccessionNumberResult());
    }

    public void removeAlternativeAccessionNumber(AlternativeAccessionNumberResult alternativeAccessionNumberResult) {
        this.livingPlantResult.getAlternativeAccessionNumbers().remove(alternativeAccessionNumberResult);
    }

    public void addSpecimen() {
        this.getSpecimenList().add(new SpecimenResult());
    }

    public void removeSpecimen(SpecimenResult specimenResult) {
        this.getSpecimenList().remove(specimenResult);
    }

    public void addAcquisitionEventSource() {
        this.livingPlantResult.getAcquistionEventSources().add(new AcquistionEventSourceResult());
    }

    public void removeAcquisitionEventSource(AcquistionEventSourceResult acquistionEventSourceResult) {
        this.livingPlantResult.getAcquistionEventSources().remove(acquistionEventSourceResult);
    }

    public void addSeparation() {
        this.livingPlantResult.getSeparations().add(new SeparationResult());
    }

    public void removeSeparation(SeparationResult separationResult) {
        this.livingPlantResult.getSeparations().remove(separationResult);
    }

    public void addCertificate() {
        this.livingPlantResult.getCertificates().add(new CertificateResult());
    }

    public void removeCertificate(CertificateResult certificateResult) {
        this.livingPlantResult.getCertificates().remove(certificateResult);
    }

    public void addGatherer() {
        this.livingPlantResult.getGatherers().add(new PersonResult());
    }

    public void removeGatherer(PersonResult gatherer) {
        this.livingPlantResult.getGatherers().remove(gatherer);
    }

    public Long getDerivativeId() {
        return derivativeId;
    }

    /**
     * Called by the JSF container, when a derivative id is passed the according entry will be loaded
     *
     * @param derivativeId
     */
    public void setDerivativeId(Long derivativeId) {
        this.derivativeId = derivativeId;

        if (this.derivativeId != null) {
            // load derivative entry, make sure we received a correct one and cast it to living plant entry
            Response botanicalObjectDerivative = this.derivativeService.load(derivativeId, LivingPlantResult.LIVING);
            if (botanicalObjectDerivative.getStatus() == 200) {
                this.livingPlantResult = botanicalObjectDerivative.readEntity(LivingPlantResult.class);
            }
            else {
                sessionController.setGrowlMessage("error", "not_allowed");
            }
            botanicalObjectDerivative.close();

            this.syncInfo();
        }
    }

    /**
     * Called when user clicks on save
     */
    public void save() {
        // convert selected sex entries to SexResult(s)
        this.livingPlantResult.getSexes().clear();
        for (String sexId : this.selectedSexes) {
            this.livingPlantResult.getSexes().add(new SexResult(Long.parseLong(sexId)));
        }
        this.livingPlantResult.setSpecimensList(this.getSpecimenList());

        // save the living plant entry
        this.livingPlantResult = this.derivativeService.livingPlantSave(this.livingPlantResult);

        this.syncInfo();
        this.saveMessage();
        this.vegetativeList = this.derivativeService.vegetativeFind(this.livingPlantResult.getDerivativeId());
        this.specimenList = this.derivativeService.specimenFind(this.livingPlantResult.getBotanicalObjectId());
    }

    /**
     * called when user saves the scientific name information
     */
    public void saveScientificNameInformation() {
        // make sure the scientific name id is set
        this.scientificNameInformationResult.setScientificNameId(this.livingPlantResult.getScientificNameId());

        // save the scientific name information
        this.scientificNameInformationResult = this.scientificNameService.scientificNameInformationSave(this.scientificNameInformationResult);

        // re-sync all info
        this.syncInfo();
        this.saveMessage();
    }

    /**
     * Helper function for synchronizing the model properties with the UI info
     */
    protected void syncInfo() {
        // load matching list of possible cultivar entries
        this.cultivarResults = this.scientificNameService.cultivarFind(this.livingPlantResult.getScientificNameId());

        // convert selected sex entries
        for (SexResult sex : this.livingPlantResult.getSexes()) {
            this.selectedSexes.add(sex.getSexId().toString());
        }
    }

    /**
     * Called when user clicks on copy and new
     */
    public void copyAndNew() {
        this.livingPlantResult.setDerivativeId(null);
        this.livingPlantResult.setIpenNumber(null);
    }

    /**
     * Add the save message to growl
     */
    protected void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

    public List<ScientificNameResult> completeScientificName(String query) {
        return this.scientificNameService.find(query, Boolean.TRUE);
    }

    public List<OrganisationResult> completeOrganisation(String query) {
        return this.organisationService.search(null, query, null, null, null, null, null, 0, 10);
    }

    public List<PersonResult> completePerson(String query) {
        return this.personService.search(query, 0, 10);
    }

    public List<AcquisitionSourceResult> completeAcquisitionSource(String query) {
        return this.acquisitionService.sourceSearch(query, 0, 10);
    }

    public List<LocationResult> completeLocation(String query) {
        return this.gatheringService.locationFind(query, 0, 10);
    }

    /**
     * Called when user changes the tab, used to dynamically load content
     */
    public void onTabChange(TabChangeEvent event) {
        if (event.getTab() != null && event.getTab().getId().equals("tabDerivative") && this.vegetativeList == null) {
            this.refreshDerivatives();
        }
    }

    /**
     * Called when the user selects an item in the location auto-completer
     *
     * @param event
     */
    public void onLocationItemSelect(SelectEvent event) {
        if (event.getObject() != null) {
            LocationResult locationResult = (LocationResult) event.getObject();
            if (locationResult.getCountryCode() != null) {
                this.setIpenNumberCountry(locationResult.getCountryCode());;
            }
        }
    }

    /**
     * Called when the user selects an organisation entry
     *
     * @param event
     */
    public void onOrganisationSelect(SelectEvent event) {
        if (event.getObject() != null) {
            OrganisationResult organisationResult = (OrganisationResult) event.getObject();
            if (this.livingPlantResult.getDerivativeId() == null) {
                this.setIpenNumberGardenCode(this.organisationService.getIpenCode(organisationResult.getOrganisationId()));
            }
        }
    }

    /**
     * Called to download the work label
     *
     * @return
     */
    public StreamedContent getWorkLabel() {
        Response response = this.labelService.getWork(this.livingPlantResult.getType(), this.livingPlantResult.getDerivativeId());
        byte[] binaryStream = response.readEntity(byte[].class);

        return new DefaultStreamedContent(this.labelService.getWork(this.livingPlantResult.getType(), this.livingPlantResult.getDerivativeId()).readEntity(InputStream.class), LabelService.APPLICATION_PDF, "work_label.pdf");
    }

    /**
     * Refresh derivatives information
     */
    public void refreshDerivatives() {
        this.vegetativeList = this.derivativeService.vegetativeFind(this.livingPlantResult.getDerivativeId());
        this.specimenList = this.derivativeService.specimenFind(this.livingPlantResult.getBotanicalObjectId());
    }

    /*
    * Getter & Setter
     */
    public LivingPlantResult getLivingPlantResult() {
        return livingPlantResult;
    }

    /**
     * Virtual IPEN attributes for easier JSF binding
     */
    public String getIpenNumberCountry() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "XX" : this.livingPlantResult.getIpenNumber().substring(0, 2);
    }

    public void setIpenNumberCountry(String ipenNumberCountry) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", ipenNumberCountry, getIpenNumberRestriction(), getIpenNumberGardenCode()));
    }

    public String getIpenNumberRestriction() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "X" : this.livingPlantResult.getIpenNumber().substring(3, 4);
    }

    public void setIpenNumberRestriction(String ipenNumberRestriction) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", getIpenNumberCountry(), ipenNumberRestriction, getIpenNumberGardenCode()));
    }

    public String getIpenNumberGardenCode() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "XX" : this.livingPlantResult.getIpenNumber().substring(5);
    }

    public void setIpenNumberGardenCode(String ipenNumberGardenCode) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", getIpenNumberCountry(), getIpenNumberRestriction(), ipenNumberGardenCode));
    }

    public List<CultivarResult> getCultivarResults() {
        return cultivarResults;
    }

    public void setCultivarResults(List<CultivarResult> cultivarResults) {
        this.cultivarResults = cultivarResults;
    }

    public List<IndexSeminumTypeResult> getIndexSeminumTypes() {
        return indexSeminumTypes;
    }

    public ScientificNameInformationResult getScientificNameInformationResult() {
        return scientificNameInformationResult;
    }

    public void setScientificNameInformationResult(ScientificNameInformationResult scientificNameInformationResult) {
        this.scientificNameInformationResult = scientificNameInformationResult;
    }

    public List<HabitusTypeResult> getHabitusTypes() {
        return habitusTypes;
    }

    public List<PhenologyResult> getPhenologies() {
        return phenologies;
    }

    public List<IdentStatusResult> getIdentStatus() {
        return identStatus;
    }

    public List<RelevancyTypeResult> getRelevancyTypes() {
        return relevancyTypes;
    }

    public List<SeparationTypeResult> getSeparationTypes() {
        return separationTypes;
    }

    public List<CertificateTypeResult> getCertificateTypes() {
        return certificateTypes;
    }

    public List<SelectItem> getSexes() {
        return sexes;
    }

    public List<String> getSelectedSexes() {
        return selectedSexes;
    }

    public void setSelectedSexes(List<String> selectedSexes) {
        this.selectedSexes = selectedSexes;
    }

    public String getWorkLabelUrl() {
        if (this.livingPlantResult != null && this.livingPlantResult.getDerivativeId() != null) {
            return ServicesUtil.getWorkLabelURL(LivingPlantResult.LIVING, this.livingPlantResult.getDerivativeId());
        }

        return null;
    }

    public List<VegetativeResult> getVegetativeList() {
        return vegetativeList;
    }

    public void setVegetativeList(List<VegetativeResult> vegetativeList) {
        this.vegetativeList = vegetativeList;
    }

    public List<SpecimenResult> getSpecimenList() {
        return specimenList;
    }

    public void setSpecimenList(List<SpecimenResult> specimenList) {
        this.specimenList = specimenList;
    }

    /**
     * Listener to get the selceted Organisation from OrganisationHierarchicSelect
     *
     * @param organisationResult
     */
    @Override
    public void setSelectedOrganisation(OrganisationResult organisationResult) {
        this.livingPlantResult.setOrganisation(organisationResult);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:organisation");
    }

}
