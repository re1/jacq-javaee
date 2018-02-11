/*
 * Copyright 2017 fhafner.
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

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.EmploymentTypeResult;
import org.jacq.common.model.rest.GroupResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.model.rest.UserTypeResult;
import org.jacq.common.rest.UserService;
import org.jacq.service.manager.UserManager;

/**
 *
 * @author fhafner
 */
public class UserServiceImpl implements UserService {

    @Inject
    protected UserManager userManager;

    @Override
    public List<UserResult> search(Long id, String username, Date birthdate, String usertype, String employmentType, String organisationDescription, Integer offset, Integer limit) {
        return userManager.search(id, username, birthdate, usertype, employmentType, organisationDescription, offset, limit);
    }

    @Override
    public int searchCount(Long id, String username, Date birthdate, String usertype, String employmentType, String organisationDescription) {
        return userManager.searchCount(id, username, birthdate, usertype, employmentType, organisationDescription);
    }

    @Override
    public List<UserResult> findAll() {
        return userManager.search(null, null, null, null, null, null, null, null);
    }

    @Override
    public UserResult load(Long id) {
        return userManager.load(id);
    }

    @Override
    public UserResult save(UserResult userResult) {
        return userManager.save(userResult);
    }

    @Override
    public List<UserTypeResult> findAllUserType() {
        return userManager.findAllUserType();
    }

    @Override
    public List<EmploymentTypeResult> findAllEmploymentType() {
        return userManager.findAllEmploymentType();
    }

    @Override
    public List<GroupResult> findAllGroup() {
        return userManager.findAllGroup();
    }

    /**
     * @see UserService#authenticate(java.lang.String, java.lang.String)
     */
    @Override
    public UserResult authenticate(String username, String password) {
        return userManager.authenticate(username, password);
    }

    @Override
    public UserResult update(String password) {
        return userManager.update(password);
    }

}
