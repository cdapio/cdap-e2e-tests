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

package io.cdap.e2e.pages.actions;

import io.cdap.e2e.pages.locators.CdfLogLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumHelper;

/**
 * Represents Cdf Logs Page Actions
 */
public class CdfLogActions {

  static {
    SeleniumHelper.getPropertiesLocators(CdfLogLocators.class);
  }

  /**
   * Click on the Logs button on the header
   */
  public static void goToLogs() {
    ElementHelper.clickOnElement(CdfLogLocators.goToLogs);
  }

  /**
   * Click on the 'VIEW ADVANCED LOGS' button
   */
  public static void viewAdvancedLogs() {
    ElementHelper.clickOnElement(CdfLogLocators.getGoToAdvanceLogs);
  }

  /**
   * Verify if 'Pipeline ... succeeded' log is shown
   */
  public static void verifyPipelineSucceededLog() {
    AssertionHelper.verifyElementDisplayed(CdfLogLocators.validateSucceeded);
  }

  /**
   * Verify if 'The preview of the pipeline has failed' error message popup is displayed
   */
  public static void verifyPipelinePreviewFailedPopupMessage() {
    AssertionHelper.verifyElementDisplayed(CdfLogLocators.errorMessagePopup);
  }

  /**
   * Click on the close button(x) on the error message
   */
  public static void dismissPopupMessage() {
    ElementHelper.clickOnElement(CdfLogLocators.dismissable);
  }

  /**
   * Verify if 'Pipeline ... failed' log is shown
   */
  public static void verifyPipelineFailedLog() {
    AssertionHelper.verifyElementDisplayed(CdfLogLocators.validateFailed);
  }

  /**
   * Verify if 'Pipeline ... failed' log is shown with 'ERROR' Level
   */
  public static void verifyPipelineFailedLogIsShownWithErrorLevel() {
    AssertionHelper.verifyElementDisplayed(CdfLogLocators.validateCategoryError);
    AssertionHelper.verifyElementContainsText(CdfLogLocators.validateCategoryError, ConstantsUtil.ERROR);
  }

  /**
   * Click on the close button(x) for the Logs page
   */
  public static void closeLogs() {
    ElementHelper.clickOnElement(CdfLogLocators.closeLogs);
  }
}
