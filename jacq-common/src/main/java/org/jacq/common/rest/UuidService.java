package org.jacq.common.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * UUID Service interface for handling unique identifiers across the jacq system
 *
 * @author wkoller
 */
@Path("/uuid")
public interface UuidService {

    /**
     * Resolves a given UUID into the corresponding URL
     *
     * @param uuid
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/resolve/{uuid}")
    public String resolve(@PathParam("uuid") String uuid);

    /**
     * Mints an UUID for the given type / internal-id, if already minted returns
     * the existing identifier
     *
     * @param type
     * @param internal_id
     * @return UUID of the given type / internal-id
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mint/{type}/{internal_id}")
    public String mint(@PathParam("type") String type, @PathParam("internal_id") int internal_id);
}
