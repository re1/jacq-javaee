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
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblInventory;
import org.jacq.common.model.jpa.TblInventoryType;
import org.jacq.common.model.rest.InventoryResult;
import org.jacq.common.model.rest.InventoryTypeResult;
import org.jacq.common.rest.InventoryService;

/**
 *
 * @author fhafner
 */
public class InventoryManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @param inventoryResult
     * @see InventoryService
     */
    @Transactional
    public InventoryResult save(InventoryResult inventoryResult) {
        String inventoryTyp;

        TblInventory tblInventory = new TblInventory();
        tblInventory.setInventoryTypeId(em.find(TblInventoryType.class, inventoryResult.getInventoryTypeId()));
        tblInventory.setUserId(em.find(FrmwrkUser.class, 1L));
        em.persist(tblInventory);
        inventoryTyp = tblInventory.getInventoryTypeId().getType();

        switch (inventoryTyp) {
            case "inventory":
                defaultinventory(inventoryResult);
                break;
            case "advancedinventory":
                advancedinventory(inventoryResult);
                break;
            default:

                break;
        }

        return new InventoryResult(tblInventory);
    }

    /**
     *
     * @see InventoryService
     * @return
     */
    @Transactional
    public List<InventoryTypeResult> findAllInventoryType() {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblInventoryType> cq = cb.createQuery(TblInventoryType.class);
        Root<TblInventoryType> bo = cq.from(TblInventoryType.class);

        // select result list
        cq.select(bo);

        // convert to typed query and apply offset / limit
        TypedQuery<TblInventoryType> inventoryTypeSearchQuery = em.createQuery(cq);

        // finally fetch the results
        ArrayList<InventoryTypeResult> results = new ArrayList<>();
        List<TblInventoryType> inventoryTypeResults = inventoryTypeSearchQuery.getResultList();
        for (TblInventoryType inventoryType : inventoryTypeResults) {
            InventoryTypeResult inventoryTypeResult = new InventoryTypeResult(inventoryType);

            // add botanical object to result list
            results.add(inventoryTypeResult);
        }

        return results;
    }

    private void defaultinventory(InventoryResult inventoryResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void advancedinventory(InventoryResult inventoryResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
