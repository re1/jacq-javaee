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
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.input.util.ServicesUtil;
import org.jacq.input.view.LazyDerivativeDataModel;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantController implements Serializable {

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;
    protected ScientificNameService scientificNameService;
    protected ScientificNameResult selectedScientificName;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService());
        this.scientificNameService = ServicesUtil.getScientificNameService();
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

    public List<ScientificNameResult> completeScientificName(String query) {
        return this.scientificNameService.find(query, Boolean.TRUE);
    }

    public ScientificNameResult getSelectedScientificName() {
        return selectedScientificName;
    }

    public void setSelectedScientificName(ScientificNameResult selectedScientificName) {
        this.selectedScientificName = selectedScientificName;

        if (selectedScientificName != null) {
            this.dataModel.setScientificNameId(selectedScientificName.getScientificNameId());
        }
        else {
            this.dataModel.setScientificNameId(null);
        }
    }

}
