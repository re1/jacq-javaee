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
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeManager {

    private static final String SELECT_LIVING = "SELECT `id`, `derivative_id`, `scientific_name`, `accession_number`, `label_annotation`, `type` FROM `view_botanical_object_living` WHERE 1";
    private static final String SELECT_VEGETATIVE = "SELECT `id`, `derivative_id`, `scientific_name`, `accession_number`, `label_annotation`, `type` FROM `view_botanical_object_vegetative` WHERE 1";

    private static final String FILTER_TYPE = "`type` = ?";
    private static final String FILTER_DERIVATIVEID = "`derivative_id` = ?";

    @PersistenceContext
    private EntityManager em;

    public List<BotanicalObjectDerivative> findDerivative(String type, Long derivativeId) {
        List<Object> params = new ArrayList<>();

        String livingQueryString = SELECT_LIVING;

        if (type != null) {
            livingQueryString += " AND " + FILTER_TYPE;
            params.add(type);
        }
        if (derivativeId != null) {
            livingQueryString += " AND " + FILTER_DERIVATIVEID;
            params.add(derivativeId);
        }

        String vegetativeQueryString = SELECT_VEGETATIVE;

        if (type != null) {
            vegetativeQueryString += " AND " + FILTER_TYPE;
            params.add(type);
        }
        if (derivativeId != null) {
            vegetativeQueryString += " AND " + FILTER_DERIVATIVEID;
            params.add(derivativeId);
        }

        String botanicalObjectSearchQueryString = livingQueryString + " UNION ALL " + vegetativeQueryString;

        Query botanicalObjectSearchQuery = em.createNativeQuery(botanicalObjectSearchQueryString, BotanicalObjectDerivative.class);
        for (int i = 0; i < params.size(); i++) {
            botanicalObjectSearchQuery.setParameter(i + 1, params.get(i));
        }

        List<BotanicalObjectDerivative> results = botanicalObjectSearchQuery.getResultList();

        return results;
    }
}
