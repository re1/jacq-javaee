/*
 * Copyright 2017 wkoller.
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
package org.jacq.common.model.jpa.custom;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author wkoller
 */
@MappedSuperclass
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "RevClassification.addRevision", procedureName = "AddRevClassification",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "i_source")
                ,
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "i_source_id")
                ,
		@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "o_uuid")
            })
})
public class CustomNamedStoredProcedureQueries {

}
