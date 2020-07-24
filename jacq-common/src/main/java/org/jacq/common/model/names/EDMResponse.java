package org.jacq.common.model.names;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Wrapper object for Europeana Data Model response format
 *
 * @author re1
 */
@XmlRootElement(name = "RDF", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
public class EDMResponse implements NameResponse<CommonName> {

    @XmlElement(name = "Concept", namespace = "http://www.w3.org/2004/02/skos/core#")
    private List<EDMConcept> concepts = new ArrayList<>();

    public void setResult(List<CommonName> result) {
        // add EDM concept for each common name
        result.forEach(commonName -> this.concepts.add(new EDMConcept(
                commonName.getId(),
                commonName.getName(),
                commonName.getFirstType(),
                commonName.getLanguage(),
                commonName.getGeography(),
                commonName.getPeriod(),
                commonName.getReferences(),
                commonName.getTaxonId())));
    }

    /**
     * Solely used to display Europeana Data Model Concept objects
     */
    static class EDMConcept {
        @XmlAttribute(namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
        private String about;
        @XmlElement(namespace = "http://www.w3.org/2004/02/skos/core#")
        private String prefLabel;
        @XmlElement(name = "note", namespace = "http://www.w3.org/2004/02/skos/core#")
        private String typeNote;
        @XmlElement(name = "note", namespace = "http://www.w3.org/2004/02/skos/core#")
        private String languageNote;
        @XmlElement(name = "note", namespace = "http://www.w3.org/2004/02/skos/core#")
        private String geographyNote;
        @XmlElement(name = "note", namespace = "http://www.w3.org/2004/02/skos/core#")
        private String periodNote;
        @XmlElement(name = "note", namespace = "http://www.w3.org/2004/02/skos/core#")
        private String referenceNote;
        @XmlElement(namespace = "http://www.w3.org/2004/02/skos/core#")
        private String editorialNote;

        /**
         * Empty constructor for JAXB
         */
        EDMConcept() {
        }

        /**
         * Creates an EDM concept object for representing a NameResponse result as XML
         *
         * @param id          name id used for about URL
         * @param name        concept label
         * @param type        concept type description
         * @param language    concept language code
         * @param geography   concept verbose geography
         * @param period      concept verbose time period
         * @param references  list of references for this concept
         * @param referenceId reference id used for editorial note URL
         */
        EDMConcept(Long id, String name, String type, String language, String geography, String period, Set<String> references, Long referenceId) {
            // set about URL
            if (id != null)
                this.about = String.format("%scommonNames/%d", System.getProperty("jacq.serviceNamesUrl"), id);

            this.prefLabel = name;
            // set type note to "common name" or "scientific name"
            // this might be useful in the future when searching for scientific names is supported
            this.typeNote = "/name/common".equals(type) ? "common name" :
                    "/name/scientific".equals(type) ? "scientific name" : type;
            // set language if available
            if (language != null) this.languageNote = "Language: " + language;
            // set geography if available
            if (geography != null) this.geographyNote = "Geography: " + geography;
            // set period if available
            if (period != null) this.periodNote = "Period: " + period;
            // set references when given
            if (references != null && !references.isEmpty())
                this.referenceNote = "Reference(s): " + String.join(", ", references);
            // set editorial note
            if (referenceId != null)
                this.editorialNote = String.format("%scommonNames/references/scientificName/%s",
                        System.getProperty("jacq.serviceNamesUrl"), referenceId);
        }
    }
}
