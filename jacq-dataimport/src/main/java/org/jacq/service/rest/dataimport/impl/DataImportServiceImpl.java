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
package org.jacq.service.rest.dataimport.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jacq.common.model.dataimport.ImportFile;
import org.jacq.common.rest.dataimport.DataImportService;
import org.jacq.service.dataimport.manager.DataImportManager;

/**
 * @see DataImportService
 * @author wkoller
 */
public class DataImportServiceImpl implements DataImportService {

    private static final Logger LOGGER = Logger.getLogger(DataImportServiceImpl.class.getName());

    @Inject
    protected DataImportManager dataImportManager;

    /**
     * @see
     * DataImportService#dataImport(org.jacq.common.model.dataimport.ImportFile)
     */
    @Override
    public void dataImport(ImportFile importFile) {
        try {
            dataImportManager.dataImport(importFile);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void importBromiMdb() {
        try {
            dataImportManager.importBromiMdb();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
