/*
 * Copyright 2018 fhafner.
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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fhafner
 */
@Entity
@Table(name = "view_protolog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewProtolog.findAll", query = "SELECT v FROM ViewProtolog v")
    , @NamedQuery(name = "ViewProtolog.findByCitationId", query = "SELECT v FROM ViewProtolog v WHERE v.citationId = :citationId")})
public class ViewProtolog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "citation_id")
    private Long citationId;
    @Lob
    @Size(max = 65535)
    @Column(name = "protolog")
    private String protolog;

    public ViewProtolog() {
    }

    public Long getCitationId() {
        return citationId;
    }

    public void setCitationId(Long citationId) {
        this.citationId = citationId;
    }

    public String getProtolog() {
        return protolog;
    }

    public void setProtolog(String protolog) {
        this.protolog = protolog;
    }

}
