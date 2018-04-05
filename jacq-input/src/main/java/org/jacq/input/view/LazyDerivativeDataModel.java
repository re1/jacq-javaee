/*
 * Copyright 2017 wkoller.
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
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.rest.DerivativeService;
import org.jacq.input.controller.LivingPlantController;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for derivative searches
 *
 * @author wkoller
 */
public class LazyDerivativeDataModel extends LazyDataModel<BotanicalObjectDerivative> {

    public static final String FILTER_ID = "id";
    public static final String FILTER_TYPE = "type";
    public static final String FILTER_TYPE_EMPTY = "null";

    /**
     * Reference to derivative service which is used during querying
     */
    protected DerivativeService derivativeService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectDerivative> derivativeResults = new ArrayList<>();

    /**
     * Search criteria
     */
    protected DerivativeSearchModel derivativeSearchModel;

    /**
     * Default constructor, needs a reference to the derivative service for later querying
     *
     * @param derivativeService
     */
    public LazyDerivativeDataModel(DerivativeService derivativeService, DerivativeSearchModel derivativeSearchModel) {
        this.derivativeService = derivativeService;
        this.derivativeSearchModel = derivativeSearchModel;
    }

    @Override
    public BotanicalObjectDerivative getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectDerivative derivativeResult : this.derivativeResults) {
            if (rowKeyLong.equals(derivativeResult.getDerivativeId())) {
                return derivativeResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectDerivative derivativeResult) {
        return derivativeResult.getDerivativeId();
    }

    @Override
    public List<BotanicalObjectDerivative> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // try to parse the id filter
        this.derivativeSearchModel.setId((filters.get(FILTER_ID) == null) ? null : Long.valueOf(String.valueOf(filters.get(FILTER_ID))));
        if (this.derivativeSearchModel.getSeparated()
                == 1) {
            this.derivativeSearchModel.setSeparatedFilter(true);
        }
        else if (this.derivativeSearchModel.getSeparated()
                == 2) {
            this.derivativeSearchModel.setSeparatedFilter(false);
        }
        else {
            this.derivativeSearchModel.setSeparatedFilter(null);
        }

        if (this.derivativeSearchModel.getIndexSeminum()
                == 1) {
            this.derivativeSearchModel.setIndexSeminumFilter(true);
        }
        else if (this.derivativeSearchModel.getIndexSeminum()
                == 2) {
            this.derivativeSearchModel.setIndexSeminumFilter(false);
        }
        else {
            this.derivativeSearchModel.setIndexSeminumFilter(null);
        }

        if (this.derivativeSearchModel.getExhibitionFilter() != null && this.derivativeSearchModel.getExhibitionFilter()) {
            this.derivativeSearchModel.setExhibition(1L);
        }
        else {
            this.derivativeSearchModel.setExhibition(null);
        }

        if (this.derivativeSearchModel.getWorkingFilter() != null && this.derivativeSearchModel.getWorkingFilter()) {
            this.derivativeSearchModel.setWorking(2L);
        }
        else {
            this.derivativeSearchModel.setWorking(null);
        }

        // quote type filter and set to null if empty
        this.derivativeSearchModel.setType(String.valueOf(filters.get(FILTER_TYPE)));

        if (StringUtils.isEmpty(this.derivativeSearchModel.getType())
                || FILTER_TYPE_EMPTY.equalsIgnoreCase(this.derivativeSearchModel.getType()) || LivingPlantController.TYPE_ALL.equalsIgnoreCase(this.derivativeSearchModel.getType())) {
            this.derivativeSearchModel.setType(null);
        }

        // get count first
        int rowCount = this.derivativeService.count(this.derivativeSearchModel.getType(), this.derivativeSearchModel.getId(), this.derivativeSearchModel.getPlaceNumber(), this.derivativeSearchModel.getAccessionNumber(), this.derivativeSearchModel.getSeparatedFilter(), this.derivativeSearchModel.getScientificNameId(), this.derivativeSearchModel.getOrganisationId(), this.derivativeSearchModel.getHierarchic(), this.derivativeSearchModel.getIndexSeminumFilter(), this.derivativeSearchModel.getGatheringLocationName(), (this.derivativeSearchModel.getExhibition() != null) ? this.derivativeSearchModel.getExhibition() : null, (this.derivativeSearchModel.getWorking() != null) ? this.derivativeSearchModel.getWorking() : null, this.derivativeSearchModel.getClassification());

        this.setRowCount(rowCount);

        List<BotanicalObjectDerivative> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.derivativeService.find(this.derivativeSearchModel.getType(), this.derivativeSearchModel.getId(), this.derivativeSearchModel.getPlaceNumber(), this.derivativeSearchModel.getAccessionNumber(), this.derivativeSearchModel.getSeparatedFilter(), this.derivativeSearchModel.getScientificNameId(), this.derivativeSearchModel.getOrganisationId(), this.derivativeSearchModel.getHierarchic(), this.derivativeSearchModel.getIndexSeminumFilter(), this.derivativeSearchModel.getGatheringLocationName(), (this.derivativeSearchModel.getExhibition() != null) ? this.derivativeSearchModel.getExhibition() : null, (this.derivativeSearchModel.getWorking() != null) ? this.derivativeSearchModel.getWorking() : null, this.derivativeSearchModel.getClassification(), sortField, (sortOrder.equals(SortOrder.DESCENDING)) ? OrderDirection.DESC : OrderDirection.ASC, first, pageSize);
        }

        return results;
    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return derivativeSearchModel;
    }

}
