/*
 * Copyright 2021 Google LLC
 */

package io.cdap.e2e.utils;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static io.cdap.e2e.utils.RemoteClass.createDriverFromSession;

/**
 * Represents CdfHelper
 */
public interface CdfHelper {

    default void openCdf() throws IOException, InterruptedException {
        SeleniumDriver seleniumDriver = new SeleniumDriver();
        RemoteWebDriver driver = createDriverFromSession(seleniumDriver.session(), seleniumDriver.url);
        SeleniumDriver.openPage(SeleniumHelper.readParameters("CDFURL"));
        try {
            SeleniumDriver.getDriver().switchTo().alert().accept();
            SeleniumDriver.waitForPageToLoad();
        } catch (NoAlertPresentException Ex) {
            SeleniumDriver.waitForPageToLoad();
        }

    }
}
