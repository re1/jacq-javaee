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
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblIndexSeminumRevision;
import org.jacq.common.model.jpa.TblOrganisation;
import org.jacq.common.model.rest.IndexSeminumResult;

/**
 *
 * @author fhafner
 */
public class IndexSeminumManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     *
     * @param indexSeminumResult
     * @return
     */
    @Transactional
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult) {
        TblIndexSeminumRevision tblIndexSeminumRevision = new TblIndexSeminumRevision();
        tblIndexSeminumRevision.setName(indexSeminumResult.getName());
        Calendar rightNow = Calendar.getInstance();
        tblIndexSeminumRevision.setTimestamp(rightNow.getTime());

        TblOrganisation tblOrganisation = em.find(TblOrganisation.class, 1L);
        tblOrganisation = findIndexSeminumStart(tblOrganisation);
        if (tblOrganisation == null) {
            return null;
        }

        List<Long> organisationIdList = findchilds(tblOrganisation);
        organisationIdList.add(tblOrganisation.getId());

        Query query = em.createQuery("SELECT t FROM TblBotanicalObject t WHERE t.organisationId in :organisationIdList").setParameter("organisationIdList", organisationIdList);
        List<TblBotanicalObject> botanicalObjectList = query.getResultList();

        em.persist(tblIndexSeminumRevision);

        return null;
    }

    /**
     * Find the Head of the Organisation Tree
     *
     * @param tblorganisation
     * @return
     */
    protected TblOrganisation findIndexSeminumStart(TblOrganisation tblOrganisation) {
        while (tblOrganisation.getIndexSeminumStart() != true) {
            if (tblOrganisation.getParentOrganisationId() == null) {
                return null;
            }
            tblOrganisation = em.find(TblOrganisation.class, tblOrganisation.getParentOrganisationId().getId());
        }
        return tblOrganisation;

    }

    /**
     *
     * @param tblOrganisation
     * @return
     */
    protected List<Long> findchilds(TblOrganisation tblOrganisation) {
        List<Long> organisationIdList = new ArrayList<>();
        for (TblOrganisation organisation : tblOrganisation.getTblOrganisationList()) {
            organisationIdList.add(organisation.getId());
            organisationIdList.addAll(findchilds(organisation));
        }
        return organisationIdList;

    }

}
