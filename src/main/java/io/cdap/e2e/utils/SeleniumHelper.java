/*
 * Copyright © 2021 Cask Data, Inc.
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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents SeleniumHelper
 */
public class SeleniumHelper {

  private static final Logger logger = LoggerFactory.getLogger(SeleniumHelper.class);
  static String path;
  private static final Properties connectProperties = new Properties();

  static {
    try {
      connectProperties.load(SeleniumHelper.class.getResourceAsStream("/connectionParameters.properties"));
      path = SeleniumHelper.readParameters("DownloadPath");
    } catch (IOException e) {
      logger.error("Failure while loading properties files: " + e);
    }
  }

  public static void folderName(String name) {
    File f1 = new File("src/test/resources/" + name);
    //Creating a folder using mkdir() method
    boolean bool = f1.mkdir();
    if (bool) {
      logger.info("Folder is created successfully");
    } else {
      logger.info("Error Found!");
    }
  }

  public static void filemove(String name, String input) throws IOException, InterruptedException {
    String aa = path + "/" + name + "/" + input;
    Thread.sleep(2000);
    File file = new File(path + "schema.json");
    if (file.renameTo
      (new File(path + "/" + name + "/" + input))) {
      // if file copied successfully then delete the original file
      file.delete();
      logger.info("File moved successfully");
    } else {
      logger.info("Failed to move the file");
    }
  }

  public static String readParameters(String property) throws IOException {
    return connectProperties.getProperty(property);
  }

  public static <T> T getPropertiesLocators(Class<T> className) {
    return PageFactory.initElements(SeleniumDriver.getDriver(), className);
  }

  /**
   * @deprecated Use {@link ElementHelper#isElementDisplayed(WebElement)}
   */
  @Deprecated
  public static boolean isElementPresent(WebElement webElement) {
    return ElementHelper.isElementDisplayed(webElement);
  }

  /**
   * @deprecated Use {@link ElementHelper#dragAndDrop(WebElement, WebElement)}
   */
  @Deprecated
  public static void dragAndDrop(WebElement from, WebElement to) {
    ElementHelper.dragAndDrop(from, to);
  }

  /**
   * Performs click-and-hold at the location of the element, moves by a given offset, then releases the mouse.
   * @deprecated Use {@link ElementHelper#dragAndDropByOffset(WebElement, int, int)}
   */
  @Deprecated
  public static void dragAndDropByOffset(WebElement element, int xOffset, int yOffset) {
    ElementHelper.dragAndDropByOffset(element, xOffset, yOffset);
  }

  /**
   * @deprecated Use {@link ElementHelper#clickOnElement(WebElement)}
   */
  @Deprecated
  public static void clickObject(WebElement element) {
    ElementHelper.clickOnElement(element);
  }

  /**
   * @deprecated Use {@link ElementHelper#sendKeys(WebElement, String)}
   */
  @Deprecated
  public static void sendKeys(WebElement element, String keys) {
    ElementHelper.sendKeys(element, keys);
  }

  /**
   * @deprecated Use {@link WaitHelper#waitForElementToBeDisplayed(WebElement, long)}
   */
  @Deprecated
  public static void waitElementIsVisible(WebElement element, long timeoutInSec) {
    WaitHelper.waitForElementToBeDisplayed(element, timeoutInSec);
  }

  /**
   * @deprecated Use {@link WaitHelper#waitForElementToBeDisplayed(WebElement)}
   */
  @Deprecated
  public static void waitElementIsVisible(WebElement element) {
    WaitHelper.waitForElementToBeDisplayed(element);
  }

  /**
   * @deprecated Use {@link ElementHelper#clickOnElement(WebElement, long)}
   */
  @Deprecated
  public static void waitAndClick(WebElement element, long timeOutInSec) {
    ElementHelper.clickOnElement(element, timeOutInSec);
  }

  /**
   * @deprecated Use {@link ElementHelper#clickOnElement(WebElement)}
   */
  @Deprecated
  public static void waitAndClick(WebElement element) {
    ElementHelper.clickOnElement(element);
  }

  /**
   * Replacing the value of the text box when clear is not working
   * https://github.com/SeleniumHQ/selenium/issues/6741
   *
   * @deprecated Use {@link ElementHelper#replaceElementValue(WebElement, String)}
   */
  @Deprecated
  public static void replaceElementValue(WebElement element, String value) {
    ElementHelper.replaceElementValue(element, value);
  }

  /**
   * @deprecated Use {@link ElementHelper#replaceElementValue(WebElement, String, int)}
   */
  @Deprecated
  public static void replaceElementValue(WebElement element, String value, int limit) {
    ElementHelper.replaceElementValue(element, value, limit);
  }

  /**
   * @deprecated Use {@link ElementHelper#isElementDisplayed(WebElement)}
   */
  @Deprecated
  public static boolean verifyElementPresent(String locator) {
    WebElement element = SeleniumDriver.getDriver().findElement(By.xpath(locator));
    return ElementHelper.isElementDisplayed(element);
  }
}
