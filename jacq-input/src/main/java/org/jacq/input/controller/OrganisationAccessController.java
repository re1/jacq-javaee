/*
 * Copyright 2018 fhafner.
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
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.rest.AccessOrganisationResult;
import org.jacq.common.rest.AuthorizationService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.view.LazyOrganisationAccessDataModel;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class OrganisationAccessController {

    protected LazyOrganisationAccessDataModel dataModel;

    protected Long organisationId;

    protected AuthorizationService authorizationService;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyOrganisationAccessDataModel(ServicesUtil.getAuthorizationService());
        this.authorizationService = ServicesUtil.getAuthorizationService();
    }

    public LazyOrganisationAccessDataModel getDataModel() {
        return dataModel;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;

        if (this.organisationId != null) {
            this.dataModel.setOrganisationId(this.organisationId);
        }
    }

    public void save(AccessOrganisationResult accessOrganisation) {
        this.authorizationService.save(accessOrganisation);
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
