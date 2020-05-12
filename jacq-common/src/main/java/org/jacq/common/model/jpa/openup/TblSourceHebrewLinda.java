package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_hebrew_linda", schema = "openup")
public class TblSourceHebrewLinda {
    // TODO: remove unused id fields from table
    @Id
    @Basic
    @Column(name = "ID1")
    private Integer id1;
    @Basic
    @Size(max = 50)
    @Column(name = "alien", length = 50)
    private String alien;
    @Basic
    @Size(max = 10)
    @Column(name = "delete", length = 10)
    private String delete;
    @Basic
    @Size(max = 10)
    @Column(name = "id", length = 10)
    private String id;
    @Basic
    @Size(max = 10)
    @Column(name = "ID_concord", length = 10)
    private String idConcord;
    @Basic
    @Size(max = 10)
    @Column(name = "NPA_Species_Code", length = 10)
    private String npaSpeciesCode;
    @Basic
    @Size(max = 100)
    @Column(name = "LatinName", length = 100)
    private String latinName;
    @Basic
    @Size(max = 43)
    @Column(name = "NPA_Hebrew", length = 43)
    private String npaHebrew;
    @Basic
    @Size(max = 10)
    @Column(name = "Frag_Latin_No", length = 10)
    private String fragLatinNo;
    @Basic
    @Size(max = 150)
    @Column(name = "CleanScientific_Name", length = 150)
    private String cleanScientificName;
    @Basic
    @Size(max = 100)
    @Column(name = "CleanScientific", length = 100)
    private String cleanScientific;
    @Basic
    @Size(max = 100)
    @Column(name = "Name", length = 100)
    private String name;
    @Basic
    @Size(max = 10)
    @Column(name = "empty", length = 10)
    private String empty;
    @Basic
    @Size(max = 39)
    @Column(name = "Frag_Hebrew", length = 39)
    private String fragHebrew;
    @Basic
    @Size(max = 20)
    @Column(name = "HebrewGenus", length = 20)
    private String hebrewGenus;
    @Basic
    @Size(max = 18)
    @Column(name = "HebrewSpecies", length = 18)
    private String hebrewSpecies;
    @Basic
    @Size(max = 10)
    @Column(name = "Frag_Family_Code", length = 10)
    private String fragFamilyCode;
    @Basic
    @Size(max = 10)
    @Column(name = "Frag_Family_Name", length = 10)
    private String fragFamilyName;
    @Basic
    @Size(max = 50)
    @Column(name = "matched_by_hand", length = 50)
    private String matchedByHand;
    @Basic
    @Size(max = 50)
    @Column(name = "new", length = 50)
    private String newField;
    @Basic
    @Size(max = 50)
    @Column(name = "merged", length = 50)
    private String merged;
    @Basic
    @Size(max = 100)
    @Column(name = "problematic", length = 100)
    private String problematic;

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public String getAlien() {
        return alien;
    }

    public void setAlien(String alien) {
        this.alien = alien;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConcord() {
        return idConcord;
    }

    public void setIdConcord(String idConcord) {
        this.idConcord = idConcord;
    }

    public String getNpaSpeciesCode() {
        return npaSpeciesCode;
    }

    public void setNpaSpeciesCode(String npaSpeciesCode) {
        this.npaSpeciesCode = npaSpeciesCode;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getNpaHebrew() {
        return npaHebrew;
    }

    public void setNpaHebrew(String npaHebrew) {
        this.npaHebrew = npaHebrew;
    }

    public String getFragLatinNo() {
        return fragLatinNo;
    }

    public void setFragLatinNo(String fragLatinNo) {
        this.fragLatinNo = fragLatinNo;
    }

    public String getCleanScientificName() {
        return cleanScientificName;
    }

    public void setCleanScientificName(String cleanScientificName) {
        this.cleanScientificName = cleanScientificName;
    }

    public String getCleanScientific() {
        return cleanScientific;
    }

    public void setCleanScientific(String cleanScientific) {
        this.cleanScientific = cleanScientific;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getFragHebrew() {
        return fragHebrew;
    }

    public void setFragHebrew(String fragHebrew) {
        this.fragHebrew = fragHebrew;
    }

    public void setHebrewGenus(String hebrewGenus) {
        this.hebrewGenus = hebrewGenus;
    }

    public void setHebrewSpecies(String hebrewSpecies) {
        this.hebrewSpecies = hebrewSpecies;
    }

    public String getFragFamilyCode() {
        return fragFamilyCode;
    }

    public void setFragFamilyCode(String fragFamilyCode) {
        this.fragFamilyCode = fragFamilyCode;
    }

    public String getFragFamilyName() {
        return fragFamilyName;
    }

    public void setFragFamilyName(String fragFamilyName) {
        this.fragFamilyName = fragFamilyName;
    }

    public String getMatchedByHand() {
        return matchedByHand;
    }

    public void setMatchedByHand(String matchedByHand) {
        this.matchedByHand = matchedByHand;
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getMerged() {
        return merged;
    }

    public void setMerged(String merged) {
        this.merged = merged;
    }

    public String getProblematic() {
        return problematic;
    }

    public void setProblematic(String problematic) {
        this.problematic = problematic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceHebrewLinda that = (TblSourceHebrewLinda) o;
        return Objects.equals(id1, that.id1) &&
                Objects.equals(alien, that.alien) &&
                Objects.equals(delete, that.delete) &&
                Objects.equals(id, that.id) &&
                Objects.equals(idConcord, that.idConcord) &&
                Objects.equals(npaSpeciesCode, that.npaSpeciesCode) &&
                Objects.equals(latinName, that.latinName) &&
                Objects.equals(npaHebrew, that.npaHebrew) &&
                Objects.equals(fragLatinNo, that.fragLatinNo) &&
                Objects.equals(cleanScientificName, that.cleanScientificName) &&
                Objects.equals(cleanScientific, that.cleanScientific) &&
                Objects.equals(name, that.name) &&
                Objects.equals(empty, that.empty) &&
                Objects.equals(fragHebrew, that.fragHebrew) &&
                Objects.equals(hebrewGenus, that.hebrewGenus) &&
                Objects.equals(hebrewSpecies, that.hebrewSpecies) &&
                Objects.equals(fragFamilyCode, that.fragFamilyCode) &&
                Objects.equals(fragFamilyName, that.fragFamilyName) &&
                Objects.equals(matchedByHand, that.matchedByHand) &&
                Objects.equals(newField, that.newField) &&
                Objects.equals(merged, that.merged) &&
                Objects.equals(problematic, that.problematic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, alien, delete, id, idConcord, npaSpeciesCode, latinName, npaHebrew, fragLatinNo, cleanScientificName, cleanScientific, name, empty, fragHebrew, hebrewGenus, hebrewSpecies, fragFamilyCode, fragFamilyName, matchedByHand, newField, merged, problematic);
    }
}
