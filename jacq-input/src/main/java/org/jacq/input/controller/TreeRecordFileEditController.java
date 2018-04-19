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

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jacq.common.model.rest.TreeRecordFileResult;
import org.jacq.common.rest.TreeRecordFileService;
import org.jacq.common.util.ServicesUtil;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class TreeRecordFileEditController {

    @Inject
    protected SessionController sessionController;

    private static final Logger LOGGER = Logger.getLogger(TreeRecordFileEditController.class.getName());

    protected Long treeRecordFileId;

    protected TreeRecordFileResult treeRecordFile;

    protected TreeRecordFileService treeRecordFileService;

    private UploadedFile file;

    @PostConstruct
    public void init() {

        this.treeRecordFileService = ServicesUtil.getTreeRecordFileService();

        this.treeRecordFile = new TreeRecordFileResult();

    }

    public Long getTreeRecordFileId() {
        return treeRecordFileId;
    }

    public void setTreeRecordFileId(Long treeRecordFileId) {
        this.treeRecordFileId = treeRecordFileId;
    }

    public TreeRecordFileResult getTreeRecordFile() {
        return treeRecordFile;
    }

    public String edit() {
        try {
            this.treeRecordFile.setFileContent(new String(Base64.getEncoder().encode(this.file.getContents()), "ASCII"));
            this.treeRecordFile = this.treeRecordFileService.save(this.treeRecordFile);

        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

}
