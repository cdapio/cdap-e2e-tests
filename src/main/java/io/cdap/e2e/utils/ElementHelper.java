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

import io.cdap.e2e.pages.actions.CdfPipelineRunAction;
import org.junit.Assert;
import org.openqa.selenium.By;
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

  /**
   * Scrolling to the WebElement
   *
   * @param element WebElement
   */
  public static void scrollToElement(WebElement element) {
    logger.info("Scrolling to the element: " + element);
    actions.moveToElement(element).perform();
  }

  /**
   * Scrolling to get the element in view using the Javascript Executor.
   * It should only be used if necessary, as it does not replicate how a user would interact with the page.
   *
   * @param element
   */
  public static void scrollToElementUsingJsExecutor(WebElement element) {
    logger.info("Scrolling to the element: " + element + " using the JsExecutor");
    js.executeScript(ConstantsUtil.JS_SCROLL_INTO_VIEW, element);
  }

  /**
   * Clicking on an element using the Javascript Executor.
   * It should only be used if necessary, as it does not replicate how a user would interact with the page.
   *
   * @param element
   */
  public static void clickOnElementUsingJsExecutor(WebElement element) {
    logger.info("Click on the element using Javascript Executor: " + element);
    js.executeScript(ConstantsUtil.JS_CLICK, element);
  }

  /**
   * Click on the WebElement after waiting for it be clickable and scrolling to it
   *
   * @param element WebElement
   */
  public static void clickOnElement(WebElement element) {
    WaitHelper.waitForElementToBeClickable(element);
    scrollToElement(element);
    logger.info("Click on the element: " + element);
    element.click();
  }

  /**
   * Click on the WebElement after waiting for it be clickable and scrolling to it
   *
   * @param element          WebElement
   * @param timeoutInSeconds timeout for the element to be clickable
   */
  public static void clickOnElement(WebElement element, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeClickable(element, timeoutInSeconds);
    scrollToElement(element);
    logger.info("Click on the element: " + element);
    element.click();
  }

  /**
   * Click on the WebElement using Actions class
   *
   * @param element WebElement
   */
  public static void clickUsingActions(WebElement element) {
    actions.moveToElement(element).click().perform();
  }

  /**
   * Click on the WebElement if it is displayed
   *
   * @param locator          Locator of the WebElement
   * @param timeoutInSeconds timeout for the element to be displayed
   */
  public static void clickIfDisplayed(By locator, long timeoutInSeconds) {
    if (isElementDisplayed(locator, timeoutInSeconds)) {
      clickOnElement(SeleniumDriver.getDriver().findElement(locator));
    }
  }

  /**
   * Click on the WebElement if it is displayed within a small timeout: {@link ConstantsUtil#SMALL_TIMEOUT_SECONDS}
   *
   * @param locator Locator of the WebElement
   */
  public static void clickIfDisplayed(By locator) {
    clickIfDisplayed(locator, ConstantsUtil.SMALL_TIMEOUT_SECONDS);
  }

  /**
   * Click on the WebElement if it was displayed within a small timeout: {@link ConstantsUtil#SMALL_TIMEOUT_SECONDS}
   *
   * If the next element which we are waiting for to be displayed is not displayed yet that means we were not able to
   * click on the desired element, so we will retry to click on the element we wanted to click on.
   *
   * @param locatorToClick Locator of the WebElement we want to click on.
   * @param timeOutInSeconds small timeout to wait before clicking.
   * @param locatorToWaitFor Locator of the WebElement we want to be displayed next.
   */
  public static void clickIfDisplayed(By locatorToClick, long timeOutInSeconds, By locatorToWaitFor)
    throws InterruptedException {
    clickIfDisplayed(locatorToClick, timeOutInSeconds);

    // If the next webElement is not displayed we will click on the desired element again.
    if (!isElementDisplayed(locatorToWaitFor, timeOutInSeconds)) {
      RetryUtils.retry(ConstantsUtil.SMALL_TIMEOUT_SECONDS, ConstantsUtil.MEDIUM_TIMEOUT_SECONDS, 2,
        () -> {
          logger.info("Retry : Click on " + locatorToClick);
          clickIfDisplayed(locatorToClick);
          return isElementDisplayed(locatorToWaitFor, timeOutInSeconds);
        });
    }
  }

  /**
   * Send keys to a WebElement
   *
   * @param element WebElement
   * @param keys    keys
   */
  public static void sendKeys(WebElement element, String keys) {
    WaitHelper.waitForElementToBeDisplayed(element);
    scrollToElement(element);
    logger.info("Send keys to element: " + element);
    element.sendKeys(keys);
  }

  /**
   * Send keys to a WebElement
   *
   * @param element WebElement
   * @param keys    keys
   */
  public static void sendKeysToTextarea(WebElement element, String keys) {
    WaitHelper.waitForElementToBeEnabled(element);
    scrollToElement(element);
    logger.info("Send keys to element: " + element);
    element.sendKeys(keys);
  }

  /**
   * Hover over a WebElement
   *
   * @param element WebElement
   */
  public static void hoverOverElement(WebElement element) {
    WaitHelper.waitForElementToBeDisplayed(element);
    scrollToElement(element);
    logger.info("Hovering over the element: " + element);
    actions.moveToElement(element).build().perform();
  }

  /**
   * Clear value of a WebElement
   *
   * @param element WebElement
   * @param limit   limit for the clear operation
   */
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

  /**
   * Clear value of a WebElement
   *
   * @param element WebElement
   */
  public static void clearElementValue(WebElement element) {
    clearElementValue(element, 1024);
  }

  /**
   * Execute Select All command based on System OS and clear the text
   *
   * @param element WebElement
   */
  public static void selectAllTextAndClear(WebElement element) {
    String currentOs = System.getProperty("os.name");
    String selectAllCommand = Keys.CONTROL + "a";

    if (currentOs.toLowerCase().contains("mac")) {
      selectAllCommand = Keys.COMMAND + "a";
    }

    logger.info("Select All text and clear for WebElement: " + element);
    WaitHelper.waitForElementToBeEnabled(element);
    element.sendKeys(selectAllCommand);
    element.sendKeys(Keys.BACK_SPACE);
  }

  /**
   * Replacing the value of the text box when clear is not working
   * https://github.com/SeleniumHQ/selenium/issues/6741
   */
  public static void replaceElementValue(WebElement element, String value) {
    replaceElementValue(element, value, 1024);
  }

  /**
   * Replace the value of a WebElement after attempting to clear the value
   *
   * @param element WebElement
   * @param value   value to replace
   * @param limit   limit for the clear operation
   */
  public static void replaceElementValue(WebElement element, String value, int limit) {
    clearElementValue(element, limit);
    element.sendKeys(value);
  }

  /**
   * Perform drag and drop between the Source WebElement and the Target WebElement
   *
   * @param source WebElement
   * @param target WebElement
   */
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

  /**
   * Check if a WebElement is displayed
   *
   * @param element WebElement
   * @return boolean
   */
  public static boolean isElementDisplayed(WebElement element) {
    logger.info("Check if element: " + element + " is displayed.");
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Check if a WebElement gets displayed with a timeout
   *
   * @param locator          Locator of the WebElement
   * @param timeoutInSeconds timeout
   * @return boolean
   */
  public static boolean isElementDisplayed(By locator, long timeoutInSeconds) {
    return WaitHelper.waitForElementToBeOptionallyDisplayed(locator, timeoutInSeconds);
  }

  /**
   * Get WebElement's text
   *
   * @param element WebElement
   * @return
   */
  public static String getElementText(WebElement element) {
    WaitHelper.waitForElementToBeDisplayed(element);
    logger.info("Get text of the WebElement: " + element);
    return element.getText();
  }

  /**
   * Get WebElement's attribute value
   *
   * @param element   WebElement
   * @param attribute Attribute name
   * @return Attribute value
   */
  public static String getElementAttribute(WebElement element, String attribute) {
    logger.info("Get attribute: " + attribute + " of the WebElement: " + element);
    return element.getAttribute(attribute);
  }

  /**
   * Get WebElement's css property value
   *
   * @param element     WebElement
   * @param cssProperty css property
   * @return css property value
   */
  public static String getElementCssProperty(WebElement element, String cssProperty) {
    logger.info("Get css property: " + cssProperty + " of the WebElement: " + element);
    return element.getCssValue(cssProperty);
  }

  /**
   * Get WebElement's css property: "color"
   *
   * @param element WebElement
   * @return Color css property value, Eg. "#a40403"
   */
  public static String getElementColorCssProperty(WebElement element) {
    String color = getElementCssProperty(element, ConstantsUtil.COLOR);
    String[] hexValue = color.replace("rgba(", "").
      replace(")", "").split(",");
    int hexValue1 = Integer.parseInt(hexValue[0]);
    hexValue[1] = hexValue[1].trim();
    int hexValue2 = Integer.parseInt(hexValue[1]);
    hexValue[2] = hexValue[2].trim();
    int hexValue3 = Integer.parseInt(hexValue[2]);
    return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
  }

  /**
   * Click on Dropdown and click on Dropdown option
   *
   * @param dropdownElement       WebElement dropdownSelectWebElement
   * @param dropdownOptionLocator By dropdown option locator
   */
  public static void selectDropdownOption(WebElement dropdownElement, By dropdownOptionLocator) {
    ElementHelper.clickOnElement(dropdownElement);
    ElementHelper.clickOnElement(SeleniumDriver.getDriver().findElement(dropdownOptionLocator));
  }

  /**
   * Select the radioButton option after waiting for it to be enabled and scrolling to it
   *
   * @param element Radio button element to select
   */
  public static void selectRadioButton(WebElement element) {
    selectRadioButton(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Select the radioButton option after waiting for given timeout to it to be enabled and scrolling to it
   *
   * @param element          Radio button element to select
   * @param timeoutInSeconds timeout
   */
  public static void selectRadioButton(WebElement element, long timeoutInSeconds) {
    logger.info("Select radio button: " + element);
    clickOnEnabledElement(element, timeoutInSeconds);
  }

  /**
   * Select the checkbox option after waiting for it to be enabled and scrolling to it
   *
   * @param element checkbox element to select
   */
  public static void selectCheckbox(WebElement element) {
    selectCheckbox(element, ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Select the checkbox option after waiting for given timeout to it to be enabled and scrolling to it
   *
   * @param element          checkbox element to select
   * @param timeoutInSeconds timeout
   */
  public static void selectCheckbox(WebElement element, long timeoutInSeconds) {
    logger.info("Select checkbox: " + element);
    clickOnEnabledElement(element, timeoutInSeconds);
  }

  /**
   * Click on the WebElement after waiting for it to be enabled and scrolling to it
   *
   * @param element
   * @param timeoutInSeconds
   */
  public static void clickOnEnabledElement(WebElement element, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeEnabled(element, timeoutInSeconds);
    scrollToElement(element);
    element.click();
  }

  /**
   * Count the number of elements having the same locator
   *
   * @param locator
   */
  public static int countNumberOfElements(By locator) {
    return SeleniumDriver.getDriver().findElements(locator).size();
  }
}
