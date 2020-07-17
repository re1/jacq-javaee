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

package org.jacq.service.names.sources;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for finish common names from the LUOMUS web service.
 *
 * @author re1
 * @see <a href="http://luomus.fi/en">English page of the LUOMUS museum</a>
 */
@ManagedBean
public class LuomusFiSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(LuomusFiSource.class.getName());

    private static final String serviceUrl = "http://ws.luomus.fi/triplestore/search?predicate=dwc:scientificName&object=";

    @PostConstruct
    public void init() {
        setServiceId(6);
        setTimeout(2592000); // 30 days
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see CommonNamesSource#query(String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWebServiceResponse(NameParserResponse query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
