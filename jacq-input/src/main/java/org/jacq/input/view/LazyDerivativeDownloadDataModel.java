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
import java.util.List;
import java.util.Map;
import org.jacq.common.model.rest.BotanicalObjectDownloadResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.rest.DerivativeService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fhafner
 */
public class LazyDerivativeDownloadDataModel extends LazyDataModel<BotanicalObjectDownloadResult> {

    /**
     * Reference to derivative service which is used during querying
     */
    protected DerivativeService derivativeService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectDownloadResult> botanicalObjectDownloadResult = new ArrayList<>();

    protected LazyDerivativeDataModel lazyDerivativeDataModel;

    /**
     * Default constructor, needs a reference to the derivative service for later querying
     *
     * @param derivativeService
     * @param lazyDerivativeDataModel
     */
    public LazyDerivativeDownloadDataModel(DerivativeService derivativeService, LazyDerivativeDataModel lazyDerivativeDataModel) {
        this.derivativeService = derivativeService;
        this.lazyDerivativeDataModel = lazyDerivativeDataModel;
    }

    @Override
    public BotanicalObjectDownloadResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectDownloadResult botanicalObjectDownloadResult : this.botanicalObjectDownloadResult) {
            if (botanicalObjectDownloadResult.getBotanicalObjectId().equals(rowKeyLong)) {
                return botanicalObjectDownloadResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectDownloadResult botanicalObjectDownloadResult) {
        return botanicalObjectDownloadResult.getBotanicalObjectId();
    }

    @Override
    public List<BotanicalObjectDownloadResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // get count first
        int rowCount = this.derivativeService.count(this.lazyDerivativeDataModel.getDerivativeSearchModel().getType(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getPlaceNumber(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getAccessionNumber(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getSeparatedFilter(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getScientificNameId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getOrganisationId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getHierarchic(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getIndexSeminumFilter(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getGatheringLocationName());
        this.setRowCount(rowCount);

        List<BotanicalObjectDownloadResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.derivativeService.downloadFind(this.lazyDerivativeDataModel.getDerivativeSearchModel().getType(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getPlaceNumber(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getAccessionNumber(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getSeparatedFilter(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getScientificNameId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getOrganisationId(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getHierarchic(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getIndexSeminumFilter(), this.lazyDerivativeDataModel.getDerivativeSearchModel().getGatheringLocationName(), sortField, (sortOrder.equals(SortOrder.DESCENDING)) ? OrderDirection.DESC : OrderDirection.ASC, first, pageSize);
        }

        return results;
    }

}
