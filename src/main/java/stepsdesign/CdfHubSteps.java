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
package stepsdesign;

import io.cdap.e2e.pages.actions.CdfHubActions;
import io.cdap.e2e.pages.locators.CdfHubLocators;
import io.cdap.e2e.utils.CdfHelper;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cucumber.java.en.Then;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDF hub related step design.
 */
public class CdfHubSteps implements CdfHelper {
  private static final Logger logger = LoggerFactory.getLogger(CdfHubSteps.class);
  static {
    SeleniumHelper.getPropertiesLocators(CdfHubLocators.class);
  }

  @Then("Click on Hub Menu")
  public static void openHub() {
    CdfHubActions.clickOnHub();
  }

  @Then("Click on {string} plugin")
  public static void openPlugin(String pluginName) {
    CdfHubActions.clickOnOptionsFromHub(pluginName);
  }

  @Then("Click on {string} pipeline")
  public static void openPipeline(String pipelineName) {
    CdfHubActions.clickOnOptionsFromHub(pipelineName);
  }

  @Then("Click on {string} option")
  public static void openOptions(String option) {
    CdfHubActions.clickOnOptions(option);
  }

  @Then("Click on close button")
  public static void closeButton() {
    CdfHubActions.clickOnCloseButton();
  }

  @Then("Deploy the plugin")
  public static void deployPlugin() {
    CdfHubActions.clickOnDeploy();
    CdfHubActions.clickOnFinish();
  }

  @Then("Enter the text in search tab {string}")
  public static void openSearch(String value) {
    CdfHubActions.clickOnSearchTab(value);
  }

  @Then("Verify that user is getting an error message: {string}")
  public void verifyErrorMessageDisplayedOnPluginHeader(String errorMessageLocation) {
    CdfHubActions.verifyErrorMessage(errorMessageLocation);
  }

  @Then("Verify that user is navigated to hub page successfully")
  public void verifyThatHubPageIsSuccessfullyOpened() {
    CdfHubActions.verifyElementIsDisplayed();
  }

  @Then("Verify that {string} plugin is successfully deployed")
  public void verifyThatPluginIsSuccessfullyDeployed(String pluginName) {
    CdfHubActions.verifyPluginIsDeployed(pluginName);
  }

  @Then("Click on delete button to delete the plugin")
  public void deleteControlCenterPlugin() {
    CdfHubActions.deletePluginControlCenter();
  }

  @Then("Verify that plugin is successfully deleted")
  public static void verifyPluginIsDeleted() {
    CdfHubActions.verifyPluginIsDeleted();
  }

  @Then("Click on {string} button")
  public static void clickOnButton(String buttonName) {
    CdfHubActions.clickOnButton(buttonName);
  }

  @Then("Verify that {string} plugin is successfully verified in studio")
  public static void verifyPluginInStudio(String pluginName) {
    CdfHubActions.verifyPluginInStudio(pluginName);
  }

  @Then("Verify that {string} plugin is displayed on the Hub page")
  public static void verifyPluginIsDisplayedInHub(String pluginName) {
    CdfHubActions.verifyPluginIsDisplayed(pluginName);
  }

  @Then("Create the pipeline")
  public static void createPipeline() {
    CdfHubActions.clickOnCreateButton();
    CdfHubActions.clickOnFinish();
  }

  @Then("Click on the {string} from the left panel")
  public static void clickOnTheOptionsFromHamburgerMenu(String option) {
    CdfHubActions.clickOnOptionFromHamburgerMenu(option);
  }

  @Then("Click on the {string} option from the list panel")
  public static void clickOnTheOption(String button) {
    CdfHubActions.clickOnButton(button);
  }

  @Then("Verify that {string} pipeline is saved in drafts")
  public static void verifyPipelineStatus(String pipelineName) {
    CdfHubActions.verifyPipelineStatus(pipelineName);
  }
}
