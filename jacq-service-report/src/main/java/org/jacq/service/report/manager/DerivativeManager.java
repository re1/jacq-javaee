/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.manager;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jacq.common.model.BotanicalObjectDerivative;

/**
 * Helper class for querying all derivatives in a unified way Due to MySQL not performing well on views with UNION ALL
 * we simulate a view by writing the queries directly in this class Normally native queries should not be used at all
 * costs
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeManager {

    private static final String SELECT_LIVING = "SELECT `id`, `derivative_id`, `scientific_name`, `accession_number`, `label_annotation`, `type` FROM `view_botanical_object_living`";
    private static final String SELECT_VEGETATIVE = "SELECT `id`, `derivative_id`, `scientific_name`, `accession_number`, `label_annotation`, `type` FROM `view_botanical_object_vegetative`";

    private static final String FILTER_TYPE = "`type` = ?";
    private static final String FILTER_DERIVATIVEID = "`derivative_id` = ?";

    @PersistenceContext
    private EntityManager em;

    public List<BotanicalObjectDerivative> findDerivative(String type, Long derivativeId) {
        List<Object> params = new ArrayList<>();

        String livingQueryString = applySearchCriteria(SELECT_LIVING, params, type, derivativeId);
        String vegetativeQueryString = applySearchCriteria(SELECT_VEGETATIVE, params, type, derivativeId);

        String botanicalObjectSearchQueryString = livingQueryString + " UNION ALL " + vegetativeQueryString;

        Query botanicalObjectSearchQuery = em.createNativeQuery(botanicalObjectSearchQueryString, BotanicalObjectDerivative.class);
        for (int i = 0; i < params.size(); i++) {
            botanicalObjectSearchQuery.setParameter(i + 1, params.get(i));
        }

        List<BotanicalObjectDerivative> results = botanicalObjectSearchQuery.getResultList();

        return results;
    }

    /**
     * Apply search criteria for querying
     *
     * @param baseSql
     * @param params
     * @param type
     * @param derivativeId
     * @return
     */
    protected String applySearchCriteria(String baseSql, List<Object> params, String type, Long derivativeId) {
        String queryString = baseSql;
        queryString += " WHERE 1 ";

        if (type != null) {
            queryString += " AND " + FILTER_TYPE;
            params.add(type);
        }
        if (derivativeId != null) {
            queryString += " AND " + FILTER_DERIVATIVEID;
            params.add(derivativeId);
        }

        return queryString;
    }
}
