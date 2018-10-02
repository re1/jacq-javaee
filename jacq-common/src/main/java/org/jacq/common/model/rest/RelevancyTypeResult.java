/*
 * Copyright 2018 wkoller.
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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.jpa.TblRelevancyType;

/**
 *
 * @author wkoller
 */
public class RelevancyTypeResult {

    protected Long relevancyTypeId;
    protected String type;
    protected boolean important;

    public RelevancyTypeResult() {
    }

    public RelevancyTypeResult(Long relevancyTypeId) {
        this.relevancyTypeId = relevancyTypeId;
    }

    public RelevancyTypeResult(TblRelevancyType tblRelevancyType) {
        if (tblRelevancyType != null) {
            this.relevancyTypeId = tblRelevancyType.getId();
            this.type = tblRelevancyType.getType();
            this.important = tblRelevancyType.getImportant();
        }
    }

    public Long getRelevancyTypeId() {
        return relevancyTypeId;
    }

    public void setRelevancyTypeId(Long relevancyTypeId) {
        this.relevancyTypeId = relevancyTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    /**
     * Helper function for converting a list of TblRelevancyType entries to
     * RelevancyTypeResult
     *
     * @param tblRelevancyTypeList
     * @return
     */
    public static List<RelevancyTypeResult> fromList(List<TblRelevancyType> tblRelevancyTypeList) {
        List<RelevancyTypeResult> relevancyTypeResultList = new ArrayList<>();

        if (tblRelevancyTypeList != null) {
            for (TblRelevancyType tblRelevancyType : tblRelevancyTypeList) {
                relevancyTypeResultList.add(new RelevancyTypeResult(tblRelevancyType));
            }
        }

        return relevancyTypeResultList;
    }
}
