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
package org.jacq.common.rest.provider;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.ParamConverter;

/**
 * Custom converter for Date.class - required due to incompatibility between Date Constructor and toString
 * implementation
 *
 * @author wkoller
 */
public class CustomDateParamConverter implements ParamConverter<Date> {

    private static final Logger LOGGER = Logger.getLogger(CustomDateParamConverter.class.getName());

    /**
     * Parse a date using DateFormat.parse
     *
     * @param value
     * @return
     */
    @Override
    public Date fromString(String value) {
        try {
            return DateFormat.getDateInstance().parse(value);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return null;
    }

    /**
     * Return string value of a date using DateFormat.format
     *
     * @param value
     * @return
     */
    @Override
    public String toString(Date value) {
        return DateFormat.getDateInstance().format(value);
    }

}
