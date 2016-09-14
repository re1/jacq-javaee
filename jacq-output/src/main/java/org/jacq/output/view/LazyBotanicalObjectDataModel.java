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
import org.jacq.common.model.BotanicalObjectResult;
import org.jacq.common.rest.BotanicalObjectService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author wkoller
 */
public class LazyBotanicalObjectDataModel extends LazyDataModel<BotanicalObjectResult> {

    /**
     * Reference to botanical object service which is used during querying
     */
    protected BotanicalObjectService botanicalObjectService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectResult> botanicalObjectResults = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the botanical object service for later querying
     *
     * @param botanicalObjectService
     */
    public LazyBotanicalObjectDataModel(BotanicalObjectService botanicalObjectService) {
        this.botanicalObjectService = botanicalObjectService;
    }

    @Override
    public BotanicalObjectResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectResult botanicalObjectResult : this.botanicalObjectResults) {
            if (botanicalObjectResult.getBotanicalObjectId().equals(rowKeyLong)) {
                return botanicalObjectResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectResult botanicalObjectResult) {
        return botanicalObjectResult.getBotanicalObjectId();
    }

    @Override
    public List<BotanicalObjectResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.botanicalObjectService.search("Annona", "", Boolean.FALSE);
    }

}
