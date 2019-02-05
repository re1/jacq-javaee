/*
 * Copyright 2018 fhafner.
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
package org.jacq.common.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.external.model.rest.GeoNamesEntryResult;
import org.jacq.common.external.model.rest.GeoNamesSearchResult;
import org.jacq.common.external.rest.GeoNamesService;
import org.jacq.common.model.jpa.TblLocation;
import org.jacq.common.model.jpa.TblLocationGeonames;
import org.jacq.common.model.rest.LocationResult;

/**
 *
 * @author fhafner
 */
public abstract class BaseGatheringManager {

    private static final Logger LOGGER = Logger.getLogger(BaseGatheringManager.class.getName());

    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected GeoNamesService geoNamesService;

    public void setGeoNamesService(GeoNamesService geoNamesService) {
        this.geoNamesService = geoNamesService;
    }

    protected String geoNamesUsername;

    public void setGeoNamesUsername(String geoNamesUsername) {
        this.geoNamesUsername = geoNamesUsername;
    }

    /**
     * @see GatheringService#locationFind(java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     */
    @Transactional
    public List<LocationResult> locationFind(String location, Integer offset, Integer count) {
        GeoNamesSearchResult geoNamesSearchResult = this.geoNamesService.searchJSON(location, count, this.geoNamesUsername);
        List<LocationResult> locationResults = new ArrayList<>();

        for (GeoNamesEntryResult geoNamesEntryResult : geoNamesSearchResult.getGeonames()) {
            try {
                TypedQuery<TblLocationGeonames> locationGeonamesQuery = entityManager.createNamedQuery("TblLocationGeonames.findByGeonameId", TblLocationGeonames.class);
                locationGeonamesQuery.setParameter("geonameId", geoNamesEntryResult.getGeonameId());
                List<TblLocationGeonames> locationGeonamesList = locationGeonamesQuery.getResultList();
                if (locationGeonamesList != null && locationGeonamesList.size() > 0) {
                    locationResults.add(new LocationResult(locationGeonamesList.get(0).getTblLocation()));
                } else {
                    if (geoNamesEntryResult.getCountryCode() == null) {
                        continue;
                    }

                    TblLocation tblLocation = new TblLocation();
                    tblLocation.setLocation(geoNamesEntryResult.getName());
                    entityManager.persist(tblLocation);

                    TblLocationGeonames tblLocationGeonames = new TblLocationGeonames();
                    tblLocationGeonames.setId(tblLocation.getId());
                    tblLocationGeonames.setTblLocation(tblLocation);
                    tblLocationGeonames.setCountryCode(geoNamesEntryResult.getCountryCode());
                    tblLocationGeonames.setGeonameId(geoNamesEntryResult.getGeonameId());
                    tblLocationGeonames.setServiceData(geoNamesEntryResult.toString());
                    entityManager.persist(tblLocationGeonames);

                    tblLocation.setTblLocationGeonames(tblLocationGeonames);

                    locationResults.add(new LocationResult(tblLocation));
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return locationResults;
    }

    /**
     * @see GatheringService#locationLoad(java.lang.Long)
     */
    public LocationResult locationLoad(Long locationId) {
        return new LocationResult(entityManager.find(TblLocation.class, locationId));
    }

}
