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
import org.jacq.service.names.model.NameParserResponse;
import org.jacq.service.names.sources.CommonNamesSource;

/**
 *
 * @author wkoller
 */
public class SourceQueryThread implements Callable<ArrayList<CommonName>> {

    protected CommonNamesSource commonNamesSource;
    protected NameParserResponse query;

    public SourceQueryThread(CommonNamesSource commonNamesSource, NameParserResponse query) {
        this.commonNamesSource = commonNamesSource;
        this.query = query;
    }

    @Override
    public ArrayList<CommonName> call() throws Exception {
        return this.commonNamesSource.query(query);

//        // query source for list of matching common names
//        ArrayList<CommonName> queryResult = this.commonNamesSource.query(query);
//
//        // merge results into global result map
//        for (CommonName commonName : queryResult) {
//            // clean the scientific name
//            // TODO: implement
//
//            // check if result already exists
//            Long deduplicateHash = commonName.deduplicateHash();
//            if (result.containsKey(deduplicateHash)) {
//                // only update references
//                result.get(deduplicateHash).getReferences().addAll(commonName.getReferences());
//            }
//            else {
//                // add entry to result list
//                result.put(deduplicateHash, commonName);
//            }
//        }
    }
}
