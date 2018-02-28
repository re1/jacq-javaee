/*
 * Copyright 2018 wkoller.
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
import org.jacq.common.model.rest.PersonResult;
import org.jacq.common.rest.PersonService;
import org.jacq.service.manager.PersonManager;

/**
 *
 * @author wkoller
 */
public class PersonServiceImpl implements PersonService {

    @Inject
    protected PersonManager personManager;

    /**
     * @see PersonService#search(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<PersonResult> search(String name, Integer offset, Integer limit) {
        return personManager.search(name, offset, limit);
    }

    /**
     * @see PersonService#load(java.lang.Long)
     */
    @Override
    public PersonResult load(Long personId) {
        return personManager.load(personId);
    }

}
