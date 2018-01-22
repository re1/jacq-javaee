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
package org.jacq.service;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import org.jacq.common.model.rest.UserResult;
import org.jacq.service.security.ServiceCallerPrincipal;

/**
 *
 * @author wkoller
 */
@ManagedBean
@SessionScoped
public class SessionManager implements Serializable {

    @Inject
    protected SecurityContext securityContext;

    public UserResult getUser() {
        return ((ServiceCallerPrincipal) securityContext.getCallerPrincipal()).getUser();
    }
}
