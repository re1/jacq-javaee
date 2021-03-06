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
package org.jacq.output.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.output.SearchService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author wkoller
 */
public class LazyBotanicalObjectDataModel extends LazyDataModel<BotanicalObjectDerivative> {

    /**
     * Reference to botanical object service which is used during querying
     */
    protected SearchService searchService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectDerivative> botanicalObjectDerivatives = new ArrayList<>();

    /**
     * Scientific name to search for
     */
    protected ScientificNameResult scientificName;

    /**
     * Organization to search for
     */
    protected OrganisationResult organization;

    /**
     * Filter for records with images only
     */
    protected Boolean hasImage;

    public LazyBotanicalObjectDataModel(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public BotanicalObjectDerivative getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectDerivative botanicalObjectDerivative : this.botanicalObjectDerivatives) {
            if (botanicalObjectDerivative.getDerivativeId() == rowKeyLong) {
                return botanicalObjectDerivative;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectDerivative botanicalObjectDerivative) {
        return botanicalObjectDerivative.getDerivativeId();
    }

    @Override
    public List<BotanicalObjectDerivative> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        // get count first
        //int rowCount = this.derivativeService.count(getScientificName(), getOrganization(), hasImage);
        int rowCount = this.searchService.count((scientificName != null) ? scientificName.getScientificNameId() : null, (organization != null) ? organization.getOrganisationId() : null, hasImage);
        this.setRowCount(rowCount);

        List<BotanicalObjectDerivative> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.searchService.find((scientificName != null) ? scientificName.getScientificNameId() : null, (organization != null) ? organization.getOrganisationId() : null, hasImage, sortField, (sortOrder.equals(SortOrder.DESCENDING)) ? OrderDirection.DESC : OrderDirection.ASC, first, pageSize);
        }

        return results;
    }

    public ScientificNameResult getScientificName() {
        return scientificName;
    }

    public void setScientificName(ScientificNameResult scientificName) {
        this.scientificName = scientificName;
    }

    public OrganisationResult getOrganization() {
        return organization;
    }

    public void setOrganization(OrganisationResult organization) {
        this.organization = organization;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(Boolean hasImage) {
        this.hasImage = hasImage;
    }
}
