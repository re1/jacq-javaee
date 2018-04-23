/*
 * Copyright 2018 wkoller.
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
package org.jacq.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.security.BasicClientRequestFilter;

/**
 * Application wide CDI bean
 *
 * @author wkoller
 */
@ManagedBean
@ApplicationScoped
public class ApplicationManager {

    @Inject
    protected JacqPortalConfig jacqPortalConfig;

    protected OrganisationService organisationService;

    protected HashMap<Long, List<OrganisationResult>> organisationHierachy;

    protected List<OrganisationResult> organisationResult;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        ServicesUtil.registerClientRequestFilter(new BasicClientRequestFilter());
    }

    public JacqPortalConfig getJacqPortalConfig() {
        return jacqPortalConfig;
    }

    public HashMap getOrganisationHierarchicHasMap() {
        this.organisationService = ServicesUtil.getOrganisationService();
        this.organisationResult = ServicesUtil.getOrganisationService().findAll();
        organisationHierachy = new HashMap<>();
        for (OrganisationResult organisation : organisationResult) {
            List<OrganisationResult> organisationResultList = new ArrayList<>();
            if (organisationHierachy.get((organisation.getParentOrganisationId() != null) ? organisation.getParentOrganisationId() : 0L) != null) {
                organisationResultList = organisationHierachy.get((organisation.getParentOrganisationId() != null) ? organisation.getParentOrganisationId() : 0L);
            }
            organisationResultList.add(organisation);
            organisationHierachy.put((organisation.getParentOrganisationId() != null) ? organisation.getParentOrganisationId() : 0L, organisationResultList);
        }
        return organisationHierachy;
    }

}
