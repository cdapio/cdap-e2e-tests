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

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents CdfLogLocators
 */
public class CdfLogLocators {

    @FindBy(xpath = "//*[contains(text(),'The preview of the pipeline has failed')]")
    public WebElement errorMessagePopup;

    @FindBy(xpath = "//*[@ng-if='dismissable']")
    public WebElement dismissable;

    @FindBy(xpath = "//*[@class='button-label' and contains(text(),'Logs')]")
    public WebElement goToLogs;

    @FindBy(xpath = "//*[contains(text(),'Pipeline') and contains(text(),'failed')]")
    public WebElement validateFailed;

    @FindBy(xpath = "//*[contains(text(),'Pipeline') and contains(text(),'failed')]/parent::*//child::div[2]")
    public WebElement validateCategoryError;


    @FindBy(xpath = "//*[@class=\"MuiButton-label\"  and contains(text(),'View')]")
    public WebElement getGoToAdvanceLogs;

    @FindBy(xpath = "//*[contains(text(),'Pipeline') and contains(text(),'succeeded')]")
    public WebElement validateSucceeded;

//    @FindBy(xpath="//*[@class='icon-svg icon-close']")
//    public WebElement closeLogs;

    @FindBy(xpath = "//*[contains(@class, 'close')]")
    public WebElement closeLogs;
}

