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
import org.jacq.common.model.rest.IndexSeminumDownloadResult;
import org.jacq.common.rest.IndexSeminumService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fhafner
 */
public class LazyIndexSeminumDownloadDataModel extends LazyDataModel<IndexSeminumDownloadResult> {

    /**
     * Reference to derivative service which is used during querying
     */
    protected IndexSeminumService indexSeminumService;

    /**
     * Internal storage of result list
     */
    protected List<IndexSeminumDownloadResult> indexSeminumDownloadResult = new ArrayList<>();

    protected LazyDerivativeDataModel lazyDerivativeDataModel;

    protected Long indexSeminumRevisionId;

    /**
     * Default constructor, needs a reference to the indexSeminum object service
     * for later querying
     *
     * @param indexSeminumService
     */
    public LazyIndexSeminumDownloadDataModel(IndexSeminumService indexSeminumService) {
        this.indexSeminumService = indexSeminumService;
    }

    @Override
    public IndexSeminumDownloadResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (IndexSeminumDownloadResult indexSeminumDownloadResult : this.indexSeminumDownloadResult) {
            if (indexSeminumDownloadResult.getIndexSeminumContentId().equals(rowKeyLong)) {
                return indexSeminumDownloadResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(IndexSeminumDownloadResult indexSeminumDownloadResult) {
        return indexSeminumDownloadResult.getIndexSeminumContentId();
    }

    @Override
    public List<IndexSeminumDownloadResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // get count first
        int rowCount = this.indexSeminumService.searchCountContent(indexSeminumRevisionId);
        this.setRowCount(rowCount);

        List<IndexSeminumDownloadResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.indexSeminumService.searchContent(indexSeminumRevisionId, first, pageSize);
        }

        return results;
    }

    public void setIndexSeminumRevisionId(Long indexSeminumRevisionId) {
        this.indexSeminumRevisionId = indexSeminumRevisionId;
    }

}
