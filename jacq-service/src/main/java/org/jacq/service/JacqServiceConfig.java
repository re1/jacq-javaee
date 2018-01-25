/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service;

import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jacq.common.manager.JacqConfig;
import org.jacq.common.model.jpa.FrmwrkConfig;

/**
 * Global bean for accessing the config parameters for jacq
 *
 * @author wkoller
 */
@ManagedBean
@Singleton
public class JacqServiceConfig extends JacqConfig {

    @PersistenceContext
    protected EntityManager em;

    /**
     * Initialize the configuration managed bean
     */
    @PostConstruct
    public void init() {
        // initialize configuration array
        this.config = new HashMap<>();

        // fetch all config entries from the database
        TypedQuery<FrmwrkConfig> query = em.createNamedQuery("FrmwrkConfig.findAll", FrmwrkConfig.class);
        List<FrmwrkConfig> configList = query.getResultList();
        for (FrmwrkConfig configEntry : configList) {
            this.config.put(configEntry.getFcName(), configEntry.getFcValue());
        }
    }
}
