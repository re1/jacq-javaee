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
package org.jacq.service.rest.names.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.service.names.manager.ScientificNameManager;

/**
 * @see ScientificNameService
 * @author wkoller
 */
public class ScientificNameServiceImpl implements ScientificNameService {

    @Inject
    protected ScientificNameManager scientificNameManager;

    /**
     * @see ScientificNameService#find(java.lang.String, java.lang.Boolean)
     */
    @Override
    public List<ScientificNameResult> find(String search, Boolean autocomplete) {
        return scientificNameManager.find(search, autocomplete);
    }

    /**
     * @see ScientificNameService#load(java.lang.Long)
     */
    @Override
    public ScientificNameResult load(Long scientificNameId) {
        return scientificNameManager.load(scientificNameId);
    }

    @Override
    public List<CultivarResult> cultivarFind(Long scientificNameId) {
        return scientificNameManager.cultivarFind(scientificNameId);
    }

    @Override
    public CultivarResult cultivarLoad(Long cultivarId) {
        return scientificNameManager.cultivarLoad(cultivarId);
    }
}
