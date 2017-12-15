/*
 * Copyright 2017 fhafner.
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
import org.jacq.common.model.jpa.TblInventory;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryResult {

    protected Long inventoryId;
    protected Long userId;
    protected Long inventoryTypeId;
    protected String fileContent;
    protected Boolean separated;
    protected Long organisationId;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(Long inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Boolean getSeparated() {
        return separated;
    }

    public void setSeparated(Boolean separated) {
        this.separated = separated;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public InventoryResult() {
    }

    public InventoryResult(TblInventory inventory) {
        this.inventoryId = inventory.getInventoryId();
        this.userId = inventory.getUserId().getId();
        this.inventoryTypeId = inventory.getInventoryTypeId().getInventoryTypeId();
    }

    /**
     * Helper function for converting a list of TblInventory entries to
     * InventoryResult
     *
     * @param inventoryList
     * @return
     */
    public static List<InventoryResult> fromList(List<TblInventory> inventoryList) {
        List<InventoryResult> inventoryResult = new ArrayList<>();

        if (inventoryList != null) {
            for (TblInventory inventory : inventoryList) {
                inventoryResult.add(new InventoryResult(inventory));
            }
        }

        return inventoryResult;
    }

}
