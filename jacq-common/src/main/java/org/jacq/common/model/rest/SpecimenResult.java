/*
 * Copyright 2018 fhafner.
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
import org.jacq.common.model.jpa.TblSpecimen;

/**
 *
 * @author fhafner
 */
public class SpecimenResult {

    protected Long specimenId;
    protected String herbarNumber;

    public Long getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(Long specimenId) {
        this.specimenId = specimenId;
    }

    public String getHerbarNumber() {
        return herbarNumber;
    }

    public void setHerbarNumber(String herbarNumber) {
        this.herbarNumber = herbarNumber;
    }

    public SpecimenResult() {
    }

    public SpecimenResult(TblSpecimen tblSpecimen) {
        this.specimenId = tblSpecimen.getSpecimenId();
        this.herbarNumber = tblSpecimen.getHerbarNumber();
    }

    /**
     * Helper function for converting a list of TblAlternativeAccessionNumber
     * entries to AlternativeAccessionNumberResults
     *
     *
     * @return
     */
    public static List<SpecimenResult> fromList(List<TblSpecimen> tblSpecimens) {
        List<SpecimenResult> specimenResults = new ArrayList<>();

        if (tblSpecimens != null) {
            for (TblSpecimen tblSpecimen : tblSpecimens) {
                specimenResults.add(new SpecimenResult(tblSpecimen));
            }
        }

        return specimenResults;
    }

}
