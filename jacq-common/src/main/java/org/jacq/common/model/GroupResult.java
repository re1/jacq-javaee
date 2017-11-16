/*
 * Copyright 2017 fhafner.
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
package org.jacq.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.FrmwrkGroup;

/**
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupResult {

    protected Long groupId;
    protected String name;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupResult() {
    }

    public GroupResult(FrmwrkGroup group) {
        this.groupId = group.getGroupId();
        this.name = group.getName();
    }

    /**
     * Helper function for converting a list of Group entries
     *
     * @param groupList
     * @return
     */
    public static List<GroupResult> fromList(List<FrmwrkGroup> groupList) {
        List<GroupResult> groupResult = new ArrayList<>();

        if (groupList != null) {
            for (FrmwrkGroup group : groupList) {
                groupResult.add(new GroupResult(group));
            }
        }

        return groupResult;
    }

}
