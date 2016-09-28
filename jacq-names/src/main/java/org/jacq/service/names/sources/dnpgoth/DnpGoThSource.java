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
package org.jacq.service.names.sources.dnpgoth;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.model.NameParserResponse;
import org.jacq.service.names.sources.CommonNamesSource;
import org.jacq.service.names.sources.util.SourcesUtil;

/**
 * Queries dnp.go.th for common names and returns them
 *
 * @author wkoller
 */
@ManagedBean
public class DnpGoThSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(DnpGoThSource.class.getName());

    protected static final String VIEWSTATE = "__VIEWSTATE";
    protected static final String VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    protected static final String EVENTVALIDATION = "__EVENTVALIDATION";

    protected static final String REFERENCE = "Thai Plant Names - Tem Smitinand, Copyright 2006-2013 Forest Herbarium, http://www.dnp.go.th/botany/ThaiPlantName/DefaultEng.aspx";
    protected static final String LANGUAGE_CODE = "tha";

    protected Pattern inputFormPattern;

    protected Pattern resultLinkPattern;

    protected Pattern resultNamePattern;

    @PostConstruct
    public void init() {
        inputFormPattern = Pattern.compile("<input.+ id=\"(.+)\" value=\"(.*)\" \\/>");
        resultLinkPattern = Pattern.compile("<td nowrap=\"nowrap\"><a href=\"javascript:__doPostBack\\('(.+)','(.+)'\\)\" id=\"(.+)\">.+<b>(.+)</b>");
        resultNamePattern = Pattern.compile("<td><img.+/></td><td nowrap=\"nowrap\"><a href=\"javascript:__doPostBack\\('.+','.+'\\)\" id=\".+\"><font face=\"Tahoma\" color=\"ForestGreen\" size=\"2\">(.+)\\s+\\((.+)\\)</font></a></td>");
    }

    /**
     * Query http://www.dnp.go.th by simulating form submits and processing the view state
     *
     * @param query
     * @return
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        DnpGoThWebSearch dnpGoThWebSearch = SourcesUtil.getDnpGoThWebSearch();

        // get genus and species from parsed name
        String genus = (query.getGenus() != null) ? query.getGenus() : query.getUninomial();
        String species = query.getSpecies();

        // if no species specified, use an empty string
        if (species == null) {
            species = "";
        }

        LOGGER.log(Level.FINE, "Querying for genus / species: {0} / {1}", new Object[]{genus, species});

        // send initial query to search form
        Response response = dnpGoThWebSearch.searchTree("%" + genus + "%", "%" + species + "%", "Species");
        String content = response.readEntity(String.class);

        // parse input form properties
        Matcher inputFormMatcher = inputFormPattern.matcher(content);
        String viewState = null;
        String viewStateGenerator = null;
        String eventValidation = null;

        // parse the static form parameters and save them to variables
        while (inputFormMatcher.find()) {
            if (null != inputFormMatcher.group(1)) {
                switch (inputFormMatcher.group(1)) {
                    case VIEWSTATE:
                        viewState = inputFormMatcher.group(2);
                        break;
                    case VIEWSTATEGENERATOR:
                        viewStateGenerator = inputFormMatcher.group(2);
                        break;
                    case EVENTVALIDATION:
                        eventValidation = inputFormMatcher.group(2);
                        break;
                    default:
                        break;
                }
            }

            LOGGER.log(Level.FINEST, "{0} = {1}", new Object[]{inputFormMatcher.group(1), inputFormMatcher.group(2)});
        }

        // parse results
        Matcher resultLinkMatcher = resultLinkPattern.matcher(content);
        while (resultLinkMatcher.find()) {
            // extract event target and argument from matched groups
            String eventTarget = resultLinkMatcher.group(1);
            String eventArgument = resultLinkMatcher.group(2).replace("\\\\", "\\");    // remove escaping resulting from javascript
            String scientificName = resultLinkMatcher.group(4);

            LOGGER.log(Level.FINEST, "{0} = {1}", new Object[]{resultLinkMatcher.group(1), resultLinkMatcher.group(2)});

            // query the source again for the actual common names
            response = dnpGoThWebSearch.searchTreeExpand("%" + genus + "%", "%" + species + "%", "Species", viewState, viewStateGenerator, eventValidation, eventTarget, eventArgument);

            // read returned result and parse it
            String expandContent = response.readEntity(String.class);
            Matcher resultNameMatcher = resultNamePattern.matcher(expandContent);
            while (resultNameMatcher.find()) {
                // extract actual common name and geography from results
                String commonName = resultNameMatcher.group(1);
                String geography = resultNameMatcher.group(2);

                // clean the common name, since it may contain <b> tags
                commonName = commonName.replaceAll("<b>", "").replaceAll("</b>", "");

                // put together the result information into a common name object
                CommonName result = new CommonName();
                result.setGeography(geography);
                result.setName(commonName);
                result.setTaxon(scientificName);
                result.setLanguage(LANGUAGE_CODE);
                result.getReferences().add(REFERENCE);

                // finally add the result to the list of results
                results.add(result);

                LOGGER.log(Level.FINEST, "{0} ({1})", new Object[]{commonName, geography});
            }
        }

        return results;
    }

    /**
     * @see CommonNamesSource#query(java.lang.String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
