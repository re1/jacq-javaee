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
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.OpenRefineInfo;
import org.jacq.common.model.names.OpenRefineResponse;
import org.jacq.service.names.sources.dnpgoth.DnpGoThSource;

/**
 * Handles all common names related actions
 *
 * @author wkoller
 */
@ManagedBean
@RequestScoped
public class CommonNamesManager {

    @PersistenceContext
    protected EntityManager em;

    @Resource
    protected ManagedExecutorService executorService;

    @Inject
    protected DnpGoThSource dnpGoThSource;

    @Inject
    protected NameParserManager nameParserManager;

    /**
     * HashMap for storing the result of all queries
     */
    protected ConcurrentHashMap<Long, CommonName> result = new ConcurrentHashMap<>();

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
        nameParserManager.parseName(query);

        OpenRefineResponse openRefineResponse = new OpenRefineResponse();

        openRefineResponse.setResult(dnpGoThSource.query(query));

        return openRefineResponse;
    }
}
