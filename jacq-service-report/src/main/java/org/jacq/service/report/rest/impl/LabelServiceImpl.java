/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.rest.impl;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jacq.common.rest.report.LabelService;
import org.jacq.common.rest.exception.JacqRestException;
import org.jacq.service.report.manager.LabelManager;

/**
 * @see LabelService
 *
 * @author wkoller
 */
@RequestScoped
public class LabelServiceImpl implements LabelService, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LabelServiceImpl.class.getName());

    @Inject
    protected LabelManager labelManager;

    /**
     * @see LabelService#getWork(java.lang.String, long)
     */
    @Override
    public Response getWork(String type, Long derivativeId) {
        try {
            return labelManager.getWork(type, derivativeId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            // encapsulate exception into Jacq specific rest exception
            throw new JacqRestException(e);
        }
    }

    @Override
    public Response getSeedOrder(Long seedOrderId) {
        try {
            return labelManager.getSeedOrder(seedOrderId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            // encapsulate exception into Jacq specific rest exception
            throw new JacqRestException(e);
        }
    }
}
