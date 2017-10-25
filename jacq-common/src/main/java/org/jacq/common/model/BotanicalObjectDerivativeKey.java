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
package org.jacq.common.model;

import java.io.Serializable;

/**
 *
 * @author wkoller
 */
public class BotanicalObjectDerivativeKey implements Serializable {

    private int id;
    private int derivativeId;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id;
        hash = 43 * hash + this.derivativeId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BotanicalObjectDerivativeKey other = (BotanicalObjectDerivativeKey) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.derivativeId != other.derivativeId) {
            return false;
        }
        return true;
    }

}
