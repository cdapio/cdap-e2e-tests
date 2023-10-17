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
    CdfHubActions.clickOnPlugin(pluginName);
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
  @Then("Select dropdown : {string} with option value: {string} in control center")
  public void selectDropdownFilterOptionValue(String pluginProperty, String option) {
    CdfHubActions.selectFilterDropdownOption(pluginProperty, option);
  }
  @Then("Enter the text in search tab {string} in control center")
  public static void openSearchControlCenter(String value) {
    CdfHubActions.clickOnSearchTabControlCenter(value);
  }
  @Then("Verify that plugin is successfully deleted")
  public static void verifyPluginIsDeleted() {
    CdfHubActions.verifyPluginIsDeleted();
  }
  @Then("Click on {string} button")
  public static void clickOnButton(String buttonName) {
    CdfHubActions.clickOnHomePage(buttonName);
  }
}
