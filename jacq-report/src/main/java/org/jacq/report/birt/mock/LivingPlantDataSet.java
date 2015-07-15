/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.report.birt.mock;

import java.util.Map;
import org.eclipse.birt.data.oda.pojo.api.IPojoDataSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.jacq.common.model.jpa.TblBotanicalObject;
import org.jacq.common.model.jpa.TblLivingPlant;

/**
 *
 * @author wkoller
 */
public class LivingPlantDataSet implements IPojoDataSet {

    protected int rowCount = 10;

    @Override
    public void open(Object o, Map<String, Object> map) throws OdaException {

    }

    @Override
    public Object next() throws OdaException {
        if (rowCount-- == 0) {
            return null;
        }

        TblLivingPlant tblLivingPlant = new TblLivingPlant();
        tblLivingPlant.setAccessionNumber((int) (Math.random() * 1000.0));
        tblLivingPlant.setLabelAnnotation("Eine Anmerkung: '" + rowCount + "'");
        tblLivingPlant.setTblBotanicalObject(new TblBotanicalObject());

        return tblLivingPlant;
    }

    @Override
    public void close() throws OdaException {
    }
}
