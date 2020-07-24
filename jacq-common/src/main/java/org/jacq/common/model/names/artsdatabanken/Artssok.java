package org.jacq.common.model.names.artsdatabanken;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Artssok", namespace = "http://artsdatabanken.no/webtjenester")
public class Artssok {
    @XmlElement(name = "Search", namespace = "http://artsdatabanken.no/webtjenester")
    private String search;

    /**
     * Empty constructor for JAXB
     */
    public Artssok() {
    }

    public Artssok(String search) {
        this.search = search;
    }
}