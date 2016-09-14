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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.output.SessionManager;
import org.jacq.output.view.LazyBotanicalObjectDataModel;

/**
 * Controller for handling the search page
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class SearchController {

    protected LazyBotanicalObjectDataModel dataModel;

    @Inject
    protected SessionManager sessionManager;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyBotanicalObjectDataModel(null);
    }

    public String getScientificName() {
        return sessionManager.getScientificName();
    }

    public void setScientificName(String scientificName) {
        sessionManager.setScientificName(scientificName);
    }

    public String getOrganization() {
        return sessionManager.getOrganization();
    }

    public void setOrganization(String organization) {
        sessionManager.setOrganization(organization);
    }

    public Boolean getHasImage() {
        return sessionManager.getHasImage();
    }

    public void setHasImage(Boolean hasImage) {
        sessionManager.setHasImage(hasImage);
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

}
