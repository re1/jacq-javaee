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
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenRefine Reconciliation Service API query object
 *
 * @author wkoller
 * @author re1
 * @see <a href="https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API#query-json-object">OpenRefine Reconciliation Service API Query JSON Object</a>
 */
@XmlRootElement
public class OpenRefineRequest {

    /**
     * A string to search for. Required.
     */
    protected String query;

    /**
     * An integer to specify how many results to return. Optional.
     */
    protected Integer limit;

    /**
     * A single string, or an array of strings, specifying the types of result e.g., person, product, ... The actual
     * format of each type depends on the service (e.g., "/government/politician" as a Freebase type). Optional.
     */
    protected String type;

    /**
     * A string, one of "any", "all", "should". Optional.
     */
    @XmlElement(name = "type_strict")
    protected String typeStrict;

    /**
     * Array of json object literals. Optional
     */
    protected List<JsonObject> properties;

    /**
     * Required for JAXB to be happy
     */
    public OpenRefineRequest() {
    }

    public OpenRefineRequest(String param) {
        // check if query string is valid JSON
        try (StringReader stringReader = new StringReader(param)) {
            // create object from valid JSON string
            JsonObject queryObject = Json.createReader(stringReader).readObject();
            // set fields from JSON object
            this.query = queryObject.getString("query", null);
            this.type = queryObject.getString("type", null);
            this.limit = queryObject.getInt("limit", 0);
            this.typeStrict = queryObject.getString("type_strict", null);
            try {
                this.properties = new ArrayList<>(queryObject.getJsonArray("properties").getValuesAs(JsonObject.class));
            } catch (Exception e) {
                // properties could not be converted to a list of JSON objects
            }
        } catch (JsonParsingException e) {
            // Query parameter is not valid JSON and will be used as query string
            this.query = param;
        }
    }

    public OpenRefineRequest(String query, Integer limit, String type, String typeStrict, List<JsonObject> properties) {
        this.query = query;
        this.limit = limit;
        this.type = type;
        this.typeStrict = typeStrict;
        this.properties = properties;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeStrict() {
        return typeStrict;
    }

    public void setTypeStrict(String typeStrict) {
        this.typeStrict = typeStrict;
    }

    public List<JsonObject> getProperties() {
        return properties;
    }

    public void setProperties(List<JsonObject> properties) {
        this.properties = properties;
    }

}
