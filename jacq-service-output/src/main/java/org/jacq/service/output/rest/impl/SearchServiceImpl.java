/*
 * Copyright 2018 wkoller.
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
package org.jacq.service.output.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.rest.output.SearchService;
import org.jacq.service.output.manager.OrganisationManager;
import org.jacq.service.output.manager.SearchManager;

/**
 * Service for exposing main search functionality for output
 *
 * @author wkoller
 */
public class SearchServiceImpl implements SearchService {

    @Inject
    protected SearchManager searchManager;

    @Inject
    protected OrganisationManager organisationManager;

    /**
     * @see SearchService#find(java.lang.Long, java.lang.Long, java.lang.String,
     * org.jacq.common.model.rest.OrderDirection, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BotanicalObjectDerivative> find(Long scientificNameId, Long organisationId, Boolean hasImage, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        return searchManager.find(null, null, null, null, null, scientificNameId, organisationId, null, null, null, null, null, null, orderColumn, orderDirection, offset, count);
    }

    /**
     * @see SearchService#count(java.lang.Long, java.lang.Long)
     */
    @Override
    public int count(Long scientificNameId, Long organisationId, Boolean hasImage) {
        return searchManager.count(null, null, null, null, null, scientificNameId, organisationId, null, null, null, null, null, null);
    }

    /**
     * @see SearchService#organisationSearch(java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<OrganisationResult> organisationSearch(Long id, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener, Integer offset, Integer limit) {
        return organisationManager.search(id, description, department, greenhouse, ipenCode, parentOrganisationDescription, gardener, offset, limit);
    }

    /**
     * @see SearchService#organisationLoad(java.lang.Long)
     */
    @Override
    public OrganisationResult organisationLoad(Long id) {
        return organisationManager.load(id);
    }
}
