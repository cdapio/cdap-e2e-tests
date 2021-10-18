/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfStudioLocators;
import io.cdap.e2e.utils.SeleniumDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents CdfBigQueryPropertiesActions
 */
public class CdfBigQueryPropertiesActions {
    public static io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators cdfBigQueryPropertiesLocators = null;
    static int run = 0;
    static {

        cdfBigQueryPropertiesLocators =
          PageFactory.initElements(SeleniumDriver.getDriver(),
                                   io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.class);
    }

    public static void enterBigQueryProperties(String arg0) throws InterruptedException {
        CdfStudioLocators.bigQueryProperties.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryReferenceName.sendKeys("automation_test");
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSetProjectID.sendKeys("cdf-athena");
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryDataSet.sendKeys("test_automation");
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.bigQueryTable.sendKeys(arg0);
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.updateTable.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.truncatableSwitch.click();
        io.cdap.e2e.pages.locators.CdfBigQueryPropertiesLocators.validateBttn.click();
        Thread.sleep(8000);
    }




}
