/*
 * Copyright 2018 fhafner.
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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jacq.common.manager.BaseGatheringManager;
import org.jacq.common.util.ServicesUtil;
import org.jacq.service.JacqServiceConfig;

/**
 *
 * @author fhafner
 */
@ManagedBean
public class GatheringManager extends BaseGatheringManager {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected JacqServiceConfig jacqConfig;

    @PostConstruct
    public void init() {
        this.setEntityManager(em);
        this.setGeoNamesService(ServicesUtil.getGeoNamesService(jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_URL)));
        this.setGeoNamesUsername(jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_USERNAME));
    }
}
