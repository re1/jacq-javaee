/*
 * Copyright 2017 fhafner.
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
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.service.manager.OrganisationManager;

/**
 *
 * @author fhafner
 */
public class OrganisationServiceImpl implements OrganisationService {

    @Inject
    protected OrganisationManager organisationManager;

    /**
     * @see OrganisationService#search(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.Integer, java.lang.Integer)
     * @param organisationId
     * @param description
     * @param department
     * @param greenhouse
     * @param ipenCode
     * @param parentOrganisationDescription
     * @param gardener
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<OrganisationResult> search(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener, Integer offset, Integer limit) {
        return organisationManager.search(organisationId, description, department, greenhouse, ipenCode, parentOrganisationDescription, gardener, offset, limit);
    }

    /**
     * @see OrganisationService#searchCount(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String,
     * java.lang.String)
     * @param organisationId
     * @param description
     * @param department
     * @param greenhouse
     * @param ipenCode
     * @param parentOrganisationDescription
     * @param gardener
     * @return
     */
    @Override
    public int searchCount(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener) {
        return organisationManager.searchCount(organisationId, description, department, greenhouse, ipenCode, parentOrganisationDescription, gardener);
    }

    /**
     * @see OrganisationService#load(java.lang.Long)
     * @param organisationId
     * @return
     */
    @Override
    public OrganisationResult load(Long organisationId) {
        return organisationManager.load(organisationId);
    }

    /**
     * @see
     * OrganisationService#save(org.jacq.common.model.rest.OrganisationResult)
     * @param organisationResult
     * @return
     */
    @Override
    public OrganisationResult save(OrganisationResult organisationResult) {
        return organisationManager.save(organisationResult);
    }

    /**
     * @see OrganisationService#findAll()
     * @return
     */
    @Override
    public List<OrganisationResult> findAll() {
        return organisationManager.search(null, null, null, null, null, null, null, null, null);
    }

    /**
     * @see OrganisationService#getIpenCode(java.lang.Long)
     * @param organisationId
     * @return
     */
    @Override
    public String getIpenCode(Long organisationId) {
        return organisationManager.getIpenCode(organisationId);
    }
}
