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

import io.cdap.e2e.pages.locators.CdfLogLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents CdfLogActions
 */
public class CdfLogActions {
    public static CdfLogLocators cdfLogLocators = null;

    static {

        cdfLogLocators = PageFactory.initElements(SeleniumDriver.getDriver(), CdfLogLocators.class);

    }

    public static void validateErrorPopupLog() throws InterruptedException {
        Thread.sleep(4000);
        cdfLogLocators.errorMessagePopup.click();
    }

    public static void validateErrorPopupLog(String error) throws InterruptedException {
        Thread.sleep(4000);

        cdfLogLocators.errorMessagePopup.click();
    }


    public static void dismissPopup() {
        cdfLogLocators.dismissable.click();
    }

    public static void goToLogs() {
        cdfLogLocators.goToLogs.click();
    }

    public static void validateFailed() {
        cdfLogLocators.validateFailed.isDisplayed();
    }

    public static void validateCategoryError() {
        cdfLogLocators.validateCategoryError.isDisplayed();
    }

    public static void validateLogError() {
        cdfLogLocators.validateCategoryError.isDisplayed();
    }

    public static void goToAdvanceLogs() {
        cdfLogLocators.getGoToAdvanceLogs.click();
    }

    public static void validateSucceeded() {
        cdfLogLocators.validateSucceeded.isDisplayed();
    }

    public static void closeLogs() {
        cdfLogLocators.closeLogs.click();
    }

}
