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
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.manager.BaseOrganisationManager;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.rest.OrganisationService;
import org.jacq.service.ApplicationManager;

/**
 *
 * @author fhafner
 */
public class OrganisationManager extends BaseOrganisationManager {

    @Inject
    protected ApplicationManager applicationManager;

    @PersistenceContext(unitName = "jacq-service")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @see OrganisationService#searchCount(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String,
     * java.lang.String)
     * @param organisationId
     * @param description
     * @param department
     * @param greenhouse
     * @param ipenCode
     * @param parentOrganisationDescription
     * @param gardener
     * @return
     */
    @Transactional
    public int searchCount(Long organisationId, String description, String department, Boolean greenhouse, String ipenCode, String parentOrganisationDescription, String gardener) {
        // prepare criteria builder & query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TblOrganisation> bo = cq.from(TblOrganisation.class);

        // count result
        cq.select(cb.count(bo));

        // apply search criteria
        applySearchCriteria(cb, cq, bo, organisationId, description, department, greenhouse, ipenCode, parentOrganisationDescription, gardener);

        // run query and return count
        return entityManager.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * @see
     * OrganisationService#save(org.jacq.common.model.rest.OrganisationResult)
     * @param organisationResult
     * @return
     */
    @Transactional
    public OrganisationResult save(OrganisationResult organisationResult) {
        TblOrganisation tblOrganisation = null;
        // Check if id then load from db else create new
        if (organisationResult.getOrganisationId() != null) {
            tblOrganisation = entityManager.find(TblOrganisation.class, organisationResult.getOrganisationId());
        } else {
            tblOrganisation = new TblOrganisation();
        }
        if (tblOrganisation != null) {
            // Set TblOrganisation
            tblOrganisation.setDescription(organisationResult.getDescription());
            tblOrganisation.setDepartment(organisationResult.getDepartment());
            tblOrganisation.setGreenhouse(organisationResult.getGreenhouse());
            tblOrganisation.setIpenCode(organisationResult.getIpenCode());
            tblOrganisation.setParentOrganisationId(entityManager.find(TblOrganisation.class, organisationResult.getParentOrganisationResult().getOrganisationId()));
            tblOrganisation.setGardenerId(entityManager.find(FrmwrkUser.class, organisationResult.getGardenerId()));
            tblOrganisation.setIndexSeminumStart(organisationResult.isIndexSeminumStart());
            tblOrganisation.setAccessionStart(organisationResult.isAccessionStart());

            // When id then merge else persist
            if (tblOrganisation.getId() != null) {
                entityManager.merge(tblOrganisation);
            } else {
                entityManager.persist(tblOrganisation);
            }
            // New Organisation is added so the cache has to be cleared
            this.applicationManager.clearOrganisationHierachyCache();
            return new OrganisationResult(tblOrganisation);
        }
        return null;
    }

    /**
     * @see OrganisationService#getIpenCode(java.lang.Long)
     * @param organisationId
     * @return
     */
    @Transactional
    public String getIpenCode(Long organisationId) {
        TblOrganisation tblOrganisation = entityManager.find(TblOrganisation.class, organisationId);
        // go up the hierarchy until we hit the top or we find a valid ipen-code
        while (tblOrganisation != null && StringUtils.isEmpty(tblOrganisation.getIpenCode())) {
            tblOrganisation = tblOrganisation.getParentOrganisationId();
        }

        // found a matching organisation? if yes return the ipen code
        if (tblOrganisation != null) {
            return tblOrganisation.getIpenCode();
        }

        return null;
    }

    /**
     * Helper function for applying the search criteria for counting / selecting
     *
     * @param parentOrganisationDescription
     * @see OrganisationManager#search(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     * @see OrganisationManager#searchCount(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String)
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
