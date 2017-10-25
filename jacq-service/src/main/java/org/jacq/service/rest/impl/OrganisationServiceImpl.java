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
import org.jacq.common.model.OrganisationResult;
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
     * @see OrganisationService#search()
     */
    @Override
    public List<OrganisationResult> search(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, Integer offset, Integer limit) {
        return organisationManager.search(organisationId, description, department, greenhouse, ipenCode, offset, limit);
    }

    @Override
    public int searchCount(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode) {
        return organisationManager.searchCount(organisationId, description, department, greenhouse, ipenCode);
    }
}
