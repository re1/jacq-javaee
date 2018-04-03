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
package org.jacq.common.security;

import javax.security.enterprise.CallerPrincipal;
import org.apache.commons.codec.binary.Base64;
import org.jacq.common.model.rest.UserResult;

/**
 *
 * @author wkoller
 */
public class JacqCallerPrincipal extends CallerPrincipal {

    protected UserResult user;
    protected String authorizationHeader;

    public JacqCallerPrincipal(String name, UserResult user, String password) {
        super(name);

        this.user = user;
        this.authorizationHeader = "Basic " + Base64.encodeBase64String((name + ":" + password).getBytes());
    }

    public UserResult getUser() {
        return user;
    }

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

}
