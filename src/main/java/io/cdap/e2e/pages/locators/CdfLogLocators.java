/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CdfLogLocators {

    @FindBy(xpath="//*[contains(text(),'The preview of the pipeline has failed')]")
    public WebElement errorMessagePopup;

    @FindBy(xpath="//*[@ng-if='dismissable']")
    public WebElement dismissable;

    @FindBy(xpath="//*[@class='button-label' and contains(text(),'Logs')]")
    public WebElement goToLogs;

    @FindBy(xpath="//*[contains(text(),'Pipeline') and contains(text(),'failed')]")
    public WebElement validateFailed;

    @FindBy(xpath="//*[contains(text(),'Pipeline') and contains(text(),'failed')]/parent::*//child::div[2]")
    public WebElement validateCategoryError;


    @FindBy(xpath="//*[@class=\"MuiButton-label\"  and contains(text(),'View')]")
    public WebElement getGoToAdvanceLogs;

    @FindBy(xpath="//*[contains(text(),'Pipeline') and contains(text(),'succeeded')]")
    public WebElement validateSucceeded;

//    @FindBy(xpath="//*[@class='icon-svg icon-close']")
//    public WebElement closeLogs;

    @FindBy(xpath="//*[contains(@class, 'close')]")
    public WebElement closeLogs;
}

