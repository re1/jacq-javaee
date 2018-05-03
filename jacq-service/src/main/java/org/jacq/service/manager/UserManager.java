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
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.rest.EmploymentTypeResult;
import org.jacq.common.model.rest.RoleResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.model.rest.UserTypeResult;
import org.jacq.common.model.jpa.FrmwrkEmploymentType;
import org.jacq.common.model.jpa.FrmwrkRole;
import org.jacq.common.model.jpa.FrmwrkUser;
import org.jacq.common.model.jpa.FrmwrkUserType;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.rest.UserService;

/**
 *
 * @author fhafner
 */
public class UserManager {

    @Inject
    protected SecurityManager sessionManager;

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    @Transactional
    public List<UserResult> search(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription, Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FrmwrkUser> cq = cb.createQuery(FrmwrkUser.class);
        Root<FrmwrkUser> bo = cq.from(FrmwrkUser.class);

        // select result list
        cq.select(bo);

        // apply search criteria
        applySearchCriteria(cb, cq, bo, id, username, birthdate, userType, employmentType, organisationDescription);

        // convert to typed query and apply offset / limit
        TypedQuery<FrmwrkUser> userSearchQuery = em.createQuery(cq);
        if (offset != null) {
            userSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            userSearchQuery.setMaxResults(limit);
        }

        // finally fetch the results
        ArrayList<UserResult> results = new ArrayList<>();
        List<FrmwrkUser> userResults = userSearchQuery.getResultList();
        for (FrmwrkUser user : userResults) {
            UserResult userResult = new UserResult(user);

            // add user object to result list
            results.add(userResult);
        }

        return results;
    }

    @Transactional
    public int searchCount(Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrmwrkUser> bo = cq.from(FrmwrkUser.class);

        // count result
        cq.select(cb.count(bo));

        // apply search criteria
        applySearchCriteria(cb, cq, bo, id, username, birthdate, userType, employmentType, organisationDescription);

        // run query and return count
        return em.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * @see UserService#load(java.lang.Long)
     */
    @Transactional
    public UserResult load(Long id) {
        FrmwrkUser frmwrkUser = em.find(FrmwrkUser.class, id);
        if (frmwrkUser != null) {
            return new UserResult(frmwrkUser);
        }

        return null;
    }

    /**
     * @see UserService#save(org.jacq.common.model.UserResult)
     */
    @Transactional
    public UserResult save(UserResult userResult) {
        FrmwrkUser frmwrkUser = null;
        if (userResult.getId() != null) {
            frmwrkUser = em.find(FrmwrkUser.class, userResult.getId());
        } else {
            frmwrkUser = new FrmwrkUser();
            frmwrkUser.setFrmwrkRoleList(new ArrayList<FrmwrkRole>());
        }
        if (frmwrkUser != null) {
            frmwrkUser.setFirstname(userResult.getFirstname());
            frmwrkUser.setLastname(userResult.getLastname());
            frmwrkUser.setUsername(userResult.getUsername());
            frmwrkUser.setTitlePrefix(userResult.getTitlePrefix());
            frmwrkUser.setTitleSuffix(userResult.getTitleSuffix());
            frmwrkUser.setBirthdate(userResult.getBirthdate());
            frmwrkUser.setOrganisationId(em.find(TblOrganisation.class, userResult.getOrganisationId()));
            frmwrkUser.setEmploymentTypeId(em.find(FrmwrkEmploymentType.class, userResult.getEmploymentTypeId()));
            frmwrkUser.setUserTypeId(em.find(FrmwrkUserType.class, userResult.getUserTypeId()));
            frmwrkUser.getFrmwrkRoleList().clear();
            for (RoleResult role : userResult.getRoleList()) {
                FrmwrkRole frmwrkRole = em.find(FrmwrkRole.class, role.getRoleId());
                if (frmwrkRole != null) {
                    frmwrkUser.getFrmwrkRoleList().add(frmwrkRole);
                }
            }

            // setting the password requires salt to be re-created and hash the password
            if (!StringUtils.isBlank(userResult.getPassword())) {
                frmwrkUser.setSalt(generateSalt());
                frmwrkUser.setPassword(DigestUtils.sha1Hex(userResult.getPassword() + DigestUtils.sha1Hex(frmwrkUser.getSalt())));
            }

            if (frmwrkUser.getId() != null) {
                em.merge(frmwrkUser);
            } else {
                em.persist(frmwrkUser);
            }

            return new UserResult(frmwrkUser);
        }
        return null;
    }

    @Transactional
    public List<UserTypeResult> findAllUserType() {
        // Create Query to get FrmwrkUserType List
        Query query = em.createNamedQuery("FrmwrkUserType.findAll");

        // finally fetch the results
        ArrayList<UserTypeResult> results = new ArrayList<>();
        List<FrmwrkUserType> userTypeResults = query.getResultList();
        for (FrmwrkUserType userType : userTypeResults) {
            UserTypeResult userTypeResult = new UserTypeResult(userType);

            // add botanical object to result list
            results.add(userTypeResult);
        }

        return results;
    }

    @Transactional
    public List<EmploymentTypeResult> findAllEmploymentType() {
        // Create Query to get FrmwrkUserType List
        Query query = em.createNamedQuery("FrmwrkEmploymentType.findAll");

        // finally fetch the results
        ArrayList<EmploymentTypeResult> results = new ArrayList<>();
        List<FrmwrkEmploymentType> employmentTypeResults = query.getResultList();
        for (FrmwrkEmploymentType employmentType : employmentTypeResults) {
            EmploymentTypeResult employmentTypeResult = new EmploymentTypeResult(employmentType);

            // add botanical object to result list
            results.add(employmentTypeResult);
        }

        return results;
    }

    @Transactional
    public List<RoleResult> findAllRole() {
        // Create Query to get FrmwrkRole List
        Query query = em.createNamedQuery("FrmwrkRole.findAll");

        // finally fetch the results
        ArrayList<RoleResult> results = new ArrayList<>();
        List<FrmwrkRole> roleResults = query.getResultList();
        for (FrmwrkRole role : roleResults) {
            RoleResult roleResult = new RoleResult(role);

            // add botanical object to result list
            results.add(roleResult);
        }

        return results;
    }

    /**
     * @see UserService#authenticate(java.lang.String, java.lang.String)
     */
    @Transactional
    public UserResult authenticate(String username, String password) {
        TypedQuery<FrmwrkUser> userQuery = em.createNamedQuery("FrmwrkUser.findByUsername", FrmwrkUser.class);
        userQuery.setParameter("username", username);
        List<FrmwrkUser> userList = userQuery.getResultList();
        if (userList != null && userList.size() > 0) {
            FrmwrkUser frmwrkUser = userList.get(0);

            // Now check password
            String passwordHash = DigestUtils.sha1Hex(password + DigestUtils.sha1Hex(frmwrkUser.getSalt()));
            if (passwordHash.equals(frmwrkUser.getPassword())) {
                return new UserResult(frmwrkUser);
            }
        }

        return null;
    }

    /**
     *
     * @param cb
     * @param cq
     * @param bo
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param organisationDescription
     */
    protected void applySearchCriteria(CriteriaBuilder cb, CriteriaQuery cq, Root<FrmwrkUser> bo, Long id, String username, Date birthdate, String userType, String employmentType, String organisationDescription) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            path = bo.get("id");
            predicates.add(cb.equal(path, id));
        }

