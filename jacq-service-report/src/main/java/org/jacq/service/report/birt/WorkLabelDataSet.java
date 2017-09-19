/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.birt;

import java.util.Map;
import org.eclipse.birt.data.oda.pojo.api.IPojoDataSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.common.model.jpa.ViewBotanicalObjectSearch;
import org.jacq.common.model.report.WorkLabel;

/**
 *
 * @author wkoller
 */
public class WorkLabelDataSet implements IPojoDataSet {

    protected int rowCount = 10;

    @Override
    public void open(Object o, Map<String, Object> map) throws OdaException {

    }

    @Override
    public Object next() throws OdaException {
        if (rowCount-- == 0) {
            return null;
        }

        WorkLabel workLabel = new WorkLabel();
        workLabel.setAccessionNumber("0123456-" + rowCount);
        workLabel.setLabelAnnotation("Eine Anmerkung:" + rowCount);
        workLabel.setScientificName("Abies alba");

        return workLabel;
    }

    @Override
    public void close() throws OdaException {
    }
}
