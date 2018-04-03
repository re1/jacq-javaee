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
import org.jacq.common.model.jpa.TblVegetative;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;

/**
 * REST model for handling properties of a vegetative result
 *
 * @author wkoller
 */
public class VegetativeResult extends BotanicalObjectDerivative {

    protected Long vegetativeId;
    protected Date cultivationDate;
    protected boolean indexSeminum;
    protected String annotation;
    protected PhenologyResult phenology;

    public VegetativeResult() {
        this.phenology = new PhenologyResult();
    }

    public VegetativeResult(TblVegetative tblVegetative) {
        if (tblVegetative != null) {
            this.vegetativeId = tblVegetative.getVegetativeId();
            super.setAccessionNumber(String.format("%07d-%03d", tblVegetative.getTblDerivative().getParentDerivativeId().getTblLivingPlant().getAccessionNumber(), tblVegetative.getAccessionNumber()));
            this.cultivationDate = tblVegetative.getCultivationDate();
            this.indexSeminum = tblVegetative.getIndexSeminum();
            this.annotation = tblVegetative.getAnnotation();
            super.setPlaceNumber(tblVegetative.getPlaceNumber());
            super.setSeparated(tblVegetative.getSeparated());
            this.phenology = new PhenologyResult(tblVegetative.getTblDerivative().getBotanicalObjectId().getPhenologyId());
        }
    }

    /**
     * Helper function for converting a list of TblVegetative entries to Vegetative results
     *
     * @param tblVegetativeList
     * @return
     */
    public static List<VegetativeResult> fromList(List<TblVegetative> tblVegetativeList) {
        List<VegetativeResult> vegetativeList = new ArrayList<>();

        if (tblVegetativeList != null) {
            for (TblVegetative tblVegetative : tblVegetativeList) {
                vegetativeList.add(new VegetativeResult(tblVegetative));
            }
        }

        return vegetativeList;
    }

    public Long getVegetativeId() {
        return vegetativeId;
    }

    public void setVegetativeId(Long vegetativeId) {
        this.vegetativeId = vegetativeId;
    }

    public Date getCultivationDate() {
        return cultivationDate;
    }

    public void setCultivationDate(Date cultivationDate) {
        this.cultivationDate = cultivationDate;
    }

    public boolean isIndexSeminum() {
        return indexSeminum;
    }

    public void setIndexSeminum(boolean indexSeminum) {
        this.indexSeminum = indexSeminum;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public PhenologyResult getPhenology() {
        return phenology;
    }

    public void setPhenology(PhenologyResult phenology) {
        this.phenology = phenology;
    }

}
