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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.manager.NameParserManager;
import org.jacq.common.model.BotanicalObjectResult;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblScientificNameInformation;
import org.jacq.common.model.jpa.ViewScientificName;
import org.jacq.common.model.jpa.ViewTaxon;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.rest.BotanicalObjectService;

/**
 *
 * @author wkoller
 */
public class BotanicalObjectManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    @Inject
    protected NameParserManager nameParserManager;

    @Inject
    protected ImageServerManager imageServerManager;

    /**
     * @see BotanicalObjectService#search(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public List<BotanicalObjectResult> search(String scientificName, String organization, Boolean hasImage, Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblBotanicalObject> cq = cb.createQuery(TblBotanicalObject.class);
        Root<TblBotanicalObject> bo = cq.from(TblBotanicalObject.class);

        // select result list
        cq.select(bo);

        // apply search criteria
        applySearchCriteria(cb, cq, bo, scientificName, organization, hasImage);

        // convert to typed query and apply offset / limit
        TypedQuery<TblBotanicalObject> botanicalObjectSearchQuery = em.createQuery(cq);
        if (offset != null) {
            botanicalObjectSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            botanicalObjectSearchQuery.setMaxResults(limit);
        }

        // finally fetch the results
        ArrayList<BotanicalObjectResult> results = new ArrayList<>();
        List<TblBotanicalObject> botanicalObjectResults = botanicalObjectSearchQuery.getResultList();
        for (TblBotanicalObject botanicalObject : botanicalObjectResults) {
            BotanicalObjectResult botanicalObjectResult = new BotanicalObjectResult(botanicalObject);

            TypedQuery<TblScientificNameInformation> scientificNameInformationQuery = em.createNamedQuery("TblScientificNameInformation.findByScientificNameId", TblScientificNameInformation.class);
            scientificNameInformationQuery.setParameter("scientificNameId", botanicalObject.getScientificNameId());
            try {
                TblScientificNameInformation scientificNameInformation = scientificNameInformationQuery.getSingleResult();
                botanicalObjectResult.getCommonNames().add(scientificNameInformation.getCommonNames());
            } catch (NoResultException e) {
            }

            // fetch image information and add it to result
            botanicalObjectResult.getImageServerResources().addAll(imageServerManager.getResources(botanicalObject));

            // add botanical object to result list
            results.add(botanicalObjectResult);
        }

        return results;
    }

    /**
     * @see BotanicalObjectService#searchCount(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public int searchCount(String scientificName, String organization, Boolean hasImage) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TblBotanicalObject> bo = cq.from(TblBotanicalObject.class);

        // count result
        cq.select(cb.count(bo));

        // apply search criteria
        applySearchCriteria(cb, cq, bo, scientificName, organization, hasImage);

        // run query and return count
        return em.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * Helper function for applying the search criteria for counting / selecting
     *
     * @see BotanicalObjectManager#search(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Integer,
     * java.lang.Integer)
     * @see BotanicalObjectManager#searchCount(java.lang.String, java.lang.String, java.lang.Boolean)
     *
     * @param cb
     * @param cq
     * @param bo
     * @param scientificName
     * @param organization
     * @param hasImage
     */
    protected void applySearchCriteria(CriteriaBuilder cb, CriteriaQuery cq, Root<TblBotanicalObject> bo, String scientificName, String organization, Boolean hasImage) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        // join organisation table and filter on greenhouse only
        Join<TblBotanicalObject, TblOrganisation> tblOrganisation = bo.join("organisationId", JoinType.LEFT);
        path = tblOrganisation.get("greenhouse");
        predicates.add(cb.equal(path, 0));

        // check for presence of scientific name
        if (!StringUtils.isEmpty(scientificName)) {
            // clean and parse the scientific name
            NameParserResponse nameParserResponse = nameParserManager.parseName(scientificName);
            // check for scientific name components
            if (!StringUtils.isEmpty(nameParserResponse.getUninomial())) {
                Join<TblBotanicalObject, ViewTaxon> viewTaxon = bo.join("viewTaxon", JoinType.LEFT);
                path = viewTaxon.get("genus");
                predicates.add(cb.like(path, nameParserResponse.getUninomial() + "%"));
            }
            // check for species
            else if (!StringUtils.isEmpty(nameParserResponse.getGenus())) {
                Join<TblBotanicalObject, ViewTaxon> viewTaxon = bo.join("viewTaxon", JoinType.LEFT);
                path = viewTaxon.get("genus");
                predicates.add(cb.like(path, nameParserResponse.getGenus() + "%"));

                if (!StringUtils.isEmpty(nameParserResponse.getSpecies())) {
                    path = viewTaxon.get("epithet");
                    predicates.add(cb.like(path, nameParserResponse.getSpecies() + "%"));
                }
            }
            // fallback to full scientific name matching if something went wrong
            else {
                Join<TblBotanicalObject, ViewScientificName> viewScientificName = bo.join("viewScientificName", JoinType.LEFT);
                path = viewScientificName.get("scientificName");
                predicates.add(cb.like(path, nameParserResponse.getScientificName() + "%"));
            }
        }

        // check if searching for organization
        if (!StringUtils.isEmpty(organization)) {
            path = tblOrganisation.get("description");
            predicates.add(cb.like(path, organization.toLowerCase() + "%"));
        }

        // check if we are searching for results which only contain images
        if (hasImage) {
            path = bo.get("hasImage");
            predicates.add(cb.equal(path, 1));
        }

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));
    }
}
