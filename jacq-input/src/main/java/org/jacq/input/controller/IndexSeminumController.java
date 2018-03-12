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
package org.jacq.input.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.view.LazyIndexSeminumDataModel;
import org.jacq.input.view.LazyIndexSeminumDownloadDataModel;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class IndexSeminumController implements Serializable {

    protected LazyIndexSeminumDataModel dataModel;

    protected LazyIndexSeminumDownloadDataModel downloadDataModel;

    protected Boolean downloadRender;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyIndexSeminumDataModel(ServicesUtil.getIndexSeminumService());
        this.downloadDataModel = new LazyIndexSeminumDownloadDataModel(ServicesUtil.getIndexSeminumService());
    }

    public LazyIndexSeminumDataModel getDataModel() {
        return dataModel;
    }

    public LazyIndexSeminumDownloadDataModel getDownloadDataModel() {
        return downloadDataModel;
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "indexseminum";
    }

    /**
     * Noop action listener for refreshing the row count after loading the
     * data-table
     *
     * @return
     */
    public String updateRowCount() {
        return null;
    }

    public void setRenderedTrue(Long indexSeminumRevisionId) {
        this.downloadDataModel.setIndexSeminumRevisionId(indexSeminumRevisionId);
        this.setDownloadRender(true);
    }

    public Boolean getDownloadRender() {
        return downloadRender;
    }

    public void setDownloadRender(Boolean downloadRender) {
        this.downloadRender = downloadRender;
    }

}
