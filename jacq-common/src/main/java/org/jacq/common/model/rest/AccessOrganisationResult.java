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
package org.jacq.common.model.rest;

import org.jacq.common.model.jpa.FrmwrkaccessOrganisation;

/**
 *
 * @author fhafner
 */
public class AccessOrganisationResult {

    private Long id;
    private Boolean allowDeny;
    private Long userId;
    private Long organisationId;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(Boolean allowDeny) {
        this.allowDeny = allowDeny;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccessOrganisationResult() {
    }

    public AccessOrganisationResult(FrmwrkaccessOrganisation frmwrkaccessOrganisation, String username) {
        this.id = frmwrkaccessOrganisation.getId() != null ? frmwrkaccessOrganisation.getId() : null;
        this.allowDeny = frmwrkaccessOrganisation.getAllowDeny();
        this.organisationId = frmwrkaccessOrganisation.getOrganisationId().getId() != null ? frmwrkaccessOrganisation.getOrganisationId().getId() : null;
        this.userId = frmwrkaccessOrganisation.getUserId().getId() != null ? frmwrkaccessOrganisation.getUserId().getId() : null;
        this.username = username != null ? username : null;
    }

    public AccessOrganisationResult(Long userId, Long organisationId, String username) {
        this.organisationId = userId != null ? userId : null;
        this.userId = organisationId != null ? organisationId : null;
        this.username = username != null ? username : null;
        this.allowDeny = null;
    }

}
