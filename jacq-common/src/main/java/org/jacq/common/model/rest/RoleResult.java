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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.FrmwrkRole;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleResult {

    protected Long roleId;
    protected String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleResult() {
    }

    /**
     * Helper Constructor for Save of Roles
     *
     * @param roleId
     */
    public RoleResult(Long roleId) {
        this.roleId = roleId;
    }

    public RoleResult(FrmwrkRole role) {
        this.roleId = role.getRoleId();
        this.name = role.getName();
    }

    /**
     * Helper function for converting a list of role entries
     *
     * @param roleList
     * @return
     */
    public static List<RoleResult> fromList(List<FrmwrkRole> roleList) {
        List<RoleResult> roleResult = new ArrayList<>();

        if (roleList != null) {
            for (FrmwrkRole role : roleList) {
                roleResult.add(new RoleResult(role));
            }
        }

        return roleResult;
    }

}
