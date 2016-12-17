/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

/**
 * Application Bean for holding all application wide data / objects
 *
 * @author wkoller
 */
@ManagedBean
@ApplicationScoped
public class ApplicationManager {

    private static final Logger LOGGER = Logger.getLogger(ApplicationManager.class.getName());

    /**
     * BIRT related objects
     */
    protected EngineConfig engineConfig;
    protected IReportEngine reportEngine;

    /**
     * Construct and initiate the required components for application wide use
     */
    @PostConstruct
    public void init() {
        // try to initialize the BIRT report engine
        try {
            engineConfig = new EngineConfig();
            engineConfig.setEngineHome("/opt/birt-runtime-4_4_2");
            engineConfig.setLogConfig("/tmp/birt", Level.FINE);
            
            Platform.startup(engineConfig);

            final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform.
                    createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

            reportEngine = FACTORY.createReportEngine(engineConfig);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Make sure we cleanup and shutdown correctly
     */
    @PreDestroy
    public void destroy() {
        try {
            reportEngine.destroy();
            Platform.shutdown();
            //Bugzilla 351052
            RegistryProviderFactory.releaseDefault();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public IReportEngine getReportEngine() {
        return reportEngine;
    }

    public void setReportEngine(IReportEngine reportEngine) {
        this.reportEngine = reportEngine;
    }
}
