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
import org.jacq.common.model.jpa.TblOrganisation;
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

    /**
     * @see BotanicalObjectService#search(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public List<BotanicalObjectResult> search(String scientificName, String organization, Boolean hasImage, Integer offset, Integer limit) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblBotanicalObject> cq = cb.createQuery(TblBotanicalObject.class);
        Root<TblBotanicalObject> bo = cq.from(TblBotanicalObject.class);

        // select result list
        cq.select(bo);

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

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));

        // convert to typed query and apply offset / limit
        TypedQuery<TblBotanicalObject> botanicalObjectSearchQuery = em.createQuery(cq);
        if (offset != null) {
            botanicalObjectSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            botanicalObjectSearchQuery.setMaxResults(limit);
        }

        // finally fetch the result
        return BotanicalObjectResult.fromList(botanicalObjectSearchQuery.getResultList());
    }

    /**
     * @see BotanicalObjectService#searchCount(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public int searchCount(String scientificName, String organization, Boolean hasImage) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TblBotanicalObject> bo = cq.from(TblBotanicalObject.class);

        // count result
        cq.select(cb.count(bo));

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

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getSingleResult().intValue();

//        TypedQuery<Long> botanicalObjectSearchQuery = em.createNamedQuery("TblBotanicalObject.findByScientificNameAndPublicCount", Long.class);
//        botanicalObjectSearchQuery.setParameter("scientificName", "%" + scientificName.toLowerCase() + "%");
//        return botanicalObjectSearchQuery.getSingleResult().intValue();
    }
}
