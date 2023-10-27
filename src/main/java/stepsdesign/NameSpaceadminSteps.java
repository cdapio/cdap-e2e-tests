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

import io.cdap.e2e.pages.actions.CdfNameSpaceAdminActions;
import io.cdap.e2e.pages.actions.CdfPluginPropertiesActions;
import io.cdap.e2e.utils.CdfHelper;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents CdfNameSpaceAdminSteps
 */
public class NameSpaceadminSteps implements CdfHelper {

  private static final Logger logger = LoggerFactory.getLogger(NameSpaceadminSteps.class);

  @Then("Click on the Hamburger bar on the left panel")
  public static void clickOnTheHamburgerIcon() {
    CdfNameSpaceAdminActions.clickOnHamburgerMenu();
  }

  @Then("Click on NameSpace Admin link from the menu")
  public void openNameSpaceAdminPage() {
    CdfNameSpaceAdminActions.openNamespaceAdminPage();
  }

  @Then("Click on create profile button for {string} Namespace")
  public void createProfileinsideNamespaceAdmin(String nameSpace) {
    CdfNameSpaceAdminActions.createProfileForNamespace(nameSpace);
  }

  @Then("Click {string} tab from Configuration page for {string} Namespace")
  public void openNameSpaceAdminPageTabs(String tabName, String nameSpace) {
    CdfNameSpaceAdminActions.clickOnNameSpaceAdminTabs(tabName, nameSpace);
  }

  @Then("Click on edit namespace preferences to set namespace preferences")
  public void clickOnEditNameSpacePreferences() {
    CdfNameSpaceAdminActions.clickOnEditPreference();
  }

  @Then("Click on Namespace dropdown button")
  public void openNameSpaceDropdown() {
    CdfNameSpaceAdminActions.openNamespaceDropdown();
  }

  @Then("Click on the Add Namespace tab")
  public void addNamespaceFromHamburgerMenu() {
    CdfNameSpaceAdminActions.addNamespaceFromHamburgerMenu();
  }

  @Then("Select navigation item: {string} from the Hamburger menu list")
  public void selectNavigationItemFromMenu(String tabName) {
    CdfNameSpaceAdminActions.selectHamburgerMenuList(tabName);
  }

  @Then("Set namespace preferences with key: {string} and value: {string}")
  public void setSystemPreferencesWithKeyAndValue(String pluginProperty, String keyValuePairs) {
    CdfNameSpaceAdminActions.enterKeyValuePreferences(pluginProperty, keyValuePairs);
  }

  @Then("Click on the Save & Close preferences button")
  public void clickOnSaveAndClosePreferencesButton() {
    CdfNameSpaceAdminActions.clickOnSavePreference();
  }

  @Then("Select a provisioner: {string} for the compute profile")
  public void selectProvisionerForComputeProfile(String provisionerName) {
    CdfNameSpaceAdminActions.selectProvisioner(provisionerName);
  }

  @Then("Click on close button of compute profile properties page")
  public void closeButtonComputeProfile() {
    CdfNameSpaceAdminActions.clickCloseButton();
  }

  @Then("Delete the preferences")
  public void clickOnDeletePreference() {
    CdfNameSpaceAdminActions.deletePreference();
  }

  @Then("Reset the preferences")
  public void clickOnResetPreference() {
    CdfNameSpaceAdminActions.clickOnResetPreference();
  }

  @Then("Verify the reset is successful for added preferences")
  public void verifyIfResetValidatedSuccessfully() {
    CdfNameSpaceAdminActions.verifyIfResetValidatedSuccessfully();
  }

  @Then("Click on: {string} button in the properties")
  public void clickOnButtonInTheProperties(String buttonType) {
    CdfNameSpaceAdminActions.clickCreateButtonComputeProfile(buttonType);
  }

  @Then("Verify that the compute profile is displaying an error message: {string} on the footer")
  public void verifyErrorMessageDisplayedOnFooter(String errorMessageLocation) {
    CdfNameSpaceAdminActions.verifyErrorMessageOnFooter(errorMessageLocation);
  }

  @Then("Enter the New Namespace Name with value: {string}")
  public void enterNamespaceNameValue(String value) {
    CdfNameSpaceAdminActions.enterNamespaceName(value);
  }

  @Then("Enter the Namespace Description with value: {string}")
  public void enterNamespaceDescriptionValue(String value) {
    CdfNameSpaceAdminActions.enterNamespaceDescription(value);
  }

  @Then("Verify the failed error message: {string} displayed on dialog box")
  public void verifyFailedErrorMessageDisplayedOnDialogBox(String errorMessageLocation) {
    CdfNameSpaceAdminActions.verifyFailedErrorMessageOnDialogBox(errorMessageLocation);
  }

  @Then("Switch to the newly created Namespace")
  public void switchToNewNameSpace() {
    CdfNameSpaceAdminActions.switchToNewNameSpace();
  }

  @Then("Verify the namespace is switched to {string} successfully")
  public void verifyNameSpaceSwitch(String newNameSpaceName) {
    CdfNameSpaceAdminActions.verifySwitchToNewNamespace(newNameSpaceName);
  }

  @Then("Add connection type as {string} and provide a {string}")
  public void addConnectionInNameSpaceAdmin(String connectionType, String connectionName) {
    CdfPluginPropertiesActions.clickPluginPropertyElement(connectionType);
    CdfPluginPropertiesActions.enterValueInInputProperty("name", connectionName);
    CdfPluginPropertiesActions.replaceValueInInputProperty("projectId", "projectId");
  }

  @Then("Verify if user successfully navigated to namespace admin page")
  public void verifyThatNamespacePageIsSuccessfullyOpened() {
    CdfNameSpaceAdminActions.verifyElementIsDisplayed();
  }

  @Then("Verify the Create a Profile page is loaded for selected provisioner")
  public void verifyTheCreateAProfilePageIsLoaded() {
    CdfNameSpaceAdminActions.verifyCreateProfilePageLoaded();
  }

  @Then("Verify the created compute profile: {string} is displayed in system compute profile list")
  public void verifyTheCreatedComputeProfile(String profile) {
    CdfNameSpaceAdminActions.verifyProvisionerPresentComputeProfile(profile);
  }
}
