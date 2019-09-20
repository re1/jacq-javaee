package org.jacq.service.names.sources.meertensknaw;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Resteasy Proxy Framework interface for the Meertens KNAW (Pland) service
 * The application response is either HTML or a serialized PHP object.
 *
 * @author re1
 */
@Path("/")
public interface MeertensKnawService {
    /**
     * Queries the Meertens KNAW (Pland) service.
     *
     * @param format         Returns a serialized PHP object in plain text if set to php
     * @param scientificName Scientific name so search for
     * @return Common names for the given scientific name as either HTML or a serialized PHP object.
     */
    @GET
    @Path("/")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    String query(@QueryParam("format") @DefaultValue("php") String format, @QueryParam("plantnaam") String scientificName);
}
