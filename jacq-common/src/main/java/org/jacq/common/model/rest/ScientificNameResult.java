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
import org.jacq.common.model.jpa.TblNomName;

/**
 *
 * @author wkoller
 */
public class ScientificNameResult {

    protected String scientificName;
    protected Long scientificNameId;

    public ScientificNameResult() {
    }

    public ScientificNameResult(TblNomName tblNomName) {
        this.scientificName = tblNomName.getViewScientificName().getScientificName();
        this.scientificNameId = tblNomName.getNameId();
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    /**
     * Helper function for converting a list of NomName entries to scientific name results
     *
     * @param nomNameList
     * @return
     */
    public static List<ScientificNameResult> fromList(List<TblNomName> nomNameList) {
        List<ScientificNameResult> scientificNameResults = new ArrayList<>();

        if (nomNameList != null) {
            for (TblNomName nomName : nomNameList) {
                scientificNameResults.add(new ScientificNameResult(nomName));
            }
        }

        return scientificNameResults;
    }
}
