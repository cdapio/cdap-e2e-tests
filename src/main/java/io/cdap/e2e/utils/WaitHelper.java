/*
 * Copyright © 2022 Cask Data, Inc.
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

package io.cdap.e2e.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wait helper
 */
public class WaitHelper {
  private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);
  private static final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) SeleniumDriver.getDriver();

  public static void waitForPageToLoad() {
    while (true) {
      logger.info("Waiting for page to load...");
      boolean result = (boolean) javascriptExecutor.executeScript("return document.readyState == 'complete'");
      if (result) {
        return;
      }
    }
  }

  public static void waitForElementToBePresent(By locator) {
    logger.info("Waiting for the element: " + locator + " to be present " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT + " seconds");
    SeleniumDriver.getWaitDriver().until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public static void waitForElementToBeDisplayed(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be displayed " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT + " seconds");
    SeleniumDriver.getWaitDriver().until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitForElementToBeDisplayed(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be displayed " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitForElementToBeClickable(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be clickable " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT + " seconds");
    SeleniumDriver.getWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitForElementToBeClickable(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be clickable " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitForElementToBeHidden(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be hidden " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT + " seconds");
    SeleniumDriver.getWaitDriver().until(ExpectedConditions.invisibilityOf(element));
  }

  public static void waitForElementToBeHidden(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be hidden " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.invisibilityOf(element));
  }
}
