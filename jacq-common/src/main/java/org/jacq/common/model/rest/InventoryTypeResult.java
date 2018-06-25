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
import org.jacq.common.model.jpa.TblInventoryType;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryTypeResult {

    protected Long inventoryTypeId;
    protected String type;

    public Long getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(Long inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InventoryTypeResult() {
    }

    public InventoryTypeResult(TblInventoryType inventoryType) {
        this.inventoryTypeId = inventoryType.getInventoryTypeId();
        this.type = inventoryType.getType();
    }

    /**
     * Helper function for converting a list of inventoryType entries to
     * inventoryTypeResult
     *
     * @param inventoryTypeList
     * @return
     */
    public static List<InventoryTypeResult> fromList(List<TblInventoryType> inventoryTypeList) {
        List<InventoryTypeResult> inventoryTypeResult = new ArrayList<>();

        if (inventoryTypeList != null) {
            for (TblInventoryType inventoryType : inventoryTypeList) {
                inventoryTypeResult.add(new InventoryTypeResult(inventoryType));
            }
        }

        return inventoryTypeResult;
    }

}
