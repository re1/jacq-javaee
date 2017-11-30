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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblIndexSeminumRevision;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IndexSeminumResult {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IndexSeminumResult() {
    }

    public IndexSeminumResult(TblIndexSeminumRevision tblIndexSeminumRevision) {
        this.name = tblIndexSeminumRevision.getName();
    }

    /**
     * Helper function for converting a list of IndexSeminumRevision entries to
     * IndexSeminumResult
     *
     * @param indexSeminumRevisionList
     * @return
     */
    public static List<IndexSeminumResult> fromList(List<TblIndexSeminumRevision> indexSeminumRevisionList) {
        List<IndexSeminumResult> indexSeminumResult = new ArrayList<>();

        if (indexSeminumRevisionList != null) {
            for (TblIndexSeminumRevision indexSeminumRevision : indexSeminumRevisionList) {
                indexSeminumResult.add(new IndexSeminumResult(indexSeminumRevision));
            }
        }

        return indexSeminumResult;
    }
}
