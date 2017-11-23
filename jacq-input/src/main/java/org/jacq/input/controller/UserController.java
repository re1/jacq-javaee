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

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.input.util.ServicesUtil;
import org.jacq.input.view.LazyUserDataModel;

/**
 * Controller for handling the search page
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class UserController implements Serializable {

    protected LazyUserDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyUserDataModel(ServicesUtil.getUserService());
    }

    public LazyUserDataModel getDataModel() {
        return dataModel;
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "manage";
    }

    /**
     * Noop action listener for refreshing the row count after loading the data-table
     *
     * @return
     */
    public String updateRowCount() {
        return null;
    }

    /**
     * Jumps to edit Form of User
     *
     * @return
     */
    public String edit() {
        return "edit";
    }

}
