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
import org.jacq.common.model.names.artsdatabanken.Artssok;
import org.jacq.service.names.sources.services.ArtsdatabankenNoService;
import org.jacq.service.names.sources.util.SourcesUtil;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Source implementation for the Artsdatabanken Web service
 *
 * @author re1
 * @see ArtsdatabankenNoService#query(String)
 * @see <a href="http://webtjenester.artsdatabanken.no/Artsnavnebase.asmx">Artsnavnebase</a>
 */
@ManagedBean
public class ArtsdatabankenNoSource extends CachedWebServiceSource {

    private static final Logger LOGGER = Logger.getLogger(ArtsdatabankenNoSource.class.getName());
    private static final String serviceUrl = "http://webtjenester.artsdatabanken.no/Artsnavnebase.asmx/Artssok";

    private Unmarshaller jaxbUnmarshaller;

    @PostConstruct
    public void init() {
        setServiceId(4);
        setTimeout(2592000); // 30 days
        // setup JAXBUnmarshaller for Artssok class
        try {
            jaxbUnmarshaller = JAXBContext.newInstance(Artssok.class).createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        // get cached response if possible
        String response = getResponse(query);
        if (response == null || response.isEmpty()) return results;

        try {
            Artssok artssok = (Artssok) jaxbUnmarshaller.unmarshal(new StringReader(response));
        } catch (JAXBException e) {
            e.printStackTrace();
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

    @Override
    public String getWebServiceResponse(NameParserResponse query) {
        // connect to ArtsdatabankenNoService
        ArtsdatabankenNoService service = SourcesUtil.getProxy(ArtsdatabankenNoService.class, serviceUrl);
        // query source for parsed scientific name
        return service.query(query.getScientificName());
    }
}
