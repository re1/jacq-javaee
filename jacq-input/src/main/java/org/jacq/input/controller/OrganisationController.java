/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import org.jacq.input.view.LazyOrganisationDataModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jacq.input.util.ServicesUtil;

/**
 * Controller for handling the search page
 *
 * @author fhafner
 */
@ManagedBean
@SessionScoped
public class OrganisationController implements Serializable {

    protected LazyOrganisationDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyOrganisationDataModel(ServicesUtil.getOrganisationService());
    }

    public LazyOrganisationDataModel getDataModel() {
        return dataModel;
    }

    public Long getOrganisationId() {
        return this.dataModel.getOrganisationId();
    }

    public void setOrganisationId(Long organisationId) {
        this.dataModel.setOrganisationId(organisationId);
    }

    public String getDescription() {
        return this.dataModel.getDescription();
    }

    public void setDescription(String description) {
        this.dataModel.setDescription(description);
    }

    public String getDepartment() {
        return this.dataModel.getDepartment();
    }

    public void setDepartment(String department) {
        this.dataModel.setDepartment(department);
    }

    public boolean getGreenhouse() {
        return this.dataModel.getGreenhouse();
    }

    public void setGreenhouse(boolean greenhouse) {
        this.dataModel.setGreenhouse(greenhouse);
    }

    public String getIpenCode() {
        return this.dataModel.getIpenCode();
    }

    public void setIpenCode(String ipenCode) {
        this.dataModel.setIpenCode(ipenCode);
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "locationmanage";
    }

    /**
     * Noop action listener for refreshing the row count after loading the
     * data-table
     *
     * @return
     */
    public String updateRowCount() {
        return null;
    }

}
