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

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.jacq.input.view.DerivativeSearchModel;

/**
 *
 * @author wkoller
 */
@Named
@ManagedBean
@SessionScoped
public class SessionManager implements Serializable {

    protected Locale language;
    protected DerivativeSearchModel derivativeSearchModel;
    protected String authorizationHeader;

    @PostConstruct
    public void init() {
        this.language = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        this.derivativeSearchModel = new DerivativeSearchModel();
    }

    public String getLanguageTag() {
        return language.getLanguage();
    }

    public void setLanguageTag(String languageTag) {
        this.language = Locale.forLanguageTag(languageTag);
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return derivativeSearchModel;
    }

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }
}
