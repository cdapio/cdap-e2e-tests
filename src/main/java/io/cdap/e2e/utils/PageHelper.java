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

import org.openqa.selenium.NoAlertPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Page helper
 */
public class PageHelper {
  private static final Logger logger = LoggerFactory.getLogger(PageHelper.class);

  /**
   * Accept alert if present
   */
  public static void acceptAlertIfPresent() {
    try {
      logger.info("Accepting alert if present");
      SeleniumDriver.getDriver().switchTo().alert().accept();
    } catch (NoAlertPresentException exception) {
      logger.info("No alert present");
    }
  }

  /**
   * Dismiss alert if present
   */
  public static void dismissAlertIfPresent() {
    try {
      logger.info("Dismiss alert if present");
      SeleniumDriver.getDriver().switchTo().alert().dismiss();
    } catch (NoAlertPresentException exception) {
      logger.info("No alert present");
    }
  }

  /**
   * Switch to browser window based on the window index
   *
   * @param windowIndex Starts with 0
   */
  public static void switchToWindow(int windowIndex) {
    WaitHelper.waitForNewWindow(windowIndex + 1);
    logger.info("Switching to Window at index (index starts with 0): " + windowIndex);
    ArrayList<String> listOfWindows = new ArrayList<>(SeleniumDriver.getDriver().getWindowHandles());
    SeleniumDriver.getDriver().switchTo().window(listOfWindows.get(windowIndex));
  }

  /**
   * Close the current window
   */
  public static void closeCurrentWindow() {
    logger.info("Close the current window");
    SeleniumDriver.getDriver().close();
  }

  /**
   * Switch back to main window (index: 0)
   */
  public static void switchBackToMainWindow() {
    logger.info("Switching back to the Main window (index: 0)");
    ArrayList<String> listOfWindows = new ArrayList<>(SeleniumDriver.getDriver().getWindowHandles());
    SeleniumDriver.getDriver().switchTo().window(listOfWindows.get(0));
  }

  /**
   * Refresh current page
   */
  public static void refreshCurrentPage() {
    logger.info("Refreshing current page");
    SeleniumDriver.getDriver().navigate().refresh();
  }
}
