/*
 * Copyright 2017 wkoller.
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
package org.jacq.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblClassification;

/**
 * Wrapper class for classification entries which exposes only the required properties
 *
 * @author wkoller
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassificationResult {

    protected ClassificationSourceType source;
    protected Long sourceId;
    protected Long scientificNameId;
    protected String scientificName;

    public ClassificationSourceType getSource() {
        return source;
    }

    public void setSource(ClassificationSourceType source) {
        this.source = source;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public ClassificationResult() {
    }

    /**
     * Create result object based on TblClassification entry
     *
     * @param classification
     */
    public ClassificationResult(TblClassification classification) {
        this.source = ClassificationSourceType.valueOf(classification.getSource());
        this.sourceId = classification.getSourceId();
        this.scientificNameId = classification.getScientificNameId();
        this.scientificName = classification.getViewScientificName().getScientificName();
    }

    /**
     * Helper function for converting a list of BotanicalObject entries to botanicalobject results
     *
     * @param classificationList
     * @return
     */
    public static List<ClassificationResult> fromList(List<TblClassification> classificationList) {
        List<ClassificationResult> classificationResults = new ArrayList<>();

        if (classificationList != null) {
            for (TblClassification classification : classificationList) {
                classificationResults.add(new ClassificationResult(classification));
            }
        }

        return classificationResults;
    }
}
