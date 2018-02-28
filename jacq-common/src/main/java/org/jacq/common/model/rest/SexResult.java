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
import org.jacq.common.model.jpa.TblSex;

/**
 * @see TblSex
 * @author wkoller
 */
public class SexResult {

    protected Long sexId;
    protected String sex;

    public SexResult() {
    }

    public SexResult(Long sexId) {
        this.sexId = sexId;
    }

    public SexResult(TblSex tblSex) {
        if (tblSex != null) {
            this.sexId = tblSex.getId();
            this.sex = tblSex.getSex();
        }
    }

    public Long getSexId() {
        return sexId;
    }

    public void setSexId(Long sexId) {
        this.sexId = sexId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Helper function for converting a list of Sex entries to sex results
     *
     * @param tblSexList
     * @return
     */
    public static List<SexResult> fromList(List<TblSex> tblSexList) {
        List<SexResult> sexResultList = new ArrayList<>();

        if (tblSexList != null) {
            for (TblSex tblSex : tblSexList) {
                sexResultList.add(new SexResult(tblSex));
            }
        }

        return sexResultList;
    }
}
