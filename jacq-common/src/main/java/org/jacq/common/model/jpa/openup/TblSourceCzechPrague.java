package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_czech_prague", schema = "openup")
public class TblSourceCzechPrague {
    @Basic
    @Size(max = 69)
    @Column(name = "Cele_jmeno")
    private String celeJmeno;
    @Id
    @Basic
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Size(max = 6)
    @Column(name = "Kubat_NE")
    private String kubatNe;
    @Basic
    @Size(max = 17)
    @Column(name = "Celed")
    private String celed;
    @Basic
    @Size(max = 18)
    @Column(name = "Rod")
    private String rod;
    @Basic
    @Size(max = 36)
    @Column(name = "Druh_epitet")
    private String druhEpitet;
    @Basic
    @Size(max = 11)
    @Column(name = "infra_rank")
    private String infraRank;
    @Basic
    @Size(max = 18)
    @Column(name = "infra_epithet")
    private String infraEpithet;
    @Basic
    @Size(max = 7)
    @Column(name = "sl_sstr")
    private String slSstr;
    @Basic
    @Size(max = 86)
    @Column(name = "Autor")
    private String autor;
    @Basic
    @Size(max = 260)
    @Column(name = "Synonyma")
    private String synonyma;
    @Basic
    @Size(max = 86)
    @Column(name = "Ceske_jmeno")
    private String ceskeJmeno;

    public String getCeleJmeno() {
        return celeJmeno;
    }

    public void setCeleJmeno(String celeJmeno) {
        this.celeJmeno = celeJmeno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKubatNe() {
        return kubatNe;
    }

    public void setKubatNe(String kubatNe) {
        this.kubatNe = kubatNe;
    }

    public String getCeled() {
        return celed;
    }

    public void setCeled(String celed) {
        this.celed = celed;
    }

    public String getRod() {
        return rod;
    }

    public void setRod(String rod) {
        this.rod = rod;
    }

    public String getDruhEpitet() {
        return druhEpitet;
    }

    public void setDruhEpitet(String druhEpitet) {
        this.druhEpitet = druhEpitet;
    }

    public String getInfraRank() {
        return infraRank;
    }

    public void setInfraRank(String infraRank) {
        this.infraRank = infraRank;
    }

    public String getInfraEpithet() {
        return infraEpithet;
    }

    public void setInfraEpithet(String infraEpithet) {
        this.infraEpithet = infraEpithet;
    }

    public String getSlSstr() {
        return slSstr;
    }

    public void setSlSstr(String slSstr) {
        this.slSstr = slSstr;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSynonyma() {
        return synonyma;
    }

    public void setSynonyma(String synonyma) {
        this.synonyma = synonyma;
    }

    public String getCeskeJmeno() {
        return ceskeJmeno;
    }

    public void setCeskeJmeno(String ceskeJmeno) {
        this.ceskeJmeno = ceskeJmeno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceCzechPrague that = (TblSourceCzechPrague) o;
        return Objects.equals(celeJmeno, that.celeJmeno) &&
                Objects.equals(id, that.id) &&
                Objects.equals(kubatNe, that.kubatNe) &&
                Objects.equals(celed, that.celed) &&
                Objects.equals(rod, that.rod) &&
                Objects.equals(druhEpitet, that.druhEpitet) &&
                Objects.equals(infraRank, that.infraRank) &&
                Objects.equals(infraEpithet, that.infraEpithet) &&
                Objects.equals(slSstr, that.slSstr) &&
                Objects.equals(autor, that.autor) &&
                Objects.equals(synonyma, that.synonyma) &&
                Objects.equals(ceskeJmeno, that.ceskeJmeno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celeJmeno, id, kubatNe, celed, rod, druhEpitet, infraRank, infraEpithet, slSstr, autor, synonyma, ceskeJmeno);
    }
}
