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
package org.jacq.service.security;

import java.util.HashSet;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import org.jacq.common.model.rest.RoleResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.service.manager.UserManager;

/**
 *
 * @author wkoller
 */
@BasicAuthenticationMechanismDefinition(realmName = "JACQ Services")
@ApplicationScoped
@DeclareRoles({"authenticated", "aclBotanicalObject", "aclClassification", "aclOrganisation", "assignLabelType", "clearLabelType", "createLivingplant", "createOrganisation",
    "createScientificNameInformation", "createTreeRecordFile", "createUser", "deleteLivingplant", "deleteOrganisation", "deleteTreeRecordFile", "deleteUser", "indexSeminum", "inventory",
    "readLivingplant", "showClassificationBrowser", "showStatistics"})
public class ServiceIdentityStore implements IdentityStore {

    @Inject
    protected UserManager userManager;

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        UserResult user = userManager.authenticate(usernamePasswordCredential.getCaller(), usernamePasswordCredential.getPasswordAsString());

        if (user != null) {
            HashSet<String> userRoleHashSet = new HashSet<>();
            for (RoleResult roleResult : user.getRoleList()) {
                userRoleHashSet.add(roleResult.getName());
            }
            userRoleHashSet.add("authenticated");

            return new CredentialValidationResult(new ServiceCallerPrincipal(user.getUsername(), user), userRoleHashSet);
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}
