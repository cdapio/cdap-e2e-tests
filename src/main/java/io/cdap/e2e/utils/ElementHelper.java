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
    actions.moveToElement(element).perform();
  }

  /**
   * Scrolling to get the element in view using the Javascript Executor.
   * It should only be used if necessary, as it does not replicate how a user would interact with the page.
   * @param element
   */
  public static void scrollToElementUsingJsExecutor(WebElement element) {
    logger.info("Scrolling to the element: " + element + " using the JsExecutor");
    js.executeScript(ConstantsUtil.JS_SCROLL_INTO_VIEW, element);
  }

  /**
   * Clicking on an element using the Javascript Executor.
   * It should only be used if necessary, as it does not replicate how a user would interact with the page.
   * @param element
   */
  public static void clickOnElementUsingJsExecutor(WebElement element) {
    logger.info("Click on the element using Javascript Executor: " + element);
    js.executeScript(ConstantsUtil.JS_CLICK, element);
  }

  public static void clickOnElement(WebElement element) {
    WaitHelper.waitForElementToBeClickable(element);
    scrollToElement(element);
    logger.info("Click on the element: " + element);
    element.click();
  }

  public static void clickOnElement(WebElement element, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeClickable(element, timeoutInSeconds);
    scrollToElement(element);
    logger.info("Click on the element: " + element);
    element.click();
  }

  public static void clickIfDisplayed(WebElement element, long timeoutInSeconds) {
    if (isElementDisplayed(element, timeoutInSeconds)) {
      clickOnElement(element, timeoutInSeconds);
    }
  }

  public static void clickIfDisplayed(WebElement element) {
    clickIfDisplayed(element, ConstantsUtil.SMALL_TIMEOUT_SECONDS);
  }

  public static void sendKeys(WebElement element, String keys) {
    WaitHelper.waitForElementToBeDisplayed(element);
    scrollToElement(element);
    logger.info("Send keys to element: " + element);
    element.sendKeys(keys);
  }

  public static void hoverOverElement(WebElement element) {
    WaitHelper.waitForElementToBeDisplayed(element);
    scrollToElement(element);
    logger.info("Hovering over the element: " + element);
    actions.moveToElement(element).build().perform();
  }

  public static void clearElementValue(WebElement element, int limit) {
    WaitHelper.waitForElementToBeDisplayed(element);
    scrollToElement(element);
    logger.info("Clearing WebElement: " + element);
    element.click();
    element.clear();

    for (int i = 0; i < limit; i++) {
      if (element.getAttribute("value").equals("")) {
        return;
      }

      element.sendKeys(Keys.BACK_SPACE);
    }

    Assert.fail("Element's current value is not cleared - " + element + " - "
      + "Current value :" + element.getAttribute("value"));
  }

  public static void clearElementValue(WebElement element) {
    clearElementValue(element, 1024);
  }

  /**
   * Replacing the value of the text box when clear is not working
   * https://github.com/SeleniumHQ/selenium/issues/6741
   */
  public static void replaceElementValue(WebElement element, String value) {
    replaceElementValue(element, value, 1024);
  }

  public static void replaceElementValue(WebElement element, String value, int limit) {
    clearElementValue(element, limit);
    element.sendKeys(value);
  }

  public static void dragAndDrop(WebElement source, WebElement target) {
    WaitHelper.waitForElementToBeDisplayed(source);
    WaitHelper.waitForElementToBeDisplayed(target);
    logger.info("Drag and drop the element from source: " + source + " to target: " + target);
    actions.dragAndDrop(source, target).build().perform();
  }

  /**
   * Performs click-and-hold at the location of the element, moves by a given offset, then releases the mouse.
   */
  public static void dragAndDropByOffset(WebElement element, int xOffset, int yOffset) {
    WaitHelper.waitForElementToBeDisplayed(element);
    logger.info("Drag and drop of element: " + element + " with xOffset: " + xOffset + " and yOffset: " + yOffset);
    actions.dragAndDropBy(element, xOffset, yOffset).build().perform();
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

  public static boolean isElementDisplayed(WebElement element, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeDisplayed(element, timeoutInSeconds);
    return isElementDisplayed(element);
  }
}
