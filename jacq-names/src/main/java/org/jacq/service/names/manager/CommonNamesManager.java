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
package org.jacq.service.names.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jacq.common.model.jpa.openup.TblScientificNameCache;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.OpenRefineInfo;
import org.jacq.common.model.names.OpenRefineResponse;
import org.jacq.service.names.model.NameParserResponse;
import org.jacq.service.names.sources.dnpgoth.DnpGoThSource;
import org.jacq.service.names.sources.util.SourceQueryThread;

/**
 * Handles all common names related actions
 *
 * @author wkoller
 */
@ManagedBean
@RequestScoped
public class CommonNamesManager {

    private static final Logger LOGGER = Logger.getLogger(CommonNamesManager.class.getName());

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    @Resource
    protected ManagedExecutorService executorService;

    @Inject
    protected DnpGoThSource dnpGoThSource;

    @Inject
    protected NameParserManager nameParserManager;

    /**
     * @see CommonNamesService#info()
     */
    public OpenRefineInfo info() {
        OpenRefineInfo openRefineInfo = new OpenRefineInfo();
        openRefineInfo.setName("JACQ Common Names Service");
        openRefineInfo.setIdentifierSpace("http://openup.nhm-wien.ac.at/commonNames/");
        openRefineInfo.setSchemaSpace("http://openup.nhm-wien.ac.at/commonNames/");

        return openRefineInfo;
    }

    /**
     * @see CommonNamesService#query(java.lang.String)
     */
    public OpenRefineResponse<CommonName> query(String query) {
        HashMap<Long, CommonName> resultMap = new HashMap<>();

        // before we start, clean the queried name
        NameParserResponse nameParserResponse = nameParserManager.parseName(query);

        // create the list of common name sources
        ArrayList<Callable<ArrayList<CommonName>>> queryTasks = new ArrayList<>();
        queryTasks.add(new SourceQueryThread(dnpGoThSource, nameParserResponse));

        try {
            // now query all sources and wait for them to finish
            List<Future<ArrayList<CommonName>>> queryResults = executorService.invokeAll(queryTasks);

            for (Future<ArrayList<CommonName>> queryResult : queryResults) {
                try {
                    ArrayList<CommonName> commonNameList;
                    commonNameList = queryResult.get();
                    // merge results into global result map
                    for (CommonName commonName : commonNameList) {
                        // clean the scientific name
                        commonName.setTaxon(nameParserManager.parseName(commonName.getTaxon()).getScientificName());

                        // check if result already exists
                        Long deduplicateHash = commonName.deduplicateHash();
                        if (resultMap.containsKey(deduplicateHash)) {
                            // only update references
                            resultMap.get(deduplicateHash).getReferences().addAll(commonName.getReferences());
                        }
                        else {
                            // add entry to result list
                            resultMap.put(deduplicateHash, commonName);
                        }
                    }
                } catch (ExecutionException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }

        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        // convert resultmap to list
        ArrayList<CommonName> resultList = new ArrayList(resultMap.values());

        // iterate over result and fetch ids resp. create them
        for (CommonName result : resultList) {
            // lookup the scientific name
            TypedQuery<TblScientificNameCache> scientificNameCacheQuery = em.createNamedQuery("TblScientificNameCache.findByName", TblScientificNameCache.class);
            scientificNameCacheQuery.setParameter("name", result.getTaxon());
            List<TblScientificNameCache> scientificNameCaches = scientificNameCacheQuery.getResultList();
            if (scientificNameCaches != null && scientificNameCaches.size() > 0) {
                result.setTaxonId(scientificNameCaches.get(0).getId());
            }
        }

        // prepare open refine response
        OpenRefineResponse<CommonName> openRefineResponse = new OpenRefineResponse();
        openRefineResponse.setResult(resultList);

        return openRefineResponse;
    }
}
