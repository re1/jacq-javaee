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
package org.jacq.common.manager;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wkoller
 */
public abstract class BaseApplicationManager {

    protected HashMap<Long, List<Long>> organisationHierachyCache = new HashMap<>();

    public void addOrganisationHierachyCache(Long organisationId, List<Long> organisationIdList) {
    }

    public List<Long> findOrganisationHierachyCache(Long organisationId) {
        return null;
    }
}
