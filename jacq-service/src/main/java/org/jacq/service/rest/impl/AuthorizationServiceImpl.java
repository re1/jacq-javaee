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
package org.jacq.service.rest.impl;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.AccessOrganisationResult;
import org.jacq.common.rest.AuthorizationService;
import org.jacq.service.manager.AuthorizationManager;

/**
 *
 * @author fhafner
 */
public class AuthorizationServiceImpl implements AuthorizationService {

    @Inject
    protected AuthorizationManager authorizationManager;

    /**
     * @see AuthorizationService#search(java.lang.Long, java.lang.String,
     * java.util.Date, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.Long, java.lang.Boolean, java.lang.Long, java.lang.Long,
     * java.lang.Integer, java.lang.Integer)
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param organisationDescription
     * @param accessOrganisationId
     * @param allowDeny
     * @param userId
     * @param organisationId
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<AccessOrganisationResult> search(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription, Long accessOrganisationId, Boolean allowDeny, Long userId, Long organisationId, Integer offset, Integer limit) {
        return authorizationManager.search(id, username, birthdate, userType, employmentType, organisationDescription, accessOrganisationId, allowDeny, userId, organisationId, offset, limit);
    }

    /**
     * @see AuthorizationService#searchCount(java.lang.Long, java.lang.String,
     * java.util.Date, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.Long, java.lang.Boolean, java.lang.Long, java.lang.Long)
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param organisationDescription
     * @param accessOrganisationId
     * @param allowDeny
     * @param userId
     * @param organisationId
     * @return
     */
    @Override
    public int searchCount(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription, Long accessOrganisationId, Boolean allowDeny, Long userId, Long organisationId) {
        return authorizationManager.searchCount(id, username, birthdate, userType, employmentType, organisationDescription, accessOrganisationId, allowDeny, userId, organisationId);
    }

    /**
     * @see
     * AuthorizationService#save(org.jacq.common.model.rest.AccessOrganisationResult)
     * @param accessOrganisationResult
     * @return
     */
    @Override
    public AccessOrganisationResult save(AccessOrganisationResult accessOrganisationResult) {
        return authorizationManager.save(accessOrganisationResult);
    }

}
