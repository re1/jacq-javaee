package org.jacq.common.rest.report;

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
     * Generate a work label for any given botanical object available through
     * the search view
     *
     * @param type Type of botanical object to search for (e.g. 'vegetative',
     * 'living')
     * @param derivativeId Id of derivative
     * @return
     */
    @GET
    @Path("/work/{type}/{derivative_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(APPLICATION_PDF)
    public Response getWork(@PathParam("type") String type, @PathParam("derivative_id") Long derivativeId);

    /**
     * Generate a seed order for a given seed order
     *
     * @param seedOrderId Id of seed order
     * @return
     */
    @GET
    @Path("/seedOrder/{seed_order_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(APPLICATION_PDF)
    public Response getSeedOrder(@PathParam("seed_order_id") Long seedOrderId);
}
