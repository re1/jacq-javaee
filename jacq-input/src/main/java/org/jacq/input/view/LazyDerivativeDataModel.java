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
import org.jacq.common.model.BotanicalObjectDerivative;
import org.jacq.common.model.OrganisationResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.OrganisationService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for derivative searches
 *
 * @author wkoller
 */
public class LazyDerivativeDataModel extends LazyDataModel<BotanicalObjectDerivative> {

    /**
     * Reference to derivative service which is used during querying
     */
    protected DerivativeService derivativeService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectDerivative> derivativeResults = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the derivative service for later querying
     *
     * @param derivativeService
     */
    public LazyDerivativeDataModel(DerivativeService derivativeService) {
        this.derivativeService = derivativeService;
    }

    @Override
    public BotanicalObjectDerivative getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectDerivative derivativeResult : this.derivativeResults) {
            if (derivativeResult.getId().equals(rowKeyLong)) {
                return derivativeResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectDerivative derivativeResult) {
        return derivativeResult.getId();
    }

    @Override
    public List<BotanicalObjectDerivative> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        // get count first
        int rowCount = this.derivativeService.count(null, null);
        this.setRowCount(rowCount);

        List<BotanicalObjectDerivative> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.derivativeService.find(null, null, sortField, sortOrder.name(), first, pageSize);
        }

        return results;
    }
}
