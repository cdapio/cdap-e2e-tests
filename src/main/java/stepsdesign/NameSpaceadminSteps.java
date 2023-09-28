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
    CdfNameSpaceAdminActions.clickOnNameSpaceAdminTab();
  }

  @Then("Click on create profile button for {string} Namespace")
  public void createProfileinsideNamespaceAdmin(String nameSpace) {
    CdfNameSpaceAdminActions.createProfileforNameSpace(nameSpace);
  }

  @Then("Click {string} tab from Configuration page for {string} Namespace")
  public void openNameSpaceAdminPageTabs(String tabName, String nameSpace) {
    CdfNameSpaceAdminActions.clickOnNameSpaceAdminTabs(tabName, nameSpace);
  }

  @Then("Click on edit namespace preferences to set namespace preferences")
  public void clickOnEditNameSpacePreferences() {
    CdfNameSpaceAdminActions.clickOnEditPreference();
  }
}
