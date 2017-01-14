/*
 * Copyright 2016 wkoller.
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
package org.jacq.common.rest.filter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * Helper filter for debugging request sent through jax-rs
 *
 * @author wkoller
 */
@Provider
public class RequestDebugFilter implements ClientRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(RequestDebugFilter.class.getName());

    @Override
    public void filter(ClientRequestContext crc) throws IOException {
        LOGGER.log(Level.WARNING, "Request-URL: ''{0}''", crc.getUri().toString());

        for (String key : crc.getHeaders().keySet()) {
            List<Object> headers = crc.getHeaders().get(key);

            LOGGER.warning("Values for header '" + key + "':");
            for (Object header : headers) {
                LOGGER.warning(header.toString());
            }
        }
        LOGGER.warning("Entity:");
        if (crc.getEntity() != null) {
            LOGGER.warning(crc.getEntity().toString());
        }
    }

}
