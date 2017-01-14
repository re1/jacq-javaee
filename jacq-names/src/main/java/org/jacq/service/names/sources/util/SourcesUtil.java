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

import org.jacq.common.rest.filter.RequestDebugFilter;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jacq.service.names.sources.dnpgoth.DnpGoThWebSearch;
import org.jacq.service.names.sources.ylist.YListWebSearch;

/**
 * Helper class for providing quick access to source-services
 *
 * @author wkoller
 */
public class SourcesUtil {

    /**
     * Create service instance for http://www.dnp.go.th
     *
     * @return
     */
    public static DnpGoThWebSearch getDnpGoThWebSearch() {
        return getProxy(DnpGoThWebSearch.class, "http://www.dnp.go.th/");
    }

    /**
     * Create service instance for http://ylist.info/
     *
     * @return
     */
    public static YListWebSearch getYListWebSearch() {
        return getProxy(YListWebSearch.class, "http://ylist.info/");
    }

    /**
     * Utility function for creating a proxy to a given source class with base-url
     *
     * @param <T>
     * @param serviceInterfaceClass
     * @param serviceURI
     * @return
     */
    protected static <T> T getProxy(Class<T> serviceInterfaceClass, String serviceURI) {
        ResteasyClient resteasyClient = new ResteasyClientBuilder().connectionPoolSize(20).build();
        //resteasyClient.register(new RequestDebugFilter());
        ResteasyWebTarget resteasyWebTarget = resteasyClient.target(serviceURI);
        return (T) resteasyWebTarget.proxy(serviceInterfaceClass);
    }
}
