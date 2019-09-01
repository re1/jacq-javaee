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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.CommonNamesSource;
import org.jacq.service.names.sources.util.SourcesUtil;

/**
 * Queries http://ylist.info for common names by simulating form submits
 *
 * @author wkoller
 */
@ManagedBean
public class YListSource implements CommonNamesSource {

    private static final Logger LOGGER = Logger.getLogger(YListSource.class.getName());

    /**
     * ISO 639-3 language codes for common names
     */
    protected static final String LANGUAGE_CODE_JAP = "jap";
    protected static final String LANGUAGE_CODE_ZHO = "zho";
    protected static final String LANGUAGE_CODE_KOR = "kor";

    // Patterns for various matching parts
    protected Pattern resultLinkPattern;
    protected Pattern resultNamePattern;
    protected Pattern chineseNamePattern;
    protected Pattern koreanNamePattern;

    /**
     * Initialize matching pattern for later usage
     */
    @PostConstruct
    public void init() {
        resultLinkPattern = Pattern.compile("<a  href = 'ylist_detail_display.php\\?pass=([^']+)'><span class = 'gakumei'>([^<]+)</span>");
        resultNamePattern = Pattern.compile("<u>和名</u>：　 ([^<]+)<br>.*<u>掲載図鑑とページ番号</u>：　([^<]+)<br>");
        chineseNamePattern = Pattern.compile("<u>中国名</u>：　([^<]+)");
        koreanNamePattern = Pattern.compile("<u>韓国名</u>：　([^<]+)");
    }

    /**
     * Query http://ylist.info by simulating form submits and parsing the result
     *
     * @param query parsed scientific name
     * @return list of common names for given query
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        // create proxy service instance
        YListWebSearch yListWebSearch = SourcesUtil.getYListWebSearch();

        // send initial query to search form
        Response response = yListWebSearch.simpleSearch(query.getScientificName(), 0, 2, 1, 0, 0, 0);
        String content = response.readEntity(String.class);

        // parse results
        Matcher resultLinkMatcher = resultLinkPattern.matcher(content);
        while (resultLinkMatcher.find()) {
            // extract event target and argument from matched groups
            String pass = resultLinkMatcher.group(1);
            String scientificName = resultLinkMatcher.group(2);

            // debug info
            LOGGER.log(Level.FINEST, "{0} = {1}", new Object[]{pass, scientificName});

            // query the source again for the actual common names
            response = yListWebSearch.detailDisplay(pass);

            // read returned result and parse it
            String detailContent = response.readEntity(String.class);
            Matcher resultNameMatcher = resultNamePattern.matcher(detailContent);
            while (resultNameMatcher.find()) {
                // extract actual common name and geography from results
                String commonName = resultNameMatcher.group(1);
                String reference = resultNameMatcher.group(2);

                // put together the result information into a common name object
                CommonName result = new CommonName();
                result.setName(commonName);
                result.setTaxon(scientificName);
                result.setLanguage(LANGUAGE_CODE_JAP);
                result.getReferences().add(reference);

                // finally add the result to the list of results
                results.add(result);

                // check if an additional chinese name is present
                Matcher chineseNameMatcher = chineseNamePattern.matcher(detailContent);
                if (chineseNameMatcher.find()) {
                    commonName = chineseNameMatcher.group(1);

                    // put together the result information into a common name object
                    result = new CommonName();
                    result.setName(commonName);
                    result.setTaxon(scientificName);
                    result.setLanguage(LANGUAGE_CODE_ZHO);
                    result.getReferences().add(reference);

                    // finally add the result to the list of results
                    results.add(result);
                }

                // check if an additional korean name is present
                Matcher koreanNameMatcher = koreanNamePattern.matcher(detailContent);
                if (koreanNameMatcher.find()) {
                    commonName = koreanNameMatcher.group(1);

                    // put together the result information into a common name object
                    result = new CommonName();
                    result.setName(commonName);
                    result.setTaxon(scientificName);
                    result.setLanguage(LANGUAGE_CODE_KOR);
                    result.getReferences().add(reference);

                    // finally add the result to the list of results
                    results.add(result);
                }

                LOGGER.log(Level.FINEST, "{0}", new Object[]{commonName});
            }
        }

        return results;
    }

    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
