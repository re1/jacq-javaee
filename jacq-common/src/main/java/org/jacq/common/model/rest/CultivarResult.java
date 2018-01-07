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
import org.jacq.common.model.jpa.TblCultivar;

/**
 *
 * @author wkoller
 */
public class CultivarResult {

    protected Long cultivarId;
    protected String cultivar;

    public CultivarResult() {
    }

    public CultivarResult(TblCultivar tblCultivar) {
        if (tblCultivar != null) {
            this.cultivarId = tblCultivar.getCultivarId();
            this.cultivar = tblCultivar.getCultivar();
        }
    }

    public Long getCultivarId() {
        return cultivarId;
    }

    public void setCultivarId(Long cultivarId) {
        this.cultivarId = cultivarId;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    /**
     * Helper function for converting a list of TblCultivar entries to cultivar results
     *
     * @param cultivarList
     * @return
     */
    public static List<CultivarResult> fromList(List<TblCultivar> cultivarList) {
        List<CultivarResult> cultivarResults = new ArrayList<>();

        if (cultivarList != null) {
            for (TblCultivar cultivar : cultivarList) {
                cultivarResults.add(new CultivarResult(cultivar));
            }
        }

        return cultivarResults;
    }
}
