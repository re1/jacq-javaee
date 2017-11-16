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
package org.jacq.input.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.EmploymentTypeResult;
import org.jacq.common.model.OrganisationResult;
import org.jacq.common.model.UserResult;
import org.jacq.common.model.UserTypeResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.UserService;
import org.jacq.input.util.ServicesUtil;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class UserEditController {

    protected Long id;

    protected UserResult user;

    protected UserService userService;

    protected OrganisationService organisationService;

    protected List<OrganisationResult> organisations;

    protected List<UserTypeResult> userTypes;

    protected List<EmploymentTypeResult> employmentTypes;

    @PostConstruct
    public void init() {
        this.userService = ServicesUtil.getUserService();

        this.user = new UserResult();

        this.organisationService = ServicesUtil.getOrganisationService();

        this.organisations = this.organisationService.findAll();

        this.userTypes = this.userService.findAllUserType();

        this.employmentTypes = this.userService.findAllEmploymentType();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

        if (this.id != null) {
            this.user = this.userService.load(this.id);
        }
    }

    public UserResult getUser() {
        return user;
    }

    public String edit() {
        this.user = this.userService.save(this.user);

        return null;
    }

    public List<OrganisationResult> getOrganisations() {
        return organisations;
    }

    public List<UserTypeResult> getUserTypes() {
        return userTypes;
    }

    public List<EmploymentTypeResult> getEmploymentTypes() {
        return employmentTypes;
    }

}
