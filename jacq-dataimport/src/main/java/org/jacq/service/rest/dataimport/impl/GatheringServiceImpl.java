/*
 * Copyright 2018 fhafner.
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
package org.jacq.service.rest.dataimport.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.rest.GatheringService;
import org.jacq.service.dataimport.manager.GatheringManager;

/**
 *
 * @author fhafner
 */
public class GatheringServiceImpl implements GatheringService {

    @Inject
    protected GatheringManager gatheringManager;

    /**
     * @see GatheringService#locationFind(java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     */
    @Override
    public List<LocationResult> locationFind(String location, Integer offset, Integer count) {
        return gatheringManager.locationFind(location, offset, count);
    }

    /**
     * @see GatheringService#locationLoad(java.lang.Long)
     */
    @Override
    public LocationResult locationLoad(Long locationId) {
        return gatheringManager.locationLoad(locationId);
    }
}
