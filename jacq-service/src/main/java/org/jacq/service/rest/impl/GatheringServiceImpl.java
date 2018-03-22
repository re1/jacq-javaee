/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.rest.GatheringService;
import org.jacq.service.manager.GatheringManager;

/**
 * @see GatheringService
 * @author wkoller
 */
public class GatheringServiceImpl implements GatheringService {

    @Inject
    protected GatheringManager gatheringManager;

    /**
     * @see GatheringService#locationFind(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<LocationResult> locationFind(String location, Integer offset, Integer count) {
        return gatheringManager.locationFind(location, offset, count);
    }

}
