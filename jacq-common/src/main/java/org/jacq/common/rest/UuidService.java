package org.jacq.common.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jacq.common.model.jpa.TblTaxClassification;

/**
 * UUID Service interface for handling unique identifiers across the jacq system
 *
 * @author wkoller
 */
@Path("/uuid")
public interface UuidService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/resolve/{uuid}")
    public String resolve(@PathParam("uuid") String uuid);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mint/")
    public List<TblTaxClassification> getTopLevelEntries();
}
