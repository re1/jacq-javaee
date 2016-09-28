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
package org.jacq.common.model.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Transient;
import javax.validation.Valid;

/**
 * Pojo for all information of a scientific name
 *
 * @author wkoller
 */
public class ScientificName {

    public Long scientificNameId;
    public String name;
    @Valid
    public List<String> type = Arrays.asList("/name/scientific");

    public Long commonNameId;
    public String common;
    public String language;
    public String geography;
    public String period;
    public Long score;
    public Boolean match;
    @Valid
    public List<String> references = new ArrayList<>();

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Boolean getMatch() {
        return match;
    }

    public void setMatch(Boolean match) {
        this.match = match;
    }

    public Long getCommonNameId() {
        return commonNameId;
    }

    public void setCommonNameId(Long commonNameId) {
        this.commonNameId = commonNameId;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    /**
     * Helper function for creating a unique hash which is used for quickly deduplicating during result fetching
     *
     * @return
     */
    @Transient
    public Long deduplicateHash() {
        Long hash = 7L;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.language);
        hash = 61 * hash + Objects.hashCode(this.geography);
        hash = 61 * hash + Objects.hashCode(this.period);
        hash = 61 * hash + Objects.hashCode(this.common);
        return hash;
    }
}
