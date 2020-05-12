package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_czech_jiri_roztoci", schema = "openup")
public class TblSourceCzechJiriRoztoci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "latin_name", nullable = false)
    private String latinName;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "czech_name", nullable = false)
    private String czechName;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "first_synonym", nullable = false)
    private String firstSynonym;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "second_synonym", nullable = false)
    private String secondSynonym;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getCzechName() {
        return czechName;
    }

    public void setCzechName(String czechName) {
        this.czechName = czechName;
    }

    public String getFirstSynonym() {
        return firstSynonym;
    }

    public void setFirstSynonym(String firstSynonym) {
        this.firstSynonym = firstSynonym;
    }

    public String getSecondSynonym() {
        return secondSynonym;
    }

    public void setSecondSynonym(String secondSynonym) {
        this.secondSynonym = secondSynonym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceCzechJiriRoztoci that = (TblSourceCzechJiriRoztoci) o;
        return Objects.equals(latinName, that.latinName) &&
                Objects.equals(czechName, that.czechName) &&
                Objects.equals(firstSynonym, that.firstSynonym) &&
                Objects.equals(secondSynonym, that.secondSynonym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latinName, czechName, firstSynonym, secondSynonym);
    }
}
