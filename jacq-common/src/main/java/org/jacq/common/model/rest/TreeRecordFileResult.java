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
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblTreeRecordFile;

/**
 * Wrapper model which represents a single result after a search Used to minimize the transfered data and only return
 * the relevant information
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TreeRecordFileResult {

    protected Long treeRecordFileId;
    protected Date year;
    protected String name;
    protected String documentNumber;
    protected String fileContent;

    public Long getTreeRecordFileId() {
        return treeRecordFileId;
    }

    public void setTreeRecordFileId(Long treeRecordFileId) {
        this.treeRecordFileId = treeRecordFileId;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public TreeRecordFileResult() {
    }

    public TreeRecordFileResult(TblTreeRecordFile treeRecordFile) {
        this.treeRecordFileId = treeRecordFile.getId();
        this.year = treeRecordFile.getYear();
        this.name = treeRecordFile.getName();
        this.documentNumber = treeRecordFile.getDocumentNumber();
    }

    /**
     * Helper function for converting a list of TreeRecordFile entries to treeRecordFileResult
     *
     * @param treeRecordFileList
     * @return
     */
    public static List<TreeRecordFileResult> fromList(List<TblTreeRecordFile> treeRecordFileList) {
        List<TreeRecordFileResult> treeRecordFileResult = new ArrayList<>();

        if (treeRecordFileList != null) {
            for (TblTreeRecordFile treeRecordFile : treeRecordFileList) {
                treeRecordFileResult.add(new TreeRecordFileResult(treeRecordFile));
            }
        }

        return treeRecordFileResult;
    }
}
