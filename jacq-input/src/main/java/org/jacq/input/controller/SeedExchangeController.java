/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.rest.SeedOrderResult;
import org.jacq.common.rest.SeedExchangeService;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ViewScoped
@ManagedBean
public class SeedExchangeController implements Serializable {

    protected SeedExchangeService seedExchangeService;

    @PostConstruct
    public void init() {
        this.seedExchangeService = ServicesUtil.getSeedExchangeService();
    }

    public List<SeedOrderResult> getSeedOrderResults() {
        return this.seedExchangeService.findAll();
    }
}
