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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.rest.AlternativeAccessionNumberResult;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.HabitusTypeResult;
import org.jacq.common.model.rest.IndexSeminumTypeResult;
import org.jacq.common.model.rest.LivingPlantResult;
import org.jacq.common.model.rest.ScientificNameInformationResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.common.rest.names.ScientificNameService;
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

    /**
     * Reference to scientific name service which is used for cultivar and
     * scientific name editing
     */
    protected ScientificNameService scientificNameService;

    /**
     * Index seminum service which is used for displaying the available types
     */
    protected IndexSeminumService indexSeminumService;

    protected LivingPlantResult livingPlantResult;

    protected List<CultivarResult> cultivarResults;

    protected List<IndexSeminumTypeResult> indexSeminumTypes;

    protected ScientificNameInformationResult scientificNameInformationResult;

    protected List<HabitusTypeResult> habitusTypes;

    @PostConstruct
    public void init() {
        this.derivativeService = ServicesUtil.getDerivativeService();
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.indexSeminumService = ServicesUtil.getIndexSeminumService();

        this.livingPlantResult = new LivingPlantResult();

        this.indexSeminumTypes = this.indexSeminumService.typeFindAll();
    }

    /**
     * Called when the user clicks on the button for reviewing the scientific
     * name information, only then this info is loaded
     *
     * @return
     */
    public void showScientificNameInformation() {
        if (this.habitusTypes == null) {
            this.habitusTypes = this.scientificNameService.findAllHabitusType();
        }

        // load scientific name information
        this.scientificNameInformationResult = this.scientificNameService.scientificNameInformationLoad(this.livingPlantResult.getScientificNameId());
    }

    public void addCultivar() {
        this.scientificNameInformationResult.getCultivarList().add(new CultivarResult());
    }

    public void removeCultivar(CultivarResult cultivarResult) {
        this.scientificNameInformationResult.getCultivarList().remove(cultivarResult);
    }

    public void addAlternativeAccessionNumber() {
        this.livingPlantResult.getAlternativeAccessionNumberResults().add(new AlternativeAccessionNumberResult());
    }

    public void removeAlternativeAccessionNumber(AlternativeAccessionNumberResult alternativeAccessionNumberResult) {
        this.livingPlantResult.getAlternativeAccessionNumberResults().remove(alternativeAccessionNumberResult);
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

            // load matching list of possible cultivar entries
            this.cultivarResults = this.scientificNameService.cultivarFind(this.livingPlantResult.getScientificNameId());

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

    public List<CultivarResult> getCultivarResults() {
        return cultivarResults;
    }

    public void setCultivarResults(List<CultivarResult> cultivarResults) {
        this.cultivarResults = cultivarResults;
    }

    public List<IndexSeminumTypeResult> getIndexSeminumTypes() {
        return indexSeminumTypes;
    }

    public ScientificNameInformationResult getScientificNameInformationResult() {
        return scientificNameInformationResult;
    }

    public void setScientificNameInformationResult(ScientificNameInformationResult scientificNameInformationResult) {
        this.scientificNameInformationResult = scientificNameInformationResult;
    }

    public List<HabitusTypeResult> getHabitusTypes() {
        return habitusTypes;
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("", ""));
    }

}
