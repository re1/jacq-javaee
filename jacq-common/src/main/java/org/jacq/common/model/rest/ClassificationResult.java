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
import org.jacq.common.model.jpa.TblClassification;

/**
 * Rest model for classification sources
 *
 * @author wkoller
 */
public class ClassificationResult {

    protected ClassificationSourceType sourceType;
    protected Long sourceId;
    protected String sourceName;

    public ClassificationResult(TblClassification tblClassification) {
        if (tblClassification != null) {
            this.sourceType = ClassificationSourceType.valueOf(tblClassification.getSource());
            this.sourceId = tblClassification.getSourceId();

            if (ClassificationSourceType.CITATION.equals(this.sourceType)) {
                this.sourceName = tblClassification.getViewProtolog().getProtolog();
            }
        }
    }

    public ClassificationSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(ClassificationSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * Helper function for converting a list of TblClassification entries to
     * Classification results
     *
     * @param tblClassificationList
     * @return
     */
    public static List<ClassificationResult> fromList(List<TblClassification> tblClassificationList) {
        List<ClassificationResult> classificationList = new ArrayList<>();

        if (tblClassificationList != null) {
            for (TblClassification tblClassification : tblClassificationList) {
                classificationList.add(new ClassificationResult(tblClassification));
            }
        }

        return classificationList;
    }
}
