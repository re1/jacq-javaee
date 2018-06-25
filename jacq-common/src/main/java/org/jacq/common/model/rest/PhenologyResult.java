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
import org.jacq.common.model.jpa.TblPhenology;

/**
 *
 * @author wkoller
 */
public class PhenologyResult {

    private Long phenologyId;
    private String phenology;

    public PhenologyResult() {
    }

    public PhenologyResult(TblPhenology tblPhenology) {
        if (tblPhenology != null) {
            this.phenologyId = tblPhenology.getId();
            this.phenology = tblPhenology.getPhenology();
        }
    }

    public Long getPhenologyId() {
        return phenologyId;
    }

    public void setPhenologyId(Long phenologyId) {
        this.phenologyId = phenologyId;
    }

    public String getPhenology() {
        return phenology;
    }

    public void setPhenology(String phenology) {
        this.phenology = phenology;
    }

    /**
     * Helper function for converting a list of phenology entries to phenology
     * results
     *
     * @param phenologyList
     * @return
     */
    public static List<PhenologyResult> fromList(List<TblPhenology> phenologyList) {
        List<PhenologyResult> phenologyResult = new ArrayList<>();

        if (phenologyList != null) {
            for (TblPhenology phenologyType : phenologyList) {
                phenologyResult.add(new PhenologyResult(phenologyType));
            }
        }

        return phenologyResult;
    }
}
