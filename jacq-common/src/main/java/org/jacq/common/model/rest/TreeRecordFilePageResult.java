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
import org.jacq.common.model.jpa.TblTreeRecordFilePage;

/**
 * Wrapper model which represents a single result after a search Used to
 * minimize the transfered data and only return the relevant information
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TreeRecordFilePageResult {

    protected Long treeRecordFilePageId;
    protected int page;
    protected String content;

    public Long getTreeRecordFilePageId() {
        return treeRecordFilePageId;
    }

    public void setTreeRecordFilePageId(Long treeRecordFilePageId) {
        this.treeRecordFilePageId = treeRecordFilePageId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TreeRecordFilePageResult() {
    }

    public TreeRecordFilePageResult(TblTreeRecordFilePage treeRecordFilePage) {
        this.treeRecordFilePageId = treeRecordFilePage.getId();
        this.page = treeRecordFilePage.getPage();
        this.content = treeRecordFilePage.getContent();
    }

    /**
     * Helper function for converting a list of TreeRecordFile entries to
     * treeRecordFileResult
     *
     * @param treeRecordFilePageList
     * @return
     */
    public static List<TreeRecordFilePageResult> fromList(List<TblTreeRecordFilePage> treeRecordFilePageList) {
        List<TreeRecordFilePageResult> treeRecordFilePageResult = new ArrayList<>();

        if (treeRecordFilePageList != null) {
            for (TblTreeRecordFilePage treeRecordFilePage : treeRecordFilePageList) {
                treeRecordFilePageResult.add(new TreeRecordFilePageResult(treeRecordFilePage));
            }
        }

        return treeRecordFilePageResult;
    }
}
