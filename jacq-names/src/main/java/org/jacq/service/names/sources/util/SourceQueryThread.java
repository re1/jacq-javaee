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
package org.jacq.service.names.sources.util;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import org.jacq.common.model.names.CommonName;
import org.jacq.service.names.sources.CommonNamesSource;

/**
 *
 * @author wkoller
 */
public class SourceQueryThread implements Runnable {

    protected CommonNamesSource commonNamesSource;
    protected String query;
    protected ConcurrentHashMap<Long, CommonName> result;

    public SourceQueryThread(CommonNamesSource commonNamesSource, String query, ConcurrentHashMap<Long, CommonName> result) {
        this.commonNamesSource = commonNamesSource;
        this.query = query;
        this.result = result;
    }

    @Override
    public void run() {
        // query source for list of matching common names
        ArrayList<CommonName> queryResult = this.commonNamesSource.query(query);

        // merge results into global result map
        for (CommonName commonName : queryResult) {

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
