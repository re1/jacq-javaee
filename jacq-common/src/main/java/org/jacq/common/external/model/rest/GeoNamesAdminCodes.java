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
package org.jacq.common.external.model.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author wkoller
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNamesAdminCodes {

    @XmlElement(name = "ISO3166_2")
    protected String ISO3166_2;

    public String getISO3166_2() {
        return ISO3166_2;
    }

    public void setISO3166_2(String ISO3166_2) {
        this.ISO3166_2 = ISO3166_2;
    }

}
