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

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Element helper
 */
public class ElementHelper {
  private static final Logger logger = LoggerFactory.getLogger(ElementHelper.class);
  private static final JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
  private static final Actions actions = new Actions(SeleniumDriver.getDriver());

  public static void scrollToElement(WebElement element) {
    logger.info("Scrolling to the element: " + element);
    js.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public static void clickOnElement(WebElement element) {
    scrollToElement(element);
    WaitHelper.waitForElementToBeClickable(element);
    logger.info("Click on the element: " + element);
    element.click();
  }

  public static void clickOnElement(WebElement element, long timeoutInSeconds) {
    scrollToElement(element);
    WaitHelper.waitForElementToBeClickable(element, timeoutInSeconds);
    logger.info("Click on the element: " + element);
    element.click();
  }

  public static void clickOnElementUsingJsExecutor(WebElement element) {
    logger.info("Click on the element using Javascript Executor: " + element);
    js.executeScript("arguments[0].click();", element);
  }

  public static void sendKeys(WebElement element, String keys) {
    scrollToElement(element);
    WaitHelper.waitForElementToBeDisplayed(element);
    logger.info("Send keys to element: " + element);
    element.sendKeys(keys);
  }

  public static void hoverOverElement(WebElement element) {
    scrollToElement(element);
    WaitHelper.waitForElementToBeDisplayed(element);
    logger.info("Hovering over the element: " + element);
    actions.moveToElement(element).build().perform();
  }

  public static void clearElementValue(WebElement element) {
    scrollToElement(element);
    WaitHelper.waitForElementToBeDisplayed(element);
    logger.info("Clear WebElement: " + element);
    element.click();
    element.clear();
    while (!element.getAttribute("value").equals("")) {
      element.sendKeys(Keys.BACK_SPACE);
    }
  }

  /**
   * Replacing the value of the text box when clear is not working
   * https://github.com/SeleniumHQ/selenium/issues/6741
   */
  public static void replaceElementValue(WebElement element, String value) {
    replaceElementValue(element, value, 1024);
  }

  public static void replaceElementValue(WebElement element, String value, int limit) {
    WaitHelper.waitForElementToBeDisplayed(element);
    boolean flag = false;
    for (int i = 0; i < limit; i++) {
      if (element.getAttribute("value").equals(StringUtils.EMPTY)) {
        flag = true;
        break;
      }
      element.sendKeys(Keys.BACK_SPACE);
    }
    if (!flag) {
      Assert.fail("Element's current value is not cleared - " + element + " - "
        + "Current value :" + element.getAttribute("value"));
    }
    element.sendKeys(value);
  }

  public static void dragAndDrop(WebElement source, WebElement target) {
    WaitHelper.waitForElementToBeDisplayed(source);
    WaitHelper.waitForElementToBeDisplayed(target);
    logger.info("Drag and drop between source: " + source + " and target: " + target);
    actions.dragAndDrop(source, target).build().perform();
  }

  public static boolean isElementDisplayed(WebElement element) {
    logger.info("Check if element: " + element + " is displayed.");
    try {
      boolean isPresent = element.isDisplayed();
      return isPresent;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
