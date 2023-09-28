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
import io.cdap.e2e.utils.ElementHelper;
import io.cdap.e2e.utils.PluginPropertyUtils;
import io.cdap.e2e.utils.SeleniumHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public static void clickOnNameSpaceAdminTab() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.namespaceAdminMenu);
  }

  public static void clickOnHamburgerMenu() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.hamburgerMenu);
  }

  public static void clickOnEditPreference() {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.editPreferencesButton);
  }

  public static void switchOnNameSpace(String nameSpaceName) {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.switchNameSpace(nameSpaceName));
  }

  public static void createProfileforNameSpace(String nameSpaceName) {
    ElementHelper.clickOnElement(CdfNameSpaceAdminLocators.createProfile(nameSpaceName));
  }

  public static void clickOnTabs(String pluginProperty) {
    String pluginPropertyDataCyAttribute = PluginPropertyUtils.getPluginPropertyDataCyAttribute(
        pluginProperty);
    if (pluginPropertyDataCyAttribute == null) {
      pluginPropertyDataCyAttribute = pluginProperty;
    }

    ElementHelper.clickOnElement(
        CdfSysAdminLocators.clickPreference(pluginPropertyDataCyAttribute));
  }
}
