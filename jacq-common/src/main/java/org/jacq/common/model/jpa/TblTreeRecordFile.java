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
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_tree_record_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTreeRecordFile.findAll", query = "SELECT t FROM TblTreeRecordFile t")
    , @NamedQuery(name = "TblTreeRecordFile.findById", query = "SELECT t FROM TblTreeRecordFile t WHERE t.id = :id")
    , @NamedQuery(name = "TblTreeRecordFile.findByYear", query = "SELECT t FROM TblTreeRecordFile t WHERE t.year = :year")
    , @NamedQuery(name = "TblTreeRecordFile.findByName", query = "SELECT t FROM TblTreeRecordFile t WHERE t.name = :name")
    , @NamedQuery(name = "TblTreeRecordFile.findByDocumentNumber", query = "SELECT t FROM TblTreeRecordFile t WHERE t.documentNumber = :documentNumber")})
public class TblTreeRecordFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "document_number")
    private String documentNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treeRecordFileId", fetch = FetchType.LAZY)
    private List<TblTreeRecordFilePage> tblTreeRecordFilePageList;

    public TblTreeRecordFile() {
    }

    public TblTreeRecordFile(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @XmlTransient
    public List<TblTreeRecordFilePage> getTblTreeRecordFilePageList() {
        return tblTreeRecordFilePageList;
    }

    public void setTblTreeRecordFilePageList(List<TblTreeRecordFilePage> tblTreeRecordFilePageList) {
        this.tblTreeRecordFilePageList = tblTreeRecordFilePageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTreeRecordFile)) {
            return false;
        }
        TblTreeRecordFile other = (TblTreeRecordFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblTreeRecordFile[ id=" + id + " ]";
    }

}
