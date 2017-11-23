/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.input.util.ServicesUtil;
import org.jacq.input.view.LazyDerivativeDataModel;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantController {

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService());
    }

    public LazyDerivativeDataModel getDataModel() {
        return dataModel;
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

}
