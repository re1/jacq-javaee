/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jacq.input.util.ServicesUtil;
import org.jacq.input.view.LazyDerivativeDataModel;

/**
 *
 * @author wkoller
 */
@ManagedBean
@SessionScoped
public class LivingPlantController {

    protected LazyDerivativeDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService());
    }

    public LazyDerivativeDataModel getDataModel() {
        return dataModel;
    }
}
