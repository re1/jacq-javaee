/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.SeedOrderResult;
import org.jacq.common.rest.SeedExchangeService;
import org.jacq.service.manager.SeedExchangeManager;

/**
 *
 * @author wkoller
 */
public class SeedExchangeServiceImpl implements SeedExchangeService {

    @Inject
    protected SeedExchangeManager seedExchangeManager;

    @Override
    public SeedOrderResult find(Long seedOrderId) {
        return seedExchangeManager.find(seedOrderId);
    }

    @Override
    public SeedOrderResult save(SeedOrderResult seedOrderResult) {
        return seedExchangeManager.save(seedOrderResult);
    }

    @Override
    public List<SeedOrderResult> findAll() {
        return seedExchangeManager.findAll();
    }
}
