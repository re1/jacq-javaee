/*
 * Copyright 2017 wkoller.
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
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import org.jacq.service.manager.DerivativeManager;
import org.jacq.common.model.BotanicalObjectDerivative;
import org.jacq.common.rest.DerivativeService;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class DerivativeServiceImpl implements DerivativeService {

    @Inject
    protected DerivativeManager derivativeManager;

    @Override
    public List<BotanicalObjectDerivative> find(String type, Long derivativeId, String orderColumn, String orderDirection, Integer offset, Integer count) {
        return derivativeManager.find(type, derivativeId, orderColumn, orderDirection, offset, count);
    }

    @Override
    public int count(String type, Long derivativeId) {
        return derivativeManager.count(type, derivativeId);
    }

}
