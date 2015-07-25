package org.jacq.common.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
     * @param accessionNumber Accession number which the label should be created for
     * @return
     */
    @GET
    @Path("/work/{accession_number}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(APPLICATION_PDF)
    public Response getWork(@PathParam("accession_number") int accessionNumber);
}
