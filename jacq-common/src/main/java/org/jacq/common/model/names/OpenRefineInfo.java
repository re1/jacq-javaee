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

import java.util.*;

/**
 * Represents the metadata information provided by an OpenRefine service
 *
 * @author wkoller
 */
public class OpenRefineInfo {

    protected String name;
    protected String identifierSpace;
    protected String schemaSpace;
    protected List<Map<String, String>> defaultTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifierSpace() {
        return identifierSpace;
    }

    public void setIdentifierSpace(String identifierSpace) {
        this.identifierSpace = identifierSpace;
    }

    public String getSchemaSpace() {
        return schemaSpace;
    }

    public void setSchemaSpace(String schemaSpace) {
        this.schemaSpace = schemaSpace;
    }

    public List<Map<String, String>> getDefaultTypes() { return defaultTypes; }

    /**
     * Adds a type to the defaultTypes list
     *
     * @param id Type identifier url
     * @param name Human readable type name
     */
    public void addDefaultType(String id, String name) {
        // create defaultTypes list if it does not exist
        if (defaultTypes == null) defaultTypes = new ArrayList<>();
        // using LinkedHashMap to set order of arguments
        Map<String, String> defaultType = new LinkedHashMap<>();
        defaultType.put("id", id);
        defaultType.put("name", name);
        defaultTypes.add(defaultType);
    }
}
