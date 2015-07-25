/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.report.rest.impl;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jacq.common.rest.LabelService;
import org.jacq.common.rest.exception.JacqRestException;
import org.jacq.report.manager.LabelManager;

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
     * @see LabelService#getWork()
     */
    @Override
    public Response getWork(int accessionNumber) {
        try {
            return labelManager.getWork(accessionNumber);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            // encapsulate exception into Jacq specific rest exception
            throw new JacqRestException(e);
        }
    }
}
