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

import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents CdfHomeActions
 */
public class CdfHomeActions {
    public static io.cdap.e2e.pages.locators.CdfHomeLocators cdfHomeLocators = null;

    static {

        cdfHomeLocators =
          PageFactory.initElements(SeleniumDriver.getDriver(), io.cdap.e2e.pages.locators.CdfHomeLocators.class);

    }

    public static void clickStudio() {

        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
        WebElement element = io.cdap.e2e.pages.locators.CdfHomeLocators.studio;
        js.executeScript("arguments[0].click();", element);
        System.out.println("FIrst case passed");

    }


}
