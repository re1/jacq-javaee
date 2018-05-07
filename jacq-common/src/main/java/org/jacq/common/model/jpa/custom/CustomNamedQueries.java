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
    , @NamedQuery(name = "TblDerivative.findByOrganisationListAndIndexSeminum", query = "SELECT d FROM TblDerivative d INNER JOIN d.tblLivingPlant l WHERE l.indexSeminum = true and d.organisationId in :organisationList")
    , @NamedQuery(name = "TblDerivative.findByLivingPlantList", query = "SELECT d FROM TblDerivative d INNER JOIN d.tblLivingPlant l WHERE l.id in :tblLivingPlantList")
    , @NamedQuery(name = "TblDerivative.findByNotInTblLivingPlantListAndOrangisation", query = "SELECT d FROM TblDerivative d INNER JOIN d.tblLivingPlant l WHERE l.id not in (:tblLivingPlantList) and d.organisationId = (:organisationId)")
    , @NamedQuery(name = "TblClassification.findTopLevelBySource", query = "SELECT t FROM TblClassification t WHERE t.source = :source AND t.sourceId = :sourceId AND t.parentScientificNameId IS NULL")
    , @NamedQuery(name = "TblClassification.findBySourceAndParent", query = "SELECT t FROM TblClassification t WHERE t.source = :source AND t.sourceId = :sourceId AND t.parentScientificNameId = :parentScientificNameId")
    , @NamedQuery(name = "TblClassification.findBySourceAndAcceptedAndScientificNameId", query = "SELECT t FROM TblClassification t WHERE t.source = :source AND t.sourceId = :sourceId AND t.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "TblLivingPlant.resetImageStatus", query = "UPDATE TblLivingPlant t SET t.hasImage = FALSE, t.hasPublicImage = FALSE WHERE t.tblDerivative IN ( SELECT td FROM TblDerivative td WHERE td.organisationId IN (:organisations) )")
    , @NamedQuery(name = "TblLivingPlant.findByAccessionNumberList", query = "SELECT t FROM TblLivingPlant t WHERE t.accessionNumber in (:accessionNumberList)")
    , @NamedQuery(name = "TblImportProperties.findByOriginalBotanicalObjectIdAndSourceName", query = "SELECT t FROM TblImportProperties t WHERE t.originalBotanicalObjectId = :originalBotanicalObjectId AND t.sourceName = :sourceName")
    , @NamedQuery(name = "TblCultivar.findByCultivarAndScientificNameId", query = "SELECT t FROM TblCultivar t INNER JOIN t.scientificNameId sni WHERE t.cultivar = :cultivar AND sni.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "TblNomName.findBySubstantive", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IS NULL AND t.secondEpithetId IS NULL")
    , @NamedQuery(name = "TblNomName.findBySubstantiveAndFirstEpithet", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IN (:firstEpithetIds) AND t.secondEpithetId IS NULL")
    , @NamedQuery(name = "TblNomName.findBySubstantiveAndFirstEpithetAndSecondEpithet", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IN (:firstEpithetIds) AND t.secondEpithetId IN (:secondEpithetIds)")
    , @NamedQuery(name = "TblNomSubstantive.findLikeSubstantive", query = "SELECT t FROM TblNomSubstantive t WHERE t.substantive LIKE :substantive")
    , @NamedQuery(name = "TblNomEpithet.findLikeEpithet", query = "SELECT t FROM TblNomEpithet t WHERE t.epithet LIKE :epithet")
    , @NamedQuery(name = "TblIndexSeminumContent.findByIndexSeminumRevisionId", query = "SELECT t FROM TblIndexSeminumContent t WHERE t.indexSeminumRevisionId = :indexSeminumRevisionId")
    , @NamedQuery(name = "TblCultivar.findByScientificNameId", query = "SELECT t FROM TblCultivar t INNER JOIN t.scientificNameId s WHERE s.scientificNameId = :scientificNameId")
    , @NamedQuery(name = "TblLocation.findByLocation", query = "SELECT t FROM TblLocation t WHERE t.location = :location")
    , @NamedQuery(name = "TblClassification.findAllAvailable", query = "SELECT t FROM TblClassification t WHERE t.parentScientificNameId IS NULL GROUP BY t.source, t.sourceId")
    , @NamedQuery(name = "TblVegetative.findByBotanicalObjectId", query = "SELECT v FROM TblVegetative v WHERE v.vegetativeId IN (SELECT td FROM TblDerivative td WHERE td.botanicalObjectId = :botanicalObjectId)")
    , @NamedQuery(name = "TblAcquisitionSource.findLikeName", query = "SELECT t FROM TblAcquisitionSource t WHERE t.name LIKE :name")
    , @NamedQuery(name = "TblSpecimen.findByBotanicalObjectId", query = "SELECT t FROM TblSpecimen t WHERE t.specimenId in (SELECT td FROM TblDerivative td WHERE td.botanicalObjectId = (:botanicalObjectId))")
    , @NamedQuery(name = "FrmwrkaccessOrganisation.findByOrganisationIdandUserId", query = "SELECT f FROM FrmwrkaccessOrganisation f WHERE f.organisationId = :organisationId and f.userId = :userId")
    , @NamedQuery(name = "FrmwrkaccessOrganisation.findByUserAndOrganisation", query = "SELECT f FROM FrmwrkaccessOrganisation f WHERE f.userId = :userId AND f.organisationId = :organisationId")
})
public class CustomNamedQueries {

}
