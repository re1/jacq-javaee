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

import org.jacq.common.manager.NameParserManager;
import org.jacq.common.model.jpa.openup.TblCommonNamesCache;
import org.jacq.common.model.jpa.openup.TblScientificNameCache;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.OpenRefineInfo;
import org.jacq.common.rest.names.CommonNameService;
import org.jacq.service.names.sources.*;
import org.jacq.service.names.sources.util.SourceQueryThread;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles all common names related actions
 *
 * @author wkoller
 */
@ManagedBean
@RequestScoped
@Transactional
public class CommonNameManager {

    private static final Logger LOGGER = Logger.getLogger(CommonNameManager.class.getName());

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    @Resource
    protected ManagedExecutorService executorService;

    @Inject
    protected AllearterDKSource allearterDKSource;

    @Inject
    protected CatalogueOfLifeSource catalogueOfLifeSource;

    @Inject
    protected CzechJiriSource czechJiriSource;

    @Inject
    protected CzechPragueSource czechPragueSource;

    @Inject
    protected EtiDatabasesSource etiDatabasesSource;

    @Inject
    protected HebrewLindaSource hebrewLindaSource;

    @Inject
    protected DnpGoThSource dnpGoThSource;

    @Inject
    protected JacqLegacySource jacqLegacySource;

    @Inject
    protected LinnaeusProjectsSource linnaeusProjectsSource;

    @Inject
    protected MeertensKnawSource meertensKnawSource;

    @Inject
    protected PESISource pesiSource;

    @Inject
    protected YListSource yListSource;

    @Inject
    protected NameParserManager nameParserManager;

    /**
     * @see CommonNameService#query
     */
    public OpenRefineInfo info() {
        OpenRefineInfo openRefineInfo = new OpenRefineInfo();
        openRefineInfo.setName("JACQ Common Names Service");
        openRefineInfo.setIdentifierSpace("http://openup.nhm-wien.ac.at/commonNames/");
        openRefineInfo.setSchemaSpace("http://openup.nhm-wien.ac.at/commonNames/");
        openRefineInfo.addDefaultType("/name/common", "Common Name");

        return openRefineInfo;
    }

