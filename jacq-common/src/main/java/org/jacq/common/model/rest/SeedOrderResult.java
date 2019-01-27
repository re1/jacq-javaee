/*
 * Copyright 2019 wkoller.
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
import java.util.Date;
import java.util.List;
import org.jacq.common.model.jpa.TblDerivative;
import org.jacq.common.model.jpa.TblSeedOrder;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;

/**
 *
 * @author wkoller
 */
public class SeedOrderResult {

    protected Long seedOrderId;
    protected Date orderDate;
    protected String annotation;
    protected boolean complete;

    protected List<BotanicalObjectDerivative> derivativeList;
    protected UserResult sender;
    protected OrganisationResult senderOrganisation;
    protected OrganisationResult ordererOrganisation;

    public SeedOrderResult() {
        this.derivativeList = new ArrayList<>();
    }

    public SeedOrderResult(TblSeedOrder tblSeedOrder, List<BotanicalObjectDerivative> botanicalObjectDerivatives) {
        this.seedOrderId = tblSeedOrder.getSeedOrderId();
        this.orderDate = tblSeedOrder.getOrderDate();
        this.annotation = tblSeedOrder.getAnnotation();
        this.complete = tblSeedOrder.getComplete();
        this.derivativeList = botanicalObjectDerivatives;

        this.sender = new UserResult(tblSeedOrder.getSenderUserId());
        this.senderOrganisation = new OrganisationResult(tblSeedOrder.getSenderOrganisationId());
        this.ordererOrganisation = new OrganisationResult(tblSeedOrder.getOrdererOrganisationId());
    }

    public Long getSeedOrderId() {
        return seedOrderId;
    }

    public void setSeedOrderId(Long seedOrderId) {
        this.seedOrderId = seedOrderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<BotanicalObjectDerivative> getDerivativeList() {
        return derivativeList;
    }

    public void setDerivativeList(List<BotanicalObjectDerivative> derivativeList) {
        this.derivativeList = derivativeList;
    }

    public UserResult getSender() {
        return sender;
    }

    public void setSender(UserResult sender) {
        this.sender = sender;
    }

    public OrganisationResult getSenderOrganisation() {
        return senderOrganisation;
    }

    public void setSenderOrganisation(OrganisationResult senderOrganisation) {
        this.senderOrganisation = senderOrganisation;
    }

    public OrganisationResult getOrdererOrganisation() {
        return ordererOrganisation;
    }

    public void setOrdererOrganisation(OrganisationResult ordererOrganisation) {
        this.ordererOrganisation = ordererOrganisation;
    }

}
