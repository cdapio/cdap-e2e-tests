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

  static String path;
  private static final Logger logger = LoggerFactory.getLogger(SeleniumHelper.class);
  private static final long DEFAULT_TIMEOUT = 1;
  protected static Properties connectProperties = new Properties();

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

  public static boolean isElementPresent(WebElement webElement) {
    try {
      boolean isPresent = webElement.isDisplayed();
      return isPresent;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public static void dragAndDrop(WebElement from, WebElement to) {
    Actions act = new Actions(SeleniumDriver.getDriver());
    //Dragged and dropped.
    act.dragAndDrop(from, to).build().perform();
  }

  public static void clickObject(WebElement element) {
    element.click();
  }

  public static void sendKeys(WebElement element, String keys) {
    element.sendKeys(keys);
  }

  public static void waitElementIsVisible(WebElement element, long timeoutInSec) {
    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getDriver(), timeoutInSec);
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitElementIsVisible(WebElement element) {
    waitElementIsVisible(element, DEFAULT_TIMEOUT);
  }

  public static void waitAndClick(WebElement element, long timeOutInSec) {
    waitElementIsVisible(element, timeOutInSec);
    element.click();
  }

  public static void waitAndClick(WebElement element) {
    waitAndClick(element, DEFAULT_TIMEOUT);
  }

  public static String readParameters(String property) throws IOException {
    return connectProperties.getProperty(property);
  }

  /**
   * Replacing the value of the text box when clear is not working
   * https://github.com/SeleniumHQ/selenium/issues/6741
   */
  public static void replaceElementValue(WebElement element, String value) {
    while (!element.getAttribute("value").equals("")) {
      element.sendKeys(Keys.BACK_SPACE);
    }
    element.sendKeys(value);
  }

  public static boolean verifyElementPresent(String locator) {
    try {
      SeleniumDriver.getDriver().findElement(By.xpath(locator));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static <T> T getPropertiesLocators(Class<T> className) {
    return PageFactory.initElements(
      SeleniumDriver.getDriver(), className);
  }
}
