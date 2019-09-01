/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.common.model.names;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.LinkedHashMap;

/**
 * Multiple OpenRefine query objects identified by a unique string
 *
 * @author re1
 * @see <a href="https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API#multiple-query-mode">OpenRefine Reconciliation Service API Multiple Query Mode</a>
 */
@XmlRootElement
public class OpenRefineMultiRequest extends LinkedHashMap<String, OpenRefineRequest> {
    /**
     * Required for JAXB to be happy
     */
    public OpenRefineMultiRequest() {
    }

    public OpenRefineMultiRequest(String param) {
        // check if string is valid JSON
        try (StringReader stringReader = new StringReader(param)) {
            JsonObject queriesObject = Json.createReader(stringReader).readObject();
            // iterate over queries and add results with their key to the results map
            for (String key : queriesObject.keySet()) {
                OpenRefineRequest queryObject = new OpenRefineRequest(queriesObject.getJsonObject(key).toString());
                this.put(key, queryObject);
            }
        } catch (JsonParsingException e) {
            // Queries parameter is not valid JSON
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Queries parameter is not valid JSON").build());
        } catch (JsonException e) {
            // Queries parameter is valid JSON but not a JSON object
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Queries parameter is not a JSON object").build());
        } catch (ClassCastException e) {
            // Query JSON object or its query parameter cannot be cast to object or string respectively
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Query JSON object's query parameter is not a string").build());
        } catch (NullPointerException e) {
            // Query JSON object has no query parameter
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST).entity("Query JSON object has no query parameter").build());
        }
    }
}
