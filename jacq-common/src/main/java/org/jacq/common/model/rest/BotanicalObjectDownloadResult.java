/*
 * Copyright 2018 fhafner.
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
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BotanicalObjectDownloadResult extends BotanicalObjectDerivative {

    protected String habitat;
    protected String altitudeMin;
    protected String altitudeMax;
    protected String latitude;
    protected String longitude;
    protected String recordedDate;

    public BotanicalObjectDownloadResult() {
    }

    public BotanicalObjectDownloadResult(BotanicalObjectDerivative botanicalObjectDerivative) {
        // BotanicalObjectDerivative properties
        this.setType(botanicalObjectDerivative.getType());
        this.setDerivativeId(botanicalObjectDerivative.getDerivativeId());
        this.setBotanicalObjectId(botanicalObjectDerivative.getBotanicalObjectId());
        this.setScientificName(botanicalObjectDerivative.getScientificName());
        this.setAccessionNumber(botanicalObjectDerivative.getAccessionNumber());
        this.setLabelAnnotation(botanicalObjectDerivative.getLabelAnnotation());
        this.setOrganisationDescription(botanicalObjectDerivative.getOrganisationDescription());
        this.setPlaceNumber(botanicalObjectDerivative.getPlaceNumber());
        this.setDerivativeCount(botanicalObjectDerivative.getDerivativeCount());
        this.setSeparated(botanicalObjectDerivative.getSeparated());
        this.setScientificNameId(botanicalObjectDerivative.getScientificNameId());

    }

    /**
     * Helper function for converting a list of BotanicalObjectDerivative
     * entries to BotanicalObjectDownloadResult
     *
     * @param botanicalObjectDerivativeList
     * @return
     */
    public static List<BotanicalObjectDownloadResult> fromList(List<BotanicalObjectDerivative> botanicalObjectDerivativeList) {
        List<BotanicalObjectDownloadResult> botanicalObjectDownloadResults = new ArrayList<>();

        if (botanicalObjectDerivativeList != null) {
            for (BotanicalObjectDerivative botanicalObjectDerivative : botanicalObjectDerivativeList) {
                botanicalObjectDownloadResults.add(new BotanicalObjectDownloadResult(botanicalObjectDerivative));
            }
        }

        return botanicalObjectDownloadResults;
    }

}
