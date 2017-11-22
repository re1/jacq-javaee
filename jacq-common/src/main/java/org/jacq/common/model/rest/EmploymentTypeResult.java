/*
 * Copyright 2017 fhafner.
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
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.FrmwrkEmploymentType;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmploymentTypeResult {

    protected Long employmentTypeId;
    protected String type;

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EmploymentTypeResult() {
    }

    public EmploymentTypeResult(FrmwrkEmploymentType employmentType) {
        this.employmentTypeId = employmentType.getEmploymentTypeId();
        this.type = employmentType.getType().toString();
    }

    /**
     * Helper function for converting a list of User entries to userTypeResult
     *
     * @param employmentTypeList
     * @param userTypeList
     * @return
     */
    public static List<EmploymentTypeResult> fromList(List<FrmwrkEmploymentType> employmentTypeList) {
        List<EmploymentTypeResult> employmentTypeResult = new ArrayList<>();

        if (employmentTypeList != null) {
            for (FrmwrkEmploymentType employmentType : employmentTypeList) {
                employmentTypeResult.add(new EmploymentTypeResult(employmentType));
            }
        }

        return employmentTypeResult;
    }

}
