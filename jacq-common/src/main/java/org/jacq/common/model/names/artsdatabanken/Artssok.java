package org.jacq.common.model.names.artsdatabanken;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Artssok", namespace = "http://artsdatabanken.no/Artsnavnebase")
public class Artssok {

    @XmlElement(name = "LatinskNavn")
    List<LatinskNavn> latinskNavn = new ArrayList<>();

    /**
     * Empty constructor for JAXB
     */
    public Artssok() {
    }

    static class LatinskNavn {
        @XmlAttribute(name = "LatinskNavnID")
        String latinskNavnID;

        @XmlAttribute(name = "OverordnaLatinskNavnID")
        String overordnaLatinskNavnID;

        @XmlAttribute(name = "Kategori")
        String kategori;

        @XmlAttribute(name = "KategoriID")
        String kategoriID;

        @XmlAttribute(name = "Underordnede")
        String underordnede;

        @XmlAttribute(name = "Hovedstatus")
        String hovedstatus;

        @XmlElement(name = "Rike")
        Classification rike;

        @XmlElement(name = "Rekke")
        Classification rekke;

        @XmlElement(name = "Underrekke")
        Classification underrekke;

        @XmlElement(name = "Klasse")
        Classification klasse;

        @XmlElement(name = "Orden")
        Classification orden;

        @XmlElement(name = "Underorden")
        Classification underorden;

        @XmlElement(name = "Familie")
        Classification familie;

        @XmlElement(name = "Rekke")
        Classification slekt;

        @XmlElement(name = "Art")
        String art;

        @XmlElement(name = "VitenskapligNavn")
        String vitenskapligNavn;

        @XmlElement(name = "Autorstreng")
        String autorstreng;

        @XmlElement(name = "Gruppe")
        Gruppe gruppeNorge;

        @XmlElement(name = "Gruppe")
        Gruppe gruppe;

        @XmlElement(name = "Takson")
        Takson takson;

        static class Classification {
            @XmlAttribute(name = "KategoriID")
            String kategoriID;

            @XmlAttribute(name = "LatinskNavnID")
            String latinskNavnID;

            @XmlAttribute(name = "TypeEksID")
            String typeEksID;

            @XmlValue
            String value;
        }

        static class Gruppe {
            @XmlAttribute(name = "GruppeID")
            String gruppeID;
        }

        static class Takson {
            @XmlAttribute(name = "TaksonID")
            String taksonID;

            @XmlAttribute(name = "TypeEksID")
            String typeEksID;

            @XmlAttribute(name = "FinnesINorge")
            boolean finnesINorge;

            @XmlElement(name = "Popularnavn")
            List<Popularnavn> popularnavn = new ArrayList<>();

            static class Popularnavn {
                @XmlElement(name = "Navn")
                Navn navn;

                static class Navn {
                    @XmlAttribute(name = "Anbefalt")
                    boolean anbefalt;

                    @XmlValue
                    String value;
                }
            }
        }
    }
}