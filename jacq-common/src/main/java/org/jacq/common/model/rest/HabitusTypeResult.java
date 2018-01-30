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
import org.jacq.common.model.jpa.TblHabitusType;

/**
 *
 * @author wkoller
 */
public class HabitusTypeResult {

    protected Long habitusTypeId;
    protected String habitus;

    public HabitusTypeResult() {
    }

    public HabitusTypeResult(TblHabitusType habitusType) {
        if (habitusType != null) {
            this.habitusTypeId = habitusType.getHabitusTypeId();
            this.habitus = habitusType.getHabitus();
        }
    }

    public String getHabitus() {
        return habitus;
    }

    public void setHabitus(String habitus) {
        this.habitus = habitus;
    }

    public Long getHabitusTypeId() {
        return habitusTypeId;
    }

    public void setHabitusTypeId(Long habitusTypeId) {
        this.habitusTypeId = habitusTypeId;
    }

    /**
     * Helper function for converting a list of habitus-type entries to habitus type result
     *
     * @param habitusTypeList
     * @return
     */
    public static List<HabitusTypeResult> fromList(List<TblHabitusType> habitusTypeList) {
        List<HabitusTypeResult> habitusTypeResult = new ArrayList<>();

        if (habitusTypeList != null) {
            for (TblHabitusType habitusType : habitusTypeList) {
                habitusTypeResult.add(new HabitusTypeResult(habitusType));
            }
        }

        return habitusTypeResult;
    }
}
