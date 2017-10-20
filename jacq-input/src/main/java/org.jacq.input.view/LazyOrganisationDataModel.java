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
package org.jacq.input;

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.OrganisationResult;
import org.jacq.common.rest.OrganisationService;
import org.primefaces.model.LazyDataModel;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author fhafner
 */
public class LazyOrganisationDataModel extends LazyDataModel<OrganisationResult> {

    /**
     * Reference to organisation service which is used during querying
     */
    protected OrganisationService organisationService;

    /**
     * Internal storage of result list
     */
    protected List<OrganisationResult> organisationResult = new ArrayList<>();

    /**
     * organisationId
     */
    protected Long organisationId;

    /**
     * description
     */
    protected String description;

    /**
     * department
     */
    protected String department;

    /**
     * ipenCode
     */
    protected String ipenCode;

    /**
     * greenhouse
     */
    protected Boolean greenhouse;

    /**
     * Default constructor, needs a reference to the botanical object service
     * for later querying
     *
     * @param organisationService
     */
    public LazyOrganisationDataModel(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Override
    public OrganisationResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (OrganisationResult organisationResult : this.organisationResult) {
            if (organisationResult.getOrganisationId().equals(rowKeyLong)) {
                return organisationResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(OrganisationResult organisationResult) {
        return organisationResult.getOrganisationId();
    }

    public List<OrganisationResult> load(int first, int pageSize) {
        // get count first
        int rowCount = this.organisationService.searchCount();
        this.setRowCount(rowCount);

        List<OrganisationResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.organisationService.search(first, pageSize);
        }

        return results;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(boolean greenhouse) {
        this.greenhouse = greenhouse;
    }

    public String getIpenCode() {
        return ipenCode;
    }

    public void setIpenCode(String ipenCode) {
        this.ipenCode = ipenCode;
    }
}
