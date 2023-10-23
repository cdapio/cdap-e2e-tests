/*
 * Copyright © 2023 Cask Data, Inc.
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

import io.cdap.e2e.pages.locators.CdfControlCenterLocators;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepsdesign.PipelineSteps;

/**
 * Represents CdfControlCenterActions
 */

public class CdfControlCenterActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfControlCenterActions.class);
  public static CdfControlCenterLocators cdfNameSpaceAdminLocators;

  static {
    cdfNameSpaceAdminLocators = SeleniumHelper.getPropertiesLocators(
      CdfControlCenterLocators.class);
  }

  public static void clickOnHamburgerMenu() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.hamburgerMenu);
  }

  public static void clickOnControlCenterTab() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.controlCenterMenu);
  }

  public static void navigateToControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.pageHeaderControlCenter);
  }

  public static void clickOnCreateButtonControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.createButtonControlCenter);
  }

  public static void pipelinePresentControlCenterPage() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.dataPipelineControlCenter);
  }

  public static void clickOnDeleteButtonOnConfirmationBox() {
    ElementHelper.clickIfDisplayed(CdfControlCenterLocators.clickOnDeleteButton());
  }

  public static void deletePipelineControlCenter() {
    ElementHelper.clickOnElement(CdfControlCenterLocators.deleteIconControlCenter);
    clickOnDeleteButtonOnConfirmationBox();
  }

  public static void pipelineDeletedIsNotPresent() {
    clickOnSearchTabControlCenter(PipelineSteps.pipelineName);
    ElementHelper.isElementDisplayed(CdfControlCenterLocators.pipelineDeletedMessage);
  }

  public static void clickOnSearchTabControlCenter(String message) {
    ElementHelper.sendKeys(CdfControlCenterLocators.searchTabControlCenter, message);
  }
}
