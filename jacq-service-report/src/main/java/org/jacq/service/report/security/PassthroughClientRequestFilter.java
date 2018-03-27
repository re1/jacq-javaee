/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;
import org.jacq.common.security.JacqCallerPrincipal;

/**
 *
 * @author wkoller
 */
@Provider
public class PassthroughClientRequestFilter implements ClientRequestFilter {

    @Context
    protected SecurityContext securityContext;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {

        List<Object> authorization = new ArrayList<Object>() {
            {
                add(((JacqCallerPrincipal) securityContext.getUserPrincipal()).getAuthorizationHeader());
            }
        };

        requestContext.getHeaders().put("Authorization", authorization);
    }

}