    /**
     * @see CommonNameService#query
     */
    @Transactional
    public ArrayList<CommonName> query(String query) {
        HashMap<Long, CommonName> resultMap = new HashMap<>();

        // parse the given scientific name
        NameParserResponse nameParserResponse = nameParserManager.parseName(query);

        // create a list of common name sources before executing any queries
        List<CommonNamesSource> commonNamesSources = Arrays.asList(
                allearterDKSource,
                catalogueOfLifeSource,
                czechJiriSource,
                czechPragueSource,
                dnpGoThSource,
                etiDatabasesSource,
                hebrewLindaSource,
                jacqLegacySource,
                linnaeusProjectsSource,
                // MeertensKnawSource is currently unavailable due to https://github.com/re1/jacq-javaee/issues/14
                // meertensKnawSource,
                pesiSource,
                yListSource
        );

        ArrayList<Callable<ArrayList<CommonName>>> queryTasks = new ArrayList<>();
        for (CommonNamesSource commonNamesSource : commonNamesSources) {
            queryTasks.add(new SourceQueryThread(commonNamesSource, nameParserResponse));
        }

        try {
            // query all sources asynchronously and wait for them to finish
            List<Future<ArrayList<CommonName>>> queryResults = executorService.invokeAll(queryTasks);
            // iterate over query results after a query is completed
            for (Future<ArrayList<CommonName>> queryResult : queryResults) {
                try {
                    ArrayList<CommonName> commonNameList = queryResult.get();
                    // merge results into global result map
                    for (CommonName commonName : commonNameList) {
                        // clean the scientific name
                        commonName.setTaxon(nameParserManager.parseName(commonName.getTaxon()).getScientificName());

                        // update score and match if one of them is not set
                        if (commonName.getMatch() == null || commonName.getScore() == null) {
                            // check if common name taxon is a direct match to its scientific name
                            if (commonName.getTaxon().equalsIgnoreCase(nameParserResponse.getScientificName())) {
                                commonName.setMatch(Boolean.TRUE);
                                commonName.setScore(100L);
                            } else {
                                commonName.setMatch(Boolean.FALSE);
                                commonName.setScore(0L);
                            }
                        }

                        // check if result already exists
                        Long deduplicateHash = commonName.deduplicateHash();
                        if (resultMap.containsKey(deduplicateHash)) {
                            // only update references
                            resultMap.get(deduplicateHash).getReferences().addAll(commonName.getReferences());
                        } else {
                            // add entry to result list
                            resultMap.put(deduplicateHash, commonName);
                        }
                    }
                } catch (ExecutionException e) {
                    LOGGER.log(Level.SEVERE, null, e);
                }
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        // convert result map to list
        ArrayList<CommonName> resultList = new ArrayList<>(resultMap.values());

        // iterate over results and fetch ids respectively or create them
        for (CommonName result : resultList) {
            // lookup scientific name from cache
            TblScientificNameCache scientificNameCache;
            TypedQuery<TblScientificNameCache> scientificNameCacheQuery = em.createNamedQuery("TblScientificNameCache.findByName", TblScientificNameCache.class);
            scientificNameCacheQuery.setParameter("name", result.getTaxon());
            List<TblScientificNameCache> scientificNameCaches = scientificNameCacheQuery.getResultList();

            if (scientificNameCaches != null && scientificNameCaches.size() > 0) {
                // use scientific name from cache
                scientificNameCache = scientificNameCaches.get(0);
            } else {
                // add scientific name to cache
                scientificNameCache = new TblScientificNameCache();
                scientificNameCache.setName(result.getTaxon());
                em.persist(scientificNameCache);
            }
            // set id of scientific name in our result
            result.setTaxonId(scientificNameCache.getId());

            // lookup common names from cache
            TblCommonNamesCache commonNamesCache;

            // we use a string building query here for performance reasons - should normally be avoided at any cost!
            String lookupQuery = "SELECT cnc FROM TblCommonNamesCache cnc WHERE cnc.name = :name AND "
                    + queryFieldHelper("language", result.getLanguage()) + " AND "
                    + queryFieldHelper("geography", result.getGeography()) + " AND "
                    + queryFieldHelper("period", result.getPeriod());

            // create query and fetch the result
            TypedQuery<TblCommonNamesCache> commonNamesCacheQuery = em.createQuery(
                    lookupQuery, TblCommonNamesCache.class).setParameter("name", result.getName());
            List<TblCommonNamesCache> commonNamesCaches = commonNamesCacheQuery.getResultList();

            if (commonNamesCaches != null && commonNamesCaches.size() > 0) {
                // use common names from cache
                commonNamesCache = commonNamesCaches.get(0);
            } else {
                // add common names to cache
                commonNamesCache = new TblCommonNamesCache();
                commonNamesCache.setName(result.getName());
                commonNamesCache.setLanguage(result.getLanguage());
                commonNamesCache.setGeography(result.getGeography());
                commonNamesCache.setPeriod(result.getPeriod());
                em.persist(commonNamesCache);
            }
            // set id of common names in our result
            result.setId(commonNamesCache.getId());
        }

        return resultList;
    }

    public CommonName getById(String id) {
        // we use a string building query here for performance reasons - should normally be avoided at any cost!
        String lookupQuery = "SELECT cnc FROM TblCommonNamesCache cnc WHERE cnc.id = '" + id + "'";
        // create query and fetch the result
        TypedQuery<TblCommonNamesCache> commonNamesCacheQuery = em.createQuery(lookupQuery, TblCommonNamesCache.class);
        List<TblCommonNamesCache> commonNamesCaches = commonNamesCacheQuery.getResultList();
        // check if common name was found
        if (commonNamesCaches != null && !commonNamesCaches.isEmpty()) {
            // get first entry as there will not be more than one
            TblCommonNamesCache commonNamesCache = commonNamesCaches.get(0);
            // create common name and set values from cache
            CommonName commonName = new CommonName();

            commonName.setId(commonNamesCache.getId());
            commonName.setName(commonNamesCache.getName());
            commonName.setPeriod(commonNamesCache.getPeriod());
            commonName.setGeography(commonNamesCache.getGeography());
            commonName.setLanguage(commonNamesCache.getLanguage());

            return commonName;
        }

        return null;
    }

    /**
     * Small helper function for string building a null / value query
     *
     * @param field key to set value for
     * @param value content for the given field
     * @return String of "field='value'" or "field IS NULL"
     */
    protected String queryFieldHelper(String field, String value) {
        return value == null ? field + " IS NULL" : field + " = '" + value + "'";
    }
}
