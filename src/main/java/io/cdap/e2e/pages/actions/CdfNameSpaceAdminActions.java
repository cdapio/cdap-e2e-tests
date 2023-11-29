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
import io.cdap.e2e.pages.locators.CdfSysAdminLocators;
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
}
