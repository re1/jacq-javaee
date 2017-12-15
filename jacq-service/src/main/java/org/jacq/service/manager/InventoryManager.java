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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblInventory;
import org.jacq.common.model.jpa.TblInventoryObject;
import org.jacq.common.model.jpa.TblInventoryType;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblOrganisation;
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
            case "inventory": {
                try {
                    defaultinventory(inventoryResult, tblInventory);
                } catch (IOException ex) {
                    Logger.getLogger(InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    private void defaultinventory(InventoryResult inventoryResult, TblInventory tblInventory) throws IOException {
        String accessionNumber = "";
        TblOrganisation organisation = em.find(TblOrganisation.class, inventoryResult.getOrganisationId());
        byte[] decodedPdf = Base64.getDecoder().decode(inventoryResult.getFileContent());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedPdf);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        List<TblLivingPlant> livingPlantList = new ArrayList<>();
        List<Long> livingPlantIdList = new ArrayList<>();
        while ((accessionNumber = bufferedReader.readLine()) != null) {
            Query query = em.createNamedQuery("TblLivingPlant.findByAccessionNumber").setParameter("accessionNumber", Integer.parseInt(accessionNumber));
            livingPlantList.addAll(query.getResultList());
            for (TblLivingPlant livingPlant : livingPlantList) {
                livingPlantIdList.add(livingPlant.getId());
            }
        }
        if (inventoryResult.getSeparated()) {
            Query query = em.createNamedQuery("TblBotanicalObject.findByNotIntblLivingPlantListAndOrangisation").setParameter("tblLivingPlantList", livingPlantIdList).setParameter("organisationId", organisation);
            List<TblBotanicalObject> botanicalObjects = new ArrayList<>();
            botanicalObjects.addAll(query.getResultList());
            for (TblBotanicalObject botanicalObject : botanicalObjects) {
                botanicalObject.setSeparated(inventoryResult.getSeparated());
                em.merge(botanicalObject);
            }
        }
        Query query = em.createNamedQuery("TblBotanicalObject.findByLivingPlantList").setParameter("tblLivingPlantList", livingPlantIdList);
        List<TblBotanicalObject> botanicalObjects = new ArrayList<>();
        botanicalObjects.addAll(query.getResultList());
        for (TblBotanicalObject botanicalObject : botanicalObjects) {
            botanicalObject.setOrganisationId(organisation);
            em.merge(botanicalObject);
            TblInventoryObject tblInventoryObject = new TblInventoryObject();
            tblInventoryObject.setInventoryId(tblInventory);
            tblInventoryObject.setBotanicalObjectId(botanicalObject);
            em.persist(tblInventoryObject);
        }

    }

    private void advancedinventory(InventoryResult inventoryResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
