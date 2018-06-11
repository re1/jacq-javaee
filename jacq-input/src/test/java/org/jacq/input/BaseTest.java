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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 *
 * @author wkoller
 */
public abstract class BaseTest {

    protected static final String TEST_URL = "https://development.jacq.org/jacq-input/";
    protected static final String TEST_USERNAME = "test";
    protected static final String TEST_PASSWORD = "test";

    protected WebDriver driver = null;
    protected FirefoxOptions options = null;

    @Before
    public void init() {
        options = new FirefoxOptions();
        options.addArguments("--headless");

        //Step 1- Driver Instantiation: Instantiate driver object as FirefoxDriver
        driver = new FirefoxDriver(options);
        //Step 2- Navigation: Open a website
        driver.navigate().to(TEST_URL);
    }

    @After
    public void destroy() {
        //Step 4- Close Driver
        driver.close();
    }

    // login to the system
    protected void doLogin() {
        //Step 3- Assertion: Check its title is correct
        Assert.assertEquals("Title check failed!", "Login", driver.getTitle());

        WebElement username = driver.findElement(By.id("j_idt8:username"));
        Assert.assertNotNull(username);
        username.sendKeys(TEST_USERNAME);

        WebElement password = driver.findElement(By.id("j_idt8:password"));
        Assert.assertNotNull(password);
        password.sendKeys(TEST_PASSWORD);

        WebElement login = driver.findElement(By.id("j_idt8:j_idt13"));
        Assert.assertNotNull(login);
        login.click();
    }
}
