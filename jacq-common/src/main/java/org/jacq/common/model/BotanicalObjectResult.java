/*
 * Copyright 2016 wkoller.
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
package org.jacq.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblBotanicalObject;

/**
 * Wrapper model which represents a single result after a search Used to minimize the transfered data and only return
 * the relevant information
 *
 * @author wkoller
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BotanicalObjectResult {

    protected Long botanicalObjectId;
    protected List<String> commonNames;
    protected String scientificName;
    protected String organization;
    protected Integer accessionNumber;
    protected List<ImageServerResource> imageServerResources;

    public List<String> getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(List<String> commonNames) {
        this.commonNames = commonNames;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(Integer accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public Long getBotanicalObjectId() {
        return botanicalObjectId;
    }

    public void setBotanicalObjectId(Long botanicalObjectId) {
        this.botanicalObjectId = botanicalObjectId;
    }

    public List<ImageServerResource> getImageServerResources() {
        return imageServerResources;
    }

    public void setImageServerResources(List<ImageServerResource> imageServerResources) {
        this.imageServerResources = imageServerResources;
    }

    public BotanicalObjectResult() {
    }

    public BotanicalObjectResult(TblBotanicalObject botanicalObject) {
        this.scientificName = botanicalObject.getViewScientificName().getScientificName();
        this.organization = botanicalObject.getOrganisationId().getDescription();

        if (botanicalObject.getTblLivingPlant() != null) {
            this.accessionNumber = botanicalObject.getTblLivingPlant().getAccessionNumber();
        }
        this.botanicalObjectId = botanicalObject.getId();
        this.commonNames = new ArrayList<>();
        this.imageServerResources = new ArrayList<>();
    }

    /**
     * Helper function for converting a list of BotanicalObject entries to botanicalobject results
     *
     * @param botanicalObjectList
     * @return
     */
    public static List<BotanicalObjectResult> fromList(List<TblBotanicalObject> botanicalObjectList) {
        List<BotanicalObjectResult> botanicalObjectResults = new ArrayList<>();

        if (botanicalObjectList != null) {
            for (TblBotanicalObject botanicalObject : botanicalObjectList) {
                botanicalObjectResults.add(new BotanicalObjectResult(botanicalObject));
            }
        }

        return botanicalObjectResults;
    }
}
