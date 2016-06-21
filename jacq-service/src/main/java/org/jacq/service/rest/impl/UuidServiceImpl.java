package org.jacq.service.rest.impl;

import javax.inject.Inject;
import org.jacq.common.rest.UuidService;
import org.jacq.service.manager.UuidManager;

/**
 *
 * @author wkoller
 */
public class UuidServiceImpl implements UuidService {

    @Inject
    protected UuidManager uuidManager;

    @Override
    public String resolve(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String mint(String type, int internal_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
