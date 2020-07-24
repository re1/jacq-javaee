package org.jacq.common.model.names.artsdatabanken;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <ArtssokResponse xmlns="http://artsdatabanken.no/webtjenester">
 *     <ArtssokResult>
 *         <LatinskNavn
 *             LatinskNavnID="103881"
 *             OverordnaLatinskNavnID="103874"
 *             Kategori="Art"
 *             KategoriID="22"
 *             Hovedstatus="Gyldig"
 *             xmlns="http://artsdatabanken.no/Artsnavnebase">
 *                 <Rike KategoriID="1" LatinskNavnID="1131" TypeEksID="1131">Plantae</Rike>
 *                 <Rekke KategoriID="3" LatinskNavnID="1381" TypeEksID="1381">Pteridophyta</Rekke>
 *                 <Klasse KategoriID="6" LatinskNavnID="1387" TypeEksID="1387">Polypodiopsida</Klasse>
 *                 <Orden KategoriID="11" LatinskNavnID="1390" TypeEksID="1390">Polypodiales</Orden>
 *                 <Familie KategoriID="15" LatinskNavnID="103873" TypeEksID="95544">Aspleniaceae</Familie>
 *                 <Slekt KategoriID="19" LatinskNavnID="103874" TypeEksID="95545">Asplenium</Slekt>
 *                 <Art>septentrionale</Art>
 *                 <VitenskapligNavn>Asplenium septentrionale</VitenskapligNavn>
 *                 <Autorstreng>(L.) Hoffm.</Autorstreng>
 *                 <Gruppe GruppeID="5">Karplanter (Norge)</Gruppe>
 *                 <Gruppe GruppeID="79">Karplanter</Gruppe>
 *                 <Takson TaksonID="130750" TypeEksID="95552" FinnesINorge="true">
 *                     <Popularnavn Spraak="Bokm?l">
 *                         <Navn Anbefalt="true">olavsskjegg</Navn>
 *                     </Popularnavn>
 *                     <Popularnavn Spraak="Nynorsk">
 *                         <Navn Anbefalt="true">olavsskjegg</Navn>
 *                     </Popularnavn>
 *                     <Popularnavn Spraak="Nordsamisk">
 *                         <Navn Anbefalt="true">riesalomot</Navn>
 *                     </Popularnavn>
 *                 </Takson>
 *         </LatinskNavn>
 *     </ArtssokResult>
 * </ArtssokResponse>
 */

@XmlRootElement(name = "ArtssokResponse", namespace = "http://artsdatabanken.no/webtjenester")
public class ArtssokResponse {

    @XmlRootElement(name = "ArtssokResult")
    private static class ArtssokResult {

        @XmlRootElement(name = "LatinskNavn", namespace = "http://artsdatabanken.no/Artsnavnebase")
        private static class LatinskNavn {

        }

    }

    @XmlElement(name = "Search", namespace = "http://artsdatabanken.no/webtjenester")
    private String search;

    /**
     * Empty constructor for JAXB
     */
    public ArtssokResponse() {
    }
}