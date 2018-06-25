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
import java.util.Date;
import java.util.List;
import org.jacq.common.model.jpa.TblAcquisitionEventSource;

/**
 *
 * @author wkoller
 */
public class AcquistionEventSourceResult {

    private Long acquisitionEventSourceId;
    private Date sourceDate;
    private AcquisitionSourceResult acquisitionEventSource;

    public AcquistionEventSourceResult() {
        this.acquisitionEventSource = new AcquisitionSourceResult();
    }

    public AcquistionEventSourceResult(TblAcquisitionEventSource tblAcquisitionEventSource) {
        if (tblAcquisitionEventSource != null) {
            this.acquisitionEventSourceId = tblAcquisitionEventSource.getAcquisitionEventSourceId();
            this.sourceDate = tblAcquisitionEventSource.getSourceDate();
            this.acquisitionEventSource = new AcquisitionSourceResult(tblAcquisitionEventSource.getAcquisitionSourceId());
        }
    }

    public Long getAcquisitionEventSourceId() {
        return acquisitionEventSourceId;
    }

    public void setAcquisitionEventSourceId(Long acquisitionEventSourceId) {
        this.acquisitionEventSourceId = acquisitionEventSourceId;
    }

    public Date getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(Date sourceDate) {
        this.sourceDate = sourceDate;
    }

    public AcquisitionSourceResult getAcquisitionEventSource() {
        return acquisitionEventSource;
    }

    public void setAcquisitionEventSource(AcquisitionSourceResult acquisitionEventSource) {
        this.acquisitionEventSource = acquisitionEventSource;
    }

    /**
     * Helper function for converting a list of TblAcquisitionEventSource entries to AcquistionEventSourceResult
     *
     * @param tblAcquisitionEventSources
     * @return
     */
    public static List<AcquistionEventSourceResult> fromList(List<TblAcquisitionEventSource> tblAcquisitionEventSources) {
        List<AcquistionEventSourceResult> acquistionEventSourceResults = new ArrayList<>();

        if (tblAcquisitionEventSources != null) {
            for (TblAcquisitionEventSource tblAcquisitionEventSource : tblAcquisitionEventSources) {
                acquistionEventSourceResults.add(new AcquistionEventSourceResult(tblAcquisitionEventSource));
            }
        }

        return acquistionEventSourceResults;
    }
}
