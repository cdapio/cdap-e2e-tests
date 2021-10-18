/*
 * Copyright © 2021 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.GoogleSignInLocator;
import io.cdap.e2e.utils.SeleniumDriver;
import io.cdap.e2e.utils.SeleniumHelper;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Represents GoogleSignInActions
 */
public class GoogleSignInActions {
    public static io.cdap.e2e.pages.locators.GoogleSignInLocator googleSignInLocator = null;

    static {

        googleSignInLocator = PageFactory.initElements(SeleniumDriver.getDriver(), GoogleSignInLocator.class);

    }

    public static void login() throws InterruptedException, IOException {
        googleSignInLocator.username.get(0).sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        googleSignInLocator.nextButton.click();
        Thread.sleep(2000);
        googleSignInLocator.passwordField.sendKeys(SeleniumHelper.readParameters("gPassword"));
        Thread.sleep(2000);
        googleSignInLocator.nextButton.click();
        Thread.sleep(6000);
        googleSignInLocator.nextButton.click();
    }

    public static void corpLogin() throws InterruptedException, IOException {
        googleSignInLocator.corpUsername.sendKeys(SeleniumHelper.readParameters("gLoginUsername"));
        googleSignInLocator.corpPassword.sendKeys(SeleniumHelper.readParameters("gPassword"));
        googleSignInLocator.corpSignnButton.click();
        Thread.sleep(3000);
    }
}
