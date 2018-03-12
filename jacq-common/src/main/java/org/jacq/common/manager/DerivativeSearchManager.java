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
package org.jacq.common.manager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.rest.DerivativeService;

/**
 * Helper class for querying all derivatives in a unified way Due to MySQL not
 * performing well on views with UNION ALL we simulate a view by writing the
 * queries directly in this class Normally native queries should not be used at
 * all costs Note: Make sure the entity manager is set prior calling any
 * functions
 *
 * @author wkoller
 */
public abstract class DerivativeSearchManager {

    protected static final Logger LOGGER = Logger.getLogger(DerivativeSearchManager.class.getName());

    protected static final String SELECT_FIELDS = "SELECT `derivative_id`, `botanical_object_id`, `scientific_name`, `scientific_name_id`, `accession_number`, `label_annotation`, `organisation_description`, `organisation_id`, `place_number`, `derivative_count`, `type`, `separated`, `cultivar_name`, `imported_species_name`";

    protected static final String SELECT_COUNT = "SELECT count(*) AS `row_count`";

    protected static final String FROM_LIVING = "FROM `view_botanical_object_living`";
    protected static final String FROM_VEGETATIVE = "FROM `view_botanical_object_vegetative`";

    protected static final String FILTER_TYPE = "`type` = ?";
    protected static final String FILTER_DERIVATIVEID = "`derivative_id` = ?";
    protected static final String FILTER_PLACENUMBER = "`place_number` = ?";
    protected static final String FILTER_ACCESSIONNUMBER = "`accession_number` = ?";
    protected static final String FILTER_SEPARATED = "`separated` = ?";
    protected static final String FILTER_SCIENTIFIC_NAME_ID = "`scientific_name_id` = ?";
    protected static final String FILTER_ORGANISATION_ID = "`organisation_id` in ";

    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected BaseApplicationManager baseApplicationManager;

    public void setBaseApplicationManager(BaseApplicationManager baseApplicationManager) {
        this.baseApplicationManager = baseApplicationManager;
    }

    /**
     * @see DerivativeService#find(java.lang.String, java.lang.Long,
     * java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Long,
     * java.lang.String, org.jacq.common.model.rest.OrderDirection,
     * java.lang.Integer, java.lang.Integer)
     */
    @Transactional
    public List<BotanicalObjectDerivative> find(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        List<Object> params = new ArrayList<>();

        List<Long> organisationIdList = new ArrayList<>();
        // organisation List for hierarchic
        if (organisationId != null) {
            if (hierarchic != null && hierarchic == true) {
                organisationIdList = baseApplicationManager.findOrganisationHierachyCache(organisationId);
                if (organisationIdList == null) {
                    organisationIdList = findChildren(entityManager.find(TblOrganisation.class, organisationId));
                    baseApplicationManager.addOrganisationHierachyCache(organisationId, organisationIdList);
                }
            }
            organisationIdList.add(organisationId);
        }

        // translate order column into database column
        orderColumn = getColumnName(orderColumn);

        // apply search criteria to all derivative views
        String livingQueryString = applySearchCriteria(SELECT_FIELDS + " " + FROM_LIVING, params, type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationIdList, orderColumn, orderDirection, offset, count);
        String vegetativeQueryString = applySearchCriteria(SELECT_FIELDS + " " + FROM_VEGETATIVE, params, type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationIdList, orderColumn, orderDirection, offset, count);

        String botanicalObjectSearchQueryString = "SELECT * FROM (SELECT * FROM (" + livingQueryString + ") AS tmp_list_living UNION ALL SELECT * FROM (" + vegetativeQueryString + ") AS tmp_list_vegetative) AS tmp_list_tbl";

        // apply order
        if (orderColumn != null) {
            botanicalObjectSearchQueryString += " ORDER BY " + orderColumn;

            if (orderDirection != null) {
                botanicalObjectSearchQueryString += " " + orderDirection;
            }
        }

        Query botanicalObjectSearchQuery = entityManager.createNativeQuery(botanicalObjectSearchQueryString, BotanicalObjectDerivative.class);
        for (int i = 0; i < params.size(); i++) {
            botanicalObjectSearchQuery.setParameter(i + 1, params.get(i));
        }

        // apply count
        if (offset != null) {
            botanicalObjectSearchQuery.setFirstResult(offset);
        }
        if (count != null) {
            botanicalObjectSearchQuery.setMaxResults(count);
        }

        // fetch result list
        List<BotanicalObjectDerivative> results = botanicalObjectSearchQuery.getResultList();

        return results;
    }