        if (username != null) {
            path = bo.get("username");
            predicates.add(cb.like(path, username + "%"));
        }

        if (birthdate != null) {
            path = bo.get("birthdate");
            predicates.add(cb.equal(path, birthdate));
        }

        if (userType != null) {
            Join<FrmwrkUser, FrmwrkUserType> frmwrkUserType = bo.join("userTypeId", JoinType.LEFT);
            path = frmwrkUserType.get("type");
            predicates.add(cb.like(path, userType + "%"));
        }

        if (employmentType != null) {
            Join<FrmwrkUser, FrmwrkEmploymentType> frmwrkEmploymentType = bo.join("employmentTypeId", JoinType.LEFT);
            path = frmwrkEmploymentType.get("type");
            predicates.add(cb.like(path, employmentType + "%"));
        }

        if (organisationDescription != null) {
            Join<FrmwrkUser, TblOrganisation> tblOrganisation = bo.join("organisationId", JoinType.LEFT);
            path = tblOrganisation.get("description");
            predicates.add(cb.like(path, organisationDescription + "%"));
        }

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));
    }

    /**
     * Small helper function for generating a salt for the password hash
     *
     * @return
     */
    protected String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    @Transactional
    public UserResult update(String password) {
        FrmwrkUser frmwrkUser = em.find(FrmwrkUser.class, sessionManager.getUser().getId());
        frmwrkUser.setSalt(generateSalt());
        frmwrkUser.setPassword(DigestUtils.sha1Hex(password + DigestUtils.sha1Hex(frmwrkUser.getSalt())));
        em.merge(frmwrkUser);
        return new UserResult(frmwrkUser);
    }

}
