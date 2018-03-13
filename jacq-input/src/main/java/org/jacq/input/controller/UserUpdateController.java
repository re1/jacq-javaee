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
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.rest.UserService;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class UserUpdateController {

    @Inject
    protected SessionController sessionController;

    protected String newPassword;
    protected String newPasswordValidate;

    protected UserResult user;

    protected UserService userService;

    @PostConstruct
    public void init() {
        this.userService = ServicesUtil.getUserService();

        this.user = new UserResult();

    }

    public String update() {
        if (!this.newPasswordValidate.isEmpty() && this.newPassword.equals(this.newPasswordValidate)) {
            this.user = this.userService.update(this.newPassword);
        } else {
            sessionController.setGrowlMessage("error", "entrynotsaved");
        }
        return null;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordValidate() {
        return newPasswordValidate;
    }

    public void setNewPasswordValidate(String newPasswordValidate) {
        this.newPasswordValidate = newPasswordValidate;
    }

    public void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

}
