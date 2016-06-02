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
package org.jacq.service.names.manager;

import javax.annotation.ManagedBean;
import org.jacq.common.model.names.OpenRefineInfo;

/**
 * Handles all common names related actions
 *
 * @author wkoller
 */
@ManagedBean
public class CommonNamesManager {

    /**
     * @see CommonNamesService#info()
     */
    public OpenRefineInfo info() {
        OpenRefineInfo openRefineInfo = new OpenRefineInfo();
        openRefineInfo.setName("JACQ Common Names Service");
        openRefineInfo.setIdentifierSpace("http://openup.nhm-wien.ac.at/commonNames/");
        openRefineInfo.setSchemaSpace("http://openup.nhm-wien.ac.at/commonNames/");

        return openRefineInfo;
    }

    /**
     * @see CommonNamesService#query(java.lang.String)
     */
    public void query(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
