package org.jacq.common.model.names;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * Wrapper object for Europeana Data Model response format
 * XmlSeeAlso is used for types not known to JAXB
 *
 * @author re1
 */
@XmlRootElement(name = "RDF", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@XmlSeeAlso(CommonName.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class EDMResponse<T> implements NameResponse<T> {

    @XmlElement(name = "Concept", namespace = "http://www.w3.org/2004/02/skos/core#", type = CommonName.class)
    protected List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
