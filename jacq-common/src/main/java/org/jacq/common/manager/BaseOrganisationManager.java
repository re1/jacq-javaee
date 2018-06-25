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
package org.jacq.common.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.rest.OrganisationService;

/**
 * Contains organisation manager functionality which is used across different services
 *
 * @author wkoller
 */
public class BaseOrganisationManager {

    protected EntityManager entityManager;

    /**
     * @see OrganisationService#search()
     */
    @Transactional
    public List<OrganisationResult> search(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener, Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblOrganisation> cq = cb.createQuery(TblOrganisation.class);
        Root<TblOrganisation> bo = cq.from(TblOrganisation.class);

        // select result list
        cq.select(bo);

        // apply search criteria
        applySearchCriteria(cb, cq, bo, organisationId, description, department, greenhouse, ipenCode, parentOrganisationDescription, gardener);

        // convert to typed query and apply offset / limit
        TypedQuery<TblOrganisation> organisationSearchQuery = entityManager.createQuery(cq);
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
     * @see OrganisationService#load(java.lang.Long)
     */
    @Transactional
    public OrganisationResult load(Long organisationId) {
        TblOrganisation tblOrganisation = entityManager.find(TblOrganisation.class, organisationId);
        if (tblOrganisation != null) {
            return new OrganisationResult(tblOrganisation);
        }

        return null;
    }

    /**
     * Helper function for applying the search criteria for counting / selecting
     *
     * @param parentOrganisationDescription
     * @see BaseOrganisationManager#search(java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.String, java.lang.Integer, java.lang.Integer)
     * @see BaseOrganisationManager#searchCount(java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.String)
     *
     * @param cb
     * @param cq
     * @param bo
     * @param organisationId
     * @param description
     * @param department
     * @param greenhouse
     * @param ipenCode
     */
    protected void applySearchCriteria(CriteriaBuilder cb, CriteriaQuery cq, Root<TblOrganisation> bo, Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        if (organisationId != null) {
            path = bo.get("id");
            predicates.add(cb.equal(path, organisationId));
        }

        if (description != null) {
            path = bo.get("description");
            predicates.add(cb.like(path, description + "%"));
        }

        if (department != null) {
            path = bo.get("department");
            predicates.add(cb.like(path, department + "%"));
        }

        if (greenhouse != null) {
            path = bo.get("greenhouse");
            predicates.add(cb.equal(path, greenhouse));
        }

        if (ipenCode != null) {
            path = bo.get("ipenCode");
            predicates.add(cb.like(path, ipenCode + "%"));
        }

        if (parentOrganisationDescription != null) {
            Join<TblOrganisation, TblOrganisation> tblOrganisation = bo.join("parentOrganisationId", JoinType.LEFT);
            path = tblOrganisation.get("description");
            predicates.add(cb.like(path, parentOrganisationDescription + "%"));
        }

        if (gardener != null) {
            Join<TblOrganisation, FrmwrkUser> frmwrkUser = bo.join("gardenerId", JoinType.LEFT);
            path = frmwrkUser.get("username");
            predicates.add(cb.like(path, gardener + "%"));
        }

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));
    }
}
