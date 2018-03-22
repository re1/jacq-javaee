/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jacq.common.external.model.rest.GeoNamesEntryResult;
import org.jacq.common.external.model.rest.GeoNamesSearchResult;
import org.jacq.common.external.rest.GeoNamesService;
import org.jacq.common.model.jpa.TblLocation;
import org.jacq.common.model.jpa.TblLocationGeonames;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.rest.GatheringService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.service.JacqServiceConfig;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class GatheringManager {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected JacqServiceConfig jacqConfig;

    protected GeoNamesService geoNamesService;
    protected String geoNamesUsername;

    @PostConstruct
    public void init() {
        this.geoNamesService = ServicesUtil.getGeoNamesService(jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_URL));
        this.geoNamesUsername = jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_USERNAME);
    }

    /**
     * @see GatheringService#locationFind(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Transactional
    public List<LocationResult> locationFind(String location, Integer offset, Integer count) {
        GeoNamesSearchResult geoNamesSearchResult = this.geoNamesService.searchJSON(location, count, this.geoNamesUsername);
        List<LocationResult> locationResults = new ArrayList<>();

        for (GeoNamesEntryResult geoNamesEntryResult : geoNamesSearchResult.getGeonames()) {
            TypedQuery<TblLocationGeonames> locationGeonamesQuery = em.createNamedQuery("TblLocationGeonames.findByGeonameId", TblLocationGeonames.class);
            locationGeonamesQuery.setParameter("geonameId", geoNamesEntryResult.getGeonameId());
            List<TblLocationGeonames> locationGeonamesList = locationGeonamesQuery.getResultList();
            if (locationGeonamesList != null && locationGeonamesList.size() > 0) {
                locationResults.add(new LocationResult(locationGeonamesList.get(0).getTblLocation()));
            }
            else {
                TblLocation tblLocation = new TblLocation();
                tblLocation.setLocation(geoNamesEntryResult.getName());
                em.persist(tblLocation);

                TblLocationGeonames tblLocationGeonames = new TblLocationGeonames();
                tblLocationGeonames.setId(tblLocation.getId());
                tblLocationGeonames.setTblLocation(tblLocation);
                tblLocationGeonames.setCountryCode(geoNamesEntryResult.getCountryCode());
                tblLocationGeonames.setGeonameId(geoNamesEntryResult.getGeonameId());
                tblLocationGeonames.setServiceData(geoNamesEntryResult.toString());
                em.persist(tblLocationGeonames);

                tblLocation.setTblLocationGeonames(tblLocationGeonames);

                locationResults.add(new LocationResult(tblLocation));
            }
        }

        return locationResults;
    }

    /**
     * @see GatheringService#locationLoad(java.lang.Long)
     */
    public LocationResult locationLoad(Long locationId) {
        return new LocationResult(em.find(TblLocation.class, locationId));
    }
}
