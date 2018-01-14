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
package org.jacq.service.dataimport.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;

/**
 * Filter for adding basic authorization to a client request using JAX-RS client
 *
 * @author wkoller
 */
@Provider
public class BasicClientRequestFilter implements ClientRequestFilter {

    protected String username;
    protected String password;

    public BasicClientRequestFilter() {
    }

    public BasicClientRequestFilter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        List<Object> authorization = new ArrayList<Object>() {
            {
                add("Basic " + Base64.encodeBase64String((username + ":" + password).getBytes()));
            }
        };

        requestContext.getHeaders().put("Authorization", authorization);
    }
}
