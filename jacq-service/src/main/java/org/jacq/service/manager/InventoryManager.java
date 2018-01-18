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
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblDerivative;
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
                    // advanced Invetory organisation and accession in File
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

        // Create Query to get TblInventoryType List
        Query query = em.createNamedQuery("TblInventoryType.findAll");

        // finally fetch the results
        ArrayList<InventoryTypeResult> results = new ArrayList<>();
        List<TblInventoryType> inventoryTypeResults = query.getResultList();
        for (TblInventoryType inventoryType : inventoryTypeResults) {
            InventoryTypeResult inventoryTypeResult = new InventoryTypeResult(inventoryType);

            // add botanical object to result list
            results.add(inventoryTypeResult);
        }

        return results;
    }

    /**
     *
     * @param separated
     * @param tblInventory
     * @param bufferedReader
     * @param organisationId
     * @throws IOException
     */
    @Transactional
    protected void defaultinventory(Boolean separated, TblInventory tblInventory, BufferedReader bufferedReader, Long organisationId) throws IOException {
        // get Organisation
        TblOrganisation organisation = em.find(TblOrganisation.class, organisationId);
        List<Long> livingPlantIdList = new ArrayList<>();
        // get all the livinplant ids by the accessionnumbers in the File
        livingPlantIdList.addAll(accessionNumberToLivingPlantIdList(bufferedReader));
        // when seperated true Livingplants which are not in the list but with the same organisationId get the separated Flag set true
        if (separated) {
            setSeparatedByLivingPlantIdListAndOrganisation(livingPlantIdList, organisation);
        }
        // Change Botanical Object to the new Organisation and create InventoryObjects for each
        setOrganisationInbotanicalObjectsAndCreateTableInventoryObject(livingPlantIdList, organisation, tblInventory);
    }

    /**
     *
     * @param separated
     * @param tblInventory
     * @param bufferedReader
     * @throws IOException
     */
    @Transactional
    protected void advancedinventory(Boolean separated, TblInventory tblInventory, BufferedReader bufferedReader) throws IOException {
        String line;
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

    /**
     * Function for byte[] to Buffered reader
     *
     * @param decodedFile
     * @return
     */
    protected BufferedReader decodeFileToBufferedReader(byte[] decodedFile) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedFile);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    /**
     * Reads line in BufferdReader to acccessionNumberList Gets all LivinPlants
     * by AccessionNumberList return LivinplantId list
     *
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    @Transactional
    protected List<Long> accessionNumberToLivingPlantIdList(BufferedReader bufferedReader) throws IOException {
        String line;
        List<TblLivingPlant> livingPlantList = new ArrayList<>();
        List<Long> accessionNumberList = new ArrayList<>();
        List<Long> livingPlantIdList = new ArrayList<>();

        // Reads Line and adds AccessionNumber from BufferedReader
        while ((line = bufferedReader.readLine()) != null && line.length() <= 8) {
            accessionNumberList.add(Long.parseLong(line));
        }
        // Create and execute Query to get Livinplant List from AccessionNumberList
        Query query = em.createNamedQuery("TblLivingPlant.findByAccessionNumberList").setParameter("accessionNumberList", accessionNumberList);
        livingPlantList.addAll(query.getResultList());
        // LivinPlantList to LivinplantIdList necessary for next query
        for (TblLivingPlant livingPlant : livingPlantList) {
            livingPlantIdList.add(livingPlant.getId());
        }
        return livingPlantIdList;
    }

    /**
     *
     * @param livingPlantIdList
     * @param organisation
     */
    @Transactional
    protected void setSeparatedByLivingPlantIdListAndOrganisation(List<Long> livingPlantIdList, TblOrganisation organisation) {
        List<TblDerivative> derivativeList = new ArrayList<>();
        // Get all BotanicalObjects which have the right Organisation and are not in the AccessionNumberList
        Query query = em.createNamedQuery("TblDerivative.findByNotInTblLivingPlantListAndOrangisation").setParameter("tblLivingPlantList", livingPlantIdList).setParameter("organisationId", organisation);
        derivativeList.addAll(query.getResultList());
        // Set Seperated to True because they have the right organisation but are not in the File
        for (TblDerivative derivative : derivativeList) {
            derivative.getBotanicalObjectId().setSeparated(true);
            em.merge(derivative);
        }
    }

    /**
     *
     * @param livingPlantIdList
     * @param organisation
     * @param tblInventory
     */
    @Transactional
    protected void setOrganisationInbotanicalObjectsAndCreateTableInventoryObject(List<Long> livingPlantIdList, TblOrganisation organisation, TblInventory tblInventory) {
        List<TblDerivative> derivativeList = new ArrayList<>();
        // Get all BotanicalObjects with the accession number from the uploaded File
        Query query = em.createNamedQuery("TblDerivative.findByLivingPlantList").setParameter("tblLivingPlantList", livingPlantIdList);
        derivativeList.addAll(query.getResultList());
        // Change the Organisation to the new Organisation from the File
        for (TblDerivative derivative : derivativeList) {
            //botanicalObject.setOrganisationId(organisation);
            em.merge(derivative);
            // Create tblInventoryObject
            TblInventoryObject tblInventoryObject = new TblInventoryObject();
            tblInventoryObject.setInventoryId(tblInventory);
            tblInventoryObject.setBotanicalObjectId(derivative.getBotanicalObjectId());
            tblInventoryObject.setMessage("TEST");
            em.persist(tblInventoryObject);
        }
    }

}
