/*
 * Copyright 2017 fhafner.
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jacq.common.model.UserResult;
import org.jacq.common.rest.UserService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fhafner
 */
public class LazyUserDataModel extends LazyDataModel<UserResult> {

    /**
     * Reference to User service which is used during querying
     */
    protected UserService userService;

    /**
     * Internal storage of result list
     */
    protected List<UserResult> userResult = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the user object service for
     * later querying
     *
     * @param userService
     */
    public LazyUserDataModel(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (UserResult userResult : this.userResult) {
            if (userResult.getId().equals(rowKeyLong)) {
                return userResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(UserResult userResult) {
        return userResult.getId();
    }

    @Override
    public List<UserResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        //Parse Datefilter
        Date date = new Date();
        if (filters.get("birthdate") != null) {
            try {
                date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(String.valueOf(filters.get("birthdate")));
            } catch (Exception exception) {
                System.out.print(exception);
            }
        } else {
            date = null;
        }

        // get count first
        int rowCount = this.userService.searchCount((filters.get("id") != null) ? Long.parseLong(filters.get("id").toString()) : null, filters.get("username") != null ? filters.get("username").toString() : null, null, filters.get("userType") != null ? filters.get("userType").toString() : null, filters.get("employmentType") != null ? filters.get("employmentType").toString() : null, filters.get("organisationDescription") != null ? filters.get("organisationDescription").toString() : null);
        this.setRowCount(rowCount);

        List<UserResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.userService.search((filters.get("id") != null) ? Long.parseLong(filters.get("id").toString()) : null, filters.get("username") != null ? filters.get("username").toString() : null, null, filters.get("userType") != null ? filters.get("userType").toString() : null, filters.get("employmentType") != null ? filters.get("employmentType").toString() : null, filters.get("organisationDescription") != null ? filters.get("organisationDescription").toString() : null, first, pageSize);
        }

        return results;
    }

}
