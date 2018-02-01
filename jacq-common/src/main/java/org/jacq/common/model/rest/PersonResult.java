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
import org.jacq.common.model.jpa.TblPerson;

/**
 *
 * @author wkoller
 */
public class PersonResult {

    protected Long personId;
    protected String name;

    public PersonResult() {
    }

    public PersonResult(TblPerson tblPerson) {
        if (tblPerson != null) {
            this.personId = tblPerson.getId();
            this.name = tblPerson.getName();
        }
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Helper function for converting a list of TblPerson entries to PersonResult
     *
     * @param tblPersonList
     * @return
     */
    public static List<PersonResult> fromList(List<TblPerson> tblPersonList) {
        List<PersonResult> personResults = new ArrayList<>();

        if (tblPersonList != null) {
            for (TblPerson tblPerson : tblPersonList) {
                personResults.add(new PersonResult(tblPerson));
            }
        }

        return personResults;
    }
}
