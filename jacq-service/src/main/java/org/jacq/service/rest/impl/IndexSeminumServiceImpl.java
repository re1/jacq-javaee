/*
 * Copyright 2017 fhafner.
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
package org.jacq.service.rest.impl;

import java.util.List;
import javax.inject.Inject;
import org.jacq.common.model.rest.IndexSeminumResult;
import org.jacq.common.model.rest.IndexSeminumTypeResult;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.service.manager.IndexSeminumManager;

/**
 *
 * @author fhafner
 */
public class IndexSeminumServiceImpl implements IndexSeminumService {

    @Inject
    protected IndexSeminumManager indexSeminumManager;

    @Override
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult) {
        return indexSeminumManager.save(indexSeminumResult);
    }

    /**
     * @see IndexSeminumService#typeFindAll()
     */
    @Override
    public List<IndexSeminumTypeResult> typeFindAll() {
        return indexSeminumManager.typeFindAll();
    }

}
