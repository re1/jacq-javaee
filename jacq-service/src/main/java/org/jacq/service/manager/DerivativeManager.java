/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.rest.DerivativeService;

/**
 * Helper class for querying all derivatives in a unified way Due to MySQL not performing well on views with UNION ALL
 * we simulate a view by writing the queries directly in this class Normally native queries should not be used at all
 * costs
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeManager {

    private static final Logger LOGGER = Logger.getLogger(DerivativeManager.class.getName());

    private static final String SELECT_FIELDS = "SELECT `derivative_id`, `botanical_object_id`, `scientific_name`, `accession_number`, `label_annotation`, `organisation_description`, `place_number`, `derivative_count`, `type`, `separated`";

    private static final String SELECT_COUNT = "SELECT count(*) AS `row_count`";

    private static final String FROM_LIVING = "FROM `view_botanical_object_living`";
    private static final String FROM_VEGETATIVE = "FROM `view_botanical_object_vegetative`";

    private static final String FILTER_TYPE = "`type` = ?";
    private static final String FILTER_DERIVATIVEID = "`derivative_id` = ?";
    private static final String FILTER_PLACENUMBER = "`place_number` = ?";
    private static final String FILTER_ACCESSIONNUMBER = "`accession_number` = ?";
    private static final String FILTER_SEPARATED = "`separated` = ?";

    @PersistenceContext
    private EntityManager em;

    /**
     * @see DerivativeManager#find(java.lang.String, java.lang.Long, java.lang.String, java.lang.String,
     * java.lang.Integer, java.lang.Integer)
     */
    public List<BotanicalObjectDerivative> find(String type, Long derivativeId) {
        return find(type, derivativeId, null, null, null, null, null, null, null);
    }

    /**
     * Find derivatives based on given search parameters. All search parameters are optional and will be ignored if they
     * are null.
     *
     * @param type
     * @param derivativeId
     * @param orderColumn
     * @param orderDirection
     * @param offset
     * @param count
     * @return
     */
    public List<BotanicalObjectDerivative> find(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
        List<Object> params = new ArrayList<>();

        // translate order column into database column
        orderColumn = getColumnName(orderColumn);

        // apply search criteria to all derivative views
        String livingQueryString = applySearchCriteria(SELECT_FIELDS + " " + FROM_LIVING, params, type, derivativeId, placeNumber, accessionNumber, separated, orderColumn, orderDirection, offset, count);
        String vegetativeQueryString = applySearchCriteria(SELECT_FIELDS + " " + FROM_VEGETATIVE, params, type, derivativeId, placeNumber, accessionNumber, separated, orderColumn, orderDirection, offset, count);

        String botanicalObjectSearchQueryString = "SELECT * FROM (SELECT * FROM (" + livingQueryString + ") AS tmp_list_living UNION ALL SELECT * FROM (" + vegetativeQueryString + ") AS tmp_list_vegetative) AS tmp_list_tbl";

        // apply order
        if (orderColumn != null) {
            botanicalObjectSearchQueryString += " ORDER BY " + orderColumn;

            if (orderDirection != null) {
                botanicalObjectSearchQueryString += " " + orderDirection;
            }
        }

        Query botanicalObjectSearchQuery = em.createNativeQuery(botanicalObjectSearchQueryString, BotanicalObjectDerivative.class);
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
    public int count(String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated) {
        List<Object> params = new ArrayList<>();

        // apply search criteria to all derivative views
        String livingQueryString = applySearchCriteria(SELECT_COUNT + " " + FROM_LIVING, params, type, derivativeId, placeNumber, accessionNumber, separated, null, null, null, null);
        String vegetativeQueryString = applySearchCriteria(SELECT_COUNT + " " + FROM_VEGETATIVE, params, type, derivativeId, placeNumber, accessionNumber, separated, null, null, null, null);

        String botanicalObjectSearchQueryString = "SELECT SUM(`row_count`) FROM (" + livingQueryString + " UNION ALL " + vegetativeQueryString + ") AS tmp_count_tbl";

        Query botanicalObjectSearchQuery = em.createNativeQuery(botanicalObjectSearchQueryString);
        for (int i = 0; i < params.size(); i++) {
            botanicalObjectSearchQuery.setParameter(i + 1, params.get(i));
        }

        return ((Number) botanicalObjectSearchQuery.getSingleResult()).intValue();
    }

    /**
     * @see DerivativeService#load(java.lang.Long, java.lang.String)
     */
    @Transactional
    public BotanicalObjectDerivative load(Long derivativeId, String type) {
        if (LivingPlantResult.LIVING.equalsIgnoreCase(type)) {
            TblLivingPlant tblLivingPlant = em.find(TblLivingPlant.class, derivativeId);
            if (tblLivingPlant != null) {
                return new LivingPlantResult(tblLivingPlant);
            }
        }

        return null;
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
    protected String applySearchCriteria(String baseSql, List<Object> params, String type, Long derivativeId, String placeNumber, String accessionNumber, Boolean separated, String orderColumn, OrderDirection orderDirection, Integer offset, Integer count) {
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
        }
        else if (offset != null) {
            queryString += " LIMIT 0, " + offset;
        }
        else if (count != null) {
            queryString += " LIMIT 0, " + count;
        }

        return queryString;
    }

    /**
     * Helper function for retrieving the actual database column name for a given column attribute name
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

}
