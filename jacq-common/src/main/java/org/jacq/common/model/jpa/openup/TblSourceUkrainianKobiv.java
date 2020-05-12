package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_ukrainian_kobiv", schema = "openup")
public class TblSourceUkrainianKobiv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @Size(max = 10)
    @Column(name = "IND", length = 10)
    private String ind;
    @Basic
    @Size(max = 60)
    @Column(name = "UNAR", length = 60)
    private String unar;
    @Basic
    @Size(max = 60)
    @Column(name = "UTYPESET", length = 60)
    private String utypeset;
    @Basic
    @Size(max = 100)
    @Column(name = "LNOM", length = 100)
    private String lnom;
    @Basic
    @Size(max = 70)
    @Column(name = "LCIT", length = 70)
    private String lcit;
    @Basic
    @Size(max = 140)
    @Column(name = "LIT", length = 140)
    private String lit;
    @Basic
    @Size(max = 40)
    @Column(name = "GEOSK", length = 40)
    private String geosk;
    @Basic
    @Size(max = 100)
    @Column(name = "LSECOND", length = 100)
    private String lsecond;
    @Basic
    @Size(max = 50)
    @Column(name = "LSYNON", length = 50)
    private String lsynon;
    @Basic
    @Size(max = 10)
    @Column(name = "PRIOR", length = 10)
    private String prior;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getUnar() {
        return unar;
    }

    public void setUnar(String unar) {
        this.unar = unar;
    }

    public String getUtypeset() {
        return utypeset;
    }

    public void setUtypeset(String utypeset) {
        this.utypeset = utypeset;
    }

    public String getLnom() {
        return lnom;
    }

    public void setLnom(String lnom) {
        this.lnom = lnom;
    }

    public String getLcit() {
        return lcit;
    }

    public void setLcit(String lcit) {
        this.lcit = lcit;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    public String getGeosk() {
        return geosk;
    }

    public void setGeosk(String geosk) {
        this.geosk = geosk;
    }

    public String getLsecond() {
        return lsecond;
    }

    public void setLsecond(String lsecond) {
        this.lsecond = lsecond;
    }

    public String getLsynon() {
        return lsynon;
    }

    public void setLsynon(String lsynon) {
        this.lsynon = lsynon;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceUkrainianKobiv that = (TblSourceUkrainianKobiv) o;
        return Objects.equals(ind, that.ind) &&
                Objects.equals(unar, that.unar) &&
                Objects.equals(utypeset, that.utypeset) &&
                Objects.equals(lnom, that.lnom) &&
                Objects.equals(lcit, that.lcit) &&
                Objects.equals(lit, that.lit) &&
                Objects.equals(geosk, that.geosk) &&
                Objects.equals(lsecond, that.lsecond) &&
                Objects.equals(lsynon, that.lsynon) &&
                Objects.equals(prior, that.prior);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ind, unar, utypeset, lnom, lcit, lit, geosk, lsecond, lsynon, prior);
    }
}
