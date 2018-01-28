/*
 * Copyright 2018 wkoller.
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

import org.jacq.input.SessionManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.jacq.common.manager.JacqConfig;
import org.jacq.input.ApplicationManager;

/**
 * JSF Session scoped bean for handling session wide settings
 *
 * @author wkoller
 */
@ManagedBean
@SessionScoped
public class SessionController {

    @Inject
    protected SessionManager sessionManager;

    @Inject
    protected ApplicationManager applicationManager;

    public String getLanguageTag() {
        return sessionManager.getLanguageTag();
    }

    public void setLanguageTag(String languageTag) {
        sessionManager.setLanguageTag(languageTag);
    }

    public Long getIndetId() {
        return applicationManager.getJacqPortalConfig().getLong(JacqConfig.GENERAL_INDET_ID);
    }
}