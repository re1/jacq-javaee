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
package org.jacq.service.names.sources.ylist;

import org.jacq.service.names.sources.dnpgoth.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Queries ylist.info for common names and returns them
 *
 * @author wkoller
 */
@ManagedBean
public class YListSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(YListSource.class.getName());

    protected static final String VIEWSTATE = "__VIEWSTATE";
    protected static final String VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    protected static final String EVENTVALIDATION = "__EVENTVALIDATION";

    protected static final String REFERENCE = "Thai Plant Names - Tem Smitinand, Copyright 2006-2013 Forest Herbarium, http://www.dnp.go.th/botany/ThaiPlantName/DefaultEng.aspx";
    protected static final String LANGUAGE_CODE_JAP = "jap";

    protected Pattern inputFormPattern;

    protected Pattern resultLinkPattern;

    protected Pattern resultNamePattern;

    @PostConstruct
    public void init() {
        // <span class = 'std_name'><a  href = 'ylist_detail_display.php?pass=11870'><span class = 'gakumei'>Annona cherimola Mill.</span>
        resultLinkPattern = Pattern.compile("<a  href = 'ylist_detail_display.php\\?pass=([^']+)'><span class = 'gakumei'>([^<]+)</span>");
        resultNamePattern = Pattern.compile("<u>和名</u>：　 ([^<]+)<br>");
    }

    /**
     * Query ylist.info by simulating form submits and parsing the result
     *
     * @param query
     * @return
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        YListWebSearch yListWebSearch = SourcesUtil.getYListWebSearch();

        // send initial query to search form
        // ?capital=0&family_order=2&family_disp_type=1&family_header=0&spec_order=0&list_type=0
        Response response = yListWebSearch.simpleSearch(query.getScientificName(), 0, 2, 1, 0, 0, 0);
        String content = response.readEntity(String.class);

        // parse results
        Matcher resultLinkMatcher = resultLinkPattern.matcher(content);
        while (resultLinkMatcher.find()) {
            // extract event target and argument from matched groups
            String pass = resultLinkMatcher.group(1);
            String scientificName = resultLinkMatcher.group(2);

            LOGGER.log(Level.FINEST, "{0} = {1}", new Object[]{pass, scientificName});

            // query the source again for the actual common names
            response = yListWebSearch.detailDisplay(pass);

            // read returned result and parse it
            String detailContent = response.readEntity(String.class);
            Matcher resultNameMatcher = resultNamePattern.matcher(detailContent);
            while (resultNameMatcher.find()) {
                // extract actual common name and geography from results
                String commonName = resultNameMatcher.group(1);

                // put together the result information into a common name object
                CommonName result = new CommonName();
                result.setName(commonName);
                result.setTaxon(scientificName);
                result.setLanguage(LANGUAGE_CODE_JAP);
                result.getReferences().add(REFERENCE);

                // finally add the result to the list of results
                results.add(result);

                LOGGER.log(Level.FINEST, "{0}", new Object[]{commonName});
            }
        }

        return results;
    }

}
