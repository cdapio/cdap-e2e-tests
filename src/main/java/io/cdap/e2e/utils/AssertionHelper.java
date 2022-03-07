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
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assertion helper
 */
public class AssertionHelper {
  private static final Logger logger = LoggerFactory.getLogger(AssertionHelper.class);

  /**
   * Verify if the WebElement is displayed
   *
   * @param element WebElement
   */
  public static void verifyElementDisplayed(WebElement element) {
    logger.info("Verifying that the element: " + element + " is displayed");
    Assert.assertTrue(element.isDisplayed());
  }

  /**
   * Verify if the WebElement is displayed
   *
   * @param element WebElement
   * @param message Message if the assertion fails
   */
  public static void verifyElementDisplayed(WebElement element, String message) {
    logger.info("Verifying that the element: " + element + " is displayed");
    Assert.assertTrue(message, element.isDisplayed());
  }

  /**
   * Verify if the WebElement contains the expected text
   *
   * @param element      WebElement
   * @param expectedText string
   */
  public static void verifyElementContainsText(WebElement element, String expectedText) {
    WaitHelper.waitForElementToBeDisplayed(element);
    String actualText = ElementHelper.getElementText(element);

    logger.info("Verifying that the element: " + element + " contains text: " + expectedText);
    logger.info("Actual text: " + actualText);
    Assert.assertTrue(actualText.contains(expectedText));
  }

  /**
   * @param element          WebElement
   * @param expectedText     string
   * @param timeoutInSeconds timeout for WebElement to be displayed
   */
  public static void verifyElementContainsText(WebElement element, String expectedText, long timeoutInSeconds) {
    WaitHelper.waitForElementToBeDisplayed(element, timeoutInSeconds);
    String actualText = ElementHelper.getElementText(element);

    logger.info("Verifying that the element: " + element + " contains text: " + expectedText);
    logger.info("Actual text: " + actualText);
    Assert.assertTrue(actualText.contains(expectedText));
  }
}
