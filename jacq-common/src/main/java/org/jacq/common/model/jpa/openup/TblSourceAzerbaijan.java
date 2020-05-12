package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_azerbaijan", schema = "openup")
public class TblSourceAzerbaijan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @Size(max = 50)
    @Column(name = "FAMİLY")
    private String fami̇ly;
    @Basic
    @Size(max = 50)
    @Column(name = "FAMAUTHOR")
    private String famauthor;
    @Basic
    @Size(max = 50)
    @Column(name = "GENUS")
    private String genus;
    @Basic
    @Size(max = 50)
    @Column(name = "GENAUTHOR")
    private String genauthor;
    @Basic
    @Size(max = 100)
    @Column(name = "SPECIES")
    private String species;
    @Basic
    @Size(max = 100)
    @Column(name = "SPAUTHOR")
    private String spauthor;
    @Basic
    @Size(max = 50)
    @Column(name = "infrarank")
    private String infrarank;
    @Basic
    @Size(max = 50)
    @Column(name = "SUBTAXA")
    private String subtaxa;
    @Basic
    @Size(max = 50)
    @Column(name = "SUBTAUTHOR")
    private String subtauthor;
    @Basic
    @Size(max = 150)
    @Column(name = "AZERİNAME")
    private String azeri̇name;
    @Basic
    @Size(max = 150)
    @Column(name = "RUSNAME")
    private String rusname;
    @Basic
    @Size(max = 150)
    @Column(name = "ENGNAME")
    private String engname;
    @Basic
    @Size(max = 150)
    @Column(name = "ACCENAME")
    private String accename;
    @Basic
    @Size(max = 150)
    @Column(name = "OTHERNAME")
    private String othername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFami̇ly() {
        return fami̇ly;
    }

    public void setFami̇ly(String fami̇ly) {
        this.fami̇ly = fami̇ly;
    }

    public String getFamauthor() {
        return famauthor;
    }

    public void setFamauthor(String famauthor) {
        this.famauthor = famauthor;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getGenauthor() {
        return genauthor;
    }

    public void setGenauthor(String genauthor) {
        this.genauthor = genauthor;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpauthor() {
        return spauthor;
    }

    public void setSpauthor(String spauthor) {
        this.spauthor = spauthor;
    }

    public String getInfrarank() {
        return infrarank;
    }

    public void setInfrarank(String infrarank) {
        this.infrarank = infrarank;
    }

    public String getSubtaxa() {
        return subtaxa;
    }

    public void setSubtaxa(String subtaxa) {
        this.subtaxa = subtaxa;
    }

    public String getSubtauthor() {
        return subtauthor;
    }

    public void setSubtauthor(String subtauthor) {
        this.subtauthor = subtauthor;
    }

    public String getAzeri̇name() {
        return azeri̇name;
    }

    public void setAzeri̇name(String azeri̇name) {
        this.azeri̇name = azeri̇name;
    }

    public String getRusname() {
        return rusname;
    }

    public void setRusname(String rusname) {
        this.rusname = rusname;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getAccename() {
        return accename;
    }

    public void setAccename(String accename) {
        this.accename = accename;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceAzerbaijan that = (TblSourceAzerbaijan) o;
        return Objects.equals(fami̇ly, that.fami̇ly) &&
                Objects.equals(famauthor, that.famauthor) &&
                Objects.equals(genus, that.genus) &&
                Objects.equals(genauthor, that.genauthor) &&
                Objects.equals(species, that.species) &&
                Objects.equals(spauthor, that.spauthor) &&
                Objects.equals(infrarank, that.infrarank) &&
                Objects.equals(subtaxa, that.subtaxa) &&
                Objects.equals(subtauthor, that.subtauthor) &&
                Objects.equals(azeri̇name, that.azeri̇name) &&
                Objects.equals(rusname, that.rusname) &&
                Objects.equals(engname, that.engname) &&
                Objects.equals(accename, that.accename) &&
                Objects.equals(othername, that.othername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fami̇ly, famauthor, genus, genauthor, species, spauthor, infrarank, subtaxa, subtauthor, azeri̇name, rusname, engname, accename, othername);
    }
}
