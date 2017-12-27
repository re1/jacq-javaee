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
import org.jacq.common.model.jpa.FrmwrkConfig;

/**
 * Global bean for accessing the config parameters for jacq
 *
 * @author wkoller
 */
@ManagedBean
@Singleton
public class JacqConfig {

    protected static final String CONFIG_SEPARATOR = ".";

    protected static final String BIRT = "birt";
    public static final String BIRT_WORK_LABEL = BIRT + CONFIG_SEPARATOR + "work_label";
    protected static final String TREERECORD = "treerecord";
    public static final String TREERECORD_PDF_PATH = TREERECORD + CONFIG_SEPARATOR + "pdf_path";

    protected HashMap<String, String> config;

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

    /**
     * Return a config value as string
     *
     * @param name Name of configuration value to return
     * @return
     */
    public String getString(String name) {
        return config.get(name);
    }
}
