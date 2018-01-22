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
import org.jacq.common.model.jpa.TblCultivar;
import org.jacq.common.model.jpa.TblIndexSeminumType;

/**
 *
 * @author wkoller
 */
public class IndexSeminumTypeResult {

    protected Long indexSeminumTypeId;
    protected String type;

    public IndexSeminumTypeResult() {
    }

    public IndexSeminumTypeResult(TblIndexSeminumType indexSeminumType) {
        if (indexSeminumType != null) {
            this.indexSeminumTypeId = indexSeminumType.getId();
            this.type = indexSeminumType.getType();
        }
    }

    public Long getIndexSeminumTypeId() {
        return indexSeminumTypeId;
    }

    public void setIndexSeminumTypeId(Long indexSeminumTypeId) {
        this.indexSeminumTypeId = indexSeminumTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper function for converting a list of TblIndexSeminumType entries to IndexSeminumType results
     *
     * @param indexSeminumTypes
     * @return
     */
    public static List<IndexSeminumTypeResult> fromList(List<TblIndexSeminumType> indexSeminumTypes) {
        List<IndexSeminumTypeResult> indexSeminumTypeResults = new ArrayList<>();

        if (indexSeminumTypes != null) {
            for (TblIndexSeminumType indexSeminumType : indexSeminumTypes) {
                indexSeminumTypeResults.add(new IndexSeminumTypeResult(indexSeminumType));
            }
        }

        return indexSeminumTypeResults;
    }

}
