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
import org.jacq.common.model.jpa.TblScientificNameInformation;

/**
 *
 * @author wkoller
 */
public class ScientificNameInformationResult {

    protected Long scientificNameId;
    protected String spatialDistribution;
    protected String commonNames;
    protected HabitusTypeResult habitusTypeId;
    protected List<CultivarResult> cultivarList;

    public ScientificNameInformationResult() {
        this.habitusTypeId = new HabitusTypeResult();
        this.cultivarList = new ArrayList<>();
    }

    public ScientificNameInformationResult(TblScientificNameInformation scientificNameInformation) {
        if (scientificNameInformation != null) {
            this.scientificNameId = scientificNameInformation.getScientificNameId();
            this.spatialDistribution = scientificNameInformation.getSpatialDistribution();
            this.commonNames = scientificNameInformation.getCommonNames();
            this.habitusTypeId = new HabitusTypeResult(scientificNameInformation.getHabitusTypeId());
            this.cultivarList = CultivarResult.fromList(scientificNameInformation.getTblCultivarList());
        }
        else {
            this.habitusTypeId = new HabitusTypeResult();
            this.cultivarList = new ArrayList<>();
        }
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(String spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    public HabitusTypeResult getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(HabitusTypeResult habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    public List<CultivarResult> getCultivarList() {
        return cultivarList;
    }

    public void setCultivarList(List<CultivarResult> cultivarList) {
        this.cultivarList = cultivarList;
    }

}
