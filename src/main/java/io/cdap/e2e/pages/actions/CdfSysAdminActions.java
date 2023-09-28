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

import io.cdap.e2e.pages.locators.CdfSysAdminLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.JsonUtils;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Represents CdfSysAdminActions
 */
public class CdfSysAdminActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfSysAdminActions.class);
  public static CdfSysAdminLocators cdfSysAdminLocators;

  static {
    cdfSysAdminLocators = SeleniumHelper.getPropertiesLocators(CdfSysAdminLocators.class);
  }

  public static void clickSystemAdminMenu() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.systemAdminMenu);
  }

  public static void clickConfigurationMenu() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.configurationMenu);
  }

  public static void selectMacroAPIService(String service) {
    Select drpAPIRequest = new Select(CdfSysAdminLocators.apiCallType);
    drpAPIRequest.selectByVisibleText(service);
  }

  public static void enterURI(String service) {
    CdfSysAdminLocators.apiInputURI.sendKeys(service);
  }

  public static void enterRequestBody(String requestBody) {
    CdfSysAdminLocators.requestBody.sendKeys(requestBody);
  }

  public static void clearRequest() {
    CdfSysAdminLocators.clearButton.click();
  }

  public static void clearAllRequest() {
    CdfSysAdminLocators.clearAll.click();
  }

  public static void sendRequest() {
    CdfSysAdminLocators.sendButton.click();
  }

  public static void verifySuccess() {
    boolean checkParam = false;
    String verify = CdfSysAdminLocators.success200.getText();
    if (verify.contains("200")) {
      checkParam = true;
      logger.info("Success");
    }
    Assert.assertTrue(checkParam);
  }

  /**
   * Click on the specific Menu
   *
   * @param menuName Operations, Reports or System Admin
   */
  public static void clickOnMenu(String menuName) {
    ElementHelper.clickOnElement(CdfSysAdminLocators.locateMenu(menuName));
  }

  /**
   * Select the type of preferenceName to open in system admin
   *
   * @param preferenceName @data-cy attribute value of preference name. If preferenceName is present
   *                       in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy
   *                       is fetched from it else preferenceName is used as it is.
   */
  public static void clickOnSystemAdminTabs(String preferenceName) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        preferenceName);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = preferenceName;
    }
    ElementHelper.clickOnElement(
        CdfSysAdminLocators.clickPreference(pluginPropertyDataCyAttribute));
  }

  /**
   * Click on the Edit System Preferences in the system admin page
   */
  public static void clickOnEditPreference() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.editSystemPreference);
  }

  /**
   * Select the Namespace option in the system admin page
   */
  public static void selectNamespace() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.openNamespaceTab);
  }

  /**
   * Open Compute Profile option in the system admin page
   */
  public static void clickOnComputeProfile() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.openSystemComputeProfile);
  }

  /**
   * Enter KeyValue Pairs For Preference Property
   *
   * @param preferenceProperty @data-cy attribute value of preference Property. If
   *                           preferenceProperty is present in {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE}
   *                           then its data-cy is fetched from it else preferenceProperty is used
   *                           as it is.
   * @param keyValuePair       Actual json KeyValue Pairs string is fetched from {@link
   *                           ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} with keyValuePair as
   *                           key
   */
  public static void enterKeyValuePreferences(String preferenceProperty, String keyValuePair) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        preferenceProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = preferenceProperty;
    }
    Map<String, String> properties =
        JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(keyValuePair));
    int index = 0;
    for (Map.Entry<String, String> entry : properties.entrySet()) {
      if (index != 0) {
        ElementHelper.clickOnElement(CdfSysAdminLocators.locateAddRowButtonProperty(
            pluginPropertyDataCyAttribute, index - 1));
      }
      ElementHelper.sendKeys(CdfSysAdminLocators.locateKeyProperty(
          pluginPropertyDataCyAttribute, index), entry.getKey());
      ElementHelper.sendKeys(CdfSysAdminLocators.locateValueProperty(
          pluginPropertyDataCyAttribute, index), entry.getValue());
      index++;
    }
  }

  /**
   * Click on Save and Close button to save preference
   */
  public static void clickOnSavePreference() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.clickSaveClose);
  }

  /**
   * Click on Reset button to reset preference
   */
  public static void clickOnResetPreference() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.clickReset);
  }

  /**
   * Verify if the added Preferences reset is validated successfully
   */
  public static void verifyIfResetValidatedSuccessfully() {
    WaitHelper.waitForElementToBeDisplayed(CdfSysAdminLocators.resetSuccessMsg);
    String expectedMessage = PluginPropertyUtils.errorProp(
        ConstantsUtil.RESET_VALIDATION_SUCCESS_MESSAGE);
    AssertionHelper.verifyElementContainsText(
        CdfSysAdminLocators.resetSuccessMsg, expectedMessage);
  }

  /**
   * Click on Delete to delete preference
   */
  public static void deletePreference() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.clickDelete);
  }

  /**
   * Click on Reload Artifacts
   */
  public static void clickReloadArtifacts() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.reloadSystemArtifacts);
  }

  /**
   * Click on Reload button to reload artifacts
   */
  public static void clickOnReload() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.clickReload);
    WaitHelper.waitForElementToBeOptionallyDisplayed(
        CdfSysAdminLocators.locatorOfLoadingSpinnerOnReloadButton(),
        ConstantsUtil.SMALL_TIMEOUT_SECONDS);
    WaitHelper.waitForElementToBeHidden(
        CdfSysAdminLocators.locatorOfLoadingSpinnerOnReloadButton(),
        ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Click on Create Compute Profile button
   */
  public static void clickCreateComputeProfile() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.createComputeProfile);
  }

  /**
   * Select on the type of Provisioner from list for Compute Profile in system admin
   *
   * @param provisionerName @data-cy attribute value of Provisioner. If Provisioner is present in
   *                        {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy is
   *                        fetched from it else Provisioner is used as it is.
   */
  public static void selectProvisioner(String provisionerName) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        provisionerName);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = provisionerName;
    }
    ElementHelper.clickOnElement(
        CdfSysAdminLocators.locateProvisionerInList(pluginPropertyDataCyAttribute));
  }

  /**
   * Click on type of button to create Compute Profile in system admin
   *
   * @param buttonType @data-cy attribute value of button. If type of action is present in {@link
   *                   ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy is fetched
   *                   from it else type of action is used as it is.
   */
  public static void clickCreateButtonComputeProfile(String buttonType) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        buttonType);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = buttonType;
    }
    ElementHelper.clickOnElement(
        CdfSysAdminLocators.locateButtonType(pluginPropertyDataCyAttribute));
  }

  /**
   * Click on the Close button in compute profile properties page
   */
  public static void clickCloseButton() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.closeButton);
  }

  /**
   * Verify Error message displayed on the footer/at the bottom of Compute Profile Properties page
   * using the Error message location in the .properties file {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param errorMessageLocation Expected error message location
   */
  public static void verifyErrorMessageOnFooter(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfSysAdminLocators.errorMessageOnFooter,
        expectedErrorMessage);
  }

  /**
   * Verify Error message displayed on the dialog box using the Error message location in the
   * .properties file {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param errorMessageLocation Expected error message location
   */
  public static void verifyFailedErrorMessageOnDialogBox(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfSysAdminLocators.failMessage,
        expectedErrorMessage);
  }

  /**
   * Click on Create Namespace button in system admin page
   */
  public static void clickCreateNamespaceButton() {
    ElementHelper.clickOnElement(CdfSysAdminLocators.createNewNamespace);
  }

  /**
   * Enter NamespaceName value in Add namespace
   *
   * @param value If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a
   *              key then its value is fetched from it else value is entered in the input as it
   *              is.
   */
  public static void enterNamespaceName(String value) {
    ElementHelper.sendKeys(CdfSysAdminLocators.namespaceName,
        PluginPropertyUtils.pluginProp(value));
  }

  /**
   * Enter NamespaceDescription value in Add namespace
   *
   * @param value If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a
   *              key then its value is fetched from it else value is entered in the input as it
   *              is.
   */
  public static void enterNamespaceDescription(String value) {
    ElementHelper.sendKeys(CdfSysAdminLocators.namespaceDescription,
        PluginPropertyUtils.pluginProp(value));
  }

  /**
   * Check whether Create Profile Properties Page is loaded
   */
  public static void verifyCreateProfilePageLoaded() {
    WaitHelper.waitForElementToBeOptionallyDisplayed(CdfSysAdminLocators.profilePropertiesPage(),
        ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Check whether Profile created is present in System Compute Profile tab
   *
   * @param profileTitle any specific title created
   */
  public static void verifyProvisionerPresentComputeProfile(String profileTitle) {
    WaitHelper.waitForElementToBeDisplayed(CdfSysAdminLocators.
        locateProfileTitle(PluginPropertyUtils.pluginProp(profileTitle)));
    AssertionHelper.verifyElementDisplayed(CdfSysAdminLocators.
        locateProfileTitle(PluginPropertyUtils.pluginProp(profileTitle)));
  }
}
