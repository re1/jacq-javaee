/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblDerivativeType;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblPhenology;
import org.jacq.common.model.jpa.TblSeparation;
import org.jacq.common.model.jpa.TblSeparationType;
import org.jacq.common.model.jpa.TblVegetative;
import org.jacq.common.model.rest.SeparationResult;
import org.jacq.common.model.rest.VegetativeResult;
import org.jacq.common.rest.DerivativeService;

/**
 * Contains business logic for vegetative derivatives related tasks
 *
 * @author wkoller
 */
@ManagedBean
public class VegetativeManager {

    @PersistenceContext
    protected EntityManager em;

    /**
     * @see DerivativeService#vegetativeSave(org.jacq.common.model.rest.VegetativeResult)
     */
    @Transactional
    public VegetativeResult vegetativeSave(VegetativeResult vegetativeResult) {
        TblVegetative tblVegetative = null;
        if (vegetativeResult.getVegetativeId() != null) {
            tblVegetative = em.find(TblVegetative.class, vegetativeResult.getVegetativeId());
        }
        else {
            // prepare skeleton vegetative entry
            tblVegetative = new TblVegetative();
        }
        tblVegetative.setCultivationDate(vegetativeResult.getCultivationDate());
        tblVegetative.setIndexSeminum((vegetativeResult.getIndexSeminum() != null) ? vegetativeResult.getIndexSeminum() : Boolean.FALSE);
        tblVegetative.setAnnotation(vegetativeResult.getAnnotation());
        tblVegetative.setPlaceNumber(vegetativeResult.getPlaceNumber());
        tblVegetative.setSeparated(vegetativeResult.getSeparated());
        tblVegetative.setPhenologyId(em.find(TblPhenology.class, vegetativeResult.getPhenology().getPhenologyId()));

        // prepare derivative for vegetative entry
        TblDerivative tblDerivative = tblVegetative.getTblDerivative();
        if (tblDerivative == null) {
            tblDerivative = new TblDerivative();
            tblVegetative.setTblDerivative(tblDerivative);
        }
        tblDerivative.setParentDerivativeId(em.find(TblDerivative.class, vegetativeResult.getParentDerivativeId()));
        tblDerivative.setBotanicalObjectId(tblDerivative.getParentDerivativeId().getBotanicalObjectId());
        tblDerivative.setCount(1L);
        tblDerivative.setDerivativeTypeId(em.find(TblDerivativeType.class, 2L));
        tblDerivative.setOrganisationId(em.find(TblOrganisation.class, vegetativeResult.getOrganisation().getOrganisationId()));

        em.persist(tblDerivative);
        em.persist(tblVegetative);

        // save separations
        for (SeparationResult separation : vegetativeResult.getSeparations()) {
            TblSeparation tblSeparation = null;
            if (separation.getSeparationId() != null) {
                tblSeparation = em.find(TblSeparation.class, separation.getSeparationId());
            }
            else {
                tblSeparation = new TblSeparation();
            }

            // set properties
            tblSeparation.setDerivativeId(tblVegetative.getTblDerivative());
            tblSeparation.setDate(separation.getDate());
            tblSeparation.setAnnotation(separation.getAnnotation());
            tblSeparation.setSeparationTypeId(em.find(TblSeparationType.class, separation.getSeparationType().getSeparationTypeId()));

            // save separation
            em.persist(tblSeparation);
        }

        return new VegetativeResult(tblVegetative);
    }
}
