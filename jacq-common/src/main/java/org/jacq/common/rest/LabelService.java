package org.jacq.common.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Service for generating labels
 *
 * @author wkoller
 */
@Path("/label")
public interface LabelService {

    public static final String APPLICATION_PDF = "application/pdf";

    /**
     * Generate a work label and return it as PDF
     *
     * @return
     */
    @GET
    @Path("/work")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(APPLICATION_PDF)
    public Response getWork();
}
