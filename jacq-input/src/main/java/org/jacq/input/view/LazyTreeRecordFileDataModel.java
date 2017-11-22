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
package org.jacq.input.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jacq.common.model.rest.TreeRecordFileResult;
import org.jacq.common.rest.TreeRecordFileService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author fhafner
 */
public class LazyTreeRecordFileDataModel extends LazyDataModel<TreeRecordFileResult> {

    /**
     * Reference to organisation service which is used during querying
     */
    protected TreeRecordFileService treeRecordFileService;

    /**
     * Internal storage of result list
     */
    protected List<TreeRecordFileResult> treeRecordFileResult = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the botanical object service
     * for later querying
     *
     * @param organisationService
     */
    public LazyTreeRecordFileDataModel(TreeRecordFileService treeRecordFileService) {
        this.treeRecordFileService = treeRecordFileService;
    }

    @Override
    public TreeRecordFileResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (TreeRecordFileResult treeRecordFileResult : this.treeRecordFileResult) {
            if (treeRecordFileResult.getTreeRecordFileId().equals(rowKeyLong)) {
                return treeRecordFileResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(TreeRecordFileResult treeRecordFileResult) {
        return treeRecordFileResult.getTreeRecordFileId();
    }

    @Override
    public List<TreeRecordFileResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        //Year Parser
        Calendar cal = Calendar.getInstance();
        Date year = new Date();
        if (filters.get("year") != null) {
            int parseYear = (int) filters.get("year");
            cal.set(parseYear, 0, 1, 0, 0, 0);
            year = cal.getTime();
        } else {
            year = null;
        }

        // get count first
        int rowCount = this.treeRecordFileService.searchCount(filters.get("treeRecordFileId") != null ? Long.parseLong(filters.get("treeRecordFileId").toString()) : null, year, filters.get("name") != null ? filters.get("name").toString() : null, filters.get("documentNumber") != null ? filters.get("documentNumber").toString() : null);
        this.setRowCount(rowCount);

        List<TreeRecordFileResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.treeRecordFileService.search(filters.get("treeRecordFileId") != null ? Long.parseLong(filters.get("treeRecordFileId").toString()) : null, year, filters.get("name") != null ? filters.get("name").toString() : null, filters.get("documentNumber") != null ? filters.get("documentNumber").toString() : null, first, pageSize);
        }

        return results;
    }
}
