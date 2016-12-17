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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionEvent.findAll", query = "SELECT t FROM TblAcquisitionEvent t"),
    @NamedQuery(name = "TblAcquisitionEvent.findById", query = "SELECT t FROM TblAcquisitionEvent t WHERE t.id = :id")})
public class TblAcquisitionEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "number")
    private String number;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @JoinTable(name = "tbl_acquisition_event_person", joinColumns = {
        @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "person_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<TblPerson> tblPersonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionEventId")
    private Collection<TblBotanicalObject> tblBotanicalObjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionEventId")
    private Collection<TblAcquisitionEventSource> tblAcquisitionEventSourceCollection;
    @JoinColumn(name = "acquisition_date_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblAcquisitionDate acquisitionDateId;
    @JoinColumn(name = "acquisition_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblAcquisitionType acquisitionTypeId;
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne
    private TblLocation locationId;
    @JoinColumn(name = "location_coordinates_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblLocationCoordinates locationCoordinatesId;

    public TblAcquisitionEvent() {
    }

    public TblAcquisitionEvent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @XmlTransient
    public Collection<TblPerson> getTblPersonCollection() {
        return tblPersonCollection;
    }

    public void setTblPersonCollection(Collection<TblPerson> tblPersonCollection) {
        this.tblPersonCollection = tblPersonCollection;
    }

    @XmlTransient
    public Collection<TblBotanicalObject> getTblBotanicalObjectCollection() {
        return tblBotanicalObjectCollection;
    }

    public void setTblBotanicalObjectCollection(Collection<TblBotanicalObject> tblBotanicalObjectCollection) {
        this.tblBotanicalObjectCollection = tblBotanicalObjectCollection;
    }

    @XmlTransient
    public Collection<TblAcquisitionEventSource> getTblAcquisitionEventSourceCollection() {
        return tblAcquisitionEventSourceCollection;
    }

    public void setTblAcquisitionEventSourceCollection(Collection<TblAcquisitionEventSource> tblAcquisitionEventSourceCollection) {
        this.tblAcquisitionEventSourceCollection = tblAcquisitionEventSourceCollection;
    }

    public TblAcquisitionDate getAcquisitionDateId() {
        return acquisitionDateId;
    }

    public void setAcquisitionDateId(TblAcquisitionDate acquisitionDateId) {
        this.acquisitionDateId = acquisitionDateId;
    }

    public TblAcquisitionType getAcquisitionTypeId() {
        return acquisitionTypeId;
    }

    public void setAcquisitionTypeId(TblAcquisitionType acquisitionTypeId) {
        this.acquisitionTypeId = acquisitionTypeId;
    }

    public TblLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(TblLocation locationId) {
        this.locationId = locationId;
    }

    public TblLocationCoordinates getLocationCoordinatesId() {
        return locationCoordinatesId;
    }

    public void setLocationCoordinatesId(TblLocationCoordinates locationCoordinatesId) {
        this.locationCoordinatesId = locationCoordinatesId;
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
        if (!(object instanceof TblAcquisitionEvent)) {
            return false;
        }
        TblAcquisitionEvent other = (TblAcquisitionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionEvent[ id=" + id + " ]";
    }

}
