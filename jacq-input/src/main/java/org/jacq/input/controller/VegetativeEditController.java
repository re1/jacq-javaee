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
package org.jacq.input.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;
import org.jacq.common.model.rest.PhenologyResult;
import org.jacq.common.model.rest.VegetativeResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class VegetativeEditController {

    protected Long vegetativeId;

    protected DerivativeService derivativeService;

    protected VegetativeResult vegetative;

    protected List<PhenologyResult> phenologies;

    @PostConstruct
    public void init() {
        this.derivativeService = ServicesUtil.getDerivativeService();
        this.vegetative = new VegetativeResult();

        // load all lookup tables
        this.phenologies = this.derivativeService.findAllPhenology();
    }

    /*
     * Getter and Setter
     */
    public Long getVegetativeId() {
        return vegetativeId;
    }

    public void setVegetativeId(Long vegetativeId) {
        this.vegetativeId = vegetativeId;

        if (this.vegetativeId != null) {
            Response response = this.derivativeService.load(this.vegetativeId, VegetativeResult.VEGETATIVE);
            this.vegetative = response.readEntity(VegetativeResult.class);
            response.close();
        }
    }

    public VegetativeResult getVegetative() {
        return vegetative;
    }

    public void setVegetative(VegetativeResult vegetative) {
        this.vegetative = vegetative;
    }

    public List<PhenologyResult> getPhenologies() {
        return phenologies;
    }

    public void setPhenologies(List<PhenologyResult> phenologies) {
        this.phenologies = phenologies;
    }
}
