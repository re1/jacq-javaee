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

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * POJO for all information of a common name
 *
 * @author wkoller, re1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonName {

    public Long id;
    public String name;
    @Valid
    public List<String> type = Collections.singletonList("/name/common");
    public String language;
    public String geography;
    public String period;
    public String taxon;
    public Long score;
    public Boolean match;
    // use taxon_id for representation to match OpenUp!
    @XmlElement(name = "taxon_id")
    public Long taxonId;
    @Valid
    public Set<String> references = new TreeSet<>();

    /**
     * @return Unique id for this common name used in the common name cache
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id Unique id for this common name used in the common name cache
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Common name for this object
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Common name for this object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return OpenRefine compatible type for this common name object
     */
    public List<String> getType() {
        return type;
    }

    /**
     * @param type OpenRefine compatible type for this common name object
     */
    public void setType(List<String> type) {
        this.type = type;
    }

    /**
     * @return Language the common name is used in
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language Language the common name is used in
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return Geographic location the common name is used in
     */
    public String getGeography() {
        return geography;
    }

    /**
     * @param geography Geographic location the common name is used in
     */
    public void setGeography(String geography) {
        this.geography = geography;
    }

    /**
     * @return Period of time the common name was used
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period Period of time the common name was used
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return Scientific name for the common name
     */
    public String getTaxon() {
        return taxon;
    }

    /**
     * @param taxon Scientific name for the common name
     */
    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }

    /**
     * @return Percent value of how much this common name differs from a possible query
     */
    public Long getScore() {
        return score;
    }

    /**
     * @param score Percent value of how much this common name differs from a possible query
     */
    public void setScore(Long score) {
        this.score = score;
        // mark as match if score is 100 percent
        this.match = score == 100;
    }

    /**
     * Helper function to set the score for this common name calculated as the percent ratio of the Levenshtein distance
     * between the query string and the currently set taxon and the larger length of either the query string or taxon.
     *
     * @param query Query string to calculate a score for
     */
    public void setScore(String query) {
        this.setScore(Long.divideUnsigned(
                StringUtils.getLevenshteinDistance(query, this.taxon) * 100,
                Math.max(query.length(), this.taxon.length())));
    }


    /**
     * @return True if this common name is a direct match for a possible query
     */
    public Boolean getMatch() {
        return match;
    }

    /**
     * @param match True if this common name is a direct match for a possible query
     */
    public void setMatch(Boolean match) {
        this.match = match;
    }

    /**
     * @return Unique id for this scientific name also used in the scientific name cache
     */
    public Long getTaxonId() {
        return taxonId;
    }

    /**
     * @param taxonId Unique id for this scientific name also used in the scientific name cache
     */
    public void setTaxonId(Long taxonId) {
        this.taxonId = taxonId;
    }

    /**
     * @return List of string references for this common name
     */
    public Set<String> getReferences() {
        return references;
    }

    /**
     * Sets references list to the given list. Add references through {@code getReferences.add()}.
     *
     * @param references List of string references for this common name
     */
    public void setReferences(Set<String> references) {
        this.references = references;
    }

    /**
     * @return Add single string of semicolon separated references for this common name
     */
    public String getReference() {
        return StringUtils.join(this.references, ";");
    }

    /**
     * Convenience function for {@code CommonName.getType().get(0)}
     *
     * @return First type as string of null if
     */
    @Transient
    public String getFirstType() {
        return this.type == null || this.type.isEmpty() ? null : this.type.get(0);
    }

    /**
     * Helper function for creating a unique hash which is used for quick deduplication during result fetching
     *
     * @return Unique hash for this common name
     */
    @Transient
    public Long deduplicateHash() {
        Long hash = 7L;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.language);
        hash = 61 * hash + Objects.hashCode(this.geography);
        hash = 61 * hash + Objects.hashCode(this.period);
        hash = 61 * hash + Objects.hashCode(this.taxon);
        return hash;
    }
}
