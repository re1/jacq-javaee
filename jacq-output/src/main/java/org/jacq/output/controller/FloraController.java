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
package org.jacq.output.controller;

import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.jacq.output.util.ServicesUtil;
import org.jacq.output.view.LazyClassificationTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class FloraController {

    private static Logger LOGGER = Logger.getLogger(FloraController.class.getName());

    protected LazyClassificationTreeNode root;

    @PostConstruct
    public void init() {
        this.root = new LazyClassificationTreeNode(ServicesUtil.getClassificationService());
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(LazyClassificationTreeNode root) {
        this.root = root;
    }

    public String getUuid() {
        return this.root.getUuid();
    }

    public void setUuid(String uuid) {
        this.root.setUuid(uuid);
    }

    public String filterForProvince(int provinceId) {
        LOGGER.severe("Action called!");

        this.root.setProvinceId(provinceId);
        this.root.applyFilter();

        return null;
    }

}
