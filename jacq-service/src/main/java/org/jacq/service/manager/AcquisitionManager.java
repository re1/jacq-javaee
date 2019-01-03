/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jacq.common.model.jpa.TblAcquisitionSource;
import org.jacq.common.model.rest.AcquisitionSourceResult;
import org.jacq.common.rest.AcquisitionService;

/**
 * Contains acquisition related business code
 *
 * @author wkoller
 */
@ManagedBean
public class AcquisitionManager {

    @PersistenceContext
    protected EntityManager em;

    /**
     * @see AcquisitionService#sourceSearch(java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     */
    public List<AcquisitionSourceResult> sourceSearch(String name, Integer offset, Integer limit) {
        TypedQuery<TblAcquisitionSource> acquisitionSourceQuery = em.createNamedQuery("TblAcquisitionSource.findLikeName", TblAcquisitionSource.class);
        acquisitionSourceQuery.setParameter("name", name + "%");
        acquisitionSourceQuery.setFirstResult(offset);
        acquisitionSourceQuery.setMaxResults(limit);

        return AcquisitionSourceResult.fromList(acquisitionSourceQuery.getResultList());
    }

    /**
     * @see AcquisitionService#sourceLoad(java.lang.Long)
     */
    public AcquisitionSourceResult sourceLoad(Long locationId) {
        return new AcquisitionSourceResult(em.find(TblAcquisitionSource.class, locationId));
    }
}
