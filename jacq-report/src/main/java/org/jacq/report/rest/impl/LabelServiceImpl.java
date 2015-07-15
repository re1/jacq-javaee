/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.report.rest.impl;

import java.io.Serializable;
import javax.ws.rs.core.Response;
import org.jacq.common.rest.LabelService;

/**
 * @see LabelService
 *
 * @author wkoller
 */
public class LabelServiceImpl implements LabelService, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @see LabelService#getWork()
     */
    @Override
    public Response getWork() {
        return Response.ok("Test 2").build();
    }
}
