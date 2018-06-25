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
package org.jacq.input.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.common.model.rest.InventoryResult;
import org.jacq.common.model.rest.InventoryTypeResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.rest.InventoryService;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.util.ServicesUtil;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class InventoryEditController {

    @Inject
    protected SessionController sessionController;

    private static final Logger LOGGER = Logger.getLogger(TreeRecordFileEditController.class.getName());

    protected List<InventoryTypeResult> inventoryTypes;

    protected InventoryResult inventory;

    protected InventoryService inventoryService;

    protected OrganisationService organisationService;

    protected List<OrganisationResult> organisations;

    protected Boolean separated;

    protected UploadedFile file;

    @PostConstruct
    public void init() {
        this.inventoryService = ServicesUtil.getInventoryService();

        this.inventoryTypes = this.inventoryService.findAllInventoryType();

        this.inventory = new InventoryResult();

        this.organisationService = ServicesUtil.getOrganisationService();

        this.organisations = this.organisationService.findAll();

    }

    public String edit() {
        try {
            this.inventory.setFileContent(new String(Base64.getEncoder().encode(this.file.getContents()), "ASCII"));
            this.inventory.setSeparated(separated);
            this.inventory = this.inventoryService.save(this.inventory);

        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<InventoryTypeResult> getInventoryTypes() {
        return inventoryTypes;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Boolean getSeparated() {
        return separated;
    }

    public void setSeparated(Boolean separated) {
        this.separated = separated;
    }

    public InventoryResult getInventory() {
        return inventory;
    }

    public List<OrganisationResult> getOrganisations() {
        return organisations;
    }

    public void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

}
