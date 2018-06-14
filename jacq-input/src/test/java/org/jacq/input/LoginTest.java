/*
 * Copyright 2018 wkoller.
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
package org.jacq.input;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author wkoller
 */
public class LoginTest extends BaseTest {

    @Test
    public void login() {
        doLogin();

        WebElement derivativeTable = driver.findElement(By.id("jacq_form:derivativeTable_data"));
        Assert.assertNotNull(derivativeTable);
        List<WebElement> derivativeRows = derivativeTable.findElements(By.tagName("tr"));
        Assert.assertTrue(derivativeRows.size() > 0);
    }
}
