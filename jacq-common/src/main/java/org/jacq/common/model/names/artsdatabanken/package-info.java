@XmlSchema(
        namespace = "http://artsdatabanken.no/webtjenester",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "ns1", namespaceURI = "http://artsdatabanken.no/webtjenester")
        }
)

package org.jacq.common.model.names.artsdatabanken;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;