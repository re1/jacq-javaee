/*
 * Copyright 2018 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.input.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import org.jacq.input.controller.SessionController;

/**
 *
 * @author wkoller
 */
@Provider
public class BasicClientRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        SessionController sessionController = getSessionController();

        if (sessionController.getAuthorizationHeader() != null) {
            List<Object> authorization = new ArrayList<Object>() {
                {
                    add(getSessionController().getAuthorizationHeader());
                }
            };

            requestContext.getHeaders().put("Authorization", authorization);
        }
    }

    protected SessionController getSessionController() {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<SessionController> bean = (Bean<SessionController>) bm.getBeans(SessionController.class).iterator().next();
        CreationalContext<SessionController> ctx = bm.createCreationalContext(bean);
        return (SessionController) bm.getReference(bean, SessionController.class, ctx);
    }

}
