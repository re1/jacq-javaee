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
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "view_scientificName")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewscientificName.findAll", query = "SELECT v FROM ViewscientificName v"),
    @NamedQuery(name = "ViewscientificName.findByScientificNameId", query = "SELECT v FROM ViewscientificName v WHERE v.scientificNameId = :scientificNameId")})
public class ViewScientificName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    private int scientificNameId;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name")
    private String scientificName;
    @Lob
    @Size(max = 65535)
    @Column(name = "scientific_name_no_author")
    private String scientificNameNoAuthor;

    public ViewScientificName() {
    }

    public int getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(int scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getScientificNameNoAuthor() {
        return scientificNameNoAuthor;
    }

    public void setScientificNameNoAuthor(String scientificNameNoAuthor) {
        this.scientificNameNoAuthor = scientificNameNoAuthor;
    }

}
