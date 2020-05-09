package org.jacq.common.model.jpa.openup;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_czech_jiri_vacnatci", schema = "openup")
public class TblSourceCzechJiriVacnatci {
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "latin_name", nullable = false)
    private String latinName;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "synonym", nullable = false)
    private String synonym;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "czech_name", nullable = false)
    private String czechName;

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getCzechName() {
        return czechName;
    }

    public void setCzechName(String czechName) {
        this.czechName = czechName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceCzechJiriVacnatci that = (TblSourceCzechJiriVacnatci) o;
        return Objects.equals(latinName, that.latinName) &&
                Objects.equals(synonym, that.synonym) &&
                Objects.equals(czechName, that.czechName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latinName, synonym, czechName);
    }
}
