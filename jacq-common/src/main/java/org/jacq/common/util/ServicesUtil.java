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
package org.jacq.common.util;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientRequestFilter;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.ClassificationService;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.common.rest.InventoryService;
import org.jacq.common.rest.PersonService;
import org.jacq.common.rest.PortalService;
import org.jacq.common.rest.TreeRecordFileService;
import org.jacq.common.rest.UserService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.common.rest.output.SearchService;
import org.jacq.common.rest.provider.CustomDateParamConverterProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * Helper function for providing proxies to several service backends
 *
 * @author fhafner
 */
public class ServicesUtil {

    private static final String JACQ_SERVICE_URL = System.getProperty("jacq.serviceUrl");
    private static final String JACQ_SERVICE_NAMES_URL = System.getProperty("jacq.serviceNamesUrl");
    private static final String JACQ_SERVICE_OUTPUT_URL = System.getProperty("jacq.serviceOutputUrl");

    protected static List<ClientRequestFilter> clientRequestFilters = new ArrayList<>();

    public static DerivativeService getDerivativeService() {
        return getProxy(DerivativeService.class, JACQ_SERVICE_URL);
    }

    public static OrganisationService getOrganisationService() {
        return getProxy(OrganisationService.class, JACQ_SERVICE_URL);
    }

    public static UserService getUserService() {
        return getProxy(UserService.class, JACQ_SERVICE_URL);
    }

    public static InventoryService getInventoryService() {
        return getProxy(InventoryService.class, JACQ_SERVICE_URL);
    }

    public static TreeRecordFileService getTreeRecordFileService() {
        return getProxy(TreeRecordFileService.class, JACQ_SERVICE_URL);
    }

    public static ClassificationService getClassificationService() {
        return getProxy(ClassificationService.class, JACQ_SERVICE_URL);
    }

    public static IndexSeminumService getIndexSeminumService() {
        return getProxy(IndexSeminumService.class, JACQ_SERVICE_URL);
    }

    public static ScientificNameService getScientificNameService() {
        return getProxy(ScientificNameService.class, JACQ_SERVICE_NAMES_URL);
    }

    public static PortalService getPortalService() {
        return getProxy(PortalService.class, JACQ_SERVICE_URL);
    }

    public static PersonService getPersonService() {
        return getProxy(PersonService.class, JACQ_SERVICE_URL);
    }

    public static SearchService getSearchService() {
        return getProxy(SearchService.class, JACQ_SERVICE_OUTPUT_URL);
    }

    /**
     * Register a client request filter for use in all requests (used for all future requests automatically)
     *
     * @param clientRequestFilter
     */
    public static void registerClientRequestFilter(ClientRequestFilter clientRequestFilter) {
        clientRequestFilters.add(clientRequestFilter);
    }

    /**
     * Get a proxy for a given service endpoint class
     *
     * @param <T>
     * @param serviceInterfaceClass
     * @param serviceURI
     * @return
     */
    protected static <T> T getProxy(Class<T> serviceInterfaceClass, String serviceURI) {
        ResteasyClient resteasyClient = new ResteasyClientBuilder().connectionPoolSize(20).build();
        resteasyClient.register(new CustomDateParamConverterProvider());
        for (ClientRequestFilter clientRequestFilter : clientRequestFilters) {
            resteasyClient.register(clientRequestFilter);
        }
        //resteasyClient.register(new BasicClientRequestFilter());
        //resteasyClient.register(new ContentTypeResponseFilter());
        ResteasyWebTarget resteasyWebTarget = resteasyClient.target(serviceURI);
        return (T) resteasyWebTarget.proxy(serviceInterfaceClass);
    }
}
