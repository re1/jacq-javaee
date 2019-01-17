/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.report.birt;

import java.util.Map;
import org.eclipse.birt.data.oda.pojo.api.IPojoDataSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.jacq.common.model.rest.SeedOrderResult;

/**
 *
 * @author wkoller
 */
public class SeedOrderDataSet implements IPojoDataSet {

    protected int rowCount = 10;

    @Override
    public void open(Object o, Map<String, Object> map) throws OdaException {

    }

    @Override
    public Object next() throws OdaException {
        if (rowCount-- == 0) {
            return null;
        }

        SeedOrderResult seedOrderResult = new SeedOrderResult();

        return seedOrderResult;
    }

    @Override
    public void close() throws OdaException {
    }
}
