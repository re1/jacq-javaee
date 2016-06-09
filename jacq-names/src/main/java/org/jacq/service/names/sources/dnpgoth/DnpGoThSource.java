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
import org.jacq.service.names.sources.CommonNamesSource;
import org.jacq.service.names.sources.util.SourcesUtil;

/**
 * Queries dnp.go.th for common names and returns them
 *
 * @author wkoller
 */
@ManagedBean
public class DnpGoThSource implements CommonNamesSource {

    protected static final String __VIEWSTATE = "__VIEWSTATE";
    protected static final String __VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    protected static final String __EVENTVALIDATION = "__EVENTVALIDATION";
    protected static final String __SCROLLPOSITIONX = "__SCROLLPOSITIONX";
    protected static final String __SCROLLPOSITIONY = "__SCROLLPOSITIONY";
    protected static final String TVWRESULT_EXPANDSTATE = "tvwResult_ExpandState";

    protected Pattern inputFormPattern;

    protected Pattern resultLinkPattern;

    @PostConstruct
    public void init() {
        inputFormPattern = Pattern.compile("<input.+ id=\"(.+)\" value=\"(.*)\" \\/>");
        // <a href="javascript:__doPostBack('tvwResult','s0\\55')" id="tvwResultt2"><font face="Tahoma" color="ForestGreen" size="2"><b>Acanthus leucostachyus Wall.</b> [S ]</font></a>
        // style="white-space:nowrap;"><a class="tvwResult_0 tvwResult_1" href="javascript:__doPostBack('tvwResult','s0\\5831')" onclick="TreeView_SelectNode(tvwResult_Data, this,'tvwResultt8');" id="tvwResultt8"><b>Rhinacanthus nasutus (L.) Kurz</b>
        resultLinkPattern = Pattern.compile("style=\"white-space:nowrap;\"><a.+href=\"javascript:__doPostBack\\('(.+)','(.+)'\\)\".+ id=\"(.+)\"><b>(.+)</b>");
    }

    @Override
    public ArrayList<CommonName> query(String query) {
        DnpGoThWebSearch dnpGoThWebSearch = SourcesUtil.getDnpGoThWebSearch();

        Response response = dnpGoThWebSearch.searchTree("Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0", "%Acanthus%", "%", "Species");
        String content = response.readEntity(String.class);

        // parse input form properties
        Matcher inputFormMatcher = inputFormPattern.matcher(content);
        String viewState = null;
        String viewStateGenerator = null;
        String eventValidation = null;
        String scrollPositionX = null;
        String scrollPositionY = null;
        String tvwResultExpandState = null;

        // parse the static form parameters and save them to variables
        while (inputFormMatcher.find()) {
            if (__VIEWSTATE.equals(inputFormMatcher.group(1))) {
                viewState = inputFormMatcher.group(2);
            }
            else if (__VIEWSTATEGENERATOR.equals(inputFormMatcher.group(1))) {
                viewStateGenerator = inputFormMatcher.group(2);
            }
            else if (__EVENTVALIDATION.equals(inputFormMatcher.group(1))) {
                eventValidation = inputFormMatcher.group(2);
            }
            else if (__SCROLLPOSITIONX.equals(inputFormMatcher.group(1))) {
                scrollPositionX = inputFormMatcher.group(2);
            }
            else if (__SCROLLPOSITIONY.equals(inputFormMatcher.group(1))) {
                scrollPositionY = inputFormMatcher.group(2);
            }
            else if (TVWRESULT_EXPANDSTATE.equals(inputFormMatcher.group(1))) {
                tvwResultExpandState = inputFormMatcher.group(2);
            }

            System.err.println(inputFormMatcher.group(1) + " = " + inputFormMatcher.group(2));
        }

        // parse results
        Matcher resultLinkMatcher = resultLinkPattern.matcher(content);
        while (resultLinkMatcher.find()) {
            String eventTarget = resultLinkMatcher.group(1);
            String eventArgument = resultLinkMatcher.group(2).replace("\\\\", "\\");    // remove escaping resulting from javascript
            String tvwResultSelectedNode = resultLinkMatcher.group(3);

            response = dnpGoThWebSearch.searchTreeExpand("Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0", "http://www.dnp.go.th/botany/ThaiPlantName/SearchTree.aspx?Genus=%25Acanthus%25&Species=%25&GroupBy=Species", "%Acanthus%", "%", "Species", viewState, viewStateGenerator, eventValidation, eventTarget, eventArgument, scrollPositionX, scrollPositionY, tvwResultExpandState, "", tvwResultSelectedNode);

            String expandContent = response.readEntity(String.class);

            System.err.println(resultLinkMatcher.group(1) + " = " + resultLinkMatcher.group(2));
        }

        return null;
    }

}
