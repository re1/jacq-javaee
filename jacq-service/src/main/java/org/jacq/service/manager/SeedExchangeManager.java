/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.jpa.TblSeedOrder;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.SeedOrderResult;
import org.jacq.common.rest.SeedExchangeService;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class SeedExchangeManager {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected SecurityManager securityManager;

    @Inject
    protected DerivativeManager derivativeManager;

    /**
     * @see SeedExchangeService#find(java.lang.Long)
     */
    @Transactional
    public SeedOrderResult find(Long seedOrderId) {
        TblSeedOrder tblSeedOrder = em.find(TblSeedOrder.class, seedOrderId);

        return new SeedOrderResult(tblSeedOrder, this.fromTblDerivativeList(tblSeedOrder.getTblDerivativeList()));
    }

    /**
     * @see SeedExchangeService#findAll()
     */
    @Transactional
    public List<SeedOrderResult> findAll() {
        List<SeedOrderResult> seedOrderResults = new ArrayList<>();

        TypedQuery<TblSeedOrder> tblSeedOrderQuery = em.createNamedQuery("TblSeedOrder.findBySenderUserId", TblSeedOrder.class);
        tblSeedOrderQuery.setParameter("senderUserId", em.find(FrmwrkUser.class, securityManager.getUser().getId()));
        List<TblSeedOrder> tblSeedOrderList = tblSeedOrderQuery.getResultList();

        for (TblSeedOrder tblSeedOrder : tblSeedOrderList) {
            seedOrderResults.add(new SeedOrderResult(tblSeedOrder, this.fromTblDerivativeList(tblSeedOrder.getTblDerivativeList())));
        }

        return seedOrderResults;
    }

    /**
     * @see SeedExchangeService#save(org.jacq.common.model.rest.SeedOrderResult)
     */
    @Transactional
    public SeedOrderResult save(SeedOrderResult seedOrderResult) {
        TblSeedOrder tblSeedOrder = null;
        if (seedOrderResult.getSeedOrderId() != null) {
            throw new IllegalArgumentException("Not allowed to edit completed seed order");
            // tblSeedOrder = em.find(TblSeedOrder.class, seedOrderResult.getSeedOrderId());
        } else {
            tblSeedOrder = new TblSeedOrder();
        }

        // find user entity for logged in user
        FrmwrkUser frmwrkUser = em.find(FrmwrkUser.class, securityManager.getUser().getId());

        // set all necessary properties for order
        tblSeedOrder.setOrderDate(new Date());
        tblSeedOrder.setAnnotation(seedOrderResult.getAnnotation());
        tblSeedOrder.setComplete(true);
        tblSeedOrder.setOrdererOrganisationId(em.find(TblOrganisation.class, seedOrderResult.getOrdererOrganisation().getOrganisationId()));
        tblSeedOrder.setSenderOrganisationId(frmwrkUser.getOrganisationId());
        tblSeedOrder.setSenderUserId(frmwrkUser);

        // add list of derivatives
        List<TblDerivative> tblDerivatives = tblSeedOrder.getTblDerivativeList();
        if (tblDerivatives == null) {
            tblDerivatives = new ArrayList<>();
        }
        for (BotanicalObjectDerivative botanicalObjectDerivative : seedOrderResult.getDerivativeList()) {
            TblDerivative tblDerivative = em.find(TblDerivative.class, botanicalObjectDerivative.getDerivativeId());

            // for living plants, decrease seminum count by one
            if (BotanicalObjectDerivative.LIVING.equalsIgnoreCase(tblDerivative.getDerivativeTypeId().getType())) {
                tblDerivative.getTblLivingPlant().setSeminumCount(tblDerivative.getTblLivingPlant().getSeminumCount() - 1L);

                em.persist(tblDerivative);
            }

            tblDerivatives.add(tblDerivative);
        }
        tblSeedOrder.setTblDerivativeList(tblDerivatives);

        // save & refresh seed order
        em.persist(tblSeedOrder);
        em.flush();
        em.refresh(tblSeedOrder);

        return new SeedOrderResult(tblSeedOrder, this.fromTblDerivativeList(tblSeedOrder.getTblDerivativeList()));
    }

    /**
     * Helper function for converting a list of tblderivatives to
     * botanicalobject derivatives
     *
     * @param tblDerivatives
     * @return
     */
    protected List<BotanicalObjectDerivative> fromTblDerivativeList(List<TblDerivative> tblDerivatives) {
        List<BotanicalObjectDerivative> botanicalObjectDerivatives = new ArrayList<>();
        for (TblDerivative tblDerivative : tblDerivatives) {
            List<BotanicalObjectDerivative> botanicalObjectDerivativeResults = derivativeManager.find(null, tblDerivative.getDerivativeId(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            if (botanicalObjectDerivativeResults != null && botanicalObjectDerivativeResults.size() > 0) {
                botanicalObjectDerivatives.add(botanicalObjectDerivativeResults.get(0));
            }
        }

        return botanicalObjectDerivatives;
    }
}
