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
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.FrmwrkGroup;
import org.jacq.common.model.jpa.FrmwrkUser;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserResult {

    protected Long id;
    protected String username;
    protected String password;
    protected String salt;
    protected String titlePrefix;
    protected String firstname;
    protected String lastname;
    protected String titleSuffix;
    protected Date birthdate;
    protected Long userTypeId;
    protected String userType;
    protected Long employmentTypeId;
    protected String employmentType;
    protected Long organisationId;
    protected String organisationDescription;
    protected List<GroupResult> groupList;

    public String getOrganisationDescription() {
        return organisationDescription;
    }

    public void setOrganisationDescription(String organisationDescription) {
        this.organisationDescription = organisationDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<GroupResult> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupResult> groupList) {
        this.groupList = groupList;
    }

    public UserResult() {
        this.groupList = new ArrayList<>();
    }

    public UserResult(FrmwrkUser user) {
        this.id = user.getId();
        this.birthdate = user.getBirthdate();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.password = user.getPassword();
        this.salt = user.getSalt();
        this.titlePrefix = user.getTitlePrefix();
        this.titleSuffix = user.getTitleSuffix();
        this.username = user.getUsername();
        this.employmentTypeId = (user.getEmploymentTypeId() != null) ? user.getEmploymentTypeId().getEmploymentTypeId() : null;
        this.userTypeId = (user.getUserTypeId() != null) ? user.getUserTypeId().getUserTypeId() : null;
        this.organisationId = (user.getOrganisationId() != null) ? user.getOrganisationId().getId() : null;
        this.organisationDescription = (user.getOrganisationId() != null) ? user.getOrganisationId().getDescription().toString() : "";
        this.userType = (user.getUserTypeId() != null) ? user.getUserTypeId().getType().toString() : "";
        this.employmentType = (user.getEmploymentTypeId() != null) ? user.getEmploymentTypeId().getType().toString() : "";
        this.groupList = (user.getFrmwrkGroupList() != null) ? GroupResult.fromList(user.getFrmwrkGroupList()) : new ArrayList<GroupResult>();

    }

    /**
     * Helper function for converting a list of User entries to Userresults
     *
     * @param userList
     * @return
     */
    public static List<UserResult> fromList(List<FrmwrkUser> userList) {
        List<UserResult> userResult = new ArrayList<>();

        if (userList != null) {
            for (FrmwrkUser user : userList) {
                userResult.add(new UserResult(user));
            }
        }

        return userResult;
    }

}
