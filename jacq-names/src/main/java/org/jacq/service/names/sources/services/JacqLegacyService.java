package org.jacq.service.names.sources.services;

import org.jacq.common.model.names.JsonRpcRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Resteasy Proxy Framework interface for the JACQ Legacy taxamatch implementation
 * The application response with content-type text/javascript which is marked as obsolete in favor of application/json.
 *
 * @author re1
 */
@Path("taxamatch/jsonRPC/json_rpc_taxamatchMdld.php")
public interface JacqLegacyService {
    /**
     * Queries the JACQ Legacy taxamatch implementation for a JSON-RPC request and returns the resulting string.
     *
     * @param request JSON-RPC request passed as a {@link JsonRpcRequest} instance
     * @return JSON-RPC response string
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/javascript")
    String query(JsonRpcRequest request);
}