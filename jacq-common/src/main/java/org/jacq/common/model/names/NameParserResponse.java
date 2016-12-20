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

/**
 * Model for holding relevant information of the name-parser response
 *
 * @author wkoller
 */
public class NameParserResponse {

    protected String scientificName;
    protected String uninomial;
    protected String genus;
    protected String species;
    protected String infraspecies;
    protected String rank;

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getUninomial() {
        return uninomial;
    }

    public void setUninomial(String uninomial) {
        this.uninomial = uninomial;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getInfraspecies() {
        return infraspecies;
    }

    public void setInfraspecies(String infraspecies) {
        this.infraspecies = infraspecies;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
