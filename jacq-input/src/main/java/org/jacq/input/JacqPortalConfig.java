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
package org.jacq.input;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import org.jacq.common.manager.JacqConfig;
import org.jacq.common.rest.PortalService;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
@Singleton
public class JacqPortalConfig extends JacqConfig {

    protected PortalService portalService;

    @PostConstruct
    public void init() {
        this.portalService = ServicesUtil.getPortalService();

        this.config = this.portalService.getConfig();
    }
}
