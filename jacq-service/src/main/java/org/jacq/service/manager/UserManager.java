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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.UserResult;
import org.jacq.common.model.jpa.FrmwrkUser;

/**
 *
 * @author fhafner
 */
public class UserManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    @Transactional
    public List<UserResult> search(Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FrmwrkUser> cq = cb.createQuery(FrmwrkUser.class);
        Root<FrmwrkUser> bo = cq.from(FrmwrkUser.class);

        // select result list
        cq.select(bo);

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

            // add botanical object to result list
            results.add(userResult);
        }

        return results;
    }

    @Transactional
    public int searchCount() {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrmwrkUser> bo = cq.from(FrmwrkUser.class);

        // count result
        cq.select(cb.count(bo));

        // run query and return count
        return em.createQuery(cq).getSingleResult().intValue();
    }

}
