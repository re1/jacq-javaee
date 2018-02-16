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
import java.util.Date;
import java.util.List;
import org.jacq.common.model.jpa.TblSeparation;

/**
 * @see TblSeparation
 *
 * @author wkoller
 */
public class SeparationResult {

    protected Long separationId;
    protected Date date;
    protected String annotation;

    protected SeparationTypeResult separationType;

    public SeparationResult() {
        this.separationType = new SeparationTypeResult();
    }

    public SeparationResult(TblSeparation tblSeparation) {
        if (tblSeparation != null) {
            this.separationId = tblSeparation.getSeparationId();
            this.date = tblSeparation.getDate();
            this.annotation = tblSeparation.getAnnotation();
            this.separationType = new SeparationTypeResult(tblSeparation.getSeparationTypeId());
        }
    }

    public Long getSeparationId() {
        return separationId;
    }

    public void setSeparationId(Long separationId) {
        this.separationId = separationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public SeparationTypeResult getSeparationType() {
        return separationType;
    }

    public void setSeparationType(SeparationTypeResult separationType) {
        this.separationType = separationType;
    }

    /**
     * Helper function for converting a list of Separation entries to separation results
     *
     * @param separationList
     * @return
     */
    public static List<SeparationResult> fromList(List<TblSeparation> separationList) {
        List<SeparationResult> separationResultList = new ArrayList<>();

        if (separationList != null) {
            for (TblSeparation separation : separationList) {
                separationResultList.add(new SeparationResult(separation));
            }
        }

        return separationResultList;
    }
}
