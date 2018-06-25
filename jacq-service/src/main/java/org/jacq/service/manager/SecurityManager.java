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
package org.jacq.service.manager;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import org.jacq.common.model.rest.UserResult;
import org.jacq.service.security.ServiceCallerPrincipal;

/**
 * Helper Manager for retrieving information from the security context
 *
 * @author wkoller
 */
@ManagedBean
public class SecurityManager implements Serializable {

    @Inject
    protected SecurityContext securityContext;

    public UserResult getUser() {
        return ((ServiceCallerPrincipal) securityContext.getCallerPrincipal()).getUser();
    }
}
