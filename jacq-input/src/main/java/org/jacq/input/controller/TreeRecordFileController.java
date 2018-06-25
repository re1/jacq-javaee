/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.view.LazyTreeRecordFileDataModel;

/**
 * Controller for handling the search page
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class TreeRecordFileController implements Serializable {

    protected LazyTreeRecordFileDataModel dataModel;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyTreeRecordFileDataModel(ServicesUtil.getTreeRecordFileService());
    }

    public LazyTreeRecordFileDataModel getDataModel() {
        return dataModel;
    }

    /**
     * Execute search using the provided filters and redirect to result list
     *
     * @return
     */
    public String search() {
        return "treecontrolsheetmanage";
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

}
