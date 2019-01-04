/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.AcquisitionSourceResult;
import org.jacq.common.rest.AcquisitionService;
import org.jacq.service.manager.AcquisitionManager;

/**
 * @see AcquisitionService
 * @author wkoller
 */
public class AcquisitionServiceImpl implements AcquisitionService {

    @Inject
    protected AcquisitionManager acquisitionManager;

    /**
     * @see AcquisitionService#sourceSearch(java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     */
    @Override
    public List<AcquisitionSourceResult> sourceSearch(String name, Integer offset, Integer limit) {
        return acquisitionManager.sourceSearch(name, offset, limit);
    }

    /**
     * @see AcquisitionService#sourceLoad(java.lang.Long)
     */
    @Override
    public AcquisitionSourceResult sourceLoad(Long locationId) {
        return acquisitionManager.sourceLoad(locationId);
    }
}
