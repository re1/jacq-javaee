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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
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
    protected SessionManager sessionController;

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;
    protected LazyDerivativeDownloadDataModel downloadDataModel;
    protected ScientificNameService scientificNameService;
    protected OrganisationService organisationService;
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

        this.downloadRender = false;
        if (sessionController.getUser() != null && this.dataModel.getDerivativeSearchModel().getCallFlag() == 0) {
            this.dataModel.getDerivativeSearchModel().setOrganisationId(sessionController.getUser().getOrganisationId() != null ? sessionController.getUser().getOrganisationId() : null);
            this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(this.organisationService.load(this.dataModel.getDerivativeSearchModel().getOrganisationId()));
            this.dataModel.getDerivativeSearchModel().setHierarchic(true);
        }
        this.showorganisationHierarchicSelectController();
    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return sessionController.getDerivativeSearchModel();
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
