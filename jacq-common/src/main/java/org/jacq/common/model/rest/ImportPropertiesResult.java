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
import org.jacq.common.model.jpa.TblImportProperties;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPropertiesResult {

    protected Long id;
    protected Long iDPflanze;
    protected String speciesName;
    protected String verbreitung;
    protected String sourceName;
    protected Long originalBotanicalObjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getiDPflanze() {
        return iDPflanze;
    }

    public void setiDPflanze(Long iDPflanze) {
        this.iDPflanze = iDPflanze;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getVerbreitung() {
        return verbreitung;
    }

    public void setVerbreitung(String verbreitung) {
        this.verbreitung = verbreitung;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Long getOriginalBotanicalObjectId() {
        return originalBotanicalObjectId;
    }

    public void setOriginalBotanicalObjectId(Long originalBotanicalObjectId) {
        this.originalBotanicalObjectId = originalBotanicalObjectId;
    }

    public ImportPropertiesResult() {
    }

    public ImportPropertiesResult(TblImportProperties importProperties) {
        this.id = importProperties.getId() != null ? importProperties.getId() : null;
        this.iDPflanze = importProperties.getIDPflanze() != null ? importProperties.getIDPflanze() : null;
        this.originalBotanicalObjectId = importProperties.getOriginalBotanicalObjectId() != null ? importProperties.getOriginalBotanicalObjectId() : null;
        this.sourceName = importProperties.getSourceName() != null ? importProperties.getSourceName() : null;
        this.speciesName = importProperties.getSpeciesName() != null ? importProperties.getSpeciesName() : null;
        this.verbreitung = importProperties.getVerbreitung() != null ? importProperties.getVerbreitung() : null;
    }

    /**
     * Helper function for converting a list of importProperties entries
     *
     * @param roleList
     * @return
     */
    public static List<ImportPropertiesResult> fromList(List<TblImportProperties> importPropertiesList) {
        List<ImportPropertiesResult> importPropertiesResult = new ArrayList<>();

        if (importPropertiesList != null) {
            for (TblImportProperties importPropertie : importPropertiesList) {
                importPropertiesResult.add(new ImportPropertiesResult(importPropertie));
            }
        }

        return importPropertiesResult;
    }

}
