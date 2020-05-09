package org.jacq.common.model.jpa.openup;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_allearter_dk", schema = "openup")
public class TblSourceAllearterDk {
    @Basic
    @Size(max = 100)
    @Column(name = "Videnskabeligt_navn")
    private String videnskabeligtNavn;
    @Basic
    @Size(max = 150)
    @Column(name = "Autor")
    private String autor;
    @Basic
    @Size(max = 100)
    @Column(name = "Dansk_navn")
    private String danskNavn;
    @Basic
    @Size(max = 50)
    @Column(name = "Artsgruppe")
    private String artsgruppe;
    @Basic
    @Size(max = 50)
    @Column(name = "Artsgruppe_dk")
    private String artsgruppeDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Taxontype")
    private String taxontype;
    @Basic
    @Size(max = 20)
    @Column(name = "Taxonstatus")
    private String taxonstatus;
    @Basic
    @Size(max = 50)
    @Column(name = "Rige")
    private String rige;
    @Basic
    @Size(max = 50)
    @Column(name = "Rige_dk")
    private String rigeDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Række")
    private String række;
    @Basic
    @Size(max = 50)
    @Column(name = "Række_dk")
    private String rækkeDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Underrække")
    private String underrække;
    @Basic
    @Size(max = 50)
    @Column(name = "Underrække_dk")
    private String underrækkeDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Overklasse")
    private String overklasse;
    @Basic
    @Size(max = 50)
    @Column(name = "Overklasse_dk")
    private String overklasseDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Klasse")
    private String klasse;
    @Basic
    @Size(max = 50)
    @Column(name = "Klasse_dk")
    private String klasseDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Underklasse")
    private String underklasse;
    @Basic
    @Size(max = 50)
    @Column(name = "Underklasse_dk")
    private String underklasseDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Infraklasse")
    private String infraklasse;
    @Basic
    @Size(max = 50)
    @Column(name = "Infraklasse_dk")
    private String infraklasseDk;
    @Basic
    @Size(max = 20)
    @Column(name = "Overorden")
    private String overorden;
    @Basic
    @Size(max = 20)
    @Column(name = "Overorden_dk")
    private String overordenDk;
    @Basic
    @Size(max = 20)
    @Column(name = "Orden")
    private String orden;
    @Basic
    @Size(max = 20)
    @Column(name = "Orden_dk")
    private String ordenDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Underorden")
    private String underorden;
    @Basic
    @Size(max = 50)
    @Column(name = "Underorden_dk")
    private String underordenDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Infraorden")
    private String infraorden;
    @Basic
    @Size(max = 50)
    @Column(name = "Infraorden_dk")
    private String infraordenDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Overfamilie")
    private String overfamilie;
    @Basic
    @Size(max = 50)
    @Column(name = "Overfamilie_dk")
    private String overfamilieDk;
    @Basic
    @Size(max = 150)
    @Column(name = "Familie")
    private String familie;
    @Basic
    @Size(max = 150)
    @Column(name = "Familie_dk")
    private String familieDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Underfamilie")
    private String underfamilie;
    @Basic
    @Size(max = 50)
    @Column(name = "Underfamilie_dk")
    private String underfamilieDk;
    @Basic
    @Size(max = 50)
    @Column(name = "Tribus")
    private String tribus;
    @Basic
    @Size(max = 20)
    @Column(name = "Tribus_dk")
    private String tribusDk;
    @Basic
    @Size(max = 255)
    @Column(name = "Synonymer")
    private String synonymer;
    @Basic
    @Size(max = 255)
    @Column(name = "Synonymer_dk")
    private String synonymerDk;
    @Basic
    @Size(max = 100)
    @Column(name = "Referencenavn")
    private String referencenavn;
    @Basic
    @Size(max = 50)
    @Column(name = "Referenceår")
    private String referenceår;
    @Basic
    @Column(name = "Referencetekst", length = -1)
    private String referencetekst;
    @Basic
    @Column(name = "Systematik", length = -1)
    private String systematik;
    @Basic
    @Size(max = 200)
    @Column(name = "Forekomst")
    private String forekomst;
    @Basic
    @Size(max = 150)
    @Column(name = "Økologi")
    private String økologi;
    @Basic
    @Size(max = 200)
    @Column(name = "Status")
    private String status;
    @Basic
    @Size(max = 20)
    @Column(name = "Dato")
    private String dato;
    @Basic
    @Size(max = 20)
    @Column(name = "Sortering")
    private String sortering;
    @Basic
    @Size(max = 50)
    @Column(name = "Den_danske_rødliste")
    private String denDanskeRødliste;
    @Basic
    @Size(max = 50)
    @Column(name = "Fredede_arter")
    private String frededeArter;
    @Basic
    @Size(max = 20)
    @Column(name = "EF_habitatdirektivet")
    private String efHabitatdirektivet;
    @Basic
    @Size(max = 20)
    @Column(name = "EF_fuglebeskyttelsesdirektivet")
    private String efFuglebeskyttelsesdirektivet;
    @Basic
    @Size(max = 20)
    @Column(name = "Bern_konventionen")
    private String bernKonventionen;
    @Basic
    @Size(max = 20)
    @Column(name = "Bonn_konventionen")
    private String bonnKonventionen;
    @Basic
    @Size(max = 20)
    @Column(name = "CITES")
    private String cites;
    @Basic
    @Size(max = 20)
    @Column(name = "Øvrige_forvaltningskategorier")
    private String øvrigeForvaltningskategorier;
    @Basic
    @Size(max = 200)
    @Column(name = "NOBANIS")
    private String nobanis;
    @Basic
    @Size(max = 20)
    @Column(name = "NOBANIS_herkomst")
    private String nobanisHerkomst;
    @Basic
    @Size(max = 100)
    @Column(name = "NOBANIS_etableringsstatus")
    private String nobanisEtableringsstatus;
    @Basic
    @Size(max = 20)
    @Column(name = "NOBANIS_invasiv_optraeden")
    private String nobanisInvasivOptraeden;

