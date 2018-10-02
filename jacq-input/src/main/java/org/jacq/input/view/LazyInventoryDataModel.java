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
import org.jacq.common.model.rest.InventoryResult;
import org.jacq.common.rest.InventoryService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fhafner
 */
public class LazyInventoryDataModel extends LazyDataModel<InventoryResult> {

    /**
     * Reference to indexSeminum service which is used during querying
     */
    protected InventoryService inventoryService;

    /**
     * Internal storage of result list
     */
    protected List<InventoryResult> inventoryResults = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the inventoryResults object
     * service for later querying
     *
     * @param inventoryService
     */
    public LazyInventoryDataModel(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public InventoryResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (InventoryResult inventoryResult : this.inventoryResults) {
            if (inventoryResult.getInventoryId().equals(rowKeyLong)) {
                return inventoryResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(InventoryResult inventoryResult) {
        return inventoryResult.getInventoryId();
    }

    @Override
    public List<InventoryResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // get count first
        int rowCount = this.inventoryService.searchCount();
        this.setRowCount(rowCount);

        List<InventoryResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.inventoryService.search(first, pageSize);
        }

        return results;
    }

}
