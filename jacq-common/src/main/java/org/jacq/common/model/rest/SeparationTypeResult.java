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
import org.jacq.common.model.jpa.TblSeparationType;

/**
 * @see TblSeparationType
 *
 * @author wkoller
 */
public class SeparationTypeResult {

    protected Long separationTypeId;
    protected String type;

    public SeparationTypeResult() {
    }

    public SeparationTypeResult(TblSeparationType tblSeparationType) {
        if (tblSeparationType != null) {
            this.separationTypeId = tblSeparationType.getId();
            this.type = tblSeparationType.getType();
        }
    }

    public Long getSeparationTypeId() {
        return separationTypeId;
    }

    public void setSeparationTypeId(Long separationTypeId) {
        this.separationTypeId = separationTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper function for converting a list of Separation type entries to separation type results
     *
     * @param separationTypeList
     * @return
     */
    public static List<SeparationTypeResult> fromList(List<TblSeparationType> separationTypeList) {
        List<SeparationTypeResult> separationTypeResultList = new ArrayList<>();

        if (separationTypeList != null) {
            for (TblSeparationType separationType : separationTypeList) {
                separationTypeResultList.add(new SeparationTypeResult(separationType));
            }
        }

        return separationTypeResultList;
    }
}
