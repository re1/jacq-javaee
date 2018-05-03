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
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.rest.AuthorizationService;
import org.jacq.service.manager.AuthorizationManager;

/**
 *
 * @author fhafner
 */
public class AuthorizationServiceImpl implements AuthorizationService {

    @Inject
    protected AuthorizationManager authorizationManager;

    @Override
    public List<UserResult> search(Long id, boolean allowDeny, Long userId, Long organisationId, Integer offset, Integer limit) {
        return authorizationManager.search(id, allowDeny, userId, organisationId, offset, limit);
    }

    @Override
    public int searchCount(Long id, boolean allowDeny, Long userId, Long organisationId) {
        return authorizationManager.searchCount(id, allowDeny, userId, organisationId);
    }

}
