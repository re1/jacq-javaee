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
import org.jacq.common.model.jpa.TblIdentStatus;

/**
 *
 * @author wkoller
 */
public class IdentStatusResult {

    protected Long identStatusId;
    protected String status;

    public IdentStatusResult() {
    }

    public IdentStatusResult(TblIdentStatus tblIdentStatus) {
        if (tblIdentStatus != null) {
            this.identStatusId = tblIdentStatus.getIdentStatusId();
            this.status = tblIdentStatus.getStatus();
        }
    }

    public Long getIdentStatusId() {
        return identStatusId;
    }

    public void setIdentStatusId(Long identStatusId) {
        this.identStatusId = identStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Helper function for converting a list of TblIdentStatus entries to IdentStatusResult
     *
     * @param tblIdentStatusList
     * @return
     */
    public static List<IdentStatusResult> fromList(List<TblIdentStatus> tblIdentStatusList) {
        List<IdentStatusResult> identStatusResults = new ArrayList<>();

        if (tblIdentStatusList != null) {
            for (TblIdentStatus tblIdentStatus : tblIdentStatusList) {
                identStatusResults.add(new IdentStatusResult(tblIdentStatus));
            }
        }

        return identStatusResults;
    }

}
