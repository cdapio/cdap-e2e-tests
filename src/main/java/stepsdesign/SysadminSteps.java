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

import io.cdap.e2e.pages.actions.CdfSysAdminActions;
import io.cdap.e2e.utils.CdfHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDF sysadmin related step design.
 */
public class SysadminSteps implements CdfHelper {

  private static final Logger logger = LoggerFactory.getLogger(SysadminSteps.class);

  @When("Open {string} menu")
  public static void openMenu(String menu) {
    CdfSysAdminActions.clickOnMenu(menu);
  }

  @Then("Select the Namespace from the System admin page")
  public static void selectTheNamespace() {
    CdfSysAdminActions.selectNamespace();
  }

  @Then("Click on the Compute Profile from the System admin page")
  public static void clickOnComputeProfileSystemAdminPage() {
    CdfSysAdminActions.clickOnComputeProfile();
  }

  @Then("Click on the Configuration link on the System admin page")
  public static void clickOnConfigurationLinkOnTheSystemAdminPage() {
    CdfSysAdminActions.clickConfigurationMenu();
  }

  @Then("Select {string} option from Configuration page")
  public void openPageFromConfiguration(String pageName) {
    CdfSysAdminActions.clickOnSystemAdminTabs(pageName);
  }

  @Then("Click on edit system preferences")
  public void clickOnEditSystemPreferences() {
    CdfSysAdminActions.clickOnEditPreference();
  }

  @Then("Set system preferences with key: {string} and value: {string}")
  public void setSystemPreferencesWithKeyAndValue(String pluginProperty, String keyValuePairs) {
    CdfSysAdminActions.enterKeyValuePreferences(pluginProperty, keyValuePairs);
  }

  @Then("Click on the Save & Close preferences button")
  public void clickOnSaveAndClosePreferencesButton() {
    CdfSysAdminActions.clickOnSavePreference();
  }

  @Then("Reset the preferences")
  public void clickOnResetPreference() {
    CdfSysAdminActions.clickOnResetPreference();
  }

  @Then("Verify the reset is successful for added preferences")
  public void verifyIfResetValidatedSuccessfully() {
    CdfSysAdminActions.verifyIfResetValidatedSuccessfully();
  }

  @Then("Verify the system admin page is navigated successfully")
  public void verifySystemAdminPageIsOpened() {
    CdfSysAdminActions.verifySystemAdminPageIsDisplayed();
  }

  @Then("Delete the preferences")
  public void clickOnDeletePreference() {
    CdfSysAdminActions.deletePreference();
  }

  @Then("Click on Reload System Artifacts from the System admin page")
  public void clickOnReloadSystemArtifacts() {
    CdfSysAdminActions.clickReloadArtifacts();
  }

  @Then("Click on Reload button on popup to reload the System Artifacts successfully")
  public void clickOnReloadButton() {
    CdfSysAdminActions.clickOnReload();
  }

  @Then("Click on create compute profile button")
  public void createComputeProfile() {
    CdfSysAdminActions.clickCreateComputeProfile();
  }

  @Then("Select a provisioner: {string} for the compute profile")
  public void selectProvisionerForComputeProfile(String provisionerName) {
    CdfSysAdminActions.selectProvisioner(provisionerName);
  }

  @Then("Click on: {string} button in the properties")
  public void clickOnButtonInTheProperties(String buttonType) {
    CdfSysAdminActions.clickCreateButtonComputeProfile(buttonType);
  }

  @Then("Click on close button of compute profile properties page")
  public void closeButtonComputeProfile() {
    CdfSysAdminActions.clickCloseButton();
  }

  @Then("Verify that the compute profile is displaying an error message: {string} on the footer")
  public void verifyErrorMessageDisplayedOnFooter(String errorMessageLocation) {
    CdfSysAdminActions.verifyErrorMessageOnFooter(errorMessageLocation);
  }

  @Then("Verify the failed error message: {string} displayed on dialog box")
  public void verifyFailedErrorMessageDisplayedOnDialogBox(String errorMessageLocation) {
    CdfSysAdminActions.verifyFailedErrorMessageOnDialogBox(errorMessageLocation);
  }

  @Then("Click on Create New Namespace button")
  public void clickOnCreateNewNamespaceButton() {
    CdfSysAdminActions.clickCreateNamespaceButton();
  }

  @Then("Enter the New Namespace Name with value: {string}")
  public void enterNamespaceNameValue(String value) {
    CdfSysAdminActions.enterNamespaceName(value);
  }

  @Then("Enter the Namespace Description with value: {string}")
  public void enterNamespaceDescriptionValue(String value) {
    CdfSysAdminActions.enterNamespaceDescription(value);
  }

  @Then("Verify the Create a Profile page is loaded for selected provisioner")
  public void verifyTheCreateAProfilePageIsLoaded() {
    CdfSysAdminActions.verifyCreateProfilePageLoaded();
  }

  @Then("Verify the created compute profile: {string} is displayed in system compute profile list")
  public void verifyTheCreatedComputeProfile(String profile) {
    CdfSysAdminActions.verifyProvisionerPresentComputeProfile(profile);
  }

  @Then("Verify the namespace created success message displayed on confirmation window")
  public void verifyNamespaceCreatedSuccessMessageDisplayed() {
    CdfSysAdminActions.namespaceCreatedSuccessMessage();
  }

  @Then("Verify the created namespace: {string} is displayed in Namespace tab")
  public void verifyCreatedNamespaceIsDisplayed(String namespace) {
    CdfSysAdminActions.verifyCreatedNamespaceIsDisplayed(namespace);
  }

  @Then("Click on close icon of successful namespace confirmation window")
  public void closeWindowIcon() {
    CdfSysAdminActions.clickOnCloseIconOnNamespaceWindow();
  }

  @Then("Click on Make HTTP calls from the System admin configuration page")
  public void clickOnMakeHttpCalls() {
    CdfSysAdminActions.clickOnMakeHttpCalls();
  }

  @Then("Click on send button")
  public void clickOnSendButton() {
    CdfSysAdminActions.sendRequest();
  }

  @Then("Verify the status code for success response")
  public void verifyStatusCode() {
    CdfSysAdminActions.verifySuccess();
  }

  @Then("Click on edit namespace preferences to set namespace preferences")
  public void clickOnEditNameSpacePreferences() {
    CdfSysAdminActions.clickOnEditNamespacePreference();
  }

  @Then("Click on the Hamburger menu on the left panel")
  public static void clickOnTheHamburgerMenu() {
    CdfSysAdminActions.clickOnHamburgerMenu();
  }

  @Then("Click {string} tab from Configuration page for {string} Namespace")
  public void openNameSpaceAdminPageTabs(String tabName, String nameSpace) {
    CdfSysAdminActions.clickOnNameSpaceAdminTabs(tabName, nameSpace);
  }

  @Then("Select navigation item: {string} from the Hamburger menu list")
  public void selectNavigationItemFromMenu(String tabName) {
    CdfSysAdminActions.selectHamburgerMenuList(tabName);
  }

  @Then("Click on the switch to namespace button")
  public static void clickOnTheSwitchToButton() {
    CdfSysAdminActions.clickOnSwitchToButton();
  }

  @Then("Select request dropdown property with option value: {string}")
  public void selectDropdownPluginPropertyOptionValue(String option) {
    CdfSysAdminActions.selectRequestDropdownOption(option);
  }
}
