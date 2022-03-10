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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wait helper
 */
public class WaitHelper {
  private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);

  public static void waitForPageToLoad(long pageLoadTimeoutInSeconds) {
    ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
      @Override
      public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
        return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
      }
    };

    logger.info("Waiting for the page to load with timeout: " + pageLoadTimeoutInSeconds);
    SeleniumDriver.getWaitDriver(pageLoadTimeoutInSeconds).until(pageLoadCondition);
  }

  public static void waitForPageToLoad() {
    logger.info("Waiting for the page to load " +
      "with the default page load timeout: " + ConstantsUtil.PAGE_LOAD_TIMEOUT_SECONDS);
    waitForPageToLoad(ConstantsUtil.PAGE_LOAD_TIMEOUT_SECONDS);
  }

  public static WebElement waitForElementToBePresent(By locator) {
    logger.info("Waiting for the element: " + locator + " to be present " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public static WebElement waitForElementToBeDisplayed(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be displayed " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.visibilityOf(element));
  }

  public static WebElement waitForElementToBeDisplayed(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be displayed " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    return SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
  }

  public static WebElement waitForElementToBeClickable(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be clickable " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
  }

  public static WebElement waitForElementToBeClickable(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be clickable " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    return SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
  }

  public static boolean waitForElementToBeHidden(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be hidden " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.invisibilityOf(element));
  }

  public static boolean waitForElementToBeHidden(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be hidden " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    return SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Wait for element to be enabled
   *
   * @param element WebElement to wait for
   */
  public static void waitForElementToBeEnabled(WebElement element) {
    waitForElementToBeEnabled(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * @param element          WebElement to wait for
   * @param timeoutInSeconds timeout
   */
  public static void waitForElementToBeEnabled(WebElement element, long timeoutInSeconds) {
    ExpectedCondition<Boolean> elementToBeEnabled = new ExpectedCondition<Boolean>() {
      @Override
      public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
        return element.isEnabled();
      }

      public String toString() {
        return "element to be enabled: " + element;
      }
    };

    logger.info("Waiting for the element: " + element + " to be enabled " +
                  "with the timeout: " + timeoutInSeconds + " seconds");
    SeleniumDriver.getWaitDriver(timeoutInSeconds).until(elementToBeEnabled);
  }
}