    /**
     * Return total available count of results based on search criteria
     *
     * @param type
     * @param derivativeId
     * @return
     */
    @Transactional
    public int count(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, Long organisationId, Boolean hierarchic) {
        List<Object> params = new ArrayList<>();

        List<Long> organisationIdList = new ArrayList<>();
        // organisation List for hierarchic
        if (organisationId != null) {
            if (hierarchic != null && hierarchic == true) {
                organisationIdList = findChildren(entityManager.find(TblOrganisation.class, organisationId));
            }
            organisationIdList.add(organisationId);
        }

        // apply search criteria to all derivative views
        String livingQueryString = applySearchCriteria(SELECT_COUNT + " " + FROM_LIVING, params, type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationIdList, null, null, null, null);
        String vegetativeQueryString = applySearchCriteria(SELECT_COUNT + " " + FROM_VEGETATIVE, params, type, derivativeId, placeNumber, accessionNumber, separated, scientificNameId, organisationIdList, null, null, null, null);

        String botanicalObjectSearchQueryString = "SELECT SUM(`row_count`) FROM (" + livingQueryString + " UNION ALL " + vegetativeQueryString + ") AS tmp_count_tbl";

        Query botanicalObjectSearchQuery = entityManager.createNativeQuery(botanicalObjectSearchQueryString);
        for (int i = 0; i < params.size(); i++) {
            botanicalObjectSearchQuery.setParameter(i + 1, params.get(i));
        }

        return ((Number) botanicalObjectSearchQuery.getSingleResult()).intValue();
    }

    /**
     * Apply search criteria for querying
     *
     * @param baseSql
     * @param params
     * @param type
     * @param derivativeId
     * @param offset
     * @param count
     * @return
     */
    protected String applySearchCriteria(String baseSql, List<Object> params, String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, Long scientificNameId, List<Long> organisationIdList, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        String queryString = baseSql;
        queryString += " WHERE 1 ";

        if (!StringUtils.isEmpty(type)) {
            queryString += " AND " + FILTER_TYPE;
            params.add(type);
        }
        if (derivativeId != null) {
            queryString += " AND " + FILTER_DERIVATIVEID;
            params.add(derivativeId);
        }
        if (!StringUtils.isEmpty(placeNumber)) {
            queryString += " AND " + FILTER_PLACENUMBER;
            params.add(placeNumber);
        }
        if (!StringUtils.isEmpty(accessionNumber)) {
            queryString += " AND " + FILTER_ACCESSIONNUMBER;
            params.add(accessionNumber);
        }
        if (separated != null) {
            queryString += " AND " + FILTER_SEPARATED;
            params.add(separated);
        }
        if (scientificNameId != null) {
            queryString += " AND " + FILTER_SCIENTIFIC_NAME_ID;
            params.add(scientificNameId);
        }
        if (organisationIdList != null && organisationIdList.size() > 0) {
            int i = 0;
            String organisationIds = "";
            while (i < organisationIdList.size()) {
                if (i < organisationIdList.size() - 1) {
                    organisationIds = organisationIds + organisationIdList.get(i).toString() + ",";
                } else {
                    organisationIds = organisationIds + organisationIdList.get(i).toString();
                }
                i++;
            }
            queryString += " AND " + FILTER_ORGANISATION_ID + "(" + organisationIds + ")";
        }

        // apply order
        if (orderColumn != null) {
            queryString += " ORDER BY " + orderColumn;

            if (orderDirection != null) {
                queryString += " " + orderDirection;
            }
        }

        // apply offset and count
        // NOTE: This must stay the last query modification
        if (offset != null && count != null) {
            queryString += " LIMIT 0, " + (offset + count);
        } else if (offset != null) {
            queryString += " LIMIT 0, " + offset;
        } else if (count != null) {
            queryString += " LIMIT 0, " + count;
        }

        return queryString;
    }

    /**
     * Helper function for retrieving the actual database column name for a
     * given column attribute name
     *
     * @param attributeName
     * @return
     */
    protected String getColumnName(String attributeName) {
        if (attributeName == null) {
            return null;
        }

        try {
            Field field = BotanicalObjectDerivative.class.getDeclaredField(attributeName);
            Column column = field.getAnnotation(Column.class);

            return column.name();
        } catch (NoSuchFieldException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Find all childs to the Head of the organisation Tree Recursiv
     *
     * @param tblOrganisation
     * @return
     */
    @Transactional
    protected List<Long> findChildren(TblOrganisation tblOrganisation) {
        List<Long> organisationIdList = new ArrayList<>();
        for (TblOrganisation organisation : tblOrganisation.getTblOrganisationList()) {
            organisationIdList.add(organisation.getId());
            organisationIdList.addAll(findChildren(organisation));
        }
        return organisationIdList;

    }

}
