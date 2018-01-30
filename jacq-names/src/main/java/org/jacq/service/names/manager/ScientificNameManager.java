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
package org.jacq.service.names.manager;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.TblCultivar;
import org.jacq.common.model.jpa.TblHabitusType;
import org.jacq.common.model.jpa.TblNomEpithet;
import org.jacq.common.model.jpa.TblNomName;
import org.jacq.common.model.jpa.TblNomSubstantive;
import org.jacq.common.model.jpa.TblScientificNameInformation;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.HabitusTypeResult;
import org.jacq.common.model.rest.ScientificNameInformationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.names.ScientificNameService;

/**
 * Manager class for handling scientific name business logic
 *
 * @author wkoller
 */
@ManagedBean
public class ScientificNameManager {

    @PersistenceContext(unitName = "jacq-names")
    protected EntityManager em;

    /**
     * @see ScientificNameService#find(java.lang.String, java.lang.Boolean)
     */
    @Transactional
    public List<ScientificNameResult> find(String search, Boolean autocomplete) {
        List<ScientificNameResult> results = new ArrayList<>();

        // split search string
        if (!StringUtils.isBlank(search)) {
            search = search.trim();
            String[] searchComponents = search.split(" ");
            List<TblNomSubstantive> substantiveList = null;
            List<TblNomEpithet> firstEpithetList = null;
            List<TblNomEpithet> secondEpithetList = null;

            // first of all search for substantive entries
            TypedQuery<TblNomSubstantive> substantiveQuery = em.createNamedQuery("TblNomSubstantive.findLikeSubstantive", TblNomSubstantive.class);
            substantiveQuery.setParameter("substantive", searchComponents[0] + ((autocomplete) ? "%" : ""));
            substantiveList = substantiveQuery.getResultList();
            // check if we found at least one hit in substantive list
            if (substantiveList != null && substantiveList.size() > 0) {
                // check if we need to search for a first epithet
                if (searchComponents.length > 1) {
                    TypedQuery<TblNomEpithet> firstEpithetQuery = em.createNamedQuery("TblNomEpithet.findLikeEpithet", TblNomEpithet.class);
                    firstEpithetQuery.setParameter("epithet", searchComponents[1] + ((autocomplete) ? "%" : ""));
                    firstEpithetList = firstEpithetQuery.getResultList();

                    // check if we found at least one hit for the first epithet
                    if (firstEpithetList != null && firstEpithetList.size() > 0) {
                        // check if we need to search for a second epithet
                        if (searchComponents.length > 2) {
                            TypedQuery<TblNomEpithet> secondEpithetQuery = em.createNamedQuery("TblNomEpithet.findLikeEpithet", TblNomEpithet.class);
                            secondEpithetQuery.setParameter("epithet", searchComponents[2] + ((autocomplete) ? "%" : ""));
                            secondEpithetList = secondEpithetQuery.getResultList();
                        }
                    }
                }

                // finally check which components did match and what we should search for
                TypedQuery<TblNomName> nameQuery = null;
                // trinomial
                if (firstEpithetList != null && firstEpithetList.size() > 0 && secondEpithetList != null && secondEpithetList.size() > 0) {
                    nameQuery = em.createNamedQuery("TblNomName.findBySubstantiveAndFirstEpithetAndSecondEpithet", TblNomName.class);
                    nameQuery.setParameter("substantiveIds", substantiveList);
                    nameQuery.setParameter("firstEpithetIds", firstEpithetList);
                    nameQuery.setParameter("secondEpithetIds", secondEpithetList);
                }
                // binomial
                else if (firstEpithetList != null && firstEpithetList.size() > 0) {
                    nameQuery = em.createNamedQuery("TblNomName.findBySubstantiveAndFirstEpithet", TblNomName.class);
                    nameQuery.setParameter("substantiveIds", substantiveList);
                    nameQuery.setParameter("firstEpithetIds", firstEpithetList);
                }
                // uninomial
                else {
                    nameQuery = em.createNamedQuery("TblNomName.findBySubstantive", TblNomName.class);
                    nameQuery.setParameter("substantiveIds", substantiveList);
                }
                // for now we limit to 10 results maximum
                nameQuery.setFirstResult(0);
                nameQuery.setMaxResults(10);

                // finally fetch the results and convert it to a scientific name result
                results = ScientificNameResult.fromList(nameQuery.getResultList());
            }
        }

        return results;
    }

    /**
     * @see ScientificNameService#load(java.lang.Long)
     */
    @Transactional
    public ScientificNameResult load(Long scientificNameId) {
        TblNomName nomName = em.find(TblNomName.class, scientificNameId);

        if (nomName != null) {
            return new ScientificNameResult(nomName);

        }
        return null;
    }

    /**
     * @see ScientificNameService#cultivarFind(java.lang.Long)
     */
    @Transactional
    public List<CultivarResult> cultivarFind(Long scientificNameId) {
        List<CultivarResult> results = new ArrayList<>();

        TypedQuery<TblCultivar> cultivarQuery = em.createNamedQuery("TblCultivar.findByScientificNameId", TblCultivar.class);
        cultivarQuery.setParameter("scientificNameId", scientificNameId);
        List<TblCultivar> tblCultivars = cultivarQuery.getResultList();

        if (tblCultivars != null && tblCultivars.size() > 0) {
            return CultivarResult.fromList(tblCultivars);
        }

        return results;
    }

    /**
     * @see ScientificNameService#cultivarLoad(java.lang.Long)
     */
    @Transactional
    public CultivarResult cultivarLoad(Long cultivarId) {
        TblCultivar cultivar = em.find(TblCultivar.class, cultivarId);

        if (cultivar != null) {
            return new CultivarResult(cultivar);
        }

        return null;
    }

    /**
     * @see ScientificNameService#scientificNameInformationLoad(java.lang.Long)
     */
    @Transactional
    public ScientificNameInformationResult scientificNameInformationLoad(Long scientificNameId) {
        TblScientificNameInformation scientificNameInformation = em.find(TblScientificNameInformation.class, scientificNameId);

        if (scientificNameInformation != null) {
            return new ScientificNameInformationResult(scientificNameInformation);
        }

        return null;
    }

    /**
     * @see ScientificNameService#findAllHabitusType()
     */
    @Transactional
    public List<HabitusTypeResult> findAllHabitusType() {
        TypedQuery<TblHabitusType> habitusTypeQuery = em.createNamedQuery("TblHabitusType.findAll", TblHabitusType.class);

        return HabitusTypeResult.fromList(habitusTypeQuery.getResultList());
    }
}
