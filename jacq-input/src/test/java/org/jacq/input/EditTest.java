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
public class EditTest extends BaseTest {

    /**
     * Test basic create capabilities of living plant form
     */
    @Test
    public void createLiving() {
        doLogin();

        // find living plant menu
        WebElement livingPlantmenu = driver.findElement(By.id("jacq_form-menu:livingplant"));
        Assert.assertNotNull(livingPlantmenu);
        livingPlantmenu.click();

        // find create living plant entry
        WebElement createLivingplant = livingPlantmenu.findElement(By.id("jacq_form-menu:livingplant_create"));
        Assert.assertNotNull(createLivingplant);
        createLivingplant.click();

        // find scientific name form
        WebElement scientificNameInput = driver.findElement(By.id("jacq_form:scientificName_input"));
        Assert.assertNotNull(scientificNameInput);
        scientificNameInput.sendKeys("Acer");

        // wait for auto-complete to appear ()
        WebElement scientificNamePanel = driver.findElement(By.id("jacq_form:scientificName_panel"));
        Assert.assertNotNull(scientificNameInput);

        // find list of results & select first scientific name
        List<WebElement> scientificNameRows = scientificNamePanel.findElements(By.tagName("tr"));
        Assert.assertTrue(scientificNameRows.size() > 0);
        scientificNameRows.get(0).click();
    }

    @Test
    public void openLiving() {
        doLogin();

        WebElement derivativeTable = driver.findElement(By.id("jacq_form:derivativeTable_data"));
        Assert.assertNotNull(derivativeTable);
        List<WebElement> derivativeRows = derivativeTable.findElements(By.tagName("tr"));
        Assert.assertTrue(derivativeRows.size() > 0);

        WebElement derivativeRow = derivativeRows.get(0);
        Assert.assertNotNull(derivativeRow);
        List<WebElement> derivativeButtons = derivativeRow.findElements(By.tagName("button"));
        Assert.assertTrue(derivativeButtons.size() == 1);

        WebElement derivativeEditButton = derivativeButtons.get(0);
        Assert.assertNotNull(derivativeEditButton);
        derivativeEditButton.click();

        WebElement livingplantForm = driver.findElement(By.id("jacq_form:livingplant_form"));
        Assert.assertNotNull(livingplantForm);
    }

}
