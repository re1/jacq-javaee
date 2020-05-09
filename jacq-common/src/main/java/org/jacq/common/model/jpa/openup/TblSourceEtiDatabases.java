package org.jacq.common.model.jpa.openup;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_eti_databases", schema = "openup", catalog = "")
public class TblSourceEtiDatabases {
    @Basic
    @NotNull
    @Size(max = 15)
    @Column(name = "Name", nullable = false)
    private String name;
    @Basic
    @NotNull
    @Size(max = 6)
    @Column(name = "iso_639_6", nullable = false)
    private String iso6396;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "Taxon", nullable = false)
    private String taxon;
    @Basic
    @NotNull
    @Size(max = 3)
    @Column(name = "Source", nullable = false)
    private String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso6396() {
        return iso6396;
    }

    public void setIso6396(String iso6396) {
        this.iso6396 = iso6396;
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
        TblSourceEtiDatabases that = (TblSourceEtiDatabases) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(iso6396, that.iso6396) &&
                Objects.equals(taxon, that.taxon) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iso6396, taxon, source);
    }
}
