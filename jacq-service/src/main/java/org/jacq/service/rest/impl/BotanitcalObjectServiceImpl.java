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
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.BotanicalObjectResult;
import org.jacq.common.rest.BotanicalObjectService;
import org.jacq.service.manager.BotanicalObjectManager;

/**
 *
 * @author wkoller
 */
public class BotanitcalObjectServiceImpl implements BotanicalObjectService {

    @Inject
    protected BotanicalObjectManager botanicalObjectManager;

    /**
     * @see BotanicalObjectService#search(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Override
    public List<BotanicalObjectResult> search(String scientificName, String organization, Boolean hasImage, Integer offset, Integer limit) {
        return botanicalObjectManager.search(scientificName, organization, hasImage, offset, limit);
    }
}
