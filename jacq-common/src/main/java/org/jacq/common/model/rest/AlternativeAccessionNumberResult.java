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
import org.jacq.common.model.jpa.TblAlternativeAccessionNumber;
import org.jacq.common.model.jpa.TblCultivar;

/**
 *
 * @author wkoller
 */
public class AlternativeAccessionNumberResult {

    protected Long alternativeAccessionNumberId;
    protected String number;

    public AlternativeAccessionNumberResult() {
    }

    public AlternativeAccessionNumberResult(TblAlternativeAccessionNumber tblAlternativeAccessionNumber) {
        this.alternativeAccessionNumberId = tblAlternativeAccessionNumber.getId();
        this.number = tblAlternativeAccessionNumber.getNumber();
    }

    public Long getAlternativeAccessionNumberId() {
        return alternativeAccessionNumberId;
    }

    public void setAlternativeAccessionNumberId(Long alternativeAccessionNumberId) {
        this.alternativeAccessionNumberId = alternativeAccessionNumberId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Helper function for converting a list of TblAlternativeAccessionNumber entries to
     * AlternativeAccessionNumberResults
     *
     * @param cultivarList
     * @return
     */
    public static List<AlternativeAccessionNumberResult> fromList(List<TblAlternativeAccessionNumber> tblAlternativeAccessionNumbers) {
        List<AlternativeAccessionNumberResult> alternativeAccessionNumberResults = new ArrayList<>();

        if (tblAlternativeAccessionNumbers != null) {
            for (TblAlternativeAccessionNumber tblAlternativeAccessionNumber : tblAlternativeAccessionNumbers) {
                alternativeAccessionNumberResults.add(new AlternativeAccessionNumberResult(tblAlternativeAccessionNumber));
            }
        }

        return alternativeAccessionNumberResults;
    }
}
