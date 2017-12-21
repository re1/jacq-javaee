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
package org.jacq.common.model.jpa.custom;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author wkoller
 */
@MappedSuperclass
@NamedQueries({
    @NamedQuery(name = "RevClassification.findByUuidMinterIdAndParent", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId = :accScientificNameId ORDER BY r.order ASC, r.scientificName ASC")
    , @NamedQuery(name = "RevClassification.findByUuidMinterIdAndTopLevel", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId IS NULL ORDER BY r.order ASC, r.scientificName ASC")
    , @NamedQuery(name = "RevClassification.findByUuidMinterIdAndTopLevelAndProvinceId", query = "SELECT r FROM RevClassification r WHERE r.uuidMinterId = :uuidMinterId AND r.accScientificNameId IS NULL AND r.provinceIds LIKE :provinceId ORDER BY r.order ASC, r.scientificName ASC")
    , @NamedQuery(name = "TblBotanicalObject.findByOrganisationList", query = "SELECT t FROM TblBotanicalObject t INNER JOIN t.tblLivingPlant l WHERE l.indexSeminum = 1 and t.organisationId in :organisationList")
    , @NamedQuery(name = "TblBotanicalObject.findByLivingPlantList", query = "SELECT t FROM TblBotanicalObject t INNER JOIN t.tblLivingPlant l WHERE l.id in :tblLivingPlantList")
    , @NamedQuery(name = "TblBotanicalObject.findByNotInTblLivingPlantListAndOrangisation", query = "SELECT t FROM TblBotanicalObject t INNER JOIN t.tblLivingPlant l WHERE l.id not in (:tblLivingPlantList) and t.organisationId = (:organisationId)")
    , @NamedQuery(name = "TblClassification.findTopLevelBySource", query = "SELECT t FROM TblClassification t WHERE t.source = :source AND t.sourceId = :sourceId AND t.parentScientificNameId IS NULL")
    , @NamedQuery(name = "TblClassification.findBySourceAndParent", query = "SELECT t FROM TblClassification t WHERE t.source = :source AND t.sourceId = :sourceId AND t.parentScientificNameId = :parentScientificNameId")
    , @NamedQuery(name = "TblLivingPlant.resetImageStatus", query = "UPDATE TblLivingPlant t SET t.hasImage = FALSE, t.hasPublicImage = FALSE WHERE t.tblBotanicalObject IN ( SELECT tbo FROM TblBotanicalObject tbo WHERE tbo.organisationId IN (:organisations) )")
    , @NamedQuery(name = "TblLivingPlant.findByAccessionNumberList", query = "SELECT t FROM TblLivingPlant t WHERE t.accessionNumber in (:accessionNumberList)")
})
public class CustomNamedQueries {

}
