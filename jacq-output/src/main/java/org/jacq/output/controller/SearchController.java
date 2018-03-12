/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.output.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.common.rest.output.SearchService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.output.SessionManager;
import org.jacq.output.view.LazyBotanicalObjectDataModel;

/**
 * Controller for handling the search page
 *
 * @author wkoller
 */
@ManagedBean
@SessionScoped
public class SearchController implements Serializable {

    protected LazyBotanicalObjectDataModel dataModel;

    /**
     * Reference to scientific name service which is used for cultivar and scientific name editing
     */
    protected ScientificNameService scientificNameService;

    protected SearchService searchService;

    @Inject
    protected SessionManager sessionManager;

    @PostConstruct
    public void init() {
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.searchService = ServicesUtil.getSearchService();

        this.dataModel = new LazyBotanicalObjectDataModel(this.searchService);
    }

    public ScientificNameResult getScientificName() {
        return this.dataModel.getScientificName();
    }

    public void setScientificName(ScientificNameResult scientificName) {
        this.dataModel.setScientificName(scientificName);
    }

    public OrganisationResult getOrganization() {
        return this.dataModel.getOrganization();
    }

    public void setOrganization(OrganisationResult organization) {
        this.dataModel.setOrganization(organization);
    }

    public Boolean getHasImage() {
        return this.dataModel.getHasImage();
    }

    public void setHasImage(Boolean hasImage) {
        this.dataModel.setHasImage(hasImage);
    }

    public LazyBotanicalObjectDataModel getDataModel() {
        return dataModel;
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "results";
    }

    /**
     * Noop action listener for refreshing the row count after loading the data-table
     *
     * @return
     */
    public String updateRowCount() {
        return null;
    }

    /**
     * Provides JSF compliant auto-completer function callback for scientific names
     *
     * @param query
     * @return
     */
    public List<ScientificNameResult> completeScientificName(String query) {
        return this.scientificNameService.find(query, Boolean.TRUE);
    }

    /**
     * Provides JSF compliant auto-completer function callback for organisation entries
     *
     * @param query
     * @return
     */
    public List<OrganisationResult> completeOrganisation(String query) {
        return this.searchService.organisationSearch(null, query, null, null, null, null, null, 0, 10);
    }

}
