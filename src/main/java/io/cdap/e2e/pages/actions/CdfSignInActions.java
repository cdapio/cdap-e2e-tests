/*
 * Copyright © 2023 Cask Data, Inc.
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

import io.cdap.e2e.pages.locators.CdfSignInLocator;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;

import java.io.IOException;



/**
 * Represents CdfSignInActions
 */
public class CdfSignInActions {
    private static CdfSignInLocator cdfSignInLocator;
    static {
        cdfSignInLocator = SeleniumHelper.getPropertiesLocators(CdfSignInLocator.class);
    }

    public static void login() throws IOException, InterruptedException {
        ElementHelper.sendKeys(cdfSignInLocator.cdfUsername, SeleniumHelper.readParameters(ConstantsUtil.CDFUSERNAME));
        ElementHelper.clickOnElement(cdfSignInLocator.nextButton);
        ElementHelper.sendKeys(cdfSignInLocator.cdfPassword, SeleniumHelper.readParameters(ConstantsUtil.CDFPASSWORD));
        ElementHelper.clickOnElement(cdfSignInLocator.nextButton);

        ElementHelper.clickIfDisplayed(cdfSignInLocator.selectTestAccount(ConstantsUtil.CDF_TEST_ACCOUNT_NAME),
          ConstantsUtil.SMALL_TIMEOUT_SECONDS, cdfSignInLocator.clickOnContinueButton());

        ElementHelper.clickIfDisplayed(cdfSignInLocator.clickOnContinueButton(), ConstantsUtil.SMALL_TIMEOUT_SECONDS,
          cdfSignInLocator.locatePluginNameInList(ConstantsUtil.FIRST_PLUGIN_IN_LIST, "Source"));

        ElementHelper.clickIfDisplayed(cdfSignInLocator.clickOnAllowButton(), ConstantsUtil.SMALL_TIMEOUT_SECONDS,
          cdfSignInLocator.locatePluginNameInList(ConstantsUtil.FIRST_PLUGIN_IN_LIST, "Source"));

    }

    public static boolean isUserLoggedInCDF() {
        return !WaitHelper.waitForElementToBeOptionallyDisplayed(
                CdfSignInLocator.locatorOfEmailTextBox(), ConstantsUtil.SMALL_TIMEOUT_SECONDS);
    }
}
