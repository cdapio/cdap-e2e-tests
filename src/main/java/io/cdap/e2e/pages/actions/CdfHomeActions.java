/*
 * Copyright 2021 Google LLC
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
