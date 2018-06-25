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
package org.jacq.input.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jacq.common.model.rest.AccessOrganisationResult;
import org.jacq.common.rest.AuthorizationService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fhafner
 */
public class LazyOrganisationAccessDataModel extends LazyDataModel<AccessOrganisationResult> {

    /**
     * Reference to authorization service which is used during querying
     */
    protected AuthorizationService authorizationService;

    /**
     * Internal storage of result list
     */
    protected List<AccessOrganisationResult> accessOrganisationResultList = new ArrayList<>();

    protected Long Id;
    protected String username;
    protected Date birthdate;
    protected String userType;
    protected String employmentType;
    protected String organisationDescription;
    protected Long accessOrganisationId;
    protected Boolean allowDeny;
    protected Long userId;
    protected Long organisationId;

    /**
     * Default constructor, needs a reference to the organisation object service
     * for later querying
     *
     * @param authorizationService
     */
    public LazyOrganisationAccessDataModel(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public AccessOrganisationResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (AccessOrganisationResult accessOrganisationResult : this.accessOrganisationResultList) {
            if (accessOrganisationResult.getId().equals(rowKeyLong)) {
                return accessOrganisationResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(AccessOrganisationResult accessOrganisationResult) {
        return accessOrganisationResult.getId();
    }

    @Override
    public List<AccessOrganisationResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // get count first
        int rowCount = this.authorizationService.searchCount(this.Id, this.username, this.birthdate, this.userType, this.employmentType, this.organisationDescription, this.accessOrganisationId, this.allowDeny, this.userId, this.organisationId);
        this.setRowCount(rowCount);

        List<AccessOrganisationResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.authorizationService.search(this.Id, this.username, this.birthdate, this.userType, this.employmentType, this.organisationDescription, this.accessOrganisationId, this.allowDeny, this.userId, this.organisationId, first, pageSize);
        }

        return results;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    public List<AccessOrganisationResult> getAccessOrganisationResultList() {
        return accessOrganisationResultList;
    }

    public void setAccessOrganisationResultList(List<AccessOrganisationResult> accessOrganisationResultList) {
        this.accessOrganisationResultList = accessOrganisationResultList;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getOrganisationDescription() {
        return organisationDescription;
    }

    public void setOrganisationDescription(String organisationDescription) {
        this.organisationDescription = organisationDescription;
    }

    public Long getAccessOrganisationId() {
        return accessOrganisationId;
    }

    public void setAccessOrganisationId(Long accessOrganisationId) {
        this.accessOrganisationId = accessOrganisationId;
    }

    public Boolean getAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(Boolean allowDeny) {
        this.allowDeny = allowDeny;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

}
