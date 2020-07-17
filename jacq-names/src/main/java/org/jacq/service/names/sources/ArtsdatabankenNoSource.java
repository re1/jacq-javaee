/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jacq.service.names.sources;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Source implementation for the Artsdatabanken Web service
 *
 * @author re1
 * @see <a href="http://webtjenester.artsdatabanken.no/Artsnavnebase.asmx">Artsnavnebase</a>
 */
@ManagedBean
public class ArtsdatabankenNoSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(ArtsdatabankenNoSource.class.getName());

    private static final String serviceUrl = "http://webtjenester.artsdatabanken.no/Artsnavnebase.asmx?WSDL";

    private static SOAPConnectionFactory soapConnectionFactory;
    private static SOAPConnection soapConnection;
    private static MessageFactory messageFactory;

    @PostConstruct
    public void init() {
        setServiceId(4);
        setTimeout(2592000); // 30 days

        try {
            // Create SOAP Connection
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();

            messageFactory = MessageFactory.newInstance();
        } catch (SOAPException e) {
            // TODO: Improve error handling
            e.printStackTrace();
        }
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        /*
        String artssokRequestXML = "" +
                "<SOAP-ENV:Envelope" +
                "        xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                "        xmlns:ns1=\"http://artsdatabanken.no/webtjenester\">\n" +
                "    <SOAP-ENV:Body>\n" +
                "        <ns1:Artssok>\n" +
                "            <ns1:Search>$term</ns1:Search>\n" +
                "        </ns1:Artssok>\n" +
                "    </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";

        SOAPMessage soapMessage = messageFactory.createMessage(
                new MimeHeaders(),
                new ByteArrayInputStream(artssokRequestXML.getBytes(StandardCharsets.UTF_8)));
        SOAPMessage loginResponse = soapConnection.call(soapMessage, serviceUrl);
        loginResponse.getSOAPBody().getElementsByTagName("LoginResult");
        */

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see CommonNamesSource#query(String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWebServiceResponse(NameParserResponse query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
