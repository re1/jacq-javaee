package org.jacq.service.names.sources.artdatabanken;

import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.CommonNamesSource;

import javax.annotation.ManagedBean;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source implementation for the ArtDatabankenSOA (Dyntaxa) Taxon service.
 *
 * @see <a href="https://taxon.artdatabankensoa.se/TaxonService.svc?wsdl">Webservice Endpoint</a>
 * @see <a href="https://doc.artdatabankensoa.se/WebService">Webservice Documentation</a>
 */
@ManagedBean
public class ArtDatabankenSource implements CommonNamesSource {
    private static final Logger LOGGER = Logger.getLogger(ArtDatabankenSource.class.getName());

    private static final String URL = "https://taxon.artdatabankensoa.se/TaxonService.svc";

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnectionFactory.createConnection();

            MessageFactory messageFactory = MessageFactory.newInstance();

            /*
            Before any other method is called a user must login to TaxonService by calling the Login method which on
            successful login returns a security token in property WebLoginResponse.Token. This security token must be
            provided in property WebClientInformation.Token in further calls to the web service.
            */
            SOAPMessage loginMessage = messageFactory.createMessage();
            loginMessage.getSOAPBody().addBodyElement(new QName("Login"));
            // send login message and receive security token
            String token = connection.call(loginMessage, URL).getSOAPBody().getAttribute("WebLoginResponse.Token");

            LOGGER.info(token);

            SOAPMessage message = messageFactory.createMessage();
            message.getSOAPBody()
                    .addBodyElement(new QName("GetTaxaBySearchCriteria"))
                    .addAttribute(new QName("WebClientInformation.Token"), token);

            LOGGER.info(message.getSOAPBody().getNodeValue());

            SOAPMessage response = connection.call(message, URL);
            // hWen the user has finished using the web service a call to the method Logout should be made.
            messageFactory.createMessage().getSOAPBody().addBodyElement(new QName(URL, "Logout"));
            // close SOAP connection
            connection.close();

        } catch (SOAPException e) {
            LOGGER.log(Level.WARNING, "A SOAP error occurred.", e);
        }

        return results;
    }

    /**
     * @see CommonNamesSource#query(String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
