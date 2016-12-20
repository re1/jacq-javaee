/*
 * Copyright 2016 wkoller.
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.model.BotanicalObjectResult;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.rest.BotanicalObjectService;

/**
 *
 * @author wkoller
 */
public class BotanicalObjectManager {

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @see BotanicalObjectService#search(java.lang.String, java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public List<BotanicalObjectResult> search(String scientificName, String organization, Boolean hasImage, Integer offset, Integer limit) {
        TypedQuery<TblBotanicalObject> botanicalObjectSearchQuery = em.createNamedQuery("TblBotanicalObject.findByScientificName", TblBotanicalObject.class);
        botanicalObjectSearchQuery.setParameter("scientificName", "%" + scientificName.toLowerCase() + "%");
        if (offset != null) {
            botanicalObjectSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            botanicalObjectSearchQuery.setMaxResults(limit);
        }

        return BotanicalObjectResult.fromList(botanicalObjectSearchQuery.getResultList());
    }
}
