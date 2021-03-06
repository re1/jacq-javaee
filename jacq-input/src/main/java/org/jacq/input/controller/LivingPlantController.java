/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import org.jacq.input.SessionManager;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.GatheringService;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.listener.OrganisationSelectListener;
import org.jacq.input.view.DerivativeSearchModel;
import org.jacq.input.view.LazyDerivativeDataModel;
import org.jacq.input.view.LazyDerivativeDownloadDataModel;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantController implements Serializable, OrganisationSelectListener {

    @Inject
    protected SessionManager sessionManager;

    @Inject
    protected SessionController sessionController;

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;
    protected LazyDerivativeDownloadDataModel downloadDataModel;
    protected ScientificNameService scientificNameService;
    protected OrganisationService organisationService;
    protected DerivativeService derivativeService;
    /**
     * Reference to gathering service
     */
    protected GatheringService gatheringService;

    protected Boolean downloadRender;

    @ManagedProperty(value = "#{organisationHierarchicSelectController}")
    protected OrganisationHierarchicSelectController organisationHierarchicSelectController;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService(), this.getDerivativeSearchModel());
        this.downloadDataModel = new LazyDerivativeDownloadDataModel(ServicesUtil.getDerivativeService(), this.dataModel);
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.organisationService = ServicesUtil.getOrganisationService();
        this.gatheringService = ServicesUtil.getGatheringService();
        this.derivativeService = ServicesUtil.getDerivativeService();

        this.downloadRender = false;
        if (sessionManager.getUser() != null && this.dataModel.getDerivativeSearchModel().getCallFlag() == 0) {
            this.dataModel.getDerivativeSearchModel().setOrganisationId(sessionManager.getUser().getOrganisationId() != null ? sessionManager.getUser().getOrganisationId() : null);
            this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(this.organisationService.load(this.dataModel.getDerivativeSearchModel().getOrganisationId()));
            this.dataModel.getDerivativeSearchModel().setHierarchic(true);
        }
        this.showorganisationHierarchicSelectController();
    }

    /**
     * Removes Markings of IndexSeminum
     */
    public void removeIndexSeminumMarking() {
        if (this.derivativeService.removeIndexSeminumMarking(this.dataModel.getDerivativeSearchModel().getType(), this.dataModel.getDerivativeSearchModel().getId(), this.dataModel.getDerivativeSearchModel().getPlaceNumber(), this.dataModel.getDerivativeSearchModel().getAccessionNumber(), this.dataModel.getDerivativeSearchModel().getSeparatedFilter(), this.dataModel.getDerivativeSearchModel().getScientificNameId(), this.dataModel.getDerivativeSearchModel().getOrganisationId(), this.dataModel.getDerivativeSearchModel().getHierarchic(), this.dataModel.getDerivativeSearchModel().getIndexSeminumFilter(), this.dataModel.getDerivativeSearchModel().getGatheringLocationName(), (this.dataModel.getDerivativeSearchModel().getExhibition() != null) ? this.dataModel.getDerivativeSearchModel().getExhibition() : null, (this.dataModel.getDerivativeSearchModel().getWorking() != null) ? this.dataModel.getDerivativeSearchModel().getWorking() : null, (this.dataModel.getDerivativeSearchModel().getSelectedCultivar() != null) ? this.dataModel.getDerivativeSearchModel().getSelectedCultivar().getCultivar() : null, this.dataModel.getDerivativeSearchModel().getClassification(), null, null) == null) {
            sessionController.setGrowlMessage(FacesMessage.SEVERITY_ERROR, "error", "not_allowed");
        }

    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return sessionManager.getDerivativeSearchModel();
    }

    public LazyDerivativeDataModel getDataModel() {
        return dataModel;
    }

    public LazyDerivativeDownloadDataModel getDownloadDataModel() {
        return downloadDataModel;
    }

    public String getTypeAll() {
        return TYPE_ALL;
    }

    public String getTypeLiving() {
        return TYPE_LIVING;
    }

    public String getTypeVegetative() {
        return TYPE_VEGETATIVE;
    }

    public List<ScientificNameResult> completeScientificName(String query) {
        return this.scientificNameService.find(query, Boolean.TRUE);
    }

    public List<LocationResult> completeLocation(String query) {
        return this.gatheringService.locationFind(query, 0, 10);
    }

    public List<CultivarResult> completeCultivar(String query) {
        return this.derivativeService.cultivarFind(query, 0, 10);
    }

    public Boolean getDownloadRender() {
        return downloadRender;
    }

    public void setDownloadRender(Boolean downloadRender) {
        this.downloadRender = downloadRender;
    }

    public List<OrganisationResult> completeOrganisation(String query) {
        return this.organisationService.search(null, query, null, null, null, null, null, 0, 10);
    }

    public void setRenderedTrue() {
        this.setDownloadRender(true);
    }

    public void organisationHierachicSelectEvent(SelectEvent event) {
        this.showorganisationHierarchicSelectController();
    }

    public void showorganisationHierarchicSelectController() {
        this.organisationHierarchicSelectController.show(this.getDerivativeSearchModel().getSelectedOrganisation(), this);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:hierachicSearch");
    }

    @Override
    public void setSelectedOrganisation(OrganisationResult organisationResult) {
        this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(organisationResult);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:organisation");
    }

    public OrganisationHierarchicSelectController getOrganisationHierarchicSelectController() {
        return organisationHierarchicSelectController;
    }

    public void setOrganisationHierarchicSelectController(OrganisationHierarchicSelectController organisationHierarchicSelectController) {
        this.organisationHierarchicSelectController = organisationHierarchicSelectController;
    }

}
