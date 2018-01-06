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
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.input.util.ServicesUtil;

/**
 * Controller for handling creating / editing of a living plant entry
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class LivingPlantEditController {

    /**
     * Reference to derivative id which is currently edited
     */
    protected Long derivativeId;

    /**
     * Reference to derivative service which is called during editing
     */
    protected DerivativeService derivativeService;

    protected LivingPlantResult livingPlantResult;

    @PostConstruct
    public void init() {
        this.derivativeService = ServicesUtil.getDerivativeService();
        this.livingPlantResult = new LivingPlantResult();
    }

    public Long getDerivativeId() {
        return derivativeId;
    }

    public void setDerivativeId(Long derivativeId) {
        this.derivativeId = derivativeId;

        if (this.derivativeId != null) {
            // load derivative entry, make sure we received a correct one and cast it to living plant entry
            Response botanicalObjectDerivative = this.derivativeService.load(derivativeId, LivingPlantResult.LIVING);
            this.livingPlantResult = botanicalObjectDerivative.readEntity(LivingPlantResult.class);
        }
    }

    public LivingPlantResult getLivingPlantResult() {
        return livingPlantResult;
    }

    /**
     * Virtual IPEN attributes for easier JSF binding
     */
    public String getIpenNumberCountry() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "XX" : this.livingPlantResult.getIpenNumber().substring(0, 2);
    }

    public void setIpenNumberCountry(String ipenNumberCountry) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", ipenNumberCountry, getIpenNumberRestriction(), getIpenNumberGardenCode()));
    }

    public String getIpenNumberRestriction() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "X" : this.livingPlantResult.getIpenNumber().substring(3, 4);
    }

    public void setIpenNumberRestriction(String ipenNumberRestriction) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", getIpenNumberCountry(), ipenNumberRestriction, getIpenNumberGardenCode()));
    }

    public String getIpenNumberGardenCode() {
        return (StringUtils.isEmpty(this.livingPlantResult.getIpenNumber())) ? "XX" : this.livingPlantResult.getIpenNumber().substring(5);
    }

    public void setIpenNumberGardenCode(String ipenNumberGardenCode) {
        this.livingPlantResult.setIpenNumber(String.format("%s-%s-%s", getIpenNumberCountry(), getIpenNumberRestriction(), ipenNumberGardenCode));
    }
}
