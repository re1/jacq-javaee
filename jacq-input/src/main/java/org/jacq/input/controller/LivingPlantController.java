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
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.input.util.ServicesUtil;
import org.jacq.input.view.DerivativeSearchModel;
import org.jacq.input.view.LazyDerivativeDataModel;
import org.jacq.input.view.LazyDerivativeDownloadDataModel;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantController implements Serializable {

    @Inject
    protected SessionManager sessionController;

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;
    protected LazyDerivativeDownloadDataModel downloadDataModel;
    protected ScientificNameService scientificNameService;
    protected OrganisationService organisationService;

    protected Boolean downloadRender;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService(), this.getDerivativeSearchModel());
        this.downloadDataModel = new LazyDerivativeDownloadDataModel(ServicesUtil.getDerivativeService(), this.dataModel);
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.organisationService = ServicesUtil.getOrganisationService();
        this.downloadRender = false;
        if (sessionController.getUser() != null && this.dataModel.getDerivativeSearchModel().getCallFlag() == 0) {
            this.dataModel.getDerivativeSearchModel().setOrganisationId(sessionController.getUser().getOrganisationId() != null ? sessionController.getUser().getOrganisationId() : null);
            this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(this.organisationService.load(this.dataModel.getDerivativeSearchModel().getOrganisationId()));
            this.dataModel.getDerivativeSearchModel().setHierarchic(true);
        }
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
}
