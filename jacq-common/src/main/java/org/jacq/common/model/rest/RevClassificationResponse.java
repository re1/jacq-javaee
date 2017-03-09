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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jacq.common.model.jpa.RevClassification;

/**
 * Wrapper class for exposing only the required revclassification properties
 *
 * @author wkoller
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RevClassificationResponse {

    protected RevClassification revClassification;

    public RevClassificationResponse() {
        this.revClassification = new RevClassification();
    }

    public RevClassificationResponse(RevClassification revClassification) {
        this.revClassification = revClassification;
    }

    public RevClassification getRevClassification() {
        return revClassification;
    }

    public void setRevClassification(RevClassification revClassification) {
        this.revClassification = revClassification;
    }

    @XmlElement
    public String getScientificName() {
        return revClassification.getScientificName();
    }

    public void setScientificName(String scientificName) {
        revClassification.setScientificName(scientificName);
    }

    public static List<RevClassificationResponse> fromList(List<RevClassification> revClassifications) {
        List<RevClassificationResponse> results = new ArrayList<>();

        return results;
    }
}
