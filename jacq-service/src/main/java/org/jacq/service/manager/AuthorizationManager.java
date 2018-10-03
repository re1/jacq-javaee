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
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.FrmwrkaccessOrganisation;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.rest.AccessOrganisationResult;
import org.jacq.common.model.rest.UserResult;

/**
 *
 * @author fhafner
 */
public class AuthorizationManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    @Inject
    protected UserManager userManager;

    /**
     * @see AuthorizationService#search(java.lang.Long, java.lang.String,
     * java.util.Date, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.Long, java.lang.Boolean, java.lang.Long, java.lang.Long,
     * java.lang.Integer, java.lang.Integer)
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param organisationDescription
     * @param accessOrganisationId
     * @param allowDeny
     * @param userId
     * @param organisationId
     * @param offset
     * @param limit
     * @return
     */
    @Transactional
    public List<AccessOrganisationResult> search(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription, Long accessOrganisationId, Boolean allowDeny, Long userId, Long organisationId, Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FrmwrkaccessOrganisation> cq = cb.createQuery(FrmwrkaccessOrganisation.class);
        Root<FrmwrkaccessOrganisation> bo = cq.from(FrmwrkaccessOrganisation.class);

        // select result list
        cq.select(bo);

        ArrayList<AccessOrganisationResult> results = new ArrayList<>();
        List<UserResult> userResults = userManager.search(id, username, birthdate, userType, employmentType, organisationDescription, offset, limit);

        for (UserResult user : userResults) {
            // apply search criteria
            applySearchCriteria(cb, cq, bo, null, null, user.getId(), organisationId);

            // convert to typed query and apply offset / limit
            TypedQuery<FrmwrkaccessOrganisation> accessOrganisationSearchQuery = em.createQuery(cq);

            // finally fetch the results
            List<FrmwrkaccessOrganisation> accessOrganisationResults = accessOrganisationSearchQuery.getResultList();

            if (accessOrganisationResults.isEmpty()) {
                AccessOrganisationResult accessOrganisationResult = new AccessOrganisationResult(user.getId(), organisationId, user.getUsername());
                results.add(accessOrganisationResult);
            } else {
                for (FrmwrkaccessOrganisation organisation : accessOrganisationResults) {
                    AccessOrganisationResult accessOrganisationResult = new AccessOrganisationResult(organisation, user.getUsername());

                    // add accessOrganisation object to result list
                    results.add(accessOrganisationResult);
                }
            }
        }

        return results;
    }

    /**
     * @see AuthorizationService#searchCount(java.lang.Long, java.lang.String,
     * java.util.Date, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.Long, java.lang.Boolean, java.lang.Long, java.lang.Long)
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param organisationDescription
     * @param accessOrganisationId
     * @param allowDeny
     * @param userId
     * @param organisationId
     * @return
     */
    @Transactional
    public int searchCount(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription, Long accessOrganisationId, Boolean allowDeny, Long userId, Long organisationId) {
        return userManager.searchCount(id, username, birthdate, userType, employmentType, organisationDescription);
    }

    /**
     *
     * @param cb
     * @param cq
     * @param bo
     * @param accessOrganisationId
     * @param allowDeny
     * @param userId
     * @param organisationId
     */
    protected void applySearchCriteria(CriteriaBuilder cb, CriteriaQuery cq, Root<FrmwrkaccessOrganisation> bo, Long accessOrganisationId, Boolean allowDeny, Long userId, Long organisationId) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        if (accessOrganisationId != null) {
            path = bo.get("id");
            predicates.add(cb.equal(path, accessOrganisationId));
        }

        if (allowDeny != null) {
            path = bo.get("allowDeny");
            predicates.add(cb.equal(path, allowDeny));
        }

        if (userId != null) {
            path = bo.get("userId");
            predicates.add(cb.equal(path, userId));
        }

        if (organisationId != null) {
            path = bo.get("organisationId");
            predicates.add(cb.equal(path, organisationId));
        }

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));
    }

    /**
     * @see
     * AuthorizationService#save(org.jacq.common.model.rest.AccessOrganisationResult)
     * @param accessOrganisationResult
     * @return
     */
    @Transactional
    public AccessOrganisationResult save(AccessOrganisationResult accessOrganisationResult) {

        if (accessOrganisationResult.getId() != null && accessOrganisationResult.getAllowDeny() == null) {
            FrmwrkaccessOrganisation frmwrkaccessOrganisation = em.find(FrmwrkaccessOrganisation.class, accessOrganisationResult.getId());
            em.remove(frmwrkaccessOrganisation);
        } else if (accessOrganisationResult.getId() != null) {
            FrmwrkaccessOrganisation frmwrkaccessOrganisation = em.find(FrmwrkaccessOrganisation.class, accessOrganisationResult.getId());
            if (frmwrkaccessOrganisation != null) {
                frmwrkaccessOrganisation.setAllowDeny(accessOrganisationResult.getAllowDeny());
                em.merge(frmwrkaccessOrganisation);
            } else {
                frmwrkaccessOrganisation = new FrmwrkaccessOrganisation();
                frmwrkaccessOrganisation.setAllowDeny(accessOrganisationResult.getAllowDeny());
                frmwrkaccessOrganisation.setOrganisationId(em.find(TblOrganisation.class, accessOrganisationResult.getOrganisationId()));
                frmwrkaccessOrganisation.setUserId(em.find(FrmwrkUser.class, accessOrganisationResult.getUserId()));
                em.persist(frmwrkaccessOrganisation);
            }
        } else if (accessOrganisationResult.getAllowDeny() != null) {
            FrmwrkaccessOrganisation frmwrkaccessOrganisation = new FrmwrkaccessOrganisation();
            frmwrkaccessOrganisation.setAllowDeny(accessOrganisationResult.getAllowDeny());
            frmwrkaccessOrganisation.setOrganisationId(em.find(TblOrganisation.class, accessOrganisationResult.getOrganisationId()));
            frmwrkaccessOrganisation.setUserId(em.find(FrmwrkUser.class, accessOrganisationResult.getUserId()));
            em.persist(frmwrkaccessOrganisation);
        } else {
            Query query = em.createNamedQuery("FrmwrkaccessOrganisation.findByOrganisationIdandUserId").setParameter("organisationId", em.find(TblOrganisation.class, accessOrganisationResult.getOrganisationId())).setParameter("userId", em.find(FrmwrkUser.class, accessOrganisationResult.getUserId()));
            FrmwrkaccessOrganisation frmwrkaccessOrganisation = (FrmwrkaccessOrganisation) query.getSingleResult();
            if (frmwrkaccessOrganisation != null) {
                em.remove(frmwrkaccessOrganisation);
            }
        }
        return null;
    }

}
