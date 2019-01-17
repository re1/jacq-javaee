/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.SeedOrderResult;
import org.jacq.common.rest.SeedExchangeService;
import org.jacq.common.rest.report.LabelService;
import org.jacq.common.util.ServicesUtil;
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

    protected LabelService labelService;

    @PostConstruct
    public void init() {
        this.seedExchangeService = ServicesUtil.getSeedExchangeService();
        this.labelService = ServicesUtil.getLabelService();

        this.seedOrderResult = new SeedOrderResult();
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

}
