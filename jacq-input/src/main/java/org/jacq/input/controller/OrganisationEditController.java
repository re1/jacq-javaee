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
package org.jacq.input.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.rest.GroupResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.UserService;
import org.jacq.input.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class OrganisationEditController {

    protected Long organisationId;

    protected OrganisationResult organisation;

    protected OrganisationService organisationService;

    protected UserService userService;

    protected List<OrganisationResult> organisations;

    protected List<UserResult> users;

    protected List<GroupResult> groups;

    @PostConstruct
    public void init() {
        this.organisationService = ServicesUtil.getOrganisationService();

        this.userService = ServicesUtil.getUserService();

        this.organisations = this.organisationService.findAll();

        this.organisation = new OrganisationResult();

        this.users = this.userService.findAll();

        this.groups = this.userService.findAllGroup();
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;

        if (this.organisationId != null) {
            this.organisation = this.organisationService.load(this.organisationId);
        }
    }

    public OrganisationResult getOrganisation() {
        return organisation;
    }

    public String edit() {
        this.organisation = this.organisationService.save(this.organisation);

        return null;
    }

    public List<OrganisationResult> getOrganisations() {
        return organisations;
    }

    public List<UserResult> getUsers() {
        return users;
    }

    public List<GroupResult> getGroups() {
        return groups;
    }
}