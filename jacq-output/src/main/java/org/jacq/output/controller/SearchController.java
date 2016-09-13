/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.output.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Controller for handling the search page
 *
 * @author wkoller
 */
@ManagedBean
@SessionScoped
public class SearchController {

    protected String scientificName;
    protected String organization;
    protected Boolean hasImage;

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(Boolean hasImage) {
        this.hasImage = hasImage;
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "results";
    }

}
