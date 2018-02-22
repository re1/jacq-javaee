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
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.jacq.input.security.InputCallerPrincipal;

/**
 * Controller for handling logins of users
 *
 * @author wkoller
 */
@RequestScoped
@ManagedBean
public class LoginController {

    @Inject
    protected SecurityContext securityContext;

    @Inject
    protected SessionManager sessionController;

    protected String username;
    protected String password;

    public String login() {
        Credential credential = new UsernamePasswordCredential(username, password);

        AuthenticationStatus as = securityContext.authenticate(getRequest(), getResponse(), AuthenticationParameters.withParams().credential(credential));

        if (as.equals(AuthenticationStatus.SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            FacesContext.getCurrentInstance().responseComplete();

            return null;
        } else if (as.equals(AuthenticationStatus.SUCCESS)) {
            // remember authorization header in session controller
            sessionController.setAuthorizationHeader("Basic " + Base64.encodeBase64String((username + ":" + password).getBytes()));
            // remember user object in session controller
            sessionController.setUser(((InputCallerPrincipal) securityContext.getCallerPrincipal()).getUser());

            // redirect to manage page
            return "livingplant/manage.xhtml?faces-redirect=true";
        }

        // by default send error message and stay on page
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "Authentication failed", null));

        return null;
    }

    /**
     * Logout the user and redirect to login page
     *
     * @return
     * @throws ServletException
     */
    public String logout() throws ServletException {
        this.getRequest().logout();
        this.getRequest().getSession().invalidate();

        return "login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext()
                .getExternalContext()
                .getRequest();
    }

    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext()
                .getExternalContext()
                .getResponse();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

}
