/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "view_scientific_name_components")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewScientificNameComponents.findAll", query = "SELECT v FROM ViewScientificNameComponents v"),
    @NamedQuery(name = "ViewScientificNameComponents.findByScientificNameId", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.scientificNameId = :scientificNameId"),
    @NamedQuery(name = "ViewScientificNameComponents.findByGenericEpithet", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.genericEpithet = :genericEpithet"),
    @NamedQuery(name = "ViewScientificNameComponents.findBySpecificEpithet", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.specificEpithet = :specificEpithet"),
    @NamedQuery(name = "ViewScientificNameComponents.findByRankAbbr", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.rankAbbr = :rankAbbr"),
    @NamedQuery(name = "ViewScientificNameComponents.findByInfraspecificEpithet", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.infraspecificEpithet = :infraspecificEpithet"),
    @NamedQuery(name = "ViewScientificNameComponents.findByAuthor", query = "SELECT v FROM ViewScientificNameComponents v WHERE v.author = :author")})
public class ViewScientificNameComponents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scientific_name_id")
    @Id
    private int scientificNameId;
    @Size(max = 100)
    @Column(name = "generic_epithet")
    private String genericEpithet;
    @Size(max = 50)
    @Column(name = "specific_epithet")
    private String specificEpithet;
    @Size(max = 10)
    @Column(name = "rank_abbr")
    private String rankAbbr;
    @Size(max = 50)
    @Column(name = "infraspecific_epithet")
    private String infraspecificEpithet;
    @Size(max = 255)
    @Column(name = "author")
    private String author;

    public ViewScientificNameComponents() {
    }

    public int getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(int scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public String getGenericEpithet() {
        return genericEpithet;
    }

    public void setGenericEpithet(String genericEpithet) {
        this.genericEpithet = genericEpithet;
    }

    public String getSpecificEpithet() {
        return specificEpithet;
    }

    public void setSpecificEpithet(String specificEpithet) {
        this.specificEpithet = specificEpithet;
    }

    public String getRankAbbr() {
        return rankAbbr;
    }

    public void setRankAbbr(String rankAbbr) {
        this.rankAbbr = rankAbbr;
    }

    public String getInfraspecificEpithet() {
        return infraspecificEpithet;
    }

    public void setInfraspecificEpithet(String infraspecificEpithet) {
        this.infraspecificEpithet = infraspecificEpithet;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
