package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_hungarian_peregovits", schema = "openup")
public class TblSourceHungarianPeregovits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @Size(max = 50)
    @Column(name = "Ordo")
    private String ordo;
    @Basic
    @Size(max = 50)
    @Column(name = "Family")
    private String family;
    @Basic
    @Size(max = 50)
    @Column(name = "Genus")
    private String genus;
    @Basic
    @Size(max = 50)
    @Column(name = "species")
    private String species;
    @Basic
    @Size(max = 50)
    @Column(name = "Auctor_year")
    private String auctorYear;
    @Basic
    @Size(max = 50)
    @Column(name = "HU_Common_name")
    private String huCommonName;
    @Basic
    @Size(max = 50)
    @Column(name = "Period")
    private String period;
    @ManyToOne
    @JoinColumn(name = "PUB_ID")
    private TblSourceHungarianPeregovitsLiterature literature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdo() {
        return ordo;
    }

    public void setOrdo(String ordo) {
        this.ordo = ordo;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
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

    public String getAuctorYear() {
        return auctorYear;
    }

    public void setAuctorYear(String auctorYear) {
        this.auctorYear = auctorYear;
    }

    public String getHuCommonName() {
        return huCommonName;
    }

    public void setHuCommonName(String huCommonName) {
        this.huCommonName = huCommonName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public TblSourceHungarianPeregovitsLiterature getLiterature() {
        return literature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceHungarianPeregovits that = (TblSourceHungarianPeregovits) o;
        return Objects.equals(ordo, that.ordo) &&
                Objects.equals(family, that.family) &&
                Objects.equals(genus, that.genus) &&
                Objects.equals(species, that.species) &&
                Objects.equals(auctorYear, that.auctorYear) &&
                Objects.equals(huCommonName, that.huCommonName) &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordo, family, genus, species, auctorYear, huCommonName, period);
    }
}
