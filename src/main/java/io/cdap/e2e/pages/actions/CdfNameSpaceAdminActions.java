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

import io.cdap.e2e.pages.locators.CdfNameSpaceAdminLocators;
import io.cdap.e2e.utils.AssertionHelper;
import io.cdap.e2e.utils.ConstantsUtil;
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.JsonUtils;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;
import io.cdap.e2e.utils.WaitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Represents CdfNameSpaceAdminActions
 */
public class CdfNameSpaceAdminActions {

  private static final Logger logger = LoggerFactory.getLogger(CdfNameSpaceAdminActions.class);
  public static CdfNameSpaceAdminLocators cdfNameSpaceAdminLocators;

  static {
    cdfNameSpaceAdminLocators = SeleniumHelper.getPropertiesLocators(
        CdfNameSpaceAdminLocators.class);
  }

  public static void clickOnNameSpaceAdminTabs(String tabName, String nameSpaceName) {
    ElementHelper.clickOnElement(
        CdfNameSpaceAdminLocators.nameSpaceModules(tabName, nameSpaceName));
  }

  public static void openNamespaceAdminPage() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.namespaceAdminMenu);
  }

  public static void clickOnHamburgerMenu() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.hamburgerMenu);
  }

  public static void clickOnEditPreference() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.editPreferencesButton);
  }

  public static void switchToNameSpace(String nameSpaceName) {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.switchNameSpace(nameSpaceName));
  }

  public static void createProfileForNamespace(String nameSpaceName) {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.createProfile(nameSpaceName));
  }

  public static void openNamespaceDropdown() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.namespaceDropdown);
  }

  public static void addNamespaceFromHamburgerMenu() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.addNamespace);
  }

  /**
   * Click on Save and Close button to save preference
   */
  public static void clickOnSavePreference() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.clickSaveClose);
  }

  public static void selectHamburgerMenuList(String listName) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        listName);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = listName;
    }
    ElementHelper.clickOnElement(
        CdfNameSpaceAdminLocators.locateMenuLink(pluginPropertyDataCyAttribute));
  }

  /**
   * Enter KeyValue Pairs For Preference Property
   *
   * @param preferenceProperty @data-cy attribute value of preference Property. If
   *                           preferenceProperty is present in
   *                           {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy
   *                           is fetched from it else preferenceProperty is used as it is.
   * @param keyValuePair       Actual json KeyValue Pairs string is fetched from
   *                           {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} with
   *                           keyValuePair as key
   */
  public static void enterKeyValuePreferences(String preferenceProperty, String keyValuePair) {
    String dataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        preferenceProperty);
    if (dataCyAttribute == null) {
      dataCyAttribute = preferenceProperty;
    }
    Map<String, String> properties =
        JsonUtils.convertKeyValueJsonArrayToMap(PluginPropertyUtils.pluginProp(keyValuePair));
    int index = 0;
    for (Map.Entry<String, String> entry : properties.entrySet()) {
      if (index != 0) {
        ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.locateAddRowButtonProperty(
            dataCyAttribute, index - 1));
      }
      ElementHelper.sendKeys(CdfNameSpaceAdminLocators.locateKeyProperty(
          dataCyAttribute, index), entry.getKey());
      ElementHelper.sendKeys(CdfNameSpaceAdminLocators.locateValueProperty(
          dataCyAttribute, index), entry.getValue());
      index++;
    }
  }

  /**
   * Select on the type of Provisioner from list for Compute Profile in system admin
   *
   * @param provisionerName @data-cy attribute value of Provisioner. If Provisioner is present in
   *                        {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy is
   *                        fetched from it else Provisioner is used as it is.
   */
  public static void selectProvisioner(String provisionerName) {
    String dataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        provisionerName);
    if (dataCyAttribute == null) {
      dataCyAttribute = provisionerName;
    }
    ElementHelper.clickOnElement(
        CdfNameSpaceAdminLocators.locateProvisionerInList(dataCyAttribute));
  }

  /**
   * Click on the Close button in compute profile properties page
   */
  public static void clickCloseButton() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.closeButton);
  }

  /**
   * Click on Delete to delete preference
   */
  public static void deletePreference() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.clickDelete);
  }

  /**
   * Click on Reset button to reset preference
   */
  public static void clickOnResetPreference() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.clickReset);
  }

  /**
   * Verify if the added Preferences reset is successful
   */
  public static void verifyIfResetValidatedSuccessfully() {
    WaitHelper.waitForElementToBeDisplayed(CdfNameSpaceAdminLocators.resetSuccessMsg);
    String expectedMessage = PluginPropertyUtils.errorProp(
        ConstantsUtil.RESET_VALIDATION_SUCCESS_MESSAGE);
    AssertionHelper.verifyElementContainsText(
        CdfNameSpaceAdminLocators.resetSuccessMsg, expectedMessage);
  }

  /**
   * Click on type of button to create Compute Profile in system admin
   *
   * @param buttonType @data-cy attribute value of button. If type of action is present in
   *                   {@link ConstantsUtil#DEFAULT_DATACY_ATTRIBUTES_FILE} then its data-cy is
   *                   fetched from it else type of action is used as it is.
   */
  public static void clickCreateButtonComputeProfile(String buttonType) {
    String dataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        buttonType);
    if (dataCyAttribute == null) {
      dataCyAttribute = buttonType;
    }
    ElementHelper.clickOnElement(
        CdfNameSpaceAdminLocators.locateButtonType(dataCyAttribute));
  }

  /**
   * Verify Error message displayed on the footer/at the bottom of Compute Profile Properties page
   * using the Error message location in the .properties file
   * {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param errorMessageLocation Expected error message location
   */
  public static void verifyErrorMessageOnFooter(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfNameSpaceAdminLocators.errorMessageOnFooter,
        expectedErrorMessage);
  }

  /**
   * Enter NamespaceName value in Add namespace
   *
   * @param value If value is present in {@link ConstantsUtil#DEFAULT_PLUGIN_PROPERTIES_FILE} as a
   *              key then its value is fetched from it else value is entered in the input as it
   *              is.
   */
  public static void enterNamespaceName(String value) {
    ElementHelper.sendKeys(CdfNameSpaceAdminLocators.namespaceName,
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
    ElementHelper.sendKeys(CdfNameSpaceAdminLocators.namespaceDescription,
        PluginPropertyUtils.pluginProp(value));
  }

  /**
   * Verify Error message displayed on the dialog box using the Error message location in the
   * .properties file {@link ConstantsUtil#DEFAULT_ERROR_PROPERTIES_FILE}
   *
   * @param errorMessageLocation Expected error message location
   */
  public static void verifyFailedErrorMessageOnDialogBox(String errorMessageLocation) {
    String expectedErrorMessage = PluginPropertyUtils.errorProp(errorMessageLocation);
    AssertionHelper.verifyElementContainsText(CdfNameSpaceAdminLocators.failMessage,
        expectedErrorMessage);
  }

  public static void switchToNewNameSpace() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.switchToNameSpaceButton);
  }

  public static void verifySwitchToNewNamespace(String value) {
    String expectedNameSpace = PluginPropertyUtils.pluginProp(value);
    AssertionHelper.verifyElementContainsText(CdfNameSpaceAdminLocators.namespaceText,
        expectedNameSpace);
  }

  public static void verifyElementIsDisplayed() {
    ElementHelper.isElementDisplayed(CdfNameSpaceAdminLocators.pageHeaderNameSpaceAdmin);
  }

  /**
   * Check whether Create Profile Properties Page is loaded
   */
  public static void verifyCreateProfilePageLoaded() {
    WaitHelper.waitForElementToBeOptionallyDisplayed(CdfNameSpaceAdminLocators.profilePropertiesPage(),
        ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }

  /**
   * Check whether Profile created is present in System Compute Profile tab
   *
   * @param profileTitle any specific title created
   */
  public static void verifyProvisionerPresentComputeProfile(String profileTitle) {
    WaitHelper.waitForElementToBeOptionallyDisplayed(CdfNameSpaceAdminLocators.
            locateProfileTitle(PluginPropertyUtils.pluginProp(profileTitle)),
        ConstantsUtil.DEFAULT_TIMEOUT_SECONDS);
  }
}
