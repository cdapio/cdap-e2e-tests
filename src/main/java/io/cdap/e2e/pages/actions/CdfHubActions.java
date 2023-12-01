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

  /**
   * Click on Hub option from the top panel.
   */
  public static void clickOnHub() {
    ElementHelper.clickOnElement(CdfHubLocators.hubPage);
  }

  /**
   * Click on differnt options from the hub.
   * @param option Plugins pipelines Drivers etc.
   */
  public static void clickOnOptionsFromHub(String option) {
    ElementHelper.clickOnElement(CdfHubLocators.locateArtifact(option));
  }

  /**
   * Click on the different buttons in hub or studio.
   */
  public static void clickOnButton(String buttonName) {
    ElementHelper.clickOnElement(CdfHubLocators.locateButton(buttonName));
  }

  /**
   * Verify that the pipeline is saved in drafts.
   */
  public static void verifyPipelineStatus(String pipelineName) {
    ElementHelper.isElementDisplayed(CdfHubLocators.locateButton(pipelineName));
  }

  /**
   * Click on Deploy button to deploy the plugin Or create the pipeline.
   */
  public static void clickOnDeploy() {
    ElementHelper.clickOnElement(CdfHubLocators.deployButton);
  }

  /**
   * Click on Finish button to deploy the plugin Or create the pipeline.
   */
  public static void clickOnFinish() {
    ElementHelper.clickOnElement(CdfHubLocators.finishButton);
  }

  /**
   * Click on the different options from hub from the left panel .
   */
  public static void clickOnOptions(String option) {
    ElementHelper.clickOnElement(CdfHubLocators.locateOptions(option));
  }

  /**
   * Click on Close button to close the hub page .
   */
  public static void clickOnCloseButton() {
    ElementHelper.clickOnElement(CdfHubLocators.closeButton);
  }

  /**
   * Click on Search tab in hub to search for artifacts or pipelines.
   */
  public static void clickOnSearchTab(String searchedText) {
    ElementHelper.sendKeys(CdfHubLocators.searchTabHub, searchedText);
  }

  /**
   * To verify the error message from the hub page .
   */
  public static void verifyErrorMessage(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfHubLocators.
                                   locateErrorMessage(expectedErrorMessage), expectedErrorMessage);
  }

  /**
   To verify whether the element is displayed or not .
   */
  public static void verifyElementIsDisplayed() {
    ElementHelper.isElementDisplayed(CdfHubLocators.locateHub);
  }

  /**
   To verify the plugin is deleted from the control center.
   */
  public static void verifyPluginIsDeleted() {
    ElementHelper.isElementDisplayed(CdfHubLocators.deletedElementStatus);
  }

  /**
   * Click on Delete button to delete the plugin from control center.
   */
  public static void clickOnDeleteButton() {
    ElementHelper.clickOnElement(CdfHubLocators.deleteButton);
  }

  /**
   * To delete the plugin in control center.
   */
  public static void deletePluginControlCenter()  {
    ElementHelper.clickOnElement(CdfHubLocators.deleteIconControlCenter);
    clickOnDeleteButton();
  }

  /**
   * To verify whether the plugin is successfully deployed.
   */
  public static void verifyPluginIsDeployed(String pluginName) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(pluginName);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginName;
    }
    ElementHelper.isElementDisplayed(CdfHubLocators.locateArtifact(pluginPropertyDataCyAttribute));
  }

  /**
   * Click on the different options from the hamburger menu .
   * @param option Home List Studio Wrangler Replication Metadata etc.
   */
  public static void clickOnOptionFromHamburgerMenu(String option) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(option);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = option;
    }
    ElementHelper.clickOnElement(CdfHubLocators.locateHamburgerOption(pluginPropertyDataCyAttribute));
  }

  /**
   * Click on Create button to create a pipeline from hub .
   */
  public static void clickOnCreateButton() {
    ElementHelper.clickOnElement(CdfHubLocators.createButton);
  }

  /**
   * To verify whether the plugin is present in studio.
   */
  public static void verifyPluginInStudio(String pluginName) {
    ElementHelper.isElementDisplayed(CdfHubLocators.locateArtifact(pluginName));
  }

  /**
   * To verify whether the plugin is displayed on the Hub page.
   */
  public static void verifyPluginIsDisplayed(String pluginName) {
    ElementHelper.isElementDisplayed(CdfHubLocators.locateArtifact(pluginName));
  }
}
