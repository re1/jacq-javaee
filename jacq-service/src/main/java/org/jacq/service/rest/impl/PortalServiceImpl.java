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
package org.jacq.service.rest.impl;

import java.util.HashMap;
import javax.inject.Inject;
import org.jacq.common.rest.PortalService;
import org.jacq.service.JacqServiceConfig;

/**
 * @see PortalService
 * @author wkoller
 */
public class PortalServiceImpl implements PortalService {

    @Inject
    protected JacqServiceConfig jacqConfig;

    /**
     * @see PortalService#getConfig()
     */
    @Override
    public HashMap<String, String> getConfig() {
        return jacqConfig.getPortalConfig();
    }

}
