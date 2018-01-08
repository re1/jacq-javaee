/*
 * Copyright 2017 wkoller.
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

import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;

/**
 * Model for transfering living plant details over REST interface
 *
 * @author wkoller
 */
public class LivingPlantResult extends BotanicalObjectDerivative {

    protected boolean reviewed;
    protected String ipenType;
    protected String ipenNumber;
    protected boolean ipenLocked;
    protected CultivarResult cultivar;
    protected String gatheringNumber;
    protected String cultureNotes;
    protected long count;

    public LivingPlantResult() {
    }

    public LivingPlantResult(TblLivingPlant tblLivingPlant) {
        // BotanicalObjectDerivative properties
        this.setType(BotanicalObjectDerivative.LIVING);
        this.setDerivativeId(tblLivingPlant.getTblDerivative().getDerivativeId());
        this.setBotanicalObjectId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getId());
        this.setScientificName(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getViewScientificName().getScientificName());
        this.setAccessionNumber(String.format("%07d", tblLivingPlant.getAccessionNumber()));
        this.setLabelAnnotation(tblLivingPlant.getLabelAnnotation());
        this.setOrganisationDescription(tblLivingPlant.getTblDerivative().getOrganisationId().getDescription());
        this.setPlaceNumber(tblLivingPlant.getPlaceNumber());
        this.setDerivativeCount(tblLivingPlant.getTblDerivative().getCount());
        this.setSeparated(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getSeparated());
        this.setScientificNameId(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getScientificNameId());

        // new properties
        this.setReviewed(tblLivingPlant.getReviewed());
        this.setIpenType(tblLivingPlant.getIpenType());
        this.setIpenNumber(tblLivingPlant.getIpenNumber());
        this.setIpenLocked(tblLivingPlant.getIpenLocked());
        this.setCultivar(new CultivarResult(tblLivingPlant.getCultivarId()));
        this.setGatheringNumber(tblLivingPlant.getTblDerivative().getBotanicalObjectId().getAcquisitionEventId().getNumber());
        this.setCultureNotes(tblLivingPlant.getCultureNotes());
        this.setCount(tblLivingPlant.getTblDerivative().getCount());
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getIpenType() {
        return ipenType;
    }

    public void setIpenType(String ipenType) {
        this.ipenType = ipenType;
    }

    public String getIpenNumber() {
        return ipenNumber;
    }

    public void setIpenNumber(String ipenNumber) {
        this.ipenNumber = ipenNumber;
    }

    public boolean isIpenLocked() {
        return ipenLocked;
    }

    public void setIpenLocked(boolean ipenLocked) {
        this.ipenLocked = ipenLocked;
    }

    public CultivarResult getCultivar() {
        return cultivar;
    }

    public void setCultivar(CultivarResult cultivar) {
        this.cultivar = cultivar;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public String getCultureNotes() {
        return cultureNotes;
    }

    public void setCultureNotes(String cultureNotes) {
        this.cultureNotes = cultureNotes;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
