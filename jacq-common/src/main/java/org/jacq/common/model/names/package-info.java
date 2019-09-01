@XmlSchema(
        namespace = "http://www.w3.org/1999/xhtml",
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://www.w3.org/1999/xhtml"),
                @XmlNs(prefix = "rdf", namespaceURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
                @XmlNs(prefix = "skos", namespaceURI = "http://www.w3.org/2004/02/skos/core#")
        },
        elementFormDefault = XmlNsForm.QUALIFIED
)

package org.jacq.common.model.names;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;