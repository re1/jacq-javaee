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
import org.jacq.common.model.jpa.TblLabelType;

/**
 *
 * @author wkoller
 */
public class LabelTypeResult {

    protected Long labelTypeId;
    protected String type;

    public LabelTypeResult() {
    }

    public LabelTypeResult(Long labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public LabelTypeResult(TblLabelType tblLabelType) {
        if (tblLabelType != null) {
            this.labelTypeId = tblLabelType.getLabelTypeId();
            this.type = tblLabelType.getType();
        }
    }

    public Long getLabelTypeId() {
        return labelTypeId;
    }

    public void setLabelTypeId(Long labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper function for converting a list of label-type entries to label type
     * result
     *
     * @param labelTypeList
     * @return
     */
    public static List<LabelTypeResult> fromList(List<TblLabelType> labelTypeList) {
        List<LabelTypeResult> labelTypeResult = new ArrayList<>();

        if (labelTypeList != null) {
            for (TblLabelType tblLabelType : labelTypeList) {
                labelTypeResult.add(new LabelTypeResult(tblLabelType));
            }
        }

        return labelTypeResult;
    }
}
