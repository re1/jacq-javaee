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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.jacq.common.model.rest.EmploymentTypeResult;
import org.jacq.common.model.rest.RoleResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.model.rest.UserTypeResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.UserService;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class UserEditController {

    @Inject
    protected SessionController sessionController;

    protected Long id;

    protected UserResult user;

    protected UserService userService;

    protected OrganisationService organisationService;

    protected List<OrganisationResult> organisations;

    protected List<UserTypeResult> userTypes;

    protected List<EmploymentTypeResult> employmentTypes;

    protected List<SelectItem> roles;

    protected List<String> selectedRoleIds;

    @PostConstruct
    public void init() {
        this.userService = ServicesUtil.getUserService();

        this.user = new UserResult();

        this.organisationService = ServicesUtil.getOrganisationService();

        this.organisations = this.organisationService.findAll();

        this.userTypes = this.userService.findAllUserType();

        this.employmentTypes = this.userService.findAllEmploymentType();

        List<RoleResult> roleResults = this.userService.findAllRole();
        this.roles = new ArrayList<>();
        for (RoleResult role : roleResults) {
            this.roles.add(new SelectItem(role.getRoleId(), role.getName()));
        }
        this.selectedRoleIds = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

        if (this.id != null) {
            this.user = this.userService.load(this.id);
            for (RoleResult role : this.user.getRoleList()) {
                this.selectedRoleIds.add(role.getRoleId().toString());
            }
        }
    }

    public UserResult getUser() {
        return user;
    }

    public String edit() {
        this.user.getRoleList().clear();
        for (String roleId : this.selectedRoleIds) {
            this.user.getRoleList().add(new RoleResult(Long.parseLong(roleId)));
        }
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

    public List<SelectItem> getRoles() {
        return roles;
    }

    public List<String> getSelectedRoleIds() {
        return selectedRoleIds;
    }

    public void setSelectedRoleIds(List<String> selectedRoleIds) {
        this.selectedRoleIds = selectedRoleIds;
    }

    public void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

}
