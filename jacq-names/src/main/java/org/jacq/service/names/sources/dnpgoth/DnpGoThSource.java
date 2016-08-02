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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import org.jacq.common.model.names.CommonName;
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

    protected static final String VIEWSTATE = "__VIEWSTATE";
    protected static final String VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    protected static final String EVENTVALIDATION = "__EVENTVALIDATION";

    protected Pattern inputFormPattern;

    protected Pattern resultLinkPattern;

    protected Pattern resultNamePattern;

    @PostConstruct
    public void init() {
        inputFormPattern = Pattern.compile("<input.+ id=\"(.+)\" value=\"(.*)\" \\/>");
        // <td nowrap="nowrap"><a href="javascript:__doPostBack('tvwResult','s0\\54')" id="tvwResultt1"><font face="Tahoma" color="ForestGreen" size="2"><b>Acanthus ebracteatus Vahl</b>
        resultLinkPattern = Pattern.compile("<td nowrap=\"nowrap\"><a href=\"javascript:__doPostBack\\('(.+)','(.+)'\\)\" id=\"(.+)\">.+<b>(.+)</b>");
        // <td><img src="/WebResource.axd?d=EIMMg-9HJMcSshywAUEDljFAW5I4W1k_5qpJxd6qTcYArBgUdHTbwXZcn87x1yEwCheqvekCbMW9bu-oNdFXy-govl4rhcj8FbRtHAKMH9gTObum0&amp;t=635588870575142005" alt="" /></td><td nowrap="nowrap"><a href="javascript:__doPostBack('tvwResult','s0\\54\\169')" id="tvwResultt2"><font face="Tahoma" color="ForestGreen" size="2">แก้มหมอ  (สตูล)</font></a></td>
        resultNamePattern = Pattern.compile("<td><img.+/></td><td nowrap=\"nowrap\"><a href=\"javascript:__doPostBack\\('.+','.+'\\)\" id=\".+\"><font face=\"Tahoma\" color=\"ForestGreen\" size=\"2\">(.+)\\s+\\((.+)\\)</font></a></td>");
    }

    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        DnpGoThWebSearch dnpGoThWebSearch = SourcesUtil.getDnpGoThWebSearch();

        String genus = (query.getGenus() != null) ? query.getGenus() : query.getUninomial();
        String species = (query.getSpecies() != null) ? query.getSpecies() : "";

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

            System.err.println(inputFormMatcher.group(1) + " = " + inputFormMatcher.group(2));
        }

        // parse results
        Matcher resultLinkMatcher = resultLinkPattern.matcher(content);
        while (resultLinkMatcher.find()) {
            // extract event target and argument from matched groups
            String eventTarget = resultLinkMatcher.group(1);
            String eventArgument = resultLinkMatcher.group(2).replace("\\\\", "\\");    // remove escaping resulting from javascript

            System.err.println(resultLinkMatcher.group(1) + " = " + resultLinkMatcher.group(2));

            // query the source again for the actual common names
            response = dnpGoThWebSearch.searchTreeExpand("%Acanthus%", "%", "Species", viewState, viewStateGenerator, eventValidation, eventTarget, eventArgument);

            // read returned result and parse it
            String expandContent = response.readEntity(String.class);
            Matcher resultNameMatcher = resultNamePattern.matcher(expandContent);
            while (resultNameMatcher.find()) {
                // extract actual common name and geography from results
                String commonName = resultNameMatcher.group(1);
                String geography = resultNameMatcher.group(2);

                // clean the common name, since it may contain <b> tags
                commonName = commonName.replaceAll("<b>", "").replaceAll("</b>", "");

                System.err.println(commonName + " (" + geography + ")");
            }
        }

        return null;
    }

}
