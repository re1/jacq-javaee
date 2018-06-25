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
package org.jacq.common.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.jacq.common.model.rest.PersonResult;
import org.jacq.common.util.ServicesUtil;

/**
 *
 * @author wkoller
 */
@FacesConverter("personConverter")
public class PersonConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.equalsIgnoreCase("null")) {
            try {
                return ServicesUtil.getPersonService().load(Long.valueOf(value));
            } catch (NumberFormatException nfe) {
                PersonResult personResult = (PersonResult) ((UIInput) component).getValue();
                personResult.setName(value);

                return personResult;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((PersonResult) value).getPersonId());
        }
        else {
            return null;
        }
    }
}
