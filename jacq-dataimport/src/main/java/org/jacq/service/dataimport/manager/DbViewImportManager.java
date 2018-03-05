/*
 * Copyright 2017 wkoller.
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
package org.jacq.service.dataimport.manager;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jacq.common.model.dataimport.ImportRecord;

/**
 * Import data to the system by selecting from a view. The views columns will be mapped to the {@link ImportRecord}
 * class. Thus the columns should be named like the POJO properties.
 *
 * @author wkoller
 */
@ManagedBean
public class DbViewImportManager {

    private static final Logger LOGGER = Logger.getLogger(DbViewImportManager.class.getName());

    protected static final String SQL_SELECT = "SELECT * FROM buga_dataImport";
    protected static final int MAX_RESULTS = 100;

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected DataImportManager dataImportManager;

    public void importView() {
        int currRecord = 0;

        while (true) {
            LOGGER.log(Level.INFO, "Importing entry {0} - {1}", new Object[]{String.valueOf(currRecord), String.valueOf(currRecord + MAX_RESULTS)});

            Query viewQuery = em.createNativeQuery(SQL_SELECT, ImportRecord.class);
            viewQuery.setFirstResult(currRecord);
            viewQuery.setMaxResults(MAX_RESULTS);

            List<ImportRecord> importRecords = viewQuery.getResultList();

            // check if we reached the end of the import queue
            if (importRecords == null || importRecords.size() <= 0) {
                break;
            }

            // run the import for each record
            for (ImportRecord importRecord : importRecords) {
                dataImportManager.importRecord(importRecord);
            }

            currRecord += MAX_RESULTS;
        }
    }
}
