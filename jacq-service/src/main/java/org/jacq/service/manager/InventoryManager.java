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

    private static final Logger LOGGER = Logger.getLogger(TreeRecordFileManager.class.getName());

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @param inventoryResult
     * @see InventoryService
     */
    @Transactional
    public InventoryResult save(InventoryResult inventoryResult) {
        String inventoryTyp;

        //Create inventory Table entry
        TblInventory tblInventory = new TblInventory();
        tblInventory.setInventoryTypeId(em.find(TblInventoryType.class, inventoryResult.getInventoryTypeId()));
        tblInventory.setUserId(em.find(FrmwrkUser.class, 1L));
        em.persist(tblInventory);

        //get inventorytyp
        inventoryTyp = tblInventory.getInventoryTypeId().getType();

        //Get BufferedReader from File in InventoryResult
        BufferedReader bufferedReader = decodeFileToBufferedReader(Base64.getDecoder().decode(inventoryResult.getFileContent()));

        switch (inventoryTyp) {
            case "inventory": {
                try {
                    //default inventory organisation in Result and only accession number in File
                    defaultinventory(inventoryResult.getSeparated(), tblInventory, bufferedReader, inventoryResult.getOrganisationId());
                } catch (IOException ex) {
                    LOGGER.getLogger(InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "advancedinventory": {
                try {
                    advancedinventory(inventoryResult.getSeparated(), tblInventory, bufferedReader);
                } catch (IOException ex) {
                    LOGGER.getLogger(InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    protected void defaultinventory(Boolean separated, TblInventory tblInventory, BufferedReader bufferedReader, Long organisationId) throws IOException {
        TblOrganisation organisation = em.find(TblOrganisation.class, organisationId);
        List<Long> livingPlantIdList = new ArrayList<>();
        livingPlantIdList.addAll(accessionNumberToLivingPlantIdList(bufferedReader));
        if (separated) {
            setSeparatedByLivingPlantIdListAndOrganisation(livingPlantIdList, organisation);
        }
        setOrganisationInbotanicalObjectsAndCreateTableInventoryObject(livingPlantIdList, organisation, tblInventory);
    }

    protected void advancedinventory(Boolean separated, TblInventory tblInventory, BufferedReader bufferedReader) throws IOException {
        String line = "";
        List<Long> livingPlantIdList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            TblOrganisation organisation = null;
            if (line.length() > 8) {
                Long organisationId = Long.parseLong(line);
                organisation = em.find(TblOrganisation.class, organisationId);
            }
            livingPlantIdList.addAll(accessionNumberToLivingPlantIdList(bufferedReader));
            if (separated) {
                setSeparatedByLivingPlantIdListAndOrganisation(livingPlantIdList, organisation);
            }
            setOrganisationInbotanicalObjectsAndCreateTableInventoryObject(livingPlantIdList, organisation, tblInventory);
        }
    }

    protected BufferedReader decodeFileToBufferedReader(byte[] decodedFile) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedFile);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    protected List<Long> accessionNumberToLivingPlantIdList(BufferedReader bufferedReader) throws IOException {
        String line = "";
        List<TblLivingPlant> livingPlantList = new ArrayList<>();
        List<Integer> accessionNumberList = new ArrayList<>();
        List<Long> livingPlantIdList = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null && line.length() <= 8) {
            accessionNumberList.add(Integer.parseInt(line));
        }
        Query query = em.createNamedQuery("TblLivingPlant.findByAccessionNumberList").setParameter("accessionNumberList", accessionNumberList);
        livingPlantList.addAll(query.getResultList());
        for (TblLivingPlant livingPlant : livingPlantList) {
            livingPlantIdList.add(livingPlant.getId());
        }
        return livingPlantIdList;
    }

    protected void setSeparatedByLivingPlantIdListAndOrganisation(List<Long> livingPlantIdList, TblOrganisation organisation) {
        Query query = em.createNamedQuery("TblBotanicalObject.findByNotInTblLivingPlantListAndOrangisation").setParameter("tblLivingPlantList", livingPlantIdList).setParameter("organisationId", organisation);
        List<TblBotanicalObject> botanicalObjects = new ArrayList<>();
        botanicalObjects.addAll(query.getResultList());
        for (TblBotanicalObject botanicalObject : botanicalObjects) {
            botanicalObject.setSeparated(true);
            em.merge(botanicalObject);
        }
    }

    protected void setOrganisationInbotanicalObjectsAndCreateTableInventoryObject(List<Long> livingPlantIdList, TblOrganisation organisation, TblInventory tblInventory) {
        Query query = em.createNamedQuery("TblBotanicalObject.findByLivingPlantList").setParameter("tblLivingPlantList", livingPlantIdList);
        List<TblBotanicalObject> botanicalObjects = new ArrayList<>();
        botanicalObjects.addAll(query.getResultList());
        for (TblBotanicalObject botanicalObject : botanicalObjects) {
            botanicalObject.setOrganisationId(organisation);
            em.merge(botanicalObject);
            TblInventoryObject tblInventoryObject = new TblInventoryObject();
            tblInventoryObject.setInventoryId(tblInventory);
            tblInventoryObject.setBotanicalObjectId(botanicalObject);
            tblInventoryObject.setMessage("TEST");
            em.persist(tblInventoryObject);
        }
    }

}
