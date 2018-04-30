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

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.jpa.FrmwrkaccessOrganisation;

/**
 *
 * @author fhafner
 */
public class AccessOrganisationResult {

    private Long id;
    private boolean allowDeny;
    private Long userId;
    private Long organisationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(boolean allowDeny) {
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

    public AccessOrganisationResult() {
    }

    public AccessOrganisationResult(FrmwrkaccessOrganisation frmwrkaccessOrganisation) {
        this.id = frmwrkaccessOrganisation.getId();
        this.allowDeny = frmwrkaccessOrganisation.getAllowDeny();
        this.organisationId = frmwrkaccessOrganisation.getOrganisationId().getId();
        this.userId = frmwrkaccessOrganisation.getUserId().getId();
    }

    /**
     * Helper function for converting a list of frmwrkaccessOrganisation entries
     * to AccessOrganisationResult
     *
     * @param frmwrkaccessOrganisationList
     * @return
     */
    public static List<AccessOrganisationResult> fromList(List<FrmwrkaccessOrganisation> frmwrkaccessOrganisationList) {
        List<AccessOrganisationResult> accessOrganisationResult = new ArrayList<>();

        if (frmwrkaccessOrganisationList != null) {
            for (FrmwrkaccessOrganisation accessOrganisation : frmwrkaccessOrganisationList) {
                accessOrganisationResult.add(new AccessOrganisationResult(accessOrganisation));
            }
        }

        return accessOrganisationResult;
    }
}
