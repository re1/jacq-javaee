/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import org.jacq.input.view.LazyOrganisationDataModel;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.input.util.ServicesUtil;

/**
 * Controller for handling the search page
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class OrganisationController implements Serializable {

    public static final String ALL = "all";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    protected Date selectedBirthdate;

    protected LazyOrganisationDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyOrganisationDataModel(ServicesUtil.getOrganisationService());
    }

    public LazyOrganisationDataModel getDataModel() {
        return dataModel;
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

    /**
     * Jumps to edit form of Organisation
     *
     * @return
     */
    public String edit() {
        return "edit";
    }

    public String getALL() {
        return ALL;
    }

    public String getTRUE() {
        return TRUE;
    }

    public String getFALSE() {
        return FALSE;
    }

    public Date getSelectedBirthdate() {
        return selectedBirthdate;
    }

    public void setSelectedBirthdate(Date selectedBirthdate) {
        this.selectedBirthdate = selectedBirthdate;
    }

}
