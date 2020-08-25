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
package org.jacq.service.names.sources.util;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.concurrent.TimeUnit;

/**
 * Helper class for providing quick access to source-services
 *
 * @author wkoller
 */
public class SourcesUtil {
    /**
     * Utility function for creating a proxy to a given source class using the service URI
     *
     * @param <T>                   Type of serviceInterfaceClass
     * @param serviceInterfaceClass Resteasy Proxy Framework service interface class of a source
     * @param serviceURI            unencoded URI to a source ReST service endpoint
     * @return Proxy object for the given service
     */
    public static <T> T getProxy(Class<T> serviceInterfaceClass, String serviceURI) {
        ResteasyClient resteasyClient = new ResteasyClientBuilder()
                .connectionPoolSize(20)
                .establishConnectionTimeout(120, TimeUnit.SECONDS)
                .socketTimeout(20, TimeUnit.SECONDS)
                .build();
        // resteasyClient.register(new RequestDebugFilter());
        ResteasyWebTarget resteasyWebTarget = resteasyClient.target(serviceURI);
        return resteasyWebTarget.proxy(serviceInterfaceClass);
    }
}