    public String getVidenskabeligtNavn() {
        return videnskabeligtNavn;
    }

    public void setVidenskabeligtNavn(String videnskabeligtNavn) {
        this.videnskabeligtNavn = videnskabeligtNavn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDanskNavn() {
        return danskNavn;
    }

    public void setDanskNavn(String danskNavn) {
        this.danskNavn = danskNavn;
    }

    public String getArtsgruppe() {
        return artsgruppe;
    }

    public void setArtsgruppe(String artsgruppe) {
        this.artsgruppe = artsgruppe;
    }

    public String getArtsgruppeDk() {
        return artsgruppeDk;
    }

    public void setArtsgruppeDk(String artsgruppeDk) {
        this.artsgruppeDk = artsgruppeDk;
    }

    public String getTaxontype() {
        return taxontype;
    }

    public void setTaxontype(String taxontype) {
        this.taxontype = taxontype;
    }

    public String getTaxonstatus() {
        return taxonstatus;
    }

    public void setTaxonstatus(String taxonstatus) {
        this.taxonstatus = taxonstatus;
    }

    public String getRige() {
        return rige;
    }

    public void setRige(String rige) {
        this.rige = rige;
    }

    public String getRigeDk() {
        return rigeDk;
    }

    public void setRigeDk(String rigeDk) {
        this.rigeDk = rigeDk;
    }

    public String getRække() {
        return række;
    }

    public void setRække(String række) {
        this.række = række;
    }

    public String getRækkeDk() {
        return rækkeDk;
    }

    public void setRækkeDk(String rækkeDk) {
        this.rækkeDk = rækkeDk;
    }

    public String getUnderrække() {
        return underrække;
    }

    public void setUnderrække(String underrække) {
        this.underrække = underrække;
    }

    public String getUnderrækkeDk() {
        return underrækkeDk;
    }

    public void setUnderrækkeDk(String underrækkeDk) {
        this.underrækkeDk = underrækkeDk;
    }

    public String getOverklasse() {
        return overklasse;
    }

    public void setOverklasse(String overklasse) {
        this.overklasse = overklasse;
    }

    public String getOverklasseDk() {
        return overklasseDk;
    }

    public void setOverklasseDk(String overklasseDk) {
        this.overklasseDk = overklasseDk;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getKlasseDk() {
        return klasseDk;
    }

    public void setKlasseDk(String klasseDk) {
        this.klasseDk = klasseDk;
    }

    public String getUnderklasse() {
        return underklasse;
    }

    public void setUnderklasse(String underklasse) {
        this.underklasse = underklasse;
    }

    public String getUnderklasseDk() {
        return underklasseDk;
    }

    public void setUnderklasseDk(String underklasseDk) {
        this.underklasseDk = underklasseDk;
    }

    public String getInfraklasse() {
        return infraklasse;
    }

    public void setInfraklasse(String infraklasse) {
        this.infraklasse = infraklasse;
    }

    public String getInfraklasseDk() {
        return infraklasseDk;
    }

    public void setInfraklasseDk(String infraklasseDk) {
        this.infraklasseDk = infraklasseDk;
    }

    public String getOverorden() {
        return overorden;
    }

    public void setOverorden(String overorden) {
        this.overorden = overorden;
    }

    public String getOverordenDk() {
        return overordenDk;
    }

    public void setOverordenDk(String overordenDk) {
        this.overordenDk = overordenDk;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getOrdenDk() {
        return ordenDk;
    }

    public void setOrdenDk(String ordenDk) {
        this.ordenDk = ordenDk;
    }

    public String getUnderorden() {
        return underorden;
    }

    public void setUnderorden(String underorden) {
        this.underorden = underorden;
    }

    public String getUnderordenDk() {
        return underordenDk;
    }

    public void setUnderordenDk(String underordenDk) {
        this.underordenDk = underordenDk;
    }

    public String getInfraorden() {
        return infraorden;
    }

    public void setInfraorden(String infraorden) {
        this.infraorden = infraorden;
    }

    public String getInfraordenDk() {
        return infraordenDk;
    }

    public void setInfraordenDk(String infraordenDk) {
        this.infraordenDk = infraordenDk;
    }

    public String getOverfamilie() {
        return overfamilie;
    }

    public void setOverfamilie(String overfamilie) {
        this.overfamilie = overfamilie;
    }

    public String getOverfamilieDk() {
        return overfamilieDk;
    }

    public void setOverfamilieDk(String overfamilieDk) {
        this.overfamilieDk = overfamilieDk;
    }

    public String getFamilie() {
        return familie;
    }

    public void setFamilie(String familie) {
        this.familie = familie;
    }

    public String getFamilieDk() {
        return familieDk;
    }

    public void setFamilieDk(String familieDk) {
        this.familieDk = familieDk;
    }

    public String getUnderfamilie() {
        return underfamilie;
    }

    public void setUnderfamilie(String underfamilie) {
        this.underfamilie = underfamilie;
    }

    public String getUnderfamilieDk() {
        return underfamilieDk;
    }

    public void setUnderfamilieDk(String underfamilieDk) {
        this.underfamilieDk = underfamilieDk;
    }

    public String getTribus() {
        return tribus;
    }

    public void setTribus(String tribus) {
        this.tribus = tribus;
    }

    public String getTribusDk() {
        return tribusDk;
    }

    public void setTribusDk(String tribusDk) {
        this.tribusDk = tribusDk;
    }

    public String getSynonymer() {
        return synonymer;
    }

    public void setSynonymer(String synonymer) {
        this.synonymer = synonymer;
    }

    public String getSynonymerDk() {
        return synonymerDk;
    }

    public void setSynonymerDk(String synonymerDk) {
        this.synonymerDk = synonymerDk;
    }

    public String getReferencenavn() {
        return referencenavn;
    }

    public void setReferencenavn(String referencenavn) {
        this.referencenavn = referencenavn;
    }

    public String getReferenceår() {
        return referenceår;
    }

    public void setReferenceår(String referenceår) {
        this.referenceår = referenceår;
    }

    public String getReferencetekst() {
        return referencetekst;
    }

    public void setReferencetekst(String referencetekst) {
        this.referencetekst = referencetekst;
    }

    public String getSystematik() {
        return systematik;
    }

    public void setSystematik(String systematik) {
        this.systematik = systematik;
    }

    public String getForekomst() {
        return forekomst;
    }

    public void setForekomst(String forekomst) {
        this.forekomst = forekomst;
    }

    public String getØkologi() {
        return økologi;
    }

    public void setØkologi(String økologi) {
        this.økologi = økologi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getSortering() {
        return sortering;
    }

    public void setSortering(String sortering) {
        this.sortering = sortering;
    }

    public String getDenDanskeRødliste() {
        return denDanskeRødliste;
    }

    public void setDenDanskeRødliste(String denDanskeRødliste) {
        this.denDanskeRødliste = denDanskeRødliste;
    }

    public String getFrededeArter() {
        return frededeArter;
    }

    public void setFrededeArter(String frededeArter) {
        this.frededeArter = frededeArter;
    }

    public String getEfHabitatdirektivet() {
        return efHabitatdirektivet;
    }

    public void setEfHabitatdirektivet(String efHabitatdirektivet) {
        this.efHabitatdirektivet = efHabitatdirektivet;
    }

    public String getEfFuglebeskyttelsesdirektivet() {
        return efFuglebeskyttelsesdirektivet;
    }

    public void setEfFuglebeskyttelsesdirektivet(String efFuglebeskyttelsesdirektivet) {
        this.efFuglebeskyttelsesdirektivet = efFuglebeskyttelsesdirektivet;
    }

    public String getBernKonventionen() {
        return bernKonventionen;
    }

    public void setBernKonventionen(String bernKonventionen) {
        this.bernKonventionen = bernKonventionen;
    }

    public String getBonnKonventionen() {
        return bonnKonventionen;
    }

    public void setBonnKonventionen(String bonnKonventionen) {
        this.bonnKonventionen = bonnKonventionen;
    }

    public String getCites() {
        return cites;
    }

    public void setCites(String cites) {
        this.cites = cites;
    }

    public String getØvrigeForvaltningskategorier() {
        return øvrigeForvaltningskategorier;
    }

    public void setØvrigeForvaltningskategorier(String øvrigeForvaltningskategorier) {
        this.øvrigeForvaltningskategorier = øvrigeForvaltningskategorier;
    }

    public String getNobanis() {
        return nobanis;
    }

    public void setNobanis(String nobanis) {
        this.nobanis = nobanis;
    }

    public String getNobanisHerkomst() {
        return nobanisHerkomst;
    }

    public void setNobanisHerkomst(String nobanisHerkomst) {
        this.nobanisHerkomst = nobanisHerkomst;
    }

    public String getNobanisEtableringsstatus() {
        return nobanisEtableringsstatus;
    }

    public void setNobanisEtableringsstatus(String nobanisEtableringsstatus) {
        this.nobanisEtableringsstatus = nobanisEtableringsstatus;
    }

    public String getNobanisInvasivOptraeden() {
        return nobanisInvasivOptraeden;
    }

    public void setNobanisInvasivOptraeden(String nobanisInvasivOptraeden) {
        this.nobanisInvasivOptraeden = nobanisInvasivOptraeden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceAllearterDk that = (TblSourceAllearterDk) o;
        return Objects.equals(videnskabeligtNavn, that.videnskabeligtNavn) &&
                Objects.equals(autor, that.autor) &&
                Objects.equals(danskNavn, that.danskNavn) &&
                Objects.equals(artsgruppe, that.artsgruppe) &&
                Objects.equals(artsgruppeDk, that.artsgruppeDk) &&
                Objects.equals(taxontype, that.taxontype) &&
                Objects.equals(taxonstatus, that.taxonstatus) &&
                Objects.equals(rige, that.rige) &&
                Objects.equals(rigeDk, that.rigeDk) &&
                Objects.equals(række, that.række) &&
                Objects.equals(rækkeDk, that.rækkeDk) &&
                Objects.equals(underrække, that.underrække) &&
                Objects.equals(underrækkeDk, that.underrækkeDk) &&
                Objects.equals(overklasse, that.overklasse) &&
                Objects.equals(overklasseDk, that.overklasseDk) &&
                Objects.equals(klasse, that.klasse) &&
                Objects.equals(klasseDk, that.klasseDk) &&
                Objects.equals(underklasse, that.underklasse) &&
                Objects.equals(underklasseDk, that.underklasseDk) &&
                Objects.equals(infraklasse, that.infraklasse) &&
                Objects.equals(infraklasseDk, that.infraklasseDk) &&
                Objects.equals(overorden, that.overorden) &&
                Objects.equals(overordenDk, that.overordenDk) &&
                Objects.equals(orden, that.orden) &&
                Objects.equals(ordenDk, that.ordenDk) &&
                Objects.equals(underorden, that.underorden) &&
                Objects.equals(underordenDk, that.underordenDk) &&
                Objects.equals(infraorden, that.infraorden) &&
                Objects.equals(infraordenDk, that.infraordenDk) &&
                Objects.equals(overfamilie, that.overfamilie) &&
                Objects.equals(overfamilieDk, that.overfamilieDk) &&
                Objects.equals(familie, that.familie) &&
                Objects.equals(familieDk, that.familieDk) &&
                Objects.equals(underfamilie, that.underfamilie) &&
                Objects.equals(underfamilieDk, that.underfamilieDk) &&
                Objects.equals(tribus, that.tribus) &&
                Objects.equals(tribusDk, that.tribusDk) &&
                Objects.equals(synonymer, that.synonymer) &&
                Objects.equals(synonymerDk, that.synonymerDk) &&
                Objects.equals(referencenavn, that.referencenavn) &&
                Objects.equals(referenceår, that.referenceår) &&
                Objects.equals(referencetekst, that.referencetekst) &&
                Objects.equals(systematik, that.systematik) &&
                Objects.equals(forekomst, that.forekomst) &&
                Objects.equals(økologi, that.økologi) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dato, that.dato) &&
                Objects.equals(sortering, that.sortering) &&
                Objects.equals(denDanskeRødliste, that.denDanskeRødliste) &&
                Objects.equals(frededeArter, that.frededeArter) &&
                Objects.equals(efHabitatdirektivet, that.efHabitatdirektivet) &&
                Objects.equals(efFuglebeskyttelsesdirektivet, that.efFuglebeskyttelsesdirektivet) &&
                Objects.equals(bernKonventionen, that.bernKonventionen) &&
                Objects.equals(bonnKonventionen, that.bonnKonventionen) &&
                Objects.equals(cites, that.cites) &&
                Objects.equals(øvrigeForvaltningskategorier, that.øvrigeForvaltningskategorier) &&
                Objects.equals(nobanis, that.nobanis) &&
                Objects.equals(nobanisHerkomst, that.nobanisHerkomst) &&
                Objects.equals(nobanisEtableringsstatus, that.nobanisEtableringsstatus) &&
                Objects.equals(nobanisInvasivOptraeden, that.nobanisInvasivOptraeden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videnskabeligtNavn, autor, danskNavn, artsgruppe, artsgruppeDk, taxontype, taxonstatus, rige, rigeDk, række, rækkeDk, underrække, underrækkeDk, overklasse, overklasseDk, klasse, klasseDk, underklasse, underklasseDk, infraklasse, infraklasseDk, overorden, overordenDk, orden, ordenDk, underorden, underordenDk, infraorden, infraordenDk, overfamilie, overfamilieDk, familie, familieDk, underfamilie, underfamilieDk, tribus, tribusDk, synonymer, synonymerDk, referencenavn, referenceår, referencetekst, systematik, forekomst, økologi, status, dato, sortering, denDanskeRødliste, frededeArter, efHabitatdirektivet, efFuglebeskyttelsesdirektivet, bernKonventionen, bonnKonventionen, cites, øvrigeForvaltningskategorier, nobanis, nobanisHerkomst, nobanisEtableringsstatus, nobanisInvasivOptraeden);
    }
}
