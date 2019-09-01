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

import java.util.ArrayList;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

/**
 * Interface definition for common names sources
 *
 * @author wkoller
 */
public interface CommonNamesSource {

    /**
     * Query the source for a list of common names, based on a scientific name
     *
     * @param query parsed scientific name
     * @return list of common names for given scientific name
     */
    public ArrayList<CommonName> query(NameParserResponse query);

    /**
     * Query the source for a list of scientific names, based on a common name
     *
     * @param query common name to query for
     * @return list of possible scientific names for given common name
     */
    public ArrayList<ScientificName> query(String query);

}
