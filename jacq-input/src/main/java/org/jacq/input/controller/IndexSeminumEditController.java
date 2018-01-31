/*
 * Copyright 2017 fhafner.
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.jacq.common.model.rest.IndexSeminumResult;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.input.util.ServicesUtil;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class IndexSeminumEditController {

    protected Long indexSeminumId;

    protected IndexSeminumResult indexSeminum;

    protected IndexSeminumService indexSeminumService;

    @PostConstruct
    public void init() {

        this.indexSeminumService = ServicesUtil.getIndexSeminumService();

        this.indexSeminum = new IndexSeminumResult();

    }

    public String edit() {
        this.indexSeminum = this.indexSeminumService.save(this.indexSeminum);

        return null;
    }

    public IndexSeminumResult getIndexSeminum() {
        return indexSeminum;
    }

    public Long getIndexSeminumId() {
        return indexSeminumId;
    }

    public void setIndexSeminumId(Long indexSeminumId) {
        this.indexSeminumId = indexSeminumId;
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("", ""));
    }

}
