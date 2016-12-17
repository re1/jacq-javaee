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
@Table(name = "view_taxon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewTaxon.findAll", query = "SELECT v FROM ViewTaxon v"),
    @NamedQuery(name = "ViewTaxon.findByTaxonID", query = "SELECT v FROM ViewTaxon v WHERE v.taxonID = :taxonID"),
    @NamedQuery(name = "ViewTaxon.findBySynID", query = "SELECT v FROM ViewTaxon v WHERE v.synID = :synID"),
    @NamedQuery(name = "ViewTaxon.findByBasID", query = "SELECT v FROM ViewTaxon v WHERE v.basID = :basID"),
    @NamedQuery(name = "ViewTaxon.findByGenID", query = "SELECT v FROM ViewTaxon v WHERE v.genID = :genID"),
    @NamedQuery(name = "ViewTaxon.findByExternal", query = "SELECT v FROM ViewTaxon v WHERE v.external = :external"),
    @NamedQuery(name = "ViewTaxon.findByGenus", query = "SELECT v FROM ViewTaxon v WHERE v.genus = :genus"),
    @NamedQuery(name = "ViewTaxon.findByDallaTorreIDs", query = "SELECT v FROM ViewTaxon v WHERE v.dallaTorreIDs = :dallaTorreIDs"),
    @NamedQuery(name = "ViewTaxon.findByDallaTorreZusatzIDs", query = "SELECT v FROM ViewTaxon v WHERE v.dallaTorreZusatzIDs = :dallaTorreZusatzIDs"),
    @NamedQuery(name = "ViewTaxon.findByAuthorG", query = "SELECT v FROM ViewTaxon v WHERE v.authorG = :authorG"),
    @NamedQuery(name = "ViewTaxon.findByFamily", query = "SELECT v FROM ViewTaxon v WHERE v.family = :family"),
    @NamedQuery(name = "ViewTaxon.findByCategory", query = "SELECT v FROM ViewTaxon v WHERE v.category = :category"),
    @NamedQuery(name = "ViewTaxon.findByStatus", query = "SELECT v FROM ViewTaxon v WHERE v.status = :status"),
    @NamedQuery(name = "ViewTaxon.findByStatusID", query = "SELECT v FROM ViewTaxon v WHERE v.statusID = :statusID"),
    @NamedQuery(name = "ViewTaxon.findByRank", query = "SELECT v FROM ViewTaxon v WHERE v.rank = :rank"),
    @NamedQuery(name = "ViewTaxon.findByTaxrankID", query = "SELECT v FROM ViewTaxon v WHERE v.taxrankID = :taxrankID"),
    @NamedQuery(name = "ViewTaxon.findByRankAbbr", query = "SELECT v FROM ViewTaxon v WHERE v.rankAbbr = :rankAbbr"),
    @NamedQuery(name = "ViewTaxon.findByAuthor", query = "SELECT v FROM ViewTaxon v WHERE v.author = :author"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID", query = "SELECT v FROM ViewTaxon v WHERE v.authorID = :authorID"),
    @NamedQuery(name = "ViewTaxon.findByBrummitPowellfull", query = "SELECT v FROM ViewTaxon v WHERE v.brummitPowellfull = :brummitPowellfull"),
    @NamedQuery(name = "ViewTaxon.findByAuthor1", query = "SELECT v FROM ViewTaxon v WHERE v.author1 = :author1"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID1", query = "SELECT v FROM ViewTaxon v WHERE v.authorID1 = :authorID1"),
    @NamedQuery(name = "ViewTaxon.findByBpf1", query = "SELECT v FROM ViewTaxon v WHERE v.bpf1 = :bpf1"),
    @NamedQuery(name = "ViewTaxon.findByAuthor2", query = "SELECT v FROM ViewTaxon v WHERE v.author2 = :author2"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID2", query = "SELECT v FROM ViewTaxon v WHERE v.authorID2 = :authorID2"),
    @NamedQuery(name = "ViewTaxon.findByBpf2", query = "SELECT v FROM ViewTaxon v WHERE v.bpf2 = :bpf2"),
    @NamedQuery(name = "ViewTaxon.findByAuthor3", query = "SELECT v FROM ViewTaxon v WHERE v.author3 = :author3"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID3", query = "SELECT v FROM ViewTaxon v WHERE v.authorID3 = :authorID3"),
    @NamedQuery(name = "ViewTaxon.findByBpf3", query = "SELECT v FROM ViewTaxon v WHERE v.bpf3 = :bpf3"),
    @NamedQuery(name = "ViewTaxon.findByAuthor4", query = "SELECT v FROM ViewTaxon v WHERE v.author4 = :author4"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID4", query = "SELECT v FROM ViewTaxon v WHERE v.authorID4 = :authorID4"),
    @NamedQuery(name = "ViewTaxon.findByBpf4", query = "SELECT v FROM ViewTaxon v WHERE v.bpf4 = :bpf4"),
    @NamedQuery(name = "ViewTaxon.findByAuthor5", query = "SELECT v FROM ViewTaxon v WHERE v.author5 = :author5"),
    @NamedQuery(name = "ViewTaxon.findByAuthorID5", query = "SELECT v FROM ViewTaxon v WHERE v.authorID5 = :authorID5"),
    @NamedQuery(name = "ViewTaxon.findByBpf5", query = "SELECT v FROM ViewTaxon v WHERE v.bpf5 = :bpf5"),
    @NamedQuery(name = "ViewTaxon.findByEpithet", query = "SELECT v FROM ViewTaxon v WHERE v.epithet = :epithet"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID = :epithetID"),
    @NamedQuery(name = "ViewTaxon.findByEpithet1", query = "SELECT v FROM ViewTaxon v WHERE v.epithet1 = :epithet1"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID1", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID1 = :epithetID1"),
    @NamedQuery(name = "ViewTaxon.findByEpithet2", query = "SELECT v FROM ViewTaxon v WHERE v.epithet2 = :epithet2"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID2", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID2 = :epithetID2"),
    @NamedQuery(name = "ViewTaxon.findByEpithet3", query = "SELECT v FROM ViewTaxon v WHERE v.epithet3 = :epithet3"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID3", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID3 = :epithetID3"),
    @NamedQuery(name = "ViewTaxon.findByEpithet4", query = "SELECT v FROM ViewTaxon v WHERE v.epithet4 = :epithet4"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID4", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID4 = :epithetID4"),
    @NamedQuery(name = "ViewTaxon.findByEpithet5", query = "SELECT v FROM ViewTaxon v WHERE v.epithet5 = :epithet5"),
    @NamedQuery(name = "ViewTaxon.findByEpithetID5", query = "SELECT v FROM ViewTaxon v WHERE v.epithetID5 = :epithetID5")})
public class ViewTaxon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxonID")
    private int taxonID;
    @Column(name = "synID")
    private Integer synID;
    @Column(name = "basID")
    private Integer basID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genID")
    private int genID;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "annotation")
    private String annotation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "external")
    private short external;
    @Size(max = 100)
    @Column(name = "genus")
    private String genus;
    @Column(name = "DallaTorreIDs")
    private Integer dallaTorreIDs;
    @Column(name = "DallaTorreZusatzIDs")
    private Character dallaTorreZusatzIDs;
    @Size(max = 255)
    @Column(name = "author_g")
    private String authorG;
    @Size(max = 50)
    @Column(name = "family")
    private String family;
    @Size(max = 2)
    @Column(name = "category")
    private String category;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "statusID")
    private Integer statusID;
    @Size(max = 255)
    @Column(name = "rank")
    private String rank;
    @Column(name = "tax_rankID")
    private Integer taxrankID;
    @Size(max = 10)
    @Column(name = "rank_abbr")
    private String rankAbbr;
    @Size(max = 255)
    @Column(name = "author")
    private String author;
    @Column(name = "authorID")
    private Integer authorID;
    @Size(max = 250)
    @Column(name = "Brummit_Powell_full")
    private String brummitPowellfull;
    @Size(max = 255)
    @Column(name = "author1")
    private String author1;
    @Column(name = "authorID1")
    private Integer authorID1;
    @Size(max = 250)
    @Column(name = "bpf1")
    private String bpf1;
    @Size(max = 255)
    @Column(name = "author2")
    private String author2;
    @Column(name = "authorID2")
    private Integer authorID2;
    @Size(max = 250)
    @Column(name = "bpf2")
    private String bpf2;
    @Size(max = 255)
    @Column(name = "author3")
    private String author3;
    @Column(name = "authorID3")
    private Integer authorID3;
    @Size(max = 250)
    @Column(name = "bpf3")
    private String bpf3;
    @Size(max = 255)
    @Column(name = "author4")
    private String author4;
    @Column(name = "authorID4")
    private Integer authorID4;
    @Size(max = 250)
    @Column(name = "bpf4")
    private String bpf4;
    @Size(max = 255)
    @Column(name = "author5")
    private String author5;
    @Column(name = "authorID5")
    private Integer authorID5;
    @Size(max = 250)
    @Column(name = "bpf5")
    private String bpf5;
    @Size(max = 50)
    @Column(name = "epithet")
    private String epithet;
    @Column(name = "epithetID")
    private Integer epithetID;
    @Size(max = 50)
    @Column(name = "epithet1")
    private String epithet1;
    @Column(name = "epithetID1")
    private Integer epithetID1;
    @Size(max = 50)
    @Column(name = "epithet2")
    private String epithet2;
    @Column(name = "epithetID2")
    private Integer epithetID2;
    @Size(max = 50)
    @Column(name = "epithet3")
    private String epithet3;
    @Column(name = "epithetID3")
    private Integer epithetID3;
    @Size(max = 50)
    @Column(name = "epithet4")
    private String epithet4;
    @Column(name = "epithetID4")
    private Integer epithetID4;
    @Size(max = 50)
    @Column(name = "epithet5")
    private String epithet5;
    @Column(name = "epithetID5")
    private Integer epithetID5;

    public ViewTaxon() {
    }

    public int getTaxonID() {
        return taxonID;
    }

    public void setTaxonID(int taxonID) {
        this.taxonID = taxonID;
    }

    public Integer getSynID() {
        return synID;
    }

    public void setSynID(Integer synID) {
        this.synID = synID;
    }

    public Integer getBasID() {
        return basID;
    }

    public void setBasID(Integer basID) {
        this.basID = basID;
    }

    public int getGenID() {
        return genID;
    }

    public void setGenID(int genID) {
        this.genID = genID;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public short getExternal() {
        return external;
    }

    public void setExternal(short external) {
        this.external = external;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Integer getDallaTorreIDs() {
        return dallaTorreIDs;
    }

    public void setDallaTorreIDs(Integer dallaTorreIDs) {
        this.dallaTorreIDs = dallaTorreIDs;
    }

    public Character getDallaTorreZusatzIDs() {
        return dallaTorreZusatzIDs;
    }

    public void setDallaTorreZusatzIDs(Character dallaTorreZusatzIDs) {
        this.dallaTorreZusatzIDs = dallaTorreZusatzIDs;
    }

    public String getAuthorG() {
        return authorG;
    }

    public void setAuthorG(String authorG) {
        this.authorG = authorG;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getTaxrankID() {
        return taxrankID;
    }

    public void setTaxrankID(Integer taxrankID) {
        this.taxrankID = taxrankID;
    }

    public String getRankAbbr() {
        return rankAbbr;
    }

    public void setRankAbbr(String rankAbbr) {
        this.rankAbbr = rankAbbr;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public String getBrummitPowellfull() {
        return brummitPowellfull;
    }

    public void setBrummitPowellfull(String brummitPowellfull) {
        this.brummitPowellfull = brummitPowellfull;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public Integer getAuthorID1() {
        return authorID1;
    }

    public void setAuthorID1(Integer authorID1) {
        this.authorID1 = authorID1;
    }

    public String getBpf1() {
        return bpf1;
    }

    public void setBpf1(String bpf1) {
        this.bpf1 = bpf1;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public Integer getAuthorID2() {
        return authorID2;
    }

    public void setAuthorID2(Integer authorID2) {
        this.authorID2 = authorID2;
    }

    public String getBpf2() {
        return bpf2;
    }

    public void setBpf2(String bpf2) {
        this.bpf2 = bpf2;
    }

    public String getAuthor3() {
        return author3;
    }

    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    public Integer getAuthorID3() {
        return authorID3;
    }

    public void setAuthorID3(Integer authorID3) {
        this.authorID3 = authorID3;
    }

    public String getBpf3() {
        return bpf3;
    }

    public void setBpf3(String bpf3) {
        this.bpf3 = bpf3;
    }

    public String getAuthor4() {
        return author4;
    }

    public void setAuthor4(String author4) {
        this.author4 = author4;
    }

    public Integer getAuthorID4() {
        return authorID4;
    }

    public void setAuthorID4(Integer authorID4) {
        this.authorID4 = authorID4;
    }

    public String getBpf4() {
        return bpf4;
    }

    public void setBpf4(String bpf4) {
        this.bpf4 = bpf4;
    }

    public String getAuthor5() {
        return author5;
    }

    public void setAuthor5(String author5) {
        this.author5 = author5;
    }

    public Integer getAuthorID5() {
        return authorID5;
    }

    public void setAuthorID5(Integer authorID5) {
        this.authorID5 = authorID5;
    }

    public String getBpf5() {
        return bpf5;
    }

    public void setBpf5(String bpf5) {
        this.bpf5 = bpf5;
    }

    public String getEpithet() {
        return epithet;
    }

    public void setEpithet(String epithet) {
        this.epithet = epithet;
    }

    public Integer getEpithetID() {
        return epithetID;
    }

    public void setEpithetID(Integer epithetID) {
        this.epithetID = epithetID;
    }

    public String getEpithet1() {
        return epithet1;
    }

    public void setEpithet1(String epithet1) {
        this.epithet1 = epithet1;
    }

    public Integer getEpithetID1() {
        return epithetID1;
    }

    public void setEpithetID1(Integer epithetID1) {
        this.epithetID1 = epithetID1;
    }

    public String getEpithet2() {
        return epithet2;
    }

    public void setEpithet2(String epithet2) {
        this.epithet2 = epithet2;
    }

    public Integer getEpithetID2() {
        return epithetID2;
    }

    public void setEpithetID2(Integer epithetID2) {
        this.epithetID2 = epithetID2;
    }

    public String getEpithet3() {
        return epithet3;
    }

    public void setEpithet3(String epithet3) {
        this.epithet3 = epithet3;
    }

    public Integer getEpithetID3() {
        return epithetID3;
    }

    public void setEpithetID3(Integer epithetID3) {
        this.epithetID3 = epithetID3;
    }

    public String getEpithet4() {
        return epithet4;
    }

    public void setEpithet4(String epithet4) {
        this.epithet4 = epithet4;
    }

    public Integer getEpithetID4() {
        return epithetID4;
    }

    public void setEpithetID4(Integer epithetID4) {
        this.epithetID4 = epithetID4;
    }

    public String getEpithet5() {
        return epithet5;
    }

    public void setEpithet5(String epithet5) {
        this.epithet5 = epithet5;
    }

    public Integer getEpithetID5() {
        return epithetID5;
    }

    public void setEpithetID5(Integer epithetID5) {
        this.epithetID5 = epithetID5;
    }

}
