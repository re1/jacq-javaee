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
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.OrganisationResult;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.rest.OrganisationService;

/**
 *
 * @author fhafner
 */
public class OrganisationManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @see OrganisationService#search()
     */
    @Transactional
    public List<OrganisationResult> search(Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblOrganisation> cq = cb.createQuery(TblOrganisation.class);
        Root<TblOrganisation> bo = cq.from(TblOrganisation.class);

        // select result list
        cq.select(bo);

        // convert to typed query and apply offset / limit
        TypedQuery<TblOrganisation> organisationSearchQuery = em.createQuery(cq);
        if (offset != null) {
            organisationSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            organisationSearchQuery.setMaxResults(limit);
        }

        // finally fetch the results
        ArrayList<OrganisationResult> results = new ArrayList<>();
        List<TblOrganisation> organisationResults = organisationSearchQuery.getResultList();
        for (TblOrganisation organisation : organisationResults) {
            OrganisationResult organisationResult = new OrganisationResult(organisation);

            // add botanical object to result list
            results.add(organisationResult);
        }

        return results;
    }

    /**
     * @see OrganisationService#searchCount()
     */
    @Transactional
    public int searchCount() {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TblOrganisation> bo = cq.from(TblOrganisation.class);

        // count result
        cq.select(cb.count(bo));

        // run query and return count
        return em.createQuery(cq).getSingleResult().intValue();
    }
}
