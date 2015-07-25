package org.jacq.service.manager;

import javax.annotation.ManagedBean;
import javax.ws.rs.core.Response;

/**
 * Business logic for label printing process
 *
 * @author wkoller
 */
@ManagedBean
public class LabelManager {

    /**
     * @see LabelService#getWork()
     */
    public Response getWork(int accessionNumber) throws Exception {
        throw new Exception("Not supported yet 5!");

//        return Response.ok("Test 2").build();
    }
}
