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

import io.cdap.e2e.pages.locators.CdfHubLocators;
import io.cdap.e2e.pages.locators.CdfPluginPropertiesLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDF hub related actions.
 */
public class CdfHubActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfHubActions.class);
  static {
    SeleniumHelper.getPropertiesLocators(CdfHubLocators.class);
  }

  public static void clickOnHub() {
    ElementHelper.clickOnElement(CdfHubLocators.hubPage);
  }
  public static void clickOnPlugin(String pluginName) {
    ElementHelper.clickOnElement(CdfHubLocators.locatePlugin(pluginName));
  }
  public static void clickOnHomePage(String buttonName) {
    ElementHelper.clickOnElement(CdfHubLocators.locateButton(buttonName));
  }
  public static void clickOnDeploy() {
    ElementHelper.clickOnElement(CdfHubLocators.deployButton);
  }
  public static void clickOnFinish() {
    ElementHelper.clickOnElement(CdfHubLocators.finishButton);
  }
  public static void clickOnOptions(String option) {
    ElementHelper.clickOnElement(CdfHubLocators.locateOptions(option));
  }
  public static void clickOnCloseButton() {
    ElementHelper.clickOnElement(CdfHubLocators.closeButton);
  }
  public static void clickOnSearchTab(String message) {
    ElementHelper.sendKeys(CdfHubLocators.searchTab, message);
  }
  public static void verifyErrorMessage(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfHubLocators.
                                   locateErrorMessage(expectedErrorMessage), expectedErrorMessage);
  }
  public static void verifyElementIsDisplayed() {
    ElementHelper.isElementDisplayed(CdfHubLocators.locateHub);
  }
  public static void verifyPluginIsDeleted() {
    ElementHelper.isElementDisplayed(CdfHubLocators.deletedElementStatus);
  }
  public static void clickOnDeleteButton() {
    ElementHelper.clickOnElement(CdfHubLocators.deleteButton);
  }
  public static void deletePluginControlCenter()  {
    ElementHelper.clickOnElement(CdfHubLocators.deleteIconControlCenter);
    clickOnDeleteButton();
  }
  public static void verifyPluginIsDeployed(String pluginName) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginName);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginName;
    }
    ElementHelper.isElementDisplayed(CdfHubLocators.locatePluginName(pluginPropertyDataCyAttribute));
  }
  public static void selectFilterDropdownOption(String pluginProperty, String option) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    String optionFromPluginPropertiesFile = PluginPropertyUtils.pluginProp(option);
    if (optionFromPluginPropertiesFile == null) {
      ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators
              .locatePropertyElement(pluginPropertyDataCyAttribute),
          CdfHubLocators.locateDropdownListItem(option));
      return;
    }

    ElementHelper.selectDropdownOption(CdfPluginPropertiesLocators.locatePropertyElement(pluginPropertyDataCyAttribute),
        CdfHubLocators.locateDropdownListItem(
            optionFromPluginPropertiesFile));
  }
  public static void clickOnSearchTabControlCenter(String message) {
    ElementHelper.sendKeys(CdfHubLocators.searchTabControlCenter, message);
  }
}
