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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.jpa.TblLocation;

/**
 *
 * @author wkoller
 */
public class LocationResult {

    protected Long locationId;
    protected String location;
    protected Long geonameId;
    protected String countryCode;

    public LocationResult(TblLocation tblLocation) {
        if (tblLocation != null) {
            this.locationId = tblLocation.getId();
            this.location = tblLocation.getLocation();

            if (tblLocation.getTblLocationGeonames() != null) {
                this.geonameId = tblLocation.getTblLocationGeonames().getGeonameId();
                this.countryCode = tblLocation.getTblLocationGeonames().getCountryCode();
            }
        }
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Long geonameId) {
        this.geonameId = geonameId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Helper function for converting a list of TblLocation entries to LocationResult
     *
     * @param tblLocationList
     * @return
     */
    public static List<LocationResult> fromList(List<TblLocation> tblLocationList) {
        List<LocationResult> locationResults = new ArrayList<>();

        if (tblLocationList != null) {
            for (TblLocation tblLocation : tblLocationList) {
                locationResults.add(new LocationResult(tblLocation));
            }
        }

        return locationResults;
    }
}
