/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Application Bean for holding all application wide data / objects
 *
 * @author wkoller
 */
@ManagedBean
@ApplicationScoped
public class ApplicationManager {

    private static final Logger LOGGER = Logger.getLogger(ApplicationManager.class.getName());

    HashMap<Long, List<Long>> organisationHierachyCache;

    /**
     * Application scoped timer manager for running schedulded jobs
     */
    @Inject
    protected TimerManager timerManager;

    /**
     * Construct and initiate the required components for application wide use
     */
    @PostConstruct
    public void init() {
        this.organisationHierachyCache = new HashMap<>();
    }

    /**
     * Make sure we cleanup and shutdown correctly
     */
    @PreDestroy
    public void destroy() {
    }

    public void addOrganisationHierachyCache(Long organisationId, List<Long> organisationIdList) {

    }

    public List<Long> findOrganisationHierachyCache(Long organisationId) {

        return null;
    }
}
