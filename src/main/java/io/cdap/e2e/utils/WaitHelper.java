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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

  /**
   * Wait for page to load
   *
   * @param pageLoadTimeoutInSeconds timeout
   */
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

  /**
   * Wait for page to load
   */
  public static void waitForPageToLoad() {
    logger.info("Waiting for the page to load " +
      "with the default page load timeout: " + ConstantsUtil.PAGE_LOAD_TIMEOUT_SECONDS);
    waitForPageToLoad(ConstantsUtil.PAGE_LOAD_TIMEOUT_SECONDS);
  }

  /**
   * Wait for element to be present
   *
   * @param locator locator of the element
   * @return WebElement
   */
  public static WebElement waitForElementToBePresent(By locator) {
    logger.info("Waiting for the element: " + locator + " to be present " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  /**
   * Wait for element to be optionally present
   *
   * @param locator locator of the element
   * @return WebElement
   */
  public static boolean waitForElementToBeOptionallyPresent(By locator, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + locator + " to be optionally present " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    boolean flag = false;

    try {
      SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
      flag = true;
      return flag;
    } catch (Exception e) {
      logger.info("Element: " + locator + " is not present");
      return flag;
    }
  }

  /**
   * Wait for element to be displayed within the specified timeout
   *
   * @param element          WebElement to wait for
   * @param timeoutInSeconds timeout
   * @return WebElement
   */
  public static WebElement waitForElementToBeDisplayed(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be displayed " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    return SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Wait for element to be displayed within the Default timeout: {@link ConstantsUtil#DEFAULT_TIMEOUT_SECONDS}
   *
   * @param element WebElement to wait for
   * @return WebElement
   */
  public static WebElement waitForElementToBeDisplayed(WebElement element) {
    return waitForElementToBeDisplayed(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Wait for element to be displayed without failing, if the element does not get displayed
   *
   * @param locator          Locator of the WebElement
   * @param timeoutInSeconds timeout
   */
  public static boolean waitForElementToBeOptionallyDisplayed(By locator, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + locator.toString() + " to be optionally displayed " +
      "with the timeout: " + timeoutInSeconds + " seconds");

    try {
      SeleniumDriver.getWaitDriver(timeoutInSeconds)
        .until(ExpectedConditions.visibilityOf(SeleniumDriver.getDriver().findElement(locator)));
      return true;
    } catch (NoSuchElementException e) {
      logger.info("Element is not displayed");
      return false;
    } catch (StaleElementReferenceException e) {
      logger.info("Element is not interactable");
      return false;
    }
  }

  /**
   * Wait for element to be clickable within the specified timeout
   *
   * @param element          WebElement to wait for
   * @param timeoutInSeconds timeout
   * @return WebElement
   */
  public static WebElement waitForElementToBeClickable(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be clickable " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    return SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Wait for element to be clickable within the Default timeout: {@link ConstantsUtil#DEFAULT_TIMEOUT_SECONDS}
   *
   * @param element WebElement to wait for
   * @return WebElement
   */
  public static WebElement waitForElementToBeClickable(WebElement element) {
    return waitForElementToBeDisplayed(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Wait for element to be hidden within the specified timeout
   *
   * @param element          WebElement to wait for
   * @param timeoutInSeconds timeout
   */
  public static void waitForElementToBeHidden(WebElement element, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + element + " to be hidden " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    try {
      SeleniumDriver.getWaitDriver(timeoutInSeconds).until(ExpectedConditions.invisibilityOf(element));
    } catch (NoSuchElementException e) {
      logger.info("Element is not displayed");
    }
  }

  /**
   * Wait for element to be hidden within the Default timeout: {@link ConstantsUtil#DEFAULT_TIMEOUT_SECONDS}
   *
   * @param element WebElement to wait for
   */
  public static void waitForElementToBeHidden(WebElement element) {
    waitForElementToBeHidden(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Wait for element to be hidden within the specified timeout
   *
   * @param locator          WebElement to wait for
   * @param timeoutInSeconds timeout
   */
  public static void waitForElementToBeHidden(By locator, long timeoutInSeconds) {
    logger.info("Waiting for the element: " + locator + " to be hidden " +
      "with the timeout: " + timeoutInSeconds + " seconds");
    try {
      SeleniumDriver.getWaitDriver(timeoutInSeconds)
        .until(ExpectedConditions.invisibilityOf(SeleniumDriver.getDriver().findElement(locator)));
    } catch (NoSuchElementException e) {
      logger.info("Element is not displayed");
    }
  }

  /**
   * Wait for element to be selected
   *
   * @param element WebElement to wait for
   * @return WebElement
   */
  public static boolean waitForElementToBeSelected(WebElement element) {
    logger.info("Waiting for the element: " + element + " to be selected " +
      "with the Default timeout: " + ConstantsUtil.DEFAULT_TIMEOUT_SECONDS + " seconds");
    return SeleniumDriver.getWaitDriver().until(ExpectedConditions.elementSelectionStateToBe(element, true));
  }

  /**
   * Waiting for the new window to open
   *
   * @param expectedTotalNumberOfWindows 2 or more
   */
  public static void waitForNewWindow(int expectedTotalNumberOfWindows) {
    logger.info("Waiting for the New Window. Expected windows count: " + expectedTotalNumberOfWindows);
    SeleniumDriver.getWaitDriver().until(ExpectedConditions.numberOfWindowsToBe(expectedTotalNumberOfWindows));
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
