/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.SeedOrderResult;
import org.jacq.common.rest.SeedExchangeService;
import org.jacq.common.rest.report.LabelService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.view.DerivativeSearchModel;
import org.jacq.input.view.LazyDerivativeDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author wkoller
 */
@ViewScoped
@ManagedBean
public class SeedExchangeEditController implements Serializable {

    protected Long seedOrderId;

    protected SeedOrderResult seedOrderResult;

    /**
     * Service for handling seed exchange related actions
     */
    protected SeedExchangeService seedExchangeService;

    /**
     * Service for printing seed order
     */
    protected LabelService labelService;

    @ManagedProperty(value = "#{livingPlantController}")
    protected LivingPlantController livingPlantController;

    @PostConstruct
    public void init() {
        this.seedExchangeService = ServicesUtil.getSeedExchangeService();
        this.labelService = ServicesUtil.getLabelService();

        this.seedOrderResult = new SeedOrderResult();

        // only search for entries with index seminum
        this.livingPlantController.getDerivativeSearchModel().setIndexSeminum(1);
    }

    public SeedOrderResult getSeedOrderResult() {
        return seedOrderResult;
    }

    public void setSeedOrderResult(SeedOrderResult seedOrderResult) {
        this.seedOrderResult = seedOrderResult;
    }

    public void addDerivative(BotanicalObjectDerivative botanicalObjectDerivative) {
        this.seedOrderResult.getDerivativeList().add(botanicalObjectDerivative);
    }

    public void removeDerivative(BotanicalObjectDerivative botanicalObjectDerivative) {
        this.seedOrderResult.getDerivativeList().remove(botanicalObjectDerivative);
    }

    public void save() {
        this.seedOrderResult = this.seedExchangeService.save(seedOrderResult);
    }

    /**
     * Called to download the seed order
     *
     * @return
     */
    public StreamedContent getSeedOrder() {
        return new DefaultStreamedContent(this.labelService.getSeedOrder(this.seedOrderResult.getSeedOrderId()).readEntity(InputStream.class), LabelService.APPLICATION_PDF, "seed_exchange_order.pdf");
    }

    public Long getSeedOrderId() {
        return seedOrderId;
    }

    public void setSeedOrderId(Long seedOrderId) {
        this.seedOrderId = seedOrderId;

        if (this.seedOrderId != null) {
            this.seedOrderResult = this.seedExchangeService.find(this.seedOrderId);
        }
    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return livingPlantController.getDerivativeSearchModel();
    }

    public LazyDerivativeDataModel getDataModel() {
        return livingPlantController.getDataModel();
    }

    public List<OrganisationResult> completeOrganisation(String query) {
        return livingPlantController.completeOrganisation(query);
    }

    public void organisationHierachicSelectEvent(SelectEvent event) {
        livingPlantController.organisationHierachicSelectEvent(event);
    }

    public LivingPlantController getLivingPlantController() {
        return livingPlantController;
    }

    public void setLivingPlantController(LivingPlantController livingPlantController) {
        this.livingPlantController = livingPlantController;
    }

}
