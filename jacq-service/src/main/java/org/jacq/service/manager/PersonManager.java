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
package org.jacq.service.manager;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jacq.common.model.jpa.TblPerson;
import org.jacq.common.model.rest.PersonResult;
import org.jacq.common.rest.PersonService;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class PersonManager {

    @PersistenceContext
    protected EntityManager em;

    /**
     * @see PersonService#search(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    public List<PersonResult> search(String name, Integer offset, Integer limit) {
        TypedQuery<TblPerson> tblPersonQuery = em.createNamedQuery("TblPerson.findByName", TblPerson.class);
        tblPersonQuery.setParameter("name", name);

        if (offset != null) {
            tblPersonQuery.setFirstResult(offset);
        }
        if (limit != null) {
            tblPersonQuery.setMaxResults(limit);
        }

        return PersonResult.fromList(tblPersonQuery.getResultList());
    }

    /**
     * @see PersonService#load(java.lang.Long)
     */
    public PersonResult load(Long personId) {
        return new PersonResult(em.find(TblPerson.class, personId));
    }
}
