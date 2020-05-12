package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_linnaeus_projects", schema = "openup")
public class TblSourceLinnaeusProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;
    @Basic
    @NotNull
    @Column(name = "language", nullable = false, length = 50)
    private String language;
    @Basic
    @NotNull
    @Column(name = "rank", nullable = false, length = 15)
    private String rank;
    @Basic
    @NotNull
    @Column(name = "taxon", nullable = false, length = 150)
    private String taxon;
    @Basic
    @NotNull
    @Column(name = "source", nullable = false, length = 20)
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTaxon() {
        return taxon;
    }

    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceLinnaeusProjects that = (TblSourceLinnaeusProjects) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(language, that.language) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(taxon, that.taxon) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, language, rank, taxon, source);
    }
}
